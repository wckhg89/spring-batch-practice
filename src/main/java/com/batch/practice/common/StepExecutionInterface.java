package com.batch.practice.common;


import org.springframework.batch.core.StepExecution;

/**
 * Created by kanghonggu on 2017. 3. 15..
 */
public interface StepExecutionInterface {
    void setStepExecution (StepExecution stepExecution);
    StepExecution getStepExecution ();
}
