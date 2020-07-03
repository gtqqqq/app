package com.yida.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.yida.service.IIncidentService;
import com.yida.entity.Incident;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
    *  前端控制器
    * </p>
 *
 * @author gtq
 * @since 2020-07-02
 * @version v1.0
 */

@RestController
@RequestMapping("/test1/incident")
public class IncidentController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IIncidentService incidentService;

    /**
     * 查询分页数据
     */
    
    @GetMapping(value = "/list")
    public Object findListByPage(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,@RequestParam(name = "pageSize", defaultValue = "20") int pageSize){
        return null;
    }


    /**
     * 根据id查询
     */
    
    @GetMapping(value = "/getById")
    public Integer getById(@RequestParam("pkid") String pkid){
       return null;
    }

    /**
     * 新增
     */
    
    @PostMapping( "/add")
    public Integer add(@RequestBody Incident incident){
        return null;
    }

    /**
     * 删除
     */
    
    @PutMapping( "/del")
    public List<String> delete(@RequestParam("ids") List<String> ids){
        return null;
    }

    /**
     * 修改
     */
    
    @DeleteMapping("/update")
    public Integer update(@RequestBody Incident incident){
        return null;
     }

}
