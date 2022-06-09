package com.coderunning.context;

import com.coderunning.state.State;

public class Context {
    private State state;

    public Context(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void handle() {
        state.handle(this);
    }
}
