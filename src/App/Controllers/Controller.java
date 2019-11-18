package App.Controllers;

import App.Exceptions.UnauthorizedException;
import App.Services.SessionService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public abstract class Controller extends HttpServlet {
	private static final String mainPath = "/Alejandro_Tidele_TP_Integrador";
	private static final String assetsPath = Controller.mainPath + "/App/Assets";
	private static final String authorizedPath = "/App/Views/Authorized/";
	private static final String unuthorizedPath = "/App/Views/Unauthorized/";
	private Boolean isAuthorized = true;

	private final void addSessionAttributes(HttpServletRequest req) {
		req.setAttribute("email", SessionService.getParameter("email"));
		req.setAttribute("name", SessionService.getParameter("name"));
		req.setAttribute("lastname", SessionService.getParameter("lastname"));
		req.setAttribute("userTypeId", SessionService.getParameter("userTypeId"));
	}
	
	protected final void setContext(HttpServletRequest req, String navbarTitle) {
		req.setAttribute("assetsPath", Controller.assetsPath);
		req.setAttribute("mainPath", Controller.mainPath);
		req.setAttribute("navbarTitle", navbarTitle);

		if (isAuthorized) this.addSessionAttributes(req);
	}

	protected final void redirect(HttpServletRequest req, HttpServletResponse resp, String location) {
		resp.setHeader("Location", req.getContextPath() + "/" + location);
		resp.setHeader("Cache-Control", "no-cache"); // Para que no redireccione siempre
		resp.setStatus(301);
	}

	protected final void revokeSession() {
		this.isAuthorized = false;
		SessionService.clean();
	}

	protected final String getDispatch(String path, String action) {
		return (isAuthorized ? authorizedPath : unuthorizedPath) + path + "/" + action + ".jsp";
	}

	protected final void mustBeAdministrator() throws UnauthorizedException {
		if ((Integer) SessionService.getParameter("userTypeId") != 1)
			throw new UnauthorizedException();
	}
}
