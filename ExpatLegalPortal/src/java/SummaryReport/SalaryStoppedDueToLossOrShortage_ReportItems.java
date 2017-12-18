/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SummaryReport;

/**
 *
 * @author USER
 */
public class SalaryStoppedDueToLossOrShortage_ReportItems {
String caseID, caseDate, name, unity, remarks, actionTaken, salaryStopped, empID, salaryAmount, salaryStoppedDate, releasedDate, totalMonth, totalSalary;

    public SalaryStoppedDueToLossOrShortage_ReportItems(String caseID, String caseDate, String name, String unity, String remarks, String actionTaken, String salaryStopped, String empID, String salaryAmount, String salaryStoppedDate, String releasedDate, String totalMonth, String totalSalary) {
        this.caseID = caseID;
        this.caseDate = caseDate;
        this.name = name;
        this.unity = unity;
        this.remarks = remarks;
        this.actionTaken = actionTaken;
        this.salaryStopped = salaryStopped;
        this.empID = empID;
        this.salaryAmount = salaryAmount;
        this.salaryStoppedDate = salaryStoppedDate;
        this.releasedDate = releasedDate;
        this.totalMonth = totalMonth;
        this.totalSalary = totalSalary;
    }
    
    

    

  
    public String getCaseID() {
        return caseID;
    }

    public String getCaseDate() {
        return caseDate;
    }

    public String getName() {
        return name;
    }

    public String getUnity() {
        return unity;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public String getSalaryStopped() {
        return salaryStopped;
    }

    public String getEmpID() {
        return empID;
    }
    
    
        public String getSalaryAmount() {
        return salaryAmount;
    }

    public String getSalaryStoppedDate() {
        return salaryStoppedDate;
    }

    public String getReleasedDate() {
        return releasedDate;
    }

    public String getTotalMonth() {
        return totalMonth;
    }

    public String getTotalSalary() {
        return totalSalary;
    }
    
    


}
