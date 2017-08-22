/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphAndPDFReport;

/**
 *
 * @author USER
 */
import com.itextpdf.awt.DefaultFontMapper;
import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import org.jfree.data.general.DefaultPieDataset; 
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.JFreeChart;
import java.awt.Color;
public class EmbedPieChartPDF {  
     public  void main(String[] args){
        try {
                /* Create Pie Chart */
                DefaultPieDataset myColoredPieChart = new DefaultPieDataset();                
                myColoredPieChart.setValue("Java", 12.9);
                myColoredPieChart.setValue("C++", 37.9);
                myColoredPieChart.setValue("C", 86.5);
                myColoredPieChart.setValue("VB", 80.5);
                myColoredPieChart.setValue("Shell Script", 19.5);                
                JFreeChart myColoredChart=ChartFactory.createPieChart("Programming - Colored Pie Chart Example",myColoredPieChart,true,true,false);
                /* Color Pie Chart */
                PiePlot ColorConfigurator = (PiePlot)myColoredChart.getPlot();                
                ColorConfigurator.setSectionPaint("Java", new Color(160, 160, 255));
                ColorConfigurator.setSectionPaint("C++", Color.ORANGE);
                ColorConfigurator.setSectionPaint("C", Color.GREEN);
                ColorConfigurator.setSectionPaint("VB", Color.PINK);
                ColorConfigurator.setSectionPaint("Shell Script", Color.YELLOW);               
                /* We have to insert this colored Pie Chart into the PDF file using iText now */                
                int width=640; /* Width of our chart */
                int height=480; /* Height of our chart */                
                Document PieChart=new Document(new Rectangle(width,height));             
                PdfWriter writer=PdfWriter.getInstance(PieChart,new FileOutputStream("My_Colored_Pie_Chart.pdf"));                
                PieChart.open();
                /* Add some Metadata to identify document later */
                PieChart.addTitle("How to color your Pie Chart and embed in a PDF file using iText");
                PieChart.addAuthor("Thinktibits");                
                PieChart.addKeywords("iText,Color PieChart,JFreeChart,PDF,Example Tutorial");                
                PdfContentByte Add_Chart_Content= writer.getDirectContent();                
                PdfTemplate template_Chart_Holder=Add_Chart_Content.createTemplate(width,height);                
                Graphics2D Graphics_Chart=template_Chart_Holder.createGraphics(width,height,new DefaultFontMapper());                
                Rectangle2D Chart_Region=new Rectangle2D.Double(0,0,540,380);                
                myColoredChart.draw(Graphics_Chart,Chart_Region);            
                Graphics_Chart.dispose();                
                Add_Chart_Content.addTemplate(template_Chart_Holder,0,0);                
                PieChart.close();
                
        }
        catch (Exception i)
        {
            System.out.println(i);
        }
    }
}