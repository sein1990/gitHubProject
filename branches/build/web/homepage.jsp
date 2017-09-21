<%@ page import = "JavaCodePackage.MyDate" %>
<%@ page import = "JavaCodePackage.InternalMainClass" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>MeTL-Attendance</title>

<!-- CSS -->
<link href="style/css/transdmin.css" rel="stylesheet" type="text/css" media="screen" />
<link href="style/css/calendercss.css" rel="stylesheet" type="text/css" media="screen" />

<!--[if IE 6]><link rel="stylesheet" type="text/css" media="screen" href="style/css/ie6.css" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" type="text/css" media="screen" href="style/css/ie7.css" /><![endif]-->

<!-- JavaScripts-->
<script type="text/javascript" src="style/js/jquery.js"></script>
<script type="text/javascript" src="style/js/jNice.js"></script>
<script type="text/javascript" src="calenderbuilder.js"></script>
<script type="text/javascript" src="Classtrial.js"></script>
<script>

function onChangeYearsOrMonthsList() {
	var monthValue = document.getElementById("monthSelect").value;
	var yearValue = document.getElementById("yearSelect").value;
	
	if(branchValue != "-1"  && monthValue != "-1"){
		
	}
}

function directToDailyPage(date){
	
	
	var branchID = document.getElementById('branchSelect').value;
	var month = document.getElementById('monthSelect').value;
	var year = document.getElementById('yearSelect').value;
        if(branchID == 0)
           alert("Please Select the Branch First !");
	else 
            window.open("dailypage.jsp?buttonid="+0+"&date="+date+"&month="+month+"&year="+year+"&branchid="+branchID,'targetWindow','toolbar=no,location=no, status=no, menubar=no, scrollbars=yes,resizable=yes,width=SomeSize, height=SomeSize');
	
}

function buildCalender(){
	<% Integer AdminBranchID = (Integer) session.getAttribute("Permission");
	   InternalMainClass main = new InternalMainClass();
	   main.prepareBranchesData();
	   Integer BraOpenedDays=10;
	   if(AdminBranchID != 0)
            BraOpenedDays = main.getSpecificOpenedDays(AdminBranchID);
	%>
	
	Month = document.getElementById("monthSelect").value;
	Year = document.getElementById("yearSelect").value;
	var c = new calenderbuilder(Month,Year,<%= BraOpenedDays %>,3,<%= AdminBranchID %>);
	var HTMLCode = c.buildCalender();
	document.getElementById('caltable').innerHTML = HTMLCode;	
}

 </script>
 
 <script>
    window.onload = buildCalender;
</script>
</head>



<body>
	<div id="wrapper">
    	<!-- h1 tag stays for the logo, you can use the a tag for linking the index page -->
        <img src="METLLogo.jpg" style="width:150px;height:100px;">
        <%	if(request.getSession().getAttribute("Permission")==null){ 
				response.sendRedirect("index.jsp");
		}%>
		<div id="product" data-prodnumber="${sessionScope.pONumb}" />
		<input type="hidden" id="pONumb"  name="pONumb" value="ddd" />
		<!-- You can name the links with lowercase, they will be transformed to uppercase by CSS, we prefered to name them with uppercase to have the same effect with disabled stylesheet -->
        <jsp:include page="MainMenu.jsp?tabid=1" />
        <!-- // #end mainNav -->
       
        <div id="containerHolder">
            <div id="container">
        	<div id="sidebar">
                   <ul class="sideNav">
                    <li><a href="#" class="active">Main Board</a></li>
                    </ul>
                </div>
	         <h2><a href="#">Attendance Board</a> &raquo;	
			 <select id="branchSelect" name="branchSelect" class="jNiceSelectWrapperBra"> 
			
				<%  if(AdminBranchID == 0){
						String st2 = "<option value='0' > - All Branches - </option>";
						out.print(st2);	
						for (Object key : main.getBranchVec().keySet()) {
							st2 = "<option value='"+key.toString()+"' > "+main.getSpecificBranch((Integer) key)+" </option>";
							out.print(st2);
						}
				   
					}else{
						
						String BraName = main.getSpecificBranch(AdminBranchID);
						
						String st2 = "<option value='"+AdminBranchID+"' > "+BraName+" </option>";
						out.print(st2);
				    }
					main.prepareDirectoriesList(AdminBranchID);
				%>
			</select>
				 

				
			

			<%MyDate currentDate = new MyDate(); %>
				
                <select id="monthSelect" name="monthSelect" class="jNiceSelectWrapperBra" onchange="buildCalender()">
                    <option value="-1">-Please Select Month-</option>
                    <option value="0"  <% if(currentDate.getMonth()-1 == 0)  out.print("selected='selected'"); %> >January</option>
                    <option value="1"  <% if(currentDate.getMonth()-1 == 1)  out.print("selected='selected'"); %> >February</option>
                    <option value="2"  <% if(currentDate.getMonth()-1 == 2)  out.print("selected='selected'"); %> >March</option>
                    <option value="3"  <% if(currentDate.getMonth()-1 == 3)  out.print("selected='selected'"); %> >April</option>
                    <option value="4"  <% if(currentDate.getMonth()-1 == 4)  out.print("selected='selected'"); %> >May</option>
                    <option value="5"  <% if(currentDate.getMonth()-1 == 5)  out.print("selected='selected'"); %> >June</option>
                    <option value="6"  <% if(currentDate.getMonth()-1 == 6)  out.print("selected='selected'"); %> >July</option>
                    <option value="7"  <% if(currentDate.getMonth()-1 == 7)  out.print("selected='selected'"); %> >August</option>
                    <option value="8"  <% if(currentDate.getMonth()-1 == 8)  out.print("selected='selected'"); %> >September</option>
		    <option value="9"  <% if(currentDate.getMonth()-1 == 9)  out.print("selected='selected'"); %> >October</option>
		    <option value="10" <% if(currentDate.getMonth()-1 == 10) out.print("selected='selected'"); %> >November</option>
		    <option value="11" <% if(currentDate.getMonth()-1 == 11) out.print("selected='selected'"); %> >December</option>
		</select>		
		
                <select id="yearSelect" name="yearSelect" class="jNiceSelectWrapperBra" onchange="buildCalender()">
					
                    <% out.print("<option value='-1'>-Please Select Year-</option>"); %>
                    <%	int startYear = 2016;%>
                    <%	for(int i=startYear;i<startYear+5;i++){%>
                    <%		String statement = "<option value="+i;%>
                    <%		if(currentDate.getYear() == i ) {
                                    statement+=" selected='selected'>"+i+"</option>";}
				else{
                                    statement+=">"+i+"</option>";
				}%>
                    <%		out.print(statement);%>
                    <%	}%>
		</select>
		</h2>
                <div id="main">
				<table id="caltable">
        			  </tbody>
				</table>
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

	
	
	                    	
                                  
                                    
                                 
				  