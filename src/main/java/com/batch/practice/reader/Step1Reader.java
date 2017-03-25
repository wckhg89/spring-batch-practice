package com.batch.practice.reader;


import com.batch.practice.domain.Member;
import com.batch.practice.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by kanghonggu on 2017. 3. 13..
 */
@Component
@StepScope
public class Step1Reader implements ItemReader<List<Member>> {

    private static Logger logger = LoggerFactory.getLogger(Step1Reader.class);

    @Autowired
    private MemberRepository memberRepository;

    private boolean isRead;

    @PostConstruct
    public void init () {
        isRead = false;
    }

    @Override
    public List<Member> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        logger.info("Step1 Reader Start");
        if (!isRead) {
            List<Member> memberList = memberRepository.findAll();
            isRead = true;

            logger.info("Step1 Reader 회원 수 : {}", memberList.size());

            return memberList;
        }

        // 더 이상 읽어올 Item이 없을 때에는 read()메소드에서 null값을 반환. 그 전까지는 순차적인 값을 리턴
        return null;
    }



}
