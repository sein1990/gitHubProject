/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpertLegalPortalClass;

/**
 *
 * @author USER
 */
public class QueryClass {
    public String UpdateAttachment(){
    return "UPDATE `attachment` SET `caseID`=? WHERE `caseID`='null'";
    }
    public String InsertA1NonReturnedFromLeave(){   
    return "INSERT INTO `a1_not_returned_from_leave` (`id`, `date`, `reason`, `name`, "
            + "`unit`, `remarks`, `amount_to_be_recovered_apart_from_salary_holdings`,"
            + " `salary_deposit`, `total_amount`, `filed_and_closed`, `emp_id`)"
            + "values(?,?,?,?,?,?,?,?,?,?,?)";      
    }
    public String UpdateA1HighestValue(){
    return  "UPDATE `highestvalue` SET `a1`=?";
    }
    public String UpdateA1NotReturnedFromLeave(String newId)
    {
    return "UPDATE `a1_not_returned_from_leave` SET `date`=?,`reason`=?,`name`=?,`unit`=?,`remarks`=?,"
                         + "`amount_to_be_recovered_apart_from_salary_holdings`=?,`salary_deposit`=?,`total_amount`=?,`filed_and_closed`=?,"
                         + "`emp_id`=? WHERE `id`='"+newId+"'";
    }
    public String InsertA2WithCompanyProperty(){
    return " INSERT INTO `a2_with_company_property` (`id`, `date`, `reason`, `name`, `unit`, `remarks`, "
            + "`amount_to_be_recovered_apart_from_salary_holdings`, `salary_deposit`, `total_amount`, "
            + "`filed_and_closed`, `emp_id`)values(?,?,?,?,?,?,?,?,?,?,?)";                     
    }
    public String UpdateA2HighestValue(){
    return "UPDATE `highestvalue` SET `a2`=?";
    }
    public String UpdateA2WithCompanyProperty(String newId){
     return "UPDATE `a2_with_company_property` SET `date`=?,`reason`=?,`name`=?,`unit`=?,`remarks`=?,"
                         + "`amount_to_be_recovered_apart_from_salary_holdings`=?,`salary_deposit`=?,`total_amount`=?,`filed_and_closed`=?,"
                         + "`emp_id`=? WHERE `id`='"+newId+"'";   
    }    
    public String InsertA3DamageOrLoss(){
    
    return  " INSERT INTO `a3_damage_or_loss` (`id`, `date`, `reason`, `name`, `unit`, `remarks`,"
            + " `amount_to_be_recovered_apart_from_salary_holdings`, `salary_deposit`, `total_amount`, "
            + "`filed_and_closed`, `emp_id`)values(?,?,?,?,?,?,?,?,?,?,?)";                 
    }
    public String UpdateA3HighestValue(){
    return "UPDATE `highestvalue` SET `a3`=?";
    }
    public String UpdateA3DamageOrLoss(String newId){
    
    return "UPDATE `a3_damage_or_loss` SET `date`=?,`reason`=?,`name`=?,`unit`=?,`remarks`=?,"
                         + "`amount_to_be_recovered_apart_from_salary_holdings`=?,`salary_deposit`=?,`total_amount`=?,`filed_and_closed`=?,"
                         + "`emp_id`=? WHERE `id`='"+newId+"'";
    }
    public String InsertDisNotReturnFromLeave(){
    
    return " INSERT INTO `dis` (`id`, `date`, `reason`, `name`, `unit`, `remarks`, `investigation`,"
            + " `action_taken`, `action_to_be_taken`, `people_involved`, `emp_id`)values(?,?,?,?,?,?,?,?,?,?,?)";                     
    }
    public String UpdateDisNotReturnFromLeaveHighestValue(){
    return "UPDATE `highestvalue` SET `dis`=?";
    }
    public String UpdateDisNotReturnFromLeave(String newId){
    return "UPDATE `dis` SET `date`=?,`reason`=?,`name`=?,`unit`=?,`remarks`=?,`investigation`=?,`action_taken`=?,"
            + "`action_to_be_taken`=?,`people_involved`=?,`emp_id`=? WHERE `id`='"+newId+"'";                
    }   
    public String InsertEmpPassedAway(){
    return "INSERT INTO `emp_passed_away` (`id`, `date`,`name`,`unit`, `remarks`, `action_taken`, "
            + "`reason_for_death`, `emp_id`)values(?,?,?,?,?,?,?,?)";              
    }
    public String UpdateInsertEmpPassedAwayHighestValue(){
    return "UPDATE `highestvalue` SET `emp_passed_away`=?";
    }
    public String UpdateEmpPassedAway(String newId){
    return  "UPDATE `emp_passed_away` SET  `date`=?,`name`=?,`unit`=?,`remarks`=?,`action_taken`=?,"
            + "`reason_for_death`=?,`emp_id`=? WHERE `id`='"+newId+"'";
    }
    public String InsertLeaveExtension(){
    return " INSERT INTO `leave_extension` (`id`, `date`,`name`, `unit`, `from_date`, `to_date`, "
            + "`extended_day`, `actual`, `action_taken`, `emp_id`)values(?,?,?,?,?,?,?,?,?,?)";                      
    }
    public String UpdateLeaveExtensionHighestValue(){
    return "UPDATE `highestvalue` SET `leave_extension`=?";
    }
    public String UpdateLeaveExtension(String newId){
    return "UPDATE `leave_extension` SET `date`=?, `name`=?,`unit`=?,`from_date`=?,`to_date`=?,"
            + "`extended_day`=?,`actual`=?,`action_taken`=?,`emp_id`=? WHERE `id`='"+newId+"'";             
    }
    public String InsertNativeStaffShortage(){
    return " INSERT INTO `native_staff_shortage` (`id`,`date`, `name`, `unit`, `remarks`, `to_be_recovered`,"
            + " `action`, `short_amount`, `current_status`, `emp_id`)values(?,?,?,?,?,?,?,?,?,?)";                      
    }
    public String UpdateNativeStaffShortageHighestValue(){
    return "UPDATE `highestvalue` SET `native_staff_shortage`=?";
    }
    public String UpdateNativeStaffShortage(String newId){
    return "UPDATE `native_staff_shortage` SET `date`=?,`name`=?,`unit`=?,`remarks`=?,"
                         + "`to_be_recovered`=?,`action`=?,`short_amount`=?,"
                         + "`current_status`=?,`emp_id`=?  WHERE `id`='"+newId+"'";
    }
    public String InsertTerminatedEmployee(){
    return "INSERT INTO `terminated_emp_due_loss_or_performance` (`id`,`date`, `name`,`unit`, "
            + "`remarks`, `action_taken`, `short_amount`, `details`, `emp_id`)values(?,?,?,?,?,?,?,?,?)";      
    }
    public String UpdateInsertTerminatedEmployeeHighestValue(){
    return "UPDATE `highestvalue` SET `terminated_emp_due_loss`=?";
    }
    public String UpdateTerminatedEmployee(String newId){
    return "UPDATE `terminated_emp_due_loss_or_performance` SET `date`=?,`name`=?, `unit`=?,"
            + "`remarks`=?, `action_taken`, `short_amount`=?,`details`=?,`emp_id`=? WHERE `id`='"+newId+"'";
    }
    public String InsertSalaryStopped(){
    return " INSERT INTO `salary_stopped_due_to_loss_or_performance` (`id`, `date`,`name`, `unit`, `remarks`,"
            + " `action_taken`, `stopped_by`, `emp_id`)values(?,?,?,?,?,?,?,?)";                     
    }
    public String UpdateSalaryStoppedHighestValue(){
    return "UPDATE `highestvalue` SET `salary_stopped_due_to_loss`=?";
    }
    public String UpdateSalaryStopped(String newId){
    return "UPDATE `salary_stopped_due_to_loss_or_performance` SET `date`=?,`name`=?,`unit`=?,"
                         + "`remarks`=?,`action_taken`=?,`stopped_by`=?,`emp_id`=? WHERE `id`='"+newId+"'";
    }
    public String InsertAttachment(){
    return  " INSERT INTO `attachment` (`flag`, `path`, `caseID`)values(?,?,?)";
    }
    public String SelectHighestID(){

    return "SELECT MAX(`id`) FROM `attachment`";
    }
    public static String loginDetails(){
    return "select * from `user` where `username`=? and `password`=?";
    }
    public String SearchResult(){
    return "SELECT `id`,`date`,`name`,`unit` FROM `a1_not_returned_from_leave`"
                      + " UNION SELECT `id`,`date`,`name`,`unit` FROM `a2_with_company_property`"
                      +" UNION SELECT `id`,`date`,`name`,`unit` FROM `a3_damage_or_loss`"
                      +" UNION SELECT `id`,`date`,`name`,`unit` FROM `dis`"
                      +" UNION SELECT `id`,`date`,`name`,`unit` FROM `leave_extension`"
                      +" UNION SELECT `id`,`date`,`name`,`unit` FROM `emp_passed_away`"
                      +" UNION SELECT  `id`,`date`,`name`,`unit` FROM `salary_stopped_due_to_loss_or_performance`"
                      +" UNION SELECT  `id`,`date`,`name`,`unit` FROM `terminated_emp_due_loss_or_performance`"
                      +" UNION SELECT  `id`,`name`,`unit`,`remarks` FROM `native_staff_shortage`";
    }
    public String A1SummaryData(String caseidupdate){
    return "SELECT * FROM `a1_not_returned_from_leave` WHERE `id`='"+caseidupdate+"'";
    }
    public String A2SummaryData(String caseidupdate){
    return "SELECT * FROM `a2_with_company_property` WHERE `id`='"+caseidupdate+"'";
    }
    public String A3SummaryData(String caseidupdate){
    return "SELECT * FROM `a3_damage_or_loss` WHERE `id`='"+caseidupdate+"'";
    }
    public String DisSummaryData(String caseidupdate){
    return "SELECT * FROM `dis` WHERE `id`='"+caseidupdate+"'";
    }
    public String EmployeePassedAwaySummaryData(String caseidupdate){
    return "SELECT * FROM `emp_passed_away` WHERE `id`='"+caseidupdate+"'";
    }
    public String NativeShortageSummaryData(String caseidupdate){
    return "SELECT * FROM `native_staff_shortage` WHERE `id`='"+caseidupdate+"'";
    }
    public String salaryStoppedDueToLossPerformanceSummaryData(String caseidupdate){
    return "SELECT * FROM `salary_stopped_due_to_loss_or_performance` WHERE `id`='"+caseidupdate+"'";
    }
    public String TerminatedEmployeeSummaryData(String caseidupdate){
    return "SELECT * FROM `terminated_emp_due_loss_or_performance` WHERE `id`='"+caseidupdate+"'";
    }
    public String A1ReportQuery(String currentFromDate, String currentToDate){
    return "SELECT count(`id`) FROM `a1_not_returned_from_leave` WHERE `date` BETWEEN '"+currentFromDate+"' AND '"+currentToDate+"'";
    }
    public String A2ReportQuery(String currentFromDate, String currentToDate){
    return "SELECT count(`id`) FROM `a2_with_company_property` WHERE `date` BETWEEN '"+currentFromDate+"' AND '"+currentToDate+"'";
    }
     public String A3ReportQuery(String currentFromDate, String currentToDate){
    return "SELECT count(`id`) FROM `a3_not_returned_from_leave`WHERE `date` BETWEEN '"+currentFromDate+"' AND '"+currentToDate+"'";
    }
     public String DisReportQuery(String currentFromDate, String currentToDate){
    return "SELECT count(`id`) FROM `dis` WHERE `date` BETWEEN '"+currentFromDate+"' AND '"+currentToDate+"'";
    }
     public String SalaryStoppedReportQuery(String currentFromDate, String currentToDate){
             return "SELECT count(`id`) FROM `salary_stopped_due_to_loss_or_performance` WHERE `date` BETWEEN '"+currentFromDate+"' AND '"+currentToDate+"'";
    }
     public String TerminatedEmployeeReportQuery(String currentFromDate, String currentToDate){
     return "SELECT count(`id`) FROM `terminated_emp_due_loss_or_performance`WHERE `date` BETWEEN '"+currentFromDate+"' AND '"+currentToDate+"'";
     }
     public String NativeStaffShortageReportQuery(String currentFromDate, String currentToDate){
     return "SELECT count(`id`) FROM `native_staff_shortage` WHERE `date` BETWEEN '"+currentFromDate+"' AND '"+currentToDate+"'";
     }
     public String EmpPassedAwayReportQuery(String currentFromDate, String currentToDate){
     return "SELECT count(`id`) FROM `emp_passed_away`WHERE `date` BETWEEN '"+currentFromDate+"' AND '"+currentToDate+"'";
     }
     public String LeaveExtensionReportQuery(String currentFromDate, String currentToDate){
     return "SELECT count(`id`) FROM `leave_extension`WHERE `date` BETWEEN '"+currentFromDate+"' AND '"+currentToDate+"'";
     }    
     public String selectHighestValue(String colName){
         return "SELECT `"+colName+"` FROM `highestvalue`";
     }
     public String a1(String caseid1){
     return "SELECT * FROM `a1_not_returned_from_leave` WHERE `id`='"+caseid1+"'";
     }
     public String a1Attachment(String caseid1){
     return "SELECT `id`,`path` FROM `attachment` WHERE `caseID`='"+caseid1+"'and `flag`='1'";
     }
      public String a2(String caseid1){
     return "SELECT * FROM `a2_with_company_property` WHERE `id`='"+caseid1+"'";
     }
     public String a2Attachment(String caseid1){
     return "SELECT `id`,`path` FROM `attachment` WHERE `caseID`='"+caseid1+"'and `flag`='1'";
     }
     
