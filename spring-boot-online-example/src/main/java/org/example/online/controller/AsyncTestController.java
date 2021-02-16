package org.example.online.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.online.service.AsyncTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AsyncTestController {

    @Autowired
    private AsyncTestService asyncTestService;

    @GetMapping("/test/async")
    public String asyncTest() {
        asyncTestService.asyncTest();
        return "asyncTest...";
    }

    @GetMapping("test/sync")
    public String syncTest() {
        asyncTestService.syncTest();
        return "syncTest...";
    }
}
