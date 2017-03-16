package com.batch.practice;

import com.batch.practice.config.BatchConfig;
import com.batch.practice.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
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
		commonAssertions(jobLauncherTestUtils.launchStep("step1"));
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
