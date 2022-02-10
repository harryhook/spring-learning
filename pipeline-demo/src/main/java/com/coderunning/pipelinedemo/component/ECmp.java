package com.coderunning.pipelinedemo.component;

import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author chenhaowei
 */
@Component("e")
@Slf4j
public class ECmp extends NodeComponent {

    @Override
    public void process() {

        log.info("e do something!");
    }
}
