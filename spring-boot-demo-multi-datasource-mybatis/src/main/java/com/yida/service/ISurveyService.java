package com.yida.service;

import com.yida.entity.Survey;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guantianqi
 * @since 2020-07-07
 */
public interface ISurveyService extends IService<Survey> {

    public Integer selectCsatRequest(int hour);

    public Integer selectCsatIncident(int hour);

    public Integer selectCsatPhone(int hour);
}
