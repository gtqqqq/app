package com.yida.service.impl.mysql;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yida.entity.DailyComplianceStatisticsL3;
import com.yida.mapper.DailyComplianceStatisticsL3Mapper;
import com.yida.service.IDailyComplianceStatisticsL3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guantianqi
 * @since 2020-07-06
 */
@Slf4j
@DS("master")
@Service
public class DailyComplianceStatisticsL3ServiceImpl extends ServiceImpl<DailyComplianceStatisticsL3Mapper, DailyComplianceStatisticsL3> implements IDailyComplianceStatisticsL3Service {


}
