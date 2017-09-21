<%@ page import = "JavaCodePackage.MyDate" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Transdmin Light</title>

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
	
	alert("fff");
	if(branchValue != "-1"  && monthValue != "-1"){
		
	}
}

function dis(x){
	
	window.open("dailypage.jsp",'targetWindow','toolbar=no,location=no, status=no, menubar=no, scrollbars=yes,resizable=yes,width=SomeSize, height=SomeSize');
	
}

function buildCalender(){
	Month = document.getElementById("monthSelect").value;
	Year = document.getElementById("yearSelect").value;
	var c = new calenderbuilder(Month,Year);
	var HTMLCode = c.buildCalender();
	document.getElementById('caltable').innerHTML = HTMLCode;	
}

 </script>
</head>


									

<body onload="buildCalender();">
	<div id="wrapper">
    	<!-- h1 tag stays for the logo, you can use the a tag for linking the index page -->
    	<h1><a href="#"><span>Transdmin Light</span></a></h1>
        
        <!-- You can name the links with lowercase, they will be transformed to uppercase by CSS, we prefered to name them with uppercase to have the same effect with disabled stylesheet -->
        <jsp:include page="MainMenu.jsp" />
        <!-- // #end mainNav -->
       
        <div id="containerHolder">
            <div id="container">
        	<div id="sidebar">
                   <ul class="sideNav">
                   <li><a href="#" class="active">Main Board</a></li>
                   <li><a href="#">Contact US</a></li>
                    </ul>
                </div>
	         <h2><a href="#">Attendance Board</a> &raquo;
                    <select id="branchSelect" name="branchSelect" class="jNiceSelectWrapperBra">
			<option value="-1">-Please Select Branch-</option>
			<option value="mwanza">Mwanza</option>
			<option value="arusha">Arusha</option>
			<option value="fullday">Mwanza</option>
			<option value="halfday">Mwanza</option>
                    </select>
                
		<%  MyDate currentDate = new MyDate(); %>
				
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
        
        <p id="footer">Feel free to use and customize it. <a href="http://www.perspectived.com">Credit is appreciated.</a></p>
    </div>
    <!-- // #wrapper -->
	
	
	
	
</body>
</html>

	
	
	                    	
                                  
                                    
                                 
				  