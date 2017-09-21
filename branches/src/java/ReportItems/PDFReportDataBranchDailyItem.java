/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportItems;

/**
 *
 * @author user
 */
public class PDFReportDataBranchDailyItem {
    
    private String BranchName;
    private String Date;

    public PDFReportDataBranchDailyItem(String BranchName, String Date) {
        this.BranchName = BranchName;
        this.Date = Date;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String BranchName) {
        this.BranchName = BranchName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
   
    
    

    
    
    
    
}
