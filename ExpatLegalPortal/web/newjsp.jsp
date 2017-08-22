
<%@page import="org.jfree.chart.ChartUtilities"%>
<%@page import="java.io.File"%>
<%@page import="org.jfree.chart.entity.StandardEntityCollection"%>
<%@page import="org.jfree.chart.ChartRenderingInfo"%>
<%@page import="org.jfree.chart.JFreeChart"%>
<%@page import="org.jfree.chart.ChartFactory"%>
<%@page import="org.jfree.data.general.DefaultPieDataset"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
  <FORM ACTION="PieChartServlet" METHOD="POST">
Maths:<INPUT TYPE="TEXT" NAME="Maths"><BR>
Physics:<INPUT TYPE="TEXT" NAME="Physics"><BR>
Chemistry:<INPUT TYPE="TEXT" NAME="Chemistry"><BR>
Biology:<INPUT TYPE="TEXT" NAME="Biology"><BR>
English:<INPUT TYPE="TEXT" NAME="English"><BR>
<INPUT TYPE="SUBMIT" VALUE="Submit">  
</FORM>
    </body>
</html>
