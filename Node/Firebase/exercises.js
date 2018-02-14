var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var admin = require("firebase-admin");

var exercisesPage = (io) =>
{
    io.on('connection', (socket) =>
  {
      console.log('Connected to firebase database');
      getExercisesArms(socket,io);
  });
};


function getExercisesArms(socket,io)
{
  //json object from class in android
  socket.on('exercises', (data)=>
{
  return admin.database().ref('/exercises/').once('value')
  .then(function(snapshot)
  {
      var name = (snapshot.val());
      console.log(name);
  });
});
}

module.exports =
{
  exercisesPage
}
