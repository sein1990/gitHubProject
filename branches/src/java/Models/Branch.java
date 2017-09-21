/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import JavaCodePackage.InternalMainClass;
import JavaCodePackage.QuerisClass;
import JavaCodePackage.conectionClass;
import java.sql.ResultSet;
import java.util.Map;

/**
 *
 * @author user
 */
public class Branch {
    private String BranchId;
    private String BranchName;
    private int NumberOfOpenDays;        

    public Branch(String BranchId, String BranchName, int NumberOfOpenDays) {
        this.BranchId = BranchId;
        this.BranchName = BranchName;
        this.NumberOfOpenDays = NumberOfOpenDays;
    }

    public Branch(String BranchId, int NumberOfOpenDays) {
        this.BranchId = BranchId;
        this.NumberOfOpenDays = NumberOfOpenDays;
    }
    
    
     public Branch(String BranchName, String NumberOfOpenDays) {
      
        this.BranchName = BranchName;
        this.NumberOfOpenDays = Integer.parseInt(NumberOfOpenDays);
        
    }

    public String getBranchId() {
        return BranchId;
    }

    public void setBranchId(String BranchId) {
        this.BranchId = BranchId;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String BranchName) {
        this.BranchName = BranchName;
    }

    public int getNumberOfOpenDays() {
        return NumberOfOpenDays;
    }

    public void setNumberOfOpenDays(int NumberOfOpenDays) {
        this.NumberOfOpenDays = NumberOfOpenDays;
    }
   
//    public void AddNewBranch(){
//        
//        conectionClass c = new conectionClass();
//        QuerisClass query = new QuerisClass();
//        
//        BranchId = "444";
//        
//        c.excuteQuery("insert into comp_info (comp_id,comp_name,op_days) values "+
//                "(565,'eee',3)");
//        c.closeConnection();
//    }
  
        public void UpdateBranch(){
        
            conectionClass c = new conectionClass();
            QuerisClass query = new QuerisClass();
            c.excuteQuery(query.updateBranchQuery(BranchId,NumberOfOpenDays));
            c.closeConnection();
    }
    
    
}
