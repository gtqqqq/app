<#if fileExist == false>
package ${controllerPackName};
import ${poPackName}.${tableName}Form;
import ${modelPackName}.${webTableName}Model;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.com.htwins.ec.fw.common.exception.BusinessException;
import cn.com.htwins.ec.fw.common.page.PaginationMapResponse;
import cn.com.htwins.ec.fw.common.page.PaginationRequest;
import cn.com.htwins.ec.fw.common.page.util.PaginationUtils;
import ${controllerPackName?substring(0,controllerPackName?last_index_of('.'))}.base.WebBaseController;


@Controller("${webTableName}")
@RequestMapping(value = "/${location}")
public class ${webTableName}Controller extends BaseController {
${webTableName}
@Resource
private ${webTableName}Model ${webTableName}Model;</#if>
<#list methods as method>
    <#if method == "HashMap-Page-PaginationRequest">
    @RequestMapping(value = "/to${tableName}List.jhtml", method = { RequestMethod.GET, RequestMethod.POST })
    public String to${tableName}List(HttpServletRequest request, Map
    <String, Object> modelMap) {
    return "${location}/${tableVar}_list";
    }
    @ResponseBody
    @RequestMapping(value = "/find${tableName}ListByPage.ajax", method = { RequestMethod.GET, RequestMethod.POST })
    public PaginationMapResponse find${tableName}ListByPage(HttpServletRequest httpServletRequest,
    ${tableName}Form ${tableVar}Form) throws BusinessException {

    PaginationRequest paginationRequest = PaginationUtils.createPaginationRequest(${tableVar}Form);
    PaginationMapResponse paginationMapResponse = ${webTableName}Model
    .find${tableName}ListByPage(paginationRequest);
    return paginationMapResponse;
    }
    </#if>
    <#if method == "Map-sum-PaginationRequest">
    @ResponseBody
    @RequestMapping(value = "/find${tableName}bySum.ajax", method = { RequestMethod.GET, RequestMethod.POST })
    public Map
    <String, Object> find${tableName}bySum(HttpServletRequest httpServletRequest,
    ${tableName}Form ${tableVar}Form) throws BusinessException {
    PaginationRequest paginationRequest = PaginationUtils.createPaginationRequest(${tableVar}Form);
    return ${webTableName}Model.find${tableName}bySum(paginationRequest);
    }
    </#if>
    <#if method == "insert-Map">
    @ResponseBody
    @RequestMapping(value = "/insert${tableName}.ajax", method = { RequestMethod.GET, RequestMethod.POST })
    public ResultDto
    <Integer> insert${tableName}(HttpServletRequest httpServletRequest,
    ${tableName}Form ${tableVar}Form) throws BusinessException {
        return ${webTableName}Model.insert${tableName}(paramMap);
        }
    </#if>
    <#if method == "update-Map">
        @ResponseBody
        @RequestMapping(value = "/update${tableName}.ajax", method = { RequestMethod.GET, RequestMethod.POST })
        public ResultDto
    <Integer> update${tableName}(HttpServletRequest httpServletRequest,
    ${tableName}Form ${tableVar}Form) throws BusinessException {
        return ${webTableName}Model.update${tableName}(paramMap);
        }
    </#if>
</#list>
<#if fileExist == false>}</#if>