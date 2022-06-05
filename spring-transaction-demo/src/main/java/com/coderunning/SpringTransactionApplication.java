package com.coderunning;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Random;

@SpringBootApplication
public class SpringTransactionApplication {

    public static void main(String[] args) throws InterruptedException {
        HashMap<Integer, String> map = new HashMap<>(2);
        map.put(1, "");
        map.put(2, "");
        map.put(3, "");
    }
}
