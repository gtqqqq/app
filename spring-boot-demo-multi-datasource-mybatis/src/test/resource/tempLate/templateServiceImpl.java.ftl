package tempLate;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yida.entity.${entity};
import com.yida.mapper.${entity}Mapper;
import com.yida.service.IL2IncindentService;
import com.yida.service.I${entity}Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author gtq
 * @since 2020-07-06
 */
@Slf4j
@DS("master")
@Service
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    //@Autowired
    //TODO service

    //sqlserver ${entity}统计数定时写入mysql
    @Scheduled(cron = "${corn.time}")
    public void testScheduled() {

        ${entity} ${(entity?substring(1))?uncap_first} = new ${entity}();
        ${(entity?substring(1))?uncap_first}.setTotalTickets(totalTickets);
        ${(entity?substring(1))?uncap_first}.setWorkingTickets(workingTickets);
        ${(entity?substring(1))?uncap_first}.setWaitingTickets(waitingTickets);
        ${(entity?substring(1))?uncap_first}.setPendingTickets(pendingTickets);
        ${(entity?substring(1))?uncap_first}.setClosedTickets(closedTickets);
        ${(entity?substring(1))?uncap_first}.setOptDate(new Date());
        savemysql(${(entity?substring(1))?uncap_first});
    }

    private void savemysql(${entity} ${(entity?substring(1))?uncap_first}) {
        QueryWrapper<${entity}> queryWrapper = new QueryWrapper<${entity}>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-MM-dd");
        queryWrapper.eq("opt_date", dateFormat.format(new Date()));
        try {
            List<${entity}> list = this.list(queryWrapper);
            if (list.size() > 0) {
                log.info("mysql:删除当天" + dateFormat.format(new Date()) + "的${entity}统计数");
                this.remove(queryWrapper);
                log.info("mysql:更新当天" + dateFormat.format(new Date()) + "的${entity}统计数");
                this.save(${(entity?substring(1))?uncap_first});
            }else{
                log.info("mysql:首次添加当天" + dateFormat.format(new Date()) + "的${entity}统计数");
                this.save(${(entity?substring(1))?uncap_first});
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }
}
