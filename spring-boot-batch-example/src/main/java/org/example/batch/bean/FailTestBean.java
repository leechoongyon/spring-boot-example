package org.example.batch.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by LeeJunGyun.
 * Date: 2020-07-06
 * Time: 16:27
 * Desc:
 */


@Slf4j
@Component
@StepScope
public class FailTestBean implements ItemStream, ItemReader<String>, ItemProcessor<String, String>, ItemWriter<String> {

    private int readCount = 0;

    private String readStr = "Test";

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {

    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (readCount == 10) {
            return null;
        }
        readCount++;
        return readStr;
    }

    @Override
    public String process(String item) throws Exception {
        log.info("process item : {}", item);
        return item;
    }

    @Override
    public void write(List<? extends String> items) throws Exception {

    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {

    }

    @Override
    public void close() throws ItemStreamException {

    }
}
