package com.coderunning.pipelinedemo.component;

import java.lang.instrument.Instrumentation;

public class TransformAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new FruitTransformer());
    }
}