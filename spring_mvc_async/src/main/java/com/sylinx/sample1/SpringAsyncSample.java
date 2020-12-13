package com.sylinx.sample1;

import com.sylinx.sample1.async.CompletionLoggingHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableAsync
public class SpringAsyncSample extends WebMvcConfigurerAdapter {

    @Autowired
    AsyncTaskExecutor  asyncTaskExecutor;

    public static void main(String []args) {
        SpringApplication.run(SpringAsyncSample.class, args);
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(asyncTaskExecutor); // Spring MVC管理下でもスレッドプールを使う
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CompletionLoggingHandlerInterceptor()); // HandlerInterceptorの適用
    }
}
