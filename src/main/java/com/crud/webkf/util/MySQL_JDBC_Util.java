package com.crud.webkf.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;

public class MySQL_JDBC_Util {
    private static Connection CON;
    private static SqlSessionFactory session;
    public static Connection Conn() {
        if (CON != null) {
            try {
                if (CON.isClosed()) {
                    return CON = Connect();
                }
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


    public static SqlSession session(){
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            session=new SqlSessionFactoryBuilder().build(resourceAsStream);
            return session.openSession(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String javaWebToken(String username,String userId){
        Calendar calendar = Calendar.getInstance();
        // 指定token过期时间为10秒
        calendar.add(Calendar.SECOND, 10);
        return JWT.create()
                .withHeader(new HashMap<>())  // Header
                .withClaim("userId", userId)  // Payload
                .withClaim("userName", username)
                .withExpiresAt(calendar.getTime())  // 过期时间
                .sign(Algorithm.HMAC256("!34ADAS"));
    }


    public static String parseToken(String token){
        // 创建解析对象，使用的算法和secret要与创建token时保持一致
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("!34ADAS")).build();
        // 解析指定的token
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        // 获取解析后的token中的payload信息
        Claim userId = decodedJWT.getClaim("userId");
        Claim userName = decodedJWT.getClaim("userName");
        System.out.println(userId.asInt());
        System.out.println(userName.asString());
        // 输出超时时间
        System.out.println(decodedJWT.getExpiresAt());
        return userName.asString();
    }
}
