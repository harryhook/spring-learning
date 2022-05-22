package com.coderunning.service;

public class SyncTest {

    public   int i;
    public synchronized void syncTask(){
        i++;
    }


    public static void main(String[] args) {
        SyncTest syncTest = new SyncTest();
        syncTest.syncTask();
    }
}
