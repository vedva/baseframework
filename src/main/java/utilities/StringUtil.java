package utilities;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StringUtil {
    public static String getFullExceptionMessage(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
    public static String getShortExceptionMessage(Exception e) {
        return e.toString();
    }
}
