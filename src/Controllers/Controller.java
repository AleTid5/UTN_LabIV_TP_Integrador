package Controllers;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exceptions.UnauthorizedException;
import Services.SessionService;
import Utils.Message;

@SuppressWarnings("serial")
public abstract class Controller extends HttpServlet {
	private static final String mainPath = "/Alejandro_Tidele_TP_Integrador";
	private static final String assetsPath = Controller.mainPath + "/App/Assets";
	private static final String authorizedPath = "/App/Views/Authorized/";
	private static final String unuthorizedPath = "/App/Views/Unauthorized/";
	private Boolean isAuthorized = true;
	protected ArrayList<Message> messages = null;

	private final void addSessionAttributes(HttpServletRequest req) {
		req.setAttribute("email", SessionService.getParameter("email"));
		req.setAttribute("name", SessionService.getParameter("name"));
		req.setAttribute("lastname", SessionService.getParameter("lastname"));
		req.setAttribute("userTypeId", SessionService.getParameter("userTypeId"));
	}
	
	protected final void setContext(HttpServletRequest req, String navbarTitle) {
		this.messages = new ArrayList<>();
		req.setAttribute("assetsPath", Controller.assetsPath);
		req.setAttribute("mainPath", Controller.mainPath);
		req.setAttribute("navbarTitle", navbarTitle);

		if (isAuthorized) this.addSessionAttributes(req);
	}

	protected final void redirect(HttpServletRequest req, HttpServletResponse resp, String location) {
		resp.setHeader("Location", req.getContextPath() + "/" + location);
		resp.setHeader("Cache-Control", "no-cache"); // Para que no redireccione siempre
		resp.setStatus(302);
	}

	protected final void revokeSession() throws IllegalStateException {
		this.isAuthorized = false;
		SessionService.clean();
	}

	protected final String getDispatch(String path, String action) {
		return (isAuthorized ? authorizedPath : unuthorizedPath) + path + "/" + action + ".jsp";
	}

	protected final Boolean isAdministrator() {
		return (Integer) SessionService.getParameter("userTypeId") == 1;
	}

	protected final void mustBeAdministrator() throws UnauthorizedException {
		if (! this.isAdministrator()) throw new UnauthorizedException();
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

	protected final String getParameter(HttpServletRequest req, String key) {
		try {
			String parameter = (String) req.getParameter(key); 
			byte[] bytes = parameter.getBytes(StandardCharsets.ISO_8859_1);
			
			return new String(bytes, StandardCharsets.UTF_8);
		} catch (Exception e) {
			return (String) req.getParameter(key);
		}
	}
}
