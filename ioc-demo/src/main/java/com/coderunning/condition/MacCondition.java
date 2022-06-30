package com.coderunning.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MacCondition implements Condition {


    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        // 获取上下文中的环境变量

        String osName = conditionContext.getEnvironment().getProperty("os.name");
        return osName.equals("Mac OS X");
    }
}