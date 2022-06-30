package com.coderunning.factory;

import com.coderunning.domain.Red;
import org.springframework.beans.factory.FactoryBean;

public class ColorFactoryBean  implements FactoryBean<Red> {
    @Override
    public Red getObject()  {
        return new Red();
    }

    @Override
    public Class<?> getObjectType() {
        return Red.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
