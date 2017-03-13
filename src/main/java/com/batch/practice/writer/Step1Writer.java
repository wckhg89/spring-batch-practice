package com.batch.practice.writer;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by kanghonggu on 2017. 3. 13..
 */
public class Step1Writer implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> items) throws Exception {
        System.out.println("Items from processor : " + items.toString());

        return;
    }
}
