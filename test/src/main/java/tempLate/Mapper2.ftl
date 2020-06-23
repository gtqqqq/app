<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${daoPackageName}.${tableName}Mapper">
    <resultMap id="BaseResultMap" type="${poPackageName}.${tableName}Bean">
        <id column="${columns[0].dbcolumnName}" jdbcType="${columns[0].dbcolumnType}" property="${columns[0].columnName}"/>
    <#list columns as column>
        <result column="${column.dbcolumnName}" jdbcType="${column.dbcolumnType}" property="${column.columnName}"/>
    </#list>
    </resultMap>
    <sql id="Base_Column_List">
    <#list columns as column>${column.dbcolumnName},</#list>
    </sql>
    <select id="selectByPrimaryKey"
            parameterType="java.lang.String"
            resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ${dbtableName!""}
        <where>
        <#list columns as column>
            <if test="${column.columnName} != null">AND ${column.dbcolumnName} = ${'#'?html}{${column.columnName}}</if>
        </#list>
        </where>
    </select>
    <delete id="deleteByPrimaryKey" parameterType="java.lang.String">
        delete from ${dbtableName}
        where ${columns[0].dbcolumnName} = ${'#'?html}{${columns[0].columnName},jdbcType=${columns[0].dbcolumnType}}
    </delete>

    <insert id="insertSelective" parameterType="${poPackageName}.${tableName}Cond">
        insert into ${dbtableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list columns as column>
            <if test="${column.columnName} != null">
            ${column.dbcolumnName},
            </if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#list columns as column>
            <if test="${column.columnName} != null">
            ${'#'?html}{${column.columnName},jdbcType=${column.dbcolumnType}},
            </if>
        </#list>
        </trim>
    </insert>


    <update id="updateByPrimaryKeySelective"
            parameterType="${poPackageName}.${tableName}Cond">

        update ${dbtableName}
        <set>
        <#list columns as column>
            <if test="${column.columnName} != null">
            ${column.dbcolumnType} = ${'#'?html}{${column.columnName},jdbcType=${column.dbcolumnType}},
            </if>
        </#list>
        </set>
        <where>
        <#list columns as column>
            <if test="${column.columnName} != null">AND ${column.dbcolumnName} = ${'#'?html}{${column.columnName}}</if>
        </#list>
        </where>
    </update>


    <select id="selectBy${tableName}List" parameterType="${poPackageName}.${tableName}Cond"
            resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${dbtableName}
        <where>
        <#list columns as column>
            <if test="${column.columnName} != null">AND ${column.dbcolumnName} = ${'#'?html}{${column.columnName}}</if>
        </#list>
        </where>
        LIMIT 2
    </select>
</mapper>