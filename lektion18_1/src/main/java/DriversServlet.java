

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.made.lektion18_1.Driver;
import com.made.lektion18_1.MyInitScript;
import com.made.lektion18_1.Storage;


/**
 * Servlet implementation class DriversServlet
 */
@WebServlet(name="drivers", urlPatterns="/drivers")
public class DriversServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriversServlet() {
    	super();
    	MyInitScript.initFunc();
        
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			//http://localhost:8080/drivers?userid=1
			String userID = request.getParameter("userid");
			int userid = Integer.parseInt(userID);
			for (Driver driver : Storage.drivers) {
				if (driver.id == userid) {
					response.getWriter().append(new Gson().toJson(driver));
					return;
				}
			}
		} catch (Exception e) {
			response.getWriter().append("catching"); //kommer aldrig her ind
		}
		
		
		String driverJsonString = new Gson().toJson(Storage.drivers.get(0));
		String driversJsonString = new Gson().toJson(Storage.drivers);
		response.getWriter().append("Served at: ").append(request.getContextPath() + "\n length: " +
		+ Storage.drivers.size()).append(driverJsonString).append(driversJsonString);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//localhost:8080/drivers?userid=9&username=nukku&name=Bertil
		String userID = request.getParameter("userid");
		int userid = Integer.parseInt(userID);
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		MyInitScript.createDriverLocally(userid, name, username);
		doGet(request, response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userID = request.getParameter("userid");
		int userid = Integer.parseInt(userID);
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		MyInitScript.updateDriver(userid, name, username);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userID = request.getParameter("userid");
		int userid = Integer.parseInt(userID);
		MyInitScript.deleteDriver(userid);
	}
	
	

}
