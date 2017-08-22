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
public class A3_ReportItems {
    String caseID,date, reason, name, unity, remarks, recovered, salaryDeposit, totalAmount, fileClosed, empID;
    public A3_ReportItems(String caseID, String date, String reason, String name, String unity, String remarks, String recovered, String salaryDeposit, String totalAmount, String fileClosed, String empID) {
        this.caseID = caseID;
        this.date = date;
        this.reason = reason;
        this.name = name;
        this.unity = unity;
        this.remarks = remarks;
        this.recovered = recovered;
        this.salaryDeposit = salaryDeposit;
        this.totalAmount = totalAmount;
        this.fileClosed = fileClosed;
        this.empID = empID;
    }

    public String getCaseID() {
        return caseID;
    }

    public String getDate() {
        return date;
    }

    public String getReason() {
        return reason;
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

    public String getRecovered() {
        return recovered;
    }

    public String getSalaryDeposit() {
        return salaryDeposit;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public String getFileClosed() {
        return fileClosed;
    }

    public String getEmpID() {
        return empID;
    }
    
}
