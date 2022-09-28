package com.crud.webkf.dao;

import com.crud.webkf.bean.Acconut;
import com.crud.webkf.bean.User;

import java.util.List;

public interface Dao {

    //添加员工
    boolean add(User user);
    //删除员工
    boolean delete(User user);
    //修改员工信息
    int update(User user);
    //查询员工表
    List<User> select();
    //分页查询员工表
    List<User> limitQuery(int index,int last);
    //登录验证
    Acconut loginAcconut(String username);
    //账户注册
    boolean register(String username,String password,String token);
    //更新私钥
    int updateToken(String username,String token);

}
