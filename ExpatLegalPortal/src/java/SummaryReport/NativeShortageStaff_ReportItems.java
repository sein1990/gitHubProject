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
public class NativeShortageStaff_ReportItems {
    String caseID, date,name, unity, remarks, recovered, action, shortAmount, currentStatus, empID;

    public NativeShortageStaff_ReportItems(String caseID, String date, String name, String unity, String remarks, String recovered, String action, String shortAmount, String currentStatus, String empID) {
        this.caseID = caseID;
        this.date = date;
        this.name = name;
        this.unity = unity;
        this.remarks = remarks;
        this.recovered = recovered;
        this.action = action;
        this.shortAmount = shortAmount;
        this.currentStatus = currentStatus;
        this.empID = empID;
    }

    public String getDate() {
        return date;
    }

    

    public String getCaseID() {
        return caseID;
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

    public String getAction() {
        return action;
    }

    public String getShortAmount() {
        return shortAmount;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public String getEmpID() {
        return empID;
    }
    
    
}
