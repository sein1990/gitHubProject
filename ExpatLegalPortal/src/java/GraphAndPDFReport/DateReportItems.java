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
public class DateReportItems {
    private String month, year, start, end;
    int numberOfCases ;
    public DateReportItems(String month, String year, String start, String end,int numberOfCases) {
        this.month = month;
        this.year = year;
        this.start = start;
        this.end = end;
        this.numberOfCases=numberOfCases;
    }

    public int getNumberOfCases() {
        return numberOfCases;
    }

    public void setNumberOfCases(int numberOfCases) {
        this.numberOfCases = numberOfCases;
    }

    
    
    
    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    
}
