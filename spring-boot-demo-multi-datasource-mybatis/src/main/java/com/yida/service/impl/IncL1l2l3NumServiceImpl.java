package com.yida.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.yida.entity.IncL1l2l3Num;
import com.yida.mapper.IncL1l2l3NumMapper;
import com.yida.service.IIncL1l2l3NumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gtq
 * @since 2020-07-03
 */
@DS("master")
@Service
public class IncL1l2l3NumServiceImpl extends ServiceImpl<IncL1l2l3NumMapper, IncL1l2l3Num> implements IIncL1l2l3NumService {

}
