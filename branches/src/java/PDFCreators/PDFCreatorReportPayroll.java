/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PDFCreators;

import JavaCodePackage.ConstantClass;
import ReportItems.PayrollVecItem;
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
public class PDFCreatorReportPayroll {
    
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
    Vector<PayrollVecItem> payrollData;
    public PDFCreatorReportPayroll(Vector<PayrollVecItem> payrollData){
       this.payrollData = payrollData;
       path = ConstantClass.getMyDocPath()+"\\branchesReport\\";
       pdfName = "report.pdf";
       File f = new File(path);
       f.mkdir();
    }
    
    public void generatePDF(String BranchName,String Month,String Year) {
        try {  
            
           
            Document document = new Document(PageSize.A4, 10, 10, 20, 20);
            PdfWriter.getInstance(document, new FileOutputStream(path+pdfName));
            document.open(); 
            
           
          
            Paragraph p = new Paragraph("MeTL-"+BranchName+"-"+Month+"-"+ Year, FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
            p.setAlignment(Element.ALIGN_CENTER);   
            document.setMargins(1, 1, 1, 1);
            p.setSpacingBefore(1f);
            p.setSpacingAfter(1f);
            document.add(p);
           
            document.add(new Paragraph(" "));
                   
            PdfPTable table = createTable();
            document.add(table);
            document.close();
      
        } catch (Exception e) {
                e.printStackTrace();
        }
        
     } 
     private PdfPTable createTable() throws BadElementException, DocumentException, ParseException {
            PdfPTable table = new PdfPTable(9);
            table.setWidths(new int[]{1,2,1,1,1,1,1,1,1});
            table.setTotalWidth(PageSize.A4.getWidth());
            table.setWidthPercentage(100);

            Phrase ph = new Phrase("Emp ID", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
            PdfPCell c1 = new PdfPCell(ph);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
                
            ph = new Phrase("Emp Name", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
            c1 = new PdfPCell(ph);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Absent", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Annual Leave", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Compassionate Leave", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Paternity Leave", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
           
            c1 = new PdfPCell(new Phrase("Maternity Leave", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Medical Leave", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Not Yet", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
            for(int i=0;i<payrollData.size();i++){
                 c1 = new PdfPCell(new Phrase(payrollData.get(i).getEmpId(), FontFactory.getFont(FontFactory.HELVETICA,10)));
                 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                 table.addCell(c1);
                 
                  c1 = new PdfPCell(new Phrase(payrollData.get(i).getEmpName(), FontFactory.getFont(FontFactory.HELVETICA,10)));
                 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                 table.addCell(c1);
                 
                 c1 = new PdfPCell(new Phrase(payrollData.get(i).getAbsent(), FontFactory.getFont(FontFactory.HELVETICA,10)));
                 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                 table.addCell(c1);
                 
                 c1 = new PdfPCell(new Phrase(payrollData.get(i).getAnnaulLeave(), FontFactory.getFont(FontFactory.HELVETICA,10)));
                 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                 table.addCell(c1);
                 
                 c1 = new PdfPCell(new Phrase(payrollData.get(i).getCompLeave(), FontFactory.getFont(FontFactory.HELVETICA,10)));
                 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                 table.addCell(c1);
                 
                 c1 = new PdfPCell(new Phrase(payrollData.get(i).getPatLeave(), FontFactory.getFont(FontFactory.HELVETICA,10)));
                 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                 table.addCell(c1);
                 
                 c1 = new PdfPCell(new Phrase(payrollData.get(i).getMatLeave(), FontFactory.getFont(FontFactory.HELVETICA,10)));
                 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                 table.addCell(c1);
                 
                 c1 = new PdfPCell(new Phrase(payrollData.get(i).getMedicalLeave(), FontFactory.getFont(FontFactory.HELVETICA,10)));
                 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                 table.addCell(c1);
                 
                 c1 = new PdfPCell(new Phrase(payrollData.get(i).getNotyet(), FontFactory.getFont(FontFactory.HELVETICA,10)));
                 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                 table.addCell(c1);     
            }
            
            
            return table;
        }
        
          public String getPath() {
        return path;
    }

    public String getPdfName() {
        return pdfName;
    }
        
    
    
}
