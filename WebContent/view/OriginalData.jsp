<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.min.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/metisMenu.min.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/sb-admin-2.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/font-awesome.min.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/custom.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/uploadify/uploadify.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap-table/bootstrap-table.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap-fileinput/css/fileinput.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/select2-4.0.3/css/select2.css' />" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap-addtabs/css/bootstrap.addtabs.css' />">
	
	<script type="text/javascript" src="<c:url value='/resources/script/jquery-1.11.1.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/bootstrap.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/sb-admin-2.js' />"></script>
   	<script type="text/javascript" src="<c:url value='/resources/script/metisMenu.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/uploadify/jquery.uploadify.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-table/bootstrap-table.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-fileinput/js/fileinput.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/select2-4.0.3/js/select2.full.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/select2-4.0.3/js/i18n/zh-CN.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-addtabs/js/bootstrap.addtabs.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/appscript/OriginalData.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-table/bootstrap-table-zh-CN.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/bootbox.min.js' />"></script>
	<title>原始数据查询1</title>
</head>
<body>
	<div id="wrapper">
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                  <!--  <h1 class="page-header">原始文件上传</h1> -->
                </div>
            </div>
            <div class="row">
            
                <div class="col-lg-12">
                	<div class="panel panel-default">
                    	<div class="panel-heading">
                       	</div>
                       	<div class="panel-body">
       						<select id="selectreport"></select>
                       	</div>
                    </div>
                </div>
                
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                        </div>
                        <div class="panel-body">
                        	<div id="myTabs">
				                <ul class="nav nav-tabs" role="tablist"></ul>
				                <div class="tab-content"></div>
				            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
    
    
    
    <div class="modal fade" id="queryReport_inst" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>查询日期</label> 
						<input class="form-control" id="querydate_inst" name="querydate_inst"/>
						<label>网点属性</label> 
						<select class="form-control" id="used_inst" name="used_inst"> 
							<option>0-全部</option>
							<option>1-自营</option>
							<option>1-代理</option>
						</select>
						<label>统计方式</label> 
						<select class="form-control" id="institutionAdd_inst" name="institutionAdd_inst">
							<option>0-交易机构</option>
							<option>1-开户机构</option>
						</select>
						<label>业务代码</label> 
						<input class="form-control" id="busicode_inst" name="busicode_inst"> 
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="query_inst" class="btn btn-primary">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<div class="modal fade" id="queryReport_busi" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>查询日期</label> 
						<input class="form-control" id="querydate_busi" name="querydate_busi"/>
						<label>网点属性</label> 
						<select class="form-control" id="used_busi" name="used_busi" >
							<option>0-全部</option>
							<option>1-自营</option>
							<option>1-代理</option>
						</select> 
						<label>统计方式</label> 
						<select class="form-control" id="institutionAdd_busi" name="institutionAdd_busi">
							<option>0-交易机构</option>
							<option>1-开户机构</option>
						</select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="query_busi" class="btn btn-primary">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<div class="modal fade" id="queryReport_eu" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>查询日期</label> 
						<input class="form-control" id="querydate_eu" name="querydate_eu"/>
						<label>网点属性</label> 
						<select class="form-control" id="used_eu" name="used_eu" >
							<option>0-全部</option>
							<option>1-自营</option>
							<option>1-代理</option>
						</select> 
						<label>统计方式</label> 
						<select class="form-control" id="institutionAdd_eu" name="institutionAdd_eu">
							<option>0-交易机构</option>
							<option>1-开户机构</option>
						</select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="query_eu" class="btn btn-primary">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
</html>