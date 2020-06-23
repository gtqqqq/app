<#if fileExist == false>
package ${poPackName};
import com.kyoocean.shipformula.framework.base.BaseConditionVO;

public class ${tableName}Cond extends BaseConditionVO {
<#list columns as column>
    private ${column.columnType} ${column.columnName};
</#list>
<#list columns as column>
    public String get${column.columnName?cap_first }() {
        return ${column.columnName};
    }
    public void set${column.columnName?cap_first }(String ${column.columnName}) {
        this.${column.columnName} = ${column.columnName};
    }
</#list>
}</#if>