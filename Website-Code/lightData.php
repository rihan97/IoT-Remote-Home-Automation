<!DOCTYPE html>
<html>
<head>
<img src="Home.jpg" alt="logo" style="width:200px;height:200px;">
<style>
table{
  border-collapse: collapse;
  width: 50%;
}

th, td{
  text-align: left;
  padding: 8px
}

tr:nth-child(even{background-color: #f2f2f2}html
html
th{
background-color: #4CAF50 
}
</style>
</head>

<body>
  <table id= "latest" border="3" background-color:#4CAF50;>
  <thead>
  <h1>
  Latest Light Readings
  </h1>
<p>

</P>

<p>
 (Sensor ID 1 = Light) 
</P>
<tr>
  <th> ID </th>
  <th> Sensor ID </th>
  <th> Light Reading </th>
  <th> Date & Time </th>
</tr>
<?php
$conn = mysqli_connect("localhost", "rayhan", "password", "HomeAutomation");
  // Check connection
  if ($conn->connect_error) {
   die("Connection failed: " . $conn->connect_error);
  } 
  #$sql = "SELECT id, sensor_id, value, dateTimeRecorded FROM sensorlog";
   $sql = "SELECT * FROM sensorlog WHERE sensor_id = 1";
  $result = $conn->query($sql);
  if ($result->num_rows > 0) {
   // output data of each row
   while($row = $result->fetch_assoc()) {
    echo "<tr><td>" . $row["id"]. "</td><td>" . $row["sensor_id"]. "</td><td>" . $row["value"]. "</td><td>" . $row["dateTimeRecorded"]. "</td></tr>";
}
echo "</table>";
} else { echo "0 results"; }
$conn->close();
?>
</table>
</body>
</html>