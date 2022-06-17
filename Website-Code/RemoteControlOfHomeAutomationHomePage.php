<html>
<body bgcolor="#AED6F1">
<head>

<h1><center><font size="100"> Remote Control Of Home Automation Home Page </font></center></h1>

<center><img src="home-automation.jpg" alt="mainimg" style="width:500px;height:420px;"></center>

</head>2
<h1>

</h1>

<!DOCTYPE html>
<html>
<head>
<style>
.dropbtn {
    background-color: #4CAF50;
    color: white;
    padding: 16px;
    font-size: 16px;
    border: none;
    cursor: pointer;
}

.dropdown {
    position: relative;
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}

.dropdown-content a:hover {background-color: #f1f1f1}

.dropdown:hover .dropdown-content {
    display: block;
}

.dropdown:hover .dropbtn {
background-color: #3e8e41;
}
</style>
</head>


<center><form action="http://localhost/Sensors.php">
    <input type="submit" value="Go to Sensors" />
</form></center>

<center><form action="http://localhost/AllData.php">
    <input type="submit" value="Complete Sensor Log" />
</form></center>

<center><form action="http://localhost/lightData.php">
    <input type="submit" value="Latest Light Reading" />
</form></center>

<center><form action="http://localhost/tempData.php">
    <input type="submit" value="Latest Temperature Reading" />
</form></center>

<center><form action="http://localhost/motionData.php">
    <input type="submit" value="Latest Motion Reading" />
</form></center>

<center>
<div class="dropdown">
  <button class="dropbtn">Go to Sensor Charts</button>
  <div class="dropdown-content">
    <center><a href=http://localhost/LightChart.php>ID : 1 - Light</a></center>
    <center><a href=http://localhost/tempChart.php>ID : 2 - Temperature</a></center>
    <center><a href=http://localhost/motionChart.php>ID : 3 - Motion</a></center>
  </div>
</div>
    

<h2>

</h2>
                           
                           
                           
<center><form>
<input type="submit" value="Heating On" onclick="alert('You have Turned On the Heating!')" onclick == 1/>
</form></center>
                           
<center><form>
<input type="submit" value="Heating Off" onclick="alert('You have Turned Off the Heating!')" onclick == 2/>
</form></center>

<center><form>
<input type="submit" value="Water On" onclick="alert('You have Turned On the Water!')" onclick == 3/>
</form></center>
                           
<center><form>
<input type="submit" value="Water Off" onclick="alert('You have Turned Off the Water!')" onclick == 4/>
</form></center>
            
<center><form>
<input type="submit" value="Lights On" onclick="alert('You have Turned On the Lights!')" onclick == 5/>
</form></center>
                           
<center><form>
<input type="submit" value="lights Off" onclick="alert('You have Turned Off the Lights!')" onclick == 6/>
</form></center>

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
</body>
</html>
