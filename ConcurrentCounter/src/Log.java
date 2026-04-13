package src;

import java.util.logging.Logger;

public class Log {
    private final static Logger logger = Logger.getLogger("counter"); 
    
    public static void info(String message) {
        String subject = "[" + Thread.currentThread().getName() + "] ";
        logger.info(
            subject + message
        );
    }    
}
