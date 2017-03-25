package com.batch.practice.processor;

import com.batch.practice.common.SuperStepExecution;
import com.batch.practice.domain.Member;
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
public class Step1Processor extends SuperStepExecution<Member>
        implements ItemProcessor<List<Member>, Member> {

    private final int SPECIFIC_MEMBER_IDX = 0;

    @Override
    public Member process(List<Member> item) throws Exception {
        Member specificMember = item.get(SPECIFIC_MEMBER_IDX);

        super.putData("SPECIFIC_MEMBER", specificMember);

        return specificMember;
    }

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        super.setStepExecution(stepExecution);
    }
}
