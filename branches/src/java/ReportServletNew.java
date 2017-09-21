/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ReportItems.PDFReportDataBranchDailyVecItem;
import ReportItems.PDFReportDataBranchDailyItem;
import ReportItems.PDFReportDataMonthlyEmpWise;
import ReportItems.PDFReportDataMonthlyBranchWise;
import ReportItems.PDFReportDataPeriodly;
import ReportItems.PDFReportDataBranchMonthlyVecItem;
import ReportItems.PDFReportDataPeriodlyBranchWise;
import PDFCreators.PDFCreatorReportMonthlyBranchwise;
import PDFCreators.PDFCreatorReportBranchDaily;
import PDFCreators.PDFCreatorReportPeriodlyBranchwise;
import PDFCreators.PDFCreatorReportMonthlySummry;
import PDFCreators.PDFCreatorReportDailySummryBranchWise;
import PDFCreators.PDFCreatorReportPeriodly;
import PDFCreators.PDFCreatorReportPeriodlySummry;
import PDFCreators.PDFCreatorReportMonthlyEmpwise;
import JavaCodePackage.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(urlPatterns = {"/ReportServletNew"})
public class ReportServletNew extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   HttpServletResponse response;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        this.response=response;
        //    PrintWriter out = response.getWriter();
       
        try {
            /* TODO output your page here. You may use following sample code. */
           
            String buttonID = request.getParameter("buttonID");
            String reportID = request.getParameter("reportID");
            int intButtonId = Integer.parseInt(buttonID);
            int intReportID = Integer.parseInt(reportID);
            
            if( (intButtonId == 0 && intReportID == 1)){ // Emp Wise - Daily 
                String dailyDate = getDataFormated(request.getParameter("dailyDate"));
                String branchName = request.getParameter("branchname");
                String employeeName = request.getParameter("employeename");
                
                String branchId = getId(branchName);
                String employeeId = getId(employeeName);
                
                QuerisClass q =  new QuerisClass();
                String query  = q.getReportDataEmpWiseDaily(branchId,employeeId,dailyDate);
                conectionClass c = new conectionClass();
                ResultSet res1 = c.excuteQuery(query);
                String JSPFile = "/reportpage.jsp";
                if (!res1.isBeforeFirst()){
                    PrintWriter out = response.getWriter();
                    out.print("<script language='JavaScript'>alert('"+
                            ConstantClass.getStatus("-3") +"');</script>");
                }
                while(res1.next()) {
                        String Status = res1.getString(1); 
                        PrintWriter out = response.getWriter();
                        out.print("<script language='JavaScript'>alert('"+
                                ConstantClass.getStatus(Status) +"');</script>");
                    }
                ServletContext sc = this.getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher(JSPFile);
                rd.include(request, response);
              
            }
            else if(intButtonId == 0 && intReportID == 2){ // Emp Wise - monthly
            	
               String monthSelect = request.getParameter("monthSelect");
               String yearSelect = request.getParameter("yearSelect");
               String branchName = request.getParameter("branchname");
               String employeeName = request.getParameter("employeename");
               
               String branchId = getId(branchName);
               String employeeId = getId(employeeName);
                              
               PDFReportDataMonthlyEmpWise reportData = new PDFReportDataMonthlyEmpWise
                       (employeeName,employeeId,branchId,branchName,monthSelect,yearSelect); 
               prepareDataForMonthlyReportEmpWise(reportData);
            
            }
            else if(intButtonId == 0 && intReportID == 3){ //Emp Wise - Period
                            
               String fromDate = request.getParameter("fromDate");
               String toDate = request.getParameter("toDate");
               String branchName = request.getParameter("branchname");
               String employeeName = request.getParameter("employeename");
               
               String branchId = getId(branchName);
               String employeeId = getId(employeeName);
               String firstDay = getDataFormated(fromDate);
               String secDay = getDataFormated(toDate);
               
               PDFReportDataPeriodly reportData = new PDFReportDataPeriodly
                       (employeeName,employeeId,branchId,branchName,firstDay,secDay); 
               prepareDataForPeriodlyReport(reportData);
             
                
            
            }
            else if(intButtonId == 0 && intReportID == 4){ 
                
               String branchName = request.getParameter("branchname");
               String employeeName = request.getParameter("employeename");
               String branchId = getId(branchName);
               String employeeId = getId(employeeName);
               
               QuerisClass q =  new QuerisClass();
               String query  = q.getReportDataEmpWiseHistory(branchId,employeeId);
               conectionClass c = new conectionClass();
               ResultSet res1 = c.excuteQuery(query);
               while(res1.next()) {
                    String Date = res1.getString(1);
                    String Status = res1.getString(2);
                    
                    PrintWriter out = response.getWriter();
                    out.println("<h1>Servlet ReportServletNew at " + Date+" "+ConstantClass.getStatus(Status) + "</h1>");
                } 
                PrintWriter out = response.getWriter();
                out.println("<h1>Servlet ReportServletNew at " + branchId + "</h1>");
                out.println("<h1>Servlet ReportServletNew at " + employeeId + "</h1>");
             
            }   
            else if  (intButtonId == 1 && intReportID == 2){ //Emp Wise - Monthly - Summary -- W
                
               String monthSelect = request.getParameter("monthSelect");
               String yearSelect = request.getParameter("yearSelect");
               String branchName = request.getParameter("branchname");
               String employeeName = request.getParameter("employeename");
               
               String branchId = getId(branchName);
               String employeeId = getId(employeeName);
               String firstDay = ConstantClass.getFirstDayOfMonth(monthSelect,yearSelect);
               String secDay = ConstantClass.getLastDayOfMonth(monthSelect,yearSelect);
               
               Map<String,String> StatusMap = new HashMap<String,String>();
               
               QuerisClass q =  new QuerisClass();
               String query  = q.getReportDataEmpWiseConsMonthly(branchId,employeeId,firstDay,secDay);
               conectionClass c = new conectionClass();
               ResultSet res1 = c.excuteQuery(query);
               while(res1.next()) {
               
                    String Status = res1.getString(1);
                    String StatusCount = res1.getString(2);
                    StatusMap.put(Status,StatusCount);
               
               }
               
                
               PDFReportDataMonthlyEmpWise reportData = new PDFReportDataMonthlyEmpWise
                       (employeeName,employeeId,branchId,branchName,monthSelect,yearSelect); 
               
               PDFCreatorReportMonthlySummry PDFCreator = new PDFCreatorReportMonthlySummry(StatusMap);
               PDFCreator.generatePDF(reportData);
               downloadDoc(PDFCreator.getPath(),PDFCreator.getPdfName());

            }
            
	    
            else if( intButtonId == 1 && intReportID == 3) { //Emp Wise - Monthly - Summary-- W
                
               String branchName = request.getParameter("branchname");
               String employeeName = request.getParameter("employeename");
               String fromDate = request.getParameter("fromDate");
               String toDate = request.getParameter("toDate");
               
               String branchId = getId(branchName);
               String employeeId = getId(employeeName);
               String firstDay = getDataFormated(fromDate);
               String secDay = getDataFormated(toDate);
              
               Map<String,String> StatusMap = new HashMap<String,String>();
               
               QuerisClass q =  new QuerisClass();
               String query  = q.getReportDataEmpWiseConsMonthly(branchId,employeeId,firstDay,secDay);
               conectionClass c = new conectionClass();
               ResultSet res1 = c.excuteQuery(query);
               while(res1.next()) {
                    String Status = res1.getString(1);
                    String StatusCount = res1.getString(2);
                    StatusMap.put(Status,StatusCount);    
               }
               PDFReportDataPeriodly reportData = new PDFReportDataPeriodly
                       (employeeName,employeeId,branchId,branchName,firstDay,secDay); 
               PDFCreatorReportPeriodlySummry PDFCreator= new PDFCreatorReportPeriodlySummry(StatusMap);
               PDFCreator.generatePDF(reportData); 
               downloadDoc(PDFCreator.getPath(),PDFCreator.getPdfName());
                 
            }
            else if(intButtonId == 2 && intReportID == 1){ //Branch Wise - Daily-- W
                                
                String dailyDate = request.getParameter("dailyDate");
                String statusList = request.getParameter("statusList");
                String branchName = request.getParameter("branchname");
                String branchId = getId(branchName);
                String formatedDailyDate = getDataFormated(dailyDate);
                
                PDFReportDataBranchDailyItem reportData = new PDFReportDataBranchDailyItem(branchName,dailyDate); 
                Vector<PDFReportDataBranchDailyVecItem> DataVec = new Vector<PDFReportDataBranchDailyVecItem>();
                
                QuerisClass q =  new QuerisClass();
                String query  = q.getReportDataBranchWiseDaily(branchId,formatedDailyDate,statusList);
                conectionClass c = new conectionClass();
                ResultSet res1 = c.excuteQuery(query);
                if (!res1.isBeforeFirst()){
                   PDFCreatorReportBranchDaily pdfCreator = new  PDFCreatorReportBranchDaily(DataVec);
                   pdfCreator.generatePDF(reportData);
                }
                else{
                    while(res1.next()) {
                        String empId = res1.getString(2);
                        String name = res1.getString(3);
                        String Status = res1.getString(4);
                        DataVec.add(new PDFReportDataBranchDailyVecItem(ConstantClass.getStatus(Status), empId, name));
                    }
                    PDFCreatorReportBranchDaily pdfCreator = new  PDFCreatorReportBranchDaily(DataVec);
                    pdfCreator.generatePDF(reportData);    
                    downloadDoc(pdfCreator.getPath(),pdfCreator.getPdfName());
                } 
            }
            
            else if(intButtonId == 2 && intReportID == 2){//Branch Wise - Monthly
		 
               String statusList = request.getParameter("statusList");
               String monthSelect = request.getParameter("monthSelect");
               String yearSelect = request.getParameter("yearSelect");
               String branchName = request.getParameter("branchname");
               String branchId = getId(branchName);
               
               PDFReportDataMonthlyBranchWise reportData = new PDFReportDataMonthlyBranchWise(branchId,branchName,monthSelect,yearSelect,statusList);
               prepareDataForMonthlyReportBranchWise(reportData);
               
                               
               
            }
            else if(intButtonId == 2 && intReportID == 3){//Branch Wise - Periodly
               
               String statusList = request.getParameter("statusList");
               String fromDate = request.getParameter("fromDate");
               String toDate = request.getParameter("toDate");
               String branchName = request.getParameter("branchname");
               String branchId = getId(branchName);
               String firstDay = getDataFormated(fromDate);
               String secDay = getDataFormated(toDate);
               
               
               PDFReportDataPeriodlyBranchWise reportData = new PDFReportDataPeriodlyBranchWise(branchId,branchName,firstDay,secDay,statusList);
               prepareDataForPeriodReportBranchWise(reportData);
                
               
              
            }
             else if ((intButtonId == 3 && intReportID == 1) ){ //Branch Wise - Daily - Summry-- W
                
                
                String branchName = request.getParameter("branchname"); 
                String dailyDate = request.getParameter("dailyDate");
                
                String branchId = getId(branchName);
                String formatedDailyDate = getDataFormated(dailyDate);
               
                Map<String,String> StatusMap = new HashMap<String,String>();
                
                QuerisClass q =  new QuerisClass();
                String query  = q.getReportDataBranchWiseConsMonthly(branchId,formatedDailyDate);
               
                conectionClass c = new conectionClass();
                ResultSet res1 = c.excuteQuery(query);
                while(res1.next()) {
                    String Status = res1.getString(1);
                    String StatusCount = res1.getString(2);
                    StatusMap.put(Status,StatusCount);    
             
                }
                 PDFReportDataBranchDailyItem reportData = new PDFReportDataBranchDailyItem
                       (branchName,formatedDailyDate); 
               PDFCreatorReportDailySummryBranchWise pdfCreator= new PDFCreatorReportDailySummryBranchWise(StatusMap);
               pdfCreator.generatePDF(reportData); 
               downloadDoc(pdfCreator.getPath(),pdfCreator.getPdfName()); 
             
             }
           
        } catch (Exception ex) {
            Logger.getLogger(ReportServletNew.class.getName()).log(Level.SEVERE, null, ex);
        }finally {            
            
        } 
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    
    private String getDataFormated(String date){
        String[] splits = date.split("-");
        return splits[2]+"-"+ConstantClass.MonthArray[Integer.parseInt(splits[1])]+"-"+splits[0];
    }
    
        
   private String getId(String str){
       return str.substring(str.indexOf("-")+1);
   }     
    
    
  private void prepareDataForPeriodlyReport(PDFReportDataPeriodly reportData) throws SQLException, ParseException, IOException{
      
               QuerisClass q =  new QuerisClass();
               String query  = q.getReportDataEmpWiseMonthly(reportData.getBranchID(),reportData.getEmpID(),reportData.getFirstDay(),reportData.getEndDay());

               conectionClass c = new conectionClass();
               ResultSet res1 = c.excuteQuery(query);
               
              
             
               Map<Calendar,String> StatusMap = new HashMap<Calendar,String>();
               
               if (!res1.isBeforeFirst()){
                   PDFCreatorReportPeriodly pdfCreator = new  PDFCreatorReportPeriodly(StatusMap);
                   pdfCreator.generatePDF(reportData);
               }
               else{
                   while(res1.next()) {
                        String DateStr = res1.getString(1);
                        String Status = res1.getString(2);
                        
                         DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
                         Date dateDate =  df.parse(DateStr);
                         Calendar dateCal = Calendar.getInstance();
                         dateCal.setTime(dateDate);
                         
                        StatusMap.put(dateCal, ConstantClass.getStatus(Status));
                   } 
                
                    PDFCreatorReportPeriodly pdfCreator = new  PDFCreatorReportPeriodly(StatusMap);
                    pdfCreator.generatePDF(reportData);
                    downloadDoc(pdfCreator.getPath(),pdfCreator.getPdfName());
                    
                    
               }
 
  }  
   
  
  private void downloadDoc(String path,String fileName) throws IOException{
     
                    BufferedInputStream buf = null;
                    ServletOutputStream myOut = null;

                    try {

                        myOut = response.getOutputStream();             //Getting mistake here
                        File myfile = new File(path + fileName);

                        //set response headers
                        response.setContentType("application/pdf");         //I want to download a PDF file

                        response.addHeader(
                                "Content-Disposition", "attachment; filename=" + fileName);

                        response.setContentLength((int) myfile.length());

                        FileInputStream input = new FileInputStream(myfile);
                        buf = new BufferedInputStream(input);
                        int readBytes = 0;

                        //read from the file; write to the ServletOutputStream
                        while ((readBytes = buf.read()) != -1) {
                            myOut.write(readBytes);
                        }

                    } catch (IOException ioe) {
                    } finally {

                        //close the input/output streams
                        if (myOut != null) {
                            myOut.close();
                        }
                        if (buf != null) {
                            buf.close();
                        }

                    } 
  }
  
  private void prepareDataForMonthlyReportEmpWise(PDFReportDataMonthlyEmpWise reportData) throws SQLException, ParseException, IOException{
      
               String firstDay = ConstantClass.getFirstDayOfMonth(reportData.getMonth(),reportData.getYear());
               String secDay = ConstantClass.getLastDayOfMonth(reportData.getMonth(),reportData.getYear());
              
               
               QuerisClass q =  new QuerisClass();
               String query  = q.getReportDataEmpWiseMonthly(reportData.getBranchID(),reportData.getEmpID(),firstDay,secDay);

               conectionClass c = new conectionClass();
               ResultSet res1 = c.excuteQuery(query);
               
              
             
               Map<Calendar,String> StatusMap = new HashMap<Calendar,String>();
               
               if (!res1.isBeforeFirst()){
                   PDFCreatorReportMonthlyEmpwise pdfCreator = new  PDFCreatorReportMonthlyEmpwise(StatusMap);
                   pdfCreator.generatePDF(reportData);
               }
               else{
                   while(res1.next()) {
                        String DateStr = res1.getString(1);
                        String Status = res1.getString(2);
                        
                         DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
                         Date dateDate =  df.parse(DateStr);
                         Calendar dateCal = Calendar.getInstance();
                         dateCal.setTime(dateDate);
                         
                        StatusMap.put(dateCal, ConstantClass.getStatus(Status));
                   } 
                
                    PDFCreatorReportMonthlyEmpwise pdfCreator = new  PDFCreatorReportMonthlyEmpwise(StatusMap);
                    pdfCreator.generatePDF(reportData);
                    downloadDoc(pdfCreator.getPath(),pdfCreator.getPdfName());
                    
               } 
 
  }  
  
  
   private void prepareDataForMonthlyReportBranchWise(PDFReportDataMonthlyBranchWise reportData) throws SQLException, ParseException, IOException{
       String firstDay = ConstantClass.getFirstDayOfMonth(reportData.getMonth(),reportData.getYear());
       String secDay = ConstantClass.getLastDayOfMonth(reportData.getMonth(),reportData.getYear()); 
       QuerisClass q =  new QuerisClass();
       String query = q.getReportDataBranchWiseMonthly(reportData.getBranchID(), reportData.getStatus(), firstDay, secDay);
       conectionClass c = new conectionClass();
       ResultSet res1 = c.excuteQuery(query);
              Vector<PDFReportDataBranchMonthlyVecItem> StatusMap = new Vector<PDFReportDataBranchMonthlyVecItem>();
               
               if (!res1.isBeforeFirst()){
                   
                   PDFCreatorReportMonthlyBranchwise pdfCreator = new  PDFCreatorReportMonthlyBranchwise(StatusMap);
                   pdfCreator.generatePDF(reportData);
               }
               else{
                   while(res1.next()) {
                        
                        String empId1 = res1.getString(2);
                        String name = res1.getString(3);
                        String Status = res1.getString(4);
                        String DateStr = res1.getString(5);
                        
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
                        Date dateDate =  df.parse(DateStr);
                        Calendar dateCal = Calendar.getInstance();
                        dateCal.setTime(dateDate);
                        
                        StatusMap.add(new PDFReportDataBranchMonthlyVecItem(Status,empId1,name,dateCal));

                   } 
                
                    PDFCreatorReportMonthlyBranchwise pdfCreator = new  PDFCreatorReportMonthlyBranchwise(StatusMap);
                    pdfCreator.generatePDF(reportData);
                     downloadDoc(pdfCreator.getPath(),pdfCreator.getPdfName());
                    
               }
      
 
  }  
  
   
   private void prepareDataForPeriodReportBranchWise(PDFReportDataPeriodlyBranchWise reportData) throws SQLException, ParseException, IOException{
     
       QuerisClass q =  new QuerisClass();
       String query = q.getReportDataBranchWiseMonthly(reportData.getBranchID(), reportData.getStatus(), reportData.getFristday(), reportData.getSecDay());
       conectionClass c = new conectionClass();
       ResultSet res1 = c.excuteQuery(query);
              Vector<PDFReportDataBranchMonthlyVecItem> StatusMap = new Vector<PDFReportDataBranchMonthlyVecItem>();
               
               if (!res1.isBeforeFirst()){
                   
                   PDFCreatorReportPeriodlyBranchwise pdfCreator = new  PDFCreatorReportPeriodlyBranchwise(StatusMap);
                   pdfCreator.generatePDF(reportData);
               }
               else{
                   while(res1.next()) {
                        
                        String empId1 = res1.getString(2);
                        String name = res1.getString(3);
                        String Status = res1.getString(4);
                        String DateStr = res1.getString(5);
                        
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
                        Date dateDate =  df.parse(DateStr);
                        Calendar dateCal = Calendar.getInstance();
                        dateCal.setTime(dateDate);
                        
                        StatusMap.add(new PDFReportDataBranchMonthlyVecItem(Status,empId1,name,dateCal));

                   } 
                
                    PDFCreatorReportPeriodlyBranchwise pdfCreator = new  PDFCreatorReportPeriodlyBranchwise(StatusMap);
                    pdfCreator.generatePDF(reportData);
                    downloadDoc(pdfCreator.getPath(),pdfCreator.getPdfName()); 
                   
               }
      
 
  }  
   
}
