package Controllers;

import Exceptions.UnauthorizedException;
import Models.Location;
import Models.Province;
import Models.Student;
import Models.User;
import Services.UserService;
import Utils.Errors;
import Utils.Helper;
import Utils.Message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/students/*")
public class StudentController extends Controller {
	private static final String path = "Students";

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURL().indexOf("/add") != -1 ? "add" :
				req.getRequestURL().indexOf("/edit") != -1 ? "edit" : "index";

		String docket = req.getParameter("user-docket");
		String error = Errors.getError(req.getParameter("errorId"));

		if (action.equals("add")) {
			Helper.addLocationsAndProvinces(req);

			if (docket != null)
				messages.add(new Message(false, "El legajo del alumno creado es " + docket));

		}

		if (action.equals("edit")) {
			User user = Helper.setUserData(req, 3);
			Helper.addLocationsAndProvinces(req, user.getLocation().getId(), user.getProvince().getId());
			if (docket != null) req.setAttribute("docket", Helper.setFlashMessage("Se ha editado exitosamente al alumno con legajo " + docket, false));
		};

		if (action.equals("index")) Helper.setUserList(req, 3);

		if (error != null) messages.add(new Message(true, error));

		req.setAttribute("messages", messages);
		req.getRequestDispatcher(getDispatch(path, action)).forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			this.mustBeAdministrator();
			this.setContext(req, "Alumnos");
			this.processRequest(req, resp);
		} catch (NullPointerException | UnauthorizedException e) {
			redirect(req, resp, "login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			this.mustBeAdministrator();
			Student student = new Student();
			student.setName(getParameter(req, "name"));
			student.setLastname(getParameter(req, "lastname"));
			student.setBorndate(req.getParameter("borndate"));
			student.setAddress(getParameter(req, "address"));
			student.setLocation(new Location(Integer.parseInt(req.getParameter("location"))));
			student.setProvince(new Province(Integer.parseInt(req.getParameter("province"))));
			student.setEmail(getParameter(req, "email"));
			student.setPhoneNumber(getParameter(req, "phoneNumber"));

			Boolean isEditing = this.compare(req.getParameter("method"), "PUT");
			String uri;

			if (isEditing) {
				student.setDocket(Integer.parseInt(req.getParameter("docket")));
				UserService.edit(student);
				uri = "edit?" + (student.getErrorKey() != null ? "errorId=" + student.getErrorKey() : "user-docket=" + student.getDocket()) +
						"&docket=" + student.getDocket();
			} else {
				UserService.add(student);
				uri = "add?" + (student.getErrorKey() != null ? "errorId=" + student.getErrorKey() :
						"user-docket=" + student.getDocket());
			}

			redirect(req, resp, "students/" + uri);
		} catch (NullPointerException | UnauthorizedException e) {
			redirect(req, resp, "login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
