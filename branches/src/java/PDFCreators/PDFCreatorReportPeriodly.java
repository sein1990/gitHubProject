/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PDFCreators;

import JavaCodePackage.ConstantClass;
import ReportItems.PDFReportDataPeriodly;
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
public class PDFCreatorReportPeriodly {
    
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
    Map<Calendar,String> StatusMap;
    public PDFCreatorReportPeriodly(Map<Calendar,String> statusMap){
       StatusMap = statusMap;
       path = ConstantClass.getMyDocPath()+"\\branchesReport\\";
       pdfName = "report.pdf";
       File f = new File(path);
       f.mkdir();
    }
    
    public void generatePDF(PDFReportDataPeriodly reportData) {
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
            Paragraph p2 = new Paragraph("Attendance Report of " +EMPNAME+"   "+ "   FROM:"+reportData.getFirstDay()+ "   TO:"+reportData.getEndDay(), FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
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
     private PdfPTable createTable(PDFReportDataPeriodly reportData) throws BadElementException, DocumentException, ParseException {
            PdfPTable table = new PdfPTable(2);
            table.setWidths(new int[]{5,5});
            table.setTotalWidth(PageSize.A4.getWidth());
            table.setWidthPercentage(100);

            Phrase ph = new Phrase("Date",  FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
            PdfPCell c1 = new PdfPCell(ph);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
                
            c1 = new PdfPCell(new Phrase("Status", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
            String startDateStr = reportData.getFirstDay();
            String endDateStr = reportData.getEndDay();  
                    
            DateFormat df = new SimpleDateFormat("dd-MMM-yyyy"); 
            
            Date startDate =  df.parse(startDateStr);
            Date endDate =  df.parse(endDateStr);
            
            Calendar startCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();
              
            startCal.setTime(startDate);
            endCal.setTime(endDate);
            
            
            while(!startCal.after(endCal)){
                
                String currentDate = df.format(startCal.getTime());
                
                String status = getStatus(startCal);
                
                
                c1= new PdfPCell(new Phrase(currentDate, FontFactory.getFont(FontFactory.HELVETICA, 9)));
                table.addCell(c1);
                
                c1= new PdfPCell(new Phrase(status, FontFactory.getFont(FontFactory.HELVETICA, 9)));
                table.addCell(c1);
                
                startCal.add(Calendar.DATE, 1);
            }
            
            return table;
        }
        
        private String getStatus(Calendar CurrentCal){
            
            if(StatusMap.containsKey(CurrentCal))
                return StatusMap.get(CurrentCal);
                        
            return ConstantClass.getStatus(ConstantClass.NullCode);
            
        }
        
        public String getPath() {
            return path;
        }

        public String getPdfName() {
            return pdfName;
        }
    
}
