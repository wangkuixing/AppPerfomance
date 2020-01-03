package Common;

import java.sql.*;
import java.util.Date;

public class SqlConnect {
    public void create(int action, double cpu, double memory, double flow, double fps, double lostframe, double battery){
        Connection conn;
        PreparedStatement stmt;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.0.210:3306/app_perfomance?useSSL=false";
        String user = "root";
        String passwd = "root";
        String sql = "insert into appdata values (?,?,?,?,?,?,?,?,?,?)";
        Date date = new Date();
        date.getTime();


        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, passwd);
            stmt = conn.prepareStatement(sql);
            stmt.setNString(1, "");
            stmt.setInt(2, action);
            stmt.setDouble(3, cpu);
            stmt.setDouble(4, memory);
            stmt.setDouble(5, flow);
            stmt.setDouble(6, fps);
            stmt.setDouble(7, lostframe);
            stmt.setDouble(8, battery);
            stmt.setDate();
            stmt.setTime();
            stmt.setInt(4,2017);
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
