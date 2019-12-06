package Controllers;

import Services.UserService;
import Utils.Errors;
import Utils.Message;

import java.io.IOException;
import java.util.ArrayList;

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
			this.messages = new ArrayList<>();
			String error = Errors.getError(req.getParameter("errorId"));
			if (error != null) messages.add(new Message(true, error));
			this.revokeSession(req);
			this.setContext(req, "");
			req.setAttribute("messages", messages);
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
			redirect(req, resp, "login?errorId=0");
			e.printStackTrace();
		}
	}
}
