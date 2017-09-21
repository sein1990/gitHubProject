<%@page import="java.util.Calendar"%>
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
var empId=0;
var branchId=0;
function pageBuilder(){     

    <%
        
        int masterVar =0; 
	int buttonid=0;
        String empid="0";
        String empname="0";
        String branchid="0";
        String[] buttonNames = {"Master Operation","Intlize Month"};

	if(request.getParameter("buttonid") != null){
            
            String prameter = request.getParameter("buttonid");
         
            buttonid = (int) Integer.parseInt(prameter);
            
        }
         
            %>     
          
          
         <%
               Integer AdminBranchID = (Integer) session.getAttribute("Permission");
               InternalMainClass main = new InternalMainClass();
               main.prepareBranchesData();
         


        %>     
              
        buttonId="<%=buttonid%>";      
        empId="<%=empid%>";
        branchId="<%=branchid%>";
        AdminBranchID="<%=AdminBranchID%>";
        if(buttonId == 0){
		buildMasterForm();
	  }else if(AdminBranchID == 0 && buttonId == 1){
            buildMonthSelForm();       
          }
        
        
}

function buildMonthSelForm(){
    
    <%
            masterVar =0;
            String HTMLCode  = "<center><br><br><br><br><br><form action='' method='post' class='jNice' id='contenttable'><fieldset id='formfield'>";
            HTMLCode = HTMLCode +"<p><label>Branch/Type :</label> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <input list='branches' id='branchname' name='branchname' required'></p>";
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
            
            HTMLCode = HTMLCode + "<center><p><select id='monthSelect' name='monthSelect' required class='jNiceSelectWrapperBra'>";
            HTMLCode = HTMLCode + "<option value='-1'>-Please Select Month-</option>";
            HTMLCode = HTMLCode + "<option value='1'>January</option>";
            HTMLCode = HTMLCode + "<option value='2'>February</option>";
            HTMLCode = HTMLCode + "<option value='3'>March</option>";
            HTMLCode = HTMLCode + "<option value='4'>April</option>";
            HTMLCode = HTMLCode + "<option value='5'>May</option>e";
            HTMLCode = HTMLCode + "<option value='6'>June</option>";
            HTMLCode = HTMLCode + "<option value='7'>July</option>";
            HTMLCode = HTMLCode + "<option value='8'>August</option>";
            HTMLCode = HTMLCode + "<option value='9'>September</option>";
            HTMLCode = HTMLCode + "<option value='10'>October</option>";
            HTMLCode = HTMLCode + "<option value='11'>November</option>";
            HTMLCode = HTMLCode + "<option value='12'>December</option>";
            HTMLCode = HTMLCode + " </select></p>";
	
            int year = Calendar.getInstance().get(Calendar.YEAR);
            HTMLCode = HTMLCode + "<p><select id='yearSelect' name='yearSelect' required class='jNiceSelectWrapperBra'>";
            HTMLCode = HTMLCode + "<option value='-1'>-Please Select Year-</option>";
		for(int y=2015;y<=year;y++)
			HTMLCode = HTMLCode + "<option value='"+y+"'>"+y+"</option>";
		HTMLCode = HTMLCode + " </select></p></center>";
            HTMLCode = HTMLCode + "<center><p><input id='fromDate' name='fromDate' type='date'></p>";
            HTMLCode = HTMLCode + "<p><input id='toDate' name='toDate' type='date'></p>";
            HTMLCode = HTMLCode + "<p><select id='weeklyoffselect' name='weeklyoffselect' required class='jNiceSelectWrapperBra'>";
            HTMLCode = HTMLCode + "<option value='-1'>-Please Select Weekly Off Day-</option>";
            HTMLCode = HTMLCode + "<option value='2'>Monday</option>";
            HTMLCode = HTMLCode + "<option value='3'>Tuesday</option>";
            HTMLCode = HTMLCode + "<option value='4'>Wendsday</option>";
            HTMLCode = HTMLCode + "<option value='5'>Thursday</option>";
            HTMLCode = HTMLCode + "<option value='6'>Friday</option>";
            HTMLCode = HTMLCode + "<option value='7'>Saturday</option>";
            HTMLCode = HTMLCode + "<option value='1'>Sunday</option>";
            HTMLCode = HTMLCode + " </select></p></center>";
            HTMLCode = HTMLCode +"<input type='button' class='processbutton' onclick='onClickButton(5);' value='Initlize'/></form>";
            HTMLCode = HTMLCode +"<input type='button' class='processbutton' onclick='onClickButton(6);' value='Get Intlization Report'/></form>";        



            %>          
    var HTMLCode  = "<%=HTMLCode%>";
    document.getElementById('main').innerHTML = HTMLCode;	
}

