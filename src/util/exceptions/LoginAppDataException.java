package util.exceptions;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginAppDataException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public LoginAppDataException() {
		super("Error in the logging");
    }

    public LoginAppDataException(String message, Level errorLevel) {
        this(null, message, errorLevel);
    }

    public LoginAppDataException(Throwable cause) {
        super(cause);
    }

    private LoginAppDataException(String message, Throwable e) {
        super(message, e);
    }
    	
	public LoginAppDataException(Throwable e, String message, Level errorLevel){
		this(message, e);
		Logger logger = LogManager.getLogger(getClass());
		logger.log(errorLevel, message);
	}
}
