package com.crud.webkf.dao;

import com.crud.webkf.bean.User;
import com.crud.webkf.util.MySQL_JDBC_Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoImp implements Dao {

    @Override
    public boolean add(User user) {
        Connection conn = MySQL_JDBC_Util.Conn();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("insert into user(userId,name,ename,personId,sex,credit,city) value(?,?,?,?,?,?,?) ");
            preparedStatement.setString(1,user.getUserId());
            preparedStatement.setString(2,user.getName());
            preparedStatement.setString(3,user.getEname());
            preparedStatement.setString(4,user.getPersonId());
            preparedStatement.setString(5,user.getSex());
            preparedStatement.setInt(6,user.getCredit());
            preparedStatement.setString(7,user.getCity());
            return preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(User user) {
        Connection conn = MySQL_JDBC_Util.Conn();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("delete from user where userId=?");
            preparedStatement.setString(1,user.getUserId());
            return preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    @Override
    public int update(User user) {
        Connection conn = MySQL_JDBC_Util.Conn();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("update user set name=?,ename=?,personId=?,sex=?,credit=?,city=?   where userId=?");
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEname());
            preparedStatement.setString(3,user.getPersonId());
            preparedStatement.setString(4,user.getSex());
            preparedStatement.setInt(5,user.getCredit());
            preparedStatement.setString(6,user.getCity());
            preparedStatement.setString(7,user.getUserId());
            return preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<User> select() {
        List<User> list = new ArrayList<>();
        Connection conn = MySQL_JDBC_Util.Conn();
        String sql = "select * from user";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            sqlQuery(list, resultSet);
            return list;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        } finally {
            try {
                if (preparedStatement != null) {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public List<User> limitQuery(int index,int last) {
        List<User> list = new ArrayList<>();
        Connection conn = MySQL_JDBC_Util.Conn();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            preparedStatement = conn.prepareStatement("select * from user limit ?,?");
            preparedStatement.setInt(1,index);
            preparedStatement.setInt(2,last);
            resultSet = preparedStatement.executeQuery();
            sqlQuery(list, resultSet);
            return list;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            try {
                if (preparedStatement != null) {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    private void sqlQuery(List<User> list, ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            User user = new User();
            user.setUserId(resultSet.getString("userId"));
            user.setName(resultSet.getString("name"));
            user.setEname(resultSet.getString("ename"));
            user.setCity(resultSet.getString("city"));
            user.setCredit(resultSet.getInt("credit"));
            user.setSex(resultSet.getString("sex"));
            user.setPersonId(resultSet.getString("personId"));
            list.add(user);
        }
    }
}
