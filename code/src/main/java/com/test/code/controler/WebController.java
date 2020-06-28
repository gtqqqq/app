package com.test.code.controler;


import com.test.code.test.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @Autowired
    ITestService iTestService;
//    @Autowired
//    RedisOperator redisOperator;

    @GetMapping("/testhtml")
    public String testhtml() {

        return "wecome";
    }
}
