
<%@page import="ExpertLegalPortalClass.OracleDbConnection"%>
<%@page import="ExpertLegalPortalClass.QueryClass"%>
<%@page import="java.io.File"%>
<%@page import="ExpertLegalPortalClass.Attachment"%>
<%@page import="java.util.Vector"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="ExpertLegalPortalClass.dbconnection"%>
<%@page import="java.sql.Connection"%>


<% 
 QueryClass objQuery=new QueryClass();  
   String caseid1 = request.getParameter("caseid"); 
            String dateS="";
            String reasonS="";
            String nameS="";
            String unityS=""; 
            String remarksS=""; 
            String recoveredS="";
            String salaryDepositS="";
            String totalAmountS="";
            String fileClosedS="";
            String empIDS="";
 Connection con1=dbconnection.getConnection();
 String dbID="";
    PreparedStatement ps=null;
    ResultSet rs=null;
    String IDquery=objQuery.a2(caseid1);
    ps=con1.prepareStatement(IDquery);
    rs=ps.executeQuery();
  
    if(rs.next())
    {	
        dbID=rs.getString(1);
        dateS=rs.getString(2);
        reasonS=rs.getString(3);
        nameS=rs.getString(4);
        unityS=rs.getString(5);
        remarksS=rs.getString(6);
        recoveredS=rs.getString(7);
        salaryDepositS=rs.getString(8);
        totalAmountS=rs.getString(9);
        fileClosedS=rs.getString(10);
        empIDS=rs.getString(11);      
    }
    IDquery=objQuery.a2Attachment(caseid1);
    ps=con1.prepareStatement(IDquery);
     rs=ps.executeQuery(); 
     Vector<Attachment> attachVector =new Vector();while(rs.next())
     attachVector.add(new Attachment(rs.getString(2), "1", caseid1, rs.getString(1), rs.getString(3)));

%>	
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

<link rel="stylesheet" href="css2/style.css">
<div class="page-header">
<h1>
        A2
        <small>
                <i class="ace-icon fa fa-angle-double-right"></i>
                With Company Property
        </small>
</h1>
</div><!-- /.page-header -->

