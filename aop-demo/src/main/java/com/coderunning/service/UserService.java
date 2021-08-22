package com.coderunning.service;

import com.coderunning.dao.UserDao;
import com.coderunning.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    void addUser(User user);
}
