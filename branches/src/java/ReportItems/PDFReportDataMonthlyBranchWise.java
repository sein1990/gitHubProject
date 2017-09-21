/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportItems;

/**
 *
 * @author user
 */
public class PDFReportDataMonthlyBranchWise {
    
   
    private String BranchID;
    private String BranchName;
    private String Month;
    private String Year;
    private String Status;

    public PDFReportDataMonthlyBranchWise( String BranchID, String BranchName, String Month, String Year,String Status) {
       
        this.BranchID = BranchID;
        this.BranchName = BranchName;
        this.Month = Month;
        this.Year = Year;
        this.Status = Status;
    }

    public String getBranchID() {
        return BranchID;
    }

    public String getBranchName() {
        return BranchName;
    }

   
    public String getMonth() {
        return Month;
    }

    public String getYear() {
        return Year;
    }

    public String getStatus() {
        return Status;
    }
    
    
    
    
    
}
