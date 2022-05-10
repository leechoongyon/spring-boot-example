package com.example.api;

import com.example.dto.SimpleDto;
import com.example.dto.SimpleV2Dto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Map;

import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleApiControllerTest {

    /**
     * 내장 톰캣 띄우고 테스트 해야 함. ExampleApplication 실행해야 함.
     * @throws Exception
     */
    @Test
    public void hello_get_메소드_rest_teamplate_호출_테스트() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        String url = "http://localhost:8080/api/hello";

        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.GET, new HttpEntity<String>(headers), String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(true, response.getBody().contains("Hello World"));
    }

    /**
     * 내장 톰캣 띄우고 테스트 해야 함. ExampleApplication 실행해야 함.
     * @throws Exception
     */
    @Test
    public void hello_post_메소드_rest_teamplate_호출_테스트() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        String url = "http://localhost:8080/api/hello";
        SimpleDto simpleDto = SimpleDto.builder()
                .key("testKey")
                .name("testName")
                .build();
        HttpEntity httpEntity = new HttpEntity<SimpleDto>(simpleDto, headers);
        ResponseEntity<SimpleDto> response = restTemplate.exchange(url,
                HttpMethod.POST, httpEntity , SimpleDto.class);
        System.out.println(response);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals("testKey", response.getBody().getKey());
        Assert.assertEquals("testName", response.getBody().getName());
    }

    /**
     * 내장 톰캣 띄우고 테스트 해야 함. ExampleApplication 실행해야 함.
     * @throws Exception
     */
    @Test
    public void helloV2_post_메소드_정상_호출_테스트() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        String url = "http://localhost:8080/api/hello/v2";
        SimpleV2Dto.Request request = SimpleV2Dto.Request.builder()
                .message("normal")
                .build();
        HttpEntity httpEntity = new HttpEntity<SimpleV2Dto.Request>(request, headers);
        ResponseEntity<Map> result = restTemplate.exchange(url,HttpMethod.POST, httpEntity , Map.class);
        if (result.getStatusCode().equals(HttpStatus.OK)) {
            Map<String, String> response = result.getBody();
            Assert.assertThat(response.get("message"), is("normal") );
        } else {
            Map<String, String> response = result.getBody();
            Assert.assertThat(response.get("errorCode"), is("2002") );
        }
    }

    /**
     * 내장 톰캣 띄우고 테스트 해야 함. ExampleApplication 실행해야 함.
     * @throws Exception
     */
    @Test
    public void helloV2_post_메소드_에러_호출_테스트() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        String url = "http://localhost:8080/api/hello/v2";
        SimpleV2Dto.Request request = SimpleV2Dto.Request.builder()
                .message("error")
                .build();
        HttpEntity httpEntity = new HttpEntity<SimpleV2Dto.Request>(request, headers);
        ResponseEntity<Map> result = restTemplate.exchange(url,HttpMethod.POST, httpEntity , Map.class);
        if (result.getStatusCode().equals(HttpStatus.OK)) {
            Map<String, String> response = result.getBody();
            Assert.assertThat(response.get("message"), is("normal") );
        } else {
            Map<String, String> response = result.getBody();
            Assert.assertThat(response.get("errorCode"), is("2002") );
        }
    }

    /**
     * 내장 톰캣 띄우고 테스트 해야 함. ExampleApplication 실행해야 함.
     * @throws Exception
     */
    @Test
    public void hello_v1_post_api_정상_테스트() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        String url = "http://localhost:8080/api/v1/hello";
        SimpleDto.Request request = SimpleDto.Request.builder()
                .name("testName")
                .key("key")
                .build();
        HttpEntity httpEntity = new HttpEntity<>(request, headers);
        ResponseEntity<Map> result = restTemplate.exchange(url,HttpMethod.POST, httpEntity , Map.class);
        Map<String, String> response = result.getBody();
        System.out.println("response : " + response);
        Assert.assertThat(response.get("key"), is("key") );
        Assert.assertThat(response.get("name"), is("testName") );
    }
}