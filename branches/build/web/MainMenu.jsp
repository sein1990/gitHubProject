<%-- 
    Document   : MainMenu
    Created on : Nov 29,1 2016, 11:49:09 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MeTL-Attendance</title>
    </head>
    <body>
       <%	if(request.getSession().getAttribute("Permission")==null){ 
				response.sendRedirect("index.jsp");
		}%>
	   <%  
			int tabid=1;
			if(request.getParameter("tabid") != null){
				tabid = Integer.parseInt(request.getParameter("tabid") );
			}
		    Integer AdminBranchID = (Integer) session.getAttribute("Permission");
		
		%>
	   
	   
        <ul id="mainNav">
            <li><a href="homepage.jsp"  <% if(tabid == 1) out.print("class='active'");%>>Attendance Board</a></li> 
            <li><a href="leavespage.jsp?buttonid=0-0-0-0" <% if(tabid == 3) out.print("class='active'");%>>Leaves</a></li>
			<% if(AdminBranchID ==0) { %>
				<li><a href="branchespage.jsp" <% if(tabid == 4) out.print("class='active'");%>>Branches</a></li>
				<li><a href="adminspage.jsp" <% if(tabid == 5) out.print("class='active'");%>>Admins</a></li>
                               
			<%}%>
                        <li><a href="masterpage.jsp" <% if(tabid == 6) out.print("class='active'");%>>Master Page</a></li>
			<li><a href="reportpage.jsp" <% if(tabid == 2) out.print("class='active'");%>>Reports</a></li>
            <li class="logout"><a href="logout.jsp">LOGOUT</a></li>
        </ul>
       
    </body>
</html>
