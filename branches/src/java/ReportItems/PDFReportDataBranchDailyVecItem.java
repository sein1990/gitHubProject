/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportItems;

/**
 *
 * @author user
 */
public class PDFReportDataBranchDailyVecItem {
    
    private String Status;
    private String EmpId;
    private String EmpName;

    public PDFReportDataBranchDailyVecItem(String Status, String EmpId, String EmpName) {
        this.Status = Status;
        this.EmpId = EmpId;
        this.EmpName = EmpName;
    }

    public String getEmpId() {
        return EmpId;
    }

    public String getEmpName() {
        return EmpName;
    }

    public String getStatus() {
        return Status;
    }
    
    
    
    
    
}
