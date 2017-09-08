package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class newjsp1_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("    <!--ADD INPUT OF TYPE FILE WITH THE MULTIPLE OPTION-->\n");
      out.write("    <p>\n");
      out.write("        <input type=\"file\" id=\"file\" multiple />\n");
      out.write("    </p>\n");
      out.write("    \n");
      out.write("    <!--SHOW RESULT HERE-->\n");
      out.write("    <p id=\"fp\"></p>\n");
      out.write("\n");
      out.write("    <p>\n");
      out.write("        <input type=\"submit\" value=\"Show Details\" onclick=\"FileDetails()\" \n");
      out.write("            style=\"font:150% Georgia;font-style:italic;\" />\n");
      out.write("    </p>\n");
      out.write("    <script>\n");
      out.write("    function FileDetails() {\n");
      out.write("\n");
      out.write("        // GET THE FILE INPUT.\n");
      out.write("        var fi = document.getElementById('file');\n");
      out.write("\n");
      out.write("        // VALIDATE OR CHECK IF ANY FILE IS SELECTED.\n");
      out.write("        if (fi.files.length > 0) {\n");
      out.write("\n");
      out.write("            // THE TOTAL FILE COUNT.\n");
      out.write("            document.getElementById('fp').innerHTML =\n");
      out.write("                'Total Files: <b>' + fi.files.length + '</b></br >';\n");
      out.write("\n");
      out.write("            // RUN A LOOP TO CHECK EACH SELECTED FILE.\n");
      out.write("            for (var i = 0; i <= fi.files.length - 1; i++) {\n");
      out.write("\n");
      out.write("                var fname = fi.files.item(i).name;      // THE NAME OF THE FILE.\n");
      out.write("                var fsize = fi.files.item(i).size;      // THE SIZE OF THE FILE.\n");
      out.write("\n");
      out.write("                // SHOW THE EXTRACTED DETAILS OF THE FILE.\n");
      out.write("                document.getElementById('fp').innerHTML =\n");
      out.write("                    document.getElementById('fp').innerHTML + '<br /> ' +\n");
      out.write("                        fname + ' (<b>' + fsize + '</b> bytes)';\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("        else { \n");
      out.write("            alert('Please select a file.') \n");
      out.write("        }\n");
      out.write("    }\n");
      out.write("</script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
