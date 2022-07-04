package com.example.interfaces.okhttp;

import com.example.common.utils.OkhttpUtils;
import com.google.gson.Gson;
import okhttp3.MediaType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OkhttpExampleApiControllerLiveTest {

    /**
     * 내장 톰캣 띄우고 테스트 해야 함. ExampleApplication 실행해야 함.
     * @throws Exception
     */
    @Test
    public void okhttp_post_메소드_테스트() throws Exception {
        String url = "http://localhost:8080/api/v1/okhttp";

        // body
        Map<String, String> body = new HashMap<>();
        body.put("name", "test");

        String result = OkhttpUtils.post(url, body, MediaType.parse("application/json; charset=utf-8"));
        OkhttpExampleDto.PostResponse response =
                new Gson().fromJson(result, OkhttpExampleDto.PostResponse.class);
        Assert.assertEquals("test", response.getName());
    }

    /**
     * 내장 톰캣 띄우고 테스트 해야 함. ExampleApplication 실행해야 함.
     * @throws Exception
     */
    @Test
    public void okhttp_get_메소드_테스트() throws Exception {
        String url = "http://localhost:8080/api/v1/okhttp/1";
        String result = OkhttpUtils.get(url);
        Assert.assertEquals("1", result);
    }
}