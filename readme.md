### Remotely Control and Analysis of Home Automation devices using IoT

Building an embedded system that allows different appliances or objects within a house to be controlled both; automatically and wirelessly (remotely via a single centralised node). The singular node being a web server, hosting a website which includes an integrated application built-in. Where the user can control the appliances of the house manually, remotely via a connection to the internet. Home appliances include; heating, water, security system, kettle and etc. 

The main core system is built on a Arduino Mega and programmed in C, which uses an wireless communication module for the transmission of data to the Master Controller being the Raspberry Pi (server side). The Raspberry Pi hosts a website; where the real-time data generated from the client system (Arduino) is forwarded to, for the data to be analysed and represented graphically in real-time and non-real-time graphs.

The additional automation aspects of the project uses surroundings to control the heating, lighting and water of the house when manual controlment is not wanted. these consists of following statements for the optimum end experience;
Firstly, if there is no motion (using the PIR sensor) within the house for 30 mins as well as no light (daylight/ house light) detected (using LDR sensor) and the temperature is above 17 degrees Celsius (using temperature sensor), then the heating would turn off automatically as the system detects is believed to think it’s night time.

Secondly, if the temperature of the house is less than or equal to 16 degrees Celsius and motion within the house is detected, then the heating would automatically turn on as the system will be programmed to believe that people are inside the house and the house is quite cold, hence heating needs to be turned on.

Thirdly, if the temperature of the house goes above 24 degrees Celsius then the heating would automatically turn off, as the house is quite hot and not comfortable to stay in.


Further features of the system include intrusion detection mechanisms and alerting systems. Also, smart decisions are made by the system on ways for the user to reduce the overall power costs and consumption's of the house. The data is logged and timestamped into a database allowing the user to access it at any given time.
