package com.coderunning.impl;

import com.coderunning.UserDao;
import com.coderunning.domain.User;
import org.springframework.stereotype.Component;

@Component("mySQLDao")
public class MySQLDaoImpl implements UserDao {
    @Override
    public void addUser(User user) {
        System.out.println("mysql add new user");
    }
}
