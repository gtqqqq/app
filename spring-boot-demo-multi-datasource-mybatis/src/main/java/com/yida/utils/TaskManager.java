package com.yida.utils;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yida.entity.DailyComplianceStatistics;
import com.yida.entity.DailyComplianceStatisticsL3;
import com.yida.service.IDailyComplianceStatisticsL3Service;
import com.yida.service.OLADailyCompliService;
import com.yida.service.impl.CsatServiceImpl;
import com.yida.service.impl.IncidentServiceImpl;
import com.yida.service.impl.RequestServiceImpl;
import com.yida.service.impl.mysql.L2TeamticketServiceImpl;
import com.yida.service.impl.mysql.L3ProductionControlServiceImpl;
import com.yida.service.impl.mysql.L3ProductionControlTotalServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @desc spirngboot 定时任务使用：
 * 1、通过@Scheduled声明一个计划任务,Scheduled包含cron(unix下定时任务)、fixDelay(延时执行)、fixRate(间隔固定时间执行)。
 * 2、在启动类上增加@EnableScheduling注解开启对计划任务的支持。
 */
@Slf4j
@Component
public class TaskManager {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //fixedRate:上一次开始执行时间点后再次执行；间隔22秒执行一次
    @Scheduled(fixedRate = 1000 * 22)
    public void testTask() {
        taskOLA();
        CsatServiceImpl.taskCsat();
        requestService.taskRequest();
        incidentService.taskIncident();
        l3ProductionControlTotalService.taskL3ProductionControlTotal();
        l3ProductionControlService.taskL3Production();
        l2TeamticketService.taskL2Teamticket();

        System.out.println(">>>>>> fixRateTime >>>>>间隔22秒执行一次..：" + dateFormat.format(new Date()));

    }

    public void taskOLA() {
        QueryWrapper<DailyComplianceStatistics> queryWrapper = new QueryWrapper<DailyComplianceStatistics>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-MM-dd");
        queryWrapper.apply(" DATE_FORMAT(create_time,'%Y-%m-%d')= DATE_FORMAT({0},'%Y-%m-%d')", new Date());
        DailyComplianceStatistics dataL2 = olaDailyCompliService.getModelL2Data();
        if (dataL2 != null) {
            savemysqlL2(dataL2, queryWrapper, dateFormat);
        } else {
            dataL2 = new DailyComplianceStatistics();
            dataL2.setCreateTime(new Date());
            savemysqlL2(dataL2, queryWrapper, dateFormat);
        }
        QueryWrapper<DailyComplianceStatisticsL3> queryWrapperl3 = new QueryWrapper<DailyComplianceStatisticsL3>();
        queryWrapperl3.apply(" DATE_FORMAT(create_time,'%Y-%m-%d')= DATE_FORMAT({0},'%Y-%m-%d')", new Date());
        DailyComplianceStatistics dataL3 = olaDailyCompliService.getModelL3Data();
        DailyComplianceStatisticsL3 l3 = new DailyComplianceStatisticsL3();

        BeanUtils.copyProperties(dataL3, l3);
        if (dataL3 != null) {
            savemysqlL3(l3, queryWrapperl3, dateFormat);
        } else {
            l3 = new DailyComplianceStatisticsL3();
            l3.setCreateTime(new Date());
            savemysqlL3(l3, queryWrapperl3, dateFormat);
        }

    }

    private void savemysqlL2(DailyComplianceStatistics dailyComplianceStatistics, QueryWrapper<DailyComplianceStatistics> queryWrapper, SimpleDateFormat dateFormat) {
        try {
            List<DailyComplianceStatistics> list = olaDailyCompliService.list(queryWrapper);
            if (list.size() > 0) {
                log.info("mysql:删除当天" + dateFormat.format(new Date()) + "的OLA");
                olaDailyCompliService.remove(queryWrapper);
                log.info("mysql:更新当天" + dateFormat.format(new Date()) + "的OLA");
                olaDailyCompliService.insertSelective(dailyComplianceStatistics);
            } else {
                log.info("mysql:首次添加当天" + dateFormat.format(new Date()) + "的OLA");
                olaDailyCompliService.insertSelective(dailyComplianceStatistics);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void savemysqlL3(DailyComplianceStatisticsL3 dailyComplianceStatisticsL3, QueryWrapper<DailyComplianceStatisticsL3> queryWrapper, SimpleDateFormat dateFormat) {
        try {
            List<DailyComplianceStatisticsL3> list = iDailyComplianceStatisticsL3Service.list(queryWrapper);
            if (list.size() > 0) {
                log.info("mysql:删除当天" + dateFormat.format(new Date()) + "的OLA");
                iDailyComplianceStatisticsL3Service.remove(queryWrapper);
                log.info("mysql:更新当天" + dateFormat.format(new Date()) + "的OLA");
                iDailyComplianceStatisticsL3Service.save(dailyComplianceStatisticsL3);
            } else {
                log.info("mysql:首次添加当天" + dateFormat.format(new Date()) + "的OLA");
                iDailyComplianceStatisticsL3Service.save(dailyComplianceStatisticsL3);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
//    // 第一次延时1秒执行，以后每2秒执行一次
//    @Scheduled(initialDelay = 1000, fixedDelay = 2000)
//    public void fixedDelayTime() {
//        System.out.println(">>>>>>>>>> fixedDelayTime >>>>>>>>第一次1秒后执行，当执行完后2秒再执行.：" + dateFormat.format(new Date()));
//    }

    //    // fixedDelay：上一次执行完毕时间点后再次执行；每天定点执行
//    @Scheduled(cron = "0 25 23 ? * *")
//    public void cronTime() {
//        System.out.println(">>>>> cronTime >>>>>>每天23点25开始执行.：" + dateFormat.format(new Date()));
//    }
//
//    //通过读取配置文件属性执行
//    @Scheduled(fixedDelayString = "${fixedDelay}")
//    public void fixedDelayStringTime() {
//        System.out.println(">>>>> fixedDelayStringTime >>>>>从配置文件加载任务信息，当前时间：" + dateFormat.format(new Date()));
//    }
    @Autowired
    OLADailyCompliService olaDailyCompliService;
    @Autowired
    IDailyComplianceStatisticsL3Service iDailyComplianceStatisticsL3Service;
    @Autowired
    CsatServiceImpl CsatServiceImpl;
    @Autowired
    RequestServiceImpl requestService;
    @Autowired
    IncidentServiceImpl incidentService;
    @Autowired
    L3ProductionControlTotalServiceImpl l3ProductionControlTotalService;
    @Autowired
    L3ProductionControlServiceImpl l3ProductionControlService;
    @Autowired
    L2TeamticketServiceImpl l2TeamticketService;

    public static void main(String[] args) {
        System.out.println(">>>>> fixedDelayStringTime >>>>>从配置文件加载任务信息，当前时间：");
    }
}
