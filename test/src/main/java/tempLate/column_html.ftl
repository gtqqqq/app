<#list columns as column>
    <#if  column.columnType == "Byte">
    <div class="./div">
        <label class="./label">${column.columnComment}: </label>
        <input type="radio" class="./input" name="${column.columnName}" id="${column.columnName}0" value="0" checked/>NO
        <input type="radio" class="./input" name="${column.columnName}" id="${column.columnName}1" value="1"/>YES
    </div>
    <#elseif column.columnName.conatins('Id')>
    <input type="hidden" name="${column.columnName}" id="${column.columnName}"/>
    <#else>
    <div class="./div">
        <label class="./label">${column.columnComment}: </label>
        <input type="text" class="./input" name="${column.columnName}" id="${column.columnName}"/>
    </div>
    </#if>
</#list>
<#--
<#if obj>

<#elseif obj lt 15>

<#elseif obj lt 18>

<#else>

</#if>
-->