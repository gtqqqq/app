<#if fileExist == false>
package ${modelPackName};

import ${daoPackName}.*;
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
public class ${tableName}Model {

	protected final Logger logger = Logger.getLogger(${tableName}Model.class);

	@Resource
	private ${tableName}Dao ${tableVar}Dao;</#if>
<#list methods as method>
<#if method == "List_String-List_String">
    public List<String> find${tableName}byIdList(List<String> IdList){
		return  ${tableVar}Dao.find${tableName}byIdList(IdList);
	}
</#if>	
<#if method == "List_Map-Map">
	public List<Map<String, Object>> find${tableName}${serverName}List(Map<String, Object> paramMap) {
		return ${tableVar}Dao.find${tableName}${serverName}List(paramMap);
	}
</#if>

<#if method == "insert-Map">	
	public Integer insert${tableName}(Map<String, Object> paramMap){
	    return ${tableVar}Dao.insert${tableName}(paramMap);
	}
</#if>
<#if method == "update-Map">	
	public Integer update${tableName}(Map<String, Object> paramMap){
	    return ${tableVar}Dao.update${tableName}(paramMap);
	}
</#if>
<#if method == "Map-Map">	
	public Map<String, Object> find${tableName}(Map<String, Object> paramMap){
	    return ${tableVar}Dao.find${tableName}(paramMap);
	}
</#if>
<#if method == "HashMap-Page-PaginationRequest">
	public PaginationMapResponse find${tableName}ListByPage(PaginationRequest paginationRequest)
			throws BusinessException {
	    PaginationMapResponse paginationMapResponse = PaginationUtils.createPaginationMapResponse(paginationRequest);
	    Integer pageCount =  ${tableVar}Dao.find${tableName}ListByCount(paginationRequest);
	    List<HashMap<String, Object>> datalist = ${tableVar}Dao.find${tableName}ListByPage(paginationRequest);
	    if (datalist != null && datalist.size() > 0 && paginationRequest.adjust(pageCount, paginationMapResponse)) {
		paginationMapResponse.setData(datalist);
		}
		return paginationMapResponse;
	}
</#if>
<#if method == "Map-sum-PaginationRequest">
    public Map<String, Object> find${tableName}bySum(PaginationRequest paginationRequest){
		Map<String, Object> datamaplist = ${tableVar}Dao.find${tableName}bySum(paginationRequest);
		return datamaplist;
    }
</#if>	
</#list>
<#if fileExist == false>}</#if>