package View;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;


public class FlagPieChart extends FlagGameView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public static DefaultPieDataset dataset = new DefaultPieDataset();
	static ChartPanel frame1;
	public FlagPieChart(){
		
		  DefaultPieDataset data = getDataSet();
	      JFreeChart chart = ChartFactory.createPieChart3D("Correct Percentage",data,true,false,false);
	    //set percentage
	      PiePlot pieplot = (PiePlot) chart.getPlot();
	      DecimalFormat df = new DecimalFormat("0%");// decimals
	      NumberFormat nf = NumberFormat.getNumberInstance();//get a NumberFormat object
	      StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//获得StandardPieSectionLabelGenerator
	      pieplot.setLabelGenerator(sp1);
	  
	  
	      pieplot.setNoDataMessage("no data");
	      pieplot.setCircular(false);
	      pieplot.setLabelGap(0.02D);
	      pieplot.setIgnoreZeroValues(false);
	     frame1=new ChartPanel (chart,true);
	      chart.getTitle().setFont(new Font("sanserif",Font.BOLD,20));//title
	      PiePlot piePlot= (PiePlot) chart.getPlot();
	      piePlot.setLabelFont(new Font("sanserif",Font.BOLD,10));
	      chart.getLegend().setItemFont(new Font("sanserif",Font.BOLD,10));
	}
	 public static int right;
    public  static DefaultPieDataset getDataSet() {
         dataset.setValue("right",new Integer(right));
         dataset.setValue("wrong or left",new Integer(5-right));

        return dataset;
    }
    
    public ChartPanel getChartPanel(){
    	return frame1;
    	
    }
}