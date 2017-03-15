package com.batch.practice.writer;

import com.batch.practice.common.StepExecutionAbst;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by kanghonggu on 2017. 3. 13..
 */
@Component
@StepScope
public class Step1Writer extends StepExecutionAbst<String> implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> items) throws Exception {
        System.out.println("Items from processor : " + items.toString());
        super.putData("step2", "Pass To Next Step");
    }

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        super.setStepExecution(stepExecution);
    }
}
