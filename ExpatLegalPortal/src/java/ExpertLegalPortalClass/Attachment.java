/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpertLegalPortalClass;

/**
 *
 * @author Sein 90
 */
public class Attachment {
    String path;
    String flag;
    String caseId;
    String Id;
    String Remarks;

    public Attachment(String path, String flag, String caseId, String Id, String Remarks) {
        this.path = path;
        this.flag = flag;
        this.caseId = caseId;
        this.Id = Id;
        this.Remarks = Remarks;
    }

    public String getPath() {
        return path;
    }

    public String getFlag() {
        return flag;
    }

    public String getCaseId() {
        return caseId;
    }

    public String getId() {
        return Id;
    }

    public String getRemarks() {
        return Remarks;
    }
  
}
