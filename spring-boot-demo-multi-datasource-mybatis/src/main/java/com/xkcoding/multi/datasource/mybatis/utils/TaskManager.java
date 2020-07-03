package com.xkcoding.multi.datasource.mybatis.utils;


import org.springframework.stereotype.Component;

/**
 * @desc spirngboot 定时任务使用：
 * 1、通过@Scheduled声明一个计划任务,Scheduled包含cron(unix下定时任务)、fixDelay(延时执行)、fixRate(间隔固定时间执行)。
 * 2、在启动类上增加@EnableScheduling注解开启对计划任务的支持。
 */
@Component
public class TaskManager {

//    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //fixedRate:上一次开始执行时间点后再次执行；间隔22秒执行一次
//    @Scheduled(fixedRate = 1000*22)
////    public void taskIncident() {
////        System.out.println(">>>>>> fixRateTime >>>>>间隔22秒执行一次..：" + dateFormat.format(new Date()));
////    }

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

    public static void main(String[] args) {
        System.out.println(">>>>> fixedDelayStringTime >>>>>从配置文件加载任务信息，当前时间：");
    }
}
