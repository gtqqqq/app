<#if fileExist == false>
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ${tableName}ServiceImpl implements ${tableName}Service {
</#if>
	@Autowired 
	${tableName}Mapper ${tableName}mapper;
	@Override
	 public   List<${tableName}VO> select${tableName}ByList(${tableName}PO ${tableVar}PO){
		return ${tableName}mapper.getListByDealerId(dealerId);
	}
	
	@Override
	public int insertSelective(${tableName}PO ${tableVar}PO) {
		return ${tableName}mapper.insertSelective(${tableVar}PO);
	}
	
	@Override
	public int updateByPrimaryKeySelective(${tableName}PO ${tableVar}PO) {
		return ${tableName}mapper.updateByPrimaryKeySelective(${tableVar}PO);
	}
	
	@Override
	public ${tableName}VO select${tableName}ByPrimaryKey(String ${tableVar}Id) {
	return ${tableName}mapper.select${tableName}ByPrimaryKey(${tableVar}Id);
	}
<#if fileExist == false>}</#if>