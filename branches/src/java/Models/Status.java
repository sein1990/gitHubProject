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
public class Status {
    private String StatusId;
    private String StatusName;

    public Status(String StatusId, String StatusName) {
        this.StatusId = StatusId;
        this.StatusName = StatusName;
    }

    public String getStatusId() {
        return StatusId;
    }

    public void setStatusId(String StatusId) {
        this.StatusId = StatusId;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String StatusName) {
        this.StatusName = StatusName;
    }
    
    
    
    
}
