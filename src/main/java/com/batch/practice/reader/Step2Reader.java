package com.batch.practice.reader;


import com.batch.practice.common.DataShareBean;
import com.batch.practice.common.SuperStepExecution;
import com.batch.practice.domain.Content;
import com.batch.practice.domain.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DataShareBean<Member> dataShareBean;

    private static Logger logger = LoggerFactory.getLogger(Step2Reader.class);
    private boolean isRead;
    private Member specificMember;

    @PostConstruct
    public void init () {
        isRead = false;
    }

    @Override
    public List<Content> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        logger.info("Step2 Reader 시작");
        if (!isRead) {
            isRead = true;
            List<Content> contentList = this.specificMember.getContents();

            logger.info("Step2 Reader 첫번째 회원의 게시글 수 : {}", contentList.size());

            return contentList;
        }

        return null;
    }

    @BeforeStep
    public void retrieveInterstepData(StepExecution stepExecution) {
        // super.setStepExecution(stepExecution);
        // this.specificMember = (Member) super.getData("SPECIFIC_MEMBER");
        this.specificMember = dataShareBean.getData("SPECIFIC_MEMBER");
    }
}
