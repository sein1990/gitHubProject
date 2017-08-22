/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphAndPDFReport;

/**
 *
 * @author USER
 */
public class GraphReportItems {
       int totalNumberOfCasePerDay; String todayDate;

    public GraphReportItems(int totalNumberOfCasePerDay, String todayDate) {
        this.totalNumberOfCasePerDay = totalNumberOfCasePerDay;
        this.todayDate = todayDate;
    }

    public int getTotalNumberOfCasePerDay() {
        return totalNumberOfCasePerDay;
    }

    public String getTodayDate() {
        return todayDate;
    }

}
