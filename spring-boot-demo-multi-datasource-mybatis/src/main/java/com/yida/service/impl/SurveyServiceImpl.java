package com.yida.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yida.entity.Survey;
import com.yida.mapper.SurveyMapper;
import com.yida.service.ISurveyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guantianqi
 * @since 2020-07-06
 */
@Slf4j
@DS("slave")
@Service
public class SurveyServiceImpl extends ServiceImpl<SurveyMapper, Survey> implements ISurveyService {


    @Override
    public Integer selectCsatRequest(int hour) {
        return baseMapper.selectCsatRequest(hour);
    }

    @Override
    public Integer selectCsatIncident(int hour) {
        return baseMapper.selectCsatIncident(hour);
    }

    @Override
    public Integer selectCsatPhone(int hour) {
        return baseMapper.selectCsatPhone(hour);
    }
}
