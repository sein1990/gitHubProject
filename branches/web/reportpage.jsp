<%@page import="java.util.Map"%>
<%@page import="Models.Branch"%>
<%@ page import = "JavaCodePackage.MyDate" %>
<%@ page import = "JavaCodePackage.InternalMainClass" %>
<%@ page import = "Models.Employee" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>MeTL-Attendance</title>

<!-- CSS -->
<link href="style/css/transdmin.css" rel="stylesheet" type="text/css" media="screen" />
<link href="style/css/calendercss.css" rel="stylesheet" type="text/css" media="screen" />
<link href="style/css/jquery-ui.css" rel="stylesheet" type="text/css" media="screen" />

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
        String[] buttonNames = {"Emp Leaves Summry","Emp Wise Leave HC","Branch Att. - Leave","Branch Wise - Summry"};
        InternalMainClass main = new InternalMainClass();
        main.prepareBranchesData();
   
        Integer AdminBranchID = (Integer) session.getAttribute("Permission");
           
    %>
	
    buttonId =  "<%=buttonid%>";
    var HTMLCode6  = buildReportForm();
    document.getElementById('main').innerHTML = HTMLCode6;
    
  
    
    
}
    
function getEmployeeAndBranchData(){
    if(buttonId == 0 || buttonId == 1){
        var HTMLCode4 ="";
        var selectedBranch = document.getElementById("branchname").value;
     
       
        <%
            main.prepareEmployeeData(AdminBranchID);
            for (int i=0;i<main.getEmployeeVec().size();i++) {
                Employee employee = main.getEmployeeVec().get(i);
                String branchId = Integer.toString(employee.getEmployeeBranchID());
                String branchName = main.getSpecificBranch(employee.getEmployeeBranchID());

            %>		
                 
                var empBranch = "<%=branchName%>";
                var empBranchID = "<%=branchId%>";
                
                var branchId = getIdOnly(selectedBranch);
               
                var data1 = "<%=employee.getEmployeeName()%>";	
		var data2 = "<%=employee.getEmployeeID()%>";	
                var data3 = data1 + "-" + data2;
		
                if(selectedBranch == "All Branches" || empBranchID == branchId || selectedBranch == empBranch){
                    HTMLCode4 = HTMLCode4 + "<option value='"+data3+"'>";
		}
                 
			
	<%			
            }
	%>
      	document.getElementById('employeesdata').innerHTML = HTMLCode4;	
    }
   
        
} 
function getIdOnly(str){
    return str.substring(str.indexOf("-")+1, str.length);
}

function buildReportForm(){
    
    <%
       
        String form = "<br><br><br><br><br><form action='ReportServletNew' ";
        form = form + " class='jNice' id='contenttable'><fieldset> ";
        form = form +"<p><label>Branch/Type :</label> &nbsp &nbsp &nbsp &nbsp ";
        form = form +"&nbsp &nbsp &nbsp &nbsp &nbsp ";
        form = form +"<input list='branches' id='branchname' name='branchname' ";
        form = form +"required onchange='getEmployeeAndBranchData();'></p>";
        form = form +"<datalist id='branches'>";
        if(AdminBranchID == 0){
           
            for (Map.Entry<Integer, Branch> entry : main.getBranchVec().entrySet()) {
                    Integer key = entry.getKey();
                    Branch value = entry.getValue();
                    String str = value.getBranchName()+"-"+key;
                    form = form +"<option value='"+str+"'>";
                }
            }
            else{
		form = form + "<option value='"+main.getSpecificBranch(AdminBranchID)+"'>";
            }
        form = form +"</datalist>";
        
        %>
      
    var HTMLCode  = "<%=form%>";
    
    if(buttonId == 0 || buttonId == 1){
        HTMLCode =  HTMLCode + "<p><label>Employee Name / ID:</label>  &nbsp &nbsp ";
        HTMLCode =  HTMLCode +"<input list='employeesdata' id='employeename' name='employeename' required></p>";
        HTMLCode =  HTMLCode + "<datalist id='employeesdata'>";
        HTMLCode =  HTMLCode + "</datalist>";
        
    }
   
    HTMLCode = HTMLCode + "<select name='reporttypeList' id='reporttypeList' onchange='reportTypeSelected(this.value)'>";
    HTMLCode = HTMLCode + "<option value=0>Choose Report Type</option>";
    if (buttonId != 1 ) 
        HTMLCode = HTMLCode + "<option value=1>Daily</option>";
    if (buttonId != 3 ){
        HTMLCode = HTMLCode + "<option value=2>Monthly-Yearly</option>";
        HTMLCode = HTMLCode + "<option value=3>Period</option>";
       
    }
    if (buttonId == 0 ){
        HTMLCode = HTMLCode + "<option value=4>Employee History</option>";   
    }
   
    HTMLCode = HTMLCode +"</select></fieldset>";
    HTMLCode = HTMLCode + "<fieldset id='formfield'></fieldset></form>";   
    return HTMLCode;
            
}

function reportTypeSelected(repID){
    reportId = repID;
    var b = new reportformbuilder(buttonId,reportId);
    var HTMLCode = b.buildTheForm();
    document.getElementById('formfield').innerHTML = HTMLCode;	
}




 </script>
</head>

<body onload="pageBuilder();">
	<div id="wrapper">
    <img src="METLLogo.jpg" style="width:150px;height:100px;"></a>
        
	<%if(request.getSession().getAttribute("Permission")==null){ 
            response.sendRedirect("index.jsp");
	}%>
		
        <jsp:include page="MainMenu.jsp?tabid=2" />
		
        <div id="containerHolder">
            <div id="container">
        	<div id="sidebar">
                   <ul class="sideNav">
                        <li><a href="reportpage.jsp?buttonid=0" <% if(buttonid == 0)  out.print("class='active'"); %> ><%= buttonNames[0]%></a></li>
                        <li><a href="reportpage.jsp?buttonid=1" <% if(buttonid == 1)  out.print("class='active'"); %> ><%= buttonNames[1]%></a></li>
			<li><a href="reportpage.jsp?buttonid=2" <% if(buttonid == 2)  out.print("class='active'"); %> ><%= buttonNames[2]%></a></li>
			<li><a href="reportpage.jsp?buttonid=3" <% if(buttonid == 3)  out.print("class='active'"); %> ><%= buttonNames[3]%></a></li>
                    </ul>
                </div>
		<h2><a href="#">Report Board</a> &raquo; <a href="#" class="active"</a>
                    <% if(buttonid == 0)  
                            out.print(buttonNames[0]);
                        else if(buttonid == 1)
                            out.print(buttonNames[1]); 
			else if(buttonid == 2)
                            out.print(buttonNames[2]); 
			else if(buttonid == 3)
			    out.print(buttonNames[3]); 
                    %>
		</h2>
                        
                <div id="main">
                   
               
                </div>
                <div class="clear"></div>
            </div>
        </div>	
    </div>
    <!-- // #wrapper -->
	
</body>
</html>

	
	
	                    	
                                  
                                    
                                 
				  