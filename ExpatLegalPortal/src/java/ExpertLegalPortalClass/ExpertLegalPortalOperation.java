/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpertLegalPortalClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Sein 90
 */
public class ExpertLegalPortalOperation {
    QueryClass objQuery=new QueryClass();
     Connection con=dbconnection.getConnection();
     public String getNewId(String Query,String Code){
         String dbID="";
       
         try {
             int counter = 0;
             PreparedStatement ps=null;
             ResultSet rs=null;
             ps=con.prepareStatement(Query);
             rs=ps.executeQuery();
             if(rs.next())
             { 
                 dbID=rs.getString(1);
             }
             if(dbID==null){
                 //  dbID="A1-1";
                 dbID=Code+"1";
             }
             else{
                 counter = Integer.parseInt(dbID);
                 counter++;
                 dbID=Code+counter;
             }       
             
               String query2 =objQuery.UpdateAttachment();
                        PreparedStatement preparedStmt = con.prepareStatement(query2);
                        preparedStmt.setString(1, dbID);                     
                        preparedStmt.executeUpdate();
             
         } catch (SQLException ex) {
             Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
         }
         return dbID;
     }
     
     private void updateId(String Query,int counter){
         try {
             PreparedStatement preparedStmt = con.prepareStatement(Query);
             preparedStmt.setInt(1, counter);
             preparedStmt.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     public String a1_notReturnFromLeave(String date, String reason, String name,String unity, String remarks, String recovered, String salaryDeposit, String totalAmount, String fileClosed, String empID,String dbID)
     {
      if(dbID.contains("null"))
          
            {
                
         
               dbID= getNewId(objQuery.selectHighestValue("a1"),"A1-");   
                String arrayDate[]= date.split("-");
                String newDateString = arrayDate[2]+"-"+monthArray[Integer.parseInt(arrayDate[1])]+"-"+arrayDate[0];                 
                
              try {
                    PreparedStatement ps2=null;
                    String sql = objQuery.InsertA1NonReturnedFromLeave();
                    ps2=con.prepareStatement(sql);
                    ps2.setString(1, dbID);
                    ps2.setString(2, newDateString);
                    ps2.setString(3, reason);
                    ps2.setString(4, name);
                    ps2.setString(5, unity);
                    ps2.setString(6, remarks);
                    ps2.setString(7, recovered); 
                    ps2.setString(8, salaryDeposit);
                    ps2.setString(9, totalAmount);
                    ps2.setString(10, fileClosed);
                    ps2.setString(11, empID);
                    ps2.executeUpdate();
                    String query2 =objQuery.UpdateA1HighestValue();
                    String dbIDM = dbID.substring(dbID.indexOf("-")+1);
                    updateId(query2,Integer.parseInt(dbIDM));
                     } catch (SQLException ex) {
                     Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
                 }
         }else{
             try {
                 String query2 = objQuery.UpdateA1NotReturnedFromLeave(dbID);
                        PreparedStatement preparedStmt = con.prepareStatement(query2);  
                        preparedStmt.setString(1, reason);
                        preparedStmt.setString(2, remarks);
                        preparedStmt.setString(3, recovered);
                        preparedStmt.setString(4, salaryDeposit);
                        preparedStmt.setString(5, totalAmount);
                        preparedStmt.setString(6, fileClosed);
                        preparedStmt.executeUpdate();
             } catch (SQLException ex) {
                 Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         return dbID;
         } 
 
      public String a2_notReturnFromLeave(String date, String reason, String name,String unity, String remarks, String recovered, String salaryDeposit, String totalAmount, String fileClosed, String empID,String dbID)
     {
          
     if(dbID.contains("null"))
            {
                dbID= getNewId(objQuery.selectHighestValue("a2"),"A2-"); 
              try {
                    String arrayDate[]= date.split("-");
                    String newDateString = arrayDate[2]+"-"+monthArray[Integer.parseInt(arrayDate[1])]+"-"+arrayDate[0];                 
                    String sql = objQuery.InsertA2WithCompanyProperty();
                    PreparedStatement ps2=null;
                    ps2=con.prepareStatement(sql);
                    ps2.setString(1, dbID);
                    ps2.setString(2, newDateString);
                    ps2.setString(3, reason);
                    ps2.setString(4, name);
                    ps2.setString(5, unity);
                    ps2.setString(6, remarks);
                    ps2.setString(7, recovered); 
                    ps2.setString(8, salaryDeposit);
                    ps2.setString(9, totalAmount);
                    ps2.setString(10, fileClosed);
                    ps2.setString(11, empID);
                    ps2.executeUpdate();
                    String query2 = objQuery.UpdateA2HighestValue();
                    String dbIDM = dbID.substring(dbID.indexOf("-")+1);
                    updateId(query2,Integer.parseInt(dbIDM));
                     } catch (SQLException ex) {
                         Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
                     }
         }else{
             try {
                    String query2 = objQuery.UpdateA2WithCompanyProperty(dbID);
                    PreparedStatement preparedStmt = con.prepareStatement(query2);
                    preparedStmt.setString(1, reason);
                    preparedStmt.setString(2, remarks);
                    preparedStmt.setString(3, recovered);
                    preparedStmt.setString(4, salaryDeposit);
                    preparedStmt.setString(5, totalAmount);
                    preparedStmt.setString(6, fileClosed);
                    preparedStmt.executeUpdate();
             } catch (SQLException ex) {
                 Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         return dbID;
         } 
      String[] monthArray={"","JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG", "SEP", "OCT", "NOV","DEC"};
       public String a3_notReturnFromLeave(String date, String reason, String name,String unity, String remarks, String recovered, String salaryDeposit, String totalAmount, String fileClosed, String empID,String dbID)
     {
    
            if(dbID.contains("null"))
            {
                   dbID= getNewId(objQuery.selectHighestValue("a3"),"A3-");   
              try {                  
                String arrayDate[]= date.split("-");
                String newDateString = arrayDate[2]+"-"+monthArray[Integer.parseInt(arrayDate[1])]+"-"+arrayDate[0];                 
                PreparedStatement ps2=null;  
                String sql =objQuery.InsertA3DamageOrLoss();               
                ps2=con.prepareStatement(sql);
                ps2.setString(1, dbID);
                ps2.setString(2, newDateString);//"to_char(to_date('"+newDateString+"','DD-MM-RRRR'),'DD-MON-RR')");
                ps2.setString(3, reason);
                ps2.setString(4, name);
                ps2.setString(5, unity);
                ps2.setString(6, remarks);
                ps2.setString(7, recovered); 
                ps2.setString(8, salaryDeposit);
                ps2.setString(9, totalAmount);
                ps2.setString(10, fileClosed);
                ps2.setString(11, empID);
                ps2.executeUpdate();
                String query2 = objQuery.UpdateA3HighestValue();
                String dbIDM = dbID.substring(dbID.indexOf("-")+1);
                updateId(query2,Integer.parseInt(dbIDM));
                 } catch (SQLException ex) {
                     String s=ex.getMessage();
                     Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
                 }
         }else{
             try {
                 String query2 = objQuery.UpdateA3DamageOrLoss(dbID);
                        PreparedStatement preparedStmt = con.prepareStatement(query2);
                        preparedStmt.setString(1, reason);
                        preparedStmt.setString(2, name);
                        preparedStmt.setString(3, unity);
                        preparedStmt.setString(4, remarks);
                        preparedStmt.setString(5, recovered);
                        preparedStmt.setString(6, salaryDeposit);
                        preparedStmt.setString(7, totalAmount);
                        preparedStmt.setString(8, fileClosed);
                        preparedStmt.setString(9, empID);
                        preparedStmt.executeUpdate();
             } catch (SQLException ex) {
                 Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         return dbID;
         } 
         public String Dis_notReturnFromLeave(String date,String reason,String name,String unity,String remarks,String investigation,String actionTaken,String actionToBeTaken,String peopleInvolved,String empID,String dbID)
     {  
         if(dbID.contains("null"))
            {
              try {
                    dbID= getNewId(objQuery.selectHighestValue("dis"),"A4-"); 
                    String arrayDate[]= date.split("-");
                    String newDateString = arrayDate[2]+"-"+monthArray[Integer.parseInt(arrayDate[1])]+"-"+arrayDate[0];    
                    PreparedStatement ps2=null;                
                    String sql = objQuery.InsertDisNotReturnFromLeave();
                    ps2=con.prepareStatement(sql);
                    ps2.setString(1, dbID);
                    ps2.setString(2, newDateString);
                    ps2.setString(3, reason);
                    ps2.setString(4, name);
                    ps2.setString(5, unity);
                    ps2.setString(6, remarks);
                    ps2.setString(7, investigation); 
                    ps2.setString(8, actionTaken);
                    ps2.setString(9, actionToBeTaken);
                    ps2.setString(10, peopleInvolved);
                    ps2.setString(11, empID);
                    ps2.executeUpdate();
                    String query2 = objQuery.UpdateDisNotReturnFromLeaveHighestValue();
                    String dbIDM = dbID.substring(dbID.indexOf("-")+1);
                    updateId(query2,Integer.parseInt(dbIDM)); 
                 } catch (SQLException ex) {
                     Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
                 }
         }else{
             try {
                 String query2 = objQuery.UpdateDisNotReturnFromLeave(dbID);
                 PreparedStatement preparedStmt = con.prepareStatement(query2);
                 preparedStmt.setString(1, reason);
                 preparedStmt.setString(2, name);
                 preparedStmt.setString(3, unity);
                 preparedStmt.setString(4, remarks);
                 preparedStmt.setString(5, investigation);
                 preparedStmt.setString(6, actionTaken);
                 preparedStmt.setString(7, actionToBeTaken);
                 preparedStmt.setString(8, peopleInvolved);
                 preparedStmt.setString(9, empID);
                 preparedStmt.executeUpdate();
             } catch (SQLException ex) {
                 Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         return dbID;  
         } 
           public String empPassedAway(String date, String name, String unity,String remarks, String actionTaken, String deathReason, String empID, String dbID)
     {
         
        if(dbID.contains("null"))
            {
              try {
                    dbID= getNewId(objQuery.selectHighestValue("emp_passed_away"),"A6-");   
                    String arrayDate[]= date.split("-");
                    String newDateString = arrayDate[2]+"-"+monthArray[Integer.parseInt(arrayDate[1])]+"-"+arrayDate[0];                 
                    PreparedStatement ps2=null;
                    String sql = objQuery.InsertEmpPassedAway();
                    ps2=con.prepareStatement(sql);
                    ps2.setString(1, dbID);
                    ps2.setString(2, newDateString);
                    ps2.setString(3, name);
                    ps2.setString(4, unity);
                    ps2.setString(5, remarks);
                    ps2.setString(6, actionTaken);
                    ps2.setString(7, deathReason);
                    ps2.setString(8, empID);
                    ps2.executeUpdate();
                    String query2 = objQuery.UpdateInsertEmpPassedAwayHighestValue();
                    String dbIDM = dbID.substring(dbID.indexOf("-")+1);
                    updateId(query2,Integer.parseInt(dbIDM)); 
                 } catch (SQLException ex) {
                     Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
                 }
         }else{
             try {
                 String query2 =objQuery.UpdateEmpPassedAway(dbID);
                 PreparedStatement preparedStmt = con.prepareStatement(query2);
                 preparedStmt.setString(1, remarks);
                 preparedStmt.setString(2, actionTaken);
                 preparedStmt.setString(3, deathReason);
                 preparedStmt.setString(4, empID);
                 preparedStmt.executeUpdate();
             } catch (SQLException ex) {
                 Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         return dbID;  
         } 
           
           public String LeaveExtension(String date, String name,String unity,String from,String to,String extendedDay,String actual,String actionTaken,String empID, String dbID)
     {
          
         if(dbID.contains("null"))
            {
              try {
                  
                String arrayDate[]= date.split("-");
                String newDateString = arrayDate[2]+"-"+monthArray[Integer.parseInt(arrayDate[1])]+"-"+arrayDate[0];                 
                    dbID= getNewId(objQuery.selectHighestValue("leave_extension"),"A9-");   
                    PreparedStatement ps2=null;    
                    String sql = objQuery.InsertLeaveExtension();
                    ps2=con.prepareStatement(sql);
                    ps2.setString(1, dbID);
                    ps2.setString(2, newDateString);
                    ps2.setString(3, name);
                    ps2.setString(4, unity);
                    ps2.setString(5, from);
                    ps2.setString(6, to);
                    ps2.setString(7, extendedDay);
                    ps2.setString(8, actual);
                    ps2.setString(9, actionTaken);
                    ps2.setString(10, empID);
                    ps2.executeUpdate();
                    String query2 = objQuery.UpdateLeaveExtensionHighestValue();
                    String dbIDM = dbID.substring(dbID.indexOf("-")+1);
                    updateId(query2,Integer.parseInt(dbIDM)); 
                 } catch (SQLException ex) {
                     Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
                 }
         }else{
             try {
                 String query2 =objQuery.UpdateLeaveExtension(dbID);
                 PreparedStatement preparedStmt = con.prepareStatement(query2);
                 preparedStmt.setString(1, date);
                 preparedStmt.setString(2, name);
                 preparedStmt.setString(3, unity);
                 preparedStmt.setString(4, from);
                 preparedStmt.setString(5, to);
                 preparedStmt.setString(6, extendedDay);
                 preparedStmt.setString(7, actual);
                 preparedStmt.setString(8, actionTaken);
                 preparedStmt.setString(9, empID);
                 preparedStmt.executeUpdate();
             } catch (SQLException ex) {
                 Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         return dbID; 
         } 
             
           public String NativeStaffShortage(String date, String name,String unity,String remarks,String recovered, String action, String shortAmount,String currentStatus,String empID, String dbID)
     {
          
        if(dbID.contains("null"))
            {
              try {
                  dbID= getNewId(objQuery.selectHighestValue("native_staff_shortage"),"A7-");  
                String arrayDate[]= date.split("-");
                String newDateString = arrayDate[2]+"-"+monthArray[Integer.parseInt(arrayDate[1])]+"-"+arrayDate[0];                 
                
                    PreparedStatement ps2=null; 
                    String sql = objQuery.InsertNativeStaffShortage();
                    ps2=con.prepareStatement(sql);
                    ps2.setString(1, dbID);
                    ps2.setString(2, newDateString);
                    ps2.setString(3, name);
                    ps2.setString(4, unity);
                    ps2.setString(5, remarks);
                    ps2.setString(6, recovered);
                    ps2.setString(7, action);
                    ps2.setString(8, shortAmount); 
                    ps2.setString(9, currentStatus);
                    ps2.setString(10, empID);
                    ps2.executeUpdate();
                    String query2 = objQuery.UpdateNativeStaffShortageHighestValue();
                    String dbIDM = dbID.substring(dbID.indexOf("-")+1);
                    updateId(query2,Integer.parseInt(dbIDM));
                 } catch (SQLException ex) {
                     Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
                 }
         }else{
             try {
                 String query2 = objQuery.UpdateNativeStaffShortage(dbID);
                 PreparedStatement preparedStmt = con.prepareStatement(query2);
               
                 preparedStmt.setString(1, name);
                 preparedStmt.setString(2, unity);
                 preparedStmt.setString(3, remarks);
                 preparedStmt.setString(4, recovered);
                 preparedStmt.setString(5, action);
                 preparedStmt.setString(6, shortAmount);
                 preparedStmt.setString(7, currentStatus);
                 preparedStmt.setString(8, empID);
                 preparedStmt.executeUpdate();
             } catch (SQLException ex) {
                 Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         return dbID;
         } 
           public String terminatedEmployee(String date, String name,String unity, String remarks,String  actionTaken,String  shortAmount,String details,String empID, String dbID)
     {
         if(dbID.contains("null"))
            {
              try {
                    dbID= getNewId(objQuery.selectHighestValue("terminated_emp_due_loss"),"A8-"); 
                    String arrayDate[]= date.split("-");
                    String newDateString = arrayDate[2]+"-"+monthArray[Integer.parseInt(arrayDate[1])]+"-"+arrayDate[0];
                    PreparedStatement ps2=null;
                    String sql = objQuery.InsertTerminatedEmployee();
                    ps2=con.prepareStatement(sql);
                    ps2.setString(1, dbID);
                    ps2.setString(2, newDateString);
                    ps2.setString(3, name);
                    ps2.setString(4, unity);
                    ps2.setString(5, remarks);
                    ps2.setString(6, actionTaken);
                    ps2.setString(7, shortAmount);
                    ps2.setString(8, details);
                    ps2.setString(9, empID); 
                    ps2.executeUpdate();
                    String query2 = objQuery.UpdateInsertTerminatedEmployeeHighestValue();
                    String dbIDM = dbID.substring(dbID.indexOf("-")+1);
                    updateId(query2,Integer.parseInt(dbIDM)); 
                 } catch (SQLException ex) {
                     Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
                 }
         }
        else{
             try {
                 String query2 =objQuery.UpdateTerminatedEmployee(dbID);
                 PreparedStatement preparedStmt = con.prepareStatement(query2);
                 preparedStmt.setString(1, remarks);
                 preparedStmt.setString(2, actionTaken);
                 preparedStmt.setString(3, shortAmount);
                 preparedStmt.setString(4, details);
                 preparedStmt.executeUpdate();
             } catch (SQLException ex) {
                 Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         return dbID;
        
         } 
            public String salaryStopped(String name, String date, String unity,String remarks, String actionTaken, String stoppedBy,String empID, String dbID)
     {
                    
        if(dbID.contains("null"))
            {
              try {
                   dbID= getNewId(objQuery.selectHighestValue("salary_stopped_due_to_loss"),"A5-");   
                  
                String arrayDate[]= date.split("-");
                String newDateString = arrayDate[2]+"-"+monthArray[Integer.parseInt(arrayDate[1])]+"-"+arrayDate[0];                 
                
                    PreparedStatement ps2=null;               
                    String sql = objQuery.InsertSalaryStopped();
                    ps2=con.prepareStatement(sql);
                    ps2.setString(1, dbID);
                    ps2.setString(2, newDateString);
                    ps2.setString(3, name);
                    ps2.setString(4, unity);
                    ps2.setString(5, remarks);
                    ps2.setString(6, actionTaken);
                    ps2.setString(7, stoppedBy);
                    ps2.setString(8, empID);
                    ps2.executeUpdate();
                    String query2 = objQuery.UpdateSalaryStoppedHighestValue();
                    String dbIDM = dbID.substring(dbID.indexOf("-")+1);
                    updateId(query2,Integer.parseInt(dbIDM)); 
  
                 } catch (SQLException ex) {
                     Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
                 }
         }else{
             try {
                 String query2 = objQuery.UpdateSalaryStopped(dbID);
                 PreparedStatement preparedStmt = con.prepareStatement(query2);
                            
                 preparedStmt.setString(1, remarks);
                 preparedStmt.setString(2, actionTaken);
                 preparedStmt.setString(3, stoppedBy);
                 preparedStmt.executeUpdate();
             } catch (SQLException ex) {
                 Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         return dbID;  
         } 
            
    
            
            
            
            
    }
    
