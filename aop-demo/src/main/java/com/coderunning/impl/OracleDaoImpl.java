package com.coderunning.impl;

import com.coderunning.UserDao;
import com.coderunning.domain.User;
import org.springframework.stereotype.Repository;

@Repository("oracleDao")
public class OracleDaoImpl implements UserDao {
    @Override
    public void addUser(User user) {
        System.out.println("oracle add new user");
    }
}
