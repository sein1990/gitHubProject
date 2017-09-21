/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class ReportServlet extends HttpServlet {

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
              out.println("<h1>dddddddddddd</h1>");
            
            
            
            
            
            /* TODO output your page here. You may use following sample code. */
           
//            String buttonID = request.getParameter("buttonID");
//            String reportID = request.getParameter("reportID");
//            
//
//            Vector<String> BranchesName = getBranchList();
//            
//            if(buttonID.equals("0") && ! reportID.equals("4")){
//                 String HTMLCode = buildTheHeader()+buildTheMainPart_DetailsEmpWise(BranchesName,"Employee Name--Branch Name","Dates")+buildTheFooter();
//                 out.println(HTMLCode);
//            }
//            else if(buttonID.equals("0") && reportID.equals("4")){
//                 String HTMLCode = buildTheHeader()+buildTheMainPart_DetailsHistory(BranchesName,"Employee Name","Dates")+buildTheFooter();
//                 out.println(HTMLCode);
//            }
//            
//            
//            else if(buttonID.equals("3")){
//                 String HTMLCode = buildTheHeader()+buildTheMainPart_Conslidate(BranchesName,"11-11-1992","Brach Name")+buildTheFooter();
//                 out.println(HTMLCode);
//            }
//            else  if(buttonID.equals("1")){
//                 String HTMLCode = buildTheHeader()+buildTheMainPart_Conslidate(BranchesName,"Employee Name","Dates")+buildTheFooter();
//                 out.println(HTMLCode);
//            }
//           
//            else{
//                out.println(" "+buttonID+" "+reportID+" ");
//            }
            
            
        }finally {            
            out.close();
        } 
    }
    
    private Vector<String> getBranchList(){
        Vector<String> BranchesName = new Vector<String>();
        for (int i = 0; i < 10; i++) {
            BranchesName.add("ffffffff");
        }
        return BranchesName;
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
