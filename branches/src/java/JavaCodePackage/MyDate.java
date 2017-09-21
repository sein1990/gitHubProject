package JavaCodePackage;


import java.util.Calendar;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class MyDate {
    
    private final String[] DaysArary = {"","Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
    private final String[] MonthsArary = {"","January","February","March","April","May","June","July",
                                            "August","September","October","November","December"};
    private final int[] NumberOdDaysInMonthArary = {-1,31,28,31,30,31,30,31,31,30,31,30,31};
                                            
    private Calendar ActualDate;
    private String Day;
    private String MonthName;
    private int Date;
    private int Month;
    private int Year;

    
    public String getTheFirstDayOfTheMonth(){
        Calendar c = ActualDate;
        c.set(Year, Month-1, 1);
        return DaysArary[c.get(Calendar.DAY_OF_WEEK)];
    }
    
    public int getIndexOfTheFirstDayTOfTheMonth(){
        Calendar c = ActualDate;
        c.set(Year, Month-1, 1);
        return c.get(Calendar.DAY_OF_WEEK);
    }
    
    public int getNumberOfDaysPerMonth(){
        if(Month == 2 && Year % 4 == 0)
                return 29;
        return NumberOdDaysInMonthArary[Month];
    } 
    public MyDate(){
       
        ActualDate = Calendar.getInstance();
        Date = ActualDate.get(Calendar.DATE);
        Month = ActualDate.get(Calendar.MONTH)+1;
        Year = ActualDate.get(Calendar.YEAR);
        Day = DaysArary[ActualDate.get(ActualDate.DAY_OF_WEEK)];
        MonthName = MonthsArary[Month];
    }

    public String getMonthName() {
        return MonthName;
    }

    public void setMonthName(String MonthName) {
        this.MonthName = MonthName;
    }
    
    MyDate(Calendar PrameterDate){
        ActualDate = PrameterDate;
        Date = ActualDate.get(Calendar.DATE);
        Month = ActualDate.get(Calendar.MONTH)+1;
        Year = ActualDate.get(Calendar.YEAR);
        Day = DaysArary[ActualDate.get(ActualDate.DAY_OF_WEEK)];
         MonthName = MonthsArary[Month];
    }
    
    
    public String getSting(){
        return Date+"-"+Month+"-"+Year+"-"+Day;
        
       
    }
    
   
    public String getDay() {
        return Day;
    }

    public void setDay(String Day) {
        this.Day = Day;
    }

    public int getDate() {
        return Date;
    }

    public void setDate(int Date) {
        this.Date = Date;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int Month) {
        this.Month = Month;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }
    
    
    
}
