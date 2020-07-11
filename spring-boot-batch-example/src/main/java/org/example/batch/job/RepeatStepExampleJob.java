package org.example.batch.job;


import lombok.extern.slf4j.Slf4j;
import org.example.batch.bean.RepeatStepExampleBean;
import org.example.batch.domain.RepeatStepExampleDomain;
import org.example.batch.repository.RepeatStepExampleRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

@Slf4j
@Configuration
public class RepeatStepExampleJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private RepeatStepExampleBean repeatStepExampleBean;

    @Autowired
    private RepeatStepExampleRepository repeatStepExampleRepository;


    /**
     * 빈 초기화는 한 번이고. 스텝은 여러 번 호출된다.
     * 아래 내용은 Step 을 몇 번 호출할지를 결정하는 iterator.
     * @return
     */
    @Bean
    public Iterator<RepeatStepExampleDomain> repeatCountIterator() {
        RepeatStepExampleDomain repeatStepExampleDomain =
                RepeatStepExampleDomain.builder().id("1")
                                                 .data("aaa")
                                                 .build();
        RepeatStepExampleDomain repeatStepExampleDomain2 =
                RepeatStepExampleDomain.builder().id("2")
                        .data("bbb")
                        .build();
        repeatStepExampleRepository.save(repeatStepExampleDomain);
        repeatStepExampleRepository.save(repeatStepExampleDomain2);
        return repeatStepExampleRepository.findAll().iterator();
    }

    @Bean
    public Job repeatStepExampleJob01() {
        return jobBuilderFactory.get("repeatStepExampleJob01")
                .incrementer(new RunIdIncrementer())
                .start(repeatStepExampleStep01())
                .on("COMPLETED")
                .to(repeatStepExampleStep01())
                .on("FAILED")
                .end()
                .end()
                .build();
    }

    @Bean
    public Step repeatStepExampleStep01() {
        return stepBuilderFactory.get("repeatStepExampleStep01")
                .<String, String> chunk(100)
                .reader(repeatStepExampleBean)
                .processor(repeatStepExampleBean)
                .writer(repeatStepExampleBean)
                .build();
    }

}
