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
<<<<<<< HEAD
  return admin.database().ref('/exercises/').once('value')
  .then(function(snapshot)
  {
      var name = (snapshot.val());
      console.log(name);
=======
return admin.datbase().ref('/exercises/' + 'arms').once('value').then(function(snapshot){
      var name = (snapshot.val() && snapshot.val().biceps)
>>>>>>> b4425719f1dea599e0d64979382e59302493d6a7
  });
});
}

module.exports =
{
  exercisesPage
}
