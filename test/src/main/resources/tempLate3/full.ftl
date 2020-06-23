

//dao
public List<HashMap<String, Object>> find${tableName}ByConditions(@Param("paginationRequest")PaginationRequest paginationRequest); 

public Map<String, Object> find${tableName}bySum(@Param("paginationRequest")PaginationRequest paginationRequest); 

public Integer findCount${tableName}ByConditions(@Param("paginationRequest") PaginationRequest paginationRequest);
//modelDao
	@Resource
	private ${tableName}Dao ${tableVar}Dao;
	public PaginationMapResponse find${tableName}ListByPage(PaginationRequest paginationRequest)
			throws BusinessException {
	PaginationMapResponse paginationMapResponse = PaginationUtils.createPaginationMapResponse(paginationRequest);
	Integer pageCount =  ${tableName}Dao.findCount${tableName}ByPage(paginationRequest);
	List<HashMap<String, Object>> datalist = ${tableVar}Dao.find${tableName}ByConditions(paginationRequest);
	if (datalist != null && datalist.size() > 0 && paginationRequest.adjust(pageCount, paginationMapResponse)) {
		paginationMapResponse.setData(datalist);
		}
		return paginationMapResponse;
	}
	
	public Map<String, Object> find${tableName}bySum(PaginationRequest paginationRequest){
			Map<String, Object> datamaplist = ${tableVar}Dao.find${tableName}bySum(paginationRequest);
			return datamaplist;
			
		}
//Api
public ResultDto<PaginationMapResponse> find${tableName}ListByPage(PaginationRequest paginationRequest); 

public ResultDto<Map<String, Object>> find${tableName}bySum(PaginationRequest paginationRequest); 

//ApiImpl
@Override
	public ResultDto<PaginationMapResponse> find${tableName}ListByPage(PaginationRequest paginationRequest) {
		ResultDto<PaginationMapResponse> serviceResultDto = new PageResultDto<PaginationMapResponse>();
		try {
			PaginationMapResponse paginationMapResponse = ${tableName}Model
					.find${tableName}ListByPage(paginationRequest);
			serviceResultDto.setSuccess(true);
			serviceResultDto.setResult(paginationMapResponse);
		} catch (Exception e) {
			serviceResultDto.setSuccess(false);
			serviceResultDto.setError(ErrorCodeConstant.ERROR_CODE_S046024,
					"系统异常[" + ErrorCodeConstant.ERROR_CODE_S046024 + "]");
			logger.exception("分页获取", "分页获取 errorCode:{0}, paginationRequest:{1}", e,
					ErrorCodeConstant.ERROR_CODE_S046024, paginationRequest);
		}
		return serviceResultDto;
	}

	@Override
	public ResultDto<Map<String, Object>> find${tableName}bySum(PaginationRequest paginationRequest) {
		ResultDto<Map<String, Object>> serviceResultDto = new ResultDto<Map<String, Object>>();
		try {
			Map<String, Object> resultMap = ${tableName}Model.find${tableName}bySum(paginationRequest);
			serviceResultDto.setSuccess(true);
			serviceResultDto.setResult(resultMap);
		} catch (Exception e) {
			serviceResultDto.setSuccess(false);
			serviceResultDto.setMessage("系统异常[" + ErrorCodeConstant.ERROR_CODE_S046026 + "]");
			logger.exception("获取****统计信息", "获取****统计信息异常 errorCode:{0}, startDate:{1}, endDate:{2}", e,
					ErrorCodeConstant.ERROR_CODE_S046026, startDate, endDate);
		}
		return serviceResultDto;
	}
////////////////////////modelApi
	@Resource
	private ${tableName}Api ${tableVar}Api;
	
	public PaginationMapResponse find${tableName}ListByPage(PaginationRequest paginationRequest)
			throws BusinessException {
			PaginationMapResponse paginationMapResponse = ${tableVar}Api.find${tableName}ListByPage(paginationRequest).getResult();;
			return paginationMapResponse;
	
	}
	public Map<String, Object> find${tableName}bySum(PaginationRequest paginationRequest){
			Map<String, Object> dataMap = ${tableVar}Api.find${tableName}bySum(paginationRequest).getResult();
			return dataMap;
	}
/////////////////////////Controller
    @Resource
	private ${tableName}Model ${tableVar}Model;

	@RequestMapping(value = "/to${tableName}List.jhtml", method = { RequestMethod.GET, RequestMethod.POST })
	public String to${tableName}List(HttpServletRequest request, Map<String, Object> modelMap)
			throws BusinessException {
		return """/${tableName}_list";
	}
	@ResponseBody
	@RequestMapping(value = "/find${tableName}ListByPage.ajax", method = { RequestMethod.GET, RequestMethod.POST })
	public PaginationMapResponse find${tableName}ListByPage(HttpServletRequest httpServletRequest,
			${tableName}Form ${tableVar}Form) throws BusinessException {
		String orderColumn = httpServletRequest.getParameter("order[0][column]");
		if (StringUtils.isNotEmpty(orderColumn)) {
			String orderDir = httpServletRequest.getParameter("order[0][dir]");
			String orderColumnName = httpServletRequest.getParameter("columns[" + orderColumn + "][name]");
			${tableName}Form.setOrderColumn(orderColumnName);
			${tableName}Form.setOrderColumnType(orderDir);
		}

		PaginationRequest paginationRequest = PaginationUtils.createPaginationRequest(${tableVar}Form);
		PaginationMapResponse paginationMapResponse = ${tableVar}Model
				.find${tableName}ListByPage(paginationRequest);
		return paginationMapResponse;
	}

	@ResponseBody
	@RequestMapping(value = "/find${tableName}bySum.ajax", method = { RequestMethod.GET, RequestMethod.POST })
	public ResultDto<Map<String, Object>> findSum${tableName}Info(HttpServletRequest httpServletRequest,
			${tableName}Form ${tableVar}Form) throws BusinessException {
			PaginationRequest paginationRequest = PaginationUtils.createPaginationRequest(${tableVar}Form);
		return ${tableVar}Model.find${tableName}bySum(paginationRequest);
	}	


