<#if fileExist == false>
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
          <div class="col-sm-6 col-md-6 col-xs-6"> 
                  <div class="form-group">
                  <label class="control-label col-xs-3">时间：</label>
                    <div class="col-xs-9">
                      <div class="input-group">
                        <input class=" form-control date-picker" name="startDate" id="startDate" placeholder="请选择开始日期" >
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        <span class="input-group-addon">至</span>
                        <input class=" form-control date-picker" name="endDate" id="endDate" placeholder="请选择结束日期" >
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-sm-4 col-md-4 col-xs-4">
                    <div class="form-group">
                        <label class="control-label col-xs-3"> 订单状态：</label>
                        <div class="col-xs-5">
                             <select id="order_state" name="order_state" class="form-control">
                                <option selected="selected" value="">全部</option>
                                <option value=""></option>
                            </select>
                        </div>
                    </div>
                </div>
             <#list columns as column>
             <div class="col-sm-4 col-md-4  m-b-15">
              <div class="form-group" >
                <label for="" class="control-label col-xs-4">${column }：</label>
                <div class="col-xs-8">
                  <div class="input-group">
                    <input type="text" class="form-control" name="${column }" id="${column }" value="" placeholder="请输入">
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
      </div>
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
</${'#escape'?html}>
</${'#macro'?html}>
</#if>
