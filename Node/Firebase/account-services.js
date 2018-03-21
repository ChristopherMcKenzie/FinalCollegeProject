var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var admin = require("firebase-admin");

var userAccountRequests = (io) =>
{
  io.on('connection', (socket)=>
  {
    console.log(`User conneced on ${socket.id}`);
    detectDisconnection(socket, io);
    logUserIn(socket,io);
    registerUser(socket, io);
  });

};


function detectDisconnection(socket, io)
{
  socket.on('disconnect', ()=>
  {
    console.log('A client has discconected');
  });
}


function logUserIn(socket,io){
  socket.on('userInfo',(data)=>{
    admin.auth().getUserByEmail(data.email)
    .then((userRecord)=>{

      var db = admin.database();
      var ref = db.ref('users');
      var userRef = ref.child(encodeEmail(data.email));
      var uid = "2131-44";

      userRef.once('value',(snapshot) =>{
        var additionalClaims = {
          email:data.email
        };


        admin.auth().createCustomToken(uid, additionalClaims)
        .then((customToken) =>{

          Object.keys(io.sockets.sockets).forEach((id)=>{
            if (id == socket.id) {
              var token = {
                authToken:customToken,
                email:data.email,
                userName:snapshot.val().userName,
                weight: snapshot.val().weight,
                height:snapshot.val().height

              }

              console.log(token);

              console.log('User logged in');
              userRef.child('hasLoggedIn').set(true);

              io.to(id).emit('token',{token});
            }
          });

        }).catch((error)=>{
          console.log(error.message);

          Object.keys(io.sockets.sockets).forEach((id)=>{
            if (id == socket.id) {
              var token = {
                authToken:error.message,
                email:'error',
                photo:'error',
                displayName:'error',
                height:'error',
                weight:'error'
              }
              io.to(id).emit('token',{token});
            }
          });
        });
      });
    });
  });
}




function registerUser(socket,io){
  socket.on('userData',(data)=>{
    admin.auth().createUser({
      displayName:data.userName,
      email:data.email,
      password:data.password,
      height:data.height,
      weight:data.weight
    })
    .then((userRecord)=>{
      console.log('User was registered successfully');
      var db = admin.database();
      var ref = db.ref('users');
      var userRef = ref.child(encodeEmail(data.email));
      var date = {
        data:admin.database.ServerValue.TIMESTAMP
      };

      userRef.set({
        userName:data.userName,
        email:data.email,
        height:data.height,
        weight:data.weight,
        dateJoined:date,
        hasLoggedIn:false
      });

      Object.keys(io.sockets.sockets).forEach((id)=>{
        if (id == socket.id) {
          var message = {
            text:'Success'
          }
          io.to(id).emit('message',{message});
        }
      });


    }).catch((error)=>{
      Object.keys(io.sockets.sockets).forEach((id)=>{
        console.log(error.message);
        if (id == socket.id) {
          var message = {
            text:error.message
          }
          io.to(id).emit('message',{message});
        }
      });
    });
  });
}



function encodeEmail(email)
{
  return email.replace('.',',');
}


module.exports =
{
  userAccountRequests
}
