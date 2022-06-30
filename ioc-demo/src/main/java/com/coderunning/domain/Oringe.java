package com.coderunning.domain;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Oringe implements InitializingBean, DisposableBean {

    public Oringe() {
        System.out.println("Oringe is created");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Oringe destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Oringe afterPropertiesSet");
    }
}
