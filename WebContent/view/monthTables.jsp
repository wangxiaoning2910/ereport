<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap Core CSS -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/bootstrap.min.css' />">
<!-- MetisMenu CSS -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/metisMenu.min.css' />">
<!-- Custom CSS -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/sb-admin-2.css' />">
<!-- Custom Fonts -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/font-awesome.min.css' />">
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value='/resources/css/bootstrap-addtabs.css' />">
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value='/resources/css/bootstrap-treeview.min.css' />">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/custom.css' />">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/uploadify/uploadify.css' />">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/bootstrap-table/bootstrap-table.css' />">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css' />">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/bootstrap-fileinput/css/fileinput.css' />">
<!-- jQuery -->
<script type="text/javascript"
	src="<c:url value='/resources/script/jquery-1.11.1.min.js' />"></script>
<!-- Bootstrap Core JavaScript -->
<script type="text/javascript"
	src="<c:url value='/resources/script/bootstrap.min.js' />"></script>
<!-- Custom Theme JavaScript -->
<script type="text/javascript"
	src="<c:url value='/resources/script/sb-admin-2.js' />"></script>
<!-- Metis Menu Plugin JavaScript -->
<script type="text/javascript"
	src="<c:url value='/resources/script/metisMenu.min.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/uploadify/jquery.uploadify.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/bootstrap-table/bootstrap-table.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/bootstrap-table/bootstrap-table-zh-CN.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/script/jquery.validate.min.js' />"></script>
<title>MonthTalbes</title>
</head>
<body>
	<div id="wrapper">
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="row">
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading"></div>
						<div class="panel-body">
							<input type="text" id="datetimepicker" name="datetimepicker">
							<button type="button" onclick="queryList()"
								class="btn btn-primary btn-sm">
								<span class="glyphicon glyphicon-search"></span> 查询
							</button>
						</div>
						<!-- /.panel-body -->
					</div>
				</div>
				<!-- /.col-lg-6 -->
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading"></div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<!-- <table id="monthTables">
								<tbody>
									
									</tbody>
							</table> -->
					<div class="dataTable_wrapper">
						<table width="100%" 
							id="monthTables">
							<thead>
								<tr>
									<th data-field="monthTableNames">名称</th>
									<th data-field="#">操作</th>
								</tr>
							</thead>
							<tbody>
								<tr>
										<td>全口径本月</td>
										<td><a><span
												class="glyphicon glyphicon-download-alt"></span></a></td>
									</tr>
									<tr>
										<td>全口径积累</td>
										<td></td>
									</tr>
									<tr>
										<td>自营本月</td>
										<td></td>
									</tr>
									<tr>
										<td>自营积累</td>
										<td></td>
									</tr>
							</tbody>
						</table>
					</div>
				</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	<div class="modal fade" id="tableScan" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog"  style="width:800px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">查看表格</h4>
				</div>
				<div class="modal-body">
					<table id="table1"></table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" id="add" class="btn btn-primary"
						onClick="save()">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
<script>
	$(document).ready(function() {
		$('#datetimepicker').datetimepicker({
			format : 'yyyy-mm',
			startDate : '201606',
			startView : 3,
			weekStart : 1,
			minView : 3,
			maxView : 4,
			todayHighlight : true,
			language : 'zh-CN',
		});

		$("#monthTables").bootstrapTable({
			classes : 'table table-hover table-condensed table-no-bordered',
			dataType : 'json',
			striped : true,//斑马线
			cache : false,
			pagination : true,//底部显示分页条
			sortable : false,//禁止所有列排序
			showRefresh : false,//不显示刷新
			pageNumber : 1, //初始化加载第一页，默认第一页
			pageSize : 10, //每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ],
			clickToSelect : true,
			onDblClickRow : onDblClickRow
		});
	});
	
	function onDblClickRow(row) {
		$('#tableScan').modal('show');
		$("#table1").bootstrapTable({
			classes : 'table table-hover table-condensed table-no-bordered',
			dataType : 'json',
			striped : true,//斑马线
			cache : false,
			pagination : true,//底部显示分页条
			sortable : false,//禁止所有列排序
			showRefresh : false,//不显示刷新
			pageNumber : 1, //初始化加载第一页，默认第一页
			pageSize : 10, //每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ],
			clickToSelect : true,
			columns : [ {
				filed : 'institudeCode',
				title : '机构代码'
			}, {
				filed : 'institudeName',
				title : '机构名称'
			}, {
				filed : 'dailyAverageQuantity',
				title : '日均保有量'
			}, {
				filed : 'ranking',
				title : '排名'
			} ]
		});
		var data;
		$.ajax({
			type:"get",
		    url:"queryMonthTables.do",
		    contentType: 'application/json',
		    dataType:'json',
		    success:function(json){
		    	data = json.list;
		    	/* for(var i = data.length - 1; i >= 0; i--){
		    		alert(data[i].ranking);
		    	}  */
		    	alert(data);
		        $("#table1").bootstrapTable("load",data);
		    },
		    error:function(){
		    	alert("错误");
		    	return;
		    }
		})
		    return data;
	}
	
</script>
</html>