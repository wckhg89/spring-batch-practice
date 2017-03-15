package com.batch.practice.common;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ExecutionContext;

/**
 * Created by kanghonggu on 2017. 3. 15..
 */
public class SuperStepExecution <T> {
    private StepExecution stepExecution;

    protected void putData(String key, T data) {
        if (this.stepExecution == null) {
            throw new NullPointerException("StepExecution is null");
        }

        ExecutionContext stepContext = this.stepExecution.getExecutionContext();
        stepContext.put(key, data);
    }

    protected Object getData(String key) {
        if (this.stepExecution == null) {
            throw new NullPointerException("StepExecution is null");
        }

        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext jobContext = jobExecution.getExecutionContext();

        return jobContext.get(key);
    }

    protected void setStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    protected StepExecution getStepExecution() {
        return this.stepExecution;
    }
}
