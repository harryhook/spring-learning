package com.coderunning.pipelinedemo;

import com.alibaba.fastjson.JSON;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.entity.data.DefaultSlot;
import com.yomahub.liteflow.entity.data.LiteflowResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@SpringBootTest
class PipelineDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private FlowExecutor flowExecutor;

    @Test
    public void testConfig(){
        LiteflowResponse<DefaultSlot> response = flowExecutor.execute2Resp("chain1", "arg");

        Assert.notNull(response, JSON.toJSONString(response));


    }
}
