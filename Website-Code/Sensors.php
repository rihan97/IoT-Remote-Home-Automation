<!DOCTYPE html>
<form action="addaction.php" method="post">
    Sensor Name:<br />
    <input type="text" name="sensorname">
    <br />
    Sensor Type:
    <br /><input type="text" name="sensortype">
    <br /><br />
    <input type="submit" value="Add Sensor">
</form>

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
  Home-Automation Sensors
  </h1>
  <p>

</P>

<p>
 (Sensors Table) 
</P>

<tr>
  <th> ID </th>
  <th> Sensor Name </th>
  <th> Location </th>
  <th> Sensor Type </th>
</tr>

<?php
//connect to mysql db, provide hostname, login privileges, dbname
$mysqli = mysqli_connect("localhost", "rayhan", "password", "HomeAutomation");


if (mysqli_connect_errno($mysqli)) {
   trigger_error('Database connection failed: '  . mysqli_connect_error(), E_USER_ERROR);
}

$query = "SELECT * from sensor"; //this is the SELECT query to select all records

//query executed and result stored in variable $result.
$result = mysqli_query($mysqli, $query) or trigger_error("Query Failed! SQL: $query - Error: ". mysqli_error($mysqli), E_USER_ERROR);

?>
<!--this is html comment -->
<!--now we provide html to create table, to display data -->
<!--first row is header and is fixed, so we type it -->


<?php
//all other rows are now coming from the database 
//this is availale in $result. This has multiple rows, so we use loop to go through all of them
//for each row, we create a html row and display the php variable
if($result)
{   
    while($row = mysqli_fetch_assoc($result)) { //go through all rows        
        echo "<tr>";
        echo "<td>".$row['id']."</td>";
        echo "<td>".$row['name']."</td>";
        echo "<td>".$row['location']."</td>";
        echo "<td>".$row['sensorType']."</td>";
        echo "</tr>";
    }//end of while
}//end of if

?>
</table>
