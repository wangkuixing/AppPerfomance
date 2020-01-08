package perfomance;

import Common.Commons;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestMain {
    public static void main(String[] args){
        Commons config = Commons.load("/globalConfig.yaml");
        System.out.println(config.mconfig.phoneid);

        //获取当前时间，并转换为java.sql.Time格式
        java.util.Date udate=new java.util.Date();
        SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
        String sdate=format.format(udate);
        Time time = null;
        try {
            time = new Time(format.parse(sdate).getTime());
            System.out.println(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
