//This class is used for displaying the live Chart for the three sensors temperature, motion and ldr
package uk.ac.mmu.cnt2.Connected2EmbeddedApplication;


import java.awt.Dimension;
import java.util.ArrayList;
//import java.util.List;


import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;


public class LiveChart extends JFrame {
	
	//Creating a list for each data type to load data from the files
	private static ArrayList<Double> Temperature;// creating array List for Temperature 
    private static ArrayList<Double> Motion;
    private static ArrayList<Double> LDR;
	private static final long serialVersionUID = 1L;
	
	public LiveChart(String s,ArrayList<Double> temp,ArrayList<Double> Mt,ArrayList<Double> light) // Inserting the lists of the data for chart
	{
		super(s);
		Temperature = temp;
		Motion = Mt;
		LDR = light;
		
	}
	
	public void create() //Creating window to display the live chart
	{
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //this is used so that all the frames are not closed when you close this windows
		JPanel jpanel = createPanel(); // creates the window
		jpanel.setPreferredSize(new Dimension(650, 400)); //sets the size of the window
		setContentPane(jpanel);
	}
	
		
	//Creating the dataset
	private XYDataset createDataset()
	{
				XYSeriesCollection xyseriescollection = new XYSeriesCollection();
				XYSeries xyseries = new XYSeries("Celcius"); //Create the reading for temperature
				XYSeries xyseries1 = new XYSeries("Light"); //Create the reading for the light
				XYSeries xyseries2 = new XYSeries("Motion"); //Create the reading for the Motion
				int i=1;
				
				for(Double temp:Temperature){ // reading the lists and plots the values for Temperature 
					xyseries.add(i,temp);
					++i;
				}
				i=1;
				
				for(Double temp:Motion){ //reading the lists and plots the values for Motion
					xyseries1.add(i,temp);
					++i;
				}
				i=1;
				
				for(Double temp:LDR){ ////reading the lists and plots the values for LDR
					xyseries2.add(i,temp);
					++i;
				}
				xyseriescollection.addSeries(xyseries);//Add's the line to the collection
				xyseriescollection.addSeries(xyseries1);//Add's the line to the collection
				xyseriescollection.addSeries(xyseries2);//Add's the line to the collection
				return xyseriescollection; //Returns the collection
				
	}
	
		
	private JFreeChart createChart(XYDataset xydataset, String str)// //Method for creating and displaying the Live Chart 
	{
		//Set the headers for the panels
		JFreeChart jfreechart = ChartFactory.createXYLineChart(str , "Number Of Readings", "Value", xydataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot(); //Creating  object 
		ValueAxis axis = xyplot.getDomainAxis();
		axis.setAutoRange(true);
        axis.setFixedAutoRange(10.0);
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyplot.getRenderer();
		xylineandshaperenderer.setBaseShapesVisible(true);
		xylineandshaperenderer.setBaseShapesFilled(true);
		NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		return jfreechart;
	}
	

	public JPanel createPanel() //creating panel inside the window
	{
		JFreeChart jfreechart = createChart(createDataset(),"Live Chart for LDR,PIR, TEMP");
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}
}
