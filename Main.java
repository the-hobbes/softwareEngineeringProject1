import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class Main {
   public static void main(String[] args) {
          PieChart demo = new PieChart("Comparison", "Which operating system are you using?");
          demo.pack();
          demo.setVisible(true);
      }
} 