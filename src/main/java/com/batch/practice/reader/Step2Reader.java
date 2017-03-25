package com.batch.practice.reader;


import com.batch.practice.common.SuperStepExecution;
import com.batch.practice.domain.Content;
import com.batch.practice.domain.Member;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by kanghonggu on 2017. 3. 13..
 */
@Component
@StepScope
public class Step2Reader extends SuperStepExecution<Member> implements ItemReader<List<Content>> {

    private boolean isRead;
    private Member specificMember;

    @PostConstruct
    public void init () {
        isRead = false;
    }

    @Override
    public List<Content> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (!isRead) {
            isRead = true;

            return this.specificMember.getContents();
        }

        return null;
    }

    @BeforeStep
    public void retrieveInterstepData(StepExecution stepExecution) {
        super.setStepExecution(stepExecution);
        this.specificMember = (Member) super.getData("SPECIFIC_MEMBER");

    }
}
