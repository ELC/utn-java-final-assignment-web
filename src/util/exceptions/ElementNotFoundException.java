package util.exceptions;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ElementNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public ElementNotFoundException() {
		super("Access Denied");
    }

    public ElementNotFoundException(String message, Level errorLevel) {
        this(null, message, errorLevel);
    }

    public ElementNotFoundException(Throwable cause) {
        super(cause);
    }

    private ElementNotFoundException(String message, Throwable e) {
        super(message, e);
    }
    	
	public ElementNotFoundException(Throwable e, String message, Level errorLevel){
		this(message, e);
		Logger logger = LogManager.getLogger(getClass());
		logger.log(errorLevel, message);
	}
}
