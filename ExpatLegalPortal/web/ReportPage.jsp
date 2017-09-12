
<%@page import="org.jfree.chart.ChartUtilities"%>
<%@page import="java.io.File"%>
<%@page import="org.jfree.chart.entity.StandardEntityCollection"%>
<%@page import="org.jfree.chart.ChartRenderingInfo"%>
<%@page import="com.itextpdf.awt.DefaultFontMapper"%>
<%@page import="java.awt.geom.Rectangle2D"%>
<%@page import="java.awt.Graphics2D"%>
<%@page import="com.itextpdf.text.pdf.PdfTemplate"%>
<%@page import="com.itextpdf.text.pdf.PdfContentByte"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page import="com.itextpdf.text.Rectangle"%>
<%@page import="com.itextpdf.text.Document"%>
<%@page import="java.awt.Color"%>
<%@page import="org.jfree.chart.plot.PiePlot"%>
<%@page import="org.jfree.chart.JFreeChart"%>
<%@page import="org.jfree.chart.ChartFactory"%>
<%@page import="org.jfree.data.general.DefaultPieDataset"%>
<%@page import="com.mysql.jdbc.Util"%>
<%@page import="GraphAndPDFReport.EmbedPieChartPDF"%>
<%@page import="ExpertLegalPortalClass.dbconnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="ExpertLegalPortalClass.Case"%>
<%@page import="ExpertLegalPortalClass.CaseSelectors"%>
<%@page import="ExpertLegalPortalClass.ExpertLegalPortalOperation"%>
<%@page import="ExpertLegalPortalClass.login"%>
<%@page import="ExpertLegalPortalClass.UserItems"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>MeTL - EXPERT LEGAL PORTAL</title>

		<meta name="description" content="Common form elements and layouts" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="assets/css/jquery-ui.custom.min.css" />
		<link rel="stylesheet" href="assets/css/chosen.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-datepicker3.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-timepicker.min.css" />
		<link rel="stylesheet" href="assets/css/daterangepicker.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-colorpicker.min.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="assets/css/fonts.googleapis.com.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
		<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="assets/js/ace-extra.min.js"></script>

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="assets/js/html5shiv.min.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
                   <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="css/tablecss.css">
		<script type="text/javascript" src="js/searchabletable.js"></script>
		
                
                
	</head>
<body class="no-skin">
     
            
         <%
         Connection con=dbconnection.getConnection();    
        String userID=(String)session.getAttribute("username");
       if(userID==null)
        {
          //  response.sendRedirect("index.jsp");

        }
        %>
		<div id="navbar" class="navbar navbar-default          ace-save-state">
			<div class="navbar-container ace-save-state" id="navbar-container">
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></span>

				</button>

				<div class="navbar-header pull-left">
					<a href="Home.jsp" class="navbar-brand">
						<small>
							<i class="fa fa-leaf"></i>
							EXPERT LEGAL PORTAL
                                                      
						</small>
					</a>
				</div>

			</div><!-- /.navbar-container -->
		</div>

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
									A1(Not returned from leave)
								</a>

								<b class="arrow"></b>
							</li>

							<li class="">
								<a href="formpage.jsp?pageid=2&caseid=null">
									<i class="menu-icon fa fa-caret-right"></i>
									A2(Not returned from leave)
								</a>

								<b class="arrow"></b>
							</li>
                                                     

					<li class="">
						<a href="formpage.jsp?pageid=3&caseid=null">
                                                    <i class="menu-icon fa fa-caret-right"></i>
							A3(Not returned from leave)
						</a>

						<b class="arrow"></b>
					</li>
                                    
					 <li class="">
						<a href="formpage.jsp?pageid=4&caseid=null">
							<i class="menu-icon fa fa-caret-right"></i>
							 Dis
						</a>

						<b class="arrow"></b>
					</li>
					
					 <li class="">
						<a href="formpage.jsp?pageid=5&caseid=null">
							<i class="menu-icon fa fa-caret-right"></i>
				Salary stopped due to loss/performance
						</a>

						<b class="arrow"></b>
					</li>
                                        
                                        <li class="">
						<a href="formpage.jsp?pageid=8&caseid=null">
							<i class="menu-icon fa fa-caret-right"></i>
                                            Terminated Emp due to loss/shortage
						</a>

						<b class="arrow"></b>
					</li>
                                    <li class="">
						<a href="formpage.jsp?pageid=7&caseid=null">
							<i class="menu-icon fa fa-caret-right"></i>
							Native Staff Shortage
						</a>

						<b class="arrow"></b>
					</li>
                                        <li class="">
						<a href="formpage.jsp?pageid=6&caseid=null">
							<i class="menu-icon fa fa-caret-right"></i>
							Emp who passed away
						</a>

						<b class="arrow"></b>
					</li>
                                        <li class="">
						<a href="formpage.jsp?pageid=9&caseid=null">
							<i class="menu-icon fa fa-caret-right"></i>
						Leave Extension
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
									A2(Not returned from leave)
								</a>

								<b class="arrow"></b>
							</li>
                                                     

					<li class="">
						<a href="A3.jsp">
                                                    <i class="menu-icon fa fa-caret-right"></i>
							A3(Not returned from leave)
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
							<ul class="breadcrumb">
						</ul><!-- /.breadcrumb -->
						</div><!-- /.nav-search -->
					</div>
                                            <div class="page-content"></div>
                                            <div class="page-content"></div>
					<div class="page-content">
						
						
                                            
                                            <div class="main-content">
				<div class="main-content-inner">
					
					
					<div class="page-content">
						
