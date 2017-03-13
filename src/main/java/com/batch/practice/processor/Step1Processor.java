package com.batch.practice.processor;

import org.springframework.batch.item.ItemProcessor;

/**
 * Created by kanghonggu on 2017. 3. 13..
 */
public class Step1Processor implements ItemProcessor<String, String> {
    @Override
    public String process(String item) throws Exception {
        System.out.println("Item From Reader : " + item);

        return "Processor";
    }
}
