<%@page import="JavaCodePackage.ConstantClass"%>
<%@page import="Models.SummryLeaveDataClass"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.sql.SQLException"%>
<%@page import="JavaCodePackage.conectionClass"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="JavaCodePackage.QuerisClass"%>
<%@page import="JavaCodePackage.MyDate" %>
<%@page import="JavaCodePackage.InternalMainClass"%>
<%@page import="Models.Branch" %>
<%@page import="Models.Leave" %>
<%@page import="Models.LeaveInfo" %>
<%@page import="Models.Employee"%>
<%@page import="Models.Admin"%>
<%@page import="java.util.Map"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Metl-Attendance</title>

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
var empId=0;
var branchId=0;
function pageBuilder(){     

    <%
        
        int masterVar =0; 
	int buttonid=0;
        String empid="0";
        String empname="0";
        String branchid="0";
        
	if(request.getParameter("buttonid") != null){
            
            String prameter = request.getParameter("buttonid");
            String strButtonId = prameter.substring(0,prameter.indexOf("-"));
            buttonid = (int) Integer.parseInt(strButtonId);
            
            prameter = prameter.substring(prameter.indexOf("-")+1);
            empid = prameter.substring(0,prameter.indexOf("-"));
            
            prameter = prameter.substring(prameter.indexOf("-")+1);
            branchid = prameter.substring(0,prameter.indexOf("-"));
            
            empname = prameter.substring(prameter.indexOf("-")+1);
           
            if(buttonid == 0 && !branchid.equals("0")){
             
            %>  
                buildLeavesTableForSelectedEmp();
            <%
                            
            }
            else if(buttonid == 1 && branchid.equals("0")){
            %> 
                buildLeavesForm();
            <%
            }
            else if(buttonid == 0 && branchid.equals("0")){
            %>     
               
               buildLeavesTable();
              
            <% 

            }
            else if(buttonid == 1 && !branchid.equals("0")){
            %>     
               buildLeavesFormForSelectedEmp(); 
            <% 

            }
        }
        
        Integer AdminBranchID = (Integer) session.getAttribute("Permission");
	String[] buttonNames = {"Manage Leave","Add Leave"};
	InternalMainClass main = new InternalMainClass();
	main.prepareBranchesData();
			
    %>
	
        
      
        buttonId="<%=buttonid%>";
        empId="<%=empid%>";
        branchId="<%=branchid%>";
        
}
function buildLeavesFormForSelectedEmp(){
	
	<% 
            
            SummryLeaveDataClass summryData =null;
            if( ! (branchid.equals("0") && empid.equals("0")) ) {   
                summryData = main.getEmployeeLeave(branchid,empid);
                String gander = main.getEmployeeGAander(branchid,empid);    
                masterVar =0;
                String HTMLCode1  = "<br><br><br><br><br><form action='AddLeaveServletNew' method='post' class='jNice' id='contenttable'><fieldset id='formfield'>";
                  HTMLCode1 = HTMLCode1 +"<p><label>Branch/Type :</label> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp"
                        + " <input list='branches' id='branchname' name='branchname'"
                        + " required onchange='getEmployeeAndBranchData();' value='"+main.getSpecificBranch(Integer.parseInt(branchid))+"-"+branchid+"'></p>";
                HTMLCode1 = HTMLCode1 +"<datalist id='branches'>";
                HTMLCode1 = HTMLCode1 +"</datalist>";

                if(!empname.equals("0")){ 
                    HTMLCode1 = HTMLCode1 +"<p><label>Employee Name / ID:</label>  &nbsp &nbsp "
                            + "<input list='employeesdata' id='employeename' name='employeename'"
                            + "value='"+empname+"-"+empid+"' required /></p>";
                }
                
                HTMLCode1 = HTMLCode1 +"<p><label>Leaves Type:</label> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <input list='leavesdata' id='leavetypes' name='leavetypes'  required /></p>";
                HTMLCode1 = HTMLCode1 +"<datalist id='leavesdata'>";
                HTMLCode1 = HTMLCode1 +"<option value='ANNUAL LEAVE-01'>";
                HTMLCode1 = HTMLCode1 +"<option value='SICK LEAVE-02'>";
                if(gander.equals("F"))
                    HTMLCode1 = HTMLCode1 +"<option value='MATERNITY LEAVE-03'>";
                 if(gander.equals("M"))
                    HTMLCode1 = HTMLCode1 +"<option value='PATERNITY LEAVE-04'>";
                HTMLCode1 = HTMLCode1 +"<option value='COMPASSIONATE LEAVE-05'>";
                HTMLCode1 = HTMLCode1 +"</datalist>";

                HTMLCode1 = HTMLCode1 +"<p><label>From :</label>  &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  &nbsp <input id='fromdate' name='fromdate' type='date' required></p>";
                HTMLCode1 = HTMLCode1 +"<p><label>To   :</label>  &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp<input id='todate' name='todate' type='date' required></p>";
                HTMLCode1 = HTMLCode1 +"<p><label>Upload File   :</label>  &nbsp<input id='uploadfile' name='uploadfile' type='file' class='custom-file-input' ></p>";
                HTMLCode1 = HTMLCode1 +"<input type='hidden' id='leavesunsummryprocesseddata'  name='leavesunsummryprocesseddata' value='"+summryData.getStrUnProcessedData()+"' />";
                
                HTMLCode1 = HTMLCode1 +"<input type='submit' class='processbutton' value='Add Leave'/></form><br><br><br><br><br>";
              
                
                
          //      HTMLCode1 = HTMLCode1 +"<p><label>Was Absent for: "+summryData.getLeavesData()[0]+" Days</label></p>";
                HTMLCode1 = HTMLCode1 +"<p><label>Annual Leave: "+summryData.getLeavesData()[1]+" Days</label></p>";
                //HTMLCode1 = HTMLCode1 +"<p><label>Sick Leave: "+summryData.getTotalDate()[2]+" Days</label></p>";
                if(gander.equals("F"))
                    HTMLCode1 = HTMLCode1 +"<p><label>Maternity Leave: "+summryData.getLeavesData()[3]+" Days</label></p>";
                if(gander.equals("M"))
                    HTMLCode1 = HTMLCode1 +"<p><label>Paternity Leave: "+summryData.getLeavesData()[4]+" Days</label></p>";
                HTMLCode1 = HTMLCode1 +"<p><label>Compassionate Leave: "+summryData.getLeavesData()[5]+" Days</label></p>";
                HTMLCode1 = HTMLCode1 +"<p><label>Please inform the HRD if the the Sick Leave: more than 15 Days consequently</label></p>";
                 
                
            
	%>
	
	var HTMLCode  = "<%=HTMLCode1%>";
	document.getElementById('main').innerHTML = HTMLCode;	
	<% 
            }
	%>

}


