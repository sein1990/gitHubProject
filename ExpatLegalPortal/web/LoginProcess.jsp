
<%@page import="ExpertLegalPortalClass.login"%>
<jsp:useBean id="UserItems" class="ExpertLegalPortalClass.UserItems" scope="session"/>
<jsp:setProperty name="UserItems" property="*"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MeTL</title>
    </head>
    <body>

<%
    //STEP 3: Open a connection

          //  return connbio;
String result=login.loginCheck(UserItems);
//user is exist
        if(result.equals("true"))
        {
             response.sendRedirect("Home.jsp");
       
         }
 
        //if use is not exist
        if(result.equals("false"))
        {
            response.sendRedirect("index.jsp?status=false");
        }
        //if error occur
           if(result.equals("error"))
        {
            response.sendRedirect("index.jsp?status=error");
        }
 
%>
    </body>
</html>
