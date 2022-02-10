package com.coderunning.pipelinedemo.component;

import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author chenhaowei
 */
@Component("c")
@Slf4j
public class CCmp extends NodeComponent {

    @Override
    public void process() {

        log.info("c do something!");
    }
}
