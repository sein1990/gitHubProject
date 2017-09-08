
package ExpertLegalPortalClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class FileInfoOperation {
    Connection con=dbconnection.getConnection();
    QueryClass objQuery=new QueryClass();        
     public void addToAttachment(String UPLOAD_DIRECTORY,  String caseidupdate, String fileName,  int lastID, String exte)
     {
         try {
             int x = 0;
             PreparedStatement ps=null;
             PreparedStatement ps2=null;
             ResultSet rs=null;            
              String sql =objQuery.InsertAttachment();  
                ps2=con.prepareStatement(sql);
                
                ps2.setBoolean(1, true);
                ps2.setString(2, UPLOAD_DIRECTORY+lastID+exte);           
                ps2.setString(3,  caseidupdate);
                ps2.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(FileInfoOperation.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public int highestID(){
         String dbID = null;
         int x = 0;
         try {
             PreparedStatement ps=null;
             PreparedStatement ps2=null;
             ResultSet rs=null;
             String IDquery=objQuery.SelectHighestID();
             ps=con.prepareStatement(IDquery);
             rs=ps.executeQuery();
             if(rs.next())
             {
                 dbID=rs.getString(1);
             }
             if(dbID==null){
                 dbID="1";
                 x = Integer.parseInt(dbID);
             }
             else{
                 x = Integer.parseInt(dbID);
                 x++;
             }
         } catch (SQLException ex) {
             Logger.getLogger(FileInfoOperation.class.getName()).log(Level.SEVERE, null, ex);
         }
          return x;
    }
 public String selectAttachmentLastRecord(String caseidupdate){
            String attachmentID=null;
             try{
             PreparedStatement ps=null;
             ResultSet rs=null;
             String IDquery=objQuery.SelectAttachment(caseidupdate);
             ps=con.prepareStatement(IDquery);
             rs=ps.executeQuery();
             while(rs.next())
             {    
                 attachmentID=rs.getString(1);         
             }
         } catch (SQLException ex) {
             Logger.getLogger(FileInfoOperation.class.getName()).log(Level.SEVERE, null, ex);
         }
         return attachmentID;
    }
    public void updateLastAttachmentRemarks(String fileRemarks, String attachmentID){  
        try {
            String query2 =objQuery.updateRemarks();
            PreparedStatement preparedStmt = con.prepareStatement(query2);
            preparedStmt.setString(1, fileRemarks);
            preparedStmt.setString(2, attachmentID);

            preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FileInfoOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}