package ${controllerPackName};
import ${poPackName}.${tableName}Form;
import ${modelPackName}.${webTableName}Model;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.com.htwins.ec.fw.common.exception.BusinessException;
import cn.com.htwins.ec.fw.common.page.PaginationMapResponse;
import cn.com.htwins.ec.fw.common.page.PaginationRequest;
import cn.com.htwins.ec.fw.common.page.util.PaginationUtils;
import ${controllerPackName}.base.WebBaseController;


@Controller
@RequestMapping(value = "/@TODOTAG")
public class ${webTableName}Controller extends WebBaseController {

	@Resource
	private ${webTableName}Model ${webTableName}Model;

	@RequestMapping(value = "/to${tableName}List.jhtml", method = { RequestMethod.GET, RequestMethod.POST })
	public String to${tableName}List(HttpServletRequest request, Map<String, Object> modelMap)
			throws BusinessException {
		return "@TODOTAG/${tableName}_list";
	}
	@ResponseBody
	@RequestMapping(value = "/find${tableName}ListByPage.ajax", method = { RequestMethod.GET, RequestMethod.POST })
	public PaginationMapResponse find${tableName}ListByPage(HttpServletRequest httpServletRequest,
			${tableName}Form ${tableVar}Form) throws BusinessException {
		
		PaginationRequest paginationRequest = PaginationUtils.createPaginationRequest(${tableVar}Form);
		PaginationMapResponse paginationMapResponse = ${webTableName}Model
				.find${tableName}ListByPage(paginationRequest);
		return paginationMapResponse;
	}

	@ResponseBody
	@RequestMapping(value = "/find${tableName}bySum.ajax", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> find${tableName}bySum(HttpServletRequest httpServletRequest,
			${tableName}Form ${tableVar}Form) throws BusinessException {
			PaginationRequest paginationRequest = PaginationUtils.createPaginationRequest(${tableVar}Form);
		return ${webTableName}Model.find${tableName}bySum(paginationRequest);
	}
	
}