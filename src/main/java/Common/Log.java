package Common;

import org.testng.Reporter;

/**
 * Created by wangkx on 2019/12/25.
 */
public class Log {

    /**
     * 日志打印
     * @param logStr
     */
    public static void logs(String logStr){
        System.out.println(logStr);
        Reporter.log("-------------------------");
        Reporter.log(logStr);
        Reporter.log("-------------------------");

    }
}
