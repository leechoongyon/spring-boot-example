package com.example.interfaces.member;

import com.example.ExampleApplication;
import com.example.interfaces.member.MemberDto.Create;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;


// Junit5 + SpringBootTest
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ExampleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberApiControllerIntegrationTest {

    @LocalServerPort
    private int port;

    // TestRestTemplate은 REST API Test 를 support 해주는 모듈
    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void registerMemberTest() throws Exception {
        Create create = new Create("1", "test");
        HttpEntity<Create> entity = new HttpEntity<>(create, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/v1/members",
                HttpMethod.POST, entity, String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("1", response.getBody());
    }

    @Test
    @DisplayName("MEMBER_조회_통합_테스트")
    public void getMemberTest() throws Exception {
        String memberId = "1";
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/v1/members/" + memberId,
                HttpMethod.GET, entity, String.class);

        String expected = "{\"id\":\"1\",\"name\":\"test\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
}
