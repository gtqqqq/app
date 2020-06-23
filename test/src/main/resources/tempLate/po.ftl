package ${poPackName};
import java.io.Serializable;
import cn.com.fan1080.shop.common.web.pager.BasePagerRequestModel;

public class ${tableName}Form extends BasePagerRequestModel implements Serializable {
<#list columns as column>
     private String ${column};
</#list>
	


	
}