<#if fileExist == false>
package ${poPackName};
import java.io.Serializable;

public class ${tableName}Bean extends BasePagerRequestModel implements Serializable {
</#if>
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
<#if fileExist == false>}</#if>