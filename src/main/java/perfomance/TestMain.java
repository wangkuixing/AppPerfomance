package perfomance;

import Common.Commons;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestMain {
    public static void main(String[] args){
        Commons config = Commons.load("/globalConfig.yaml");
        System.out.println(config.mconfig.phoneid);
    }
}
