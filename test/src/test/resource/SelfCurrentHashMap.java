package resource;

import java.util.concurrent.ConcurrentHashMap;

/**
 * *************************************************************
 *
 * @项目名 : test
 * @文件名 : selfCurrentHashMap
 * @建立日期    : 2018/11/30
 * @author    : guantianqi
 * @模块        :
 * @描述        :
 * @备注        : *************************************************************
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class SelfCurrentHashMap extends ConcurrentHashMap{
    @SuppressWarnings("unchecked")
	@Override
    public Object put(Object key, Object value) {
        return super.put(key,value==null?"":value);
    }
}
