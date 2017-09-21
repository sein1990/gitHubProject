function calenderbuilder(M,Y,O,OB,A){


		this.DaysArary = ["Sun","Mon","Tue","Wed","Thu","Fri","Sat"];
		this.MonthsArary = ["January","February","March","April","May","June","July","August","September","October","November","December"];
		this.NumberOdDaysInMonthArary = [31,28,31,30,31,30,31,31,30,31,30,31];
		this.Month=M;
		this.Year=Y;
		var today = new Date();
		this.date= today.getDate();
		this.OpenDays = O;
		this.OpenDaysBack = OB;
		this.NotMasterAdmin=A;
		this.NumberOfPreviousMonthDays=0;
                this.ListOfPreviousMonthDays=[];
               
                var d = new Date();
                var systemMonth = d.getMonth();
                var systemYear = d.getFullYear();
                
                
                
                if(systemMonth-this.Month == 1 && systemYear == this.Year){
                   
                    
                    if(this.date <= this.OpenDays){
                        
                        var j = this.NumberOdDaysInMonthArary[M]; 
                        for(i=0;i<(this.OpenDays-this.date)+1;i++){
                            //alert(j);
                            this.ListOfPreviousMonthDays.push(j);
                            j--;
                            
                        }
                        
                    }
                }
                
}

calenderbuilder.prototype.getNumberOfDaysPerMonth =function(){
	if(this.Month == 1 && this.Year % 4 == 0)
		return 29;
	return this.NumberOdDaysInMonthArary[this.Month];

	};

calenderbuilder.prototype.getIndexOfTheFirstDayTOfTheMonth  = function (){
	var date = new Date();
	date.setYear(this.Year);
	date.setMonth(this.Month);
	date.setDate(1);
				
	return date.getDay();
				
};

calenderbuilder.prototype.checkPreMonth = function (datee){
    
    for(var k=0,size = this.ListOfPreviousMonthDays.length;k<size;k++){
        if(this.ListOfPreviousMonthDays[k] == datee){
            return true;
            
        }    
    }
    return false;
    
}


calenderbuilder.prototype.buildCalender = function (){
	
	var numberOfDays = this.getNumberOfDaysPerMonth();
	var indexOfFirstDay = this.getIndexOfTheFirstDayTOfTheMonth();
	
	var numberOfSpaces = indexOfFirstDay - 1; 
	if(numberOfSpaces < 0)
		numberOfSpaces =  6;
	var HTMLCode = "<caption>"+this.MonthsArary[this.Month]+"-"+this.Year+"</caption>";
		HTMLCode = HTMLCode +"<col class='weekend'><thead><tr><th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th><th>Sat</th><th>Sun</th></tr></thead>";
		HTMLCode=HTMLCode+"<tbody><tr>";
	for(var i=0;i<numberOfSpaces;i++){
		HTMLCode=HTMLCode+"<td><button type='button'><div class='day'> </div></td>";
	}
	var dateCounter = 1;
	for(;dateCounter<=7-numberOfSpaces;dateCounter++){
                HTMLCode=HTMLCode+"<td><button type='button' ";
                
                var enteredInPremonthLoop = this.checkPreMonth(dateCounter);
                if(enteredInPremonthLoop)
                    HTMLCode=HTMLCode+" enabled ";
                else if(!this.NotMasterAdmin && this.OpenDaysBack == 0)
				HTMLCode=HTMLCode+" enabled ";
		else if(!enteredInPremonthLoop && this.NotMasterAdmin && ! ((dateCounter-this.date) >=-1*(this.OpenDays)   && (dateCounter-this.date) < this.OpenDaysBack))
				HTMLCode=HTMLCode+" disabled ";
		
		HTMLCode=HTMLCode+" onclick='directToDailyPage("+dateCounter+")'><div class='day'>"+dateCounter+"</div></td>";
	}
	
	
	HTMLCode=HTMLCode+"</tr>";
        for(var i=1;i<6;i++){
		if(i%2 != 0) 
			HTMLCode=HTMLCode+"<tr class='odd'>";
		else
			HTMLCode=HTMLCode+"<tr>";
		for(var j=0;j<7 && dateCounter<=numberOfDays;j++,dateCounter++){
			HTMLCode=HTMLCode+"<td><button type='button'";
			
                        enteredInPremonthLoop = this.checkPreMonth(dateCounter);
                        if(enteredInPremonthLoop){
                            HTMLCode=HTMLCode+" enabled ";
                           
            
                        }
                        else if(! this.NotMasterAdmin && this.OpenDaysBack == 0)
				HTMLCode=HTMLCode+" enabled ";
			    
                        else if(this.NotMasterAdmin &&  ! ((dateCounter-this.date) >=-1*(this.OpenDays)   && (dateCounter-this.date) < this.OpenDaysBack))
				HTMLCode=HTMLCode+" disabled ";
			
			HTMLCode=HTMLCode+" onclick='directToDailyPage("+dateCounter+")'><div class='day'>"+dateCounter+"</div></td>";
		}
		HTMLCode=HTMLCode+"</tr>";
	}
	return HTMLCode;
	
	
	
};