package com.yida.service;

import com.yida.entity.Request;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gtq
 * @since 2020-07-02
 */
public interface IRequestService extends IService<Request> {
   Integer selectL1Request();


   Integer selectL2Request(String state);


   Integer selectL3Request(String state);
}
