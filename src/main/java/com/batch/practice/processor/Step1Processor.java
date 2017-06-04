package com.batch.practice.processor;

import com.batch.practice.common.DataShareBean;
import com.batch.practice.common.SuperStepExecution;
import com.batch.practice.domain.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by kanghonggu on 2017. 3. 13..
 */
@Component
@StepScope
public class Step1Processor extends SuperStepExecution<Member>
        implements ItemProcessor<List<Member>, Member> {

    @Autowired
    private DataShareBean<Member> dataShareBean;

    private static Logger logger = LoggerFactory.getLogger(Step1Processor.class);

    private final int SPECIFIC_MEMBER_IDX = 0;

    @Override
    public Member process(List<Member> item) throws Exception {
        logger.info("Step1 Processor 시작");
        Member specificMember = item.get(SPECIFIC_MEMBER_IDX);

        logger.info("Step1 Processor 첫번째 회원 정보 : {}", specificMember);
        // super.putData("SPECIFIC_MEMBER", specificMember);
        dataShareBean.putData("SPECIFIC_MEMBER", specificMember);

        return specificMember;
    }

//    @BeforeStep
//    public void saveStepExecution(StepExecution stepExecution) {
//        super.setStepExecution(stepExecution);
//    }
}
