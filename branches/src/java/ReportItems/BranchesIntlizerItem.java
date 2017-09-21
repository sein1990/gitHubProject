/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportItems;

/**
 *
 * @author user
 */
public class BranchesIntlizerItem {
    
    private String FromDate;
    private String ToDate;
    private String BranchID;
    private String BranchName;
    private String Month;
    private String Year;

    
    public boolean isSame(String id,String n,String m ,String y){
        if(BranchID.equals(id) && BranchName.equals(n) && Month.equals(m) && Year.equals(y))
            return true;
        return false;
    }
           
    
    public BranchesIntlizerItem(String BranchID, String BranchName, String Month, String Year,String fromDate, String toDate) {
        this.FromDate = fromDate;
        this.ToDate = toDate;
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

   


    public String getMonth() {
        return Month;
    }

    public String getYear() {
        return Year;
    }

    public String getFromDate() {
        return FromDate;
    }

    public String getToDate() {
        return ToDate;
    }
    
    public String print(){
        return BranchID+" "+BranchName+" "+Month+" "+Year+" "+FromDate+" "+ToDate;
    }
    
    
    
}
