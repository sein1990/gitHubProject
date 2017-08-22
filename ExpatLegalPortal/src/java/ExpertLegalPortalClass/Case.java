/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpertLegalPortalClass;

/**
 *
 * @author user
 */
public class Case {
    String CaseId;
    String EmpName;
    String EmpUnit;
    String CaseDate;

    public Case(String CaseId, String EmpName, String EmpUnit, String CaseDate) {
        this.CaseId = CaseId;
        this.EmpName = EmpName;
        this.EmpUnit = EmpUnit;
        this.CaseDate = CaseDate;
    }

    public String getCaseId() {
        return CaseId;
    }

    public String getEmpName() {
        return EmpName;
    }

    public String getEmpUnit() {
        return EmpUnit;
    }

    public String getCaseDate() {
        return CaseDate;
    }
    
  
    
    
}
