package ${package.Controller};

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
<#if swagger2>import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;</#if>
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
    * ${table.comment!} 前端控制器
    * </p>
 *
 * @author ${author}
 * @since ${date}
 * @version v1.0
 */
<#if restControllerStyle>
@Api(tags = {"${table.comment!}"})
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ${table.serviceName} ${(table.serviceName?substring(1))?uncap_first};

    /**
     * 查询分页数据
    <#if swagger2>
     * @ApiImplicitParams({
    <#list table.fields as field>
     *    @ApiImplicitParam(name = "${field.propertyName}", value = "${field.comment}", dataType = "${field.propertyType}"),
    </#list>
        })
    </#if>
     */
    <#if swagger2>@ApiOperation(value = "查询分页数据") </#if>
    @GetMapping(value = "/list")
    public Object findListByPage(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,@RequestParam(name = "pageSize", defaultValue = "20") int pageSize){
        return null;
    }


    /**
     * 根据id查询
     */
    <#if swagger2>@ApiOperation(value = "根据id查询数据") </#if>
    @GetMapping(value = "/getById")
    public ResponseWeb<${entity}> getById(@RequestParam("pkid") String pkid){
       return null;
    }

    /**
     * 新增
     */
    <#if swagger2>@ApiOperation(value = "新增数据") </#if>
    @PostMapping( "/add")
    public ResponseWeb<${entity}> add(@RequestBody ${entity} ${entity?uncap_first}){
        return null;
    }

    /**
     * 删除
     */
    <#if swagger2>@ApiOperation(value = "删除数据") </#if>
    @PutMapping( "/del")
    public ResponseWeb<String> delete(@RequestParam("ids") List<String> ids){
        return null;
    }

    /**
     * 修改
     */
    <#if swagger2>@ApiOperation(value = "更新数据") </#if>
    @DeleteMapping("/update")
    public ResponseWeb<${entity}> update(@RequestBody ${entity} ${entity?uncap_first}){
        return null;
     }

}
</#if>