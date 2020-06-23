package com.test.code.controler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.test.code.test.entity.Test;
import com.test.code.test.service.ITestService;
import com.test.code.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WebController {

    @Autowired
    ITestService iTestService;
    @Autowired
    RedisOperator redisOperator;

    @GetMapping("/testhtml")
    public String testhtml(){

        return "wecome";
    }
}
