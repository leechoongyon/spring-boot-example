package com.example.repository;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FunctionTestDomainRepositoryTest {
    @Autowired
    private FunctionTestDomainRepository functionTestDomainRepository;

    @Test
    @Sql(scripts = "/sql/functionTest.sql")
    public void H2_FUNCTION_TEST() throws Exception {
        String result = functionTestDomainRepository.functionTest("test");
        Assert.assertThat(result, is("test"));
    }
}
