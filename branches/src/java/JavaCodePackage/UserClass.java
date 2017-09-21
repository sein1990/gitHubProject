/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaCodePackage;

/**
 *
 * @author user
 */
public class UserClass {
    
    private String UserName;
    private String Name;
    private String Password;
    private String Id; 
    private String Type;
    
    public UserClass(String Id, String Name, String UserName, String Password,String Type) {
        this.UserName = UserName;
        this.Name = Name;
        this.Password = Password;
        this.Id = Id;
        this.Type = Type;
    }

    public void print(){
        System.out.println(UserName+" "+Name+" "+Password+" "+Id+" "+Type);
    }
    
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    
}
