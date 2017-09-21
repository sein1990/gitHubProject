/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaCodePackage;

/**
 *
 * @author user
 */
public class QuerisClass {
    
    public String getSpecificBranchesQuery(int id){
        return "select comp_id,comp_name,op_days from comp_info where "
                    + "comp_id = "+ id;
    }
    
    public String getBranchesQuery(int sID, int eID,int sID1,int eID1){
        return "select comp_id,comp_name,op_days from comp_info where "
                    + "comp_id between "+ sID +" and "+eID+" or comp_id between "+ sID1 +" and "+eID1;
    }
    
    public String getAdminsQuery(){
        return "select comp_id,user_name,user_pass from user_admin order by comp_id";
    }
    
    public String addAdminQuery(int AdminAndBranchId,String UserName,String Password){
        
        return "insert into user_admin (comp_id,user_name,user_pass) values"+
                " ('"+AdminAndBranchId+"','"+UserName+"','"+Password+"')";         
    }
    
    public String addBranchQuery(String id,String name,int numberOfOpenDays){
        
        return "insert into comp_info (comp_id,comp_name,op_days) values"+
                "("+id+", "+name+","+numberOfOpenDays+")";                  
    }
    
    
    public String getDataFromBioPuchingDetailsMonthlyQuery(String CompID,String TransMonth,String TtransYear){
        return "select EMP_ID,ABSENT,ANNUAL_LEAVE,COMPASSIONATE_LEAVE,MEDICAL_LEAVE,"
                + "PATERNITY_LEAVE,MATERNITY_LEAVE from BIO_PUNCHING_DETAILS_MONTHLY "
                + "where comp_id='"+CompID+"' and TRANSACTION_MONTH ='"+TransMonth+"' "
                + "and TRANSACTION_YEAR='"+TtransYear+"'";
    }
    
    public String addToBioPuchingDetailsMonthlyQuery(String TransNum,String TranDate,String TranMonth,
            String TranYear,String CompID,String EmpID,String numberOfAbsentDays,
            String NAL,String NCL,String NML,String NMATL,String NPATL){        
        return "insert into BIO_PUNCHING_DETAILS_MONTHLY (TRAN_NO,TRANSACTION_DATE,"
                + "TRANSACTION_MONTH,TRANSACTION_YEAR,comp_id,emp_id,absent,"
                + "ANNUAL_LEAVE,COMPASSIONATE_LEAVE,MEDICAL_LEAVE,MATERNITY_LEAVE,PATERNITY_LEAVE) values"+
                "(to_char('"+TransNum+"'),to_char(to_date('"+TranDate+"','DD-MM-RRRR'),'DD-MON-RR'),to_char('"+TranMonth+"'),"+TranYear+",to_char('"+CompID+"'),"
                +"to_char('"+EmpID+"'),"+numberOfAbsentDays+","+NAL+","+NCL+","+NML+","+NMATL+","+NPATL+")";                  
    }
    
     public String updateBioPuchingDetailsMonthlyQuery(String TransNum,String TranDate,String numberOfAbsentDays,
            String NAL,String NCL,String NML,String NMATL,String NPATL){        
      
         
         return "UPDATE BIO_PUNCHING_DETAILS_MONTHLY SET "
                +"absent='"+numberOfAbsentDays+"',ANNUAL_LEAVE='"+NAL+"',COMPASSIONATE_LEAVE='"+NCL+"'"
                +",MEDICAL_LEAVE='"+NML+"',"+"MATERNITY_LEAVE='"+NMATL+"',PATERNITY_LEAVE='"+NPATL+"'"
                +",TRANSACTION_DATE=to_char(to_date('"+TranDate+"','DD-MM-RRRR'),'DD-MON-RR') " 
                +"WHERE TRAN_NO='"+TransNum+"'";
          }
    
    
    
