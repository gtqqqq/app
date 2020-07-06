package com.test.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.test.service.ICsatService;
import com.test.entity.Csat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
@RequestMapping("/test/csat")
public class CsatController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ICsatService csatService;

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
    public Object add(@RequestBody Csat csat){
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
    public Object update(@RequestBody Csat csat){
        return null;
     }

}
