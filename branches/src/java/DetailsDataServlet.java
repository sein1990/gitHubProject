/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import JavaCodePackage.ConstantClass;
import JavaCodePackage.QuerisClass;
import JavaCodePackage.conectionClass;
import Models.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(urlPatterns = {"/DetailsDataServlet"})
public class DetailsDataServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            
               out.println("<h1>Servlet ReportServletNew at ");
                String[] empid = request.getParameterValues("empid");
                String[] statusvalue = request.getParameterValues("statusvalue");
                String[] branchid = request.getParameterValues("branchid");
                String[] mydate = request.getParameterValues("mydate");
                String[] month = request.getParameterValues("month");
                String[] year = request.getParameterValues("year");
                
                for(int i=0;i<empid.length;i++){
                  String s = addDetailsDataToDate(branchid[i],empid[i],
                    getDataFormated(mydate[i],month[i],year[i]),statusvalue[i]);  
                    
                  
                  out.println("<h1>Servlet ReportServletNew at "+branchid[i]+" "
                          +empid[i]+" "+getDataFormated(mydate[i],month[i],year[i])
                          +" "+statusvalue[i]+" "+s+"</h1>");
                }
            
        } catch (Exception ex) {
            Logger.getLogger(ReportServletNew.class.getName()).log(Level.SEVERE, null, ex);
        }finally {            
            out.close();
        } 
    }

    private String addDetailsDataToDate(String branchId,String empId,String dailyDate,String Status){
        
       String savedStatus =  getStatusFromDailyAttandanceTabel(branchId,empId,dailyDate);
       if(savedStatus.equals("null")){
           addStatusToDailyAttandanceTabel(branchId,empId,dailyDate,Status);
       }
       else{
           updateStatusToDailyAttandanceTabel(branchId,empId,dailyDate,Status);
       }
       return savedStatus;
    }
    
    private String getDataFormated(String date,String month,String year){
      
        return date+"-"+ConstantClass.MonthArray[Integer.parseInt(month)+1]+"-"+year;
    }
    
    private void addStatusToDailyAttandanceTabel(String branchId,String empId,String dailyDate,String Status){
        conectionClass c = new conectionClass();
        QuerisClass query = new QuerisClass();
        c.excuteQuery(query.addtStatusForSpecificEmpandDate(branchId,empId,dailyDate,Status));
        c.closeConnection();  
    }
    
     private void updateStatusToDailyAttandanceTabel(String branchId,String empId,String dailyDate,String Status){
        conectionClass c = new conectionClass();
        QuerisClass query = new QuerisClass();
        c.excuteQuery(query.updateStatusForSpecificEmpandDate(branchId,empId,dailyDate,Status));
        c.closeConnection();  
    
    }
    
    private String getStatusFromDailyAttandanceTabel(String branchId,String empId,String dailyDate){
      
       String returnVal = "null";  
         
       QuerisClass q =  new QuerisClass();
       String query  = q.selectStatusForSpecificEmpandDate(branchId,empId,dailyDate);
       conectionClass c = new conectionClass();
       try {
           ResultSet res = c.excuteQuery(query);
           if(res != null){
              while(res.next()) {
                   String value = res.getString(1);
                   returnVal = value;
              }
           }
          
       }catch (SQLException ex) {
            //Logger.getLogger(InternalMainClass.class.getName()).log(Level.SEVERE, null, ex);
      }
      return returnVal;
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

    
    
    
    
    
 
    
    
}
