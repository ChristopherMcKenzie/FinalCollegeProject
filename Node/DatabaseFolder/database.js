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
  sqlCon.connect();

  io.on('connection', (socket)=>
  {
    console.log("Connected to database");
    addUserWorkout(io,socket);
    testInsert();
  });
};


function testInsert()
{
  var query = "INSERT INTO `exercises`.`user_workout` (`user_email`, `monday`, `thusday`, `wednesday`, `thursday`, `friday`, `saturday`, `sunday`) VALUES (?);";
  var testarray = ['EMAIL', 'mon', 'tues', 'wed', 'thurs', 'fri', 'sat', 'sun'];

  sqlCon.query(query,[testarray], function(err, result){
    if(err) throw err;
    console.log(result.affectedRows);
  });

}

function addUserWorkout(io, socket)
{
  io.on('addWorkout',(data)=>{
    admin.auth.getUserByEmail(data.email)
    .then((userWorkoutRecord)=>{
      console.log("User email saved" + data.email );
      var db = admin.database();
      var ref = db.ref('users');
      var userRef = ref.child(encodeEmail(data.email));
      sqlCon.beginTransaction(function(err){
        var query = "INSERT INTO `exercises`.`user_workout` (`user_email`, `monday`, `thusday`, `wednesday`, `thursday`, `friday`, `saturday`, `sunday`)"
        + " VALUES (?)";

        var workoutinfo = [userRef, data.mon, data.tues, data.wed, data.thurs, data.fri, data.sat, data.sun];

        sqlCon.query(query,[workoutinfo], function(err, result){

        });
      });
    });
  });
}



function getAllArmExercises(io,socket)
{
  console.log("get all arm stuff");
    sqlCon.query("SELECT * FROM exerciseLists", function(err, result, fields)
    {
      if (err) throw err;
      console.log(result);
      /*Object.keys(io.sockets.sockets).forEach((id)=>
      {
        if(id == socket.id)
        {
          var results =
          {
            name:data.name,
            description:data.desc
          }
          console.log("Working");
          io.to(id).emit('exerciseArm',{results});
        }
      });*/
    });
}

module.exports =
{
  getExercises
}
