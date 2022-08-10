package com.example.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SequenceTestDomainRepositoryTest {

    @Autowired
    private SequenceTestDomainRepository sequenceTestDomainRepository;

    @Test
    @Sql(scripts = "/sql/sequenceTest.sql")
    public void H2_SEQUENCE_TEST() throws Exception {
        int seq = sequenceTestDomainRepository.getSequence();
        Assertions.assertEquals(1, seq);
    }
}