function buildLeavesTableForSelectedEmp(){
    
 
    
    <%
    main.prepareLeavesData(branchid,empid);
    //HTMLCode = "<h1> "+s+" "+branchid+" "+empid+" "+main.getDailyDetails().size()+"</h1>";
    String HTMLCode = "<br><br><br><br><br><form action='ManageLeaveServlet' method='post' class='jNice' id='contenttable'>";
    HTMLCode = HTMLCode + "<table cellpadding='0' cellspacing='0' >";
    for (int i=0;i<main.getDailyDetails().size();i++) {
            String[] leaveData = main.getDailyDetails().get(i).split("@");
            String gander = main.getEmployeeGAander(branchid,empid);    
            HTMLCode = HTMLCode + "<tr><td>"+empname+"--"+empid+"</td>";
            String[] date = leaveData[0].split(" ");
            String[] dates = date[0].split("-");
            String defaultDate = dates[0]+"-"+dates[1]+"-"+dates[2];
            HTMLCode = HTMLCode + "<td><input id='leavedate' name='leavedate' type='date' value='"+defaultDate+"' required></td>";
            
            HTMLCode = HTMLCode +"<td><select name='statusList' id='statusList class='jNiceSelectWrapperStatus' >";
            HTMLCode = HTMLCode + "<option value=-1>Absent</option>";
            HTMLCode = HTMLCode + "<option value=00>Full Day</option>";
            HTMLCode = HTMLCode + "<option value=-2>Half Day</option>";
            if(leaveData[1].equals(ConstantClass.AnnualCode))
                HTMLCode = HTMLCode + "<option value=01 selected='selected'>ANNUAL LEAVE</option>";
            else    
                HTMLCode = HTMLCode + "<option value=01>ANNUAL LEAVE</option>";
            
            if(leaveData[1].equals(ConstantClass.SickCode))
                HTMLCode = HTMLCode + "<option value=02 selected='selected'>SICK LEAVE</option>";
            else    
                HTMLCode = HTMLCode + "<option value=02>SICK LEAVE</option>";
            if(gander.equals("F")){ 
                if(leaveData[1].equals(ConstantClass.MatCode))
                    HTMLCode = HTMLCode + "<option value=03 selected='selected'>MATERNITY LEAVE</option>";
                else    
                    HTMLCode = HTMLCode + "<option value=03>MATERNITY LEAVE</option>";
            }
            if(gander.equals("M")){ 
                if(leaveData[1].equals(ConstantClass.PatCode))
                    HTMLCode = HTMLCode + "<option value=04 selected='selected'>PATERNITY LEAVE</option>";
                else    
                    HTMLCode = HTMLCode + "<option value=04>PATERNITY LEAVE</option>";
            }
            if(leaveData[1].equals(ConstantClass.CompCode))
                 HTMLCode = HTMLCode + "<option value=05 selected='selected'>COMPASSIONATE LEAVE</option>";
            else    
                 HTMLCode = HTMLCode + "<option value=05>COMPASSIONATE LEAVE</option>";
            HTMLCode = HTMLCode + "</select></td>";
           
          
            
	}		
	HTMLCode = HTMLCode + "</table><input type='hidden' id='empid'  name='empid' value='"+empid+"' />";
        HTMLCode = HTMLCode + "<input type='hidden' id='compid'  name='compid' value='"+branchid+"' />";
        HTMLCode = HTMLCode +"<input type='submit' class='processbutton' value='Manage Leave'/></form>";
   %>
   var HTMLCode1  = "<%=HTMLCode%>";
   document.getElementById('main').innerHTML = HTMLCode1;	        
}

