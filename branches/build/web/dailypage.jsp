<%@page import="java.time.Month"%>
<%@page import="java.lang.String"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="PDFCreators.PDFCreatorSheet"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Vector"%>
<%@page import="JavaCodePackage.ConstantClass"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="JavaCodePackage.conectionClass"%>
<%@page import="JavaCodePackage.QuerisClass"%>
<%@ page import = "JavaCodePackage.InternalMainClass" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>MeTL-Attendance</title>

<!-- CSS -->
<link href="style/css/transdmin.css" rel="stylesheet" type="text/css" media="screen" />
<!--[if IE 6]><link rel="stylesheet" type="text/css" media="screen" href="style/css/ie6.css" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" type="text/css" media="screen" href="style/css/ie7.css" /><![endif]-->

<!-- JavaScripts-->
<script type="text/javascript" src="style/js/jquery.js"></script>
<script type="text/javascript" src="style/js/jNice.js"></script>
<script>

function typeFunction() {
		
	var x = document.getElementById("typeSelect").value;
	if(x == "leave"){
		
		var element = document.createElement("input");

	//Assign different attributes to the element.
		element.setAttribute("type", "file");
		element.setAttribute("name", "leavefile");
		element.setAttribute("id", "leavefile");
		element.setAttribute("value", "leavefile");
		element.setAttribute("class", "custom-file-input");

       document.getElementById("emprow").appendChild(element);
		alert("blabla");
	
	}
	else{
		var myElem = document.getElementById("leavefile");
		if (myElem != null) 
			myElem.style.visibility = "hidden";
		
	}
}
	
 </script>
<script>

var buttonid = "0";
var date = 0;
var month = 0;
var year = 0;
var branchid = 0;

function buildPage(){
    <%
	String buttonid="0";
	if(request.getParameter("buttonid") != null)
            buttonid = request.getParameter("buttonid");
        int mydate = 0;
        if(request.getParameter("date") != null)
             mydate = Integer.parseInt(request.getParameter("date") );
		
        int month = 0;
        if(request.getParameter("month") != null)
             month = Integer.parseInt(request.getParameter("month") );
        int year = 0;
        if(request.getParameter("year") != null)
            year = Integer.parseInt(request.getParameter("year") );
			
         int branchid = 0;
            if(request.getParameter("branchid") != null)
		branchid = Integer.parseInt(request.getParameter("branchid") );
			
	%>
	buttonid="<%=buttonid%>";
	date="<%=mydate%>";
	month="<%=month%>";
	year="<%=year%>";
	branchid="<%=branchid%>";
        <% if(branchid == 0 && buttonid.equals("1")){
		out.print("alert('Please Choose the Branch First');");
	}
	else if(buttonid.equals("1")){
		month ++;
		String s = mydate+"\\"+month+"\\"+year;
		PDFCreatorSheet pdfCreator = new PDFCreatorSheet(branchid,mydate,month,year,(String)session.getAttribute("Username"));
		pdfCreator.generateFiles();
                String fileName = pdfCreator.getPdfName();              
                String path = pdfCreator.getPath();        
                BufferedInputStream buf = null;
                ServletOutputStream myOut = null;
                
                try {

                    myOut = response.getOutputStream();             
                    File myfile = new File(path + fileName);
                    //set response headers
                    response.setContentType("application/pdf");         //I want to download a PDF file
                    response.addHeader(
                            "Content-Disposition", "attachment; filename=" + fileName);

                    response.setContentLength((int) myfile.length());

                    FileInputStream input = new FileInputStream(myfile);
                    buf = new BufferedInputStream(input);
                    int readBytes = 0;

                    //read from the file; write to the ServletOutputStream
                    while ((readBytes = buf.read()) != -1) {
                        myOut.write(readBytes);
                    }

                } catch (IOException ioe) {
                } finally {

                    //close the input/output streams
                    if (myOut != null) {
                        myOut.close();
                    }
                    if (buf != null) {
                        buf.close();
                    }

                } 
              
                
	}
	
	
        %>
     
        buildEmployeeTable(buttonid);	
				
}
        
