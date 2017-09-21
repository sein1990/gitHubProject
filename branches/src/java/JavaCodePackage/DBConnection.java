package JavaCodePackage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**=============================================================================================================================================================================['
 *
 * @author user
 */
public class DBConnection {
     public DBConnection(){
        connnectFun();
        
        dbLink = "jdbc:oracle:thin:@192.168.14.77:1521:orcl";
        userName = "mgpayroll";
        password = "payroll";
    }
    
     
     String dbLink;
     String userName;
     String password;
     
     
    public void connnectFun(){
        try{  
        //step1 load the driver class  
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //step2 create  the connection object  
            Connection con=DriverManager.getConnection(  
            dbLink,userName,password);  

       //     System.out.print(con.getSchema());
            
            
            //step3 create the statement object  
            Statement stmt=con.createStatement();  
//
//            //step4 execute query  
            ResultSet rs=stmt.executeQuery("select * from months");  
            while(rs.next())  
            System.out.println(rs.getString(1));  
//0
//            //step5 close the connection object  
            con.close();  

        }catch(Exception e){ 
            System.out.println(e);}  

        }  
    
    
    
}
