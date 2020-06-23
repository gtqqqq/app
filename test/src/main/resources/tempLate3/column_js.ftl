<#list columns as column>
,{field:'${column.columnName}', title:'${column.columnComment}', width:100}
</#list>
<#--
.set${column.columnName?cap_first }(t.get("key0").toString());//${column.columnComment}
<#if obj>

<#elseif obj lt 15>

<#elseif student.studentAge lt 18>

<#else>

</#if>
-->