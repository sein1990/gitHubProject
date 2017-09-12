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
 * @author Sein 90
 */
@WebServlet(urlPatterns = {"/A2Servlet"})
@MultipartConfig
public class A2Servlet extends HttpServlet {
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
    String numberOfFiles=null;
    String caseidupdate=null;
    String reason=null;
    String unity=null;
    String remarks=null;
    String recovered=null;
    String salaryDeposit=null;
    String totalAmount=null;
    int x=1;
    String fileRemarks;
    String fileClosed=null;
    String empID=null;
    String fileOne=null;
    String fileName=null;
    String dbID=null;
    String currencyType=null;
    boolean submit=false;
    ExpertLegalPortalOperation Obj=new ExpertLegalPortalOperation();
    FileInfoOperation fileObj;
    int lastID=0;
    String exte;   
    int i=0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
               int salaryDepositAmount = Integer.parseInt(salaryDeposit);
                int recoveredAmount=Integer.parseInt(recovered);
                int total=recoveredAmount+salaryDepositAmount;
                totalAmount=Integer.toString(total);
                 String totalAmount1=Integer.toString(total);
                totalAmount=totalAmount1+currencyType;
                    dbID = Obj.a2_notReturnFromLeave(date, reason, name,unity,remarks, recovered,salaryDeposit, totalAmount, fileClosed, empID,caseidupdate);
                if(fileRemarks!=null){
                    String attachmentID=fileObj.selectAttachmentLastRecord(dbID); 
                    fileObj.updateLastAttachmentRemarks(fileRemarks, attachmentID);
                 }
              response.sendRedirect("formpage.jsp?pageid=2&caseid="+dbID+""); 
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
                         if(fieldName.equals("reason"))
                           reason = item.getString();
                        if(fieldName.equals("unity"))
                           unity = item.getString();
                        if(fieldName.equals("remarks"))
                           remarks = item.getString();
                        if(fieldName.equals("recovered"))
                           recovered = item.getString();
                        if(fieldName.equals("salaryDeposit"))
                           salaryDeposit = item.getString();                        
                        if(fieldName.equals("totalAmount"))
                           totalAmount = item.getString();
                        if(fieldName.equals("fileClosed"))
                           fileClosed = item.getString();
                        if(fieldName.equals("empID")){
                            String[] array = item.getString().split("-");
                            name = array[0];
                            empID = array[1];
                        }
                         if(fieldName.equals("currencyType")){
                           currencyType=item.getString();
                        }
                        if(fieldName.equals("fileRemarks")){
//                            fileRemarks[i]=item.getString();
//                            i++;
                            fileRemarks=item.getString();
                        }
//                        if(fieldName.equals("numberOfFiles")){
//                        numberOfFiles=(item.getString());
//                          x=Integer.parseInt(numberOfFiles);
//                        }
                       
                        if(fieldName.equals("fileOne"))
                           fileOne = item.getString();
                        if(fieldName.equals("sub"))
                           submit = true;
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
