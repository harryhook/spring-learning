package com.coderunning.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author haowei
 * @since 2024/2/18 11:42
 */
public class Test {

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = args[0];
        String user = args[1];
        String pass = args[2];
        String sql = args[3];
        String interval = args[4];

        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            while (true) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setFetchSize(Integer.MIN_VALUE);
                stmt.setString(1, interval);
                ResultSet rs = stmt.executeQuery();
                rs.close();
                stmt.close();

                PreparedStatement stmt2 = conn.prepareStatement(sql);
                stmt2.setString(1, interval);
                rs = stmt2.executeQuery();
                //Thread.sleep(60000);
                while (rs.next()) {
                    System.out.println("fine");
                }
                rs.close();
                stmt2.close();

                Thread.sleep(Long.valueOf(interval));
                break;
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}