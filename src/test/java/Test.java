
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Test {

    public static void main(String[] args) throws ParseException {
        float f=(float)32000/1024/1024;
        System.out.println(f);

        String osname=System.getProperty("os.name");
        System.out.println(osname);
        System.out.println(20 / 19.67);

//        java.util.Date get=new java.util.Date();
//        SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
//        String sdate=format.format(get);
//        Date date = new Date(format.parse(sdate).getTime());
//        System.out.println(date);

        java.util.Date udate=new java.util.Date();
        SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
        String sdate=format.format(udate);
        Time time = new Time(format.parse(sdate).getTime());
        System.out.println(time);

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
//        String time = format.format(new Date()).concat("-0100:00:00");
//        java.sql.Date timePara  = null;
//        try {
//            timePara = new java.sql.Date(format.parse(time).getTime());
//            System.out.println(timePara);
//            } catch (ParseException e) {
//            e.printStackTrace();
//            }

//        Time time = new Time()
    }
}
