package com.example.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，
 * 这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，
 * 并用于构建bean定义，初始化Spring容器。
 *
 * 注意：@Configuration注解的配置类有如下要求：
 *
 * Configuration不可以是final类型；
 * Configuration不可以是匿名类；
 * 嵌套的configuration必须是静态类。
 *
 */
@Configuration
public class JobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloWorleJob(){
        return jobBuilderFactory.get("helloWorleJob").start(step1()).build();
    }
    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1").tasklet((stepContribution, chunkContext) -> {
            System.out.println("11111");
            return RepeatStatus.FINISHED;
        }).build();
    }
}
