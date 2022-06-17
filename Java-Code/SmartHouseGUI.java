//This class is used for reading the data in from the serial port as well as displaying it 
// This class is where the actual GUI is built with all the buttons 
package uk.ac.Connected2EmbeddedApplication;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JTextArea;

import org.jfree.ui.RefineryUtilities;

import java.awt.TextArea;

import java.util.Date;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.Icon;


public class SmartHouseGUI extends JFrame implements SerialPortEventListener { //class called 

	/**
	 * 
	 */
	private static final long serialVersionUID = -5164047937305246047L;
	private JPanel contentPane; //the panel 
	private ArrayList<Double> tempList = new ArrayList<Double>(); // creating a list for Temperature to store the data
	private ArrayList<Double> ldrList = new ArrayList<Double>(); //creating a list for ldrList to store the data 
	private ArrayList<Double>motionList = new ArrayList <Double>(); //creating a list for motion to store the data
	private ArrayList<DataFile> dataList = new ArrayList<DataFile>(); //saving the csv data
	LiveChart combined = new LiveChart("Smart House",tempList,ldrList,motionList);
	int live=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) { // main method
		EventQueue.invokeLater(new Runnable() {
			public void run() { //method run for running  the serial port
				try {
					SmartHouseGUI frame = new SmartHouseGUI();
					frame.initialize();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	//Creating the Frame for  Graphical User Interface
	public SmartHouseGUI() { // The Graphical User Interface Window
		setBackground(new Color(255, 153, 102)); // setting the background colour
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //this is used so that all the frames are not closed when you close this windows
		setBounds(100, 100, 910, 588); // sets the size of the window
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE); // Setting background colour
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); //sets the borders
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textDisplay = new TextArea(); // creating text area for displaying the data coming in through the serial port
		textDisplay.setForeground(Color.RED);
		textDisplay.setFont(new Font("Arial", Font.PLAIN, 19)); //setting the font size and font style
		textDisplay.setBounds(284, 155, 363, 233); //set the size of the text area
		contentPane.add(textDisplay); //adding textArea to the contentPane
		
		
	// Code for opening the Temperature Chart	
		JButton btnTemperatureChart = new JButton("Temperature Chart"); // A button for Temperature Chart
		btnTemperatureChart.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 19)); //setting the font and size
		btnTemperatureChart.setBackground(Color.GREEN); //setting the background colour for the  button
		btnTemperatureChart.setToolTipText("Click to Open Temperature Chart");//This code is created for hover animation

		
		btnTemperatureChart.addActionListener(new ActionListener() { //adding action to the button so that it does something when the button is pressed	
			public void actionPerformed(ActionEvent arg0) { //allows the Temperature Chart class to run so that the Temperature can be displayed 
				TemperatureChart chartTemp = new TemperatureChart("Temperature Chart",tempList);//Calling the Temperature Chart class and giving name as chartTemp
				chartTemp.create();
				chartTemp.pack();
				RefineryUtilities.centerFrameOnScreen(chartTemp);
				chartTemp.setVisible(true);
			}
		});
		btnTemperatureChart.setBounds(340, 421, 233, 38); //setting the size of the button and the place
		contentPane.add(btnTemperatureChart); //adding the button to the content pane inside the window
	
	// Code for opening the LDR Chart
		JButton btnLdrChart = new JButton("LDR Chart"); //A button for LDR Chart
		btnLdrChart.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 19));//setting the font and size
		btnLdrChart.setBackground(new Color(255, 153, 102)); //setting the background colour for the  button
		btnLdrChart.setToolTipText("Click to Open LDR Chart"); //This code is created for hover animation

		btnLdrChart.addActionListener(new ActionListener() { //adding action to the button so that it does something when the button is pressed
			public void actionPerformed(ActionEvent arg0) { //allows the LDR Chart class to run so that the LDR can be displayed 
				
				LdrChart chart = new LdrChart("LDR Chart",ldrList); //Calling the LDRChart class and giving name as chartLDR
				chart.pack();
				RefineryUtilities.centerFrameOnScreen(chart);
				chart.setVisible(true);
				
			}
		});
		btnLdrChart.setBounds(45, 421, 206, 38); //setting the size of the buttons 
		contentPane.add(btnLdrChart); //adding the button to the gui window
		
		// Code for opening the Motion Chart
		JButton btnMotionChart = new JButton("Motion Chart"); // A button for Motion Chart
		btnMotionChart.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 19)); //Setting the size and font style for the button 
		btnMotionChart.setBackground(Color.MAGENTA); //Setting the background colour for the button
		btnMotionChart.setToolTipText("Click to Open Motion Chart"); //This code is created for hover animation

		btnMotionChart.addActionListener(new ActionListener() { //adding action to the button so that it does something when the button is pressed
			public void actionPerformed(ActionEvent arg0) { //allows the Motion Chart class to run so that the Motion data can be displayed 
					MotionChart chart = new MotionChart("Motion Chart",motionList);//Calling the LDRChart class and giving name as chartLDR
					chart.pack();
					RefineryUtilities.centerFrameOnScreen(chart);
					chart.setVisible(true);

				}
			
		});
		btnMotionChart.setBounds(663, 421, 206, 38); //setting the size of the motion chart button and placing it
		contentPane.add(btnMotionChart); //adding it to the content pane the GUI window
		
		//Code for turning the Live Chart on
		JButton btnLiveChartOn = new JButton(" Live Chart On"); // For turning the live chart on 
		btnLiveChartOn.setBackground(Color.LIGHT_GRAY);
		btnLiveChartOn.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 19));
		btnLiveChartOn.setToolTipText("Click to Turn On Live Chart");//This code is created for hover animation

		btnLiveChartOn.addActionListener(new ActionListener() { //adding action to the button so that it does something when the button is pressed
			public void actionPerformed(ActionEvent arg0) {
				live=1; //1 = live chart is on 
			}
		});
		btnLiveChartOn.setBounds(45, 484, 206, 35); //setting the size of the button
		contentPane.add(btnLiveChartOn); //adding the button to the content pane
		
		
		//Code for turning the Live Chart off
		JButton btnLiveChartOff = new JButton("Live Chart Off"); // for Turning the live chart off
		btnLiveChartOff.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 19));
		btnLiveChartOff.setBackground(Color.GRAY);
		btnLiveChartOff.setToolTipText("Click to Turn off Live Chart"); //This code is created for hover animation
		
		btnLiveChartOff.addActionListener(new ActionListener() { //adding action to the button so that it does something when the button is pressed
			public void actionPerformed(ActionEvent e) {
				live=0;//0= live chart off
			}
		});
		btnLiveChartOff.setBounds(663, 483, 206, 37); //setting the size of the button
		contentPane.add(btnLiveChartOff); //adding the button to the content pane
		
		//Code for Saving Data in CSV format
		JButton btnSaveData = new JButton("Save Data"); //declaring and creating a button for saving data in csv format
		btnSaveData.setBackground(Color.CYAN);
		btnSaveData.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 19));
		btnSaveData.setToolTipText("Click to Save the Data"); //This code is created for hover animation

		btnSaveData.addActionListener(new ActionListener() { //adding action to the button so that it does something when the button is pressed
			public void actionPerformed(ActionEvent e) {
				FileSaving file = new FileSaving(); //Using the Class FileSaving Class and using the rList Array list from FileSaving 
				file.setrList(dataList); //setting the name file to rList from the class FIleSaving
				try {///try catch used if the file not saved or no permission
					file.writeCSV(); // writing in the format of CSV
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSaveData.setBounds(355, 482, 206, 38); //Setting the size and place of the button	
		contentPane.add(btnSaveData); //Adding the button to gui pane
		
		//Code for Giving the GUI a title
		JTextArea txtrSmartHouse = new JTextArea();
		txtrSmartHouse.setEditable(false);
		txtrSmartHouse.setForeground(Color.GREEN);
		txtrSmartHouse.setBackground(Color.BLACK); // setting the colour of the text area to Red
		txtrSmartHouse.setFont(new Font("Arial", Font.BOLD, 36));//Setting the font style and font size 
		txtrSmartHouse.setText("Home Automation System"); //Giving a title
		txtrSmartHouse.setBounds(245, 24, 458, 54);//Giving the Text Area a size
		contentPane.add(txtrSmartHouse); //adding the Text Area to the content pane to Windows Builder
		
		//Text Area for showing if heating is on or off
		Heating = new JTextField();
		Heating.setForeground(Color.BLUE);
		Heating.setFont(new Font("Arial Black", Font.PLAIN, 21));//Setting the font style and size
		Heating.setBounds(322, 91, 278, 44); //setting the size of the text field
		contentPane.add(Heating); //adding the text field to content pane
		Heating.setColumns(10);
	}
	

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Serial Communication code 
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	SerialPort serialPort;
	//Identifying the most popular ports which get used
	private static final String PORT_NAMES[] = { 
			"/dev/tty.usbserial-A9007UX1", // Mac OS X
			"/dev/ttyUSB0", // Linux
			"COM16", // Windows //
	};

	//A buffered reader declared as an input which will get data from InputStreamReader
	//Done by converting bytes into character
	private BufferedReader input;	
	private static final int TIME_OUT = 2000; //Time used in milliseconds to block while waiting for the port to open
	private static final int DATA_RATE = 9600; //Declared the data rate as the default data rate per second for COM ports
	
	private TextArea textDisplay; //For displaying the actual data
	private JTextField Heating;//For Displaying if heating is on or off in GUI
	
	public void initialize() {
		CommPortIdentifier portId = null;
		Enumeration<?> portEnum = CommPortIdentifier.getPortIdentifiers();

		// Finding an instance of serial port from the  PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) { //if the port couldn't be found then do this 
			System.out.println("Could not find COM port.");
			return;
		}
		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// setting port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams so that the data can be read in 
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			serialPort.getOutputStream();

			// adding event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	// This is used when you stop using the port
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	//This is where reading in the data and printing occurs by handling the event on the serial port
	public synchronized void serialPlot(SerialPortEvent oEvent) {
		
	}
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) { // Asking if data is available
			try {
				Date d = new Date(); //getting the current date and time
				SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //Displays the date
				String datetime = dt.format(d);
				String inputLine=input.readLine(); //in CSV format
				
				String[] data = inputLine.split(","); // using to split the data
				double ldr = Double.parseDouble(data[0]); // this contains the LDR reading 
				double temp = Double.parseDouble(data[1]); // this contains the temperature reading 
				double motion = Double.parseDouble(data[2]); //this contains the motion reading
				tempList.add(temp); //this is for future use
				ldrList.add(ldr); //this is for future use
				motionList.add(motion); //this is for future use
				
				if (live==1){
					combined.create();
					combined.pack();
					RefineryUtilities.centerFrameOnScreen(combined);
					combined.setVisible(true);
				}
				
				//plotting the data in Live
				System.out.println(datetime+" > "+inputLine); //print on the console 
				textDisplay.append(datetime+" > "+inputLine+"\n"); //displaying the value on to the textArea
				
				
				if( (ldr ==0) && (motion==0) && (temp>=18)){// night time heating off 
					Heating.setText("Heating Off Night");//above 18 and this works
				}
				else if ( (temp<=24) && (motion==1)){// Day time 
					Heating.setText("Heating On- In House" );
				}
				else if (temp>=25&& (motion==1) && (motion==0)){//Too hot
					Heating.setText("Hot Heating off");
				}
				
				DataFile r = new DataFile(); //Using the class Record 
				r.setDate(datetime); //using setters to set 
				r.setTemp(temp);//using setters to set
				r.setMotion(motion);//using setters to set
				r.setLight(ldr);//using setters to set
				
				dataList.add(r);
				
			} catch (Exception e) {
				System.err.println(e.toString());
			}
	}
	}
}