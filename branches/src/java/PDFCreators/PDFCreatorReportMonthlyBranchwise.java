/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PDFCreators;

import JavaCodePackage.ConstantClass;
import ReportItems.PDFReportDataBranchMonthlyVecItem;
import ReportItems.PDFReportDataMonthlyBranchWise;
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
public class PDFCreatorReportMonthlyBranchwise {
    
    private  Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
                        Font.BOLD);
    private  Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                        Font.NORMAL, BaseColor.RED);
    private  Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
                        Font.BOLD);
    private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                        Font.BOLD);
    
       
    private String path;
    private String pdfName;
    Vector<PDFReportDataBranchMonthlyVecItem> StatusMap;
    public PDFCreatorReportMonthlyBranchwise(Vector<PDFReportDataBranchMonthlyVecItem> statusMap){
       StatusMap = statusMap;
       path = ConstantClass.getMyDocPath()+"\\branchesReport\\";
       pdfName = "report.pdf";
       File f = new File(path);
       f.mkdir();
    }
    
    public void generatePDF(PDFReportDataMonthlyBranchWise reportData) {
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
          
            Paragraph p2 = new Paragraph("Attendance Report"+"   "+ConstantClass.monthNames[Integer.parseInt(reportData.getMonth())]+"/"+reportData.getYear(), FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
            p2.setAlignment(Element.ALIGN_CENTER);   
            document.setMargins(1, 1, 1, 1);
            p2.setSpacingBefore(1f);
            p2.setSpacingAfter(1f);
            document.add(p2);
            Paragraph p3 = new Paragraph(" ");
            document.add(p3);
      
                 
            PdfPTable table = createTable(reportData);
            document.add(table);
            document.close();
          
           
//            if (pdfFile.endsWith(".pdf")) {
//                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + pdfFile);
//            }
        } catch (Exception e) {
                e.printStackTrace();
        }
        
     } 
     private PdfPTable createTable(PDFReportDataMonthlyBranchWise reportData) throws BadElementException, DocumentException, ParseException {
            PdfPTable table = new PdfPTable(4);
            table.setWidths(new int[]{2,2,3,3});
            table.setTotalWidth(PageSize.A4.getWidth());
            table.setWidthPercentage(100);

            Phrase ph = new Phrase("Date", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
            PdfPCell c1 = new PdfPCell(ph);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
            ph = new Phrase("Employee ID", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
            c1 = new PdfPCell(ph);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
                
            c1 = new PdfPCell(new Phrase("Employee Name", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Status", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
            for(int k=0;k<StatusMap.size();k++){               
                Calendar cal = StatusMap.get(k).getCalendar();
                Date dat =  cal.getTime();
                String strDate = df.format(dat);                 
                c1= new PdfPCell(new Phrase(strDate, FontFactory.getFont(FontFactory.HELVETICA,10)));
                table.addCell(c1); 
                c1= new PdfPCell(new Phrase(StatusMap.get(k).getEmpId(), FontFactory.getFont(FontFactory.HELVETICA,10)));
                table.addCell(c1);
                c1= new PdfPCell(new Phrase(StatusMap.get(k).getEmpName(), FontFactory.getFont(FontFactory.HELVETICA,10)));
                table.addCell(c1);
                c1= new PdfPCell(new Phrase(ConstantClass.getStatus(StatusMap.get(k).getStatus()), FontFactory.getFont(FontFactory.HELVETICA,10)));
                table.addCell(c1);         
            }
            
            return table;
        }
        
        private int getItem(Calendar Date,String Id){
            
           for(int i=0;i<StatusMap.size();i++){
               if(StatusMap.get(i).getEmpId().equals(Id) && StatusMap.get(i).getCalendar().equals(Date))
                   return i;
           }
           return -1;        
            
        }
          public String getPath() {
        return path;
    }

    public String getPdfName() {
        return pdfName;
    }  
    
    
}
