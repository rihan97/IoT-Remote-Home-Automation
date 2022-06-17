//This class is used for displaying the Motion reading

package uk.ac.Connected2EmbeddedApplication;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;


public class MotionChart extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static ArrayList<Double> motion; //making an ArrayList called motion
	public MotionChart(String s,ArrayList<Double> Motion)
	{	
		super(s);		
		motion = Motion;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //this is used so that all the frames are not closed when you close this windows
		JPanel jpanel = createDemoPanel(); // creates the window
		jpanel.setPreferredSize(new Dimension(500, 270)); //sets the size of the window
		setContentPane(jpanel);
	}

	private static XYDataset createDataset() //method for getting motion data to display
	{
		//creating data set
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		XYSeries xyseries = new XYSeries("Motion");
		int i=1;
		for(Double ldr:motion){
			xyseries.add(i,ldr);
			++i;
		}
		
		xyseriescollection.addSeries(xyseries);//Add's the line to the collection
		return xyseriescollection;
		
		
	}

	private static JFreeChart createChart(XYDataset xydataset) //Method for displaying the Motion Chart 
	{
		JFreeChart jfreechart = ChartFactory.createXYLineChart(" Motion Chart", "Number Of Readings", "Value", xydataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyplot.getRenderer();
		xylineandshaperenderer.setBaseShapesVisible(true);
		xylineandshaperenderer.setBaseShapesFilled(true);
		NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		return jfreechart;
	}

	public static JPanel createDemoPanel()// creating panel inside the window
	{
		JFreeChart jfreechart = createChart(createDataset());
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}

}