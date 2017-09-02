/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpertLegalPortalClass;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author USER
 */
public class PieChartDemo {
  public static JFreeChart getBarChart(){
  DefaultCategoryDataset dataset=new DefaultCategoryDataset();
  dataset.setValue(57, "Business", "Asia");
  dataset.setValue(57, "Business", "Africa");
  dataset.setValue(57, "Business", "America");
  dataset.setValue(57, "Business", "Indonesia");
  dataset.setValue(57, "Business", "Australia");
  return ChartFactory.createBarChart("Business distribution", "Location", "Business", dataset, PlotOrientation.VERTICAL, false, true, false);
  }
  public static JFreeChart getPieChart(){
   DefaultPieDataset dataset=new DefaultPieDataset();
  
  dataset.setValue("Asia", 57);
  dataset.setValue("Africa", 70);
  dataset.setValue("America", 40);
  dataset.setValue("Indonesia", 50);
  dataset.setValue("Australia", 60);
  return ChartFactory.createPieChart("Business distribution", dataset,true, true, false);
  }
  public static void convertToPdf(JFreeChart chart, int width, int height, String filename){
  Document document=new Document(new Rectangle(width, height));
  try{
      PdfWriter writer;
      writer=PdfWriter.getInstance(document, new FileOutputStream(filename));
      document.open();
      PdfContentByte cb=writer.getDirectContent();
      PdfTemplate tp=cb.createTemplate(width, height);
      Graphics2D g2d=tp.createGraphics(width, height, new DefaultFontMapper());
      Rectangle2D r2d=new Rectangle2D.Double(0, 0, width, height);
      chart.draw(g2d, r2d);
      g2d.dispose();
      cb.addTemplate(tp, 0, 0);
      
  }   catch (DocumentException ex) {
          Logger.getLogger(PieChartDemo.class.getName()).log(Level.SEVERE, null, ex);
      } catch (FileNotFoundException ex) {
          Logger.getLogger(PieChartDemo.class.getName()).log(Level.SEVERE, null, ex);
      }
  document.close();
  
  }
  
  public static void go(){
      convertToPdf(getBarChart(),640,480,"C:/Users/USER/Desktop/graph/bar.pdf");
      convertToPdf(getPieChart(),640,480,"C:/Users/USER/Desktop/graph/bar2.pdF");
  }
  
  
  public static void main(String[] args){
      try {
          //  try {
          ///  try {
          /*    go();
          int m=1;
          String month = "invalid";
          DateFormatSymbols dfs = new DateFormatSymbols();
          String[] months = dfs.getMonths();
          if (m >= 0 && m <= 11 ) {
          month = months[m];
          }
          System.out.print(month);*/
//          Connection con=dbconnection.getConnection();
//          System.out.println("v");
//          String sql = "INSERT INTO highestvalue (id, a1, a2, a3, dis, emp_passed_away, leave_extension, native_staff_shortage, salary_stopped_due_to_loss, terminated_emp_due_loss) VALUES(?,?,?,?,?,?,?,?,?,?)";
//          PreparedStatement ps2=null;
//          ps2=con.prepareStatement(sql);
//            ps2.setInt(1, 0);;
//          ps2.setInt(2, 0);
//          ps2.setInt(3, 0);
//          ps2.setInt(4, 0);
//          ps2.setInt(5, 0);
//          ps2.setInt(6, 0);
//          ps2.setInt(7, 0);
//          ps2.setInt(8, 0);
//          ps2.setInt(9, 0);
//          ps2.setInt(10, 0);
//         
//          ps2.executeUpdate();
//      } catch (SQLException ex) {
//          Logger.getLogger(PieChartDemo.class.getName()).log(Level.SEVERE, null, ex);
//      }
//      }
//}
          
          
          
//String UPLOAD_DIRECTORY ="C://Users//Sein 90//Desktop//up";
          
//String caseid1 = request.getParameter("caseid");
//          String dateS="";
//          String reasonS="";
//          String nameS="";
//          String unityS="";
//          String remarksS="";
//          String investigationS="";
//          String actionTakenS="";
//          String actionToBeTakenS="";
//          String peopleInvolvedS="";
//          String empIDS="";
          Connection con1=dbconnection.getConnection();
      //    String dbID="CCCCCC";
          PreparedStatement ps=null;
          ResultSet rs=null;
          String IDquery="SELECT * FROM attachment";
          ps=con1.prepareStatement(IDquery);
          rs=ps.executeQuery();
          
          
          while(rs.next())
          {
              int dbID=rs.getInt(1);
             String dateS=rs.getString(3);
             String date=rs.getString(4);
//              reasonS=rs.getString(3);
//              nameS=rs.getString(4);
//              unityS=rs.getString(5);
//              remarksS=rs.getString(6);
//              investigationS=rs.getString(7);
//              actionTakenS=rs.getString(8);
//              actionToBeTakenS=rs.getString(9);
//              peopleInvolvedS=rs.getString(10);
//              empIDS=rs.getString(11);
                   
      System.out.println(dbID+dateS+date);
          } 

      } catch (SQLException ex) {
          Logger.getLogger(PieChartDemo.class.getName()).log(Level.SEVERE, null, ex);
      }

  }
          
}