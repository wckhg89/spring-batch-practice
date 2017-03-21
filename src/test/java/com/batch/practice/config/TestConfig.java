package com.batch.practice.config;

import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * Created by kanghonggu on 2017. 3. 17..
 */

@Configuration
@ComponentScan("com.batch.practice")
public class TestConfig {

    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils () {
        return new JobLauncherTestUtils();
    }

}
