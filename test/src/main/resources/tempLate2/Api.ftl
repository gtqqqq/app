<#if fileExist == false>
package ${apiPackName};
import java.util.Map;
import cn.com.htwins.ec.fw.common.page.PaginationMapResponse;
import cn.com.htwins.ec.fw.common.page.PaginationRequest;
import cn.com.htwins.fw.dto.ResultDto;
public interface ${tableName}Api {
</#if>
<#list methods as method>
<#if method == "List_String-List_String">
public List<String> find${tableName}byIdList(List<String> idList); 
</#if>
<#if method == "List_map-List_String">
public List<Map<String, Object>> find${tableName}byIdList(List<String> IdList); 
</#if>
<#if method == "List_Map-Map">
public List<Map<String, Object>> find${tableName}${serverName}List(Map<String, Object> paramMap);
</#if>
<#if method == "insert-Map">
public Integer insert${tableName}(Map<String, Object> paramMap); 
</#if>
<#if method == "update-Map">
public Integer update${tableName}(Map<String, Object> paramMap); 
</#if>
<#if method == "Map-Map">
public Map<String, Object> find${tableName}(Map<String, Object> paramMap);
</#if>
<#if method == "HashMap-Page-PaginationRequest">
public ResultDto<PaginationMapResponse> find${tableName}ListByPage(PaginationRequest paginationRequest); 
</#if>
<#if method == "Map-sum-PaginationRequest">
public ResultDto<Map<String, Object>> find${tableName}bySum(PaginationRequest paginationRequest); 
</#if>
</#list>
<#if fileExist == false>}</#if>