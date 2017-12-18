
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
//String UPLOAD_DIRECTORY ="C://Users//Sein 90//Desktop//up";
   QueryClass objQuery=new QueryClass();
String caseid1 = request.getParameter("caseid"); 
String dateS="";
String unityS="";
String nameS="";
String remarksS="";
String actionTakenS="";
String deathReasonS="";
String amountPaidToFamily2="";
String empIDS="";
 Connection con1=dbconnection.getConnection();
 String dbID="";
    PreparedStatement ps=null;
    ResultSet rs=null;
    String IDquery=objQuery.empPassedAway(caseid1);
    ps=con1.prepareStatement(IDquery);
    rs=ps.executeQuery();
  
    
    if(rs.next())
    {	
        dbID=rs.getString(1);
        dateS=rs.getString(2);
        nameS=rs.getString(3);
        unityS=rs.getString(4);
        remarksS=rs.getString(5);
        actionTakenS=rs.getString(6);
        deathReasonS=rs.getString(7);
        empIDS=rs.getString(8);   
        amountPaidToFamily2=rs.getString(9);
    }
    IDquery=objQuery.empPassedAwayAttachment(caseid1);
    ps=con1.prepareStatement(IDquery);
    rs=ps.executeQuery(); 
    Vector<Attachment> attachVector =new Vector();while(rs.next())
        attachVector.add(new Attachment(rs.getString(2), "1", caseid1, rs.getString(1),rs.getString(3)));

%>	
 
<div class="page-header">
<h1>
       Employee 
        <small>
                <i class="ace-icon fa fa-angle-double-right"></i>
             Passed Away
        </small>
