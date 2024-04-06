package com.coderunning;

import com.coderunning.factory.CglibProxyFactory;
import com.coderunning.impl.UnicomeSmsSendImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AopDemoApplication {

    public static void main(String[] args) {

        UnicomeSmsSendImpl unicomeSmsSend = (UnicomeSmsSendImpl) CglibProxyFactory.getProxy(UnicomeSmsSendImpl.class);
        unicomeSmsSend.sendSms();
    }
}
