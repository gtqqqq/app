<${'#include'?html} "/common/common_var_definds.ftl" />
<${'#macro render data'?html}>
<${'#escape'?html} x as x?html>
<div class="contentpanel">
  <div class="panel panel-default">
    <div class="panel-heading">
      <div class="panel-btns"><a href="#" class="minimize">−</a></div>
      <h4 class="panel-title">查询</h4>
    </div>
    <div class="panel-body">
      <div class="form-horizontal">
        <form id="searchForm" class="form-horizontal">
          <div class="row">
            <div class="col-sm-4 col-md-4  m-b-15">
              <div class="form-group" >
                <label for="" class="control-label col-xs-4">查询日期：</label>
                <div class="col-xs-8">
                  <div class="input-group">
                    <input type="text" class="form-control date-picker" name="queryDate" id="queryDate" value="${lastDay!''}" placeholder="请选择查询日期" required>
                    <span class="input-group-addon">
                      <i class="fa fa-calendar"></i>
                    </span> 
                  </div>
                </div>
              </div>
            </div>
             <#list columns as column>
             <div class="col-sm-4 col-md-4  m-b-15">
              <div class="form-group" >
                <label for="" class="control-label col-xs-4">${column }：</label>
                <div class="col-xs-8">
                  <div class="input-group">
                    <input type="text" class="form-control" name="${column }" id="${column }" value="" required>
                    <span class="input-group-addon">
                      <i class="fa fa-calendar"></i>
                    </span> 
                  </div>
                </div>
              </div>
            </div>
             </#list>
            <div class="col-sm-4 col-md-4  m-b-15 text-right pull-right">
              <div class="form-group" >
                <div class="col-xs-12">
                  <a id="searchBtn" href="Javascript:void(0);" class="btn btn-info"><i class="fa fa-search"></i> 查询</a>
                </div>
              </div>
            </div>
          </div>
          </form>
</div>



  <table class="table dataTable no-footer table-bordered table-hover" id="dataListTable"  role="grid" aria-describedby="table1_info">
    <thead>
      <tr>
        <th>序号</th>
          <#list columns as column>
        <th>${column}</th>
        </#list>
      
       
      </tr>
    </thead>
    <tbody></tbody>
  </table>
</div>
</div>

</${'#escape'?html}>
</${'#macro'?html}>

