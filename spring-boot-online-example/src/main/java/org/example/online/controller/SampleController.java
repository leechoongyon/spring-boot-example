package org.example.online.controller;

import org.example.online.domain.SampleIO;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {
    @RequestMapping(value ="test/{id}", method = RequestMethod.GET)
    public SampleIO getTestData(@PathVariable("id") Long id) {
        SampleIO testIO = SampleIO.builder()
                .id(id)
                .name("test")
                .build();
        return testIO;
    }
}