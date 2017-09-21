<%@page import="JavaCodePackage.ConstantClass"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="JavaCodePackage.InternalMainClass"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="JavaCodePackage.QuerisClass"%>
<%@page import="JavaCodePackage.conectionClass"%>


<%-- 
    Document   : try
    Created on : Jan 25, 2017, 9:22:47 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
<%        
                String dailyDate = "2017-02-15";
                String branchName = request.getParameter("branchname");
                String employeeName = request.getParameter("employeename");
                
                String branchId = "32";
                String employeeId = "005";
                
                QuerisClass q =  new QuerisClass();
                
                String query  = q.getReportDataEmpWiseDaily(branchId,employeeId,dailyDate);
                conectionClass c = new conectionClass();
                
                 try {
                        ResultSet res = c.excuteQuery(query);
                        if(res != null){
                            while(res.next()) {

                                String value = res.getString(1);
                                out.println("<h1>Servlet ReportServletNew at " + value + "</h1>");
                            }
                        }
                    }catch (SQLException ex) {
                        Logger.getLogger(InternalMainClass.class.getName()).log(Level.SEVERE, null, ex);
                    }

                                  
                out.println("<h1>Servlet ReportServletNew at " + dailyDate + "</h1>");
                out.println("<h1>Servlet ReportServletNew at " + branchId + "</h1>");
                out.println("<h1>Servlet ReportServletNew at " + employeeId + "</h1>");

                
           
      
    


  %>      
        
        
    </body>
</html>