<div class="row">
<div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <% //formpage.jsp?pageid=1&caseid="<%=caseid1 %>
        <form class="form-horizontal"  action="A2Servlet" method="post" enctype="multipart/form-data" >
                <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1" > Case ID</label>

                        <div class="col-sm-9">
                            <input type="text" name="caseidupdate"  class="form-control" required="required" value="<%=caseid1%>" readonly/>
                        </div>
                </div>
                    <div class="form-group">
                    
                    
                    <%
                    if(caseid1.contains("null")){
                    %>
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-1" > Date</label>
                        <div class="col-sm-9">
                            <input type="date" name="date" class="form-control" required="required" value="<%=dateS%>"/>
                        </div>
                        <%
                        }else{
                        %>
                          <label class="col-sm-3 control-label no-padding-right" for="form-field-1" > Date</label>
                            <div class="col-sm-9">
                            <input type="text" name="date"  class="form-control" required="required" value="<%=dateS%>" readonly/>
                        </div>
                        <%
                        }   
                        %>
                </div>
                                          <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1">Employee ID and Name</label>

                <div class="col-sm-9">
                    <%
                    if(caseid1.contains("null")){
                    %>
                        <input list="empid" class="form-control" name="empID"  required="">
                        <datalist id="empid">
                            <%       
                               Connection OracleConnection=OracleDbConnection.OracleGetConnection();
                               String query=objQuery.getEmpData();
                               PreparedStatement ps2=OracleConnection.prepareStatement(query);
                               String ID="";

                               ResultSet rs2=ps2.executeQuery();

                               while(rs2.next())
                                {		
                                  ID=rs2.getString(3)+ "- "+rs2.getString(1);
                                  out.println("<option>"+ID+"</option>");
                              
                                }
                             %>
                        </datalist>
                        <%
                        }else{
                        %>
                            <input  value="<%=nameS+"-"+empIDS%>" class="form-control" name="empID" readonly/> 
                        <%
                        }   
                        %>	


                </div>
                </div>  
                <div class="form-group">
                <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="food">Unit</label>
                <div class="col-xs-12 col-sm-9">
                <%
                
                if(caseid1.contains("null")){
                    %>
                    <input list="unit"  name="unity" class="form-control">
                    <datalist id="unit">
                    
                    
                    <%
                    Connection OracleConnection=OracleDbConnection.OracleGetConnection();
                    String query=objQuery.getUnits();
                    PreparedStatement ps2=OracleConnection.prepareStatement(query);
                    String unit="";

                    ResultSet rs2=ps2.executeQuery();
                    while(rs2.next()){		
                        unit=rs2.getString(1);
                        out.println("<option>"+unit+"</option>");
                    }
                    %>
                    </datalist>
                    <%
                }else{
                    %>
                    <input type="text"  value="<%=unityS%>" class="form-control" name="unity" readonly/> 
                   <%
                }
                %>        
                    </div>
                </div>
                    
                         <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1" > Reason </label>

                        <div class="col-sm-9">
                                <input type="text"  value="<%=reasonS%>" class="form-control" name="reason" required/>
                        </div>
                </div>
                         
                         
                <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> Remarks </label>

                        <div class="col-sm-9">
                                <input type="text" name="remarks" value="<%=remarksS%>" class="form-control"  required/>
                        </div>
                </div>
                        
                        
                          <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> Currency Type</label>

                      

                     <div class="form-group">
                 
                    <%
                
                          if(caseid1.contains("null")){
                    %>
                      <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> 
                          <input type="radio" name="currencyType" value="-USD"/> USD    
                        <input type="radio" name="currencyType" value="-Tshs" />Tshs 
                        
                    </label>
                           
                    </div>
                         <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> Amount to be recovered(A part from salary holdings) </label>

                            <div class="col-sm-9">
                                    <input type="text" name="recovered"  value="<%=recoveredS%>"class="form-control" onkeypress="return isNumberKey(event)" />
                            </div>
                            </div>
                            <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> Salary deposit </label>

                        <div class="col-sm-9">
                                <input type="text" name="salaryDeposit" value="<%=salaryDepositS%>" class="form-control" onkeypress="return isNumberKey(event)" />
                        </div>
                      
                    </div>
                    
                             <%
                } else{
                    %> 
                      <div class="form-group">
                               <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> 
                               <%
                                    String string = totalAmountS;
                                    String[] parts = string.split("-");
                                    String part2CurrencyType  = parts[1]; 
                               %>
                                 <input type="text" name="currencyType" value="<%="-"+part2CurrencyType%>" class="form-control"  readonly></label>
                          </div>
                
                       
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> Amount to be recovered(A part from salary holdings) </label>

                            <div class="col-sm-9">
                                    <input type="text" name="recovered"  value="<%=recoveredS%>"class="form-control" onkeypress="return isNumberKey(event)" />
                            </div>
                            </div>
                            <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> Salary deposit </label>

                        <div class="col-sm-9">
                                <input type="text" name="salaryDeposit"  value="<%=salaryDepositS%>" class="form-control" onkeypress="return isNumberKey(event)" />
                        </div>
                      
                    </div>
                    <%
                }
                    %>
                  
                        
                        <%
                         if(!caseid1.contains("null")){
                        %>
                       
         
                        <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> Total Amount </label>

                        <div class="col-sm-9">
                                <input type="text" name="totalAmount" value="<%=totalAmountS%>" class="form-control"  readonly/>
                        </div>
                        </div>
                        <%}%>
                                <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1">Filed & Closed </label>

                        <div class="col-sm-9">

                        <select name="fileClosed"   class="form-control">
                             <option><%=fileClosedS%></option>
                            <option>YES</option>
                            <option>NO</option>

                        </select>
                                </div>
                        </div>                                                 
                          <script type="text/javascript">
                            function isNumberKey(evt)
                            {
                                      var charCode = (evt.which) ? evt.which : event.keyCode;
                                      if (charCode != 46 && charCode > 31 
                                        && (charCode < 48 || charCode > 57))
                                         return false;

                                      return true;
                            }
                            </script>
                 
                              <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> </label>

                        <div class="col-sm-9">
                                 <div class="widget-body">
                    <div class="widget-main">


                            <div class="form-group">
                                    <div class="col-xs-12">
                                           
                                                                <p>
        <input type="file" name="fileOne"  id="fileOne" onchange="FileDetails()"/>
    </p>
    
    <!--SHOW RESULT HERE-->
    <p id="fp"></p>

 
    <script>
    function FileDetails()
    {
        // GET THE FILE INPUT.
        var fi = document.getElementById('fileOne');
        // VALIDATE OR CHECK IF ANY FILE IS SELECTED.
        if (fi.files.length > 0) {
            // THE TOTAL FILE COUNT.
//            document.getElementById('fp').innerHTML =
//                'Total Files: <b>' + fi.files.length + '</b></br >';
            // RUN A LOOP TO CHECK EACH SELECTED FILE.
            for (var i = 0; i <= fi.files.length - 1; i++) {
                var fname = fi.files.item(i).name;      // THE NAME OF THE FILE.
         //       var fsize = fi.files.item(i).size;      // THE SIZE OF THE FILE.
                // SHOW THE EXTRACTED DETAILS OF THE FILE.
                document.getElementById('fp').innerHTML =
                document.getElementById('fp').innerHTML + '<br/>' +
                '<input type="text" name="fileRemarks" class="form-control" required/><p>'+ '<b>'+fname+'</p></b></br>';
            }
//            var fileSize = parseInt(fi.files.length);
//            var arrySize=0;
//            arrySize=parseInt("<%=attachVector.size()%>");
//            var totalSize=fileSize+arrySize;
//        document.getElementById('fp').innerHTML = document.getElementById('fp').innerHTML + '<input type="text" name="numberOfFiles" value="'+fileSize+'">';
        }
        else { 
            alert('Please select a file.');
        }
    }
