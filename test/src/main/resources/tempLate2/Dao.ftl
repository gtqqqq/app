<#if fileExist == false>
package ${daoPackName};

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import cn.com.htwins.ec.fw.common.page.PaginationMapResponse;
import cn.com.htwins.ec.fw.common.page.PaginationRequest;

public interface ${tableName}Dao {
</#if>
<#list methods as method>
<#if method == "List_String-List_String">
	public List<String> find${tableName}byIdList(@Param("idList")List<String> idList);
</#if>
<#if method == "List_Map-Map">
	public List<Map<String, Object>> find${tableName}${serverName}List(@Param("paramMap")Map<String, Object> paramMap);
</#if>
<#if method == "insert-Map">
	public Integer insert${tableName}(@Param("paramMap")Map<String, Object> paramMap);
</#if>

<#if method == "update-Map">
	public Integer update${tableName}(@Param("paramMap")Map<String, Object> paramMap);
</#if>
<#if method == "Map-Map">
	public Map<String, Object> find${tableName}(@Param("paramMap")Map<String, Object> paramMap);
</#if>
<#if method == "HashMap-Page-PaginationRequest">
	public List<HashMap<String, Object>> find${tableName}ListByPage(@Param("paginationRequest")PaginationRequest paginationRequest); 

	public Integer find${tableName}ListByCount(@Param("paginationRequest") PaginationRequest paginationRequest);
</#if>
<#if method == "Map-sum-PaginationRequest">
public Map<String, Object> find${tableName}bySum(@Param("paginationRequest")PaginationRequest paginationRequest);
</#if>
</#list>
<#if fileExist == false>}</#if>