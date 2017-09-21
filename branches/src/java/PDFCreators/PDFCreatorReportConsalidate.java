/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PDFCreators;

import JavaCodePackage.ConstantClass;
import ReportItems.ConsalidateReportItem;
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
public class PDFCreatorReportConsalidate {
    
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
    Vector<ConsalidateReportItem> consalidateData;
    public PDFCreatorReportConsalidate(Vector<ConsalidateReportItem> consalidateData){
       this.consalidateData = consalidateData;
       path = ConstantClass.getMyDocPath()+"\\branchesReport\\";
       pdfName = "report.pdf";
       File f = new File(path);
       f.mkdir();
    }
    
    public void generatePDF(String BranchName,String Month,String Year) {
        try {  
            
          
            Document document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);
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
            
            
            p = new Paragraph("NY : Not Yet", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
            document.add(p);
            
            p = new Paragraph("F  : Full Day", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
            document.add(p);
            
            p = new Paragraph("H  : Half Day", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
            document.add(p);
            
            p = new Paragraph("A  : Absent", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
            document.add(p);
            
            p = new Paragraph("AL : Annual Leave", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
            document.add(p);
            
            p = new Paragraph("PL : Paternity Leave", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
            document.add(p);

            p = new Paragraph("ML : Maternity Leave", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
            document.add(p);
            
            p = new Paragraph("CL : Compassionate Leave", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
            document.add(p);
//            
            p = new Paragraph("SL : Sick Leave", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
            document.add(p);
            
            document.close();
      
        } catch (Exception e) {
                e.printStackTrace();
        }
        
     } 
     private PdfPTable createTable() throws BadElementException, DocumentException, ParseException {
            int numOfCol = 3+consalidateData.get(0).getStatus().size();
            PdfPTable table = new PdfPTable(numOfCol);
            int[] arraySize = new int[numOfCol];
            arraySize[0]=1;
            arraySize[1]=3;
            for(int i=2;i<numOfCol-1;i++){
                 arraySize[i]=1;
            }
            arraySize[numOfCol-1]=1;    
            
            table.setWidths(arraySize);
            table.setTotalWidth(PageSize.A4.getWidth());
            table.setWidthPercentage(100);

            Phrase ph = new Phrase("ID", FontFactory.getFont(FontFactory.HELVETICA,10, Font.BOLD));
            PdfPCell c1 = new PdfPCell(ph);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
            ph = new Phrase("Name", FontFactory.getFont(FontFactory.HELVETICA,10, Font.BOLD));
            c1 = new PdfPCell(ph);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
               
             for(int i=0;i<consalidateData.get(0).getStatus().size();i++){
                   String date = consalidateData.get(0).getLogDates().get(i);
                   String[] dateArray1 = date.split(" ");
                   String[] dateArray2 = dateArray1[0].split("-");
                   
                   ph = new Phrase(dateArray2[2]+"  "+ConstantClass.MonthArray[Integer.parseInt(dateArray2[1])], FontFactory.getFont(FontFactory.HELVETICA,7, Font.BOLD));
                   c1 = new PdfPCell(ph);
                   c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                   table.addCell(c1);
             }
            
            
            ph = new Phrase("S", FontFactory.getFont(FontFactory.HELVETICA,10, Font.BOLD));
            c1 = new PdfPCell(ph);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
           
            
            for(int i=0;i<consalidateData.size();i++){
                
                c1 = new PdfPCell(new Phrase(consalidateData.get(i).getEmpId(), FontFactory.getFont(FontFactory.HELVETICA,10)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
                    
                c1 = new PdfPCell(new Phrase(consalidateData.get(i).getEmpName(), FontFactory.getFont(FontFactory.HELVETICA,10)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);   
                for(int j=0;j<consalidateData.get(i).getStatus().size();j++)
                {    
                    c1 = new PdfPCell(new Phrase(ConstantClass.getShortForm(
                    consalidateData.get(i).getStatus().get(j)), FontFactory.getFont(FontFactory.HELVETICA,10)));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c1);   
                }
                c1 = new PdfPCell(new Phrase(" ", FontFactory.getFont(FontFactory.HELVETICA,10)));
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
