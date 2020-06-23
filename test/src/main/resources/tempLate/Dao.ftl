package ${daoPackName};

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import cn.com.htwins.ec.fw.common.page.PaginationMapResponse;
import cn.com.htwins.ec.fw.common.page.PaginationRequest;

public interface ${tableName}Dao {

public List<HashMap<String, Object>> find${tableName}ListByPage(@Param("paginationRequest")PaginationRequest paginationRequest); 

public Map<String, Object> find${tableName}bySum(@Param("paginationRequest")PaginationRequest paginationRequest); 

public Integer find${tableName}ListByCount(@Param("paginationRequest") PaginationRequest paginationRequest);

}