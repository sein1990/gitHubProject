package Models;

import JavaCodePackage.QuerisClass;
import JavaCodePackage.conectionClass;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author user
 */
public class Admin {
    
    private String UserName;
    private String Password;
    private int AdminAndBranchId;
    
    public Admin(String UserName, String Password,int Type) {
        this.UserName = UserName;
        this.Password = Password;
        this.AdminAndBranchId = Type;
    }

    public void print(){
        System.out.println(UserName+"  "+Password+"  "+AdminAndBranchId);
    }
    
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getAdminAndBranchId() {
        return AdminAndBranchId;
    }

    public void setAdminAndBranchId(int AdminAndBranchId) {
        this.AdminAndBranchId = AdminAndBranchId;
    }

    public void addAdmin(){
        conectionClass c = new conectionClass();
        QuerisClass query = new QuerisClass();
//        c.excuteQuery("insert into user_admin (comp_id,user_name,user_pass) values"+
//                " ('"+AdminAndBranchId+"','"+UserName+"','"+Password+"')");
        
        c.excuteQuery(query.addAdminQuery(AdminAndBranchId, UserName, Password));
        c.closeConnection();  
    }
     
    public void UpdateAdmin(){
        
            conectionClass c = new conectionClass();
            QuerisClass query = new QuerisClass();
            c.excuteQuery(query.updateAdminQuery(UserName,Password,AdminAndBranchId));
            c.closeConnection();
    }

    
}
