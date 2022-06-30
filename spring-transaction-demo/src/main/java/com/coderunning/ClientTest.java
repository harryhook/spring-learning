package com.coderunning;

import com.coderunning.context.Context;
import com.coderunning.context.OrderStateContext;
import com.coderunning.state.concreate.AState;

public class ClientTest {

    public static void main(String[] args) {
        Context context = new Context(new AState());
        context.handle();
        context.handle();
    }
}
