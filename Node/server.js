var app = require('express')();

var http = require('http').Server(app);
var mysql = require('mysql');
//Connects clients to server
var io = require('socket.io')(http);

var admin = require("firebase-admin");

var serviceAccount = require(__dirname + '/private/serviceCred.json');

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://final-year-project-9b6cb.firebaseio.com"
});

var accountRequest = require('./Firebase/account-services');
var exerciseRequest = require('./DatabaseFolder/database');

exerciseRequest.getExercises(io);
accountRequest.userAccountRequests(io);
//port number
//=> arrow function and constructor
http.listen(62001, ()=>{
  console.log('Server is listening on port 62001');
});
//dsd

//to make it a proper list add "mon" to the monday and so on
//then use a regex to take out the "mon" and add a "/n"
