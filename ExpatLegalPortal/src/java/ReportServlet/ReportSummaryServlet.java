/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportServlet;

import SummaryReport.A1_Report;
import SummaryReport.A2_Report;
import SummaryReport.A3_Report;
import SummaryReport.Dis_Report;
import SummaryReport.EmployeePassedAway_Report;
import SummaryReport.LeaveExtension_Report;
import SummaryReport.NativeShortageStaff_Report;
import SummaryReport.SalaryStoppedDueToLossOrShortage_Report;
import SummaryReport.TerminatedEmployeeDueToLossOrShortage_Report;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
@WebServlet(name = "ReportSummaryServlet", urlPatterns = {"/ReportSummaryServlet"})
public class ReportSummaryServlet extends HttpServlet {

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
 
        try {
            response.setContentType("text/html;charset=UTF-8");
            String caseidupdate=request.getParameter("IDcase");
            String[] parts=caseidupdate.split("-");
            String part1=parts[0];
            String part2=parts[1];
            if(part1.equals("A1")){          
                A1_Report file = new A1_Report();
                file.A1Report(caseidupdate);
                String DEST = file.getDEST();
                String pdfName = file.getPdfName();
                file.pp(response);
            }
                else if(part1.equals("A2")){
                A2_Report file = new A2_Report();
                file.A2Report(caseidupdate);
                String DEST = file.getDEST();
                String pdfName = file.getPdfName();
                file.pp(response);
            }
            else if(part1.equals("A3")){
                A3_Report file = new A3_Report();
                file.A3Report(caseidupdate);
                String DEST = file.getDEST();
                String pdfName = file.getPdfName();
                file.pp(response);
            }
            else if(part1.equals("A4")){
                Dis_Report file = new Dis_Report();
                file.DisReport(caseidupdate);
                String DEST = file.getDEST();
                String pdfName = file.getPdfName();
                file.pp(response);
            }
            else if(part1.equals("A6")){
               EmployeePassedAway_Report file = new EmployeePassedAway_Report();
                file.EmployeePassedAwayReport(caseidupdate);
                String DEST = file.getDEST();
                String pdfName = file.getPdfName();
                file.pp(response);
            }
             else if(part1.equals("A5")){
                 SalaryStoppedDueToLossOrShortage_Report file = new SalaryStoppedDueToLossOrShortage_Report();
                file.SalaryStoppedDueToLossOrShortageReport(caseidupdate);
                String DEST = file.getDEST();
                String pdfName = file.getPdfName();
                file.pp(response);
            }
            else if(part1.equals("A7")){
                NativeShortageStaff_Report file = new NativeShortageStaff_Report();
                file.NativeShortageStaffReport(caseidupdate);
                String DEST = file.getDEST();
                String pdfName = file.getPdfName();
                file.pp(response);
            }
               else if(part1.equals("A8")){
                TerminatedEmployeeDueToLossOrShortage_Report file = new TerminatedEmployeeDueToLossOrShortage_Report();
                file.TerminatedEmployeeDueToLossOrShortageReport(caseidupdate);
                String DEST = file.getDEST();
                String pdfName = file.getPdfName();
                file.pp(response);
            }
            else if(part1.equals("A9")){
                LeaveExtension_Report file = new  LeaveExtension_Report();
                file. LeaveExtensionReport(caseidupdate);
                String DEST = file.getDEST();
                String pdfName = file.getPdfName();
                file.pp(response);
               //kjkjhjghg
            }
        } catch (DocumentException ex) {
            Logger.getLogger(ReportSummaryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReportSummaryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //out.close();
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
        processRequest(request, response);
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
