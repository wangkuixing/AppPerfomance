package Common;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SqlConnect {
    public void create(int action, double cpu, double memory, double flow, double fps, double lostframe, double battery){
        Connection conn;
        PreparedStatement stmt;
        String driver = "com.mysql.cj.jdbc.Driver";
        Commons commons = Commons.load("/globalConfig.yaml");
        String url  = commons.mconfig.mysql;
        String user = commons.mconfig.user;
        String passwd = commons.mconfig.passwd;
        String sql = "insert into appdata values (?,?,?,?,?,?,?,?,?,?)";

        //获取当前日期，并转换为java.sql.Date格式
        java.util.Date udate=new java.util.Date();
        Date date = new Date(udate.getTime());

        //获取当前时间，并转换为java.sql.Time格式
        SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
        String sdate=format.format(udate);
        Time time = null;
        try {
            time = new Time(format.parse(sdate).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, passwd);
            stmt = conn.prepareStatement(sql);
            stmt.setNull(1, 1);
            stmt.setInt(2, action);
            stmt.setDouble(3, cpu);
            stmt.setDouble(4, memory);
            stmt.setDouble(5, flow);
            stmt.setDouble(6, fps);
            stmt.setDouble(7, lostframe);
            stmt.setDouble(8, battery);
            stmt.setDate(9, date);
            stmt.setTime(10, time);
            stmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

    }
}
