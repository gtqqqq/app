<#if fileExist == false>
<${'#include'?html} "/common/common_var_definds.ftl"  />
<${'#macro render'?html}>
<link href="${r'${resources}'}/js/lib/DataTables/css/data-table.css" rel="stylesheet" type="text/css" />
<style>
input.error{
    border: solid 1px red !important;
}

select.error{
    border: solid 1px red !important;
}
</style>
</${'#macro'?html}>
</#if>
