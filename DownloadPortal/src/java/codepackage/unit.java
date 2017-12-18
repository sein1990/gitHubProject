package codepackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class unit {
    String ipAddress, machineNumber, unit;

    public unit(String ipAddress, String machineNumber, String unit) {
        this.ipAddress = ipAddress;
        this.machineNumber = machineNumber;
        this.unit = unit;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public String getUnit() {
        return unit;
    }
}
