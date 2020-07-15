package com.yida.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yida.entity.DailyComplianceStatistics;
import com.yida.mapper.CdcMetricMapper;
import com.yida.mapper.DailyComplianceStatisticsMapper;
import com.yida.service.OLADailyCompliService;
import com.yida.utils.CopyField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

/**
 * @author fnchenxi
 */
@Slf4j
@Service
@DS("slave")
public class OLADailyCompliServiceImpl extends ServiceImpl<DailyComplianceStatisticsMapper, DailyComplianceStatistics> implements OLADailyCompliService {

    @Autowired
    CdcMetricMapper cdcMetricMapper;

    @Autowired
    DailyComplianceStatisticsMapper dailyComplianceStatisticsMapper;


    @Override
    public DailyComplianceStatistics getModelL2Data() {
        DailyComplianceStatistics model = new DailyComplianceStatistics();
        List<Map<String, Object>> list = cdcMetricMapper.selectL2OLAList();
        if (!list.isEmpty()) {
            model = operationMap(list, model, "OLA");
            return model;
        }
        return null;
    }

    @Override
    public DailyComplianceStatistics getModelL3Data() {
        DailyComplianceStatistics model = new DailyComplianceStatistics();
        List<Map<String, Object>> list = cdcMetricMapper.selectL3OLAList();
        if (!list.isEmpty()) {
            model = operationMap(list, model, "OLA");
            return model;
        }
        return null;
    }

    /**
     * 操作查询的结果，统计达标率和封装为数据库需要的实体对象
     *
     * @param list
     * @param model
     * @return
     */
    public DailyComplianceStatistics operationMap(List<Map<String, Object>> list, DailyComplianceStatistics model, String type) {
        Map<Integer, List<Map<String, Object>>> format = new HashMap<>();
        /**
         * 1.把查询的结果根据小时(H)进行分组
         */
        if (!list.isEmpty()) {
            for (Map<String, Object> val : list) {
                Date opened = (Date) val.get("opened");
                int hours = opened.getHours();
                if (format.get(hours) == null) {
                    List<Map<String, Object>> openedList = new ArrayList<>();
                    openedList.add(val);
                    format.put(hours, openedList);
                }
                }
            }

            /**
             * 2.遍历封装好的数据 统计哪些数据达标,统一分配一个标识状态，下一步做统计
             */
            for (List<Map<String, Object>> values : format.values()) {
                for (Map<String, Object> i : values) {
                    String priority = (String) i.get("Priority");
                    Integer duration = (Integer) i.get("Duration");

                    if (priority != null && "Priority 4".equals(priority)) {
                        if (duration >= 2) {
                            i.put("status", true);
                        } else {
                            i.put("status", false);
                        }
                    } else if (priority != null && "Priority 3".equals(priority)) {
                        if (duration >= 2) {
                            i.put("status", true);
                        } else {
                            i.put("status", false);
                        }
                    } else if (priority != null && "Priority 2".equals(priority)) {
                        if (duration >= 1) {
                            i.put("status", true);
                        } else {
                            i.put("status", false);
                        }
                    } else {
                        if (duration >= 1) {
                            i.put("status", true);
                        } else {
                            i.put("status", false);
                        }
                    }
                }
            }

            /**
             * 3.根据status的标识值计算百分百 算法为:合格/该小时的总数量
             */

            for (List<Map<String, Object>> values : format.values()) {
                BigDecimal range = new BigDecimal(0);
                int total = 0;
                for (Map<String, Object> i : values) {
                    Boolean status = (Boolean) i.get("status");
                    if (status) {
                        total += 1;
                    }
                }
                int queryMailNum = values.size();
                // 创建一个数值格式化对象
                NumberFormat numberFormat = NumberFormat.getInstance();
                // 设置精确到小数点后2位
                numberFormat.setMaximumFractionDigits(2);
                String result = numberFormat.format((float) total / (float) queryMailNum * 100);
                System.out.println("百分比为:" + result + "%");
            }

            int dailyCases = 0;
            for (Map.Entry<Integer, List<Map<String, Object>>> en : format.entrySet()) {
                Integer key = en.getKey();
                List<Map<String, Object>> values = en.getValue();
                if (key >= 9 && key <= 18) {
                    dailyCases += values.size();
                }
                int total = 0;
                for (Map<String, Object> i : values) {
                    Boolean status = (Boolean) i.get("status");
                    if (status) {
                        total += 1;
                    }
                }
                int queryMailNum = values.size();
                // 创建一个数值格式化对象
                NumberFormat numberFormat = NumberFormat.getInstance();
                // 设置精确到小数点后2位
                numberFormat.setMaximumFractionDigits(2);
                String result = numberFormat.format((float) total / (float) queryMailNum * 100);

                /**
                 * 4.把最后的结果封装为DB数据库的实体对象格式返回
                 */
                /**
                 * 4.把最后的结果封装为DB数据库的实体对象格式返回
                 */
                CopyField.setModelField(model, new BigDecimal(total), key, "hour","Ok");
                CopyField.setModelField(model, new BigDecimal(values.size()), key, "hour","All");
            }

            model.setType(type);
            model.setCreateTime(new Date());
            int allDailyCases = CopyField.fieldSum(model, "hour", "All").intValue();
            model.setAllDailyCases(allDailyCases);//24小时总数
            model.setWorkDailyCases(dailyCases);//工作时间总数

        return model;

    }



    @Override
    @DS("master")
    public int insertSelective(DailyComplianceStatistics record) {
        return  baseMapper.insertSelective(record);
    }


    @Override
    @DS("master")
    public  List<DailyComplianceStatistics> list(QueryWrapper<DailyComplianceStatistics> queryWrapper) {
        return  super.list(queryWrapper);
    }

    @Override
    @DS("master")
    public boolean remove(QueryWrapper<DailyComplianceStatistics> queryWrapper) {
        return super.remove(queryWrapper);
    }


}

