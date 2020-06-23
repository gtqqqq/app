package com.test.code.test.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.code.test.entity.Test;
import com.test.code.test.mapper.TestMapper;
import com.test.code.test.service.ITestService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-06-14
 */
@Service
@DS("mssql")
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
