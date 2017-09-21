/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Models.Leave;
import Models.SummryLeaveDataClass;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(urlPatterns = {"/AddLeaveServletNew"})
public class AddLeaveServletNew extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddLeaveServletNew</title>");   
                        
            String branchId = getIdOnly(request.getParameter("branchname"));
            String employeeId = getIdOnly(request.getParameter("employeename"));
            String leavetypes = getIdOnly(request.getParameter("leavetypes"));
            
            String leavesUnProcessedData =  request.getParameter("leavesunsummryprocesseddata");
      
            String[] fromArray = request.getParameter("fromdate").split("-");
            String fromDateStr = fromArray[2]+"-"+fromArray[1]+"-"+fromArray[0];  

            String[] toArray = request.getParameter("todate").split("-");
            String toDateStr = toArray[2]+"-"+toArray[1]+"-"+toArray[0];  
        
       
            Date fromDate=new SimpleDateFormat("dd-MM-yyyy").parse(fromDateStr);  
            Date toDate=new SimpleDateFormat("dd-MM-yyyy").parse(toDateStr);  
               
            Leave leave = new Leave(branchId, employeeId, leavetypes, fromDate,toDate,
            leavesUnProcessedData);
           
            
            int res = leave.addLeave();
            if(res == -1){
                 out.println("<h1>No Leave has been added please check</h1>");       
            }else{
                 out.println("<h1>Leave has been added</h1>");       
           
            }
                
           


            
            out.println("<h1>"+branchId+"</h1>");       
            out.println("<h1>"+employeeId+"</h1>");
            out.println("<h1>"+leavetypes+"</h1>");
       
            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddLeaveServletNew at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } catch (ParseException ex) {
            Logger.getLogger(AddLeaveServletNew.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    
    public String getIdOnly(String str){
	
	return str.substring(str.indexOf("-")+1, str.length());
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
