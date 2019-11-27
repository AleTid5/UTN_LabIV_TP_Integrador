package Utils;

import javax.servlet.http.HttpServletRequest;

import Models.Location;
import Models.Province;
import Models.User;
import Services.GeoLocationService;
import Services.UserService;

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

    public static final void addLocationsAndProvinces(HttpServletRequest req, Integer locationId, Integer provinceId) {
        StringBuilder optionLocations = new StringBuilder();
        StringBuilder optionProvinces = new StringBuilder();

        for (Location location : GeoLocationService.getLocations())
            optionLocations.append("<option value='").append(location.getId()).append("' ")
                    .append(location.getId().equals(locationId) ? "selected " : "")
                    .append(! location.getStatus().equals("A") ? "disabled>" : ">")
                    .append(location.getName()).append("</option>");

        for (Province province : GeoLocationService.getProvinces())
            optionProvinces.append("<option value='").append(province.getId()).append("' ")
                    .append(province.getId().equals(provinceId) ? "selected " : "")
                    .append(! province.getStatus().equals("A")? "disabled>" : ">")
                    .append(province.getName()).append("</option>");

        req.setAttribute("optionLocations", optionLocations);
        req.setAttribute("optionProvinces", optionProvinces);
    }

    public static final void setUserList(HttpServletRequest req, Integer userTypeId) {
        StringBuilder userList = new StringBuilder();
        String type = (userTypeId == 2 ? "teachers" : "students");

        for (User user : UserService.list(userTypeId)) {
            userList.append("<tr>");
            userList.append("<td>").append(user.getDocket()).append("</td>");
            userList.append("<td>").append(user.getLastname()).append(", ").append(user.getName()).append("</td>");
            userList.append("<td>").append(user.getBorndate(true)).append("</td>");
            userList.append("<td>").append(user.getAddress()).append("</td>");
            userList.append("<td>").append(user.getLocation()).append("</td>");
            userList.append("<td>").append(user.getProvince()).append("</td>");
            userList.append("<td>").append(user.getEmail()).append("</td>");
            userList.append("<td>").append(user.getPhoneNumber()).append("</td>");
            userList.append("<td>" + "<a href='" + type + "/edit?docket=")
                    .append(user.getDocket())
                    .append("' class='btn btn-link btn-warning btn-just-icon edit'><i class='material-icons'>dvr</i></a>")
                    .append("<a href='" + type + "/remove?docket=")
                    .append(user.getDocket())
                    .append("' class='btn btn-link btn-danger btn-just-icon remove'><i class='material-icons'>close</i></a>")
                    .append("</td>");
            userList.append("</tr>");
        }

        req.setAttribute("userList", userList);
    }

    public static final String setFlashMessage(String message, Boolean isError) {
        return message == null ? "" :
                "<div class='alert alert-" + (isError ? "rose" : "success") + "'>" +
                "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>" +
                "<i class='material-icons'>close</i>" +
                "</button>" +
                "<span>" +
                "<b>" + (isError ? "Error" : "Operación exitosa") + "</b><br>" + message + "</span>" +
                "</div>";
    }

    public static final User setUserData(HttpServletRequest req, Integer userTypeId) {
    	String strDocket = req.getParameter("docket");
        Integer docket = Integer.parseInt(strDocket == null ? "0" : strDocket);
        User user = UserService.getUserByDocket(docket, userTypeId);
        req.setAttribute("user", user);

        return user;
    }
}
