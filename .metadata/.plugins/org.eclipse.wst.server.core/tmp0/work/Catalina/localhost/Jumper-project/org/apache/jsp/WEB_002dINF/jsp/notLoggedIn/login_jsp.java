/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.30
 * Generated at: 2020-01-28 16:57:16 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.notLoggedIn;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("  <meta charset=\"utf-8\">\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n");
      out.write("\n");
      out.write("  <!-- Bootstrap CSS -->\n");
      out.write("  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\n");
      out.write("\n");
      out.write("  <!-- Bootstrap glyphicons (icons) -->\n");
      out.write("  <link href=\"https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\" rel=\"stylesheet\">\n");
      out.write("    \n");
      out.write("  <!-- our CSS -->\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/ourStyles.css\"> \n");
      out.write("  \n");
      out.write("  <!-- our JavaScript -->\n");
      out.write("  <script src=\"../js/ourScripts.js\" defer></script>  \n");
      out.write("  \n");
      out.write("  <title>Login page</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("    <!-- container/grid der indeholder alle elementerne i body-tagget -->           \n");
      out.write("    <div class=\"container\">\n");
      out.write("    \n");
      out.write("        <!-- navigations-menu -->\n");
      out.write("        <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\n");
      out.write("            <a class=\"navbar-brand\" href=\"#\">Jumperr</a>\n");
      out.write("            \n");
      out.write("            <!-- ændrer udseendet på navigations-menuen på en mobil-device -->\n");
      out.write("            <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\">\n");
      out.write("                <span class=\"navbar-toggler-icon\"></span>\n");
      out.write("            </button>           \n");
      out.write("            \n");
      out.write("            <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n");
      out.write("                <ul class=\"navbar-nav ml-auto\">\n");
      out.write("                \n");
      out.write("                    <!--  Home -->\n");
      out.write("                    <li class=\"nav-item\">\n");
      out.write("                        <a class=\"nav-link active\" href=\"/\">Home</a>\n");
      out.write("                    </li>\n");
      out.write("                    \n");
      out.write("                    <!--  Register user -->\n");
      out.write("                    <li class=\"nav-item\">\n");
      out.write("                        <a class=\"nav-link\" href=\"/CreateUser\">Register</a>\n");
      out.write("                    </li>                      \n");
      out.write("                    \n");
      out.write("                    <!--  About - den har en dropdown-menu -->\n");
      out.write("                    <li class=\"nav-item dropdown\">\n");
      out.write("                        <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" data-toggle=\"dropdown\">\n");
      out.write("                            About\n");
      out.write("                        </a>\n");
      out.write("                        <div class=\"dropdown-menu\">\n");
      out.write("                            <a class=\"dropdown-item\" href=\"/AboutUs\">About us</a>\n");
      out.write("                            <a class=\"dropdown-item\" href=\"/FAQ\">FAQ</a>\n");
      out.write("                            <div class=\"dropdown-divider\"></div>\n");
      out.write("                            <a class=\"dropdown-item\" href=\"/ContactUs\">Contact us</a>\n");
      out.write("                        </div>\n");
      out.write("                    </li>\n");
      out.write("                    \n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("        </nav>         \n");
      out.write("    \n");
      out.write("        <!-- Jumbotron - det er en form for header eller en udvidet header (hero-section) -->\n");
      out.write("        <div class=\"jumbotron\"> \n");
      out.write("        \n");
      out.write("            <h1>Jumperr, a modern way of traveling!</h1>\n");
      out.write("            \n");
      out.write("            <p class=\"lead\">\n");
      out.write("                Jumperr utilizes the capacity of cars through a simple matchmaking platform -\n");
      out.write("                A hybrid solution between GoMore and TravellingCard, which is 40% faster transport at competitive prices\n");
      out.write("            </p>\n");
      out.write("            \n");
      out.write("            <hr class=\"my-5\">                      \n");
      out.write(" \n");
      out.write("            <!-- login-formular -->               \n");
      out.write("            <main class=\"my-form\">\n");
      out.write("                <div class=\"container col-md-8\">\n");
      out.write("                    <div class=\"row justify-content-center\">\n");
      out.write("                        <div class=\"col-md-10\">\n");
      out.write("                            <!-- fejl meddelelse -->\n");
      out.write("                            <div id=\"error\" class=\"alert alert-danger alert-dismissible\" role=\"alert\">\n");
      out.write("                               <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n");
      out.write("                               ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${error}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("                            </div>  \n");
      out.write("                                <div class=\"card\">\n");
      out.write("                                    <div class=\"card-header\">Login</div>\n");
      out.write("                                    <div class=\"card-body\">\n");
      out.write("                                        <form name=\"my-form\" action=\"/\" method=\"post\">\n");
      out.write("            \n");
      out.write("                                            <div class=\"form-group row\">\n");
      out.write("                                                <label for=\"username\" class=\"col-md-4 col-form-label text-md-right\">Username</label>\n");
      out.write("                                                <div class=\"col-md-6\">\n");
      out.write("                                                    <input type=\"text\" id=\"username\" class=\"form-control\" name=\"username\" required=\"true\">\n");
      out.write("                                                </div>\n");
      out.write("                                            </div>\n");
      out.write("            \n");
      out.write("                                            <div class=\"form-group row\">\n");
      out.write("                                                <label for=\"password\" class=\"col-md-4 col-form-label text-md-right\">Password</label>\n");
      out.write("                                                <div class=\"col-md-6\">\n");
      out.write("                                                    <input type=\"password\" id=\"password\" class=\"form-control\" name=\"password\" required=\"true\">\n");
      out.write("                                                </div>\n");
      out.write("                                            </div>                                   \n");
      out.write("            \n");
      out.write("                                            <div class=\"col-md-6 offset-md-4\">\n");
      out.write("                                                <button type=\"submit\" class=\"btn btn-primary\">\n");
      out.write("                                                Login\n");
      out.write("                                                </button>\n");
      out.write("                                            </div>                                          \n");
      out.write("                                       </form>\n");
      out.write("                                    </div> <!-- card-body -->\n");
      out.write("                                </div> <!-- card -->\n");
      out.write("                            </div> <!-- yderste column -->\n");
      out.write("                        </div> <!-- yderste row -->\n");
      out.write("                    </div>  <!-- container i login formularen -->         \n");
      out.write("               </main> <!-- login formularen -->\n");
      out.write("           </div> <!-- jumbotron -->\n");
      out.write("     </div> <!-- yderste container -->\n");
      out.write("\n");
      out.write("    <!-- Optional JavaScript -->\n");
      out.write("    <!-- jQuery first, then Popper.js, then Bootstrap JS -->\n");
      out.write("    <script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>\n");
      out.write("    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script>\n");
      out.write("    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}