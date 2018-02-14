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


accountRequest.userAccountRequests(io);
//port number
//=> arrow function and constructor
http.listen(62001, ()=>{
  console.log('Server is listening on port 3000');
});

var con = mysql.createConnection({
  host: "finalprojectdb.cljh7lrro0df.us-east-1.rds.amazonaws.com",
  user: "ChrisAdmin",
  password: "Chrismac12",
  database: "exercises"
});

con.connect(function(err) {
  if (err) throw err;
  console.log("Connected!");
});
