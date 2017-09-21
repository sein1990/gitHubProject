function reportformbuilder(butID,repID){

		this.buttonID=butID;
		this.reportID=repID;
		
		
		
}

reportformbuilder.prototype.buildTheForm =function(){
		var HTMLCode = "";
		if(this.buttonID == 0 && this.reportID == 1){
			HTMLCode = this.dailyForm();
		}
		else if(this.buttonID == 0 && this.reportID == 2){
			HTMLCode = this.monthlyyearlyForm();
		}
		else if(this.buttonID == 0 && this.reportID == 3){
			HTMLCode = this.periodForm();
		}
		else if(this.buttonID == 1 && this.reportID == 2){
			HTMLCode = this.monthlyyearlyForm();
		}
		else if(this.buttonID == 1 && this.reportID == 3){
			HTMLCode = this.periodForm();
		}
		else if(this.buttonID == 2 && this.reportID == 1){
			HTMLCode = this.dailyForm();
		}
		else if(this.buttonID == 2 && this.reportID == 2){
			HTMLCode = this.monthlyyearlyForm();
		}
		else if(this.buttonID == 2 && this.reportID == 3){
			HTMLCode = this.periodForm();
		}
		else if(this.buttonID == 3 && this.reportID == 1){
			HTMLCode = this.dailyForm();
		}
        
		HTMLCode = HTMLCode + "<input type='hidden' value='"+this.buttonID+"' name='buttonID' id='buttonID' />";
		HTMLCode = HTMLCode + "<input type='hidden' value='"+this.reportID+"' name='reportID' id='reportID' />";
		HTMLCode = HTMLCode + "<p><input type='submit' value='Get!' class='formsubmitbutton'/></p></center>";
		
		return HTMLCode;
};


reportformbuilder.prototype.buildStatusList =function(){

		var HTMLCode = "<center><p><select name='statusList' id='statusList' onchange='onStatusListChanhe(this.value)' class='jNiceSelectWrapperStatus' >)";
                HTMLCode = HTMLCode + "<option value=-3>All Status</option>";
		HTMLCode = HTMLCode + "<option value=-1>Absent</option>";
		HTMLCode = HTMLCode + "<option value=00>Full Day</option>";
		HTMLCode = HTMLCode + "<option value=-2>Half Day</option>";
                HTMLCode = HTMLCode + "<option value=01>ANNUAL LEAVE</option>";
                HTMLCode = HTMLCode + "<option value=02>SICK LEAVE</option>";
                HTMLCode = HTMLCode + "<option value=03>MATERNITY LEAVE</option>";
                HTMLCode = HTMLCode + "<option value=04>PATERNITY LEAVE</option>";
                HTMLCode = HTMLCode + "<option value=05>COMPASSIONATE LEAVE</option>";
                HTMLCode = HTMLCode + "<option value=06>Weekly Off</option>";
                HTMLCode = HTMLCode + "<option value=07>Not Yet Joined</option>";
                HTMLCode = HTMLCode + "</select></p></center>";
		
		
		return HTMLCode;
};
reportformbuilder.prototype.dailyForm =function(){
		var HTMLCode = "";
		
		if(this.buttonID == 2)
			HTMLCode = this.buildStatusList();
		
		HTMLCode = HTMLCode + "<center><p><input type='date' name='dailyDate'></p>";
		return HTMLCode;
};

reportformbuilder.prototype.monthlyyearlyForm =function(){
		
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var year = currentTime.getFullYear();
		
		var HTMLCode = "";
		
		if(this.buttonID == 2)
			HTMLCode = this.buildStatusList();
		
		HTMLCode = HTMLCode + "<center><p><select id='monthSelect' name='monthSelect' class='jNiceSelectWrapperBra'>";
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
	
		HTMLCode = HTMLCode + "<p><select id='yearSelect' name='yearSelect' class='jNiceSelectWrapperBra'>";
		HTMLCode = HTMLCode + "<option value='-1'>-Please Select Year-</option>";
		for(y=2015;y<=year;y++)
			HTMLCode = HTMLCode + "<option value='"+y+"'>"+y+"</option>";
		HTMLCode = HTMLCode + " </select></p>";
		
		return HTMLCode;
};

reportformbuilder.prototype.periodForm =function(){
		
		var HTMLCode = "";
		
		if(this.buttonID == 2)
			HTMLCode = this.buildStatusList();
		
		HTMLCode = HTMLCode + "<center><p><input name='fromDate' type='date'></p>";
		HTMLCode = HTMLCode + "<p><input name='toDate' type='date'></p>";
		return HTMLCode;
};
