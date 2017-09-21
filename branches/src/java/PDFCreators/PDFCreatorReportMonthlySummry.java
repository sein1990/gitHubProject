/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PDFCreators;

import JavaCodePackage.ConstantClass;
import ReportItems.PDFReportDataMonthlyEmpWise;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author user
 */
public class PDFCreatorReportMonthlySummry {
    
    private  Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
                        Font.BOLD);
    private  Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                        Font.NORMAL, BaseColor.RED);
    private  Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
                        Font.BOLD);
    private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                        Font.BOLD);
    
    private String pdfName;
    private String path;
    Map<String,String> StatusMap;
    public PDFCreatorReportMonthlySummry(Map<String,String> statusMap){
       StatusMap = statusMap;
       path = ConstantClass.getMyDocPath()+"\\branchesReport\\";
       pdfName = "report.pdf";
       File f = new File(path);
       f.mkdir();
    }
    
    public void generatePDF(PDFReportDataMonthlyEmpWise reportData) {
        try {  
            
           
            Document document = new Document(PageSize.A4, 10, 10, 20, 20);
            PdfWriter.getInstance(document, new FileOutputStream(path+pdfName));
            document.open();
            
            
            String BN= reportData.getBranchName().substring(0,reportData.getBranchName().indexOf("-"));
            Paragraph p = new Paragraph("MeTL-"+BN, FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
            p.setAlignment(Element.ALIGN_CENTER);   
            document.setMargins(1, 1, 1, 1);
            p.setSpacingBefore(1f);
            p.setSpacingAfter(1f);
            document.add(p);
            
            String EMPNAME=reportData.getEmpName().substring(0,reportData.getEmpName().indexOf("-"));
            Paragraph p2 = new Paragraph("Attendance Report of " +EMPNAME+"   "+reportData.getMonth()+ "  "+reportData.getYear(), FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
            p2.setAlignment(Element.ALIGN_CENTER);   
            document.setMargins(1, 1, 1, 1);
            p2.setSpacingBefore(1f);
            p2.setSpacingAfter(1f);
            document.add(p2);
            
            Paragraph p3 = new Paragraph(" ");
            document.add(p3);      
            PdfPTable table = createTable();
            document.add(table);
            document.close();

//            if (pdfName.endsWith(".pdf")) {
//                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + pdfName);
//            }
        } catch (Exception e) {
                e.printStackTrace();
        }
        
     } 
     private PdfPTable createTable() throws BadElementException, DocumentException, ParseException {
            PdfPTable table = new PdfPTable(2);
            table.setWidths(new int[]{5,5});
            table.setTotalWidth(PageSize.A4.getWidth());
            table.setWidthPercentage(100);

            Phrase ph = new Phrase("Status", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
            PdfPCell c1 = new PdfPCell(ph);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
                
            c1 = new PdfPCell(new Phrase("Number of Days", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
            c1= new PdfPCell(new Phrase("Absent", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(c1);
           
            c1= new PdfPCell(new Phrase(getStatus(ConstantClass.AbsentCode), FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(c1);

            c1= new PdfPCell(new Phrase("Present", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(c1);
            
            c1= new PdfPCell(new Phrase(getStatus(ConstantClass.PresentCode), FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(c1);
            
            c1= new PdfPCell(new Phrase("Half Day", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(c1);
            
            c1= new PdfPCell(new Phrase(getStatus(ConstantClass.HalfDayCode), FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(c1);
            
            c1= new PdfPCell(new Phrase("Annual Leave", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(c1);
            
            c1= new PdfPCell(new Phrase(getStatus(ConstantClass.AnnualCode), FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(c1);
            
            c1= new PdfPCell(new Phrase("Maternity Leave", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(c1);
            
            c1= new PdfPCell(new Phrase(getStatus(ConstantClass.MatCode), FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(c1);
            
            c1= new PdfPCell(new Phrase("Paternity Leave", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(c1);
            c1= new PdfPCell(new Phrase(getStatus(ConstantClass.PatCode), FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(c1);
         
            c1= new PdfPCell(new Phrase("Compassionate Leave", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(c1);
            c1= new PdfPCell(new Phrase(getStatus(ConstantClass.CompCode), FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(c1);
            
            return table;
        }
        
        private String getStatus(String Status){
            
            if(StatusMap.containsKey(Status))
                return StatusMap.get(Status);
                        
            return "0";
            
        }
       
            public String getPath() {
        return path;
    }

    public String getPdfName() {
        return pdfName;
    }
    
    
}
