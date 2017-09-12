/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ExpertLegalPortalClass.ExpertLegalPortalOperation;
import ExpertLegalPortalClass.FileInfoOperation;
import ExpertLegalPortalClass.PathClass;
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
@WebServlet(urlPatterns = {"/LeaveExtension"})
@MultipartConfig
public class LeaveExtension extends HttpServlet {
    PathClass pathObj=new PathClass();
    private final String UPLOAD_DIRECTORY =pathObj.path();
   
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   String date=null;
    String name=null;
    String caseidupdate=null;
     String unity=null;
    String from=null;
    String to=null;
    String extendedDay=null;
    String actual=null;
    String actionTaken=null;
    String empID=null;
    String fileOne=null;
    String fileName=null;
    String fileRemarks=null;
    String dbID=null;
    boolean submit=false;
    ExpertLegalPortalOperation Obj=new ExpertLegalPortalOperation();
    FileInfoOperation fileObj;
    int lastID=0;
    String exte;

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {            
                dbID=Obj.LeaveExtension(date, name, unity, from, to,extendedDay, actual, actionTaken, empID, caseidupdate);
              
                 if(fileRemarks!=null){
                    String attachmentID=fileObj.selectAttachmentLastRecord(dbID); 
                    fileObj.updateLastAttachmentRemarks(fileRemarks, attachmentID);
                 }
                 
                    response.sendRedirect("formpage.jsp?pageid=9&caseid="+dbID+""); 
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
                        fileObj=new FileInfoOperation();
                        lastID=fileObj.highestID();      
                        fileName = new File(item.getName()).getName();
                        exte=fileName.substring(fileName.indexOf("."));
                        item.write( new File(UPLOAD_DIRECTORY +lastID+exte));
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
                        if(fieldName.equals("from"))
                           from = item.getString();
                        if(fieldName.equals("to"))
                           to = item.getString();
                        if(fieldName.equals("extendedDay"))
                           extendedDay = item.getString();                        
                        if(fieldName.equals("actual"))
                           actual = item.getString();
                        if(fieldName.equals("actionTaken"))
                           actionTaken = item.getString();
                        if(fieldName.equals("empID")){
                          String[] array = item.getString().split("-");
                          name = array[0];
                          empID = array[1];
                        }
                        if(fieldName.equals("fileRemarks"))
                            fileRemarks=item.getString(); 
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
