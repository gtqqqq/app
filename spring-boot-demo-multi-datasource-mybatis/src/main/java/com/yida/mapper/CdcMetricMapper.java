package com.yida.mapper;

import com.yida.entity.CdcMetric;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gtq
 * @since 2020-07-08
 */
public interface CdcMetricMapper extends BaseMapper<CdcMetric> {

    @Select("select  " +
            "  yy.Number, " +
            " yy.Priority, " +
            " yy.opened, " +
            " yy.opened_new, " +
            " yy.Resolved, " +
            " yy.resolved_new, " +
            " ( " +
            " yy.DiffDate- (SELECT COUNT ( * ) AS dd FROM sheet2 WHERE Hoilday >= yy.opened_new AND Hoilday <= yy.Resolved_new )*24)AS Duration  " +
            "from ( " +
            " select  " +
            " xx.Number, " +
            " xx.Priority, " +
            " xx.opened, " +
            " xx.opened_new, " +
            " xx.Resolved, " +
            " xx.resolved_new, " +
            " DATEDIFF(hour,xx.opened_new,xx.resolved_new) AS DiffDate  " +
            " from ( " +
            " select  " +
            "  Priority, " +
            "  Number, " +
            "  mainData.opened, " +
            "  case when mainData.calcdata is null THEN mainData.opened else mainData.calcdata end as opened_new,  " +
            "  mainData.Resolved, " +
            "  case when endDate.calcdata is null THEN mainData. Resolved else endDate.calcdata  end as resolved_new " +
            " from ( " +
            "  SELECT " +
            "  mainData0.Priority, " +
            "  mainData0.[Start] as opened, " +
            "  mainData0.[End] as Resolved, " +
            "  mainData0.Number, " +
            "  startDate.calcdata  " +
            " FROM " +
            "  CDC_Metric AS mainData0 " +
            "  LEFT JOIN sheet2 AS startDate ON ( " +
            "  DATEPART( yyyy, mainData0.[Start] ) = DATEPART( yyyy, startDate.hoilday ))  " +
            "  AND ( " +
            "  DATEPART( m, mainData0.[Start] ) = DATEPART( m, startDate.hoilday ))  " +
            "  AND ( " +
            "  DATEPART( d, mainData0.[Start] ) = DATEPART( d, startDate.hoilday )) " +
            " where mainData0.[Assignment group] like '%L3%' " +
            " ) AS mainData  " +
            "  LEFT JOIN sheet2 AS endDate ON ( " +
            "  DATEPART( yyyy, mainData.Resolved) = DATEPART( yyyy, endDate.hoilday ))  " +
            "  AND ( " +
            "  DATEPART( m, mainData.Resolved) = DATEPART( m, endDate.hoilday ))  " +
            "  AND ( " +
            "  DATEPART( d, mainData.Resolved) = DATEPART( d ,endDate.hoilday )) " +
            "  )as xx " +
            ")as yy " +
            "where DATEPART(hh,yy.opened)>8 or (DATEPART(hh,yy.opened)=8 and DATEPART(mi,yy.opened)>30) and " +
            "      DATEPART(hh,yy.Resolved)>8 or (DATEPART(hh,yy.Resolved)=8 and DATEPART(mi,yy.Resolved)>30) and " +
            "      DATEPART(hh,yy.opened)<17 or (DATEPART(hh,yy.opened)=17 and DATEPART(mi,yy.opened)<30) and " +
            "      DATEPART(hh,yy.Resolved)<17 or (DATEPART(hh,yy.Resolved)=17 and DATEPART(mi,yy.Resolved)<30) and " +
            "      DateDiff(dd,yy.Resolved,getdate())=0")
    public List<Map<String,Object>> selectL3OLAList();

    @Select("select  " +
            "  yy.Number, " +
            " yy.Priority, " +
            " yy.opened, " +
            " yy.opened_new, " +
            " yy.Resolved, " +
            " yy.resolved_new, " +
            " ( " +
            " yy.DiffDate- (SELECT COUNT ( * ) AS dd FROM sheet2 WHERE Hoilday >= yy.opened_new AND Hoilday <= yy.Resolved_new )*24)AS Duration  " +
            "from ( " +
            " select  " +
            " xx.Number, " +
            " xx.Priority, " +
            " xx.opened, " +
            " xx.opened_new, " +
            " xx.Resolved, " +
            " xx.resolved_new, " +
            " DATEDIFF(hour,xx.opened_new,xx.resolved_new) AS DiffDate  " +
            " from ( " +
            " select  " +
            "  Priority, " +
            "  Number, " +
            "  mainData.opened, " +
            "  case when mainData.calcdata is null THEN mainData.opened else mainData.calcdata end as opened_new,  " +
            "  mainData.Resolved, " +
            "  case when endDate.calcdata is null THEN mainData. Resolved else endDate.calcdata  end as resolved_new " +
            " from ( " +
            "  SELECT " +
            "  mainData0.Priority, " +
            "  mainData0.[Start] as opened, " +
            "  mainData0.[End] as Resolved, " +
            "  mainData0.Number, " +
            "  startDate.calcdata  " +
            " FROM " +
            "  CDC_Metric AS mainData0 " +
            "  LEFT JOIN sheet2 AS startDate ON ( " +
            "  DATEPART( yyyy, mainData0.[Start] ) = DATEPART( yyyy, startDate.hoilday ))  " +
            "  AND ( " +
            "  DATEPART( m, mainData0.[Start] ) = DATEPART( m, startDate.hoilday ))  " +
            "  AND ( " +
            "  DATEPART( d, mainData0.[Start] ) = DATEPART( d, startDate.hoilday )) " +
            " where mainData0.[Assignment group] not like '%L3%' " +
            " " +
            " ) AS mainData  " +
            "  LEFT JOIN sheet2 AS endDate ON ( " +
            "  DATEPART( yyyy, mainData.Resolved) = DATEPART( yyyy, endDate.hoilday ))  " +
            "  AND ( " +
            "  DATEPART( m, mainData.Resolved) = DATEPART( m, endDate.hoilday ))  " +
            "  AND ( " +
            "  DATEPART( d, mainData.Resolved) = DATEPART( d ,endDate.hoilday )) " +
            "  )as xx " +
            ")as yy " +
            "where DATEPART(hh,yy.opened)>8 or (DATEPART(hh,yy.opened)=8 and DATEPART(mi,yy.opened)>30) and " +
            "      DATEPART(hh,yy.Resolved)>8 or (DATEPART(hh,yy.Resolved)=8 and DATEPART(mi,yy.Resolved)>30) and " +
            "      DATEPART(hh,yy.opened)<17 or (DATEPART(hh,yy.opened)=17 and DATEPART(mi,yy.opened)<30) and " +
            "      DATEPART(hh,yy.Resolved)<17 or (DATEPART(hh,yy.Resolved)=17 and DATEPART(mi,yy.Resolved)<30) and " +
            "      DateDiff(dd,yy.Resolved,getdate())=0")
    public List<Map<String,Object>> selectL2OLAList();

}
