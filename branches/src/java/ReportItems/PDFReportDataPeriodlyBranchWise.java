/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportItems;

/**
 *
 * @author user
 */
public class PDFReportDataPeriodlyBranchWise {
    
   
    private String BranchID;
    private String BranchName;
    private String Fristday;
    private String SecDay;
    private String Status;

    public PDFReportDataPeriodlyBranchWise(String BranchID,String BranchName, String Fristday, String SecDay, String Status) {
        this.BranchID = BranchID;
        this.BranchName=BranchName;
        this.Fristday = Fristday;
        this.SecDay = SecDay;
        this.Status = Status;
    }

    public String getBranchID() {
        return BranchID;
    }

    public String getBranchName() {
        return BranchName;
    }

    public String getFristday() {
        return Fristday;
    }

    public String getSecDay() {
        return SecDay;
    }

    public String getStatus() {
        return Status;
    }


    
    
    
}
