


		var DaysArary = ["Sun","Mon","Tue","Wed","Thu","Fri","Sat"];
		var MonthsArary = ["January","February","March","April","May","June","July","August","September","October","November","December"];
		var NumberOdDaysInMonthArary = [31,28,31,30,31,30,31,31,30,31,30,31];
		var Month;
		var Year;

		function getNumberOfDaysPerMonth(){
				if(Month == 1 && Year % 4 == 0)
						return 29;
				return NumberOdDaysInMonthArary[Month];
		} 

		function getIndexOfTheFirstDayTOfTheMonth(){
				var date = new Date();
				date.setYear(Year);
				date.setMonth(Month);
				date.setDate(1);
				
				return date.getDay();
				
		}
		function calenderBuilder(Month,year){
			
			
			var numberOfDays = getNumberOfDaysPerMonth();
			var indexOfFirstDay = getIndexOfTheFirstDayTOfTheMonth();
			
			var numberOfSpaces = indexOfFirstDay - 1; 
			if(numberOfSpaces < 0)
				numberOfSpaces =  6;

			
			var HTMLCode = "<caption>"+MonthsArary[Month]+"-"+Year+"</caption>";
			HTMLCode = HTMLCode +"<col class='weekend'><thead><tr><th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th><th>Sat</th><th>Sun</th></tr></thead>";
			HTMLCode=HTMLCode+"<tbody><tr>";
			
			for(var i=0;i<numberOfSpaces;i++){
				HTMLCode=HTMLCode+"<td><button type='button'><div class='day'> </div></td>";
			}
			var dateCounter = 1;
			for(;dateCounter<=7-numberOfSpaces;dateCounter++){
				HTMLCode=HTMLCode+"<td><button type='button' onclick='dis("+dateCounter+")'><div class='day'>"+dateCounter+"</div></td>";
			}
			HTMLCode=HTMLCode+"</tr>";
			for(var i=1;i<6;i++){
				if(i%2 != 0) 
					HTMLCode=HTMLCode+"<tr class='odd'>";
				else
					HTMLCode=HTMLCode+"<tr>";
				for(var j=0;j<7 && dateCounter<=numberOfDays;j++,dateCounter++){
					HTMLCode=HTMLCode+"<td><button type='button' onclick='dis("+dateCounter+")'><div class='day'>"+dateCounter+"</div></td>";
				}
				HTMLCode=HTMLCode+"</tr>";
			}
			
			return HTMLCode;
			
			
		}