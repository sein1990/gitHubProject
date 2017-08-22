/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ExpertLegalPortalClass.ExpertLegalPortalOperation;
import ExpertLegalPortalClass.FileInfoOperation;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author USER
 */
@WebServlet(urlPatterns = {"/SalaryStopped"})
@MultipartConfig
public class SalaryStopped extends HttpServlet {
     private final String UPLOAD_DIRECTORY ="C:\\Users\\USER\\Documents\\NetBeansProjects\\ExpatLegalPortal\\up";

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
                ExpertLegalPortalOperation Obj=new ExpertLegalPortalOperation();
                Obj.salaryStopped(name, date, unity,remarks, actionTaken, stoppedBy,empID, caseidupdate);
                response.sendRedirect("formpage.jsp?pageid=5&caseid="+caseidupdate+""); 
        } finally {
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
    String name;
    String date;
    String caseidupdate;
    String unity;
    String actionTaken;
    String stoppedBy;;
    String remarks;
    String empID;
    String fileOne;
    String fileName=null;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           if(ServletFileUpload.isMultipartContent(request)){
            try {
               
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts){
                    if(!item.isFormField())
                    {   
                        FileInfoOperation fileObj=new FileInfoOperation();
                        int lastID=fileObj.highestID();
                       
                        fileName = new File(item.getName()).getName();
                        String exte=fileName.substring(fileName.indexOf("."));
                        item.write( new File(UPLOAD_DIRECTORY + "\\" + caseidupdate+"#"+lastID+exte));
                        fileObj.addToAttachment(UPLOAD_DIRECTORY, caseidupdate, fileName, lastID, exte);
                    }
                    else{        
                        String fieldName = item.getFieldName();
                        if(fieldName.equals("caseidupdate"))
                            caseidupdate = item.getString();
                         if(fieldName.equals("date"))
                            date = item.getString();
                        if(fieldName.equals("name"))
                            name = item.getString();
                        if(fieldName.equals("unity"))
                           unity = item.getString();    
                        if(fieldName.equals("remarks"))
                           remarks = item.getString(); 
                        if(fieldName.equals("actionTaken"))
                           actionTaken = item.getString();
                        if(fieldName.equals("stoppedBy"))
                           stoppedBy = item.getString();
                        if(fieldName.equals("empID"))
                           empID = item.getString();
                        if(fieldName.equals("fileOne"))
                           fileOne = item.getString();
                    }
                }
               //File uploaded successfully
               request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
         
        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
        
        
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