/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author user
 */
public class SummryLeaveDataClass {
    
    String BranchId;
    String EmpId;
    int[] leavesData;
     
     
     
     
//    int[] processedData;
   
//    int[] TotalData;

//    public String getStrProcessedData(){
//       return processedData[0]+"-"+processedData[1]+"-"+processedData[2]+"-"+
//              processedData[3]+"-"+processedData[4]+"-"+processedData[5];
//    }
//    
    public String getStrUnProcessedData(){
       return leavesData[0]+"-"+leavesData[1]+"-"+leavesData[2]+"-"+
              leavesData[3]+"-"+leavesData[4]+"-"+leavesData[5];
    }
//    
//    public String getStrTotalData(){
//       return TotalData[0]+"-"+TotalData[1]+"-"+TotalData[2]+"-"+
//              TotalData[3]+"-"+TotalData[4]+"-"+TotalData[5];
//    }
//    
//    public SummryLeaveDataClass(String BranchId, String EmpId,String StrProcessedData,String StrUnProcessedData,String StrTotalData ){
//        String[] s1 = StrProcessedData.split("-");
//        String[] s2 = StrUnProcessedData.split("-");
//        String[] s3 = StrTotalData.split("-");
//        
//        processedData = new int[6];
//        unProcessedData = new int[6];
//        TotalData = new int[6];
//        
//        for (int i = 0; i < s3.length; i++) {
//            processedData[i] = Integer.parseInt(s1[i]);
//            unProcessedData[i] = Integer.parseInt(s2[i]);
//            TotalData[i] = Integer.parseInt(s3[i]);
//        }        
//        
//        this.BranchId = BranchId;
//        this.EmpId = EmpId;
//        
//
//    }
//    
//    
//    
//    public SummryLeaveDataClass(String BranchId, String EmpId, int[] processedDate, int[] unProcessedDate, int[] TotalDate) {
//        this.BranchId = BranchId;
//        this.EmpId = EmpId;
//        this.processedData = processedDate;
//        this.unProcessedData = unProcessedDate;
//        this.TotalData = TotalDate;
//    }
//    
//    public String getBranchId() {
//        return BranchId;
//    }
//
//    public void setBranchId(String BranchId) {
//        this.BranchId = BranchId;
//    }
//
//    public String getEmpId() {
//        return EmpId;
//    }
//
//    public void setEmpId(String EmpId) {
//        this.EmpId = EmpId;
//    }
//
//    public int[] getProcessedDate() {
//        return processedData;
//    }
//
//    public void setProcessedDate(int[] processedDate) {
//        this.processedData = processedDate;
//    }
//
//    public int[] getUnProcessedDate() {
//        return unProcessedData;
//    }
//
//    public void setUnProcessedDate(int[] unProcessedDate) {
//        this.unProcessedData = unProcessedDate;
//    }
//
//    public int[] getTotalDate() {
//        return TotalData;
//    }
//
//    public void setTotalDate(int[] TotalDate) {
//        this.TotalData = TotalDate;
//    }
//    
//   

    public SummryLeaveDataClass(String BranchId, String EmpId, int[] leavesData) {
        this.BranchId = BranchId;
        this.EmpId = EmpId;
        this.leavesData = leavesData;
    }

    public String getBranchId() {
        return BranchId;
    }

    public String getEmpId() {
        return EmpId;
    }

    public int[] getLeavesData() {
        return leavesData;
    }
    
     public SummryLeaveDataClass(String BranchId, String EmpId,String StrUnProcessedData ){
        String[] s2 = StrUnProcessedData.split("-");
        
        leavesData = new int[6];
        
        for (int i = 0; i < s2.length; i++) {
            leavesData[i] = Integer.parseInt(s2[i]);
         
        }        
        
        this.BranchId = BranchId;
        this.EmpId = EmpId;
        

    }
    
    
}
