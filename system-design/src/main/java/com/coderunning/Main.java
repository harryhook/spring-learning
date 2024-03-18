package com.coderunning;

/**
 * @author haowei.chen
 * @since 2024/3/13 23:28
 */
public class Main {


    public static void main(String[] args) {
        String encodeStr = Base62Utils.encode(11157);
        System.out.println(encodeStr);
    }

}