</script>
                                    </div>
                            </div>

                    </div>
            </div>
                        </div>
                        </div>
                       
                                  <div class="col-md-offset-3 col-md-9">
                                <button class="btn btn-info" type="submit" name="sub">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        Submit
                                </button>

                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="reset">
                                        <i class="ace-icon fa fa-undo bigger-110"></i>
                                        Reset
                                </button>
                                </form>
</div>



                        </div>
   </div>
<p><p>
                     
                                 <%                        
                            for(int i=0;i<attachVector.size();i++){                               
                                               
                                out.print("<div class='alert alert-info'>");
                                out.print("<i class='ace-icon fa fa-hand-o-right'></i>");
                                out.print("<a href='a1FileOpen?param="+attachVector.get(i).getPath()+"'>"); 
                                out.print("'"+attachVector.get(i).getRemarks()+"'</a>");
                                out.print("<form action='deleteattachmentservlet' method=post><input type='hidden' name='deleteattachmentid' id='deleteattachmentid' value='"+attachVector.get(i).getId()+"'>");
                                out.print("<input type='hidden' name='deleteattachmentdbid' id='deleteattachmentdbid' value='"+attachVector.get(i).getCaseId()+"'>");
                                out.print("<input type='hidden' name='deleteattachmentpageid' id='deleteattachmentpageid' value='2'>");   
                                out.print("<button type='submit' name='deleteatta'  class='close'> <i class='ace-icon fa fa-times'></i></button></form></div>");
                                   // onclick='onCloseFunction("+attachVector.get(i).getId()+")'
                            }
                           
                          
                        %>   
              <script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="assets/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="assets/js/excanvas.min.js"></script>
		<![endif]-->
		<script src="assets/js/jquery-ui.custom.min.js"></script>
		<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="assets/js/chosen.jquery.min.js"></script>
		<script src="assets/js/spinbox.min.js"></script>
		<script src="assets/js/bootstrap-datepicker.min.js"></script>
		<script src="assets/js/bootstrap-timepicker.min.js"></script>
		<script src="assets/js/moment.min.js"></script>
		<script src="assets/js/daterangepicker.min.js"></script>
		<script src="assets/js/bootstrap-datetimepicker.min.js"></script>
		<script src="assets/js/bootstrap-colorpicker.min.js"></script>
		<script src="assets/js/jquery.knob.min.js"></script>
		<script src="assets/js/autosize.min.js"></script>
		<script src="assets/js/jquery.inputlimiter.min.js"></script>
		<script src="assets/js/jquery.maskedinput.min.js"></script>
		<script src="assets/js/bootstrap-tag.min.js"></script>

		<!-- ace scripts -->
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
		
                