/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import JavaCodePackage.ConstantClass;
import JavaCodePackage.QuerisClass;
import JavaCodePackage.conectionClass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Leave {
   
    String BranchID;
    String EmployeeID;
    String LeaveType;
    Date FromDate;
    Date ToDate;
    Calendar startCalender;
    Calendar endCalender; 
    SummryLeaveDataClass summryData;
  
    public Leave( String BranchID,  String EmployeeID, String LeaveType, 
            Date FromDate, Date ToDate ,String unProceesedData) {
        
        this.BranchID = BranchID;
      
        this.EmployeeID = EmployeeID;
        this.LeaveType = LeaveType;
        this.FromDate = FromDate;
        this.ToDate = ToDate;
        
        
        this.startCalender = Calendar.getInstance();
        this.startCalender.setTime(FromDate);
        this.endCalender = Calendar.getInstance();
        this.endCalender.setTime(ToDate);
        
        this.summryData = new SummryLeaveDataClass(BranchID, EmployeeID, unProceesedData);
        
                
    }

    
    public boolean checkLeaveValidity(){
        
        long numberOfDays = getNumberOfLeavesDays();
        if(LeaveType.equals(ConstantClass.AnnualCode) &&  numberOfDays + summryData.getLeavesData()[1] < ConstantClass.AnnualLeaveDuration)
            return true;
        else if (LeaveType.equals(ConstantClass.SickCode) &&  numberOfDays + summryData.getLeavesData()[2] < ConstantClass.SickLeaveDuration)
            return true;
        else if (LeaveType.equals(ConstantClass.MatCode) &&  numberOfDays + summryData.getLeavesData()[3] < ConstantClass.MatLeaveDuration)
            return true;
        else if (LeaveType.equals(ConstantClass.PatCode) &&  numberOfDays + summryData.getLeavesData()[4] < ConstantClass.PatLeaveDuration)
            return true;
        else if (LeaveType.equals(ConstantClass.CompCode) &&  numberOfDays + summryData.getLeavesData()[5] < ConstantClass.CompeaveDuration)
            return true;
        
        return false;
        
        
    }
    
    
    private long getNumberOfLeavesDays(){
        long diff = Math.abs(ToDate.getTime() - FromDate.getTime());
        long diffDays = diff / (24 * 60 * 60 * 1000);
        
        
        return diffDays;
    }
    
    
    
    
    public int addLeave(){
        if(!checkLeaveValidity())
            return -1;
        
        
        
        conectionClass c = new conectionClass();
        QuerisClass query = new QuerisClass();
        

        for (Date date = startCalender.getTime(); !startCalender.after(endCalender);
                 startCalender.add(Calendar.DATE, 1), date = startCalender.getTime()) 
            {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH)+1;
                int day = cal.get(Calendar.DAY_OF_MONTH);
                String l = getDataFormated(Integer.toString(day),Integer.toString(month-1),Integer.toString(year));
                String leaveDate = day+"-"+month+"-"+year;
                ResultSet res =  c.excuteQuery(query.getReportDataEmpWiseDailyForLeave(BranchID,EmployeeID,l));
                String s = "null";
            try {
                if(res.next()) {
                    s=res.getString(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Leave.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(s.equals("null"))    
               c.excuteQuery(query.addtStatusForSpecificEmpandDate(BranchID, EmployeeID, leaveDate, LeaveType)); 
            else
               c.excuteQuery(query.updateStatusForSpecificEmpandDateForLeave(BranchID, EmployeeID, l, LeaveType));
               
            }
        c.closeConnection();  

        return 1;
       
        
    }
    
    private String getDataFormated(String date,String month,String year){
      
        return date+"-"+ConstantClass.MonthArray[Integer.parseInt(month)+1]+"-"+year;
    }
    
  
    public String getBranchID() {
        return BranchID;
    }

    public void setBranchID(String BranchID) {
        this.BranchID = BranchID;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public String getLeaveType() {
        return LeaveType;
    }

    public void setLeaveType(String LeaveType) {
        this.LeaveType = LeaveType;
    }

    public Date getFromDate() {
        return FromDate;
    }

    public void setFromDate(Date FromDate) {
        this.FromDate = FromDate;
    }

    public Date getToDate() {
        return ToDate;
    }

    public void setToDate(Date ToDate) {
        this.ToDate = ToDate;
    }

    public SummryLeaveDataClass getSummryData() {
        return summryData;
    }

    public void setSummryData(SummryLeaveDataClass summryData) {
        this.summryData = summryData;
    }

    
    
    
}
