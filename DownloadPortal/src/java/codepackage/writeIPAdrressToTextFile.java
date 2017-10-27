/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codepackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class writeIPAdrressToTextFile {
    int i=0;
       public void writeText()
    {
        pathClass pathObject=new pathClass();
        String path=pathObject.path();
           try {
                String fileName="operation.txt";
                String strPath=path+fileName;
                //File creation
                File strFile = new File(strPath);
                boolean fileCreated = strFile.createNewFile();
                //File appending
                Writer objWriter = new BufferedWriter(new FileWriter(strFile));
                objWriter.write("Download");
                objWriter.flush();
                objWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(writeIPAdrressToTextFile.class.getName()).log(Level.SEVERE, null, ex);
            }
    } 
}
