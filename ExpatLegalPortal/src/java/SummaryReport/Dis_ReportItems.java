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
public class Dis_ReportItems {
    String caseID,date, reason, name, unity, remarks, investigation, actionTaken, actionToBeTaken, peopleInvolved, empID;

    public Dis_ReportItems(String caseID, String date, String reason, String name, String unity, String remarks, String investigation, String actionTaken, String actionToBeTaken, String peopleInvolved, String empID) {
        this.caseID = caseID;
        this.date = date;
        this.reason = reason;
        this.name = name;
        this.unity = unity;
        this.remarks = remarks;
        this.investigation = investigation;
        this.actionTaken = actionTaken;
        this.actionToBeTaken = actionToBeTaken;
        this.peopleInvolved = peopleInvolved;
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

    public String getInvestigation() {
        return investigation;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public String getActionToBeTaken() {
        return actionToBeTaken;
    }

    public String getPeopleInvolved() {
        return peopleInvolved;
    }

    public String getEmpID() {
        return empID;
    }

    
    
}
