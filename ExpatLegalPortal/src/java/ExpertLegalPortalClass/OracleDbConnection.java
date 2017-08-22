/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpertLegalPortalClass;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author USER
 */
public class OracleDbConnection {
      
    public static Connection OracleGetConnection()
    {
        Connection OracleConnection = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            OracleConnection=DriverManager.getConnection(
            "jdbc:oracle:thin:@192.168.14.72:1521:orcl","hrpayroll","hrevgavg2016");
  System.out.println("connected");
            
        }catch(Exception e){
            //e.printStackTrace();
        }
        
        return OracleConnection;
    }
}
    

