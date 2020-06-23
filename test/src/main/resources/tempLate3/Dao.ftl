
import ${poPackageName}.${tableName}PO;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ${tableName}Mapper {

    
    int deleteByPrimaryKey(String ${tableVar}Id);

   
    int insertSelective(${tableName}PO ${tableVar}PO);

  
    ${tableName}VO select${tableName}ByPrimaryKey(String ${tableVar}Id);

   
    int updateByPrimaryKeySelective(${tableName}PO ${tableVar}PO);

   
    List<${tableName}VO> select${tableName}ByList(${tableName}PO ${tableVar}PO);

}