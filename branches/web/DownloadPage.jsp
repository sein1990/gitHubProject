<%-- 
    Document   : DownloadPage
    Created on : May 21, 2017, 4:41:55 PM
    Author     : user
--%>

<%@page import="java.io.IOException"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       
        
        
    </head>
    <body>
        
        <%
            
        
            String path="ww",fileName="ww";
            if(request.getParameter("path") != null){
               path = request.getParameter("path") ;
            }
            if(request.getParameter("pdfname") != null){
               fileName = request.getParameter("pdfname") ;
             }
            out.print(path+"-"+fileName);
             BufferedInputStream buf = null;
             ServletOutputStream myOut = null;

             try {

                 myOut = response.getOutputStream();             //Getting mistake here
                 File myfile = new File(path + fileName);

                 //set response headers
                 response.setContentType("application/pdf");         //I want to download a PDF file

                 response.addHeader(
                                "Content-Disposition", "attachment; filename=" + fileName);

                 response.setContentLength((int) myfile.length());

                 FileInputStream input = new FileInputStream(myfile);
                 buf = new BufferedInputStream(input);
                 int readBytes = 0;

                 //read from the file; write to the ServletOutputStream
                 while ((readBytes = buf.read()) != -1) {
                         myOut.write(readBytes);
                 }
   
            } catch (IOException ioe) {
               } finally {
 //close the input/output streams
                        if (myOut != null) {
                            myOut.close();
                        }
                        if (buf != null) {
                            buf.close();
                        }

                       
                    } 
            
            
         
            out.print(path+"-"+fileName);
        %>
        
        
    </body>
</html>
