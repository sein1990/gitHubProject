/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportItems;

import java.util.Calendar;

/**
 *
 * @author user
 */
public class PDFReportDataBranchMonthlyVecItem {
    
    private String Status;
    private String EmpId;
    private String EmpName;
    private Calendar calendar;

    public PDFReportDataBranchMonthlyVecItem(String Status, String EmpId, String EmpName, Calendar calendar) {
        this.Status = Status;
        this.EmpId = EmpId;
        this.EmpName = EmpName;
        this.calendar = calendar;
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

    public Calendar getCalendar() {
        return calendar;
    }

    
    


    
    
    
    
}
