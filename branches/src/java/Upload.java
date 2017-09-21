/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author user
 */
public class Upload extends HttpServlet {
     private String UPLOAD_DIRECTORY ="C:/Users/user/Desktop/dd";

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
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
            out.print(description); 
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
      String description;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
//         if(ServletFileUpload.isMultipartContent(request)){
//            try {
//                List<FileItem> multiparts = new ServletFileUpload(
//                                         new DiskFileItemFactory()).parseRequest(request);
//              
//                for(FileItem item : multiparts){
//                    if(!item.isFormField()){
//                       String name = new File(item.getName()).getName();
//                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
//                    }
//                    else{        
                        description=request.getParameter("description");
                   //     description = item.getString();

//                    }
                      
//                }
//           
//               //File uploaded successfully
//               request.setAttribute("message", "File Uploaded Successfully");
//            } catch (Exception ex) {
//               request.setAttribute("message", "File Upload Failed due to " + ex);
//            }          
//         
//        }else{
//            request.setAttribute("message",
//                                 "Sorry this Servlet only handles file upload request");
//        }


          
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
