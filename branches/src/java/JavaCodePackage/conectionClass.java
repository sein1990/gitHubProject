package JavaCodePackage;

import java.sql.*;  
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */

public class conectionClass {
    
    Connection con;
    Statement stmt;
   
    
    public void closeConnection(){
        try {  
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(conectionClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public conectionClass(){
        
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@192.168.14.77:1521:orcl","mgpayroll","payroll");
            
            

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(conectionClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(conectionClass.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   
   
    }
    
    
   
    public ResultSet excuteQuery(String Query){
        ResultSet rs = null;
        try{  
            
            stmt=con.createStatement();  
            rs=stmt.executeQuery(Query);  
          
        }catch(Exception e){ 
          
        }  

         
    
        return rs;
    
    }
}