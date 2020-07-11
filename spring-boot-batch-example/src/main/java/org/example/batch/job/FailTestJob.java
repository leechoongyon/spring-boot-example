package org.example.batch.job;

import org.example.batch.bean.FailTestBean;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by LeeJunGyun.
 * Date: 2020-07-06
 * Time: 16:26
 * Desc:
 */

@Configuration
public class FailTestJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    private FailTestBean failTestBean;

    public FailTestJob(FailTestBean failTestBean) {
        this.failTestBean = failTestBean;
    }

    @Bean
    public Job failTestJob01() {
        return jobBuilderFactory.get("failTestJob01")
                .incrementer(new RunIdIncrementer())
                .start(failTestStep01())
                .build()
                ;
    }

    @Bean
    public Step failTestStep01() {
        return stepBuilderFactory.get("failTestStep01")
                .<String, String>chunk(100)
                .reader(failTestBean)
                .processor(failTestBean)
                .writer(failTestBean)
                .build();
    }
}
