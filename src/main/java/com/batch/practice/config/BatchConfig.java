package com.batch.practice.config;

import com.batch.practice.domain.Content;
import com.batch.practice.domain.Member;
import com.batch.practice.processor.Step1Processor;
import com.batch.practice.processor.Step2Processor;
import com.batch.practice.reader.Step1Reader;
import com.batch.practice.reader.Step2Reader;
import com.batch.practice.writer.Step2Writer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.PostConstruct;
import java.util.List;

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
    @Primary
    public BatchProperties batchProperties () {
        final String SCHEMA_LOCATION = "classpath:custom_batch_schema-h2.sql";
        BatchProperties batchProperties = new BatchProperties();
        batchProperties.setSchema(SCHEMA_LOCATION);

        return batchProperties;
    }

    @Bean
    public ItemReader<List<Member>> step1Reader () {
        return new Step1Reader();
    }

    @Bean
    public ItemProcessor<List<Member>, Member> step1Processor () {
        return new Step1Processor();
    }

    @Bean
    public ItemReader<List<Content>> step2Reader () {
        return new Step2Reader();
    }


    @Bean
    public ItemProcessor<List<Content>, Content> step2Processor () {
        return new Step2Processor();
    }

    @Bean
    public ItemWriter<Content> step2Writer () {
        return new Step2Writer();
    }



    @Bean
    public Step step1 () {
        return stepBuilderFactory.get("step1")
                .<List<Member>, Member>chunk(1)
                .reader(step1Reader())
                .processor(step1Processor())
                .listener(promotionListener())
                .build();
    }

    @Bean
    public Step step2 () {
        return stepBuilderFactory.get("step2")
                // commit-interval은 트랜잭션이 커밋되기 직전까지 처리되는 item의 수를 의미한다.
                .<List<Content>, Content>chunk(1)
                .reader(step2Reader())
                .processor(step2Processor())
                .writer(step2Writer())
                .listener(promotionListener())
                .build();
    }

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("job")
                .start(step1())
                .next(step2())
                .build();
    }

    @Bean
    public ExecutionContextPromotionListener promotionListener () {
        ExecutionContextPromotionListener executionContextPromotionListener = new ExecutionContextPromotionListener();
        executionContextPromotionListener.setKeys(new String[]{"SPECIFIC_MEMBER"});

        return executionContextPromotionListener;
    }

}
