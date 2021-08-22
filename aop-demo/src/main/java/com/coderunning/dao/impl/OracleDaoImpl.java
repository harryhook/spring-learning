package com.coderunning.dao.impl;

import com.coderunning.dao.UserDao;
import com.coderunning.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("oracleDao")
public class OracleDaoImpl implements UserDao {
    @Override
    public void addUser(User user) {
        System.out.println("oracle add new user");
    }
}
