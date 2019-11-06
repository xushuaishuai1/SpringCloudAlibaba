package com.xtm.action;

import com.xtm.dubbo.HelloDubboService;
import com.xtm.service.DiscoveryServer;
import com.xtm.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;


@RefreshScope
@RestController
@RequestMapping("/testAction")
public class TestAction {
    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    DiscoveryServer discoveryServer;


    @Value("${didispace.title:}")
    private String title;

    @GetMapping("/getProperties")
    public String getProperties() {
        return title;
    }

    /**
     * balance调用其它微服务接口
     * @return
     */
    @GetMapping("/balance")
    public String test() {
        // 通过spring cloud common中的负载均衡接口选取服务提供节点实现接口调用
        ServiceInstance serviceInstance = loadBalancerClient.choose("baseServer");
        String url = serviceInstance.getUri() + "/testAction/hello?name=" + "didi";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return "Invoke : " + url + ", return : " + result;
    }

    /**
     * feign调用其它微服务接口
     * @return
     */
    @GetMapping("/feign")
    public String test1() {
        String result = discoveryServer.hello("Feign调用");
        return result;
    }


    @Autowired
    private TestService testService;

    /**
     * 测试stentinel实现限流、熔断降级
     * @return
     */
    @GetMapping("/testXianLiu")
    public String hello() {
        return testService.doSomeThing("hello " + new Date());
    }


    @Reference
    private HelloDubboService helloDubboService;

    /**
     * 测试dubbo功能
     * @return
     */
    @GetMapping("/dubbo")
    public String dubblo() {
        return helloDubboService.helloDubbo("springcloud实现dubbo功能");
    }
}
