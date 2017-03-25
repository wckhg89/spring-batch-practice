package com.batch.practice.writer;

import com.batch.practice.domain.Content;
import com.batch.practice.repository.ContentRepository;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by kanghonggu on 2017. 3. 13..
 */
@Component
@StepScope
public class Step2Writer implements ItemWriter<Content> {

    @Autowired
    private ContentRepository contentRepository;

    @Override
    @Transactional
    public void write(List<? extends Content> items) throws Exception {

        contentRepository.save(items);

    }

}
