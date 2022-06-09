package com.coderunning;

import com.coderunning.context.Context;
import com.coderunning.context.OrderStateContext;
import com.coderunning.state.State;
import com.coderunning.state.concreate.AState;
import com.coderunning.state.concreate.BState;

import java.util.HashMap;
import java.util.Map;

public class ShareContext extends Context {

    private static Map<String, State> shareStateMap = new HashMap<>();

    private State state;

    static {
        shareStateMap.put(AState.class.getSimpleName(), new AState());
        shareStateMap.put(BState.class.getSimpleName(), new BState());
    }

    public ShareContext(State state){
        super(state);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    //读取状态
    public static State getState(String key) {
        return shareStateMap.get(key);
    }

    public void handle() {
        state.handle(this);
    }
}

