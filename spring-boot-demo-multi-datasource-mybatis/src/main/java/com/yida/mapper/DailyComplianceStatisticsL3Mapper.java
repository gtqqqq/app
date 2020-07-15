package com.yida.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yida.entity.DailyComplianceStatisticsL3;
import com.yida.entity.DailyComplianceStatisticsL3Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DailyComplianceStatisticsL3Mapper extends BaseMapper<DailyComplianceStatisticsL3> {
    long countByExample(DailyComplianceStatisticsL3Example example);

    int deleteByExample(DailyComplianceStatisticsL3Example example);

    int insertSelective(DailyComplianceStatisticsL3 record);

    List<DailyComplianceStatisticsL3> selectByExample(DailyComplianceStatisticsL3Example example);

    int updateByExampleSelective(@Param("record") DailyComplianceStatisticsL3 record, @Param("example") DailyComplianceStatisticsL3Example example);

    int updateByExample(@Param("record") DailyComplianceStatisticsL3 record, @Param("example") DailyComplianceStatisticsL3Example example);

}