    public String selectFromBioPuchingDetailsMonthlyQuery(String TranMonth,
            String TranYear,String CompID,String EmpID){        
        return "select TRAN_NO from BIO_PUNCHING_DETAILS_MONTHLY where TRANSACTION_MONTH='"+TranMonth+
               "' and TRANSACTION_YEAR='"+TranYear+"' and comp_id='"+CompID+"' and emp_id='"+EmpID+"'";
        
                     
    }
    
    
    public String selectEmployeesOnLeavesQuery(String BranchId,String date){
        return "select EMP_ID,STATUS from BRANCH_DAILY_ATTENDANCE WHERE COMP_ID = '"+BranchId+"'"
                + " AND LOGDATE=to_char(to_date('"+date+"','DD-MM-RRRR'),'DD-MON-RR') "
                + " AND STATUS IN ('01','02','03','04','05','06','07')";
    }
    
    
     public String selectApprovalMonthFromBioPuchingDetailsMonthlyQuery(String CompID,String EmpID){        
//        return "select TRANSACTION_MONTH,TRANSACTION_YEAR from BIO_PUNCHING_DETAILS_MONTHLY where " + 
//               "' comp_id='"+CompID+"' and emp_id='"+EmpID+"' order by TRANSACTION_DATE desc ";
        return "select TRANSACTION_MONTH,TRANSACTION_YEAR from BIO_PUNCHING_DETAILS_MONTHLY where COMP_ID='"+CompID+"'"
         + " and EMP_ID='"+EmpID+"' order by TRANSACTION_DATE desc";
                     
    }
    

    public String getMaxTransactionNumber(String branchID){        
        return "select max (TRAN_NO) from BIO_PUNCHING_DETAILS_MONTHLY where comp_id="+branchID;                  
    }
    
    public String updateBranchQuery(String id,int numberOfOpenDays){
        
        return "UPDATE comp_info SET op_days=' "+numberOfOpenDays+" ' WHERE comp_id="+id;                
    }
    
    public String updateAdminQuery(String UserName,String Password,int id){
        
        return "UPDATE user_admin SET user_name='"+UserName+"' , user_pass='"+Password+"' WHERE comp_id="+id;                
    }
    
    
    public String selectAllEmployee(int sID, int eID,int sID1, int eID1){
        return "select EMP_ID, NAME,COMP_ID  from EXISTING_EMPLOYEES where "+
                   "comp_id between "+ sID +" and "+eID+" or comp_id between "+ sID1 +" and "+eID1;

    }
    
    public String selectAllEmployeeForBranchWithStatusForDate(int BranchId,String d,int m,String y){
     
   
        String myDate = d+"-"+ConstantClass.MonthArray[m+1]+"-"+y;

         return "select A.EMP_ID,A.STATUS from "
                + "BRANCH_DAILY_ATTENDANCE A where "
                + "A.COMP_ID = '"+BranchId+"' and A.LOGDATE = '"+myDate+"'";
        
    
    }
  
    public String selectAllEmployeeForBranch(int id){
        return "select EMP_ID, NAME,COMP_ID  from EXISTING_EMPLOYEES where "+
                    "comp_id = "+ id ;
    }
    
    
    public String selectAllLeavesInfoPerBranch(int id){
        return "select A.COMP_ID,A.LEAVE_CODE,A.NO_OF_DAYS,A.FIELD1,B.LEAVE_DESCRIPTION from "
                + "COMP_LEAVE_TYPES A inner join STANDARD_LEAVE_TYPES B on A.LEAVE_CODE = B.LEAVE_CODE "
                + "where A.COMP_ID=B.COMP_ID and A.COMP_ID= "+id;
    //   return "select * frsom STANDARD_LEAVE_TYPES";
     }

      public String selectAllLeavesInfo(int sID,int eID){
          return "select A.COMP_ID,A.LEAVE_CODE,A.NO_OF_DAYS,A.FIELD1,B.LEAVE_DESCRIPTION from "
                + "COMP_LEAVE_TYPES A inner join STANDARD_LEAVE_TYPES B on A.LEAVE_CODE = B.LEAVE_CODE "
                + "where A.COMP_ID between " + sID +" and "+eID;
    
      }

//      public String addToDailyAttendance(String compID,String empID,String date,String status){
//          
//          return "insert into BRANCH_DAILY_ATTENDANCE (COMP_ID,EMP_ID,LOGDATE,STATUS) values"+
//             "(to_char('"+compID+"'), to_char('"+empID+"') ,to_char(to_date('"+date+"','DD-MM-RRRR'),'DD-MON-RR') ,to_char('"+status+"'))";
//                  
//                  
//      }
     
      public String selectAllLeavesInfoPerBranchPerEmp(String compID,String empID,String fromDate,String toDate){
          
          return "select LOGDATE,STATUS from BRANCH_DAILY_ATTENDANCE where"
                  + " COMP_ID = '"+compID+"' and EMP_ID='"+empID+"' and "
                  + " LOGDATE BETWEEN '"+fromDate+"' and '"+toDate+"' and STATUS in"
                  + " ("+ConstantClass.AnnualCode+","+ConstantClass.CompCode+","
                  + ConstantClass.MatCode+","+ConstantClass.PatCode+","+ConstantClass.SickCode+")";
          
      }
      
