<?php
//setting header to json
header('Content-Type: application/json');

//database
$host = 'localhost';
$user = 'rayhan';
$pass = 'password';
$db = 'HomeAutomation';


//get connection
$mysqli = new mysqli($host,$user,$pass,$db);

if(!$mysqli){
  die("Connection failed: " . $mysqli->error);
}

//query to get data from the table
$query = sprintf("SELECT sensor_id = 1, dateTimeRecorded FROM sensorlog");

//execute query
$result = $mysqli->query($query);

//loop through the returned data
$data = array();
foreach ($result as $row) {
  $data[] = $row;
}

//free memory associated with result
$result->close();

//close connection
$mysqli->close();

//now print the data
print json_encode($data);


