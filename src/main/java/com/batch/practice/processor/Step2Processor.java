package com.batch.practice.processor;

import com.batch.practice.common.SuperStepExecution;
import com.batch.practice.domain.Content;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by kanghonggu on 2017. 3. 13..
 */
@Component
@StepScope
public class Step2Processor implements ItemProcessor<List<Content>, Content> {

    private final int SPECIFIC_CONTENT_IDX = 1;

    @Override
    public Content process(List<Content> item) throws Exception {

        Content specificContent = item.get(SPECIFIC_CONTENT_IDX);

        specificContent.modifyContent("글 수정");

        return specificContent;
    }
}
