
package ExpertLegalPortalClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class FileInfoOperation {
     Connection con=dbconnection.getConnection();
       QueryClass objQuery=new QueryClass();
//                 
//        public String getAttachment( ){
//         String HighestValueQuery=objQuery.selectHighestAttachmentValue();
//        String attachmentID=null;
//                  try {
//             int counter = 0; 
//             PreparedStatement ps=null;
//             ResultSet rs=null;
//             ps=con.prepareStatement(HighestValueQuery);
//             rs=ps.executeQuery();
//             if(rs.next())
//             { 
//                 attachmentID=rs.getString(1);
//             }
//             if(attachmentID==null){
//                 //  dbID="A1-1";
//                 attachmentID="attachment-"+"1";
//             }
//             else{
//                 
//            String[] parts = attachmentID.split("-");
//            String part1attachment = parts[0]; // 004
//            String part2attachment = parts[1]; 
//                
//                 attachmentID=part1attachment;
//             }       
//             
//               String query2 =objQuery.UpdateAttachment();
//                    PreparedStatement preparedStmt = con.prepareStatement(query2);
//                    preparedStmt.setString(1, attachmentID);                     
//                    preparedStmt.executeUpdate();
//             
//         } catch (SQLException ex) {
//             Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
//         }
//         return attachmentID;
//     }
//     
//     private void updateattachmentID(String Query,int counter){
//         try {
//             PreparedStatement preparedStmt = con.prepareStatement(Query);
//             preparedStmt.setInt(1, counter);
//             preparedStmt.executeUpdate();
//         } catch (SQLException ex) {
//             Logger.getLogger(ExpertLegalPortalOperation.class.getName()).log(Level.SEVERE, null, ex);
//         }
//     }
//            
//            
//           
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
}