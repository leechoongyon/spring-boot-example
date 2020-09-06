package org.example.jpa.repository;


import org.example.jpa.domain.CompositeDomain;
import org.example.jpa.domain.EmbeddedDomain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompositeDomainRepositoryTest {

    @Autowired
    private CompositeDomainRepository compositeDomainRepository;

    @Test
    public void compositeDomainRepositoryTest() {
        //given
        String pk1 = "aaa";
        String pk2 = "bbb";

        EmbeddedDomain embeddedDomain = EmbeddedDomain.builder()
                .pk1(pk1)
                .pk2(pk2)
                .build();

        CompositeDomain compositeDomain = new CompositeDomain();
        compositeDomain.setEmbeddedDomain(embeddedDomain);
        compositeDomain.setContent("content123");

        compositeDomainRepository.save(compositeDomain);

        //when
        Optional<CompositeDomain> result = compositeDomainRepository.findById(embeddedDomain);

        //then
        assertThat(result.get().getContent(), is("content123"));
    }

}
