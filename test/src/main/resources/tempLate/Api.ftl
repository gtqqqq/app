package ${apiPackName};
import java.util.Map;
import cn.com.htwins.ec.fw.common.page.PaginationMapResponse;
import cn.com.htwins.ec.fw.common.page.PaginationRequest;
import cn.com.htwins.fw.dto.ResultDto;
public interface ${tableName}Api {

 <#if 1=1>
public ResultDto<PaginationMapResponse> find${tableName}ListByPage(PaginationRequest paginationRequest); 
 </#if>

<#if 1=1>
public ResultDto<Map<String, Object>> find${tableName}bySum(PaginationRequest paginationRequest); 
</#if>
}