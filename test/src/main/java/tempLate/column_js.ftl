<#list columns as column>

    <#list columns as column>
        <#if  column.columnType == "Integer" ||column.columnType == "Long" >
        isNull(${column.columnName }))&&alert("请输入${column.columnComment}");
        isNumber(${column.columnName })&&alert("${column.columnComment}必须是数字");
        <#elseif  column.columnType == "Double" || column.columnType == "BigDecimal">
        isNull(${column.columnName }))&&alert("请输入${column.columnComment}");
        isNumber(${column.columnName })&&alert("${column.columnComment}必须是数字");
        <#elseif  column.columnType == "Date" >
        isNull(${column.columnName }))&&alert("请输入${column.columnComment}");
        isDate(${column.columnName }&&alert("请输入${column.columnComment}日期格式不正确");
        <#else>
        isNull(${column.columnName })&&alert("请输入${column.columnComment}");
        </#if>
    </#list>
    <#if  column.columnType == "tinyint">
    {title:"${column.columnComment}",field:'${column.columnName}', align:"center" ,width:100,formatter : function(val) {
    return val;
    }, editor: {
    type: 'combobox',
    options: {
    valueField: 'Gender',
    textField: 'text',
    data: [{'text': '1', 'Gender': '1'}, {'text': '2', 'Gender': '2'}],
    required: true
    }
    }
    },
    <#elseif column.columnName.conatins('id')>
    {title:"${column.columnComment}",field:'${column.columnName}', align:"center" ,width:100,hidden:'true'},
    <#else>
    {title:"${column.columnComment}",field:'${column.columnName}', align:"center" ,width:100,formatter : function(val) {
    return val;
    },editor:'textbox'},
    </#if>
</#list>
<#--
<#if obj>

<#elseif obj lt 15>

<#elseif student.studentAge lt 18>

<#else>

</#if>
-->