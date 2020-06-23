<#if fileExist == false>
package ${poPackageName};
import com.kyoocean.shipformula.framework.base.BaseConditionVO;
import java.math.BigDecimal;
import java.util.Date;

public class ${tableName}Cond extends BaseConditionVO {
    <#list columns as column>
    /* ${column.columnComment}*/
    private ${column.columnType} ${column.columnName};
    </#list>
    <#list columns as column>
    public ${column.columnType} get${column.columnName?cap_first }() {
    return ${column.columnName};
    }
    public void set${column.columnName?cap_first }(${column.columnType} ${column.columnName}) {
    this.${column.columnName} = ${column.columnName};
    }
    </#list>
}</#if>