/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SummaryReport;

import ExpertLegalPortalClass.QueryClass;
import ExpertLegalPortalClass.dbconnection;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class LeaveExtension_Report {
    Connection con1=dbconnection.getConnection();
      QueryClass objQuery=new QueryClass();
    public void LeaveExtensionReport(String caseidupdate) throws IOException, DocumentException, SQLException
    {
        LeaveExtension_ReportItems attachItems = getLeaveExtension_Data(caseidupdate);
        Vector<AttachmentRemarks> a1remarksArray=a1Remarks(caseidupdate);
        writeToPDF(attachItems,a1remarksArray);
    }
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
     public LeaveExtension_ReportItems getLeaveExtension_Data(String caseidupdate){
 
        LeaveExtension_ReportItems attachItems = null; 
         try {
             String dbID="";
             String caseid1="";
             PreparedStatement ps=null;
             ResultSet rs=null;
             String IDquery=objQuery.leaveExtension(caseidupdate);
             ps=con1.prepareStatement(IDquery);
             rs=ps.executeQuery();
             if(rs.next())
                 attachItems=new LeaveExtension_ReportItems(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
             
         } catch (SQLException ex) {
             Logger.getLogger(A1_Report.class.getName()).log(Level.SEVERE, null, ex);
         }
         return attachItems;
    }
     
    public Vector<AttachmentRemarks>  a1Remarks(String caseidupdate){
         Vector<AttachmentRemarks> a1remarksArray = new Vector();
       
        try {   
            PreparedStatement ps=null;
            ResultSet rs=null;
            String IDquery=objQuery.Remarks(caseidupdate);
            ps=con1.prepareStatement(IDquery);
            rs=ps.executeQuery();
            while(rs.next())
            {
                 a1remarksArray.add(new AttachmentRemarks(rs.getString(1)));
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(A1_Report.class.getName()).log(Level.SEVERE, null, ex);
        }
         return a1remarksArray;
    }
   public void writeToPDF(LeaveExtension_ReportItems attachItems, Vector<AttachmentRemarks> a1remarksArray){
    
         Font blueFont = FontFactory.getFont(FontFactory.HELVETICA,10, Font.BOLD);
        try {
            DEST =getMyDocPath()+"\\Canteen\\";
            pdfName="dpt.pdf";
            File fN=new File(DEST);
            fN.mkdir();
            com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4, 40, 20, 10, 10);
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
            Paragraph p2 = new Paragraph("LEAVE EXTENSION ", blueFont);
            p2.setAlignment(Element.ALIGN_CENTER);         
            document.setMargins(1, 1, 1, 1);
            p2.setSpacingBefore(1f);
            p2.setSpacingAfter(1f);
            document.add(p2);
            Paragraph p3 = new Paragraph("SUMMARY REPORT ", blueFont);
            p3.setAlignment(Element.ALIGN_CENTER);         
            document.setMargins(1, 1, 1, 1);
            p3.setSpacingBefore(25f);
            p3.setSpacingAfter(1f);
            document.add(p3);
           
            Paragraph p4 = new Paragraph("CASE ID:       "+attachItems.getCaseID(), blueFont);
            p4.setAlignment(Element.ALIGN_LEFT);         
            document.setMargins(1, 1, 1, 1);
            p4.setSpacingBefore(1f);
            p4.setSpacingAfter(10f);
            document.add(p4);
           
//            Paragraph p5 = new Paragraph("DATE:            "+attachItems.getDate(), blueFont);
//            p5.setAlignment(Element.ALIGN_LEFT);         
//            document.setMargins(1, 1, 1, 1);
//            p5.setSpacingBefore(1f);
//            p5.setSpacingAfter(10f);
//            document.add(p5);
//            Paragraph p6 = new Paragraph("REASON:       "+attachItems.getReason(), blueFont);
//            p6.setAlignment(Element.ALIGN_LEFT);         
//            document.setMargins(1, 1, 1, 1);
//            p6.setSpacingBefore(1f);
//            p6.setSpacingAfter(10f);
//            document.add(p6);
            Paragraph p7 = new Paragraph("NAME:            "+attachItems.getName(), blueFont);
            p7.setAlignment(Element.ALIGN_LEFT);         
            document.setMargins(1, 1, 1, 1);
            p7.setSpacingBefore(1f);
            p7.setSpacingAfter(10f);
            document.add(p7);
           
            Paragraph p8 = new Paragraph("UNIT:              "+attachItems.getUnity(), blueFont);
            p8.setAlignment(Element.ALIGN_LEFT);         
            document.setMargins(1, 1, 1, 1);
            p8.setSpacingBefore(1f);
            p8.setSpacingAfter(10f);
            document.add(p8);
            Paragraph p9 = new Paragraph("FROM:    "+attachItems.getFrom(), blueFont);
            p9.setAlignment(Element.ALIGN_LEFT);         
            document.setMargins(1, 1, 1, 1);
            p9.setSpacingBefore(1f);
            p9.setSpacingAfter(10f);
            document.add(p9);
            
            Paragraph p10 = new Paragraph("TO:    "+attachItems.getTo(), blueFont);
            p10.setAlignment(Element.ALIGN_LEFT);         
            document.setMargins(1, 1, 1, 1);
            p10.setSpacingBefore(1f);
            p10.setSpacingAfter(10f);
            document.add(p10);
            
            Paragraph p11 = new Paragraph("EXTENDED DAY:      "+attachItems.getExtendedDay(), blueFont);
            p11.setAlignment(Element.ALIGN_LEFT);         
            document.setMargins(1, 1, 1, 1);
            p11.setSpacingBefore(1f);
            p11.setSpacingAfter(10f);
            document.add(p11);
            
            Paragraph p12 = new Paragraph("ACTUAL DAY:         "+attachItems.getActual(), blueFont);
            p12.setAlignment(Element.ALIGN_LEFT);         
            document.setMargins(1, 1, 1, 1);
            p12.setSpacingBefore(1f);
            p12.setSpacingAfter(10f);
            document.add(p12);
            
            Paragraph p13 = new Paragraph("ACTION TAKEN :    "+attachItems.getActionTaken(), blueFont);
            p13.setAlignment(Element.ALIGN_LEFT);         
            document.setMargins(1, 1, 1, 1);
            p13.setSpacingBefore(1f);
            p13.setSpacingAfter(10f);
            document.add(p13);
            
            Paragraph p14 = new Paragraph(" EMPLOYEE ID:    "+attachItems.getEmpID(), blueFont);
            p14.setAlignment(Element.ALIGN_LEFT);         
            document.setMargins(1, 1, 1, 1);
            p14.setSpacingBefore(1f);
            p14.setSpacingAfter(10f);
            document.add(p14);  
            Paragraph p15 = new Paragraph(" ATTACHMENT REMARKS:    ",blueFont);
            p15.setAlignment(Element.ALIGN_LEFT);         
            document.setMargins(1, 1, 1, 1);
            p15.setSpacingBefore(20f);
            p15.setSpacingAfter(10f);
            document.add(p15);
             for (AttachmentRemarks a1remarksArray1 : a1remarksArray) {
                 Paragraph p16 = new Paragraph(a1remarksArray1.getAttachmentRemarks(), blueFont);
                 p16.setAlignment(Element.ALIGN_LEFT);
                 document.setMargins(1, 1, 1, 1);
                 p16.setSpacingBefore(1f);
                 p16.setSpacingAfter(10f);
                 document.add(p16);
             }
            document.close();
        }
       catch (IOException ex) {
            Logger.getLogger(A1_Report.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
             Logger.getLogger(A1_Report.class.getName()).log(Level.SEVERE, null, ex);
         }
       
    }
    
    
}
