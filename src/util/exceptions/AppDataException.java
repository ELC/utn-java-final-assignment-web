package util.exceptions;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppDataException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public AppDataException() {
		super("Error in data layer");
    }
	
    public AppDataException(String message, Level errorLevel) {
        this(null, message, errorLevel);
    }
    
    public AppDataException(Throwable cause) {
        super(cause);
    }
	
	private AppDataException(Throwable e, String message){
		super(message, e);
	}
	
	public AppDataException(Throwable e, String message, Level errorLevel){
		this(e, message);
		Logger logger = LogManager.getLogger(getClass());
		logger.log(errorLevel, message);
	}
}
