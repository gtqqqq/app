package ${modelPackName};

import ${daoPackName}.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.com.htwins.ec.fw.common.exception.BusinessException;
import cn.com.htwins.ec.fw.common.page.PaginationMapResponse;
import cn.com.htwins.ec.fw.common.page.PaginationRequest;
import cn.com.htwins.ec.fw.common.page.util.PaginationUtils;
import cn.com.htwins.fw.dto.ResultDto;
import cn.com.htwins.fw.log4j2.Logger;

@Service
public class ${tableName}Model {

	protected final Logger logger = Logger.getLogger(${tableName}Model.class);

	@Resource
	private ${tableName}Dao ${tableVar}Dao;

	public PaginationMapResponse find${tableName}ListByPage(PaginationRequest paginationRequest)
			throws BusinessException {
	PaginationMapResponse paginationMapResponse = PaginationUtils.createPaginationMapResponse(paginationRequest);
	Integer pageCount =  ${tableVar}Dao.find${tableName}ListByCount(paginationRequest);
	List<HashMap<String, Object>> datalist = ${tableVar}Dao.find${tableName}ListByPage(paginationRequest);
	if (datalist != null && datalist.size() > 0 && paginationRequest.adjust(pageCount, paginationMapResponse)) {
		paginationMapResponse.setData(datalist);
		}
		return paginationMapResponse;
	}
	
	public Map<String, Object> find${tableName}bySum(PaginationRequest paginationRequest){
			Map<String, Object> datamaplist = ${tableVar}Dao.find${tableName}bySum(paginationRequest);
			return datamaplist;
			
	}
	
}