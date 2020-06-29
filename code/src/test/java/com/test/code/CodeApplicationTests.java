package com.test.code;

import com.test.code.test.service.ITestService;
import com.test.testMysql.mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootTest
@EnableScheduling
class CodeApplicationTests {
	@Autowired
	ITestService iTestService;
	@Test
	void contextLoads() {
		System.out.println(iTestService.getById(1));
	}

}
