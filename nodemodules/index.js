const vandium = require('vandium');

var mysql = require('mysql');

exports.handler = vandium.generic()
    .handler( (event, context, callback) => {

    var sqlCon = mysql.createConnection({
      host:"finalprojectdb.cljh7lrro0df.us-east-1.rds.amazonaws.com",
      user:"ChrisAdmin",
      password:"Chrismac12",
      database:"exercises"
    });

	sqlCon.query('SELECT * FROM exercise_list', function (error, results, fields) {

	callback( null, results );
		
  });
});