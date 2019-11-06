package com.xtm.service.fallBack;

import com.xtm.service.DiscoveryServer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class DiscoveryServerFallBack implements DiscoveryServer {
    @Override
    public String hello(@RequestParam(name = "name") String name){
        return "";
    }
}
