package com.coderunning.mysql;

/**
 * @author haowei.chen
 * @since 2024/2/18 20:50
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author haowei
 * @since 2024/2/18 11:42
 */
public class Test3 {

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = args[0];
        String user = args[1];
        String pass = args[2];
//        String sql = args[3];
        String interval = args[3];

        Connection conn = DriverManager.getConnection(url, user, pass);

        try {
            String sql = "INSERT INTO user(name, age) VALUES(?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            conn.setAutoCommit(true);
            for (int i = 0; i < 100000; i++) {
                stmt.setString(1, "user" + i);
                stmt.setInt(2, 20 + i);
                stmt.addBatch(); // 添加到batch
            }
            Thread.sleep(Long.parseLong(interval));
            stmt.executeBatch();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}