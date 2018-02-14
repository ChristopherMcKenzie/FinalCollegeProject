var app = require('express');
var http = require('http').Server(app);
var mysql = require('mysql');
var io = require('socket.io')(http);




var sqlCon = mysql.createConnection({
  host:"finalprojectdb.cljh7lrro0df.us-east-1.rds.amazonaws.com",
  user:"ChrisAdmin",
  password:"Chrismac12",
  database:"exercises"
});

var getExercises = (io) =>
{
  io.on('connection', (socket)=>
  {
    getAllArmExercises(io,socket);
  });
};


function getAllArmExercises(io,socket)
{
  io.on('connection', (socket)=>{
    sqlCon.connect(function(err){
      if (err) throw err;
      sqlCon.query("SELECT * FROM exerciseLists", function(err, result, fields)
      {
        if (err) throw err;
        console.log(result);
      });
    });
  });
}


module.exports =
{
  getExercises
}
