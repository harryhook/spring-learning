package com.coderunning.state;

import com.coderunning.context.Context;

public abstract class State {

    public abstract void handle(Context context);
}

