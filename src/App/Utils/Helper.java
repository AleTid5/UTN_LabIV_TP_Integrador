package App.Utils;

import App.Models.Location;
import App.Models.Province;
import App.Services.GeoLocationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Helper {
    public static final void addLocationsAndProvinces(HttpServletRequest req) {
        StringBuilder optionLocations = new StringBuilder();
        StringBuilder optionProvinces = new StringBuilder();

        for (Location location : GeoLocationService.getLocations())
            optionLocations.append("<option value='").append(location.getId()).append("' ")
                    .append(! location.getStatus().equals("A") ? "disabled>" : ">")
                    .append(location.getName()).append("</option>");

        for (Province province : GeoLocationService.getProvinces())
            optionProvinces.append("<option value='").append(province.getId()).append("' ")
                    .append(! province.getStatus().equals("A")? "disabled>" : ">")
                    .append(province.getName()).append("</option>");

        req.setAttribute("optionLocations", optionLocations);
        req.setAttribute("optionProvinces", optionProvinces);
    }

    public static final void processResponse(HttpServletRequest req, HttpServletResponse resp, String key) {
        try {
        	if (! resp.getHeader(key).isEmpty()) {
                req.setAttribute(key, resp.getHeader(key));
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}
