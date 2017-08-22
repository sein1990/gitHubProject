/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpertLegalPortalClass;

/**
 *
 * @author USER
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
 
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import java.text.DateFormatSymbols;
import javax.servlet.http.HttpServletRequest;
 
/**
 * Ugly demo of how to use jFreeChart
 * with iText. The output will have
 * the stair-case effect.
 *
 * @author Jee Vang
 *
 */
public class UglyDemo {
 
    
    public  void fmain() throws FileNotFoundException, DocumentException, IOException {
 
        getMonthForInt(m);
    }
    int m=3;
    String getMonthForInt(int m) {
    String month = "invalid";
    DateFormatSymbols dfs = new DateFormatSymbols();
    String[] months = dfs.getMonths();
    if (m >= 0 && m <= 11 ) {
        month = months[m];
    }
    return month;
}

}
