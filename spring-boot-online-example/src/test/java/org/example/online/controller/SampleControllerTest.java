package org.example.online.controller;

import org.example.online.domain.SampleIO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private SampleController sampleController;

    private SampleIO sampleIO;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(sampleController).build();
        sampleIO = SampleIO.builder()
                .id(100)
                .name("Test")
                .build();
    }

    @Test
    public void sampleControllerTest() throws Exception {

        /** 100 이라는 값이 들어왔을 때, 아래 값을 주도록 하기. */
        given(this.sampleController.getTestData(new Long(100)))
                .willReturn(sampleIO);

        mockMvc.perform(get("/test/{id}", 100))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['name']", containsString("Test")))
                .andDo(print());
    }
}