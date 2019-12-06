package Controllers;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exceptions.UnauthorizedException;
import Interfaces.iController;
import Services.UserService;
import Utils.Message;

@SuppressWarnings("serial")
public abstract class Controller extends HttpServlet implements iController {
    private Boolean isAuthorized = true;
    protected ArrayList<Message> messages = null;

	protected final void setPaths(HttpServletRequest req, String navbarTitle) {
		req.setAttribute("assetsPath", Controller.assetsPath);
		req.setAttribute("mainPath", Controller.mainPath);
		req.setAttribute("navbarTitle", navbarTitle);
	}
	
	protected final void setContext(HttpServletRequest req, String navbarTitle) {
		this.messages = new ArrayList<>();
		this.setPaths(req, navbarTitle);
	}

	protected final void redirect(HttpServletRequest req, HttpServletResponse resp, String location) {
		resp.setHeader("Location", req.getContextPath() + "/" + location);
		resp.setHeader("Cache-Control", "no-cache"); // Para que no redireccione siempre
		resp.setStatus(302);
	}

	protected final void revokeSession(HttpServletRequest req) throws IllegalStateException {
		this.isAuthorized = false;
		req.getSession().invalidate();
	}

	protected final String getDispatch(String path, String action) {
		return (isAuthorized ? authorizedPath : unauthorizedPath) + path + "/" + action + ".jsp";
	}

	protected final void mustBeAdministrator(Integer userTypeId) throws UnauthorizedException {
		if (! userTypeId.equals(1)) throw new UnauthorizedException();
	}

	protected final void mustBeTeacher(Integer userTypeId) throws UnauthorizedException {
		if (! userTypeId.equals(2)) throw new UnauthorizedException();
	}

	protected final void mustBeLogged(Integer userTypeId) throws UnauthorizedException {
		if (userTypeId == null) throw new UnauthorizedException();
	}
	
	protected final Boolean compare(String v1, String v2) {
		Boolean comparison = null;
		
		try {
			comparison = v1.equals(v2);
		} catch (Exception e) {
			comparison = false;
		}
		
		return comparison;
	}

	protected final String getCleanPath(HttpServletRequest req, String index) {
		String dirtyURL = req.getRequestURL().toString();
		int start = dirtyURL.lastIndexOf(mainPath) + mainPath.length();
		String cleanPath = req.getRequestURL().substring(start);
		req.setAttribute("currentLink", cleanPath);
		cleanPath = cleanPath.substring(cleanPath.lastIndexOf("/") + 1);

		return cleanPath.equals(index) ? "index" : cleanPath;
	}

	protected final void handledCall(HttpServletRequest req, HttpServletResponse resp, Callable func) {
		try {
			func.call();
		}  catch (NullPointerException | UnauthorizedException e) {
			redirect(req, resp, "login");
		} catch (Exception e) {
			resp.setStatus(402); // Unprocessable entity
		}
	}
}
