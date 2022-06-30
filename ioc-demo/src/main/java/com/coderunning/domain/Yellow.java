package com.coderunning.domain;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Yellow {

    public Yellow() {
        System.out.println("Yellow Constrctor");
    }

    @PostConstruct
    public void init() {
        System.out.println("Yellow init, postContract");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Yellow destroy, PreDestroy");
    }
}
