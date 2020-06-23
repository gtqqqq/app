<#if fileExist == false>
<${'#include'?html} "/common/common_var_definds.ftl" />
<${'#macro render'?html}>
<script type="text/javascript" src="${r'${resources}'}/js/lib/jquery/jquery.dataTables.js"></script>
<script type="text/javascript" src="${r'${resources}'}/js/lib/jquery/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="${r'${resources}'}/js/lib/jquery/dt-operation-commons.js"></script>
<script type="text/javascript" src="${r'${resources}'}/js/common/Tab.js"></script>
<script type="text/javascript" src="${r'${resources}'}/js/lib/jquery/jquery.blockUI.min.js"></script>
<script type="text/javascript" src="${r'${resources}'}/js/lib/plupload/moxie.js"></script>
<script type="text/javascript" src="${r'${resources}'}/js/lib/plupload/plupload.dev.js"></script>
<script type="text/javascript" src="${r'${resources}'}/js/common/pager.js"></script>
<script type="text/javascript" src="${r'${ctx}'}/resources/js/common/Tab.js"></script>

<script>                         
    $(function() {
		bindButton();
		initDataTable();
	});

	function today(day) {
		var now = new Date(day);
		var y = now.getFullYear();
		var m = now.getMonth() + 1;
		var d = now.getDate();
		if (m >= 1 && m <= 9) {
			m = "0" + m;
		}
		if (d >= 0 && d <= 9) {
			d = "0" + d;
		}
		return (y + '-' + m + '-' + d);
	}

	function bindButton() {
		$("#searchBtn").click(function() {
			var isPassed = true;
			$('[required]').each(function() {
				var foucsId = checkRequired(this);
				if (foucsId != '') {
					$("#" + foucsId).focus();
					isPassed = false;
				}
			});

			if (isPassed) {
			    var startDate = $('#startDate').val();
			    var endDate = $('#endDate').val();
			    if(startDate != '' && endDate != ''){
			      startDate = new Date(startDate);
			      endDate = new Date(endDate);
			      if(startDate > endDate){
			        jsErrAlert("开始日期不能大于统计结束日期");
			        return;
			      }
			    }
				query${tableName}List();
			}

		});

		$("#exportBtn").click(function() {
			var isPassed = true;
			$('[required]').each(function() {
				var foucsId = checkRequired(this);
				if (foucsId != '') {
					$("#" + foucsId).focus();
					isPassed = false;
				}
			});

			if (isPassed) {
				export${tableName}ListData();
			}
		});

		$('[required]').each(function() {
			$(this).keyup(function() {
				checkRequired(this);
			});
			$(this).blur(function() {
				checkRequired(this);
			});
			$(this).change(function() {
				checkRequired(this);
			});
		});
	};

	function initDataTable() {
		$("#dataListTable")
				.addIndex()
				.DataTable(
						   {
							stateSave : true,
							"ordering" : true,
							"columnDefs" : [ {
								"orderable" : false,
								"targets" : [ 0, 1 ]
							} ],
							"aLengthMenu" : [ [ 50, 100 ], [ "50", "100" ] ],         
			"columns" : [{
					"sClass" : "center",
					"mRender" : function(obj, type, rowData) {
						return rowData.num.toFixed(2);
						//return '<a href="javascript:void(0);" onClick="toModify(\''+ rowData.id+'\');" class=" btn btn-primary btn-xs m-5" ><i class="fa fa-edit"></i>关闭</a>';
				    }
				    <#list columns as column>
		        },{ 
					"sClass" : "center",
					"mRender" : function(obj, type, rowData) {
						return rowData.${column} == null ? "" : rowData.${column};
					}
		        	,{
					"sClass" : "center",
					"mRender" : function(obj, type, rowData) {
						return '<a href="javascript:void(0);" onClick="toModify(\''+ rowData.id+'\');" class=" btn btn-primary btn-xs m-5" ><i class="fa fa-edit"></i>修改</a>';
				    }
                    </#list>
				}  
			],
			"fnDrawCallback" : function(o) {
				$('.dataTables_info').css('margin-left', '15px');
				$('.dataTables_length').css('margin-left', '10px');
				$('.dataTables_length').css('margin-top', '10px');
			}
		});
	};

	function query${tableName}List() {
		$("#dataListTable tbody").empty();
		var datatable = new dataTable("dataListTable");
		datatable.table.ajax.url('/${location}/find${tableName}ListByPage.ajax?' + $("#searchForm").serialize()).load();
	};

	function checkRequired(obj){
	  if($(obj).val() == ''){
	    addErrerToElement(obj, $(obj).attr("placeholder"), 'error_required');
	    return obj.id;
	  } else {
	    removeErrorFromElement(obj, 'error_required');
	  }
	  return '';
	}

	function addErrerToElement(obj, message, errorType){
	  if($(obj).hasClass(errorType) || $(obj).hasClass('error')){
	    return;
	  }

	  $(obj).addClass(errorType);
	  $(obj).addClass('error');

	  if($(obj).parent().hasClass('input-group')){
	    $(obj).parent().parent().append('<label i="'+obj.id+'-error" class="error" for="'+obj.id+'">'+message+'</label>');
	  } else {
	    $(obj).parent().append('<label id="'+obj.id+'-error" class="error" for="'+obj.id+'">'+message+'</label>');
	  }
	}

	function removeErrorFromElement(obj, errorType){
	  if($(obj).hasClass(errorType)){
	    $(obj).removeClass(errorType);
	    $(obj).removeClass('error');
	    $("#"+obj.id+"-error").remove();
	  }
	}
	Date.prototype.Format = function (fmt) { 
	      var o = {
	          "M+": this.getMonth() + 1, // 月份
	          "d+": this.getDate(), // 日
	          "h+": this.getHours(), // 小时
	          "m+": this.getMinutes(), // 分
	          "s+": this.getSeconds(), // 秒
	          "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
	          "S": this.getMilliseconds() // 毫秒
	      };
	      if (/(y+)/.test(fmt))
	          fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	      for (var k in o)
	          if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	              return fmt;
	}
	function toModify(id){
	    
	}
</script>
</${'#macro'?html}>
</#if>