</h1>
</div><!-- /.page-header -->
<div class="row">
<div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <% //formpage.jsp?pageid=1&caseid="<%=caseid1 %>
        <form class="form-horizontal"  action="EmpPassedAway" method="post" enctype="multipart/form-data" >
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
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> Employee ID and Name </label>

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
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> Remarks </label>

                        <div class="col-sm-9">
                                <input type="text" name="remarks" value="<%=remarksS%>" class="form-control"  required/>
                        
                        </div>
                </div>
             
            <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> Action Taken </label>

                        <div class="col-sm-9">
                                <input type="text" name="actionTaken" value="<%=actionTakenS%>" class="form-control"  required/>
     
                        </div>
                        </div>
                                 <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> Reason for Death</label>

                        <div class="col-sm-9">
                                <input type="text" name="deathReason" value="<%=deathReasonS%>" class="form-control"  required/>
     
                        </div>
                        </div>
                                
                                       <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1">Amount Paid To Family</label>

                        <div class="col-sm-9">
                                <input type="text" name="amountPaidToFamily" value="<%=amountPaidToFamily2%>" class="form-control"  required/>
     
                        </div>
                        </div>
                                                                                                                                                <div class="form-group">
                     
                
                </div>
                     
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
                '<input type="text" name="fileRemarks" class="form-control" required/>'+ '<b>'+fname+'</b></br>';
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
                                out.print("<input type='hidden' name='deleteattachmentpageid' id='deleteattachmentpageid' value='6'>");   
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
		<script type="text/javascript">
			
    
                            jQuery(function($) {
				$('#id-disable-check').on('click', function() {
					var inp = $('#form-input-readonly').get(0);
					if(inp.hasAttribute('disabled')) {
						inp.setAttribute('readonly' , 'true');
						inp.removeAttribute('disabled');
						inp.value="This text field is readonly!";
					}
					else {
						inp.setAttribute('disabled' , 'disabled');
						inp.removeAttribute('readonly');
						inp.value="This text field is disabled!";
					}
				});
			
			
				if(!ace.vars['touch']) {
					$('.chosen-select').chosen({allow_single_deselect:true}); 
					//resize the chosen on window resize
			
					$(window)
					.off('resize.chosen')
					.on('resize.chosen', function() {
						$('.chosen-select').each(function() {
							 var $this = $(this);
							 $this.next().css({'width': $this.parent().width()});
						})
					}).trigger('resize.chosen');
					//resize chosen on sidebar collapse/expand
					$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
						if(event_name != 'sidebar_collapsed') return;
						$('.chosen-select').each(function() {
							 var $this = $(this);
							 $this.next().css({'width': $this.parent().width()});
						})
					});
			
			
					$('#chosen-multiple-style .btn').on('click', function(e){
						var target = $(this).find('input[type=radio]');
						var which = parseInt(target.val());
						if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
						 else $('#form-field-select-4').removeClass('tag-input-style');
					});
				}
			
			
				$('[data-rel=tooltip]').tooltip({container:'body'});
				$('[data-rel=popover]').popover({container:'body'});
			
				autosize($('textarea[class*=autosize]'));
				
				$('textarea.limited').inputlimiter({
					remText: '%n character%s remaining...',
					limitText: 'max allowed : %n.'
				});
			
				$.mask.definitions['~']='[+-]';
				$('.input-mask-date').mask('99/99/9999');
				$('.input-mask-phone').mask('(999) 999-9999');
				$('.input-mask-eyescript').mask('~9.99 ~9.99 999');
				$(".input-mask-product").mask("a*-999-a999",{placeholder:" ",completed:function(){alert("You typed the following: "+this.val());}});
			
			
			
				$( "#input-size-slider" ).css('width','200px').slider({
					value:1,
					range: "min",
					min: 1,
					max: 8,
					step: 1,
					slide: function( event, ui ) {
						var sizing = ['', 'input-sm', 'input-lg', 'input-mini', 'input-small', 'input-medium', 'input-large', 'input-xlarge', 'input-xxlarge'];
						var val = parseInt(ui.value);
						$('#form-field-4').attr('class', sizing[val]).attr('placeholder', '.'+sizing[val]);
					}
				});
			
				$( "#input-span-slider" ).slider({
					value:1,
					range: "min",
					min: 1,
					max: 12,
					step: 1,
					slide: function( event, ui ) {
						var val = parseInt(ui.value);
						$('#form-field-5').attr('class', 'col-xs-'+val).val('.col-xs-'+val);
					}
				});
			
			
				
				//"jQuery UI Slider"
				//range slider tooltip example
				$( "#slider-range" ).css('height','200px').slider({
					orientation: "vertical",
					range: true,
					min: 0,
					max: 100,
					values: [ 17, 67 ],
					slide: function( event, ui ) {
						var val = ui.values[$(ui.handle).index()-1] + "";
			
						if( !ui.handle.firstChild ) {
							$("<div class='tooltip right in' style='display:none;left:16px;top:-6px;'><div class='tooltip-arrow'></div><div class='tooltip-inner'></div></div>")
							.prependTo(ui.handle);
						}
						$(ui.handle.firstChild).show().children().eq(1).text(val);
					}
				}).find('span.ui-slider-handle').on('blur', function(){
					$(this.firstChild).hide();
				});
				
				
				$( "#slider-range-max" ).slider({
					range: "max",
					min: 1,
					max: 10,
					value: 2
				});
				
				$( "#slider-eq > span" ).css({width:'90%', 'float':'left', margin:'15px'}).each(function() {
					// read initial values from markup and remove that
					var value = parseInt( $( this ).text(), 10 );
					$( this ).empty().slider({
						value: value,
						range: "min",
						animate: true
						
					});
				});
				
				$("#slider-eq > span.ui-slider-purple").slider('disable');//disable third item
			
				
				$('#id-input-file-1 , #id-input-file-2').ace_file_input({
					no_file:'No File ...',
					btn_choose:'Choose',
					btn_change:'Change',
					droppable:false,
					onchange:null,
					thumbnail:false //| true | large
					//whitelist:'gif|png|jpg|jpeg'
					//blacklist:'exe|php'
					//onchange:''
					//
				});
				//pre-show a file name, for example a previously selected file
				//$('#id-input-file-1').ace_file_input('show_file_list', ['myfile.txt'])
			
			
				$('#id-input-file-3').ace_file_input({
					style: 'well',
					btn_choose: 'Drop files here or click to choose',
					btn_change: null,
					no_icon: 'ace-icon fa fa-cloud-upload',
					droppable: true,
					thumbnail: 'small'//large | fit
					//,icon_remove:null//set null, to hide remove/reset button
					/**,before_change:function(files, dropped) {
						//Check an example below
						//or examples/file-upload.html
						return true;
					}*/
					/**,before_remove : function() {
						return true;
					}*/
					,
					preview_error : function(filename, error_code) {
						//name of the file that failed
						//error_code values
						//1 = 'FILE_LOAD_FAILED',
						//2 = 'IMAGE_LOAD_FAILED',
						//3 = 'THUMBNAIL_FAILED'
						//alert(error_code);
					}
			
				}).on('change', function(){
					//console.log($(this).data('ace_input_files'));
					//console.log($(this).data('ace_input_method'));
				});
				
				
				//$('#id-input-file-3')
				//.ace_file_input('show_file_list', [
					//{type: 'image', name: 'name of image', path: 'http://path/to/image/for/preview'},
					//{type: 'file', name: 'hello.txt'}
				//]);
			
				
				
			
				//dynamically change allowed formats by changing allowExt && allowMime function
				$('#id-file-format').removeAttr('checked').on('change', function() {
					var whitelist_ext, whitelist_mime;
					var btn_choose
					var no_icon
					if(this.checked) {
						btn_choose = "Drop images here or click to choose";
						no_icon = "ace-icon fa fa-picture-o";
			
						whitelist_ext = ["jpeg", "jpg", "png", "gif" , "bmp"];
						whitelist_mime = ["image/jpg", "image/jpeg", "image/png", "image/gif", "image/bmp"];
					}
					else {
						btn_choose = "Drop files here or click to choose";
						no_icon = "ace-icon fa fa-cloud-upload";
						
						whitelist_ext = null;//all extensions are acceptable
						whitelist_mime = null;//all mimes are acceptable
					}
					var file_input = $('#id-input-file-3');
					file_input
					.ace_file_input('update_settings',
					{
						'btn_choose': btn_choose,
						'no_icon': no_icon,
						'allowExt': whitelist_ext,
						'allowMime': whitelist_mime
					})
					file_input.ace_file_input('reset_input');
					
					file_input
					.off('file.error.ace')
					.on('file.error.ace', function(e, info) {
						//console.log(info.file_count);//number of selected files
						//console.log(info.invalid_count);//number of invalid files
						//console.log(info.error_list);//a list of errors in the following format
						
						//info.error_count['ext']
						//info.error_count['mime']
						//info.error_count['size']
						
						//info.error_list['ext']  = [list of file names with invalid extension]
						//info.error_list['mime'] = [list of file names with invalid mimetype]
						//info.error_list['size'] = [list of file names with invalid size]
						
						
						/**
						if( !info.dropped ) {
							//perhapse reset file field if files have been selected, and there are invalid files among them
							//when files are dropped, only valid files will be added to our file array
							e.preventDefault();//it will rest input
						}
						*/
						
						
						//if files have been selected (not dropped), you can choose to reset input
						//because browser keeps all selected files anyway and this cannot be changed
						//we can only reset file field to become empty again
						//on any case you still should check files with your server side script
						//because any arbitrary file can be uploaded by user and it's not safe to rely on browser-side measures
					});
					
					
					/**
					file_input
					.off('file.preview.ace')
					.on('file.preview.ace', function(e, info) {
						console.log(info.file.width);
						console.log(info.file.height);
						e.preventDefault();//to prevent preview
					});
					*/
				
				});
			
				$('#spinner1').ace_spinner({value:0,min:0,max:200,step:10, btn_up_class:'btn-info' , btn_down_class:'btn-info'})
				.closest('.ace-spinner')
				.on('changed.fu.spinbox', function(){
					//console.log($('#spinner1').val())
				}); 
				$('#spinner2').ace_spinner({value:0,min:0,max:10000,step:100, touch_spinner: true, icon_up:'ace-icon fa fa-caret-up bigger-110', icon_down:'ace-icon fa fa-caret-down bigger-110'});
				$('#spinner3').ace_spinner({value:0,min:-100,max:100,step:10, on_sides: true, icon_up:'ace-icon fa fa-plus bigger-110', icon_down:'ace-icon fa fa-minus bigger-110', btn_up_class:'btn-success' , btn_down_class:'btn-danger'});
				$('#spinner4').ace_spinner({value:0,min:-100,max:100,step:10, on_sides: true, icon_up:'ace-icon fa fa-plus', icon_down:'ace-icon fa fa-minus', btn_up_class:'btn-purple' , btn_down_class:'btn-purple'});
			
				//$('#spinner1').ace_spinner('disable').ace_spinner('value', 11);
				//or
				//$('#spinner1').closest('.ace-spinner').spinner('disable').spinner('enable').spinner('value', 11);//disable, enable or change value
				//$('#spinner1').closest('.ace-spinner').spinner('value', 0);//reset to 0
			
			
				//datepicker plugin
				//link
				$('.date-picker').datepicker({
					autoclose: true,
					todayHighlight: true
				})
				//show datepicker when clicking on the icon
				.next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
			
				//or change it into a date range picker
				$('.input-daterange').datepicker({autoclose:true});
			
			
				//to translate the daterange picker, please copy the "examples/daterange-fr.js" contents here before initialization
				$('input[name=date-range-picker]').daterangepicker({
					'applyClass' : 'btn-sm btn-success',
					'cancelClass' : 'btn-sm btn-default',
					locale: {
						applyLabel: 'Apply',
						cancelLabel: 'Cancel',
					}
				})
				.prev().on(ace.click_event, function(){
					$(this).next().focus();
				});
			
			
				$('#timepicker1').timepicker({
					minuteStep: 1,
					showSeconds: true,
					showMeridian: false,
					disableFocus: true,
					icons: {
						up: 'fa fa-chevron-up',
						down: 'fa fa-chevron-down'
					}
				}).on('focus', function() {
					$('#timepicker1').timepicker('showWidget');
				}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
				
				
			
				
				if(!ace.vars['old_ie']) $('#date-timepicker1').datetimepicker({
				 //format: 'MM/DD/YYYY h:mm:ss A',//use this option to display seconds
				 icons: {
					time: 'fa fa-clock-o',
					date: 'fa fa-calendar',
					up: 'fa fa-chevron-up',
					down: 'fa fa-chevron-down',
					previous: 'fa fa-chevron-left',
					next: 'fa fa-chevron-right',
					today: 'fa fa-arrows ',
					clear: 'fa fa-trash',
					close: 'fa fa-times'
				 }
				}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
				
			
				$('#colorpicker1').colorpicker();
				//$('.colorpicker').last().css('z-index', 2000);//if colorpicker is inside a modal, its z-index should be higher than modal'safe
			
				$('#simple-colorpicker-1').ace_colorpicker();
				//$('#simple-colorpicker-1').ace_colorpicker('pick', 2);//select 2nd color
				//$('#simple-colorpicker-1').ace_colorpicker('pick', '#fbe983');//select #fbe983 color
				//var picker = $('#simple-colorpicker-1').data('ace_colorpicker')
				//picker.pick('red', true);//insert the color if it doesn't exist
			
			
				$(".knob").knob();
				
				
				var tag_input = $('#form-field-tags');
				try{
					tag_input.tag(
					  {
						placeholder:tag_input.attr('placeholder'),
						//enable typeahead by specifying the source array
						source: ace.vars['US_STATES'],//defined in ace.js >> ace.enable_search_ahead
						/**
						//or fetch data from database, fetch those that match "query"
						source: function(query, process) {
						  $.ajax({url: 'remote_source.php?q='+encodeURIComponent(query)})
						  .done(function(result_items){
							process(result_items);
						  });
						}
						*/
					  }
					)
			
					//programmatically add/remove a tag
					var $tag_obj = $('#form-field-tags').data('tag');
					$tag_obj.add('Programmatically Added');
					
					var index = $tag_obj.inValues('some tag');
					$tag_obj.remove(index);
				}
				catch(e) {
					//display a textarea for old IE, because it doesn't support this plugin or another one I tried!
					tag_input.after('<textarea id="'+tag_input.attr('id')+'" name="'+tag_input.attr('name')+'" rows="3">'+tag_input.val()+'</textarea>').remove();
					//autosize($('#form-field-tags'));
				}
				
				
				/////////
				$('#modal-form input[type=file]').ace_file_input({
					style:'well',
					btn_choose:'Drop files here or click to choose',
					btn_change:null,
					no_icon:'ace-icon fa fa-cloud-upload',
					droppable:true,
					thumbnail:'large'
				})
				
				//chosen plugin inside a modal will have a zero width because the select element is originally hidden
				//and its width cannot be determined.
				//so we set the width after modal is show
				$('#modal-form').on('shown.bs.modal', function () {
					if(!ace.vars['touch']) {
						$(this).find('.chosen-container').each(function(){
							$(this).find('a:first-child').css('width' , '210px');
							$(this).find('.chosen-drop').css('width' , '210px');
							$(this).find('.chosen-search input').css('width' , '200px');
						});
					}
				})
				/**
				//or you can activate the chosen plugin after modal is shown
				//this way select element becomes visible with dimensions and chosen works as expected
				$('#modal-form').on('shown', function () {
					$(this).find('.modal-chosen').chosen();
				})
				*/
			
				
				
				$(document).one('ajaxloadstart.page', function(e) {
					autosize.destroy('textarea[class*=autosize]')
					
					$('.limiterBox,.autosizejs').remove();
					$('.daterangepicker.dropdown-menu,.colorpicker.dropdown-menu,.bootstrap-datetimepicker-widget.dropdown-menu').remove();
				});
			
			});
		</script>
                
                   <script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
                         function onCloseFunction(){
                            alert("ffff");
                        }
                   
                   
                   </script>
		<script src="assets/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->
		<script src="assets/js/dropzone.min.js"></script>

		<!-- ace scripts -->
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($){
			
			try {
			  Dropzone.autoDiscover = false;
			
			  var myDropzone = new Dropzone('#dropzone', {
			    previewTemplate: $('#preview-template').html(),
			    
				thumbnailHeight: 120,
			    thumbnailWidth: 120,
			    maxFilesize: 0.5,
				
				//addRemoveLinks : true,
				//dictRemoveFile: 'Remove',
				
				dictDefaultMessage :
				'<span class="bigger-150 bolder"><i class="ace-icon fa fa-caret-right red"></i> Drop files</span> to upload \
				<span class="smaller-80 grey">(or click)</span> <br /> \
				<i class="upload-icon ace-icon fa fa-cloud-upload blue fa-3x"></i>'
			,
				
			    thumbnail: function(file, dataUrl) {
			      if (file.previewElement) {
			        $(file.previewElement).removeClass("dz-file-preview");
			        var images = $(file.previewElement).find("[data-dz-thumbnail]").each(function() {
						var thumbnailElement = this;
						thumbnailElement.alt = file.name;
						thumbnailElement.src = dataUrl;
					});
			        setTimeout(function() { $(file.previewElement).addClass("dz-image-preview"); }, 1);
			      }
			    }
			
			  });
			
			
			  //simulating upload progress
			  var minSteps = 6,
			      maxSteps = 60,
			      timeBetweenSteps = 100,
			      bytesPerStep = 100000;
			
			  myDropzone.uploadFiles = function(files) {
			    var self = this;
			
			    for (var i = 0; i <2; i++) {
			      var file = files[i];
			          totalSteps = Math.round(Math.min(maxSteps, Math.max(minSteps, file.size / bytesPerStep)));
			
			      for (var step = 0; step < totalSteps; step++) {
			        var duration = timeBetweenSteps * (step + 1);
			        setTimeout(function(file, totalSteps, step) {
			          return function() {
			            file.upload = {
			              progress: 100 * (step + 1) / totalSteps,
			              total: file.size,
			              bytesSent: (step + 1) * file.size / totalSteps
			            };
			
			            self.emit('uploadprogress', file, file.upload.progress, file.upload.bytesSent);
			            if (file.upload.progress == 100) {
			              file.status = Dropzone.SUCCESS;
			              self.emit("success", file, 'success', null);
			              self.emit("complete", file);
			              self.processQueue();
			            }
			          };
			        }(file, totalSteps, step), duration);
			      }
			    }
			   }
			   //remove dropzone instance when leaving this page in ajax mode
			   $(document).one('ajaxloadstart.page', function(e) {
					try {
						myDropzone.destroy();
					} catch(e) {}
			   });
			
			} catch(e) {
			}
			
			});
		</script>