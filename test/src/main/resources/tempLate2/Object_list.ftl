<#if fileExist == false>
<#-- 加载通用变量定义 -->
<${'#include'?html} "/common/common_var_definds.ftl" />

<#-- 导入通用模板 -->
<${'#import'?html} "/common/common_template.ftl" as common_template />

<${'#import'?html} "./fields/field_${tableVar}_links.ftl" as field_${tableVar}_links />
<${'#import'?html} "./fields/field_${tableVar}_scripts.ftl" as field_${tableVar}_scripts />
<${'#import'?html} "./fields/field_${tableVar}_main.ftl" as field_${tableVar}_main />

<#-- 调用通用模板 [ 标题  | metas | 链接  | 脚本  | 主要内容  | 数据  ]-->
<${'@common_template.render'?html} "@title",[],[field_${tableVar}_links],[field_${tableVar}_scripts],[field_${tableVar}_main],data?? />
</#if>