      public String getEmployeeGander(String compID,String empID){
          return "Select EMP_SEX from EXISTING_EMPLOYEES where COMP_ID='"+compID+"' and EMP_ID='"+empID+"'";
      } 
      
      
     public String selectFromDailyAttendance(){
          
          return "select STATUS from BRANCH_DAILY_ATTENDANCE";          
          
      }
    
     
     // to be used per branch
      public String selectAllEmployeeLeavesDetailsPerBranch(String BranchId){
    
           return "select C.COMP_ID , C.EMP_ID , C.NAME , B.LEAVE_DESCRIPTION"
                  + " from BRANCH_DAILY_ATTENDANCE A,STANDARD_LEAVE_TYPES B,"
                  + " EXISTING_EMPLOYEES C  where A.COMP_ID = B.COMP_ID and"
                  + " A.STATUS = B.LEAVE_CODE and A.COMP_ID = C.COMP_ID and"
                  + " A.EMP_ID = C.EMP_ID and A.COMP_ID= "+ BranchId;   
      }
     
      public String selectProcessedSummryDataPerEmployeePerThreeYears(String BranchId,String EmpId){
          
          return "select COMP_ID,EMP_ID,COMPASSIONATE_LEAVE"
                + ",MEDICAL_LEAVE,MATERNITY_LEAVE,PATERNITY_LEAVE,TRANSACTION_DATE,"
                + "TRANSACTION_MONTH,TRANSACTION_YEAR from BIO_PUNCHING_DETAILS_DUMMY"
                + " where COMP_ID= "+ BranchId+ "and EMP_ID = "+EmpId;
      }
      
      public String selectProcessedSummryDataPerEmployeePerOneYears(String BranchId,String EmpId){
        return "select ABSENT,ANNUAL_LEAVE from BIO_PUNCHING_DETAILS_DUMMY"
                + " where COMP_ID= "+ BranchId+ "and EMP_ID = "+EmpId;
      }
      
      public String selectUnProcessedSummryDataPerEmployee(String BranchId,String EmpId,String LeaveId,String startDate,String endDate){
        return "select count(STATUS) from BRANCH_DAILY_ATTENDANCE where COMP_ID= "+ BranchId
          + " and EMP_ID = "+EmpId +" and STATUS = "+ LeaveId+" and LOGDATE between '"+startDate+"' and '"+endDate+"'";
      }
      
      
    public String addIntoProcessedSummryDataPerEmployee(){
        String date = "11-11-2017";
        
        return "insert into BIO_PUNCHING_DETAILS_DUMMY (COMP_ID,EMP_ID,ABSENT,"
                + "ANNUAL_LEAVE,COMPASSIONATE_LEAVE,MEDICAL_LEAVE,"
                + "MATERNITY_LEAVE,PATERNITY_LEAVE,TRANSACTION_DATE,"
                + "TRANSACTION_MONTH,TRANSACTION_YEAR)"
                + " values (to_char('26'),to_char('001'),6,7,8,9,10,11,"
                + "to_char(to_date('"+date+"','DD-MM-RRRR'),'DD-MON-RR'),"
                + "'Febaruary','2017')";
    }
      
    public String getReportDataEmpWiseDaily(String BranchId,String EmpId,String date){
 
         return "select STATUS from BRANCH_DAILY_ATTENDANCE where COMP_ID='"
                +BranchId+"' and EMP_ID='"+EmpId+"' and LOGDATE='"+date+"'";
    } 
     
    public String getReportDataEmpWiseDailyForLeave(String BranchId,String EmpId,String date){
 
         return "select STATUS from BRANCH_DAILY_ATTENDANCE where COMP_ID='"
                +BranchId+"' and EMP_ID='"+EmpId+"' and LOGDATE=to_char(to_date('"+date+"','DD-MM-RRRR'),'DD-MON-RR')";
    } 
    
    public String getReportDataEmpWiseMonthly(String BranchId,String EmpId
            ,String FirstDate,String SecDate ){
 
         return "select LOGDATE , STATUS from BRANCH_DAILY_ATTENDANCE where COMP_ID='"
                +BranchId+"' and EMP_ID='"+EmpId+"' and LOGDATE between '"+FirstDate+"' and '"+SecDate+"'";
    }
    
