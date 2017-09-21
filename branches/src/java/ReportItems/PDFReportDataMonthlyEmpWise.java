/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportItems;

/**
 *
 * @author user
 */
public class PDFReportDataMonthlyEmpWise {
    
    private String EmpName;
    private String EmpID;
    private String BranchID;
    private String BranchName;
    private String Month;
    private String Year;

    public PDFReportDataMonthlyEmpWise(String EmpName, String EmpID, String BranchID, String BranchName, String Month, String Year) {
        this.EmpName = EmpName;
        this.EmpID = EmpID;
        this.BranchID = BranchID;
        this.BranchName = BranchName;
        this.Month = Month;
        this.Year = Year;
    }

    public String getBranchID() {
        return BranchID;
    }

    public String getBranchName() {
        return BranchName;
    }

    public String getEmpID() {
        return EmpID;
    }

    public String getEmpName() {
        return EmpName;
    }

    public String getMonth() {
        return Month;
    }

    public String getYear() {
        return Year;
    }
    
    
    
    
    
}
