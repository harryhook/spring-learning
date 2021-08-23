package com.coderunning.service.impl;

import com.coderunning.service.SomeService;
import org.springframework.stereotype.Service;

@Service("someService")
public class SomeServiceImpl implements SomeService {

    @Override
    public void doSomething() {
        System.out.println("do something");
    }

    @Override
    public void doOtherThing() {
        System.out.println("do other thing");

    }
}
