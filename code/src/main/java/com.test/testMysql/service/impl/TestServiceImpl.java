package com.test.testMysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.testMysql.entity.Test;
import com.test.testMysql.mapper.TestMapper;
import com.test.testMysql.service.ITestService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gtq
 * @since 2020-06-28
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
