package ${package.ServiceImpl};

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.entity.Csat;
import com.test.mapper.CsatMapper;
import com.test.service.ICsatService;
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
public class CsatServiceImpl extends ServiceImpl<CsatMapper, Csat> implements ICsatService {

    //@Autowired
    //TODO service

    //sqlserver Csat统计数定时写入mysql
    @Scheduled(cron = " ${corn.time}")
    public void testScheduled() {
        Integer createHour = null;
        Date createDay = null;
        Integer ratePhone = null;
        Integer rateRequest = null;
        Integer rateIncident = null;
        Csat csat = new Csat();
        csat.setCreateHour(createHour);
        csat.setCreateDay(createDay);
        csat.setRatePhone(ratePhone);
        csat.setRateRequest(rateRequest);
        csat.setRateIncident(rateIncident);
        QueryWrapper<Csat> queryWrapper = new QueryWrapper<Csat>();
        queryWrapper.eq("create_hour", null);
        queryWrapper.eq("create_Day", null);
        queryWrapper.eq("rate_phone", null);
        queryWrapper.eq("rate_request", null);
        queryWrapper.eq("rate_incident", null);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-MM-dd");
        savemysql(csat,queryWrapper,dateFormat);
    }

    private void savemysql(Csat csat, QueryWrapper<Csat> queryWrapper,SimpleDateFormat dateFormat ) {
        try {
            List<Csat> list = this.list(queryWrapper);
            if (list.size() > 0) {
                log.info("mysql:删除当天" + dateFormat.format(new Date()) + "的Csat统计数");
                this.remove(queryWrapper);
                log.info("mysql:更新当天" + dateFormat.format(new Date()) + "的Csat统计数");
                this.save(csat);
            }else{
                log.info("mysql:首次添加当天" + dateFormat.format(new Date()) + "的Csat统计数");
                this.save(csat);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }
}
