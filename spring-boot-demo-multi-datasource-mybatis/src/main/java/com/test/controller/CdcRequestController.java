package com.test.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.yida.service.ICdcRequestService;
import com.yida.entity.CdcRequest;

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
 * @since 2020-07-06
 * @version v1.0
 */

@RestController
@RequestMapping("/test/cdc-request")
public class CdcRequestController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ICdcRequestService cdcRequestService;

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
    public Object getById(@RequestParam("pkid") String pkid){
       return null;
    }

    /**
     * 新增
     */
    
    @PostMapping( "/add")
    public Object add(@RequestBody CdcRequest cdcRequest){
        return null;
    }

    /**
     * 删除
     */
    
    @PutMapping( "/del")
    public Object delete(@RequestParam("ids") List<String> ids){
        return null;
    }

    /**
     * 修改
     */
    
    @DeleteMapping("/update")
    public Object update(@RequestBody CdcRequest cdcRequest){
        return null;
     }

}
