var app = require('express');
var http = require('http').Server(app);
var mysql = require('mysql');


http.listen(3000, ()=>{
  console.log('Server is listening to posrt 3000');
});


var io = require('socket.io')(http);
var admin = require("firebase-admin");
var serviceAccount = require(__dirname + "/private/serviceCred.json");


admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://final-college-project.firebaseio.com/"
});
var accountRequest = require('./Firebase/account-services');

accountRequest.userAccountRequests(io);



  //json object from class in android
  return admin.database().ref('/exercises/' + 'arms').once('value').then(function(snapshot){
    var name = (snapshot.val());
    console.log(name);
});
/*Database stuff from here on*/
