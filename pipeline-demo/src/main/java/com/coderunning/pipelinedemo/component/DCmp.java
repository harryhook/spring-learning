package com.coderunning.pipelinedemo.component;

import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author chenhaowei
 */
@Component("d")
@Slf4j
public class DCmp extends NodeComponent {

    @Override
    public void process() {

        log.info("d do something!");
    }
}
