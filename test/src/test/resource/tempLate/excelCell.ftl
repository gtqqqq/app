<#list columns as column>
    <#if  column.columnType == "Integer" ||column.columnType == "Long" || column.columnType == "Byte">
    if( StringUtils.isNumeric((String)cellMap.get(${column_index}))){
    ${tableVar}.set${column.columnName?cap_first }(cellMap.get(${column_index}))
    }
    <#elseif  column.columnType == "Double" || column.columnType == "BigDecimal">
    if(isDouble((String)cellMap.get(${column_index}))){
    ${tableVar}.set${column.columnName?cap_first }(cellMap.get(${column_index}))
    }
    <#elseif  column.columnType == "Date" >
    if(isDouble((String)cellMap.get(${column_index}))){
    ${tableVar}.set${column.columnName?cap_first }(cellMap.get(${column_index}))
    }
    <#else>
    if(StringUtils.isNotBlank((String)cellMap.get(${column_index}))){
    ${tableVar}.set${column.columnName?cap_first }(cellMap.get(${column_index}))
    }
    </#if>
</#list>

<#list columns as column>
    <#if  column.columnType == "Integer" ||column.columnType == "Long" || column.columnType == "Byte">
    if( StringUtils.isNumeric((String)cellMap.get(${column_index}))){
    ${tableVar}.set${column.columnName?cap_first }(cellMap.get(${column_index}))
    }
    <#elseif  column.columnType == "Double" || column.columnType == "BigDecimal">
    if(isDouble((String)cellMap.get(${column_index}))){
    ${tableVar}.set${column.columnName?cap_first }(cellMap.get(${column_index}))
    }
    <#elseif  column.columnType == "Date" >
    if(isDouble((String)cellMap.get(${column_index}))){
    ${tableVar}.set${column.columnName?cap_first }(cellMap.get(${column_index}))
    }
    <#else>
    if(StringUtils.isNotBlank((String)cellMap.get(${column_index}))){
    ${tableVar}.set${column.columnName?cap_first }(cellMap.get(${column_index}))
    }
    </#if>
</#list>