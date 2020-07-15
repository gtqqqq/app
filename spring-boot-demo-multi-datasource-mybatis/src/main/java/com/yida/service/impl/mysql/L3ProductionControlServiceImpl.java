package com.yida.service.impl.mysql;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yida.entity.L3ProductionControl;
import com.yida.mapper.L3ProductionControlMapper;
import com.yida.service.IL3IncindentService;
import com.yida.service.IL3ProductionControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
public class L3ProductionControlServiceImpl extends ServiceImpl<L3ProductionControlMapper, L3ProductionControl> implements IL3ProductionControlService {

    @Autowired
    IL3IncindentService il3IncindentService;

    public void  taskL3Production() {
        Date createTime = new Date();
        List<Map<String, Object>> list = il3IncindentService.selectTeamProductList();
        for (Map<String, Object> map:
                list) {
            Integer totalNum = (Integer) map.get("total_num");
            Integer Responded = (Integer) map.get("respond_num");;
            Integer closeNum = (Integer) map.get("close_num");;
            String assignmentGroup = (String) map.get("assignmentGroup");
            L3ProductionControl l3ProductionControl = new L3ProductionControl();
            l3ProductionControl.setCreateTime(createTime);
            l3ProductionControl.setTotalNum(totalNum);
            l3ProductionControl.setResponded(Responded);
            l3ProductionControl.setCloseNum(closeNum);
            l3ProductionControl.setAssignmentGroup(assignmentGroup);
            QueryWrapper<L3ProductionControl> queryWrapper = new QueryWrapper<L3ProductionControl>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-MM-dd");
            queryWrapper.apply(" DATE_FORMAT(create_time,'%Y-%m-%d')= DATE_FORMAT({0},'%Y-%m-%d')", new Date());
            savemysql(l3ProductionControl,queryWrapper,dateFormat);
        }
    }


    //sqlserver L3ProductionControl统计数定时写入mysql

    private void savemysql(L3ProductionControl l3ProductionControl, QueryWrapper<L3ProductionControl> queryWrapper,SimpleDateFormat dateFormat ) {
        try {
            List<L3ProductionControl> list = this.list(queryWrapper);
            if (list.size() > 0) {
                log.info("mysql:删除当天" + dateFormat.format(new Date()) + "的L3ProductionControl统计数");
                this.remove(queryWrapper);
                log.info("mysql:更新当天" + dateFormat.format(new Date()) + "的L3ProductionControl统计数");
                this.save(l3ProductionControl);
            }else{
                log.info("mysql:首次添加当天" + dateFormat.format(new Date()) + "的L3ProductionControl统计数");
                this.save(l3ProductionControl);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }
}
