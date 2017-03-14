package com.batch.practice;

import javafx.application.Application;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class BatchApplication {

    private static String[] JOB_LIST = {
            "job"
    };

	public static void main(String[] args) throws Exception {

	    if (args.length <  1) {
	        throw new IllegalArgumentException("No Argument");
        }

        String job = args[0];

	    if (!Arrays.asList(JOB_LIST).contains(job)) {
	        throw new IllegalArgumentException("Illegal Argument");
        }


		SpringApplication app = new SpringApplication(BatchApplication.class);

        ConfigurableApplicationContext ctx = app.run(args);

        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);

        Job newJob = ctx.getBean(job, Job.class);

        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date())
                .addParameter(job, new JobParameter(job))
                .toJobParameters();


        jobLauncher.run(newJob, jobParameters);

        System.exit(0);
	}
}
