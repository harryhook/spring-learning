package com.coderunning.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenhaowei
 */
@RestController
@Slf4j
public class GcTestController {

    private List<Greeting> objListCache = new ArrayList<>();

    @RequestMapping("/greeting")
    public String greeting() {

        Greeting greeting = new Greeting();
        if (objListCache.size() >= 100000) {

            log.info("clean the List!!!!!!!!!!");
             objListCache.clear();
        } else {
             objListCache.add(greeting);
        }
        return greeting.toString();

    }


}

class Greeting {
    private String message1;
    private String message2;
    private String message3;
    private String message4;
    private String message5;
    private String message6;
    private String message7;
    private String message8;
    private String message9;
    private String message10;
    private String message11;
    private String message12;
    private String message13;
    private String message14;
    private String message15;
    private String message16;
    private String message17;
    private String message18;
    private String message19;
    private String message20;
    private String message21;
    private String message22;
    private String message23;
    private String message24;
    private String message25;
    private String message26;
    private String message27;
    private String message28;
    private String message29;
    private String message30;
    private String message31;
    private String message32;
    private String message33;
    private String message34;
    private String message35;
    private String message36;
    private String message37;
    private String message38;
    private String message39;
    private String message40;
    private String message41;
    private String message42;
    private String message43;
    private String message44;
    private String message45;
    private String message46;
    private String message47;
    private String message48;
    private String message49;
    private String message50;
    private String message51;
    private String message52;
    private String message53;
    private String message54;
    private String message55;
    private String message56;
    private String message57;
    private String message58;
    private String message59;

}