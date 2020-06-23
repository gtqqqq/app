package com.test.code.controler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.test.code.test.entity.Test;
import com.test.code.test.service.ITestService;
import com.test.code.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    ITestService iTestService;
    @Autowired
    RedisOperator redisOperator;
    @GetMapping("/test")
    public Object test(){
  Page page=new Page(1,10);          //1表示当前页，而10表示每页的显示显示的条目数
      Test ts = iTestService.getById(1);
        return ts;
    }
    @GetMapping("/testredis")
    public Object testRedis(String name){
        redisOperator.set("name",name);
        return redisOperator.get("name")+"@@@@@@@@@@@@@@@@";
    }

    @GetMapping("/testkafka")
    public Object testkafka(){
        Page page=new Page(1,10);          //1表示当前页，而10表示每页的显示显示的条目数
        Test ts = iTestService.getById(1);
        return ts;
    }

    @GetMapping("/testmq")
    public Object testmq(){
        Page page=new Page(1,10);          //1表示当前页，而10表示每页的显示显示的条目数
        Test ts = iTestService.getById(1);
        return ts;
    }

    @PostMapping("/testUpExcel")
    public Object testUpExcel(){
        Page page=new Page(1,10);          //1表示当前页，而10表示每页的显示显示的条目数
        Test ts = iTestService.getById(1);
        return ts;
    }

    @GetMapping("/testDownExcel")
    public Object testDownExcel(){
        Page page=new Page(1,10);          //1表示当前页，而10表示每页的显示显示的条目数
        Test ts = iTestService.getById(1);
        return ts;
    }

}
