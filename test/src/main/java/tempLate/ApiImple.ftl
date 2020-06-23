<#if fileExist == false>
package ${apiImplPackName};

import ${modelPackName}.*;
import ${apiPackName}.*;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import cn.com.fan1080.shop.common.constant.ErrorCodeConstant;
import cn.com.htwins.ec.fw.common.page.PaginationMapResponse;
import cn.com.htwins.ec.fw.common.page.PaginationRequest;
import cn.com.htwins.ec.fw.rpc.api.BaseApi;
import cn.com.htwins.fw.dto.PageResultDto;
import cn.com.htwins.fw.dto.ResultDto;
import cn.com.htwins.fw.log4j2.Logger;
import org.springframework.stereotype.Service;

@Service
public class ${tableName}ApiImpl extends BaseApi implements ${tableName}Api {

protected final Logger logger = Logger.getLogger(${tableName}ApiImpl.class);

@Resource
private ${tableName}Model ${tableVar}Model;
</#if>
<#list methods as method>

    <#if method == "List_String-List_String">
    @Override
    public List
    <String> find${tableName}byIdList(List
    <String> idList){
        return  ${tableVar}Model.find${tableName}byIdList(idList);
        }
    </#if>
    <#if method == "List_Map-Map">
        @Override
        public List
        <Map
        <String, Object>> find${tableName}${serverName}List(Map
        <String, Object> paramMap) {
        return ${tableVar}Model.find${tableName}${serverName}List(paramMap);
        }
    </#if>
    <#if method == "insert-Map">
        @Override
        public Integer insert${tableName}(Map
        <String, Object> paramMap) {
        return ${tableVar}Model.insert${tableName}(paramMap);
        }
    </#if>
    <#if method == "update-Map">
        @Override
        public Integer update${tableName}(Map
        <String, Object> paramMap) {
        return ${tableVar}Model.update${tableName}(paramMap);
        }
    </#if>
    <#if method == "Map-Map">
        @Override
        public Map
        <String, Object> find${tableName}(Map
        <String, Object> paramMap){
        return ${tableVar}Model.find${tableName}(paramMap);
        }
    </#if>
    <#if method == "HashMap-Page-PaginationRequest">
        @Override
        public ResultDto
    <PaginationMapResponse> find${tableName}ListByPage(PaginationRequest paginationRequest) {
        ResultDto
    <PaginationMapResponse> serviceResultDto = new PageResultDto
    <PaginationMapResponse>();
        try {
        PaginationMapResponse paginationMapResponse = ${tableVar}Model
        .find${tableName}ListByPage(paginationRequest);
        serviceResultDto.setSuccess(true);
        serviceResultDto.setResult(paginationMapResponse);
        } catch (Exception e) {
        serviceResultDto.setSuccess(false);
        serviceResultDto.setError(ErrorCodeConstant.ERROR_CODE_S046024,
        "系统异常[" + ErrorCodeConstant.ERROR_CODE_S046024 + "]");
        logger.exception("分页获取${tableName}", "分页获取${tableName} errorCode:{0}, paginationRequest:{1}", e,
        ErrorCodeConstant.ERROR_CODE_S046024, paginationRequest);
        }
        return serviceResultDto;
        }
    </#if>
    <#if method == "Map-sum-PaginationRequest">
        @Override
        public ResultDto
        <Map
        <String, Object>> find${tableName}bySum(PaginationRequest paginationRequest) {
        ResultDto
        <Map
        <String, Object>> serviceResultDto = new ResultDto
        <Map
        <String, Object>>();
        try {
        Map
        <String, Object> resultMap = ${tableVar}Model.find${tableName}bySum(paginationRequest);
        serviceResultDto.setSuccess(true);
        serviceResultDto.setResult(resultMap);
        } catch (Exception e) {
        serviceResultDto.setSuccess(false);
        serviceResultDto.setMessage("系统异常[" + ErrorCodeConstant.ERROR_CODE_S046026 + "]");
        logger.exception("获取${tableName}统计信息", "获取${tableName}统计信息异常 errorCode:{0}, paginationRequest:{1}", e,
        ErrorCodeConstant.ERROR_CODE_S046026, paginationRequest);
        }
        return serviceResultDto;
        }
    </#if>
</#list>
<#if fileExist == false>}</#if>