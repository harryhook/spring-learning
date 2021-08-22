package com.coderunning.dao.impl;

import com.coderunning.dao.UserDao;
import com.coderunning.domain.User;
import org.springframework.stereotype.Component;

@Component("mySQLDao")
public class MySQLDaoImpl implements UserDao {
    @Override
    public void addUser(User user) {
        System.out.println("mysql add new user");
    }
}
