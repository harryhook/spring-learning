package com.coderunning.state.concreate;

import com.coderunning.ShareContext;
import com.coderunning.context.Context;
import com.coderunning.context.OrderStateContext;
import com.coderunning.state.State;

public class AState extends State {

    public void handle(Context context) {
        System.out.println(this.getClass().getSimpleName() + "将流转到BState");
        context.setState(ShareContext.getState("BState"));

    }
}
