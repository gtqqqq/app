package com.yida.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.yida.service.IReqL1l2l3NumService;
import com.yida.entity.ReqL1l2l3Num;

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
 * @since 2020-07-03
 * @version v1.0
 */

@RestController
@RequestMapping("/yida/req-l1-l2-l3-num")
public class ReqL1l2l3NumController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IReqL1l2l3NumService reqL1l2l3NumService;

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
    public List<ReqL1l2l3Num> getById(@RequestParam("pkid") String pkid){
       return null;
    }

    /**
     * 新增
     */
    
    @PostMapping( "/add")
    public List<ReqL1l2l3Num> add(@RequestBody ReqL1l2l3Num reqL1l2l3Num){
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
    public List<ReqL1l2l3Num> update(@RequestBody ReqL1l2l3Num reqL1l2l3Num){
        return null;
     }

}
