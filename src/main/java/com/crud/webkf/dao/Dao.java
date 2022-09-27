package com.crud.webkf.dao;

import com.crud.webkf.bean.User;

import java.util.List;

public interface Dao {

    boolean add(User user);
    boolean delete(User user);
    int update(User user);
    List<User> select();
    List<User> limitQuery(int index,int last);

}
