<%-- 
    Document   : MasterServlet
    Created on : Apr 26, 2017, 10:57:09 AM
    Author     : user
--%>


<%@page import="JavaCodePackage.ConstantClass"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="PDFCreators.PDFCreatorReportConsalidate"%>
<%@page import="ReportItems.ConsalidateReportItem"%>
<%@page import="ReportItems.BranchesIntlizerItem"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileWriter"%>
<%@page import="PDFCreators.PDFCreatorReportPayroll"%>
<%@page import="ReportItems.PayrollVecItem"%>
<%@page import="java.util.Vector"%>
<%@page import="JavaCodePackage.MasterRoller"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        
        
    </head>
    <body onload="pageBuilder();">
        
       
        
      
	<div id="wrapper">
    	<!-- h1 tag stays for the logo, you can use the a tag for linking the index page -->
        <img src="METLLogo.jpg" style="width:150px;height:100px;">
        
        <!-- You can name the links with lowercase, they will be transformed to uppercase by CSS, we prefered to name them with uppercase to have the same effect with disabled stylesheet -->
        <%	if(request.getSession().getAttribute("Permission")==null){ 
				response.sendRedirect("index.jsp");
		}%>
		<jsp:include page="MainMenu.jsp?tabid=6" />
		
        <!-- // #end mainNav -->
       
        <div id="containerHolder">
            
            <div id="container">
        	
				
                <div id="hiddenbranchname">
                    
                    
                </div>
                <div id="main">
				 <%
            String prameter = request.getParameter("buttonid");
            String message = request.getParameter("message");
          
          
             
            String strButtonId = prameter.substring(0,prameter.indexOf("-"));
          
            
            
            int buttonid = (int) Integer.parseInt(strButtonId);
                          if(buttonid == 6){
//               String dir = "C:\\Users\\USER\\Desktop\\graph";
//                            String filename= dir+"\\BranchesData.txt";
                String dir = "C:\\Users\\Administrator\\Documents";
                String filename= dir+"\\BranchesData.txt";
                              
                BufferedReader in = new BufferedReader(new FileReader(filename)); 
                String line;
                while((line = in.readLine()) != null)
                    {
                    String[] arrayStr = line.split("@");
              
                            
                        out.print("<p>"+arrayStr[0]+" - "+arrayStr[1]+" - "+
                            arrayStr[2]+" - "+arrayStr[3]+" - "+arrayStr[4]+" - "+arrayStr[5]+"</p>");
                           
                }
           
            }else{
                prameter = prameter.substring(prameter.indexOf("-")+1);
                String BranchName = prameter.substring(0,prameter.indexOf("-"));

                prameter = prameter.substring(prameter.indexOf("-")+1);
                String BranchId = prameter.substring(0,prameter.indexOf("-"));

                prameter = prameter.substring(prameter.indexOf("-")+1);
                String Month = prameter.substring(0,prameter.indexOf("-"));

                String Year = prameter.substring(prameter.indexOf("-")+1);

            //   out.println(buttonid+" "+BranchName+" "+Month+" "+Year);

                if(buttonid == 5){
                    prameter = Year;
                    Year = prameter.substring(0,prameter.indexOf("$"));

                    prameter = prameter.substring(prameter.indexOf("$")+1);
                    String fromDate = prameter.substring(0,prameter.indexOf("$"));
                    
                    prameter = prameter.substring(prameter.indexOf("$")+1);
                    String toDate = prameter.substring(0,prameter.indexOf("$"));
                    
                    String weeklyOff = prameter.substring(prameter.indexOf("$")+1);

                    MasterRoller intlizer = new MasterRoller(BranchName,BranchId,Month,Year,fromDate,toDate);
                    intlizer.initlizeMainFunction(weeklyOff);

                    try{
                                String dir = "C:\\Users\\Administrator\\Documents";
                                   String filename= dir+"\\BranchesData.txt";
                            String line = BranchId+"@"+BranchName +"@"+Month+"@"+Year+"@"+fromDate+"@"+toDate+"@"+weeklyOff;

                            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
                            fw.write(line+"\n");//appends the string to the file
                            fw.close();
                          %>
                          <a href="masterpage.jsp?buttonid=1&message="><h2>Initialization Done!!!!!Back Previous Page</h2></a>
                            <%
                        }
                        catch(IOException ioe)
                        {
                            out.println("IOException: " + ioe.getMessage());
                        }
               
                }

                else if(buttonid == 2 || buttonid == 1){
                String dir = "C:\\Users\\Administrator\\Documents";
                String filename= dir+"\\BranchesData.txt";
//                    String dir = "C:\\Users\\Administrator\\Documents";
//                String filename= dir+"\\BranchesData.txt";
                BufferedReader in = new BufferedReader(new FileReader(filename)); 
                String line;
                Vector<BranchesIntlizerItem> vec = new Vector<BranchesIntlizerItem>();
                while((line = in.readLine()) != null)
                    {
                    String[] arrayStr = line.split("@");
                    vec.add(new BranchesIntlizerItem(arrayStr[0],arrayStr[1],
                            arrayStr[2],arrayStr[3],arrayStr[4],arrayStr[5]) );
                    }
                    in.close();
                    String fromDate = "-1";
                    String toDate = "-1";

                    for(int i=vec.size()-1;i>=0;i--){

                        BranchesIntlizerItem item = vec.get(i);
                        if(item.isSame(BranchId, BranchName, Month, Year)){
                            fromDate = vec.get(i).getFromDate();
                            toDate = vec.get(i).getToDate();
                            break;
                        }

                    }
                    if(fromDate.equals("-1") || toDate.equals("-1")){
                      %>
                      <a href="masterpage.jsp?buttonid=0"><h2>Please enter the data first</h2></a>
                    <%
                    }
                    else{
                        MasterRoller processor = new MasterRoller(BranchName,BranchId,Month,Year,fromDate,toDate);
                        if(buttonid == 2){
                            Vector<PayrollVecItem> payrollData = processor.processPayroll();
                            PDFCreatorReportPayroll PDFCreatorReportPayrollObj = new PDFCreatorReportPayroll(payrollData);
                            PDFCreatorReportPayrollObj.generatePDF(BranchName, Month, Year);     

                            String fileName = PDFCreatorReportPayrollObj.getPdfName();              
                            String path = PDFCreatorReportPayrollObj.getPath();

                            BufferedInputStream buf = null;
                            ServletOutputStream myOut = null;

                            try {

                                myOut = response.getOutputStream();             //Getting mistake here
                                File myfile = new File(path + fileName);

                                //set response headers
                                response.setContentType("application/pdf");         //I want to download a PDF file

                                response.addHeader(
                                        "Content-Disposition", "inline; filename=" + fileName);

                                response.setContentLength((int) myfile.length());

                                FileInputStream input = new FileInputStream(myfile);
                                buf = new BufferedInputStream(input);
                                int readBytes = 0;

                                //read from the file; write to the ServletOutputStream
                                while ((readBytes = buf.read()) != -1) {
                                    myOut.write(readBytes);
                                }

                            } catch (IOException ioe) {

                                out.print(ioe.getMessage());

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
                        else if(buttonid == 1){
                            Vector<ConsalidateReportItem> consalidateData = processor.getConslidateDate();
                            PDFCreatorReportConsalidate PDFCreatorObj = new PDFCreatorReportConsalidate(consalidateData);
                            PDFCreatorObj.generatePDF(BranchName, Month, Year);     

                            String fileName = PDFCreatorObj.getPdfName();              
                            String path = PDFCreatorObj.getPath();

                            BufferedInputStream buf = null;
                            ServletOutputStream myOut = null;

                            try {

                                myOut = response.getOutputStream();             //Getting mistake here
                                File myfile = new File(path + fileName);

                                //set response headers
                                response.setContentType("application/pdf");         //I want to download a PDF file

                                response.addHeader(
                                        "Content-Disposition", "inline; filename=" + fileName);

                                response.setContentLength((int) myfile.length());

                                FileInputStream input = new FileInputStream(myfile);
                                buf = new BufferedInputStream(input);
                                int readBytes = 0;

                                //read from the file; write to the ServletOutputStream
                                while ((readBytes = buf.read()) != -1) {
                                    myOut.write(readBytes);
                                }

                            } catch (IOException ioe) {

                                out.print(ioe.getMessage());

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


                    }

                }   
                else if(buttonid == 3){
                String dir = "C:\\Users\\Administrator\\Documents";
                String filename= dir+"\\BranchesData.txt";
//                  String dir = "C:\\Users\\Administrator\\Documents";
//                String filename= dir+"\\BranchesData.txt";
                BufferedReader in = new BufferedReader(new FileReader(filename)); 
                String line;
                Vector<BranchesIntlizerItem> vec = new Vector<BranchesIntlizerItem>();
                while((line = in.readLine()) != null)
                    {
                    String[] arrayStr = line.split("@");
                    vec.add(new BranchesIntlizerItem(arrayStr[0],arrayStr[1],
                            arrayStr[2],arrayStr[3],arrayStr[4],arrayStr[5]) );
                    }
                    in.close();
                    String fromDate = "-1";
                    String toDate = "-1";

                    for(int i=vec.size()-1;i>=0;i--){

                        BranchesIntlizerItem item = vec.get(i);
                        if(item.isSame(BranchId, BranchName, Month, Year)){
                            fromDate = vec.get(i).getFromDate();
                            toDate = vec.get(i).getToDate();
                            break;
                        }

                    }
                    if(fromDate.equals("-1") || toDate.equals("-1"))
                    {
                      %>
                      <a href="masterpage.jsp?buttonid=0"><h2>Please enter the data first</h2></a>
                    <%
                    }else
                    {


                        MasterRoller processor = new MasterRoller(BranchName,BranchId,Month,Year,fromDate,toDate);
                        Vector<PayrollVecItem> payrollData = processor.processPayroll();
                        boolean val = false;
                        for(int i=0;i<payrollData.size();i++){
                            if(Integer.parseInt(payrollData.get(i).getNotyet()) != 0){
                                out.println("There are some missing Data,please cheak");
                                val = true;
                                break;
                            }
                        } 
                        if(!val)
                        processor.approvePayroll(payrollData); 
                        
                        
                        response.sendRedirect("masterpage.jsp?buttonid="+3+"-"+BranchName+"-"+Month+"-"+Year); 
                        
                           %>
                          <a href="masterpage.jsp?buttonid=3&message="><h2>Approve Attendance Done!!!!!Back Previous Page</h2></a>
                            <%
                        
                        
                        
                        
                            
                        
                    }
                }   
                else if(buttonid == 4){
                    MasterRoller processor = new MasterRoller(BranchName,BranchId,Month,Year);
                    Vector<PayrollVecItem> payrollData = processor.getPayrollData();
                    PDFCreatorReportPayroll PDFCreatorReportPayrollObj = new PDFCreatorReportPayroll(payrollData);
                    PDFCreatorReportPayrollObj.generatePDF(BranchName, Month, Year);


                    String fileName = PDFCreatorReportPayrollObj.getPdfName();              
                            String path = PDFCreatorReportPayrollObj.getPath();

                            BufferedInputStream buf = null;
                            ServletOutputStream myOut = null;

                            try {

                                myOut = response.getOutputStream();             //Getting mistake here
                                File myfile = new File(path + fileName);

                                //set response headers
                                response.setContentType("application/pdf");         //I want to download a PDF file

                                response.addHeader(
                                        "Content-Disposition", "inline; filename=" + fileName);

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
                               response.sendRedirect("masterpage.jsp?buttonid="+4+"-"+BranchName+"-"+Month+"-"+Year); 
                }
            }
        
        %>	
				
              
                </div>
                
                                <div class="clear">
                                    
                                    
                                    
                                    
                                    
                                </div>
			
               
            </div>
            
        </div>	
 </div>
    <!-- // #wrapper -->
	
	
	
	
</body>
        
</html>
