package com.batch.practice.processor;

import com.batch.practice.domain.Content;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(Step2Processor.class);
    private final int SPECIFIC_CONTENT_IDX = 0;

    @Override
    public Content process(List<Content> item) throws Exception {
        logger.info("Step2 Processor 시작");
        Content specificContent = item.get(SPECIFIC_CONTENT_IDX);

        logger.info("Step2 Processor 첫번째 회원의 첫번째 게시물 정보 : {}", specificContent);

        specificContent.modifyContent("글 수정");

        return specificContent;
    }
}
