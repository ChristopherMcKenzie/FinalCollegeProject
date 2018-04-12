var app = require('express');
var http = require('http').Server(app);
var mysql = require('mysql');
var io = require('socket.io')(http);
var admin = require('firebase-admin');



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
    console.log("Connected to database");
  //  addUserWorkout(io,socket);
    //getExercises(io, socket);
    getAllArmExercises(io, socket);
    //testInsert();

  });
};

/*
function testInsert()
{
  var query = "INSERT INTO `exercises`.`user_workout` (`user_email`, `monday`, `thusday`, `wednesday`, `thursday`, `friday`, `saturday`, `sunday`) VALUES (?);";
  var testarray = ['EMAIL', 'mon', 'tues', 'wed', 'thurs', 'fri', 'sat', 'sun'];

   on.query(query,[testarray], function(err, result){
    if(err) throw err;
    console.log(result.affectedRows);
  });

}
*/
function getExercises(io, socket)
{
  socket.once('connect', function()
  {
  console.log("get all arm stuff");
    sqlCon.query("SELECT * FROM exercise_list", function(err, result, fields)
    {
      if (err) throw err;
      console.log(result);
      Object.keys(io.sockets.sockets).forEach((id)=>
      {
        if(id == socket.id)
        {
          var results =
          {
            name:data.bodyType,
            description:data.exName
          }
          console.log("Working");
          io.to(id).emit('exerciseArm',{results});
        }
      });
    });
  });
  }


function addUserWorkout(io, socket)
{
  io.on('addWorkout',(data)=>{
      sqlCon.beginTransaction(function(err){
      var query = "INSERT INTO `exercises`.`user_workout` (`user_email`, `monday`, `thusday`, `wednesday`, `thursday`, `friday`, `saturday`, `sunday`)"
      + " VALUES (?)";

      var workoutinfo = [userRef, data.mon, data.tues, data.wed, data.thurs, data.fri, data.sat, data.sun];

      sqlCon.query(query,[workoutinfo], function(err, result){
          console.log(err);
        });
      });
    });
  }




function getAllArmExercises(io,socket)
{

  console.log("get all arm stuff");
    sqlCon.query("SELECT * FROM exercise_list", function(err, result, fields)
    {
      if (err) throw err;
      console.log(result);
      Object.keys(io.sockets.sockets).forEach((id)=>
      {
        if(id == socket.id)
        {
          var results =
          {
            bodyPart: result.bodypart,
            name: result.exName
          };

          console.log(results);
          io.to(id).emit('exerciseArm',{results});
        }
      });
    });
}

module.exports =
{
  getExercises
}
