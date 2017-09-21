<%	
	if(request.getSession().getAttribute("Permission")==null){ 
			response.sendRedirect("index.jsp");
		}

	session.setAttribute("Username", null);
    session.setAttribute("Permission", null);
	session.invalidate();
	response.sendRedirect("index.jsp");

%>