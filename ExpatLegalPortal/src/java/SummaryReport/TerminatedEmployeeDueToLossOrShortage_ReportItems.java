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
public class TerminatedEmployeeDueToLossOrShortage_ReportItems {
    String caseID, date,name,unit, remarks,actionTaken, details,shortAmount,empID;

    public TerminatedEmployeeDueToLossOrShortage_ReportItems(String caseID, String date, String name, String unit, String remarks, String actionTaken, String details, String shortAmount, String empID) {
        this.caseID = caseID;
        this.date = date;
        this.name = name;
        this.unit = unit;
        this.remarks = remarks;
        this.actionTaken = actionTaken;
        this.details = details;
        this.shortAmount = shortAmount;
        this.empID = empID;
    }

    public String getCaseID() {
        return caseID;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public String getDetails() {
        return details;
    }

    public String getShortAmount() {
        return shortAmount;
    }

    public String getEmpID() {
        return empID;
    }

   
    
    
}
