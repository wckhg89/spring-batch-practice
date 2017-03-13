package com.batch.practice.reader;


import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * Created by kanghonggu on 2017. 3. 13..
 */
public class Step1Reader implements ItemReader<String> {
    public int cnt = 0;
    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        cnt++;
        System.out.println("Call Cnt = " + cnt);
        return cnt == 2 ? null : "Reader";
    }
}
