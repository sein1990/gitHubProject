
import JavaCodePackage.conectionClass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.jstl.sql.Result;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class TempMainClass {
    public static void main(String[] args){
        try {
            System.out.print("gggggg");
            conectionClass c = new conectionClass();
            ResultSet res = c.excuteQuery("select comp_id,comp_name,op_days from comp_info");
            while(res.next()) {
                System.out.println(res.getString(1)+" "+res.getString(2)+res.getString(3));
            }
              c.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(TempMainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
       
    }
}