     public String getConsalidateReport(String BranchId,String EmpId
            ,String FirstDate,String SecDate ){
 
         return "select LOGDATE , STATUS from BRANCH_DAILY_ATTENDANCE where COMP_ID='"
                +BranchId+"' and EMP_ID='"+EmpId+"' and LOGDATE between '"
                 +FirstDate+"' and '"+SecDate+"' order by LOGDATE";
    }
    
    
    public String getReportDataEmpWiseHistory(String BranchId,String EmpId){
 
         return "select LOGDATE , STATUS from BRANCH_DAILY_ATTENDANCE where COMP_ID='"
                +BranchId+"' and EMP_ID='"+EmpId+"' ";
    }
     
    public String getReportDataEmpWiseConsMonthly(String BranchId,String EmpId
            ,String FirstDate,String SecDate ){
 
         return "select STATUS, count (STATUS) from BRANCH_DAILY_ATTENDANCE where COMP_ID='"
                +BranchId+"' and EMP_ID='"+EmpId+"' and LOGDATE between '"+FirstDate+"' and '"+SecDate+"' group by STATUS";
    }
    
    
    public String getReportDataBranchWiseDaily(String BranchId ,String date,String Status ){

        if(Status.equals("-3"))
           return "select A.COMP_ID,A.EMP_ID,B.NAME,A.STATUS,A.LOGDATE from BRANCH_DAILY_ATTENDANCE A,"
              + "EXISTING_EMPLOYEES B where A.COMP_ID=B.COMP_ID and A.EMP_ID = B.EMP_ID"
              + " and B.COMP_ID='"+BranchId+"' and A.LOGDATE='"+date+"'";  
        
       return "select A.COMP_ID,A.EMP_ID,B.NAME,A.STATUS,A.LOGDATE from BRANCH_DAILY_ATTENDANCE A,"
              + "EXISTING_EMPLOYEES B where A.COMP_ID=B.COMP_ID and A.EMP_ID = B.EMP_ID"
              + " and B.COMP_ID='"+BranchId+"' and A.STATUS='"+Status+"' and A.LOGDATE='"+date+"'";
    }
    
    public String getReportDataBranchWiseMonthly(String BranchId ,String Status
            ,String FirstDate,String SecDate){
         
        if(Status.equals("-3"))
           return "select A.COMP_ID,A.EMP_ID,B.NAME,A.STATUS,A.LOGDATE from BRANCH_DAILY_ATTENDANCE A,"
              + "EXISTING_EMPLOYEES B where A.COMP_ID=B.COMP_ID and A.EMP_ID = B.EMP_ID"
              + " and B.COMP_ID='"+BranchId+"' and"
              + " A.LOGDATE between '"+FirstDate+"' and '"+SecDate+"' order by A.EMP_ID,A.LOGDATE";
            
        
           return "select A.COMP_ID,A.EMP_ID,B.NAME,A.STATUS,A.LOGDATE from BRANCH_DAILY_ATTENDANCE A,"
              + "EXISTING_EMPLOYEES B where A.COMP_ID=B.COMP_ID and A.EMP_ID = B.EMP_ID"
              + " and B.COMP_ID='"+BranchId+"' and A.STATUS='"+Status+"' and"
              + " A.LOGDATE between '"+FirstDate+"' and '"+SecDate+"' order by A.EMP_ID,A.LOGDATE";
    }
    
    public String getReportDataBranchWiseConsMonthly(String BranchId,String Date ){
 
         return "select STATUS, count (STATUS) from BRANCH_DAILY_ATTENDANCE where COMP_ID='"
                +BranchId+"' and LOGDATE = '"+Date+"' group by STATUS";
    }
    
    
    public String selectAllEmployeeLeavesDetails(){
    
          // this one is the correct one ; please check the Data in the DB 
          
           return "select C.COMP_ID , C.EMP_ID , C.NAME , B.LEAVE_DESCRIPTION "
                  + " from BRANCH_DAILY_ATTENDANCE A,STANDARD_LEAVE_TYPES B,"
                  + " EXISTING_EMPLOYEES C  where A.COMP_ID = B.COMP_ID and"
                  + " A.STATUS = B.LEAVE_CODE and A.COMP_ID = C.COMP_ID and"
                  + " A.EMP_ID = C.EMP_ID";        

      }

    public String selectStatusForSpecificEmpandDate(String BranchId,String EmpId
            ,String Date){
        return "select STATUS from BRANCH_DAILY_ATTENDANCE where COMP_ID='"+BranchId+"' and"
                + " EMP_ID='"+EmpId+"' and LOGDATE = '"+Date+"'";
    }

