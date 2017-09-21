/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportItems;

import java.util.Vector;

/**
 *
 * @author user
 */
public class ConsalidateReportItem {
    
    private String BranchId;
    private String EmpId;
    private String EmpName;
    private String Month;
    private String Year;
    private Vector<String> Status;
    private Vector<String> LogDates;

    public ConsalidateReportItem(String BranchId, String EmpId, String EmpName, String Month,String Year) {
        this.BranchId = BranchId;
        this.EmpId = EmpId;
        this.EmpName = EmpName;
        this.Month = Month;
        this.Year = Year;
        Status = new  Vector<String>();
        LogDates = new  Vector<String>();
    }

    public String getBranchId() {
        return BranchId;
    }

    public void setBranchId(String BranchId) {
        this.BranchId = BranchId;
    }

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String EmpId) {
        this.EmpId = EmpId;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String EmpName) {
        this.EmpName = EmpName;
    }

    public Vector<String> getLogDates() {
        return LogDates;
    }

    public void setLogDates(Vector<String> LogDates) {
        this.LogDates = LogDates;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String Month) {
        this.Month = Month;
    }

    public Vector<String> getStatus() {
        return Status;
    }

    public void setStatus(Vector<String> Status) {
        this.Status = Status;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }
    
    
    
    
}
