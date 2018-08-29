package com.ljs.batch.biz.board;

import com.ljs.batch.domain.Board;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BoardBatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BoardReader reader;

    @Autowired
    public BoardProcessor processor;

    @Autowired
    public BoardWriter writer;

    @Bean("boardBatchJob")
    public Job job() {
        return jobBuilderFactory.get("boardBatchJob")
                .start(step())
                .build();
    }

    @Bean("boardBatchStep")
    public Step step() {
        return stepBuilderFactory.get("boardBatchStep")
                .<Board, Board>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }


}