<div class="row">
        <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <form action="ReportServlet" class="form-horizontal" method="post">

                        <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">FROM</label>

                                <div class="col-sm-9">
                                        <div class="pos-rel">
                                                <input class="form-control" type="date" name="from" />
                                        </div>
                                </div>
                        </div>
                                                <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">TO</label>

                                <div class="col-sm-9">
                                        <div class="pos-rel">
                                                <input class="form-control" type="date" name="to" />
                                        </div>
                                </div>
                                
                        </div>



									<div class="form-group">
										<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="food">CASE</label>

										<div class="col-xs-12 col-sm-9">
											<select name="caseName" class="multiselect">
												<option>A1::Not returned from leave</option>
												<option>A2::With Company Property</option>
												<option>A3::Damage/Loss</option>
												<option>DIS</option>
												<option>Salary Stopped::Due to Loss/Performance</option>
                                                                                                <option>Terminated Employees::Due To Loss/Shortage  </option>
                                                                                                <option>Native Shortage Staff  </option>
                                                                                                <option>Employee::Passed Away  </option>
                                                                                                <option>Leave Extension </option>
                                                                                                
											</select>
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
                                                                    

							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->
                                        
                                            
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<div class="footer">
				<div class="footer-inner">
					<div class="footer-content">
						<span class="bigger-120">	
						</span>

						
						</span>
					</div>
				</div>
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
		<script src="assets/js/jquery.bootstrap-duallistbox.min.js"></script>
		<script src="assets/js/jquery.raty.min.js"></script>
		<script src="assets/js/bootstrap-multiselect.min.js"></script>
		<script src="assets/js/select2.min.js"></script>
		<script src="assets/js/jquery-typeahead.js"></script>

		<!-- ace scripts -->
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($){
			    var demo1 = $('select[name="duallistbox_demo1[]"]').bootstrapDualListbox({infoTextFiltered: '<span class="label label-purple label-lg">Filtered</span>'});
				var container1 = demo1.bootstrapDualListbox('getContainer');
				container1.find('.btn').addClass('btn-white btn-info btn-bold');
			
				/**var setRatingColors = function() {
					$(this).find('.star-on-png,.star-half-png').addClass('orange2').removeClass('grey');
					$(this).find('.star-off-png').removeClass('orange2').addClass('grey');
				}*/
				$('.rating').raty({
					'cancel' : true,
					'half': true,
					'starType' : 'i'
					/**,
					
					'click': function() {
						setRatingColors.call(this);
					},
					'mouseover': function() {
						setRatingColors.call(this);
					},
					'mouseout': function() {
						setRatingColors.call(this);
					}*/
				})//.find('i:not(.star-raty)').addClass('grey');
				
				
				
				//////////////////
				//select2
				$('.select2').css('width','200px').select2({allowClear:true})
				$('#select2-multiple-style .btn').on('click', function(e){
					var target = $(this).find('input[type=radio]');
					var which = parseInt(target.val());
					if(which == 2) $('.select2').addClass('tag-input-style');
					 else $('.select2').removeClass('tag-input-style');
				});
				
				//////////////////
				$('.multiselect').multiselect({
				 enableFiltering: true,
				 enableHTML: true,
				 buttonClass: 'btn btn-white btn-primary',
				 templates: {
					button: '<button type="button" class="multiselect dropdown-toggle" data-toggle="dropdown"><span class="multiselect-selected-text"></span> &nbsp;<b class="fa fa-caret-down"></b></button>',
					ul: '<ul class="multiselect-container dropdown-menu"></ul>',
					filter: '<li class="multiselect-item filter"><div class="input-group"><span class="input-group-addon"><i class="fa fa-search"></i></span><input class="form-control multiselect-search" type="text"></div></li>',
					filterClearBtn: '<span class="input-group-btn"><button class="btn btn-default btn-white btn-grey multiselect-clear-filter" type="button"><i class="fa fa-times-circle red2"></i></button></span>',
					li: '<li><a tabindex="0"><label></label></a></li>',
			        divider: '<li class="multiselect-item divider"></li>',
			        liGroup: '<li class="multiselect-item multiselect-group"><label></label></li>'
				 }
				});
			
				
				///////////////////
					
				//typeahead.js
				//example taken from plugin's page at: https://twitter.github.io/typeahead.js/examples/
				var substringMatcher = function(strs) {
					return function findMatches(q, cb) {
						var matches, substringRegex;
					 
						// an array that will be populated with substring matches
						matches = [];
					 
						// regex used to determine if a string contains the substring `q`
						substrRegex = new RegExp(q, 'i');
					 
						// iterate through the pool of strings and for any string that
						// contains the substring `q`, add it to the `matches` array
						$.each(strs, function(i, str) {
							if (substrRegex.test(str)) {
								// the typeahead jQuery plugin expects suggestions to a
								// JavaScript object, refer to typeahead docs for more info
								matches.push({ value: str });
							}
						});
			
						cb(matches);
					}
				 }
			
				 $('input.typeahead').typeahead({
					hint: true,
					highlight: true,
					minLength: 1
				 }, {
					name: 'states',
					displayKey: 'value',
					source: substringMatcher(ace.vars['US_STATES']),
					limit: 10
				 });
					
					
				///////////////
				
				
				//in ajax mode, remove remaining elements before leaving page
				$(document).one('ajaxloadstart.page', function(e) {
					$('[class*=select2]').remove();
					$('select[name="duallistbox_demo1[]"]').bootstrapDualListbox('destroy');
					$('.rating').raty('destroy');
					$('.multiselect').multiselect('destroy');
				});
			
			});
		</script>
	</body>
</html>
