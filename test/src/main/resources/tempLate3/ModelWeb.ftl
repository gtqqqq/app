<#if fileExist == false>
package ${modelPackName};

import ${modelPackName}.*;
import cn.com.fan1080.shop.${location}.api.${tableName}Api;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.com.htwins.ec.fw.common.exception.BusinessException;
import cn.com.htwins.ec.fw.common.page.PaginationMapResponse;
import cn.com.htwins.ec.fw.common.page.PaginationRequest;
import cn.com.htwins.ec.fw.common.page.util.PaginationUtils;
import cn.com.htwins.fw.dto.ResultDto;
import cn.com.htwins.fw.log4j2.Logger;

@Service
public class ${webTableName}Model {

	protected final Logger logger = Logger.getLogger(${webTableName}Model.class);

	@Resource
	private ${tableName}Api ${tableVar}Api;</#if>
<#list methods as method>
<#if method == "List_String-List_String">
    public List<String> find${tableName}byIdList(List<String> IdList){
		return  ${tableVar}Api.find${tableName}byIdList(IdList);
	}
</#if>	
<#if method == "List_Map-Map">
	public List<Map<String, Object>> find${tableName}List(Map<String, Object> paramMap) {
		return ${tableVar}Api.find${tableName}List(paramMap);
	}
</#if>

<#if method == "insert-Map">	
	public ResultDto<Integer> insert${tableName}(Map<String, Object> paramMap) {
	    ResultDto<Integer> resultDto = new ResultDto<Integer>();
	    try {
		Integer result=   ${tableVar}Api.insert${tableName}(paramMap);
		if(result > 0){
        	resultDto.setResult(result);
        	resultDto.setSuccess(true);
        	resultDto.setMessage("成功");
		}else{
		    resultDto.setResult(null);
			resultDto.setSuccess(false);
			resultDto.setMessage("失败");
		}
	    } catch (Exception e) {
		resultDto.setResult(null);
		resultDto.setSuccess(false);
		resultDto.setMessage("失败");
	    }
		return resultDto;
	}
</#if>
<#if method == "update-Map">	
	public ResultDto<Integer> update${tableName}(Map<String, Object> paramMap) {
	    ResultDto<Integer> resultDto = new ResultDto<Integer>();
	    try {
		Integer result=   ${tableVar}Api.update${tableName}(paramMap);
		if(result > 0){
        	resultDto.setResult(result);
        	resultDto.setSuccess(true);
        	resultDto.setMessage("成功");
		}else{
		    resultDto.setResult(null);
			resultDto.setSuccess(false);
			resultDto.setMessage("失败");
		}
	    } catch (Exception e) {
		resultDto.setResult(null);
		resultDto.setSuccess(false);
		resultDto.setMessage("失败");
	    }
		return resultDto;
	}
</#if>
<#if method == "Map-Map">	
	public Map<String, Object> find${tableName}(Map<String, Object> paramMap){
	    return ${tableVar}Api.find${tableName}(paramMap);
	}
</#if>
<#if method == "HashMap-Page-PaginationRequest">
	public PaginationMapResponse find${tableName}ListByPage(PaginationRequest paginationRequest)
			throws BusinessException {
	  	PaginationMapResponse paginationMapResponse = ${tableVar}Api.find${tableName}ListByPage(paginationRequest).getResult();
	    List<HashMap<String, Object>> list = paginationMapResponse.getData();
		for (HashMap<String, Object> map : list) {
		   <#list columns as column>
			map.put("${column}", map.get("${column}") != null ? map.get("${column}").toString() : null);
           </#list>
	    }
		return paginationMapResponse;
	}
</#if>
<#if method == "Map-sum-PaginationRequest">
    public Map<String, Object> find${tableName}bySum(PaginationRequest paginationRequest){
		Map<String, Object> datamaplist = ${tableVar}Api.find${tableName}bySum(paginationRequest);
		return datamaplist;
    }
</#if>	
</#list>
<#if fileExist == false>}</#if>