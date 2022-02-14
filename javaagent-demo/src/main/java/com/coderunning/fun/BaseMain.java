package com.coderunning.fun;

public class BaseMain {

    public int print(int i) {
        System.out.println("i: " + i);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i + 2;
    }

    public void run() {
        int i = 1;
        while (true) {
            i = print(i);
        }
    }

    public static void main(String[] args) {
        BaseMain main = new BaseMain();
        main.run();
    }
}