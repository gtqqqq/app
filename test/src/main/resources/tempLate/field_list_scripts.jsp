
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

	function today() {
		var now = new Date(new Date().getTime());
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
				findSum${tableName}Info($("#queryDate").val());
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
						return rowData.num;
				    }
				    <#list columns as column>
		        },{ "name" : ${column},
					"sClass" : "center",
					"mRender" : function(obj, type, rowData) {
						return rowData.${column} == null ? "" : rowData.${column};
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
		datatable.table.ajax.url('/dailysheet/find${tableName}ListByPage.ajax?' + $("#searchForm").serialize()).load();
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

</script>
</${'#macro'?html}>