    public String addtStatusForSpecificEmpandDate(String BranchId,String EmpId
            ,String Date,String Status){
        
        return "insert into BRANCH_DAILY_ATTENDANCE values ('"+BranchId+"','"
                + EmpId+"','"+Date+"','"+Status+"')";
    }

    
    public String updateStatusForSpecificEmpandDate(String BranchId,String EmpId
            ,String Date,String Status){
        
        return "UPDATE BRANCH_DAILY_ATTENDANCE SET STATUS='"+Status+"' WHERE comp_id='"+BranchId+
            "' and EMP_ID='"+EmpId+"' and LOGDATE = '"+Date+"'";
    }
   
    public String updateStatusForSpecificEmpandDateForLeave(String BranchId,String EmpId
            ,String Date,String Status){
        
        return "UPDATE BRANCH_DAILY_ATTENDANCE SET STATUS='"+Status+"' WHERE comp_id='"+BranchId+
            "' and EMP_ID='"+EmpId+"' and LOGDATE = to_char(to_date('"+Date+"','DD-MM-RRRR'),'DD-MON-RR')";
    } 
    
    
     public String addToLeaveTracker(String CompID,String EmpID,String DateOfJoining,
          String NAL,String NCL,String NML,String NMATL,String NPATL,String TranDate){        
       
   /*      return "insert into ATTEN_EMP_DETAILS (COMP_ID,EMP_ID,JOIN_DATE,"
                + "ANNUAL_LEAVE,COMPASSIONATE_LEAVE,MEDICAL_LEAVE,MATERNITY_LEAVE,"
                + "PATERNITY_LEAVE,POSTED_DATE) values"
                +"(to_char('"+CompID+"'),to_char('"+EmpID+"'),"+DateOfJoining+","
                 +NAL+","+NCL+","+NML+","+NMATL+","+NPATL+",to_char(to_date('"+TranDate+"','DD-MM-RRRR'),'DD-MON-RR'))";  
                 * 
         
           return "insert into ATTEN_EMP_DETAILS (COMP_ID,EMP_ID,JOIN_DATE,"
                + "ANNUAL_LEAVE,COMPASSIONATE_LEAVE,MEDICAL_LEAVE,MATERNITY_LEAVE,"
                + "PATERNITY_LEAVE) values"
                +"(to_char('"+CompID+"'),to_char('"+EmpID+"'),"+DateOfJoining+","
                 +NAL+","+NCL+","+NML+","+NMATL+","+NPATL+")";    */
         
         
           return "insert into ATTEN_EMP_DETAILS (COMP_ID,EMP_ID,"
                + "ANNUAL_LEAVE,COMPASSIONATE_LEAVE,MEDICAL_LEAVE,MATERNITY_LEAVE,"
                + "PATERNITY_LEAVE,POSTED_DATE,JOIN_DATE) values"
                +"(to_char('"+CompID+"'),to_char('"+EmpID+"'),"
                +NAL+","+NCL+","+NML+","+NMATL+","+NPATL+","
                + "to_char(to_date('"+TranDate+"','DD-MM-RRRR'),'DD-MON-RR'),"
                + "to_char(to_date('"+DateOfJoining+"','DD-MM-RRRR'),'DD-MON-RR'))";      
         
    }
    
   public String updateLeaveTracker(String CompID,String EmpID,
          String NAL,String NCL,String NML,String NMATL,String NPATL,String TranDate){  
         
       return "update ATTEN_EMP_DETAILS set ANNUAL_LEAVE='"+NAL+"',COMPASSIONATE_LEAVE='"+NCL+
               "',MEDICAL_LEAVE='"+NML+"',MATERNITY_LEAVE='"+NMATL+"',PATERNITY_LEAVE='"+NPATL+"'"
             + " where COMP_ID="+CompID+" and EMP_ID="+EmpID; 
       
       
       } 
     
     
    public String getLeaveTrackerData(String CompID,String EmpID ){
        return "select ANNUAL_LEAVE,COMPASSIONATE_LEAVE,MEDICAL_LEAVE,MATERNITY_LEAVE,"
                + "PATERNITY_LEAVE from ATTEN_EMP_DETAILS where COMP_ID='"+CompID+"' and EMP_ID='"+EmpID+"'";
    } 
     
      public String getDOJ(String CompID,String EmpID){    
          return "select EFFECTIVE_DATE from EXISTING_EMPLOYEES where "
                  + "COMP_ID='"+CompID+"' and EMP_ID='"+EmpID+"'";
      }

     public String getNameEmployee(String CompID,String EmpID){    
          return "select Name from EXISTING_EMPLOYEES where "
                  + "COMP_ID='"+CompID+"' and EMP_ID='"+EmpID+"'";
      }
    
    
}