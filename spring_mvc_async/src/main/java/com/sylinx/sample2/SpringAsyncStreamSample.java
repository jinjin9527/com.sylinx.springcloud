package com.sylinx.sample2;

import com.sylinx.sample1.SpringAsyncSample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringAsyncStreamSample {

    public static void main(String []args) {
        SpringApplication.run(SpringAsyncStreamSample.class, args);
    }
}
