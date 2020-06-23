<#if fileExist == false>

public interface ${tableName}Service {
</#if>
    List<${tableName}VO> select${tableName}ByList(${tableName}PO ${tableVar}PO);
	
	int insertSelective(${tableName}PO ${tableVar}PO);
    
    int updateByPrimaryKeySelective(${tableName}PO ${tableVar}PO);
    
    ${tableName}VO select${tableName}ByPrimaryKey(String ${tableVar}Id);
<#if fileExist == false>}</#if>