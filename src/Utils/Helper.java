package Utils;

import javax.servlet.http.HttpServletRequest;

import Services.GeoLocationService;
import Services.UserService;

public class Helper {
    public static final void addLocationsAndProvinces(HttpServletRequest req) {
        req.setAttribute("locations", GeoLocationService.getLocations());
        req.setAttribute("provinces", GeoLocationService.getProvinces());
    }

    public static final void setUser(HttpServletRequest req, Integer userTypeId) {
    	String strDocket = req.getParameter("docket");
        Integer docket = Integer.parseInt(strDocket == null ? "0" : strDocket);
        req.setAttribute("user", UserService.getUserByDocket(docket, userTypeId));
    }
}
