package codepackage;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class readTextFile {
private static final String FILENAME = "C:\\Users\\USER\\Documents\\NetBeansProjects\\gitHubProject\\DownloadPortal\\biometricsMachineIPAddress.txt";
//public Vector<String>() 
public Vector<unit> biometricsDetails = new Vector();
 public void biometricInfo(){
         
{
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(FILENAME));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                
                String[] parts = line.split("#");
                String ipaddress = parts[0]; // 004-
                String machineNumber = parts[1]; // 034556
                String bioUnit = parts[2]; 
                
                biometricsDetails.add(new unit(ipaddress, machineNumber, bioUnit));
               System.out.println(biometricsDetails.size());
	
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
         
    }    
  
}
 public static void main(String[] args) {        	
    readTextFile obj=new readTextFile();
    obj.biometricInfo();
}
}
 
 
 
 
