package com.batch.practice.domain;

import com.batch.practice.common.DataShareBean;
import com.batch.practice.config.BatchConfig;
import com.batch.practice.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;


/**
 * Created by kanghonggu on 2017. 6. 4..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BatchConfig.class, TestConfig.class})
public class DataShareBeanTest {

    @Autowired
    private DataShareBean<Member> dataShareBean;

    private Member shareMember;


    @Before
    public void 멤버만들기 () {
        shareMember = new Member(1L, "pw", "name", "email");
    }

    @Test
    public void 공유빈_멤버_테스트 () {
        dataShareBean.putData("MEMBER1", shareMember);

        assertEquals(1, dataShareBean.getSize());

    }

}
