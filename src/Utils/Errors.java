package Utils;

import java.util.HashMap;

public class Errors {
    @SuppressWarnings("serial")
	private static final HashMap<Integer, String> errorList = new HashMap<Integer, String>(){
        {
            put(0, "Las credenciales son inválidas.");
            put(1, "Ya existe un usuario con el E-Mail ingresado.");
            put(2, "Ya existe un usuario con el DNI ingresado.");
            put(3, "Ya existe un curso con los datos asignados.");
        }
    };

    public static final String getError(String key) {
        try {
        	return errorList.get(Integer.parseInt(key));
        } catch (Exception e) {
        	return null;
        }
    }
}
