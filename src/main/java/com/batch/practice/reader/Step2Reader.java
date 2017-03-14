package com.batch.practice.reader;


import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

/**
 * Created by kanghonggu on 2017. 3. 13..
 */
@Component
@StepScope
public class Step2Reader implements ItemReader<String> {

    private int cnt = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        cnt++;
        System.out.println("Call Step2 Cnt = " + cnt);

        return cnt == 2 ? null : "Step2 Reader";
        // return "Step2 Reader";

    }
}
