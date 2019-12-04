package Mappers;

import Models.Location;
import Models.Province;
import Models.User;

import javax.servlet.http.HttpServletRequest;

public class UserMapper extends Mapper {
    private User user;

    public UserMapper(HttpServletRequest req, Object type) {
        this.user = (User) type;
        user.setDNI(Integer.parseInt(getParameter(req, "DNI")));
        user.setName(getParameter(req, "name"));
        user.setLastname(getParameter(req, "lastname"));
        user.setBorndate(req.getParameter("borndate"));
        user.setAddress(getParameter(req, "address"));
        user.setLocation(new Location(Integer.parseInt(req.getParameter("location"))));
        user.setProvince(new Province(Integer.parseInt(req.getParameter("province"))));
        user.setEmail(getParameter(req, "email"));
        user.setPhoneNumber(getParameter(req, "phoneNumber"));
    }

    public final User getUser() {
        return this.user;
    }
}
