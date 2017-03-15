package com.batch.practice.processor;

import com.batch.practice.common.SuperStepExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by kanghonggu on 2017. 3. 13..
 */
@Component
@StepScope
public class Step2Processor extends SuperStepExecution<String> implements ItemProcessor<String, String> {
    private Object someObject;

    @Override
    public String process(String item) throws Exception {
        System.out.println(someObject + " " + item);


        return null;
    }

    @BeforeStep
    public void retrieveInterstepData(StepExecution stepExecution) {
        super.setStepExecution(stepExecution);
        this.someObject = super.getData("step2");

    }
}
