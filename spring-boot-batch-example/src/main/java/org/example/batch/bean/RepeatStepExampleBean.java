package org.example.batch.bean;


import lombok.extern.slf4j.Slf4j;
import org.example.batch.domain.RepeatStepExampleDomain;
import org.example.batch.job.RepeatStepExampleJob;
import org.example.batch.repository.RepeatStepExampleRepository;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Component
@StepScope
public class RepeatStepExampleBean implements ItemStream, ItemReader<String>,
        ItemProcessor<String, String>, ItemWriter<String>, StepExecutionListener {

    private String readData = null;

    @Autowired
    private Iterator<RepeatStepExampleDomain> repeatCountIterator;

    private Iterator<String> iterator;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            return null;
        }
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        RepeatStepExampleRepository repeatStepExampleRepository;
        if (repeatCountIterator.hasNext()) {
            readData = repeatCountIterator.next().getData();
            iterator = new ArrayList<>(Arrays.asList(readData, readData)).iterator();
        } else {
            readData = null;
        }
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {

    }

    @Override
    public void close() throws ItemStreamException {

    }

    @Override
    public void write(List<? extends String> items) throws Exception {

    }

    @Override
    public String process(String item) throws Exception {
        log.info("##############################");
        log.info("item : {}", item);
        log.info("##############################");
        return item;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    /**
     * 다음 데이터가 없으면 종료시킨다.
     * @param stepExecution
     * @return
     */
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("repeatCountIterator.hasNext() : {}", repeatCountIterator.hasNext());
        return repeatCountIterator.hasNext() ? ExitStatus.EXECUTING : ExitStatus.FAILED;
    }
}
