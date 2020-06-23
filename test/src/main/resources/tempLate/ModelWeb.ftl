package ${modelPackName};

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
import cn.com.htwins.fw.dto.ResultDto;
import cn.com.htwins.fw.log4j2.Logger;

@Service
public class ${webTableName}Model {

	protected final Logger logger = Logger.getLogger(${webTableName}Model.class);
		
	@Resource
	private ${tableName}Api ${tableVar}Api;

	
	public PaginationMapResponse find${tableName}ListByPage(PaginationRequest paginationRequest)
			throws BusinessException {
			PaginationMapResponse paginationMapResponse = ${tableVar}Api.find${tableName}ListByPage(paginationRequest).getResult();
			return paginationMapResponse;
	
	}
	public Map<String, Object> find${tableName}bySum(PaginationRequest paginationRequest){
			Map<String, Object> dataMap = ${tableVar}Api.find${tableName}bySum(paginationRequest).getResult();
			return dataMap;
	}
	

	
}