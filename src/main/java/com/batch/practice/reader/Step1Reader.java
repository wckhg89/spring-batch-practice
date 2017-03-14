package com.batch.practice.reader;


import org.springframework.batch.core.annotation.AfterStep;
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
public class Step1Reader implements ItemReader<String> {

    private int cnt = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        cnt++;
        System.out.println("Call Cnt = " + cnt);

        // 더 이상 읽어올 Item이 없을 때에는 read()메소드에서 null값을 반환. 그 전까지는 순차적인 값을 리턴
        return cnt == 2 ? null : "Reader";
        // return "Reader";
    }

    @AfterStep
    public String end () {
        return null;
    }
}
