package com.sylinx.springcloud.controller;

import com.sylinx.springcloud.pojo.Dept;
import com.sylinx.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient client;

    @PostMapping("/dept/add")
    public boolean addDept(Dept dept){
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{deptno}")
    public Dept getDept(@PathVariable("deptno") Long deptno){

        Dept dept = deptService.queryById(deptno);
        if(dept == null) {
            throw new RuntimeException("no data exits : " + deptno);
        }

        return dept;
    }

    @GetMapping("/dept/list")
    public List<Dept> queryAll(){
        return deptService.queryAll();
    }

    @GetMapping("/dept/discovery")
    public Object discovery(){
        //
        List<String> services =  client.getServices();
        System.out.println("abc" + services);

        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");

        for (ServiceInstance obj : instances) {
            System.out.println(obj.getHost() + " : " + obj.getPort() + " : " + obj.getUri() + " : " + obj.getServiceId());
        }
        return this.client;
    }
}
