package com.yida.mapper;


import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author fnchenxi
 */
public interface DailyCompliQueryMapper {

    /**
     * 查询每日达标 SLA L2
     * @return
     */
   @Select("select \n" +
           "  yy.Number,\n" +
           "\tyy.Priority,\n" +
           "\tyy.opened,\n" +
           "\tyy.opened_new,\n" +
           "\tyy.Resolved,\n" +
           "\tyy.resolved_new,\n" +
           "\t(\n" +
           "\tyy.DiffDate- ( SELECT COUNT ( * ) AS dd FROM sheet2 WHERE Hoilday >= yy.opened_new AND Hoilday <= yy.Resolved_new ) * 24 \n" +
           "\t) AS Duration \n" +
           "from (\n" +
           "\tselect \n" +
           "\txx.Number,\n" +
           "\txx.Priority,\n" +
           "\txx.opened,\n" +
           "\txx.opened_new,\n" +
           "\txx.Resolved,\n" +
           "\txx.resolved_new,\n" +
           "\tDATEDIFF(hour,xx.opened_new,xx.resolved_new) AS DiffDate \n" +
           "\tfrom (\n" +
           "\tselect \n" +
           "\t Priority,\n" +
           "\t Number,\n" +
           "\t mainData.opened,\n" +
           "\t case when mainData.calcdata is null THEN mainData.opened else mainData.calcdata end as opened_new, \n" +
           "\t mainData.Resolved,\n" +
           "\t case when endDate.calcdata is null THEN mainData.Resolved else endDate.calcdata  end as resolved_new\n" +
           "\tfrom (\n" +
           "\t\tSELECT\n" +
           "\t\tmainDate.Priority,\n" +
           "\t\tmainDate.opened,\n" +
           "\t\tmainDate.Resolved,\n" +
           "\t\tmainDate.Number,\n" +
           "\t\tstartDate.calcdata \n" +
           "\tFROM\n" +
           "\t\tL2Incindent AS mainDate\n" +
           "\t\tLEFT JOIN sheet2 AS startDate ON (\n" +
           "\t\tDATEPART( yyyy, mainDate.opened ) = DATEPART( yyyy, startDate.hoilday )) \n" +
           "\t\tAND (\n" +
           "\t\tDATEPART( m, mainDate.opened ) = DATEPART( m, startDate.hoilday )) \n" +
           "\t\tAND (\n" +
           "\t\tDATEPART( d, mainDate.opened ) = DATEPART( d, startDate.hoilday ))) AS mainData\t\n" +
           "\t\tLEFT JOIN sheet2 AS endDate ON (\n" +
           "\t\tDATEPART( yyyy, mainData.Resolved ) = DATEPART( yyyy, endDate.hoilday )) \n" +
           "\t\tAND (\n" +
           "\t\tDATEPART( m, mainData.Resolved ) = DATEPART( m, endDate.hoilday )) \n" +
           "\t\tAND (\n" +
           "\t\tDATEPART( d, mainData.Resolved ) = DATEPART( d ,endDate.hoilday ))\n" +
           "\t\t)as xx\n" +
           ")as yy \n" +
           "where DateDiff(dd,Opened,getdate())=0 ")
    List<Map<String, Object>> getDailyCompliByL2Sla();


    /**
     * 查询每日达标 SLA L3
     * @return
     */
   @Select("select \n" +
           "  yy.Number,\n" +
           "\tyy.Priority,\n" +
           "\tyy.opened,\n" +
           "\tyy.opened_new,\n" +
           "\tyy.Resolved,\n" +
           "\tyy.resolved_new,\n" +
           "\t(\n" +
           "\tyy.DiffDate- ( SELECT COUNT ( * ) AS dd FROM sheet2 WHERE Hoilday >= yy.opened_new AND Hoilday <= yy.Resolved_new ) * 24 \n" +
           "\t) AS Duration \n" +
           "from (\n" +
           "\tselect \n" +
           "\txx.Number,\n" +
           "\txx.Priority,\n" +
           "\txx.opened,\n" +
           "\txx.opened_new,\n" +
           "\txx.Resolved,\n" +
           "\txx.resolved_new,\n" +
           "\tDATEDIFF(hour,xx.opened_new,xx.resolved_new) AS DiffDate \n" +
           "\tfrom (\n" +
           "\tselect \n" +
           "\t Priority,\n" +
           "\t Number,\n" +
           "\t mainData.opened,\n" +
           "\t case when mainData.calcdata is null THEN mainData.opened else mainData.calcdata end as opened_new, \n" +
           "\t mainData.Resolved,\n" +
           "\t case when endDate.calcdata is null THEN mainData.Resolved else endDate.calcdata  end as resolved_new\n" +
           "\tfrom (\n" +
           "\t\tSELECT\n" +
           "\t\tmainDate.Priority,\n" +
           "\t\tmainDate.opened,\n" +
           "\t\tmainDate.Resolved,\n" +
           "\t\tmainDate.Number,\n" +
           "\t\tstartDate.calcdata \n" +
           "\tFROM\n" +
           "\t\tL3Incindent AS mainDate\n" +
           "\t\tLEFT JOIN sheet2 AS startDate ON (\n" +
           "\t\tDATEPART( yyyy, mainDate.opened ) = DATEPART( yyyy, startDate.hoilday )) \n" +
           "\t\tAND (\n" +
           "\t\tDATEPART( m, mainDate.opened ) = DATEPART( m, startDate.hoilday )) \n" +
           "\t\tAND (\n" +
           "\t\tDATEPART( d, mainDate.opened ) = DATEPART( d, startDate.hoilday ))) AS mainData\t\n" +
           "\t\tLEFT JOIN sheet2 AS endDate ON (\n" +
           "\t\tDATEPART( yyyy, mainData.Resolved ) = DATEPART( yyyy, endDate.hoilday )) \n" +
           "\t\tAND (\n" +
           "\t\tDATEPART( m, mainData.Resolved ) = DATEPART( m, endDate.hoilday )) \n" +
           "\t\tAND (\n" +
           "\t\tDATEPART( d, mainData.Resolved ) = DATEPART( d ,endDate.hoilday ))\n" +
           "\t\t)as xx\n" +
           ")as yy \n" +
           "where DateDiff(dd,Opened,getdate())=0 ")
    List<Map<String, Object>> getDailyCompliByL3Sla();


}