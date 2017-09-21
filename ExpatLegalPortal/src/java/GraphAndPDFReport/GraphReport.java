/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphAndPDFReport;

import ExpertLegalPortalClass.QueryClass;
import ExpertLegalPortalClass.dbconnection;
import SummaryReport.A1_Report;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class GraphReport {
    Connection con=dbconnection.getConnection();
     public void pp(HttpServletResponse response){
          BufferedInputStream buf = null;
                        ServletOutputStream myOut = null;
                    try {
                        myOut = response.getOutputStream();             
                        File myfile = new File(DEST + pdfName);

                        //set response headers
                        response.setContentType("application/pdf");         //I want to download a PDF file

                        response.addHeader(
                                "Content-Disposition", "inline; filename=" + pdfName);

                        response.setContentLength((int) myfile.length());

                        FileInputStream input = new FileInputStream(myfile);
                        buf = new BufferedInputStream(input);
                        int readBytes = 0;
                        //read from the file; write to the ServletOutputStream
                        while ((readBytes = buf.read()) != -1) {
                            myOut.write(readBytes);
                        }

                    } catch (IOException ioe) {
                        String s=ioe.getMessage();
                    } finally {

                        //close the input/output streams
                        if (myOut != null) {
                            try {
                                myOut.close();
                            } catch (IOException ex) {
                                Logger.getLogger(A1_Report.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        if (buf != null) {
                            try {
                                buf.close();
                            } catch (IOException ex) {
                                Logger.getLogger(A1_Report.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                      }  
}
     static final public String getMyDocPath(){
        String myDocuments = null;
            try {
                Process p =  Runtime.getRuntime().exec("reg query \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\" /v personal");
                p.waitFor();
                InputStream in = p.getInputStream();
                byte[] b = new byte[in.available()];
                in.read(b);
                in.close();
                myDocuments = new String(b);
                myDocuments = myDocuments.split("\\s\\s+")[4];
            } catch(Throwable t) {
                t.printStackTrace();
            }
       return myDocuments;      
   }
    
    private String DEST,pdfName;

    public String getDEST() {
        return DEST;
    }

    public String getPdfName() {
        return pdfName;
    }
   public void writeToPDF(String fromDate, String toDate, String caseName, Vector<DateReportItems> datesVec){
   
        Font blueFont = FontFactory.getFont(FontFactory.HELVETICA,8, Font.BOLD);
        try {
            DEST =getMyDocPath()+"\\Canteen\\";
            pdfName="EMP.pdf";
            File fN=new File(DEST);
            fN.mkdir();
            
            Document document = new Document(PageSize.A4, 0, 0, 0, 0);
            PdfWriter.getInstance(document, new FileOutputStream(DEST+pdfName));
            document.open();
           
           Paragraph p = new Paragraph("MeTL COMPANY LIMITED DAR ES SALLAM, TANZANIA", blueFont);
            p.setAlignment(Element.ALIGN_CENTER);         
            document.setMargins(1, 1, 1, 1);
            p.setSpacingBefore(1f);
                p.setSpacingAfter(1f);
            document.add(p);
            Paragraph p1 = new Paragraph("EXPAT DISCIPLINARY PORTAL", blueFont);
            p1.setAlignment(Element.ALIGN_CENTER);         
            document.setMargins(1, 1, 1, 1);
            p1.setSpacingBefore(1f);
            p1.setSpacingAfter(1f);
            document.add(p1);
            // first row
        
            
           Paragraph p3 = new Paragraph("Case Name:   "+caseName, blueFont);
           p3.setAlignment(Element.ALIGN_CENTER);         
            document.setMargins(1, 1, 1, 1);
            p3.setSpacingBefore(1f);
            p3.setSpacingAfter(1f);
            document.add(p3);

            Paragraph p2 = new Paragraph();
            p2.add(new Phrase("From:", FontFactory.getFont(FontFactory.HELVETICA,9, Font.BOLD)));
            p2.add(new Phrase(fromDate+"    ", FontFactory.getFont(FontFactory.HELVETICA,9)));
            p2.add(new Phrase("To:", FontFactory.getFont(FontFactory.HELVETICA,9, Font.BOLD)));
            p2.add(new Phrase(toDate+"    ", FontFactory.getFont(FontFactory.HELVETICA,9)));
            p2.setSpacingBefore(1f);
            p2.setSpacingAfter(5f);
            
        //    p2.add(new Phrase("ECOF"+"    ", FontFactory.getFont(FontFactory.HELVETICA,9)));
            p2.setAlignment(Element.ALIGN_CENTER); 
            document.add(p2);
            PdfPTable table = new PdfPTable(2);
            document.setMargins(1, 1, 1, 1);
            table.setWidthPercentage(70);
            table.setSpacingBefore(1f);
            table.setSpacingAfter(1f);
            int totalCases=0;
            table.addCell(new Phrase("Date", FontFactory.getFont(FontFactory.HELVETICA,7, Font.BOLD)));
            table.addCell(new Phrase("Total Case Per Day", FontFactory.getFont(FontFactory.HELVETICA,7, Font.BOLD)));
            for(int i=0;i<datesVec.size();i++){
              int month = Integer.parseInt(datesVec.get(i).getYear());
              table.addCell(new Phrase(getMonthForInt(month)+","+" "+datesVec.get(i).getMonth(), FontFactory.getFont(FontFactory.HELVETICA,7, Font.BOLD)));
              //table.addCell(new Phrase(datesVec.get(i).getMonth()+","+datesVec.get(i).getMonth(), FontFactory.getFont(FontFactory.HELVETICA,7, Font.BOLD)));
              table.addCell(new Phrase(Integer.toString(datesVec.get(i).getNumberOfCases()), FontFactory.getFont(FontFactory.HELVETICA,7, Font.BOLD)));
              //table.addCell(new Phrase(Integer.toString(datesVec.get(i).getNumberOfCases())+getMonthForInt(datesVec.get(i).getYear()), FontFactory.getFont(FontFactory.HELVETICA,7, Font.BOLD)));
              totalCases+=datesVec.get(i).getNumberOfCases();
            }
             table.addCell(new Phrase("TOTAl", FontFactory.getFont(FontFactory.HELVETICA,7, Font.BOLD)));
            table.addCell(new Phrase(""+totalCases, FontFactory.getFont(FontFactory.HELVETICA,7, Font.BOLD)));
            document.add(table);
            
            document.close();
            //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ pdfName);
        } catch (DocumentException ex) {
            Logger.getLogger(GraphReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GraphReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
   
   }
    String getMonthForInt(int num) {
        String month = "December";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num-1];
        }
        return month;
    }
       QueryClass objQuery=new QueryClass();
    
    public  Vector<DateReportItems> getReport(String fromDate, String toDate, String caseName) throws IOException, DocumentException, SQLException
    {
        Vector<DateReportItems> datesVec = new Vector();
        try {
                DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
                        Date startDate=df.parse(fromDate);
                        Calendar start = Calendar.getInstance();
                        start.setTime(startDate);
                        Date endDate=df.parse(toDate);
                        Calendar end = Calendar.getInstance();
                        end.setTime(endDate);
                        while(!start.after(end)){   
                            int totalNumberPerMonth=0;
                        int year=start.get(Calendar.YEAR);
                        int month=start.get(Calendar.MONTH)+1;
                        int day=start.get(Calendar.DAY_OF_MONTH);
                        int numberOfMonth =  numberOfDayPerMonth(month,year);
                        
                        String currentFromDate =year+"-"+getFormatedMonth(month)+"-01";
                        String currentToDate = year+"-"+getFormatedMonth(month)+"-"+numberOfMonth;
                       
                        String[] monthArray={"","JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG", "SEP", "OCT", "NOV","DEC"};
                        String fromAarrayDate[]= currentFromDate.split("-");
                        currentFromDate = fromAarrayDate[2]+"-"+monthArray[Integer.parseInt(fromAarrayDate[1])]+"-"+fromAarrayDate[0];   

                        String toArrayDate[]= currentToDate.split("-");
                        currentToDate = toArrayDate[2]+"-"+monthArray[Integer.parseInt(toArrayDate[1])]+"-"+toArrayDate[0];   
                        
                        
                        
                        if(caseName.equals("A1::Not returned from leave")){
                        totalNumberPerMonth = getNumberFromDB(objQuery.A1ReportQuery(currentFromDate, currentToDate));
                        }
                        else if(caseName.equals("A2::Not returned from leave")){
                        totalNumberPerMonth = getNumberFromDB(objQuery.A2ReportQuery(currentFromDate, currentToDate));
                        }
                        else if(caseName.equals("A3::Damage/Loss")){
                        totalNumberPerMonth = getNumberFromDB(objQuery.A3ReportQuery(currentFromDate, currentToDate));
                        }
                        else if(caseName.equals("DIS")){
                        totalNumberPerMonth = getNumberFromDB(objQuery.DisReportQuery(currentFromDate, currentToDate));
                        }
                        else if(caseName.equals("Salary Stopped::Due to Loss/Performance")){
                        totalNumberPerMonth = getNumberFromDB(objQuery.SalaryStoppedReportQuery(currentFromDate, currentToDate));
                        }
                        else if(caseName.equals("Terminated Employees::Due To Loss/Shortage")){
                        totalNumberPerMonth = getNumberFromDB(objQuery.TerminatedEmployeeReportQuery(currentFromDate, currentToDate));
                        }
                        else if(caseName.equals("Native Shortage Staff")){
                        totalNumberPerMonth = getNumberFromDB(objQuery.NativeStaffShortageReportQuery(currentFromDate, currentToDate));
                        }
                        else if(caseName.equals("Employee::Passed Away")){
                        totalNumberPerMonth = getNumberFromDB(objQuery.EmpPassedAwayReportQuery(currentFromDate, currentToDate));
                        }
                        else if(caseName.equals("Leave Extension")){
                        totalNumberPerMonth = getNumberFromDB(objQuery.LeaveExtensionReportQuery(currentFromDate, currentToDate));
                        }
                        datesVec.add(new DateReportItems(""+year,""+month ,"01-"+month+"-"+year,numberOfMonth+"-"+month+"-"+year,totalNumberPerMonth));
                   
                        start.add(Calendar.MONTH, 1);
}
        } catch (ParseException ex) {
            Logger.getLogger(GraphReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datesVec;
    }
    
     private int getNumberFromDB(String query){
        int res = 0;
        try {
            
            Connection con=dbconnection.getConnection();
            ResultSet resSet = null;
            PreparedStatement preSta=null;
            preSta=con.prepareStatement(query);
            resSet=preSta.executeQuery();
            while(resSet.next())
            {
                res= resSet.getInt(1);            
            }
              con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GraphReport.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      return res;  
        
    } 
    
     private String getFormatedMonth(int m){
         if(m>9)
             return m+"";
         else
             return "0"+Integer.toString(m);
     }
     
     
     public void caseReport(String fromDate, String toDate, String caseName)
    {        
        try {
            // Vector<String> empVec = getEmployeeIds();
            Vector<DateReportItems> datesVec = getReport(fromDate,toDate, caseName);
            writeToPDF(fromDate,toDate,caseName, datesVec);
        } catch (IOException ex) {
            Logger.getLogger(GraphReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GraphReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GraphReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
	private int numberOfDayPerMonth(int month, int year){
    
        int[] NumberODays={31,28,31,30,31,30,31,31,30,31,30,31};
        if(month==02 && year%4 == 0)
            return 29;        
        return NumberODays[month-1];
            
     }
}
