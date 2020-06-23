<#--${column.columnName}${column.columnType}${column.columnComment!""} <#if column_has_next>list最后一个元素</#if>-->
数据
<#list columns as column>
    ${column.columnName }:''<#if column_has_next>,</#if>//${column.columnComment!""}
</#list>
表单
 <#list columns as column>
        <#if  column.columnType == "Integer" ||column.columnType == "Long" >
        <el-form-item label="${column.columnComment!""}" prop="${column.columnName }">
            <el-input v-model="ruleForm.${column.columnName }"></el-input>
        </el-form-item>
        <#elseif  column.columnType == "Double" || column.columnType == "BigDecimal">
        <el-form-item label="${column.columnComment!""}" prop="${column.columnName }">
            <el-input v-model="ruleForm.${column.columnName }"></el-input>
        </el-form-item>
        <#elseif  column.columnType == "Date" >
        <el-form-item label="${column.columnComment!""}" required>
            <el-col :span="11">
                <el-form-item prop="${column.columnName }">
                    <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.${column.columnName }" style="width: 100%;"></el-date-picker>
                </el-form-item>
            </el-col>
            <#--<el-col class="line" :span="2">-</el-col>-->
            <#--<el-col :span="11">-->
                <#--<el-form-item prop="date2">-->
                    <#--<el-time-picker placeholder="选择时间" v-model="ruleForm.date2" style="width: 100%;"></el-time-picker>-->
                <#--</el-form-item>-->
            <#--</el-col>-->
        </el-form-item>
        <#else>
        <el-form-item label="${column.columnComment!""}" prop="${column.columnName }">
            <el-input v-model="ruleForm.${column.columnName }"></el-input>
        </el-form-item>
        </#if>
</#list>

表单验证
<#list columns as column>
    <#if  column.columnType == "Integer" ||column.columnType == "Long" >
    ${column.columnName }: [
    { required: true, message: '请输入${column.columnComment!""}', trigger: 'blur' }]<#if column_has_next>,</#if>
    <#--{ required: true, message: '请选${column.columnComment!""}', trigger: 'change' }<#if column_has_next>,</#if>-->
    <#elseif  column.columnType == "Double" || column.columnType == "BigDecimal">
    ${column.columnName }: [
    { required: true, message: '请输入${column.columnComment!""}', trigger: 'blur' }]<#if column_has_next>,</#if>
    <#--{ required: true, message: '请选择${column.columnComment!""}', trigger: 'change' }<#if column_has_next>,</#if>-->
    <#elseif  column.columnType == "Date" >
    ${column.columnName }: [
    { type: 'date', required: true, message: '请选择${column.columnComment!""}', trigger: 'change' }]<#if column_has_next>,</#if>
    <#else>
    ${column.columnName }: [
    { required: true, message: '请输入${column.columnComment!""}', trigger: 'blur' }]<#if column_has_next>,</#if>
    </#if>
</#list>
表格
<#list columns as column>
<el-table-column
    label="${column.columnComment!""}"
    prop="${column.columnName }">
</el-table-column>

</#list>
<#--
<#if obj>

<#elseif obj lt 15>

<#elseif student.studentAge lt 18>

<#else>

</#if>
-->