function buildLeavesForm(){
	
	<% 
            masterVar =0;
            HTMLCode  = "<br><br><br><br><br><form action='' method='post' class='jNice' id='contenttable'><fieldset id='formfield'>";
            HTMLCode = HTMLCode +"<p><label>Branch/Type :</label> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <input list='branches' id='branchname' name='branchname' required onchange='getEmployeeAndBranchData();'></p>";
            HTMLCode = HTMLCode +"<datalist id='branches'>";
            if(AdminBranchID == 0){
                HTMLCode = HTMLCode +"<option value='All Branches'>";
		for (Map.Entry<Integer, Branch> entry : main.getBranchVec().entrySet()) {
                    Integer key = entry.getKey();
                    Branch value = entry.getValue();
                    String str = value.getBranchName()+"-"+key;
                    HTMLCode = HTMLCode + "<option value='"+str+"'>";
                }
            }
            else{
                String str = main.getSpecificBranch(AdminBranchID)+"-"+AdminBranchID;
		HTMLCode = HTMLCode + "<option value='"+str+"'>";
            }
            HTMLCode = HTMLCode +"</datalist>";
            
            HTMLCode = HTMLCode +"<p><label>Employee Name / ID:</label>  &nbsp &nbsp <input list='employeesdata' id='employeename' name='employeename' required' onchange='redirectThePage(1);'></p>";
            HTMLCode = HTMLCode +"<datalist id='employeesdata'>";
            HTMLCode = HTMLCode +"</datalist>";
		
            HTMLCode = HTMLCode +"<p><label>Leaves Type:</label> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <input list='leavesdata' id='leavetypes' name='leavetypes'  required /></p>";
            HTMLCode = HTMLCode +"<datalist id='leavesdata'>";
            HTMLCode = HTMLCode +"</datalist>";
	
            HTMLCode = HTMLCode +"<p><label>From :</label>  &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  &nbsp <input id='fromdate' name='fromdate' type='date' required></p>";
            HTMLCode = HTMLCode +"<p><label>To   :</label>  &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp<input id='todate' name='todate' type='date' required></p>";
            HTMLCode = HTMLCode +"<p><label>Upload File   :</label>  &nbsp<input id='uploadfile' name='uploadfile' type='file' class='custom-file-input' ></p>";
            
            HTMLCode = HTMLCode +"<input type='submit' class='processbutton' value='Add Leave'/></form><br><br><br><br><br>";
         

	%>
	
	var HTMLCode  = "<%=HTMLCode%>";
	document.getElementById('main').innerHTML = HTMLCode;	
		
}


