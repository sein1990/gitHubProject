/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportItems;

/**
 *
 * @author user
 */
public class PayrollVecItem {
    
    private String BranchId;
    private String EmpId;
    private String EmpName;
    private String Absent;
    private String AnnaulLeave;
    private String CompLeave;
    private String MedicalLeave;
    private String PatLeave;
    private String MatLeave;
    private String Notyet;
    private String FullDay;
    private String HalfDay;

    public PayrollVecItem(String BranchId, String EmpId,  String EmpName, String Absent, String AnnaulLeave, String CompLeave, String MedicalLeave, String PatLeave, String MatLeave) {
        this.BranchId = BranchId;
        this.EmpName = EmpName;
        this.EmpId = EmpId;
        this.Absent = Absent;
        this.AnnaulLeave = AnnaulLeave;
        this.CompLeave = CompLeave;
        this.MedicalLeave = MedicalLeave;
        this.PatLeave = PatLeave;
        this.MatLeave = MatLeave;
    }

    
    
    
    public PayrollVecItem(String BranchId, String EmpId,String EmpName) {
        this.BranchId = BranchId;
        this.EmpId = EmpId;
        this.EmpName = EmpName;
        this.Absent="0";
        this.AnnaulLeave = "0";
        this.CompLeave= "0";
        this.MedicalLeave= "0";
        this.PatLeave= "0";
        this.MatLeave= "0";
        this.Notyet= "0";
        this.FullDay= "0";
        this.HalfDay= "0";
    }

    public String getFullDay() {
        return FullDay;
    }

    public void setFullDay(String FullDay) {
        this.FullDay = FullDay;
    }

    public String getHalfDay() {
        return HalfDay;
    }

    public void setHalfDay(String HalfDay) {
        this.HalfDay = HalfDay;
    }

    public void setAbsent(String Absent) {
        this.Absent = Absent;
    }

    public void setAnnaulLeave(String AnnaulLeave) {
        this.AnnaulLeave = AnnaulLeave;
    }

    public void setBranchId(String BranchId) {
        this.BranchId = BranchId;
    }

    public void setCompLeave(String CompLeave) {
        this.CompLeave = CompLeave;
    }

    public void setEmpId(String EmpId) {
        this.EmpId = EmpId;
    }

    public String getNotyet() {
        return Notyet;
    }

    public void setNotyet(String Notyet) {
        this.Notyet = Notyet;
    }

    public void setMatLeave(String MatLeave) {
        this.MatLeave = MatLeave;
    }

    public void setMedicalLeave(String MedicalLeave) {
        this.MedicalLeave = MedicalLeave;
    }

    public void setPatLeave(String PatLeave) {
        this.PatLeave = PatLeave;
    }

    
    
    public String getAbsent() {
        return Absent;
    }

    public String getAnnaulLeave() {
        return AnnaulLeave;
    }

    public String getBranchId() {
        return BranchId;
    }

    public String getCompLeave() {
        return CompLeave;
    }

    public String getEmpId() {
        return EmpId;
    }

    

    public String getMatLeave() {
        return MatLeave;
    }

    public String getMedicalLeave() {
        return MedicalLeave;
    }

    public String getPatLeave() {
        return PatLeave;
    }

    public String getEmpName() {
        return EmpName;
    }
    
   
    
    
    
    
    
}
