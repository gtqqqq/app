package com.yida.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yida.entity.Csat;
import com.yida.mapper.CsatMapper;
import com.yida.service.ICsatService;
import com.yida.service.ISurveyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author guantianqi
 * @since 2020-07-06
 */
@Slf4j
@DS("master")
@Service
public class CsatServiceImpl extends ServiceImpl<CsatMapper, Csat> implements ICsatService {

    @Autowired
    ISurveyService iSurveyService;

    //sqlserver Csat统计数定时写入mysql
    @Scheduled(cron = " 0 */1 * * * ?")
    public void testScheduled() {
        Date createDay = new Date();
        Csat csatPhone = new Csat();
        csatPhone.setCreateDay(createDay);
        csatPhone.setCsatType("Phone");
        Field[] Fields = csatPhone.getClass().getDeclaredFields();
        for (Field field : Fields) {
            field.setAccessible(true);
            if (field.getName().startsWith("h")) {
                Integer hour = Integer.valueOf(field.getName().substring(1));
                Integer rate = iSurveyService.selectCsatPhone(hour);
                try {
                    field.set(csatPhone, rate);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        Csat csatInc = new Csat();
        csatInc.setCreateDay(createDay);
        csatInc.setCsatType("Incident");
        Field[] iFields = csatInc.getClass().getDeclaredFields();
        for (Field field : iFields) {
            field.setAccessible(true);
            if (field.getName().startsWith("h")) {
                Integer hour = Integer.valueOf(field.getName().substring(1));
                Integer rate = iSurveyService.selectCsatIncident(hour);
                try {
                    field.set(csatInc, rate);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }


        Csat csatReq = new Csat();
        csatReq.setCreateDay(createDay);
        csatReq.setCsatType("Incident");
        Field[] rFields = csatReq.getClass().getDeclaredFields();
        for (Field field : rFields) {
            field.setAccessible(true);
            if (field.getName().startsWith("h")) {
                Integer hour = Integer.valueOf(field.getName().substring(1));
                try {
                    Integer rate = iSurveyService.selectCsatRequest(hour);
                    field.set(csatReq, rate);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    log.info("mysql:插入当天" +hour+ "时的Csat统计数异常");
                }
            }
        }


        QueryWrapper<Csat> queryWrapper = new QueryWrapper<Csat>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-MM-dd");
        queryWrapper.eq("create_Day", new Date());

        savemysql(csatPhone, queryWrapper, dateFormat);
        savemysql(csatInc, queryWrapper, dateFormat);
        savemysql(csatReq, queryWrapper, dateFormat);
    }

    private void savemysql(Csat csat, QueryWrapper<Csat> queryWrapper, SimpleDateFormat dateFormat) {
        try {
            List<Csat> list = this.list(queryWrapper);
            if (list.size() > 0) {
                log.info("mysql:删除当天" + dateFormat.format(new Date()) + "的Csat统计数");
                this.remove(queryWrapper);
                log.info("mysql:更新当天" + dateFormat.format(new Date()) + "的Csat统计数");
                this.save(csat);
            } else {
                log.info("mysql:首次添加当天" + dateFormat.format(new Date()) + "的Csat统计数");
                this.save(csat);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


}
