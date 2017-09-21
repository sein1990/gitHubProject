/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import JavaCodePackage.ConstantClass;
import JavaCodePackage.QuerisClass;
import JavaCodePackage.conectionClass;
import Models.Admin;
import com.oreilly.servlet.MultipartRequest;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
 * @author user
 */
@WebServlet(urlPatterns = {"/UandSServlet"})
@MultipartConfig
public class UandSServlet extends HttpServlet {    
    final String email= "branches.att@metl.net"; //"abdulrahim@metl.net";
    final String password = "branches.att";//"abdul@2017";
    //final String path="D:/BranchesAttUploadFile/";
 //   private String UPLOAD_DIRECTORY ="C:/Users/user/Desktop/k";
    private String UPLOAD_DIRECTORY ="C:/Users/Administrator/Desktop/dd";   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
          
        String host = "mail.metl.net";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");

//        // Get the default Session object.
        Session mailSession = Session.getDefaultInstance(properties, new Authenticator() {
       protected PasswordAuthentication  getPasswordAuthentication(){
           return new PasswordAuthentication(
                        email, password);
                        }
            });
        try  {          
                      out.print(date);
                      out.print(month);
                      out.print(year);
                      out.print(branchid);    
                
               
               QuerisClass q =  new QuerisClass();
               String query  = q.getSpecificBranchesQuery(Integer.parseInt(branchid));
               conectionClass c = new conectionClass();
               ResultSet res1 = c.excuteQuery(query);
                while(res1.next()) {
                        brachname = res1.getString(2); 
//                        out.print("<script language='JavaScript'>alert('"+
//                                ConstantClass.getStatus(Status) +"');</script>");
                   }
                
                
                
                
                
                
//        MultipartRequest m=new MultipartRequest(request,path);
//        out.print("successfully uploaded");
//            
//           String oriFileName = m.getOriginalFileName("fname");        
//            out.print(oriFileName);   
//            
         Message message = new MimeMessage(mailSession);
//
//         // Set From: header field of the header.
         message.setFrom(new InternetAddress(email));
//
//         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(email));
         message.setSubject("Attendence of "+brachname+"-"+date+"-"+ConstantClass.fullMonthNames[Integer.parseInt(month)+1]+"-"+year);

//         // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();
//
//         // Now set the actual message
         messageBodyPart.setText("This mail sent automaticaly by the system");
//
//         // Create a multipar message
         Multipart multipart = new MimeMultipart();
//
//         // Set text message part
         multipart.addBodyPart(messageBodyPart);
//
//         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
//      
//         
//         
         //String filename=request.getParameter("file");
         String filename = UPLOAD_DIRECTORY+File.separator + name;
         DataSource source = new FileDataSource(filename);
         String ext = name.substring(name.indexOf("."));
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(brachname+"-"+date+"-"+ConstantClass.fullMonthNames[Integer.parseInt(month)+1]+"-"+year+ext);
         multipart.addBodyPart(messageBodyPart);
//
//         // Send the complete message parts
         message.setContent(multipart);
//                
//            
//                
//                
//                // Send message
        Transport.send(message);
        String result = "Sent message successfully....";  
        out.println(result);   
          
        } 
         catch (MessagingException mex) {
                out.print(mex.getMessage());
                String result = "Error: unable to send message....";   
        }
        finally {            
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UandSServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String date,month,year,branchid,name,brachname;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if(ServletFileUpload.isMultipartContent(request)){
               try {
                   List<FileItem> multiparts = new ServletFileUpload(
                                            new DiskFileItemFactory()).parseRequest(request);
                 
                   for(FileItem item : multiparts){
                       if(!item.isFormField()){
                           name = new File(item.getName()).getName();
                           item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                           
                       }
                       else{                         
                           String itemName = item.getFieldName();
                           if(itemName.equals("date"))
                               date =  item.getString();
                           else if(itemName.equals("month"))
                               month=item.getString();
                           else if(itemName.equals("year"))
                               year=item.getString();
                           else if(itemName.equals("branchid"))
                               branchid=item.getString();
                           
                           }
                   }           
                  //File uploaded successfully
                  request.setAttribute("message", "File Uploaded Successfully");
               } catch (Exception ex) {
                  request.setAttribute("message", "File Upload Failed due to " + ex);
               }          
            
           }
            else{
               request.setAttribute("message",
               "Sorry this Servlet only handles file upload request");
           }
           processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UandSServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
