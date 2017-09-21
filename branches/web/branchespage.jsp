<%@ page import = "JavaCodePackage.MyDate" %>
<%@ page import = "JavaCodePackage.InternalMainClass" %>
<%@ page import = "Models.Branch" %>
<%@ page import = "java.util.Map" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>MeTL-Attendance</title>

<!-- CSS -->
<link href="style/css/transdmin.css" rel="stylesheet" type="text/css" media="screen" />
<link href="style/css/jquery-ui.css" rel="stylesheet" type="text/css" media="screen" />


<!--[if IE 6]><link rel="stylesheet" type="text/css" media="screen" href="style/css/ie6.css" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" type="text/css" media="screen" href="style/css/ie7.css" /><![endif]-->

<!-- JavaScripts-->
<script type="text/javascript" src="style/js/jquery.js"></script>
<script type="text/javascript" src="style/js/jNice.js"></script>
<script type="text/javascript" src="calenderbuilder.js"></script>
<script type="text/javascript" src="reportformbuilder.js"></script>
<script type="text/javascript" src="Classtrial.js"></script>
 
 
<script>

var buttonId = 0;
var reportId = 0;



function buildBranchesTable(){
	
	
	<%  
		
		InternalMainClass main = new InternalMainClass();
		main.prepareBranchesData();
		String HTMLCode = "<br><br><br><br><br><form action='ManageBranchServlet' method='post' class='jNice' id='contenttable'>";
		HTMLCode = HTMLCode + "<table cellpadding='0' cellspacing='0' >";
		
		for (Map.Entry<Integer, Branch> entry : main.getBranchVec().entrySet()) {
				
				Integer key = entry.getKey();
                Branch value = entry.getValue();
				HTMLCode = HTMLCode + "<tr><td>"+value.getBranchName()+"</td>";
				HTMLCode = HTMLCode + "<td><input type='number' max='31' min='1' id='opennumbers' name='opennumbers' value="+value.getNumberOfOpenDays()+" required class='text'/></td>";
				HTMLCode = HTMLCode + "<td><input type='hidden' id='branchid' name='branchid' value='"+key+"'/></td></tr>";
		}
		
		HTMLCode = HTMLCode + "</table><input type='submit' class='processbutton' value='Update Branch'/></form>";
	%>
	
	var HTMLCode  = "<%=HTMLCode%>";
	document.getElementById('main').innerHTML = HTMLCode;
}

var buttonId = 0;
function pageBuilder(){
	
		
		<%
			int buttonid=0;
			if(request.getParameter("buttonid") != null)
				buttonid = Integer.parseInt(request.getParameter("buttonid") );
			
			String[] buttonNames = {"Manage Branch"};
		
		%>
		buttonId="<%=buttonid%>";
		if(buttonId == 0){
			buildBranchesTable();
		}
}




 </script>
</head>

<body onload="pageBuilder();">
	<div id="wrapper">
    	<!-- h1 tag stays for the logo, you can use the a tag for linking the index page -->
    	      <img src="METLLogo.jpg" style="width:150px;height:100px;"></a>
        
        <!-- You can name the links with lowercase, they will be transformed to uppercase by CSS, we prefered to name them with uppercase to have the same effect with disabled stylesheet -->
        <%	if(request.getSession().getAttribute("Permission")==null){ 
				response.sendRedirect("index.jsp");
		}%>
		<jsp:include page="MainMenu.jsp?tabid=4" />
		
        <!-- // #end mainNav -->
       
        <div id="containerHolder">
            <div id="container">
        	<div id="sidebar">
                   <ul class="sideNav">
					<li><a href="branchespage.jsp?buttonid=0" <% if(buttonid == 0)  out.print("class='active'"); %> ><%= buttonNames[0]%></a></li>
							 
                   </ul>
                </div>
				<h2><a href="#">Branches Board</a> &raquo; <a href="#" class="active"</a>
					<% if(buttonid == 0)  
						out.print(buttonNames[0]);
						
						
					%>
				</h2>
                <div id="main">
					
				
              
                </div>
                <!-- // #main -->
                
                <div class="clear"></div>
			
               
            </div>
            <!-- // #container -->
        </div>	
        <!-- // #containerHolder -->
        
       
    </div>
    <!-- // #wrapper -->
	
	
	
	
</body>
</html>

	
	
	                    	
                                  
                                    
                                 
				  