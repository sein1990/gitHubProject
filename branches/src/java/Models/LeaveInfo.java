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
public class LeaveInfo {
    int BrnchId;
    String Code;
    int NumberOfDays;
    int Period;
    String Description;

    public LeaveInfo(int BrnchId, String Code, int NumberOfDays, int Period, String Description) {
        this.BrnchId = BrnchId;
        this.Code = Code;
        this.NumberOfDays = NumberOfDays;
        this.Period = Period;
        this.Description = Description;
    }
    
    public int getBrnchId() {
        return BrnchId;
    }

    public void setBrnchId(int BrnchId) {
        this.BrnchId = BrnchId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getNumberOfDays() {
        return NumberOfDays;
    }

    public void setNumberOfDays(int NumberOfDays) {
        this.NumberOfDays = NumberOfDays;
    }

    public int getPeriod() {
        return Period;
    }

    public void setPeriod(int Period) {
        this.Period = Period;
    }
    
    
    
}
