<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<!-- SpreadJS语言设置（1）-->
    	<meta name="spreadjs culture" content="zh-cn" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.min.css' />">
	    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/metisMenu.min.css' />">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/sb-admin-2.css' />">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/font-awesome.min.css' />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/resources/css/bootstrap-addtabs.css' />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/resources/css/bootstrap-treeview.min.css' />">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/custom.css' />">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap-table/bootstrap-table.css' />">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css' />">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/Spread/css/gc.spread.sheets.10.0.1.css' />">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/Spread/css/gc.spread.sheets.excel2016colorful.10.0.1.css' />">
		<script type="text/javascript" src="<c:url value='/resources/script/jquery-1.11.1.min.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/script/bootstrap.min.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/script/sb-admin-2.js' />"></script>
    	<script type="text/javascript" src="<c:url value='/resources/script/metisMenu.min.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/bootstrap-table/bootstrap-table.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/script/jquery.validate.min.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/script/appscript/integration.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/Spread/scripts/gc.spread.sheets.all.10.0.1.min.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/Spread/scripts/resources/zh/gc.spread.sheets.resources.zh.10.0.1.min.js' />"></script>
		<script src="../resources/bootstrap-table/bootstrap-table-zh-CN.js"></script>
		
		<title>UpLoadFileTOService</title>
	</head>
	<body style="margin: 0;padding: 0">
		<div id="wrapper">
	        <!-- Page Content -->
	        <div id="page-wrapper">
	            <div class="row">
	                <div class="col-md-12">
	                   <!-- <h1 class="page-header">报表文件下载</h1> -->
	                </div>
	            </div>
	            <div class="row">
	                <div class="col-md-12">
	                    <div class="panel panel-default">
	                        <div class="panel-heading">
	                        </div>
	                        <div id="toolbar" class="btn-group">
                        		<button class="btn btn-primary btn-sm" id="addreport">
									<span class="glyphicon glyphicon-plus"></span> 增加
								</button>
			
								<button class="btn btn-primary btn-sm" id="editreport">
									<span class="glyphicon glyphicon-edit"></span> 编辑
								</button>
								<button class="btn btn-primary btn-sm" id="deletereport">
									<span class="glyphicon glyphicon-remove"></span> 删除
								</button>
                        	</div>
	                        <div class="panel-body">
	                        	<table id="integration"></table>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
    	</div>
		<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">新增报表</h4>
					</div>
					
					<div class="modal-body">
						 <div class="form-group">
							<label>报表名称</label> 
							<input class="form-control" id="reportname" name="reportname"/>
							<label>报表属性</label> 
							<select class="form-control" id="reporttype" name="reporttype"> 
								<option>0-周报</option>
								<option>1-月报</option>
								<option>2-季报</option>
							</select>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" id="insertdata1" class="btn btn-primary">提交</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
		</div>
	</body>
</html>