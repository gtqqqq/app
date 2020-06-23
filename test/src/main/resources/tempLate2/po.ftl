<#if fileExist == false>
package ${poPackName};
import java.io.Serializable;
import cn.com.fan1080.shop.common.web.pager.BasePagerRequestModel;

public class ${tableName}Form extends BasePagerRequestModel implements Serializable {
<#list columns as column>
    private String ${column};
</#list>
<#list columns as column>

    public String get${column?cap_first }() {
        return ${column};
    }
    public void set${column?cap_first }(String ${column}) {
        this.${column} = ${column};
    }
        
</#list>
}</#if>