function buildEmployeeTable(){
    
    <%
    Vector<String> idsVector = new Vector<String>(); 
    Vector<String> namesVector = new Vector<String>();
       
    QuerisClass q =  new QuerisClass();
    String query = q.selectAllEmployeeForBranch(branchid);
    conectionClass c = new conectionClass();
    ResultSet res1 = c.excuteQuery(query);
    while(res1.next()) {
        idsVector.add(res1.getString(1));
        namesVector.add(res1.getString(2));
    }

    Map empHas = new HashMap<String,String>();
    QuerisClass q1 =  new QuerisClass();
    String query1 = q1.selectAllEmployeeForBranchWithStatusForDate(branchid,Integer.toString(mydate),month,Integer.toString(year));
    
    conectionClass c1 = new conectionClass();
    ResultSet res2 = c1.excuteQuery(query1);
    while(res2.next()) {
          String empId = res2.getString(1);
          String status = res2.getString(2);
         
          empHas.put(empId, status);
    }
    
   
   // Iterator it = empHas.entrySet().iterator();
   // while (it.hasNext()) {
     //   Map.Entry pair = (Map.Entry)it.next();
       
       // it.remove(); // avoids a ConcurrentModificationException
   // }*/
    String HTMLCode = "<p>"+namesVector.size()+"</p>";
    int evenValue=0;
    for(int i=0;i<idsVector.size();i++){
        String status ="null";
        if(empHas.containsKey(idsVector.get(i)))
           status = (String) empHas.get(idsVector.get(i));
                      
           if(evenValue % 2 == 0){
                HTMLCode = HTMLCode +"<tr>";
           }else{
                 HTMLCode = HTMLCode +"<tr class='odd'>";
           }
           HTMLCode = HTMLCode +"<td>"+namesVector.get(i) +"-"+idsVector.get(i)+"</td><td><select id='statusvalue'  name='statusvalue'>";
           HTMLCode = HTMLCode +"<option value=-3>Choose Status Type</option>";
      
           
           if(status.equals(ConstantClass.AbsentCode))
                HTMLCode = HTMLCode +"<option selected='selected' value=-1>Absent</option>";
           else 
                HTMLCode = HTMLCode +"<option value=-1>Absent</option>";
           
           if(status.equals(ConstantClass.PresentCode))
                 HTMLCode = HTMLCode +"<option selected='selected' value=00>Full Day</option>";
           else 
                 HTMLCode = HTMLCode +"<option value=00>Full Day</option>";
           
           if(status.equals(ConstantClass.HalfDayCode))
                HTMLCode = HTMLCode +"<option selected='selected' value=-2>Half Day</option>";
           else 
                HTMLCode = HTMLCode +"<option value=-2>Half Day</option>";
            if(status.equals(ConstantClass.WeeklyOffCode))
                HTMLCode = HTMLCode +"<option selected='selected' value=06>Weekly Off</option>";
           else 
                HTMLCode = HTMLCode +"<option value=06>Weekly Off</option>";
             if(status.equals(ConstantClass.NotJoinedYetCode))
                HTMLCode = HTMLCode +"<option selected='selected' value=07>Not Joined Yet</option>";
           else 
                HTMLCode = HTMLCode +"<option value=07>Not Joined Yet</option>";
           
           HTMLCode = HTMLCode +"</select></td></tr>";        
           HTMLCode = HTMLCode +"<input type='hidden' id='empid'  name='empid' value='"+idsVector.get(i)+"' />";
           HTMLCode = HTMLCode +"<input type='hidden' id='branchid'  name='branchid' value='"+branchid+"' />";
           HTMLCode = HTMLCode +"<input type='hidden' id='mydate'  name='mydate' value='"+mydate+"' />";
           HTMLCode = HTMLCode +"<input type='hidden' id='month'  name='month' value='"+month+"' />";
           HTMLCode = HTMLCode +"<input type='hidden' id='year'  name='year' value='"+year+"' />";
           evenValue++;
        
        
                           
                            
                         
               

       //    String status = res1.getString(4); 
         //  String compId = res1.getString(1);
     //      String empId = res1.getString(2); 
     //      String logDate = res1.getString(3);
    //       String status = res1.getString(4); 
    //       String empName = res1.getString(5);
        
    }

    HTMLCode = HTMLCode + "</table> <input type='submit' class='processbutton' value='Update Data'/></form>";   
                                                             
                                  
    %>
    var HTMLCode  = "<%=HTMLCode%>";
    document.getElementById('employeetable').innerHTML = HTMLCode;	        
            
        
            
            
}        
</script>
</head>

<body onload="buildPage();">
	<div id="wrapper">
    	<!-- h1 tag stays for the logo, you can use the a tag for linking the index page -->
    	      <img src="METLLogo.jpg" style="width:150px;height:100px;"></a>
        <%	if(request.getSession().getAttribute("Permission")==null){ 
                    response.sendRedirect("index.jsp");
		}%>
        <!-- You can name the links with lowercase, they will be transformed to uppercase by CSS, we prefered to name them with uppercase to have the same effect with disabled stylesheet -->
		<jsp:include page="MainMenu.jsp" />
        <!-- // #end mainNav -->
        
        <div id="containerHolder">
            <div id="container">
                <div id="sidebar">
                 <ul class="sideNav">
                    <li><% out.print("<a href='dailypage.jsp?buttonid="+1+"&date="+mydate+"&month="+month+"&year="+year+"&branchid="+branchid+"'>");%>Print Document</a></li>
            	 </ul>
                </div>    
                <h2><a href="#">Dashboard</a> &raquo; <a href="#" class="active">Daily Page</a>&raquo;    <%=mydate+"-"+Month.of(month).name()+"-"+year%></h2>
		<div id="main">
                    <form action="UandSServlet" method="post" enctype="multipart/form-data">                      
                    <input type="hidden"  name="date" value="<%=mydate%>" >
                    <input type="hidden"  name="month" value="<%=month%>" >
                    <input type="hidden"  name="year" value="<%=year%>">
                    <input type="hidden"  name="branchid" value="<%=branchid%>" >                                    
                    <input type="file"  name="file" class="custom-file-input">
                    <span><input type="submit" name="send" value="process" class="processbutton"></span>
                    <br><br><br>
                    </form>
                           <form action="DetailsDataServlet" method="post">
                           <table cellpadding='0' cellspacing='0' id='employeetable'>
                </div>
                <div class="clear"></div>
            </div>
            
        </div>	 
    </div>
</body>
</html>


