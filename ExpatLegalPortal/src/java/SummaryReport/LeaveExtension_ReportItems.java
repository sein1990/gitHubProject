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
public class LeaveExtension_ReportItems {
    
String caseID, date, name,unity, from, to, extendedDay, actual,actionTaken, empID;

    public LeaveExtension_ReportItems(String caseID, String date, String name, String unity, String from, String to, String extendedDay, String actual, String actionTaken, String empID) {
        this.caseID = caseID;
        this.date = date;
        this.name = name;
        this.unity = unity;
        this.from = from;
        this.to = to;
        this.extendedDay = extendedDay;
        this.actual = actual;
        this.actionTaken = actionTaken;
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

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getExtendedDay() {
        return extendedDay;
    }

    public String getActual() {
        return actual;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public String getEmpID() {
        return empID;
    }

    
    
}
