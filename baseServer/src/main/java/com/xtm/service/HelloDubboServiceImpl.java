package com.xtm.service;

import com.xtm.dubbo.HelloDubboService;
import org.apache.dubbo.config.annotation.Service;

//org.apache.dubbo.config.annotation.Service
@Service
public class HelloDubboServiceImpl implements HelloDubboService {
    @Override
    public String helloDubbo(String name) {
        return "hello " + name;
    }
}
