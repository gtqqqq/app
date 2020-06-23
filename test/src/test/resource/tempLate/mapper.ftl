<#if fileExist == false>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${daoPackName}.${tableName}Dao">
</#if>
<#list methods as method>
<#if method == "HashMap-Page-PaginationRequest">
	<select id="find${tableName}ListByPage" resultType="java.util.HashMap">
	select 
		
     
     <include refid="where_${tableName}"></include>
	  order by a.create_date desc
          <![CDATA[
	      	limit  ${'#'?html}{paginationRequest.start}, ${'#'?html}{paginationRequest.length}
	      ]]>
	</select>
	
	<select id="find${tableName}ListByCount" resultType="java.lang.Integer">
	 select count(1)
         <include refid="where_${tableName}"></include>
	</select>
    <sql id="where_${tableName}">
       from T1 a
      inner T2 b on
      where a.cancel_flag = 0
        <if test="paginationRequest.params.name !=null and paginationRequest.params.name !=''">
            AND a.name REGEXP concat('([\s\S]*',${'#'?html}{paginationRequest.params.name},'[\s\S]*|','.*',${'#'?html}{paginationRequest.params.name},'.*)')   
        </if>
          <if test="paginationRequest.params.userCode !=null and paginationRequest.params.userCode !=''">
            AND a.user_code REGEXP concat('([\s\S]*',${'#'?html}{paginationRequest.params.userCode},'[\s\S]*)')   
        </if>
        <if test="paginationRequest.params.startDate !=null and paginationRequest.params.startDate !=''">
            AND a.create_date >= DATE(${'#'?html}{paginationRequest.params.startDate})
        </if>
        <if test="paginationRequest.params.endDate !=null and paginationRequest.params.endDate !=''">
            <![CDATA[ AND a.create_date < DATE_ADD(${'#'?html}{paginationRequest.params.endDate}, interval 1 day) ]]>
        </if>
         <if test="paginationRequest.params.idList != null and paginationRequest.params.idList.size() > 0">
            and a.id in 
            <foreach item="id" index="index" collection="paginationRequest.params.idList" open="(" separator="," close=")">  
               ${'#'?html}{id}
            </foreach>
        </if>
</sql>
</#if>
<#if method == "Map-sum-PaginationRequest">
<select id="find${tableName}ListByPage" resultType="java.util.Map">
 <include refid="where_sales_sms_statistics"></include>
</select>
</#if>
<#if method == "List_String-List_String">
<select id="${tableName}byIdList" resultType="java.lang.String">
         <if test="paramMap.startDate !=null and paramMap.startDate !=''">
            AND a.create_date >= DATE(${'#'?html}{paramMap.startDate})
        </if>
        <if test="paramMap.endDate !=null and paramMap.endDate !=''">
            <![CDATA[ AND a.create_date < DATE_ADD(${'#'?html}{paramMap.endDate}, interval 1 day) ]]>
        </if>
        <if test="paramMap.idList != null and paramMap.idList.size() > 0">
            and a.id in 
            <foreach item="id" index="index" collection="paramMap.idList" open="(" separator="," close=")">  
                ${'#'?html}{id}
            </foreach>
        </if>
</select>
</#if>

<#if method == "List_map-List_String">
<select id="find${tableName}byIdList" resultType="java.util.Map">
        <if test="paramMap.startDate !=null and paramMap.startDate !=''">
            AND a.create_date >= DATE(${'#'?html}{paramMap.startDate})
        </if>
        <if test="paramMap.endDate !=null and paramMap.endDate !=''">
            <![CDATA[ AND a.create_date < DATE_ADD(${'#'?html}{paramMap.endDate}, interval 1 day) ]]>
        </if>
        <if test="paramMap.idList != null and paramMap.idList.size() > 0">
            and a.id in 
            <foreach item="id" index="index" collection="paramMap.idList" open="(" separator="," close=")">  
                ${'#'?html}{id}
            </foreach>
        </if>
</select>
</#if>

<#if method == "List_Map-Map">
<select id="find${tableName}${serverName}List" resultType="java.util.Map">
        <if test="paramMap.startDate !=null and paramMap.startDate !=''">
            AND a.create_date >= DATE(${'#'?html}{paramMap.startDate})
        </if>
        <if test="paramMap.endDate !=null and paramMap.endDate !=''">
            <![CDATA[ AND a.create_date < DATE_ADD(${'#'?html}{paramMap.endDate}, interval 1 day) ]]>
        </if>
        <if test="paramMap.idList != null and paramMap.idList.size() > 0">
            and a.id in 
            <foreach item="id" index="index" collection="paramMap.idList" open="(" separator="," close=")">  
                ${'#'?html}{id}
            </foreach>
        </if>
</select>
</#if>

<#if method == "insert-Map">
<insert id="insert${tableName}" >
     <#list columns as column>
      ${column},
     </#list>
</insert>
</#if>

<#if method == "update-Map">
<update id="update${tableName}">
    update set
     <#list columns as column>
      ${column},
     </#list>
    where id=${'#'?html}{id}
</update>
</#if>

<#if method == "Map-Map">
<select id="find${tableName}" resultType="java.util.Map">
        <if test="paramMap.startDate !=null and paramMap.startDate !=''">
            AND a.create_date >= DATE(${'#'?html}{paramMap.startDate})
        </if>
        <if test="paramMap.endDate !=null and paramMap.endDate !=''">
            <![CDATA[ AND a.create_date < DATE_ADD(${'#'?html}{paramMap.endDate}, interval 1 day) ]]>
        </if>
        <if test="paramMap.idList != null and paramMap.idList.size() > 0">
            and a.id in 
            <foreach item="id" index="index" collection="paramMap.idList" open="(" separator="," close=")">  
               ${'#'?html}{id}
            </foreach>
        </if>
</select>
</#if>
</#list>
<#if fileExist == false></mapper></#if>