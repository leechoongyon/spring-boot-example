package com.example.api;

import com.example.dto.SimpleCommand;
import com.example.dto.SimpleDto;
import com.example.dto.SimpleV2Dto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SimpleApiController {

    private final SimpleDtoMapper simpleDtoMapper;

    @GetMapping("/api/hello")
    public String helloWorld() {
        return "Hello World";
    }

    @PostMapping("/api/hello")
    public ResponseEntity<?> helloWorldPost(@RequestBody SimpleDto simpleDto) {
        SimpleDto result = SimpleDto.create(simpleDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/api/hello/v2")
    public ResponseEntity<?> helloWorldV2(@RequestBody SimpleV2Dto.Request request) {
        if (request.getMessage().equals("normal")) {
            SimpleV2Dto.Response response = SimpleV2Dto.Response.builder()
                    .code("1002")
                    .message("normalAPi")
                    .build();;
            return new ResponseEntity<>(request, HttpStatus.OK);
        } else if (request.getMessage().equals("error")) {
            SimpleV2Dto.ErrorResponse errorResponse = SimpleV2Dto.ErrorResponse.builder()
                    .errorCode("2002")
                    .errorDesc("errorApi")
                    .build();;
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_GATEWAY);
        } else {
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }

    @PostMapping("/api/v1/hello")
    public ResponseEntity<?> hello(@RequestBody SimpleDto.Request request) {
//        SimpleCommand.Request command = SimpleDtoMapper.INSTANCE.of(request);
        SimpleCommand.Request command = simpleDtoMapper.of(request);
        log.info("command : {}", command);
        return new ResponseEntity<>(command, HttpStatus.OK);
    }
}
