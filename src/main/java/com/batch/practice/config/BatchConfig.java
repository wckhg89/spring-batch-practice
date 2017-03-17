package com.batch.practice.config;

import com.batch.practice.processor.Step1Processor;
import com.batch.practice.processor.Step2Processor;
import com.batch.practice.reader.Step1Reader;
import com.batch.practice.reader.Step2Reader;
import com.batch.practice.writer.MultiStep1Writer;
import com.batch.practice.writer.MultiStep2Writer;
import com.batch.practice.writer.Step1Writer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.item.ChunkOrientedTasklet;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.misc.Contended;

/**
 * Created by kanghonggu on 2017. 3. 13..
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public ItemReader<String> step1Reader () {
        return new Step1Reader();
    }

    @Bean
    public ItemProcessor<String, String> step1Processor () {
        return new Step1Processor();
    }

    @Bean
    public ItemWriter<String> step1Writer () {
        return new Step1Writer();
    }


    @Bean
    public ItemReader<String> step2Reader () {
        return new Step2Reader();
    }

    @Bean
    public ItemProcessor<String, String> step2Processor () {
        return new Step2Processor();
    }


    @Bean
    public ItemWriter<String> multiStep1writer () {
        return new MultiStep1Writer();
    }

    @Bean
    public ItemWriter<String> multiStep2writer () {
        return new MultiStep2Writer();
    }

    @Bean
    public Step step1 () {
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(1)
                .reader(step1Reader())
                .processor(step1Processor())
                .writer(step1Writer())
//                .listener(promotionListener())
                .build();
    }

    @Bean
    public Step step2 () {
        return stepBuilderFactory.get("step2")
                // commit-interval은 트랜잭션이 커밋되기 직전까지 처리되는 item의 수를 의미한다.
                .<String, String>chunk(1)
                .reader(step2Reader())
                .processor(step2Processor())
                .writer(multiStep2writer())
                .build();
    }

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("job")
                .start(step1())
                .build();
    }

    @Bean
    public ExecutionContextPromotionListener promotionListener () {
        ExecutionContextPromotionListener executionContextPromotionListener = new ExecutionContextPromotionListener();
        executionContextPromotionListener.setKeys(new String[]{"step2"});

        return executionContextPromotionListener;
    }

}
