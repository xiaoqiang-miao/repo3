package com.example.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobDemoJob(){
        return jobBuilderFactory.get("jobDemoJob")
                //.start(step1())
               // .next(step2())
               // .next(step3())
                .start(step11())
                .on("COMPLETED").to(step22())
                .from(step22()).on("COMPLETED").to(step33())
                .from(step33()).end()
                .build();
    }
    @Bean
    public Step step11(){
        return stepBuilderFactory.get("step1").tasklet(((stepContribution, chunkContext) -> {
            System.out.println("step1--->mylamda函数");
            return RepeatStatus.FINISHED;
        })).build();
    }
    @Bean
    public Step step22(){
        return stepBuilderFactory.get("step2").tasklet(((stepContribution, chunkContext) -> {
            System.out.println("miaoqiang");
            return RepeatStatus.FINISHED;
        })).build();
    }
    @Bean
    public Step step33(){
        return stepBuilderFactory.get("step3").tasklet(((stepContribution, chunkContext) -> {
            System.out.println("miaoqiang");
            return RepeatStatus.FINISHED;
        })).build();
    }
}
