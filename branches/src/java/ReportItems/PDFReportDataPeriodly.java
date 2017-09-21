/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportItems;

/**
 *
 * @author user
 */
public class PDFReportDataPeriodly {
    
    private String EmpName;
    private String EmpID;
    private String BranchID;
    private String BranchName;
    private String FirstDay;
    private String EndDay;

    public PDFReportDataPeriodly(String EmpName, String EmpID, String BranchID, String BranchName, String FirstDay, String EndDay) {
        this.EmpName = EmpName;
        this.EmpID = EmpID;
        this.BranchID = BranchID;
        this.BranchName = BranchName;
        this.FirstDay = FirstDay;
        this.EndDay = EndDay;
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

     public String getEndDay() {
        return EndDay;
    }

    public String getFirstDay() {
        return FirstDay;
    }
    
    
    
    
}
