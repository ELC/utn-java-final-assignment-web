package util.exceptions;

public class LoginAppDataException extends Exception{
	
	public LoginAppDataException() {
		super("Error en el inicio de sesión");
    }

    public LoginAppDataException(String message) {
        super(message);
    }

    public LoginAppDataException(Throwable cause) {
        super(cause);
    }

    public LoginAppDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
