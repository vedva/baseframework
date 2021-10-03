package base;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class LoggerService {
    private static Logger instance;
    private LoggerService(){
    }
    public static Logger getInstance(){
        if(instance == null){
            synchronized (LoggerService.class) {
                if(instance == null){
                        ConsoleAppender consoleAppender = new ConsoleAppender();
                        consoleAppender.setThreshold(Level.INFO);
                        consoleAppender.setLayout(new PatternLayout("%d [%p|%c|%C{1}] %m%n"));
                        consoleAppender.activateOptions();
                        Logger.getRootLogger().addAppender(consoleAppender);
                    instance = Logger.getLogger("Logger");
                }
            }
        }
        return instance;
    }
}
