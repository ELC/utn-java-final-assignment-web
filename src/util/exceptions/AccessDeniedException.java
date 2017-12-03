package util.exceptions;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccessDeniedException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public AccessDeniedException() {
		super("Access Denied");
    }

    public AccessDeniedException(String message, Level errorLevel) {
        this(null, message, errorLevel);
    }

    public AccessDeniedException(Throwable cause) {
        super(cause);
    }

    private AccessDeniedException(String message, Throwable e) {
        super(message, e);
    }
    	
	public AccessDeniedException(Throwable e, String message, Level errorLevel){
		this(message, e);
		Logger logger = LogManager.getLogger(getClass());
		logger.log(errorLevel, message);
	}
}
