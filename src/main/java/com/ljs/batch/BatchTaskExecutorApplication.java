package com.ljs.batch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.support.SimpleJvmExitCodeMapper;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BatchTaskExecutorApplication {

	public static void main(String[] args) throws NoSuchJobException, JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
		ConfigurableApplicationContext appContext = SpringApplication.run(BatchTaskExecutorApplication.class, args);

		JobLauncher jobLauncher = appContext.getBean(JobLauncher.class);
		JobRegistry jobRegistry = appContext.getBean(JobRegistry.class);

		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();

		/*
		for (int i = 2; i < args.length; i++) {
			String[] param = args[i].split("=");
			builder.addParameter(param[0], new JobParameter(param[1]));
		}
		*/

		jobParametersBuilder.addParameter("BASE_DATE", new JobParameter("20180801"));
		JobExecution jobExecution = jobLauncher.run(jobRegistry.getJob("boardBatchJob"), jobParametersBuilder.toJobParameters());

		if (BatchStatus.FAILED.equals(jobExecution.getStatus())) {
			System.exit(SimpleJvmExitCodeMapper.JVM_EXITCODE_GENERIC_ERROR);
		}

		System.out.println(jobExecution.getStatus());
	}

}