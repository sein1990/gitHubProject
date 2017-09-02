
package ExpertLegalPortalClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class dbconnection
{
    
                       public static Connection getConnection()
    {
        
                      Connection con = null;
        try
{
//            Class.forName("com.mysql.jdbc.Driver");
//            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ExpatDisciplinaryPortal","root","1234");
//            System.out.println("connected");
            Class.forName("oracle.jdbc.driver.OracleDriver");
             con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.72:1521:orcl","hrpayroll","hrevgavg2016");
            System.out.println("connected");
        }
        catch(Exception e){
            //e.printStackTrace();
        }
        
        return con;
    }
}
