/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import JavaCodePackage.ConstantClass;
import JavaCodePackage.QuerisClass;
import JavaCodePackage.conectionClass;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/ManageLeaveServlet"})
public class ManageLeaveServlet extends HttpServlet {

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
         
            String[] dates = request.getParameterValues("leavedate");
            String[] status = request.getParameterValues("statusList");
            String empid = request.getParameter("empid");
            String compid = request.getParameter("compid");
            
            
            for(int i=0;i<dates.length;i++){
               
//                 
                 conectionClass c = new conectionClass();
                 QuerisClass query = new QuerisClass();
                 c.excuteQuery(query.updateStatusForSpecificEmpandDate(compid,empid,getDataFormated(dates[i]),status[i]));
                 c.closeConnection();
                
            }
         String JSPFile = "/leavespage.jsp?buttonid=0-0-0-0";
            ServletContext sc = this.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher(JSPFile);
            rd.include(request, response);
        }finally {            
            out.close();
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
    
}
