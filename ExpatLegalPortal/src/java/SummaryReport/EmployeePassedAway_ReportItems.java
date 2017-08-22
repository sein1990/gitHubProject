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
public class EmployeePassedAway_ReportItems {
    
String caseID, date, name, unit,remarks,actionTaken, deathReason, empID;

    public EmployeePassedAway_ReportItems(String caseID, String date, String name, String unit, String remarks, String actionTaken, String deathReason, String empID) {
        this.caseID = caseID;
        this.date = date;
        this.name = name;
        this.unit = unit;
        this.remarks = remarks;
        this.actionTaken = actionTaken;
        this.deathReason = deathReason;
        this.empID = empID;
    }

    public String getDate() {
        return date;
    }

    public String getUnit() {
        return unit;
    }

    public String getCaseID() {
        return caseID;
    }

    public String getName() {
        return name;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public String getDeathReason() {
        return deathReason;
    }

    public String getEmpID() {
        return empID;
    }

  
    
    
}
