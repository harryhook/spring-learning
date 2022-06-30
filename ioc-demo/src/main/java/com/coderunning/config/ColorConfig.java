package com.coderunning.config;

import com.coderunning.domain.Orinage;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;

@Configuration
@Import({Orinage.class, ColorRegistrar.class, ColorSelector.class})
public class ColorConfig {

}
