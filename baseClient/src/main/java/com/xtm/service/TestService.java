package com.xtm.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
@Service
public class TestService {
    Log log = LogFactory.getLog(TestService.class);
    /**
     * 注解开启接口限流
     * @param str
     */
    @SentinelResource(value = "doSomeThing", blockHandler = "exceptionHandler", fallback = "fallbackHandler")
    public String doSomeThing(String str) {
        log.warn(str);
        return "success:"+str;
    }

    // 限流与阻塞处理
    public String exceptionHandler(String str, BlockException ex) {
        log.error( "blockHandler：" + str, ex);
        return "blockHandler:"+str;
    }
    //熔断降级
    public String fallbackHandler(String str) {
        log.error("fallbackHandler：" + str);
        return "fallbackHandler:"+str;
    }
}
