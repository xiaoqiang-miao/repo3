package com.example.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FowDemo {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step flowDemoStep1(){
        return stepBuilderFactory.get("flowDemoStep1").tasklet(((stepContribution, chunkContext) -> {
            System.out.println("123123123");
            return RepeatStatus.FINISHED;
        })).build();
    }
    @Bean
    public Step flowDemoStep2(){
        return stepBuilderFactory.get("flowDemoStep2").tasklet(((stepContribution, chunkContext) -> {
            System.out.println("321321321");
            return RepeatStatus.FINISHED;
        })).build();
    }
    @Bean
    public Step flowDemoStep3(){
        return stepBuilderFactory.get("flowDemoStep3").tasklet(((stepContribution, chunkContext) -> {
            System.out.println("456456456");
            return RepeatStatus.FINISHED;
        })).build();
    }
    @Bean
    public Flow flowDemoFlow(){
        return new FlowBuilder<Flow>("flowDemoFlow")
                .start(flowDemoStep1())
                .next(flowDemoStep2())
                .build();
    }
    @Bean
    public Flow flowDemoFlow2(){
        return new FlowBuilder<Flow>("flowDemoFlow2")
                .start(flowDemoStep3())
                .build();
    }
    @Bean
    public Job jobFlowDemo1(){
        return jobBuilderFactory.get("jobFlowDemo1")
                .start(flowDemoFlow())
                .next(flowDemoFlow2())
                .end()
                .build();
    }

}
