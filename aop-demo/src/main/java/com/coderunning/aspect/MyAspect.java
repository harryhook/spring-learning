package com.coderunning.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


@Aspect
public class MyAspect {

    @Before(value = "execution( *  do*())")
    public void doBefore() {

        System.out.println(System.currentTimeMillis());
    }

}
