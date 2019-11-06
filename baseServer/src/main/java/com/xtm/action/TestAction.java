package com.xtm.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/testAction")
public class TestAction {
    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
//            log.info("invoked name = " + name);
        return "hello " + name;
    }
}
