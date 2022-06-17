<?php
//Extract data from html form
$name = $_POST['name'];//storing in php variable
$sensorType = $_POST['sensorType'];//storing in php variable

//Connect to mysql db
$mysqli = mysqli_connect("localhost", "rayhan", "password", "HomeAutomation");

if (mysqli_connect_errno($mysqli)) {
   trigger_error('Database connection failed: '  . mysqli_connect_error(), E_USER_ERROR);
}

//Generate SQL INSERT query dynamically
$sql = "INSERT INTO sensor (name,sensorType) values('$name','$sensorType')";

//echo $sql;//just to debug if correct sql is created

//Now Execute the SQL INSERT query
if ($mysqli->query($sql) === TRUE) {
    echo "New record created successfully"; // displaying the message to the user
} else {
    echo "Error: " . $sql . "<br>" . $mysqli->error;
}
//now provide option to user to go to index.php, provide a hyperlink
?>
<br /><br />
<a href="index.php">Display all Sensors</a>


<form action="addaction.php" method="post">
    Sensor Name:<br />
    <input type="text" name="sensorname">
    <br />
    Sensor Type:
    <br /><input type="text" name="sensortype">
    <br /><br />
    <input type="submit" value="Add Sensor">
</form>