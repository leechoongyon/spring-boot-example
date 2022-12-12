package com.example.interfaces.member;

import com.example.dto.SimpleCommand;
import com.example.interfaces.okhttp.OkhttpExampleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    @PostMapping("/api/v1/members")
    public ResponseEntity<?> registerMember(@RequestBody MemberDto.Create create) {
        return ResponseEntity.ok(create.getId());
    }

    @GetMapping("/api/v1/members/{id}")
    public ResponseEntity<?> getMember(@PathVariable("id") String id) {
        MemberDto.Base base = new MemberDto.Base(id, "test");
        return new ResponseEntity<>(base, HttpStatus.OK);
    }
}
