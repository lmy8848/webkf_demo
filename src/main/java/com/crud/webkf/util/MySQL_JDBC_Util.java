package com.crud.webkf.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL_JDBC_Util {
    private static Connection CON;

    public static Connection Conn() {
        if (CON != null) {
            try {
                if (CON.isClosed())
                    return CON = Connect();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return CON = Connect();


    }


    private static Connection Connect() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javakc100?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false", "root", "qj315");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
