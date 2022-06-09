package com.coderunning.factory;

import com.coderunning.enums.OrderStateEnum;
import com.coderunning.state.AbstractOrderState;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderStateFactory implements ApplicationContextAware {

    private static final Map<Enum, AbstractOrderState> stateMap = new HashMap<>(OrderStateEnum.values().length);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, AbstractOrderState> beans = applicationContext.getBeansOfType(AbstractOrderState.class);
        beans.values().forEach(item -> stateMap.put(item.type(), item));
    }

    public static AbstractOrderState getState(Enum orderStateEnum) {
        return stateMap.get(orderStateEnum);
    }
}
