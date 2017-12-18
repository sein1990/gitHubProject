/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codepackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
@WebServlet(name = "getCheckBoxValue", urlPatterns = {"/getCheckBoxValue"})
public class getCheckBoxValue extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
                pathClass pathObject=new pathClass();
                String path=pathObject.path();
               //File creation
                String fileName="temIPAddress.txt";
                String strPath=path+fileName;
                File strFile = new File(strPath);
                boolean fileCreated = strFile.createNewFile();
                //File appending
                Writer objWriter = new BufferedWriter(new FileWriter(strFile));           
                String valueSelected[]=request.getParameterValues("checkBoxValue");
                for(int i=0; i<valueSelected.length;i++)
                {
                   objWriter.write(valueSelected[i]+"#");
                   writeIPAdrressToTextFile writetxtObj=new writeIPAdrressToTextFile();
                   writetxtObj.writeText();
                }
                    objWriter.flush();
                    objWriter.close();
                    writeIPAdrressToTextFile writetxtObj=new writeIPAdrressToTextFile();
                    writetxtObj.writeText();
                    
                    response.sendRedirect("index.jsp"); 
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

}
