package com.batch.practice.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by kanghonggu on 2017. 3. 13..
 */
@Component
@StepScope
public class Step1Processor implements ItemProcessor<String, String> {
    @Override
    public String process(String item) throws Exception {
        System.out.println("Item From Reader : " + item);

        return "Processor";
    }
}
