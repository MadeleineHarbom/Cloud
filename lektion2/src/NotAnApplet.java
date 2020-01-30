

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NotAnApplet
 */
@WebServlet("/NotAnApplet")
public class NotAnApplet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotAnApplet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		handleRequest(request, response);
		String s = request.getAttribute("first") + ", " + request.getAttribute("second") + ", "+ request.getAttribute("third") + "!";
		
		response.getWriter().append("Served at: ").append(request.getContextPath() + "\n" + s);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setAttribute("first", "First Hello");
		request.setAttribute("second", "Second Hello");
		request.setAttribute("third", "Third Hello");
	}

}
