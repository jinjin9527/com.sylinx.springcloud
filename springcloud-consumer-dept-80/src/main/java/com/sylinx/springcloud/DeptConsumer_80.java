package com.sylinx.springcloud;

import com.sylinx.myrule.SylinxRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name="SPRINGCLOUD-PROVIDER-DEPT" , configuration = SylinxRule.class)
public class DeptConsumer_80 {
    public static void main(String []args) {
        SpringApplication.run(DeptConsumer_80.class, args);
    }
}
