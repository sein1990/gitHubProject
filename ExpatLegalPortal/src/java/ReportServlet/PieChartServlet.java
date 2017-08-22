/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportServlet;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author USER
 */
@WebServlet(name = "PieChartServlet", urlPatterns = {"/PieChartServlet"})
public class PieChartServlet extends HttpServlet {
    public PieChartServlet() {
/* No code in the constructor for this demonstration */
}

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("image/png"); /* Set the HTTP Response Type */
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PieChartServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PieChartServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         OutputStream out = response.getOutputStream(); /* Get the output stream from the response object */
        try {
                DefaultPieDataset myServletPieChart = new DefaultPieDataset();
                /* We will now get the values posted to us from the HTML form, to generate a dynamic pie chart */
                /* to get the form values we use request.getParameter method. We have to convert this to Double format to 
                pass this as an input to our pie chart*/
                /* The NAME used in HTML form will serve as input to getParameter */
                myServletPieChart.setValue("Maths",Double.parseDouble(request.getParameter("Maths")));
                myServletPieChart.setValue("Physics", Double.parseDouble(request.getParameter("Physics")));
                myServletPieChart.setValue("Chemistry", Double.parseDouble(request.getParameter("Chemistry")));
                myServletPieChart.setValue("Biology", Double.parseDouble(request.getParameter("Biology")));
                myServletPieChart.setValue("English",Double.parseDouble(request.getParameter("English")));        
                JFreeChart mychart = ChartFactory.createPieChart("HTTP Servlet - Dynamic Pie Chart Example",myServletPieChart,true,true,false);
                /* We use the configurator to define labels for the chart, which can be shown on image also */
                PiePlot ColorConfigurator = (PiePlot) mychart.getPlot();
                ColorConfigurator.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{1}"));
                ColorConfigurator.setLabelBackgroundPaint(new Color(220, 220, 220));  
               
                /* Send a big chart back to the browser */
                ChartUtilities.writeChartAsPNG(out, mychart, 640, 480);/* Write the data to the output stream */
        }
        catch (Exception e) {
                System.err.println(e.toString()); /* Throw exceptions to log files */
        }
        finally {
                out.close();/* Close the output stream */
        }
          processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