function buildMasterForm(){
	
	<% 
            masterVar =0;
            HTMLCode  = "<center><br><br><br><br><br><form action='' method='post' class='jNice' id='contenttable'><fieldset id='formfield'>";
            HTMLCode = HTMLCode +"<p><label>Branch/Type :</label> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <input list='branches' id='branchname' name='branchname' required'></p>";
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
            
            HTMLCode = HTMLCode + "<center><p><select id='monthSelect' name='monthSelect' required class='jNiceSelectWrapperBra'>";
            HTMLCode = HTMLCode + "<option value='-1'>-Please Select Month-</option>";
            HTMLCode = HTMLCode + "<option value='1'>January</option>";
            HTMLCode = HTMLCode + "<option value='2'>February</option>";
            HTMLCode = HTMLCode + "<option value='3'>March</option>";
            HTMLCode = HTMLCode + "<option value='4'>April</option>";
            HTMLCode = HTMLCode + "<option value='5'>May</option>e";
            HTMLCode = HTMLCode + "<option value='6'>June</option>";
            HTMLCode = HTMLCode + "<option value='7'>July</option>";
            HTMLCode = HTMLCode + "<option value='8'>August</option>";
            HTMLCode = HTMLCode + "<option value='9'>September</option>";
            HTMLCode = HTMLCode + "<option value='10'>October</option>";
            HTMLCode = HTMLCode + "<option value='11'>November</option>";
            HTMLCode = HTMLCode + "<option value='12'>December</option>";
            HTMLCode = HTMLCode + " </select></p>";
	
            year = Calendar.getInstance().get(Calendar.YEAR);
            HTMLCode = HTMLCode + "<p><select id='yearSelect' name='yearSelect' required class='jNiceSelectWrapperBra'>";
            HTMLCode = HTMLCode + "<option value='-1'>-Please Select Year-</option>";
		for(int y=2015;y<=year;y++)
			HTMLCode = HTMLCode + "<option value='"+y+"'>"+y+"</option>";
		HTMLCode = HTMLCode + " </select></p></center>";
           if(AdminBranchID == 0){
               HTMLCode = HTMLCode +"<p><input type='button' class='processbutton' onclick='onClickButton(2);' value='Process Attendance'/></p>";     
               HTMLCode = HTMLCode +"<p><input type='button' class='processbutton' onclick='onClickButton(3);' value='Approve Attendance'/></p>";
              HTMLCode = HTMLCode +"<p><input type='button' class='processbutton' onclick='onClickButton(4);' value='Print Approved Attendance Sheet'/></p></form>";
         
           }
           HTMLCode = HTMLCode +"<p><input type='button' class='processbutton' onclick='onClickButton(1);' value='Print Consolidate Sheet'/></p></form>";


        %>
	
	var HTMLCode  = "<%=HTMLCode%>";
	document.getElementById('main').innerHTML = HTMLCode;	
		
}


function onClickButton(id){
    
   if(id == 6){
     window.location.assign("MasterServlet.jsp?buttonid="+id+"-"); 
   } 
   else{ 
    
        var BranchName= document.getElementById('branchname').value;
        var Month= document.getElementById('monthSelect').value;
        var Year= document.getElementById('yearSelect').value;

        if(id != 5){
                window.location.assign("MasterServlet.jsp?buttonid="+id+"-"+BranchName+"-"+Month+"-"+Year); 
                
                
                

        }else{
                var FromDate= document.getElementById('fromDate').value;
                var ToDate= document.getElementById('toDate').value;
                var weeklyOff = document.getElementById('weeklyoffselect').value;
                window.location.assign("MasterServlet.jsp?buttonid="+id+"-"+BranchName+"-"+Month+"-"+Year+"$"+FromDate+"$"+ToDate+"$"+weeklyOff); 
                alert(message);
               
        }
                    
                                
               
   }
    //    
        
        
 //  }
}






 </script>
</head>

<body onload="pageBuilder();">
  
	<div id="wrapper">
              <img src="METLLogo.jpg" style="width:150px;height:100px;"></a>
    	<!-- h1 tag stays for the logo, you can use the a tag for linking the index page -->
    	
        
        <!-- You can name the links with lowercase, they will be transformed to uppercase by CSS, we prefered to name them with uppercase to have the same effect with disabled stylesheet -->
        <%	if(request.getSession().getAttribute("Permission")==null){ 
				response.sendRedirect("index.jsp");
		}%>
		<jsp:include page="MainMenu.jsp?tabid=6" />
		
        <!-- // #end mainNav -->
       
        <div id="containerHolder">
            <div id="container">
        	<div id="sidebar">
                   <ul class="sideNav">
			           
			<li><a href="masterpage.jsp?buttonid=0" <% if(buttonid == 0)  out.print("class='active'"); %> ><%= buttonNames[0]%></a></li>
			<li><a href="masterpage.jsp?buttonid=1" <% if(buttonid == 1)  out.print("class='active'"); %> ><%= buttonNames[1]%></a></li>
									 
                   </ul>
                </div>
				<h2><a href="#">Master Board</a> &raquo; <a href="#" class="active"</a>
					<% if(buttonid == 0)  
                                            out.print(buttonNames[0]);
                                            else if(buttonid == 1)
                                            out.print(buttonNames[1]); 
					%>
				</h2>
				<div id="hiddenbranchname"></div>
                <div id="main"></div>
                
                <div class="clear"></div>
			
               
            </div>
        </div>	
 </div>
    <!-- // #wrapper -->
</body>
</html>