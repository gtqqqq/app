package ${package.ServiceImpl};

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${package.Entity}.${entity};
import ${package.Mapper}.${entity}Mapper;
import ${package.Service}.I${entity}Service;
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
 * @author guantianqi
 * @since 2020-07-06
 */
@Slf4j
@DS("master")
@Service
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    //@Autowired
    //TODO service

    //sqlserver ${entity}统计数定时写入mysql
    @Scheduled(cron = " ${'${'?html}corn.time}")
    public void testScheduled() {
         <#list table.fields as field>
        ${field.propertyType} ${field.propertyName} = null;
         </#list>
        ${entity} ${entity?uncap_first} = new ${entity}();
         <#list table.fields as field>
        ${entity?uncap_first}.set${field.capitalName}(${field.propertyName});
         </#list>
        QueryWrapper<${entity}> queryWrapper = new QueryWrapper<${entity}>();
          <#list table.fields as field>
        queryWrapper.eq("${field.name}", null);
         </#list>
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-MM-dd");
        savemysql(${entity?uncap_first},queryWrapper,dateFormat);
    }

    private void savemysql(${entity} ${entity?uncap_first}, QueryWrapper<${entity}> queryWrapper,SimpleDateFormat dateFormat ) {
        try {
            List<${entity}> list = this.list(queryWrapper);
            if (list.size() > 0) {
                log.info("mysql:删除当天" + dateFormat.format(new Date()) + "的${entity}统计数");
                this.remove(queryWrapper);
                log.info("mysql:更新当天" + dateFormat.format(new Date()) + "的${entity}统计数");
                this.save(${entity?uncap_first});
            }else{
                log.info("mysql:首次添加当天" + dateFormat.format(new Date()) + "的${entity}统计数");
                this.save(${entity?uncap_first});
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }
}
