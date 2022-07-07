package com.example.common.properties;

import com.example.ExampleApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ExampleApplication.class)
public class TestPropertiesTest {

    @Autowired
    private TestProperties.FirstLevelProperties firstLevelProperties;

    @Autowired
    private TestProperties.SecondLevelProperties secondLevelProperties;

    @Test
    public void testProperties_로딩_테스트() {
        assertThat(firstLevelProperties.getName()).isEqualTo("firstLevel");
        assertThat(secondLevelProperties.getName()).isEqualTo("secondLevel");
    }
}