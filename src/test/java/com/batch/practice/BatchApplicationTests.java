package com.batch.practice;

import com.batch.practice.config.BatchConfig;
import com.batch.practice.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.StepScopeTestExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class,
        StepScopeTestExecutionListener.class })
@ContextConfiguration(classes = {BatchConfig.class, TestConfig.class})
public class BatchApplicationTests {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	public void testJob() throws Exception {
		commonAssertions(jobLauncherTestUtils.launchJob());
	}

	@Test
	public void testStep1() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1");

        // put data to executionCtx test
        assertEquals(getDataFromExecutionContext(jobExecution), "Pass To Next Step");

		commonAssertions(jobExecution);
	}


    @Test
    public void testStep2() throws Exception {
        StepExecution execution = MetaDataInstanceFactory.createStepExecution();
        execution.getExecutionContext().put("step2", "Pass To Next Step");

        JobExecution jobExecution = jobLauncherTestUtils.launchStep("step2", execution.getExecutionContext());

        // get data from executionCtx test
        assertEquals(getDataFromExecutionContext(jobExecution), "Pass To Next Step");

        commonAssertions(jobExecution);
    }

    private Object getDataFromExecutionContext(JobExecution jobExecution) {
        return jobExecution.getExecutionContext().get("step2");
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
