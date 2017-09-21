/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaCodePackage;

import ReportItems.ConsalidateReportItem;
import ReportItems.PayrollVecItem;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class MasterRoller {
    
    
    String BranchId;
    String BranchName;
    String Month;
    String Year;
    String fromDate;
    String toDate;
    conectionClass c;
    QuerisClass q;
    Vector<String> EmpPayrollDate;

    public MasterRoller(String BranchName, String BranchId, String Month, String Year, String fromDate, String toDate) {
        this.BranchId = BranchId;
        this.BranchName = BranchName;
        this.Month = Month;
        this.Year = Year;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
   
    
    
    
    public MasterRoller(String BranchName,String BranchId, String Month, String Year) {
        this.BranchId = BranchId;
        this.BranchName = BranchName;
        this.Month = Month;
        this.Year = Year;
         this.fromDate = "";
        this.toDate = "";
        
        
    }
    
    public Vector<ConsalidateReportItem> getConslidateDate(){
        
        c = new conectionClass();
        q =  new QuerisClass();
        Vector<ConsalidateReportItem> consalidateData = new Vector<ConsalidateReportItem>();
        Vector<String> empId = getEmployeeId();
        String fromDate[] = this.fromDate.split("-");
        String toDate[] = this.toDate.split("-");
        String firstDay = getDataFormated(fromDate[2],Integer.toString(Integer.parseInt(fromDate[1])-1),fromDate[0]);
        String secDay = getDataFormated(toDate[2],Integer.toString(Integer.parseInt(toDate[1])-1),toDate[0]);
           
        for(int i=0;i<empId.size();i++){
            
            try {
                String query = q.getNameEmployee(BranchId,empId.get(i));
                ResultSet res1 = c.excuteQuery(query);
                String EmpName = "null";
                while(res1.next()) {
                    EmpName = res1.getString(1);
                }
                
                
                query  = q.getConsalidateReport(BranchId,empId.get(i),firstDay,secDay);
                res1 = c.excuteQuery(query);
                ConsalidateReportItem consalidateDataItem = new ConsalidateReportItem
                        (BranchId, empId.get(i), EmpName, Month, Year);
                while(res1.next()) {
                    String LogDate = res1.getString(1);
                    String Status = res1.getString(2);
                    
                    consalidateDataItem.getLogDates().add(LogDate);
                    consalidateDataItem.getStatus().add(Status);
                         
                }
               consalidateData.add(consalidateDataItem);
                
                
            } catch (SQLException ex) {
                Logger.getLogger(MasterRoller.class.getName()).log(Level.SEVERE, null, ex);
            }
             
          
      }
      return  consalidateData;    
        
    }
    
    
    public Vector<PayrollVecItem> getPayrollData(){
        Vector<PayrollVecItem> payrollData = new Vector<PayrollVecItem>();
        try {
            c = new conectionClass();
            q =  new QuerisClass();
            String query  = q.getDataFromBioPuchingDetailsMonthlyQuery(BranchId,
                    ConstantClass.fullMonthNames[Integer.parseInt(Month)],Year);
           ResultSet res1 = c.excuteQuery(query);
           while(res1.next()) {
                 String empId = res1.getString(1);
                 String absent = res1.getString(2);
                 String annualLeave = res1.getString(3);
                 String compLeave = res1.getString(4);
                 String medLeave = res1.getString(5);
                 String patLeave = res1.getString(6);
                 String matLeave = res1.getString(7);
               
                 payrollData.add(new PayrollVecItem
                         (BranchId, empId,getEmployeeName(BranchId,empId), absent, annualLeave, compLeave, medLeave, patLeave, matLeave));
                 
               
           }
        } catch (SQLException ex) {
            Logger.getLogger(MasterRoller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return payrollData;
         
         
          
    }
    
    
//      String savedStatus =  getStatusFromDailyAttandanceTabel(branchId,empId,dailyDate);
//       if(savedStatus.equals("null")){
//           addStatusToDailyAttandanceTabel(branchId,empId,dailyDate,Status);
//       }
//       else{
//           updateStatusToDailyAttandanceTabel(branchId,empId,dailyDate,Status);
//       }
//       return savedStatus;
    
   
    private String getFromMonthlyAttandanceTabel(String empID){
         String returnVal = "null";  
         String query =q.selectFromBioPuchingDetailsMonthlyQuery(ConstantClass.fullMonthNames[Integer.parseInt(Month)],
                 Year, BranchId,empID );
         
         try {
           ResultSet res = c.excuteQuery(query);
           if(res != null){
              while(res.next()) {
                   String value = res.getString(1);
                   returnVal = value;
              }
           }
           
       }catch (SQLException ex) {
            //Logger.getLogger(InternalMainClass.class.getName()).log(Level.SEVERE, null, ex);
      }
         
         
         return returnVal;
    }
    
    
    private String getDateOfJoining(String compID,String empID){
         String returnVal = "null";  
         String query =q.getDOJ(compID, empID);
         
         try {
           ResultSet res = c.excuteQuery(query);
           if(res != null){
              while(res.next()) {
                   String value = res.getString(1);
                   returnVal = value;
              }
           }
           
       }catch (SQLException ex) {
            //Logger.getLogger(InternalMainClass.class.getName()).log(Level.SEVERE, null, ex);
      }
         
         
         return returnVal;
    }
    
    private void addToToMonthlyAttandanceTabel(PayrollVecItem item,int d,int m,int y){
        int transNum =0 ;
        String query=  q.getMaxTransactionNumber(BranchId);
        ResultSet res = c.excuteQuery(query);
        if(res != null){
           try {
               while(res.next()) {
                   String s = res.getString(1);
                   if(s == null)
                        transNum = 0;
                    else
                             
                     transNum= Integer.parseInt(res.getString(1));
                   }
           } catch (SQLException ex) {
                    Logger.getLogger(MasterRoller.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       else{
              transNum=0;
       }
       transNum++;
       query = q.addToBioPuchingDetailsMonthlyQuery(Integer.toString(transNum),
               getDataFormated(Integer.toString(d),Integer.toString(m) ,Integer.toString(y)),
               ConstantClass.fullMonthNames[Integer.parseInt(Month)]
              ,Year,BranchId,item.getEmpId(),item.getAbsent(), item.getAnnaulLeave(),
              item.getCompLeave(), item.getMedicalLeave(),item.getMatLeave() ,item.getPatLeave());
              c.excuteQuery(query);
        
        
    }
    
  
    
    private void updateStatusToDailyAttandanceTabel(String TransNum,int d,int m,int y,PayrollVecItem item){
        String query= q.updateBioPuchingDetailsMonthlyQuery(TransNum,
                getDataFormated(Integer.toString(d),Integer.toString(m) ,Integer.toString(y)),item.getAbsent(),item.getAnnaulLeave(),
                item.getCompLeave(),item.getMedicalLeave(),item.getMatLeave(),item.getPatLeave());
        c.excuteQuery(query);
     
    }
    
    private void addToLeaveTracker(PayrollVecItem item,String DOJ,int d,int m,int y){
        
          String query =q.addToLeaveTracker(BranchId,item.getEmpId(),DOJ,item.getAnnaulLeave()
                        ,item.getCompLeave(),item.getMedicalLeave(),item.getMatLeave(),item.getPatLeave(),
                        getDataFormated(Integer.toString(d),Integer.toString(m) ,Integer.toString(y)));
         c.excuteQuery(query);
        
    }
    
    private  void dealWithLeaveTracker(PayrollVecItem item,String DOJ,int d,int m,int y){
        String query = q.getLeaveTrackerData(BranchId, item.getEmpId());
        ResultSet res =c.excuteQuery(query);
        boolean t =true;
        if(res != null){
           try {
               while(res.next()) {
                   t=false;
                   String AL = Integer.toString(Integer.parseInt(res.getString(1)) + Integer.parseInt(item.getAnnaulLeave()));
                   String CL = Integer.toString(Integer.parseInt(res.getString(2)) + Integer.parseInt(item.getCompLeave()));
                   String ML =Integer.toString(Integer.parseInt(res.getString(3)) + Integer.parseInt(item.getMedicalLeave()));
                   String MATL = Integer.toString(Integer.parseInt(res.getString(4)) + Integer.parseInt(item.getMatLeave()));
                   String PATL = Integer.toString(Integer.parseInt(res.getString(5)) + Integer.parseInt(item.getPatLeave()));
                   
                   query = q.updateLeaveTracker(BranchId,item.getEmpId(),AL,CL,
                   ML,MATL,PATL,getDataFormated(Integer.toString(d),Integer.toString(m) ,Integer.toString(y)));
                   c.excuteQuery(query);
                   
                   }
           } catch (SQLException ex) {
                    Logger.getLogger(MasterRoller.class.getName()).log(Level.SEVERE, null, ex);
           }
           if(t)
               addToLeaveTracker(item,DOJ,d,m,y);  
       }
       else{
              addToLeaveTracker(item,DOJ,d,m,y);  
       }   
    }
    
    
    public void approvePayroll(Vector<PayrollVecItem> payrollData){
            c = new conectionClass();
            q =  new QuerisClass();
            
            Calendar cal = Calendar.getInstance();
            int d = cal.get(Calendar.DATE);
            int m = cal.get(Calendar.MONTH);
            int y = cal.get(Calendar.YEAR);
            
            for(int i=0;i<payrollData.size();i++){
                PayrollVecItem item = payrollData.get(i);
                String transActionNumber =   getFromMonthlyAttandanceTabel(item.getEmpId());
                 if(transActionNumber.equals("null")){
                     addToToMonthlyAttandanceTabel(item,d,m,y);
                 }
                 else{
                     updateStatusToDailyAttandanceTabel(transActionNumber,d,m,y,item);
                 }
                 String DOJ = getDateOfJoining(BranchId,item.getEmpId());
                 DOJ = DOJ.substring(0,DOJ.indexOf(" "));
                 String DOJarray[] = DOJ.split("-");
                 dealWithLeaveTracker(item,getDataFormated(DOJarray[2],
                  Integer.toString(Integer.parseInt(DOJarray[1])-1), DOJarray[0]),d,m,y);
                
            }
             
            c.closeConnection();
            
    }
    private String getEmployeeName(String BID,String EID){
         conectionClass c1 = new conectionClass();
         QuerisClass q1 =  new QuerisClass();
         String EmpName = "null";
         try {
            String query1 = q1.getNameEmployee(BID,EID);
            ResultSet res2 = c1.excuteQuery(query1);
           
            while(res2.next()) {
                EmpName = res2.getString(1);
            }
             c1.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MasterRoller.class.getName()).log(Level.SEVERE, null, ex);
        }
         return EmpName;
    }
 
    public Vector<PayrollVecItem> processPayroll(){
                
            c = new conectionClass();
            q =  new QuerisClass();
            Vector<String> empId = getEmployeeId();
            Vector<PayrollVecItem> payrollData = new Vector<PayrollVecItem>();
            
            String fromDate[] = this.fromDate.split("-");
            String toDate[] = this.toDate.split("-");
            
          
            
            String firstDay = getDataFormated(fromDate[2],Integer.toString(Integer.parseInt(fromDate[1])-1),fromDate[0]);
            String secDay = getDataFormated(toDate[2],Integer.toString(Integer.parseInt(toDate[1])-1),toDate[0]);
           
            for(int i=0;i<empId.size();i++){
            try {
                String query = q.getNameEmployee(BranchId,empId.get(i));
                ResultSet res1 = c.excuteQuery(query);
                String EmpName = "null";
                while(res1.next()) {
                    EmpName = res1.getString(1);
                }
                
                
                query  = q.getReportDataEmpWiseConsMonthly(BranchId,empId.get(i),firstDay,secDay);
                res1 = c.excuteQuery(query);
                PayrollVecItem payrollDataItem = new PayrollVecItem(BranchId, empId.get(i),EmpName);
                while(res1.next()) {
                    String Status = res1.getString(1);
                    String StatusCount = res1.getString(2);
                    
                    if(Status.equals(ConstantClass.AbsentCode))
                      payrollDataItem.setAbsent(StatusCount);
                    else if(Status.equals(ConstantClass.AnnualCode))
                      payrollDataItem.setAnnaulLeave(StatusCount);
                    else if(Status.equals(ConstantClass.CompCode))
                      payrollDataItem.setCompLeave(StatusCount);
                    else if(Status.equals(ConstantClass.SickCode))
                      payrollDataItem.setMedicalLeave(StatusCount);
                    else if(Status.equals(ConstantClass.MatCode))
                      payrollDataItem.setMatLeave(StatusCount);
                    else if(Status.equals(ConstantClass.PatCode))
                      payrollDataItem.setPatLeave(StatusCount);
                    else if(Status.equals(ConstantClass.NullCode))
                      payrollDataItem.setNotyet(StatusCount);
                    else if(Status.equals(ConstantClass.PresentCode))
                      payrollDataItem.setFullDay(StatusCount);
                    else if(Status.equals(ConstantClass.HalfDayCode))
                      payrollDataItem.setHalfDay(StatusCount);
                            
                        
                }
                payrollData.add(payrollDataItem);
                
                
            } catch (SQLException ex) {
                Logger.getLogger(MasterRoller.class.getName()).log(Level.SEVERE, null, ex);
            }
             
                
            }
            
          return payrollData;
            
            
            
    }
    
    
    
    public void initlizeMainFunction(String weeklyOffCode){
        try {
          
            c = new conectionClass();
            q =  new QuerisClass();
            Vector<String> empId = getEmployeeId();
         
            String fromDate[] = this.fromDate.split("-");
            String toDate[] = this.toDate.split("-");
            
            String startDateStr = fromDate[0]+"-"+fromDate[1]+"-"+fromDate[2];
            String endDateStr = toDate[0]+"-"+toDate[1]+"-"+toDate[2]; 
                        
    
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
                
            Date startDate =  df.parse(startDateStr);
            Date endDate =  df.parse(endDateStr);
                
            Calendar startCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();
                  
            startCal.setTime(startDate);
            endCal.setTime(endDate);
            int h=0;
            c.closeConnection();
            while(!startCal.after(endCal)){
               c = new conectionClass();
               int year = startCal.get(Calendar.YEAR);
               int month = startCal.get(Calendar.MONTH);
               int date = startCal.get(Calendar.DATE);
               int dayOfWeek = startCal.get(Calendar.DAY_OF_WEEK);
               String Status = ConstantClass.NullCode;  
               if(dayOfWeek == Integer.parseInt(weeklyOffCode))
                   Status = ConstantClass.WeeklyOffCode;
           //    String currentDate = df.format(startCal.getTime());
              
               String dbDate = getDataFormated(Integer.toString(date),Integer.toString(month),Integer.toString(year));
               for(int i=0;i<empId.size();i++){
                
                   addDetails(empId.get(i),dbDate,Status);
               
               }
               startCal.add(Calendar.DATE, 1);
               h++;
               c.closeConnection();
            }
            
            
        } catch (ParseException ex) {
            Logger.getLogger(MasterRoller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    
    private Vector<String> getEmployeeId(){
        
        Vector<String> empId = new Vector();
      
        String query = q.selectAllEmployeeForBranch(Integer.parseInt(BranchId));
       
        ResultSet res1 = c.excuteQuery(query);
        try {
            while(res1.next()) {
                  empId.add(res1.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MasterRoller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empId;
    }
    
    
    
     private String addDetails(String empId,String dailyDate,String Status){
     
       
       String savedStatus =  getStatusFromDailyAttandanceTabel(BranchId,empId,dailyDate);
       if(savedStatus.equals("null")){
           c.excuteQuery(q.addtStatusForSpecificEmpandDate(BranchId,empId,dailyDate,Status));
       }
//       else{
//           c.excuteQuery(q.updateStatusForSpecificEmpandDate(BranchId,empId,dailyDate,Status));
//       }
       savedStatus =  getStatusFromDailyAttandanceTabel(BranchId,empId,dailyDate);
       return savedStatus;
     }
    
     private String getStatusFromDailyAttandanceTabel(String branchId,String empId,String dailyDate){
      
       String returnVal = "null";  
       String query  = q.selectStatusForSpecificEmpandDate(branchId,empId,dailyDate);
       
       try {
           ResultSet res = c.excuteQuery(query);
           if(res != null){
              while(res.next()) {
                   String value = res.getString(1);
                   returnVal = value;
              }
           }
          
       }catch (SQLException ex) {
            //Logger.getLogger(InternalMainClass.class.getName()).log(Level.SEVERE, null, ex);
      }
      return returnVal;
    }
     
    
    
     
     private String getDataFormated(String date,String month,String year){
    
         
        return date+"-"+ConstantClass.MonthArray[Integer.parseInt(month)+1]+"-"+year;
    }
     
   
}
