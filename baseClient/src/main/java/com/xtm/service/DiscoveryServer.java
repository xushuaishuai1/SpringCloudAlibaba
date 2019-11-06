package com.xtm.service;

import com.xtm.config.FeignConfig;
import com.xtm.service.fallBack.DiscoveryServerFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "baseServer",fallback = DiscoveryServerFallBack.class,configuration={FeignConfig.class})
public interface DiscoveryServer {
    @GetMapping("/testAction/hello")
    String hello(@RequestParam(name = "name") String name);
}
