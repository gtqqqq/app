<#if fileExist == false>
import java.io.Serializable;

public class ${tableName}PO  implements Serializable {
<#list columns as column>
	/**
	  *${column.columnComment}
	  */
    private String ${column.columnName};
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