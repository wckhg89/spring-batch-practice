package com.batch.practice.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.util.List;

/**
 * Created by kanghonggu on 2017. 3. 14..
 */
@Component
@StepScope
public class MultiStep2Writer implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> items) throws Exception {
        System.out.println("Multi Step2 Writer");
    }
}