    public String a3(String caseid1){
     return "SELECT * FROM `a3_damage_or_loss` WHERE `id`='"+caseid1+"'";
     }
     public String a3Attachment(String caseid1){
     return "SELECT `id`,`path` FROM `attachment` WHERE `caseID`='"+caseid1+"'and `flag`='1'";
     }
     public String Dis(String caseid1){
     return "SELECT * FROM `dis` WHERE `id`='"+caseid1+"'";
     }
     public String DisAttachment(String caseid1){
     return "SELECT `id`,`path` FROM `attachment` WHERE `caseID`='"+caseid1+"'and `flag`='1'";
     }
      public String empPassedAway(String caseid1){
     return "SELECT * FROM `emp_passed_away` WHERE `id`='"+caseid1+"'";
     }
     public String empPassedAwayAttachment(String caseid1){
     return "SELECT `id`,`path` FROM `attachment` WHERE `caseID`='"+caseid1+"'and `flag`='1'";
     }
     public String leaveExtension(String caseid1){
     return "SELECT * FROM `leave_extension` WHERE `id`='"+caseid1+"'";
     }
     public String leaveExtensionAttachment(String caseid1){
     return "SELECT `id`,`path` FROM `attachment` WHERE `caseID`='"+caseid1+"'and `flag`='1'";
     }
     
     public String nativeStaffShortage(String caseid1){
     return "SELECT * FROM `native_staff_shortage` WHERE `id`='"+caseid1+"'";
     }
     public String nativeStaffShortageAttachment(String caseid1){
     return "SELECT `id`,`path` FROM `attachment` WHERE `caseID`='"+caseid1+"'and `flag`='1'";
     }
     public String salaryStopped(String caseid1){
     return "SELECT * FROM `salary_stopped_due_to_loss_or_performance` WHERE `id`='"+caseid1+"'";
     }
     public String salaryStoppedAttachment(String caseid1){
     return "SELECT `id`,`path` FROM `attachment` WHERE `caseID`='"+caseid1+"'and `flag`='1'";
     }
      public String terminatedEmp(String caseid1){
     return "SELECT * FROM `terminated_emp_due_loss_or_performance` WHERE `id`='"+caseid1+"'";
     }
     public String terminatedEmpAttachment(String caseid1){
     return "SELECT `id`,`path` FROM `attachment` WHERE `caseID`='"+caseid1+"' and `flag`='1'";
     }
     
     
     
     
}
