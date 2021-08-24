package com.coderunning.service.impl;

import com.coderunning.domain.User;
import com.coderunning.service.SomeService;
import org.springframework.stereotype.Service;


public class SomeServiceImpl implements SomeService {

    @Override
    public void doSomething() {
        System.out.println("do something");
    }

    @Override
    public User doOtherThing(String name) {

        User user  = new User();
        user.setAge(11);
        user.setName(name);

        System.out.println(user);
        return user;

    }
}
