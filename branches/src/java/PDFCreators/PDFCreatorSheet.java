/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDFCreators;

import JavaCodePackage.ConstantClass;
import JavaCodePackage.InternalMainClass;
import JavaCodePackage.QuerisClass;
import JavaCodePackage.conectionClass;
import Models.Employee;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class PDFCreatorSheet {

    public String getPath() {
        return path;
    }

    public String getPdfName() {
        return pdfName;
    }
        
        
    private  Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
                        Font.BOLD);
    private  Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                        Font.NORMAL, BaseColor.RED);
    private  Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
                        Font.BOLD);
    private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                        Font.BOLD);

    private String BranchName;
    private int BranchID;
    private int Date;
    private int Month;
    private int Year;
    private String UserName;
    private String pdfName;
    private String path;
    private InternalMainClass main;
    private Vector<Employee> employeeList;
    String txtName;
    private Vector<String> employeeOnLeavesList;
        private Vector<String> employeeOnLeavesListStatus;
    //String toBeWriten;

    public PDFCreatorSheet(int BranchID, int Date, int Month , int Year, String UserName) {
    
        this.BranchID = BranchID;
        this.Date = Date;
        this.Month = Month;
        this.Year = Year;
        this.UserName = UserName;
        if(BranchID == 0)
            return;
        this.main = new InternalMainClass();
        this.main.internalMain();
        this.BranchName = this.main.getSpecificBranch(BranchID);
        this.main.prepareEmployeeData(BranchID);
        this.employeeList = this.main.prepareEmployeeVecPerUnit(BranchID);
        this.employeeOnLeavesList = new Stack<String>();
        this.employeeOnLeavesListStatus = new Stack<String>();
       // this.toBeWriten = "";
    }
    
    private void makeDirectories(){
        path = ConstantClass.getMyDocPath()+"\\branchesReport\\";
        pdfName = "sheet.pdf";
        File f = new File(path);
        f.mkdir();
    }
    
    public void generateFiles(){
        makeDirectories();
        selectEmployeesOnLeaves();
        generatePDF();
    }

    private void generatePDF() {
        try {  
            Document document = new Document(PageSize.A4, 10, 10, 20, 20);
            PdfWriter.getInstance(document, new FileOutputStream(path+pdfName));
            document.open();
            PdfPTable table = createTable();
            document.add(table);
            document.close();
          
           
        //    if (pdfName.toString().endsWith(".pdf")) {
         //       Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + pdfName);
          //  }
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

     private String getDataFormated(int date,int month,int year){
    
         
        return date+"-"+ConstantClass.MonthArray[month]+"-"+year;
    }
     
    
    private void selectEmployeesOnLeaves(){
        
        try {
            conectionClass c = new conectionClass();
            QuerisClass q =  new QuerisClass();
            
            String dbDate = getDataFormated(this.Date,this.Month,this.Year);
            
            String query = q.selectEmployeesOnLeavesQuery(Integer.toString(this.BranchID),dbDate);
            ResultSet res1 = c.excuteQuery(query);
            
            while(res1.next()) {
                employeeOnLeavesList.add(res1.getString(1));
                employeeOnLeavesListStatus.add(res1.getString(2));
            
            }
            
            c.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PDFCreatorSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
     
    
    private String getStatus(String empId){
        for(int i=0;i<employeeOnLeavesList.size();i++){
            
            
            if(employeeOnLeavesList.get(i).equals(empId))
                return employeeOnLeavesListStatus.get(i);
        }
        return "NULL";
    }
    
    private PdfPTable createTable() throws BadElementException, DocumentException {
        PdfPTable table = new PdfPTable(4);
        table.setWidths(new int[]{2,4,3,3});
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setWidthPercentage(100);
                
        Phrase ph = new Phrase("ID", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD));
        PdfPCell c1 = new PdfPCell(ph);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPaddingTop(8);
        table.addCell(c1);
           
        c1 = new PdfPCell(new Phrase("Name", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPaddingTop(8);
        table.addCell(c1);
               
        c1 = new PdfPCell(new Phrase("F", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPaddingTop(8);
        table.addCell(c1);
                
        c1 = new PdfPCell(new Phrase("H", FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPaddingTop(8);
        table.addCell(c1);
                
        table.setHeaderRows(1);
      //  toBeWriten = "";
        for (int j=0;j<employeeList.size();j++){  
          
                
                c1= new PdfPCell(new Phrase(employeeList.get(j).getEmployeeID(), FontFactory.getFont(FontFactory.HELVETICA,9)));
                table.addCell(c1);
             //   toBeWriten = toBeWriten + employeeList.get(j).getEmployeeID() +"#";
                
                c1= new PdfPCell(new Phrase(employeeList.get(j).getEmployeeName(), FontFactory.getFont(FontFactory.HELVETICA,9)));
                table.addCell(c1);
                
                String status = getStatus(employeeList.get(j).getEmployeeID());
                
                if(status.equals("NULL"))
                   c1= setCell("   ");
                else
                    c1= setCell(ConstantClass.getStatus(status));
                table.addCell(c1);
                table.addCell(c1);
                   
        }
        
        c1 = new PdfPCell(new Phrase("Branch Name :"+BranchName, FontFactory.getFont(FontFactory.HELVETICA,9, Font.BOLD)));
        c1.setPaddingTop(8);
        c1.setColspan(4);
        table.addCell(c1);
            
        c1 = new PdfPCell(new Phrase("Date :"+Date+"\\"+Month+"\\"+Year, FontFactory.getFont(FontFactory.HELVETICA,9, Font.BOLD)));
        c1.setPaddingTop(8);
        c1.setColspan(4);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Admin Name :"+UserName, FontFactory.getFont(FontFactory.HELVETICA,9, Font.BOLD)));
        c1.setPaddingTop(8);
        c1.setColspan(4);
        table.addCell(c1);
               
        c1 = new PdfPCell(new Phrase("Signature :", FontFactory.getFont(FontFactory.HELVETICA,9, Font.BOLD)));
        c1.setPaddingTop(8);
        c1.setColspan(4);
        table.addCell(c1);
        return table;
        }
        
         private PdfPCell setCell(String Data){
            PdfPCell c1 = new PdfPCell(new Phrase(Data, FontFactory.getFont(FontFactory.HELVETICA,9)));
            c1.setBackgroundColor(BaseColor.WHITE);
            return c1;
        }
        
       
}
