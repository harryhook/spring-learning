package com.coderunning.config;

import com.coderunning.domain.Blue;
import com.coderunning.domain.Red;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class ColorSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.coderunning.domain.Yellow", "com.coderunning.domain.Blue"};
    }
}
