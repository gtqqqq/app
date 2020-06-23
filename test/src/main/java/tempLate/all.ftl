
    <#--${tableName}-->
    <#--<#list columns as column>-->
    <#--/* ${column.columnComment}*/-->
    <#--private ${column.columnType} ${column.columnName};-->
    <#--</#list>-->

    <#--<#list columns as column>-->
    <#--public ${column.columnType} get${column.columnName?cap_first }() {-->
    <#--return ${column.columnName};-->
    <#--}-->
    <#--public void set${column.columnName?cap_first }(${column.columnType} ${column.columnName}) {-->
    <#--this.${column.columnName} = ${column.columnName};-->
    <#--}-->
    <#--</#list>-->
	//s
    <#list columns as column>
    // ${column.columnComment}
    var ${column.columnName} = data.${column.columnName};
    ${'$'?html}('${'#'?html}${column.columnName}').val(${column.columnName});
    </#list>

    <#list columns as column>
    // ${column.columnComment}
    var ${column.columnName} = ${'$'?html}('${'#'?html}${column.columnName}').val();
    submitForm.${column.columnName}= ${column.columnName};
    </#list>

    <#list columns as column>
        // ${column.columnComment}
        var ${column.columnName} = ${'$'?html}('${'#'?html}${column.columnName}').val();
        if(${column.columnName} ==''){
        alert('${column.columnComment}不能为空!')
        }
        <#if column.columnType == "Integer" || column.columnType == "BigDecimal">
        if(check(${column.columnName})){
        alert('${column.columnComment}必须是数字!')
        }
        </#if>
    </#list>
