/**
 * Statistics
 * @author phelanvendeville
 * Class to handle the display and processing of game statistics.
 *
 * Note on classpath: javac -classpath charts4j-1.3.jar:junit-4.4.jar *.java
 * OR, you can put the .JAR:s in "/System/Library/Java/Extensions".
 */

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot; //xyplot
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.plot.PlotOrientation;
import java.awt.Color;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.axis.NumberAxis;

import java.util.ArrayList;

public class Statistics extends JFrame {
	//field declarations
	ArrayList<int[]> dataSet;

	/**
	  * unit test with sample data
	  */
	// public static void main(String[] args) {
	// 	ArrayList<int[]> historicalScores = new ArrayList<int[]>();
	// 	int[] testScores1 = new int[2];
	// 	int[] testScores2 = new int[2];
	// 	int[] testScores3 = new int[2];
	// 	testScores1[0] = 4;
	// 	testScores1[1] = 0;
	// 	testScores2[0] = 1;
	// 	testScores2[1] = 9;
	// 	testScores3[0] = 7;
	// 	testScores3[1] = 3;
	// 	historicalScores.add(testScores1);
	// 	historicalScores.add(testScores2);
	// 	historicalScores.add(testScores3);

	// 	Statistics statGraph = new Statistics("GoFish Stats!", "Summary of Play Scores", historicalScores);
	// 	statGraph.pack();
	// 	statGraph.setVisible(true);
 //      }

	/**
	  * Constructor
	  */
	public Statistics(String applicationTitle, String chartTitle, ArrayList<int[]> dataSet) {
        super(applicationTitle); //call JFrame constructor (superclass)
        this.dataSet = dataSet;

        //make a properly formatted dataset from our data
        XYDataset formattedSet = createDataset(dataSet);

        // Class cls = formattedSet.getClass(); 
        // System.out.println(cls.getName());

        //make chart with data and title 
        JFreeChart chart = createChart(formattedSet, chartTitle);
        
        //put chart on a panel
        ChartPanel chartPanel = new ChartPanel(chart);

        //set size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        
        //add it to the application
        setContentPane(chartPanel);

    }

    /**
	  * Create dataset formatted for the graph from our data
	  * @param our data
	  * @return formatted dataset
	  */
    private XYDataset createDataset(ArrayList<int[]> dataSet) {
    	XYSeries series1 = new XYSeries("Computer");
    	XYSeries series2 = new XYSeries("Human");
    	int counter = 1;

    	//add each piece of appropriate data to the right series
    	for (int[] game : dataSet) {   
		    // System.out.println("Computer score " + game[0] + " Player score " + game[1]);
		    series1.add(counter, game[0]);
		    series2.add(counter, game[1]);
		    counter++;
		}

		//add each series to a collection so it can be returned
		XYSeriesCollection formattedSet = new XYSeriesCollection();
		formattedSet.addSeries(series1);
		formattedSet.addSeries(series2);

		return formattedSet;
    }

    /**
	  * Create a JFreechart chart
	  * @param the formatted dataset, as well as a the title of the chart
	  * @return the chart itself
	  */
    private JFreeChart createChart(XYDataset formattedSet, String title) {

    	//make chart object
    	JFreeChart chart = ChartFactory.createXYLineChart(
            title,     				  // chart title
            "Games",                  // x axis label
            "Scores",                 // y axis label
            formattedSet,             // data
            PlotOrientation.VERTICAL, // plot orientation
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

    	XYPlot plot = chart.getXYPlot();

    	return chart;
    }
}