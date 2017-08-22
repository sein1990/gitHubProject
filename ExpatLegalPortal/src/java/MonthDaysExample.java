/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
   import java.util.Calendar;
import java.util.Scanner;
import static javax.print.attribute.Size2DSyntax.MM;

public class MonthDaysExample {
public static void main(String[] args)
	        {
	        //Create scanner object to obtain input from user
	        Scanner input = new Scanner (System.in);
	         
	        int MonthNum; //To hold the month from user input
	        int Year; //To hold the year
	        int numDays;
	        String Month="";
	         
	        System.out.print("Please enter the Month #");
	        MonthNum = input.nextInt();	        System.out.print("Please enter the Year");
	        Year = input.nextInt();
	         
	        if (MonthNum == 2)
	            {
	             if ( (Year % 4 == 0) && (Year % 400 == 0)
	                     && !(Year % 100 == 0) )
	                    numDays = 29;
	                else
	                    numDays = 28;
	            }
	        else if (MonthNum == 1 || MonthNum == 3 || MonthNum == 5 || MonthNum == 7 || MonthNum == 8 
	                    || MonthNum == 10 || MonthNum == 12)
	            numDays = 31;
	        else
	            numDays = 30;
	         	        if (MonthNum == 1)
	            Month = "January";
	        else if (MonthNum == 2)
	            Month = "Feburary";
	        else if (MonthNum == 3)
	            Month = "March";
	        else if (MonthNum == 4)
	            Month = "April";
	        else if (MonthNum == 5)
	            Month = "May";
	        else if (MonthNum == 6)
	            Month = "June";
	        else if (MonthNum == 7)
	            Month = "July";
	        else if (MonthNum == 8)
	            Month = "August";
	        else if (MonthNum == 9)
	            Month = "September";
	        else if (MonthNum == 10)
	            Month = "October";
	        else if (MonthNum == 11)
	            Month = "November";
	        else if (MonthNum == 12)
	            Month = "December";
	     
	         
	    //    System.out.println(Month + " " + Year " has " + numDays"." );
	        System.out.println(Month);
	        System.out.println(numDays);
	    }
}
