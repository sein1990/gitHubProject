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
String caseID,name, unity, remarks, actionTaken, salaryStopped, empID;

    public SalaryStoppedDueToLossOrShortage_ReportItems(String caseID, String name, String unity, String remarks, String actionTaken, String salaryStopped, String empID) {
        this.caseID = caseID;
        this.name = name;
        this.unity = unity;
        this.remarks = remarks;
        this.actionTaken = actionTaken;
        this.salaryStopped = salaryStopped;
        this.empID = empID;
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

    public String getActionTaken() {
        return actionTaken;
    }

    public String getSalaryStopped() {
        return salaryStopped;
    }

    public String getEmpID() {
        return empID;
    }

}
