/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author user
 */
public class Employee {
 
   private String EmployeeID;
   private String EmployeeName;
   private int EmployeeBranchID;

    public Employee(String EmployeeID, String EmployeeName, int EmployeeBranch) {
        this.EmployeeID = EmployeeID;
        this.EmployeeName = EmployeeName;
        this.EmployeeBranchID = EmployeeBranch;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String EmployeeName) {
        this.EmployeeName = EmployeeName;
    }

    public int getEmployeeBranchID() {
        return EmployeeBranchID;
    }

    public void setEmployeeBranchID(int EmployeeBranchID) {
        this.EmployeeBranchID = EmployeeBranchID;
    }

   
   
    
    
}
