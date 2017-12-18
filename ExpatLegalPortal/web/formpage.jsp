


<%@page import="ExpertLegalPortalClass.dbconnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="ExpertLegalPortalClass.Case"%>
<%@page import="ExpertLegalPortalClass.CaseSelectors"%>
<%@page import="ExpertLegalPortalClass.ExpertLegalPortalOperation"%>
<%@page import="ExpertLegalPortalClass.login"%>
<jsp:useBean id="UserItems" class="ExpertLegalPortalClass.UserItems" scope="session"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>MeTL - EXPERT LEGAL PORTAL</title>

		<meta name="description" content="Common form elements and layouts" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

                
                
                	<!-- Google web fonts -->
		<link href="http://fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700" rel='stylesheet' />

		<!-- The main CSS file -->
		
                
		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />
                 
                  
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
                <link rel="stylesheet" href="css2/style.css">
		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="assets/css/jquery-ui.custom.min.css" />
		<link rel="stylesheet" href="assets/css/chosen.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-datepicker3.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-timepicker.min.css" />
		<link rel="stylesheet" href="assets/css/daterangepicker.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-colorpicker.min.css" />
		<link rel="stylesheet" href="assets/css/fonts.googleapis.com.css" />
		<link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
		<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
		<script src="assets/js/ace-extra.min.js"></script>
                
                
                
                
                <meta name="description" content="Drag &amp; drop file upload with image preview" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="assets/css/dropzone.min.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="assets/css/fonts.googleapis.com.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
		<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
                
	</head>

	<body class="no-skin">
            
         <%
          Connection con=dbconnection.getConnection();    
        String userID=(String)session.getAttribute("username");
      if(userID==null)
        {
            //response.sendRedirect("index.jsp");
            
        } 
            int pageid = Integer.parseInt(request.getParameter("pageid"));
            String caseid = request.getParameter("caseid"); 
             
             
        %>
        
        <script>
             var pageID = <%= pageid%>; 
        </script>
        
     
        
		<div id="navbar" class="navbar navbar-default          ace-save-state">
			<div class="navbar-container ace-save-state" id="navbar-container">
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></sp					<span class="sr-only">Toggle sidebar</span>
an>

				</button>

				<div class="navbar-header pull-left">
					<a href="Home.jsp" class="navbar-brand">
						<small>
							<i class="fa fa-leaf"></i>
							EXPAT DISCIPLINARY PORTAL
                                                      
						</small>
					</a>
				</div>

			</div><!-- /.navbar-container -->
		</div>
                <div><div><div>
		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>

			<div id="sidebar" class="sidebar                  responsive                    ace-save-state">
				<script type="text/javascript">
					try{ace.settings.loadState('sidebar')}catch(e){}
				</script>

                                <ul class="nav nav-list">
         				<li class="">
						<a href="Home.jsp">
							<i class="menu-icon fa fa-tachometer"></i>
							<span class="menu-text"> Home </span>
						</a>
						<b class="arrow"></b>
					</li>
					<li class="active close">
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-pencil-square-o"></i>
							<span class="menu-text"> ADD </span>
							<b class="arrow fa fa-angle-down"></b>
						</a>
						<b class="arrow"></b>
						<ul class="submenu">
							<li class="active">
								<a href="formpage.jsp?pageid=1&caseid=null">
									<i class="menu-icon fa fa-caret-right"></i>
									1--A1(Not returned from leave)
								</a>
								<b class="arrow"></b>
							</li>
							<li class="">
								<a href="formpage.jsp?pageid=2&caseid=null">
									<i class="menu-icon fa fa-caret-right"></i>
									2--A2( With Company Property)
								</a>
								<b class="arrow"></b>
							</li>
					<li class="">
						<a href="formpage.jsp?pageid=3&caseid=null">
                                                    <i class="menu-icon fa fa-caret-right"></i>
							3--A3(Damage/Loss)
						</a>

						<b class="arrow"></b>
					</li>
                                    
					 <li class="">
						<a href="formpage.jsp?pageid=4&caseid=null">
							<i class="menu-icon fa fa-caret-right"></i>
							 4--Disciplinary
						</a>

						<b class="arrow"></b>
					</li>
					
					 <li class="">
						<a href="formpage.jsp?pageid=5&caseid=null">
							<i class="menu-icon fa fa-caret-right"></i>
				5--Salary stopped due to loss/performance
						</a>

						<b class="arrow"></b>
					</li>
                                            <li class="">
						<a href="formpage.jsp?pageid=6&caseid=null">
							<i class="menu-icon fa fa-caret-right"></i>
							6--Emp who passed away
						</a>

						<b class="arrow"></b>
					</li>
                                          <li class="">
						<a href="formpage.jsp?pageid=7&caseid=null">
							<i class="menu-icon fa fa-caret-right"></i>
							7--Native Staff Shortage
						</a>

						<b class="arrow"></b>
					</li>
                                        <li class="">
						<a href="formpage.jsp?pageid=8&caseid=null">
							<i class="menu-icon fa fa-caret-right"></i>
				8--Terminated Emp due to loss/shortage
						</a>

						<b class="arrow"></b>
					</li>
                                  
                                    
                                        <li class="">
						<a href="formpage.jsp?pageid=9&caseid=null">
							<i class="menu-icon fa fa-caret-right"></i>
						9--Leave Extension
						</a>

						<b class="arrow"></b>
					</li>
                                        </ul>
					</li>
                                        
                                        <li class="active close">
						
						<b class="arrow"></b>

						<ul class="submenu">
							<li class="active">
								<a href="A1.jsp">
									<i class="menu-icon fa fa-caret-right"></i>
									A1(Not returned from leave)
								</a>

								<b class="arrow"></b>
							</li>

							<li class="">
								<a href="A2.jsp">
									<i class="menu-icon fa fa-caret-right"></i>
									A2(With Company Property)
								</a>

								<b class="arrow"></b>
							</li>
					<li class="">
						<a href="A3.jsp">
                                                    <i class="menu-icon fa fa-caret-right"></i>
							A3:Damage/Loss
						</a>

						<b class="arrow"></b>
					</li>
                                    
					 <li class="">
						<a href="Dis.jsp">
							<i class="menu-icon fa fa-caret-right"></i>
							 Dis
						</a>

						<b class="arrow"></b>
					</li>
					
					 <li class="">
						<a href=" SalaryStopped.jsp">
							<i class="menu-icon fa fa-caret-right"></i>
				Salary stopped due to loss/performance
						</a>

						<b class="arrow"></b>
					</li>
                                        
                                        <li class="">
						<a href="TerminatedEmp.jsp">
							<i class="menu-icon fa fa-caret-right"></i>
				Terminated Emp due to loss/shortage
						</a>

						<b class="arrow"></b>
					</li>
                                    <li class="">
						<a href="NativeStaffShortage.jsp">
							<i class="menu-icon fa fa-caret-right"></i>
							Native Staff Shortage
						</a>

						<b class="arrow"></b>
					</li>
                                        <li class="">
						<a href="EmpPassedAway.jsp">
							<i class="menu-icon fa fa-caret-right"></i>
							Emp who passed away
						</a>

						<b class="arrow"></b>
					</li>
                                        <li class="">
						<a href="LeaveExtension.jsp">
							<i class="menu-icon fa fa-caret-right"></i>
						Leave Extension
						</a>

						<b class="arrow"></b>
					</li>
                                        </ul>
					</li>
                                        <li class="">
						<a href="ReportPage.jsp">
							<i class="menu-icon fa fa-list-alt"></i>
							<span class="menu-text"> REPORT </span>
						</a>

						<b class="arrow"></b>
					</li>
			</div>

			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
							<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="Home.jsp">Home</a>
							</li>

							
							<li class="active"><a href="logout.jsp">Logout</a></li>
						</ul><!-- /.breadcrumb -->

						<div class="nav-search" id="nav-search">
						
						</div><!-- /.nav-search -->
					</div>

					
				
                                               
                                                
                                                <% if(pageid == 1){ %>
                                                <%@include file="A1.jsp" %>
                                                <%                                             
                                                    }
                                                else if(pageid == 2){%>
                                                <%@include file="A2.jsp" %>
                                                <%
                                                }else if(pageid == 3){%>                                                
                                                <%@include file="A3.jsp" %>
                                                 <%                  
                                                }else if(pageid ==4){%>
                                                     <%@include file="Dis.jsp" %> 
                                                  <%
                                                    }
                                                  else if(pageid ==5){%>
                                                     <%@include file="SalaryStopped.jsp" %> 
                                                  <%
                                                  }
                                                  else if(pageid ==6){%>
                                                     <%@include file="EmpPassedAway.jsp" %> 
                                                  <%     }
                                                  else if(pageid ==7){%>
                                                     <%@include file="NativeStaffShortage.jsp" %> 
                                                  <%
                                                  }
                                                    else if(pageid ==8){%>
                                                     <%@include file="TerminatedEmp.jsp" %> 
                                                  <%
                                                }
                                         else if(pageid ==9){%>
                                                     <%@include file="LeaveExtension.jsp" %> 
                                                  <%
                                                  }
                                        %>
								</div><!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
				
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<div class="footer">
			
			</div>

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="assets/js/jquery-2.1.4.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
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
		
                
	</body>
</html>


        

			<div class="footer">
			
			</div>

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="assets/js/jquery-2.1.4.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
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
		
                
	</body>
</html>


        