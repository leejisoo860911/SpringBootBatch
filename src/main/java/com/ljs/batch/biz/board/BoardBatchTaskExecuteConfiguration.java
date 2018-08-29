package com.ljs.batch.biz.board;

import com.ljs.batch.domain.Board;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.CommandLineJobRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class BoardBatchTaskExecuteConfiguration {

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

    @Bean("boardBatchTaskExecuteJob")
    public Job job() {
        return jobBuilderFactory.get("boardBatchTaskExecuteJob")
                .start(step())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        return new SimpleAsyncTaskExecutor("boardBatchTaskExecutor");
    }

    @Bean("boardBatchTaskExecutorStep")
    public Step step() {
        return stepBuilderFactory.get("boardBatchTaskExecutorStep")
                .<Board, Board>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .taskExecutor(taskExecutor())
                .build();
    }

}
