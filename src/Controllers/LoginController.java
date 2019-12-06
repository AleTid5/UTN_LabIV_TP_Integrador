package Controllers;

import Services.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginController extends Controller {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			this.revokeSession(req);
			this.setContext(req, "");
			req.getRequestDispatcher("/App/Views/Unauthorized/Login/index.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			UserService.login(email, password);
			UserService.authenticate(req, email);
			String location = (Integer) req.getSession().getAttribute("userTypeId") == 1 ? "dashboard" : "courses";
			redirect(req, resp, location);
		} catch (Exception e) {
			redirect(req, resp, "login");
			e.printStackTrace();
		}
	}
}
