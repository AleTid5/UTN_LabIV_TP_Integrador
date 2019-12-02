package Mappers;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

abstract class Mapper {
    protected final String getParameter(HttpServletRequest req, String key) {
        try {
            String parameter = req.getParameter(key);
            byte[] bytes = parameter.getBytes(StandardCharsets.ISO_8859_1);

            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return req.getParameter(key);
        }
    }
}
