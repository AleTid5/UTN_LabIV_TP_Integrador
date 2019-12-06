package Utils;

import javax.servlet.http.HttpServletRequest;

import Services.GeoLocationService;
import Services.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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

    public static final String getPretty(String date, Boolean toView) {
        String borndate = date;

        try {
            SimpleDateFormat viewFormat = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat databaseFormat = new SimpleDateFormat("yyyy-MM-dd");
            borndate = toView ? viewFormat.format(databaseFormat.parse(borndate)) : databaseFormat.format(viewFormat.parse(borndate));
        } catch (ParseException e) {
            borndate = date;
        }

        return borndate;
    }
}
