package com.batch.practice;

import com.batch.practice.common.DataShareBean;
import com.batch.practice.config.BatchConfig;
import com.batch.practice.config.TestConfig;
import com.batch.practice.domain.Content;
import com.batch.practice.domain.Member;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.StepScopeTestExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class,
        StepScopeTestExecutionListener.class })
@ContextConfiguration(classes = {BatchConfig.class, TestConfig.class})
public class BatchApplicationTests {

    private static Logger logger = LoggerFactory.getLogger(BatchApplicationTests.class);

    @Autowired
	private DataShareBean<Member> dataShareBean;

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	@Transactional
	public void testJob() throws Exception {
		commonAssertions(jobLauncherTestUtils.launchJob());
	}


	@Test
	@Transactional
	public void testStep1_SHARED_BEAN () throws Exception {
		JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1");

		assertNotNull(getDataFromDataSharedBean());

		commonAssertions(jobExecution);
	}

	@Test
	@Transactional
	public void testStep2_SHARED_BEAN () throws Exception {
		Member mockMember = new Member(2L, "test", "줌구2", "wckhg89@gmail.com");

		List<Content> contents = new ArrayList<>();
		contents.add(new Content(mockMember, "test", new DateTime()));

		mockMember.setContents(contents);

		dataShareBean.putData("SPECIFIC_MEMBER", mockMember);


		JobExecution jobExecution = jobLauncherTestUtils.launchStep("step2");

		// get data from executionCtx test
		assertEquals(getDataFromDataSharedBean(), mockMember);

		commonAssertions(jobExecution);
	}

	@Test
	@Transactional
	public void testStep1_STEP_EXECUTION () throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1");

        assertNotNull(getDataFromExecutionContext(jobExecution));

		commonAssertions(jobExecution);
	}

    @Test
	@Transactional
    public void testStep2_STEP_EXECUTION() throws Exception {

		Member mockMember = new Member(2L, "test", "줌구2", "wckhg89@gmail.com");

		List<Content> contents = new ArrayList<>();
		contents.add(new Content(mockMember, "test", new DateTime()));

		mockMember.setContents(contents);

        StepExecution execution = MetaDataInstanceFactory.createStepExecution();
        execution.getExecutionContext().put("SPECIFIC_MEMBER", mockMember);

        JobExecution jobExecution = jobLauncherTestUtils.launchStep("step2", execution.getExecutionContext());

        // get data from executionCtx test
        assertEquals(getDataFromExecutionContext(jobExecution), mockMember);

        commonAssertions(jobExecution);
    }

    private Object getDataFromDataSharedBean () {
		return dataShareBean.getData("SPECIFIC_MEMBER");
	}

    private Object getDataFromExecutionContext(JobExecution jobExecution) {
        return jobExecution.getExecutionContext().get("SPECIFIC_MEMBER");
    }


    private void commonAssertions(JobExecution jobExecution) {
		assertNotNull(jobExecution);
		BatchStatus batchStatus = jobExecution.getStatus();
		assertEquals(BatchStatus.COMPLETED, batchStatus);
		assertFalse(batchStatus.isUnsuccessful());

		ExitStatus exitStatus = jobExecution.getExitStatus();
		assertEquals("COMPLETED", exitStatus.getExitCode());
		assertEquals("", exitStatus.getExitDescription());

		List<Throwable> failureExceptions = jobExecution.getFailureExceptions();
		assertNotNull(failureExceptions);
		assertTrue(failureExceptions.isEmpty());
	}

}
