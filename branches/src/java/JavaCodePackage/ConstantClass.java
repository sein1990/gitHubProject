/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaCodePackage;

import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 *
 * @author user
 */
public class ConstantClass {
    
    static final public String filePath = "C://Users//user//Desktop//id2";
    static final public String uploadedDirectory = filePath+"//leavesFiles";
    static final public String[] monthNames = {"","Jan","Feb","Mar","Apr","May","June",
                                        "Jul","Aug","Sep","Oct","Nov","Dec"};
    static final public String[] fullMonthNames = {"","JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE",
                                        "JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"};
    static final public int firstIDBranch = 21;
    static final public int lastIDBranch= 43;
    static final public int firstIDBranch1 = 61;
    static final public int lastIDBranch1= 66;
    
    static final public String[] MonthArray = {"","JAN","FEB","MAR","APR","MAY","JUN","JUL"
                                ,"AUG","SEP","OCT","NOV","DEC"};
    static final public int[] NumberOfDaysInMonthArray = {-1,31,28,31,30,31,30,31,
                               31,30,31,30,31};
    
    static final public String AbsentCode = "-1";
    static final public String PresentCode = "00";
    static final public String AnnualCode = "01";
    static final public String SickCode = "02";
    static final public String MatCode = "03";
    static final public String PatCode = "04";
    static final public String CompCode = "05";
    static final public String WeeklyOffCode = "06";
    static final public String NotJoinedYetCode = "07";
    
    static final public String HalfDayCode = "-2";
    static final public String NullCode = "-3";
    
    static final public int AnnualLeaveDuration = 28;
    static final public int SickLeaveDuration = 126;
    static final public int MatLeaveDuration = 84;
    static final public int PatLeaveDuration = 3;
    static final public int CompeaveDuration = 4;
    
   static final public String getStatus(String code){
       
       if(code.equals(AbsentCode))
           return "Absent";
       else if(code.equals(PresentCode))
           return "Present";
       else if(code.equals(HalfDayCode))
           return "Half Day";
       else if(code.equals(AnnualCode))
           return "Annual Leave";
       else if(code.equals(SickCode))
           return "Sick Leave";
       else if(code.equals(MatCode))
           return "Maternity Leave";
       else if(code.equals(PatCode))
           return "Paternity Leave";
       else if(code.equals(CompCode))
           return "Compassionate Leave";
       else if(code.equals(NullCode))
           return "Not yet";
       else if(code.equals(WeeklyOffCode))
           return "Weekly Off";
       else if(code.equals(NotJoinedYetCode))
           return "Not Joined Yet Code";
       
       
       return "";
       
   } 
   
   static final public String getShortForm(String code){
       
       if(code.equals(AbsentCode))
           return "A";
       else if(code.equals(PresentCode))
           return "P";
       else if(code.equals(HalfDayCode))
           return "H";
       else if(code.equals(AnnualCode))
           return "AL";
       else if(code.equals(SickCode))
           return "SL";
       else if(code.equals(MatCode))
           return "ML";
       else if(code.equals(PatCode))
           return "PL";
       else if(code.equals(CompCode))
           return "CL";
       else if(code.equals(NullCode))
           return "NY";
       else if(code.equals(WeeklyOffCode))
           return "WF";
       else if(code.equals(NotJoinedYetCode))
           return "NYJ";
       
       return "";
       
   } 
   
   
   static final public int getIndexOfMonth(String MonthName){
       
       for(int i=0;i<fullMonthNames.length;i++){
           if(fullMonthNames[i].equals(MonthName))
               return i;
       }
       return -1;
       
   }
   
   
   static final public String getFirstDayOfMonth(String Month,String Year){
       
        int monthIndex = Integer.parseInt(Month);
        if(monthIndex == -1 )
            monthIndex = 1;
        return "1-"+ConstantClass.MonthArray[monthIndex]+"-"+Year;
   }
    
   static final public String getLastDayOfMonth(String Month,String Year){
        
        int monthIndex = Integer.parseInt(Month);
        if(monthIndex == -1 )
            monthIndex = 12;
       
        if(monthIndex == 2 && Integer.parseInt(Year)%4==0)
            return "29-"+ConstantClass.MonthArray[2]+"-"+Year;
        
        return ConstantClass.NumberOfDaysInMonthArray[monthIndex]+"-"+ConstantClass.MonthArray[monthIndex]+"-"+Year;
   }
   
   
   static final public String getMyDocPath(){
       
        String myDocuments = null;

            try {
                Process p =  Runtime.getRuntime().exec("reg query \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\" /v personal");
                p.waitFor();

                InputStream in = p.getInputStream();
                byte[] b = new byte[in.available()];
                in.read(b);
                in.close();

                myDocuments = new String(b);
                myDocuments = myDocuments.split("\\s\\s+")[4];

            } catch(Throwable t) {
                t.printStackTrace();
            }
       return myDocuments;
   }
   
 
  
    
}
