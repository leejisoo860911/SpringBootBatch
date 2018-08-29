package com.ljs.batch.biz.board;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BoardBatchTaskletConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BoardTasklet boardTasklet;

    @Bean("boardBatchTaskLetJob")
    public Job job() {
        return jobBuilderFactory.get("boardBatchTaskLetJob")
                .start(step())
                .build();
    }

    @Bean("boardTaskletStep")
    public Step step() {
        return stepBuilderFactory.get("boardTaskletStep")
                .tasklet(boardTasklet)
                .build();
    }


}
