package App.Exceptions;

@SuppressWarnings("serial")
public class UnauthorizedException extends Exception {
    public UnauthorizedException() {
        super("No tienes permiso para acceder a ése módulo.");
    }
}
