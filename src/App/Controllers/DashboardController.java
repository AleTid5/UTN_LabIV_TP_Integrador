package App.Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/dashboard")
public class DashboardController extends Controller {
	private static final String path = "Dashboard";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			this.setContext(req, "Dashboard");

			req.getRequestDispatcher(getDispatch(path, "index")).forward(req, resp);
		} catch (NullPointerException e) {
			redirect(req, resp, "login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
