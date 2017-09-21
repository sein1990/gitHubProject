package JavaCodePackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates

 * and open the template in the editor.
 */


 
import Models.Admin;
import Models.Branch;
import Models.Employee;
import Models.Leave;
import Models.LeaveInfo;

import JavaCodePackage.conectionClass;
import Models.SummryLeaveDataClass;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.jca.GetInstance;

/**
 *
 * @author user
 */
public class InternalMainClass {
    
    static Vector<Admin> UserVec;
    public static HashMap<Integer,Branch> BranchHashmap;
    static Vector<Employee> EmployeeVec;
    static Vector<Leave> LeaveVec;
    static Vector<LeaveInfo> LeaveInfoVec;
    
     static Vector<String> DailyDetails;
    
    
    conectionClass c ;
    QuerisClass query;
 
   
    private HashMap<Integer,Branch> prepareBranchVec(){
       
        String q =  query.getBranchesQuery(ConstantClass.firstIDBranch, 
                ConstantClass.lastIDBranch,ConstantClass.firstIDBranch1, 
                ConstantClass.lastIDBranch1); 
        
        
        HashMap<Integer,Branch> vec = new HashMap<Integer,Branch>();
        try {
            ResultSet res = c.excuteQuery(q);
            while(res.next()) {
               String branchName = res.getString(2);
               branchName = branchName.substring(branchName.indexOf("-")+2, branchName.length());
               vec.put(res.getInt(1), new Branch(res.getString(1),branchName , res.getInt(3)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(InternalMainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.closeConnection();
        return vec;
    }
    
    public String getSpecificBranch(int key){
        return BranchHashmap.get(key).getBranchName();
    }

    public int getSpecificOpenedDays(int key){
        return BranchHashmap.get(key).getNumberOfOpenDays();
    }
    
    
    public static HashMap<Integer, Branch> getBranchHashmap() {
        return BranchHashmap;
    }
    
     public static Vector<String> getDailyDetails() {
        return DailyDetails;
    }
    
    
    public void prepareDirectoriesList(int AdminBranchID){
        File f;
        if(AdminBranchID == 0){
            
            for (Map.Entry<Integer, Branch> entry : BranchHashmap.entrySet()) {
                Integer key = entry.getKey();
                Branch value = entry.getValue();
                f = new File(ConstantClass.filePath+"//"+value.getBranchName());
                f.mkdir();
                f = new File(ConstantClass.filePath+"//"+value.getBranchName()+"//PDF");
                f.mkdir();
                f = new File(ConstantClass.filePath+"//"+value.getBranchName()+"//IMG");
                f.mkdir();
                f = new File(ConstantClass.filePath+"//"+value.getBranchName()+"//TXT");
                f.mkdir();
                
            }
        
        }
        else {
          // f = new File(ConstantClass.filePath+"//"+getSpecificBranch(AdminBranchID)); 
          f = new File(ConstantClass.filePath+"//"+getSpecificBranch(AdminBranchID));   
          f.mkdir();
                f = new File(ConstantClass.filePath+"//"+getSpecificBranch(AdminBranchID)+"//PDF");
                f.mkdir();
                f = new File(ConstantClass.filePath+"//"+getSpecificBranch(AdminBranchID)+"//IMG");
                f.mkdir();
                f = new File(ConstantClass.filePath+"//"+getSpecificBranch(AdminBranchID)+"//TXT");
                f.mkdir();
          
          
          
         }
        
        
    }
    
    public void prepareUsersData(){
       UserVec = new Vector<Admin>();
       conectionClass c = new conectionClass();
        try {
            UserVec.add(new Admin("Mo1", "12345", 0));
            ResultSet res = c.excuteQuery(query.getAdminsQuery());
            while(res.next()) {
                
                UserVec.add(new Admin(res.getString(2),res.getString(3) , res.getInt(1)));
            }       
        } catch (SQLException ex) {
            Logger.getLogger(InternalMainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.closeConnection();
       
        
    }
    
    public void prepareBranchesData(){
        String q =  query.getBranchesQuery(ConstantClass.firstIDBranch, 
                ConstantClass.lastIDBranch,ConstantClass.firstIDBranch1, 
                ConstantClass.lastIDBranch1); 
        
        BranchHashmap = new HashMap<Integer,Branch>();
        try {
            ResultSet res = c.excuteQuery(q);
            while(res.next()) {
               String branchName = res.getString(2);
               branchName = branchName.substring(branchName.indexOf("-")+2, branchName.length());
               BranchHashmap.put(res.getInt(1), new Branch(res.getString(1),branchName , res.getInt(3)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(InternalMainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.closeConnection();
        
    }
    
    public void prepareLeavesData(String BranchId,String EmpId){
        
        conectionClass c = new conectionClass();
         DailyDetails = new Vector<String>();
        if(!BranchId.equals("0")){
           
            query = new QuerisClass();
            String q = query.selectApprovalMonthFromBioPuchingDetailsMonthlyQuery(BranchId,EmpId);
            
            try {

                ResultSet res = c.excuteQuery(q);
                if(res.next()) {
                    String approvedMonthStr = res.getString(1);
                    String approvedYearStr  = res.getString(2);
                    int approvedMonthInt = ConstantClass.getIndexOfMonth(approvedMonthStr);
                    int approvedYearInt = Integer.parseInt(approvedYearStr);
//                    if(approvedMonthInt == 12){
//                        approvedMonthInt = 1;
//                        approvedYearInt++;
//                    }
//                    
                    String firstDate = getFirstDayOfMonth(approvedMonthInt,approvedYearInt);
                    String lastDate = getLastDayOfMonth(approvedMonthInt,approvedYearInt);
                    
                    DailyDetails = new Vector<String>();
     
                    q = query.selectAllLeavesInfoPerBranchPerEmp(BranchId,EmpId,firstDate,lastDate); 
                    res = c.excuteQuery(q);
                    while(res.next()) {
                        DailyDetails.add(res.getString(1)+"@"+res.getString(2));
                        }       
                } 
            } catch (SQLException ex) {
                Logger.getLogger(InternalMainClass.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
        c.closeConnection();
      
    }
   
    private String getDataFormated(String date){
        String[] splits = date.split("-");
        return splits[2]+"-"+ConstantClass.MonthArray[Integer.parseInt(splits[1])]+"-"+splits[0];
    }
    
    private String getFirstDayOfMonth(int Month,int Year){
      
        return "1-"+ConstantClass.MonthArray[Month]+"-"+Year;
    }
    
    private String getLastDayOfMonth(int Month,int Year){
        if(Month == 2 && Year%4==0)
            return "29-"+ConstantClass.MonthArray[2]+"-"+Year;
        
        return ConstantClass.NumberOfDaysInMonthArray[Month]+"-"+ConstantClass.MonthArray[Month]+"-"+Year;
    }

    
    
     public void prepareLeavesInfoData(int AdminID){
        
        String q =  query.selectAllLeavesInfoPerBranch(AdminID); 
        
        if(AdminID == 0)
            q = query.selectAllLeavesInfoPerBranch(ConstantClass.firstIDBranch);
        
        
       LeaveInfoVec = new Vector<LeaveInfo>();
       conectionClass c = new conectionClass();
       try {
           
            ResultSet res = c.excuteQuery(q);
            while(res.next()) {
                LeaveInfoVec.add(new LeaveInfo(Integer.parseInt(res.getString(1))
                ,res.getString(2),Integer.parseInt(res.getString(3))
                ,Integer.parseInt(res.getString(4)),res.getString(5)));
                
            }       
        } catch (SQLException ex) {
            Logger.getLogger(InternalMainClass.class.getName()).log(Level.SEVERE, null, ex);
        } 
        c.closeConnection();
        
    }
    
     private int[] getProcessedData(String branchID,String empID){
         
        int[] array = {0,0,0,0,0,0};
        String COMP_ID = "";
        String EMP_ID = "";
        String TRANSACTION_DATE = "";
        String TRANSACTION_MONTH = "";
        String TRANSACTION_YEAR = "";
        
        
        QuerisClass query = new QuerisClass();
        String q = query.selectProcessedSummryDataPerEmployeePerThreeYears(branchID, empID);
        String q1 = query.selectProcessedSummryDataPerEmployeePerOneYears(branchID, empID);
        conectionClass c = new conectionClass();
         try {
            ResultSet res = c.excuteQuery(q);
            while(res.next()) {
            
               COMP_ID = res.getString(1);
               EMP_ID = res.getString(2);

               array[2] = Integer.parseInt(res.getString(4));
               array[3] = Integer.parseInt(res.getString(5));
               array[4] = Integer.parseInt(res.getString(6));
               array[5] = Integer.parseInt(res.getString(3));
               TRANSACTION_DATE = res.getString(9);
               TRANSACTION_MONTH = res.getString(10);
               TRANSACTION_YEAR = res.getString(11);
           }
            ResultSet res1 = c.excuteQuery(q1);
            while(res1.next()) {
                array[0] = Integer.parseInt(res1.getString(3));
                array[1] = Integer.parseInt(res1.getString(4));
            }
        
            
        }catch (Exception ex) {
            Logger.getLogger(InternalMainClass.class.getName()).log(Level.SEVERE, null, ex);
            return array;
        }
//       
        return array; 
     }
     
     
     private int getUnProcessedDataPartally(String branchID,String empID,String leaveID,String SD,String ED){
         
          String q = query.selectUnProcessedSummryDataPerEmployee(branchID, empID,leaveID,SD,ED);
          QuerisClass query = new QuerisClass();
          int value = 0;
        

          conectionClass c = new conectionClass();
            try {
                ResultSet res = c.excuteQuery(q);
                if(res != null){
                    while(res.next()) {
                    
                        value = (int) Integer.parseInt(res.getString(1));
                    }
                }
            }catch (SQLException ex) {
                //Logger.getLogger(InternalMainClass.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
           
            return value;
     }
     
     
      private int[] getUnProcessedData(String branchID,String empID,String SD,String SD3,String ED){
         
        int[] array  = {0,0,0,0,0,0};
        
        
        array[0] = getUnProcessedDataPartally(branchID,empID,ConstantClass.AbsentCode,SD,ED);
        array[1] = getUnProcessedDataPartally(branchID,empID,ConstantClass.AnnualCode,SD,ED);
        array[2] = getUnProcessedDataPartally(branchID,empID,ConstantClass.SickCode,SD,ED);
        array[3] = getUnProcessedDataPartally(branchID,empID,ConstantClass.MatCode,SD3,ED);
        array[4] = getUnProcessedDataPartally(branchID,empID,ConstantClass.PatCode,SD3,ED);
        array[5] = getUnProcessedDataPartally(branchID,empID,ConstantClass.CompCode,SD,ED);
         
        return array;
         
     }
     private String getDateOfJoining(String compID,String empID){
         String returnVal = "null";  
         QuerisClass q = new QuerisClass(); 
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
    public int[] getLeaveData(String branchID,String empID){
        c = new conectionClass();
        String DOJ = getDateOfJoining(branchID,empID);
        DOJ = DOJ.substring(0,DOJ.indexOf(" "));
        String DOJarray[] = DOJ.split("-");
        
        int dateOfJoining = Integer.parseInt(DOJarray[2]);
        int monthOfJoining = Integer.parseInt(DOJarray[1])-1;
        int yearOfJoining = Integer.parseInt(DOJarray[0]);
        
        Calendar cal = Calendar.getInstance();
        int todayDate = cal.get(Calendar.DATE);
        int todayMonth = cal.get(Calendar.MONTH);
        int todayYear = cal.get(Calendar.YEAR);
        
        int changedYearOne = todayYear;
        int changedYearThree = todayYear-2;
        
        if(todayMonth < monthOfJoining || (todayMonth == monthOfJoining && todayDate > todayYear)){
            changedYearOne --;
            changedYearThree--;
            
        }
            
    
        String startDateOne = getDataFormated(Integer.toString(dateOfJoining),
                Integer.toString(monthOfJoining),Integer.toString(changedYearOne));
        
        String startDateThree = getDataFormated(Integer.toString(dateOfJoining),
                Integer.toString(monthOfJoining),Integer.toString(changedYearThree));
        
        String endDate = getDataFormated(Integer.toString(todayDate),
                Integer.toString(todayMonth),Integer.toString(todayYear));
        
        
        
        
        int[] array = getUnProcessedData(branchID,empID,startDateOne,startDateThree,endDate);
        c.closeConnection();
        return array;
        
        
    } 
      
    public String getEmployeeGAander(String branchID,String empID){
        String g = "M";
        try {
            c = new conectionClass();
            
            String q =  query.getEmployeeGander(branchID, empID);
            ResultSet res = c.excuteQuery(q);
               while(res.next()) {
                  g =  res.getString(1);
                   
               }    
            
            c.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(InternalMainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return g;
    } 
    
    public SummryLeaveDataClass getEmployeeLeave(String branchID,String empID){

  
       int[] unProcessedData = getLeaveData(branchID,empID); 
       SummryLeaveDataClass summryData = new SummryLeaveDataClass(branchID, 
            empID, unProcessedData);
         
        
        return summryData;
    }
    
    
    public void prepareEmployeeData(int AdminID){
        
        String q =  query.selectAllEmployeeForBranch(AdminID); 
        
        if(AdminID == 0)
            q = query.selectAllEmployee(ConstantClass.firstIDBranch, 
                    ConstantClass.lastIDBranch,ConstantClass.firstIDBranch1,ConstantClass.lastIDBranch1);
        
        
       EmployeeVec = new Vector<Employee>();
       conectionClass c = new conectionClass();
       try {
           
            ResultSet res = c.excuteQuery(q);
            while(res.next()) {
                EmployeeVec.add(new Employee(res.getString(1),res.getString(2),Integer.parseInt(res.getString(3))));
                
            }       
        } catch (SQLException ex) {
            Logger.getLogger(InternalMainClass.class.getName()).log(Level.SEVERE, null, ex);
        } 
        c.closeConnection();
//        
    }
    
    
    public InternalMainClass(){
        c = new conectionClass();
        query = new QuerisClass();
     
    }
    
    
    
    
    
    
    public void internalMain(){
      
        
   //     UserVec = prepareUserVec();
       
             
      
                        
    //    BranchHashmap =  prepareBranchVec();      
   //     EmployeeVec=prepareEmployeeVec();
    //    LeaveVec=prepareLeaveVec();
//                
           

//        File f = new File(ConstantClass.filePath+"//Mwanza");
//        f.mkdir();
//
//        f = new File(ConstantClass.filePath+"//tabora"); 
//        f.mkdir();
         
//        EmployeeVec = filter.getEmployeeVec();
//        DepartmentVec = filter.getDepartmentVec();
         
    }

    public  HashMap<Integer,Branch>  getBranchVec() {
        
        return BranchHashmap;
    
    }

    public Vector<Admin> getUserVec() {
        return UserVec;
    }

    public Vector<Employee> getEmployeeVec() {
        return EmployeeVec;
    }

    public static Vector<Leave> getLeaveVec() {
        return LeaveVec;
    }

    public static Vector<LeaveInfo> getLeaveInfoVec() {
        return LeaveInfoVec;
    }

    public static void setLeaveInfoVec(Vector<LeaveInfo> LeaveInfoVec) {
        InternalMainClass.LeaveInfoVec = LeaveInfoVec;
    }

    
    
  
     public Vector<Employee> prepareEmployeeVecPerUnit(int branchID){
        Vector<Employee> vec = new Vector<Employee>();
        for (Employee employee : EmployeeVec) {
            if(employee.getEmployeeBranchID() == branchID){
                vec.add(employee);
            }
        }
        return vec;
    }
    
      private String getDataFormated(String date,String month,String year){
           
        return date+"-"+ConstantClass.MonthArray[Integer.parseInt(month)+1]+"-"+year;
    }
     
            
}
