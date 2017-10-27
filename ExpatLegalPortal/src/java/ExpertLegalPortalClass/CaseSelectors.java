/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpertLegalPortalClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class CaseSelectors {
    private  Vector<Case> EmpData;
    
    public CaseSelectors(){
        EmpData = new Vector<Case>();
        setData();
    }

    public Vector<Case> getEmpData() {
        return EmpData;
    }
    QueryClass objQuery=new QueryClass();
    private void setData(){
        try {
            Connection con=dbconnection.getConnection();
            PreparedStatement ps=null;
            PreparedStatement ps2=null;
            ResultSet rs=null;
            String IDquery=objQuery.SearchResult();
            ps=con.prepareStatement(IDquery);
            rs=ps.executeQuery();
            while(rs.next())
            {
            Case e = new Case(rs.getString(1),rs.getString(3),rs.getString(4),rs.getString(2), rs.getString(5));  
               EmpData.add(e);               
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CaseSelectors.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
}
