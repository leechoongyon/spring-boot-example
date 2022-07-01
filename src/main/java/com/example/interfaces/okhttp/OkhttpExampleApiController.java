package com.example.interfaces.okhttp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OkhttpExampleApiController {

    private final OkhttpExampleDtoMapper okhttpExampleDtoMapper;

    @PostMapping("/api/v1/okhttp")
    public ResponseEntity<?> okhttpPostExample(@RequestBody OkhttpExampleDto.PostRequest request) {
        return new ResponseEntity<>(okhttpExampleDtoMapper.of(request), HttpStatus.OK);
    }

    @GetMapping("/api/v1/okhttp/{id}")
    public ResponseEntity<?> okhttpGetExample(@PathVariable("id") String id) {
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
