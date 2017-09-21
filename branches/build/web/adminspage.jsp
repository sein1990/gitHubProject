<%@ page import = "JavaCodePackage.MyDate" %>
<%@ page import = "JavaCodePackage.InternalMainClass" %>
<%@ page import = "Models.Branch" %>
<%@ page import = "Models.Admin" %>
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

							
function pageBuilder(){
	
		
		<%
			int buttonid=0;
			if(request.getParameter("buttonid") != null)
				buttonid = Integer.parseInt(request.getParameter("buttonid") );
			
			String[] buttonNames = {"Manage Admin","Add Admin"};
			InternalMainClass main = new InternalMainClass();
			main.prepareBranchesData();
			
			
			String HTMLCode ="";
		%>
		buttonId="<%=buttonid%>";
		if(buttonId == 0){
			buildAdminsTable();
		}else if(buttonId == 1)
			buildAdminsForm();
}                 


function buildAdminsForm(){
	
	<% 
		
		HTMLCode  = "<br><br><br><br><br><form action='AddAdminServlet' method='post' class='jNice' id='contenttable'><fieldset id='formfield'>";
		HTMLCode = HTMLCode +"<p><label>User Name:</label> &nbsp <input id='username' name='username' type='text' required class='text'/></p>";
		HTMLCode = HTMLCode +"<p><label>Password :</label> &nbsp &nbsp <input id='password' name='password' type='text' required class='text'/></p>";
		HTMLCode = HTMLCode +"<p><label>Branc/Type :</label>   &nbsp <input name='branchid' id=name='branchid' list='branches'></p>";
		HTMLCode = HTMLCode +"<datalist id='branches'><option value='Master User'>";
		
		for (Map.Entry<Integer, Branch> entry : main.getBranchVec().entrySet()) {
				
				Integer key = entry.getKey();
                Branch value = entry.getValue();
				HTMLCode = HTMLCode + "<option value='"+value.getBranchName()+"-"+value.getBranchId()+"'>";
			}		
		
		HTMLCode = HTMLCode + "</datalist><input type='submit' class='processbutton' value='Add Admin'/></form>";
	%>
	
	var HTMLCode  = "<%=HTMLCode%>";
	document.getElementById('main').innerHTML = HTMLCode;	
		
}

function buildAdminsTable(){
	
	<%  
		
		
		main.prepareUsersData();
		
		HTMLCode = "<br><br><br><br><br><form action='ManageAdminServlet' method='post' class='jNice' id='contenttable'>";
		HTMLCode = HTMLCode + "<table cellpadding='0' cellspacing='0' >";
		
		for (int i=0;i<main.getUserVec().size();i++) {
			
			Admin admin = main.getUserVec().get(i);
			int adminID = admin.getAdminAndBranchId();
			if(adminID == 0)
				continue;
			else
				HTMLCode = HTMLCode + "<tr><td>"+main.getSpecificBranch(adminID)+"</td>";
			HTMLCode = HTMLCode + "<td><input id='username' name='username' value='"+admin.getUserName()+"' type='text' required class='text'/></td>";
			HTMLCode = HTMLCode + "<td><input id='password' name='password' value='"+admin.getPassword()+"' type='text' required class='text'/></td>";
			HTMLCode = HTMLCode + "<td><input id='userid' name='userid' value='"+admin.getAdminAndBranchId()+"' type='hidden'/></td></tr>";
		}
		
		HTMLCode = HTMLCode + "</table><input type='submit' class='processbutton' value='Update Admin'/></form>";
	%>
	
	var HTMLCode1  = "<%=HTMLCode%>";
	document.getElementById('main').innerHTML = HTMLCode1;
	
}

var buttonId = 0;





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
		<jsp:include page="MainMenu.jsp?tabid=5" />
		
        <!-- // #end mainNav -->
       
        <div id="containerHolder">
            <div id="container">
        	<div id="sidebar">
                   <ul class="sideNav">
                    <li><a href="adminspage.jsp?buttonid=0" <% if(buttonid == 0)  out.print("class='active'"); %> ><%= buttonNames[0]%></a></li>
                    <li><a href="adminspage.jsp?buttonid=1" <% if(buttonid == 1)  out.print("class='active'"); %> ><%= buttonNames[1]%></a></li>
							 
                   </ul>
                </div>
				<h2><a href="#">Leaves Board</a> &raquo; <a href="#" class="active"</a>
					<% if(buttonid == 0)  
						out.print(buttonNames[0]);
						else if(buttonid == 1)
						   out.print(buttonNames[1]); 
						
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

	
	
	                    	
                                  
                                    
                                 
				  