function buildLeavesTable(){
	
	<% 
            masterVar =0;
            HTMLCode  = "<br><br><br><br><br><form action='' method='post' class='jNice' id='contenttable'><fieldset id='formfield'>";
            HTMLCode = HTMLCode +"<p><label>Branch/Type :</label> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <input list='branches' id='branchname' name='branchname' required onchange='getEmployeeAndBranchData();'></p>";
            HTMLCode = HTMLCode +"<datalist id='branches'>";
            if(AdminBranchID == 0){
                HTMLCode = HTMLCode +"<option value='All Branches'>";
		for (Map.Entry<Integer, Branch> entry : main.getBranchVec().entrySet()) {
                    Integer key = entry.getKey();
                    Branch value = entry.getValue();
                    String str = value.getBranchName()+"-"+key;
                    HTMLCode = HTMLCode + "<option value='"+str+"'>";
                }
            }
            else{
		HTMLCode = HTMLCode + "<option value='"+main.getSpecificBranch(AdminBranchID)+"'>";
            }
            HTMLCode = HTMLCode +"</datalist>";
            
            HTMLCode = HTMLCode +"<p><label>Employee Name / ID:</label>  &nbsp &nbsp <input list='employeesdata' id='employeename' name='employeename' required' onchange='redirectThePage(0);'></p>";
            HTMLCode = HTMLCode +"<datalist id='employeesdata'>";
            HTMLCode = HTMLCode +"</datalist>";
	
         

	%>
	
	var HTMLCode  = "<%=HTMLCode%>";
	document.getElementById('main').innerHTML = HTMLCode;	
		
}



function redirectThePage(x){
  
    var selectedEmployee = document.getElementById("employeename").value;
    var selectedBranch = document.getElementById("branchname").value;
    
    var branchID = getIdOnly(selectedBranch);
    var empID = getIdOnly(selectedEmployee);
    var empNAME = getNameOnly(selectedEmployee);
    
    
    alert("Please Wait");   
    if(x == 1)
        window.location.assign("leavespage.jsp?buttonid=1-"+empID+"-"+branchID+"-"+empNAME);
    else 
        window.location.assign("leavespage.jsp?buttonid=0-"+empID+"-"+branchID+"-"+empNAME);
}





function getEmployeeAndBranchData(){
	var selectedBranch = document.getElementById("branchname").value;
	
	var HTMLCode4 = getEmployeesData(selectedBranch);
	document.getElementById('employeesdata').innerHTML = HTMLCode4;	
} 



function getEmployeesData(selectedBranch){
	
        var HTMLCode4 = "";
	
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
	return HTMLCode4;
}

function getIdOnly(str){
    return str.substring(str.indexOf("-")+1, str.length);
}
	
function getNameOnly(str){
    return str.substring(0,str.indexOf("-"));
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
		<jsp:include page="MainMenu.jsp?tabid=3" />
		
        <!-- // #end mainNav -->
       
        <div id="containerHolder">
            <div id="container">
        	<div id="sidebar">
                   <ul class="sideNav">
			<li><a href="leavespage.jsp?buttonid=0-0-0-0" <% if(buttonid == 0)  out.print("class='active'"); %> ><%= buttonNames[0]%></a></li>
			<li><a href="leavespage.jsp?buttonid=1-0-0-0" <% if(buttonid == 1)  out.print("class='active'"); %> ><%= buttonNames[1]%></a></li>
                                        
							 
                   </ul>
                </div>
				<h2><a href="#">Leaves Board</a> &raquo; <a href="#" class="active"</a>
					<% if(buttonid == 0)  
						out.print(buttonNames[0]);
                                            else if(buttonid == 1)
						out.print(buttonNames[1]); 
                                                  
					%>
				</h2>
				<div id="hiddenbranchname"></div>
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