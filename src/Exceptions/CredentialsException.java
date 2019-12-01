package Exceptions;

@SuppressWarnings("serial")
public class CredentialsException extends Exception {
    public CredentialsException() {
        super("Las credenciales proporcionadas son inválidas");
    }
}
