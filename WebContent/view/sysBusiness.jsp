<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Bootstrap Admin Theme</title>

<!-- Bootstrap Core CSS -->
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="../resources/css/metisMenu.min.css" rel="stylesheet">

<!-- Timeline CSS -->
<link href="../resources/css/timeline.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="../resources/css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="../resources/css/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../resources/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
	<!-- jQuery -->
	<script src="../resources/script/jquery-1.11.1.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="../resources/script/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="../resources/script/metisMenu.min.js"></script>

	<!-- DataTables JavaScript -->
	<script src="../resources/script/jquery.dataTables.min.js"></script>
	<script src="../resources/script/dataTables.bootstrap.min.js"></script>
	<script src="../resources/script/dataTables.responsive.js"></script> 

	<!-- Custom Theme JavaScript -->
	<script src="../resources/script/sb-admin-2.js"></script>
	<script src="../resources/bootstrap-table/bootstrap-table.js"></script>
	<!-- <script src="../resources/script/bootstrap-table-zh-CN.js"></script> -->
</head>
<body>

	<div id="tb"></div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<button class="btn btn-default btn-sm" data-toggle="modal"
					data-target="#sysBusiness_update">
						<span class="glyphicon glyphicon-pencil"></span> 编辑
					</button>

					<button class="btn btn-default btn-sm" data-toggle="modal"
						data-target="#sysBusiness_add">
						<span class="glyphicon glyphicon-plus"></span> 增加
					</button>

					<button class="btn btn-default btn-sm" onClick="sys_delete()">
						<span class="glyphicon glyphicon-remove"></span> 删除
					</button>
					<!-- <button class="btn btn-default btn-sm" onClick="All_delete()">
						<span class="glyphicon glyphicon-link"></span> 全部删除
					</button> -->
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table width="100%" data-toggle="table" data-toolbar="#toolbar"
							data-click-to-select="true"
							class="table table-striped table-bordered table-hover"
							id="dataTables">
							<thead>
								<tr>
									<th data-field="check_box" data-checkbox="true">
									<th data-field="systemBusinessName">业务系统名称</th>
									<th data-field="userName">登录名</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list }" var="m" varStatus="n">
									<tr class="odd gradeX">
										<td></td>
										<td><c:out value="${m.systemBusinessName }"></c:out></td>
										<td><c:out value="${m.userName }"></c:out></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- =============================新增============================== -->
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="sysBusiness_add" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">新增业务系统</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>请选择业务系统</label> <select class="form-control"
							id="systemBusinessAdd" name="systemBusinessAdd">
							<c:forEach items="${list1 }" var="m" varStatus="n">
								<option>${m.businessName }</option>
							</c:forEach>
						</select>
					</div>
					<label>登录名</label> <input class="form-control" id="userNameAdd"
						name="userNameAdd"> <label>登录密码</label> <input
						class="form-control" type="password" id="passwordAdd"
						name="passwordAdd"> <label>登录机构</label> <input
						class="form-control" id="institutionAdd" name="institutionAdd">
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
	<!-- =============================更新============================== -->
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="sysBusiness_update" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">编辑业务系统</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>请选择业务系统</label> <select class="form-control"
							id="systemBusinessUpdate" name="systemBusinessUpdate">
							<c:forEach items="${list1 }" var="m" varStatus="n">
								<option>${m.businessName }</option>
							</c:forEach>
						</select>
					</div>
					<label>登录名</label> <input class="form-control" id="userNameUpdate"
						name="userNameUpdate"> <label>登录密码</label> <input
						class="form-control" type="password" id="passwordUpdate"
						name="passwordUpdate"> <label>登录机构</label> <input
						class="form-control" id="institutionUpdate"
						name="institutionUpdate">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" id="update" class="btn btn-primary"
						onClick="update_sel()">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<script>
		/* $(function(){
		    window.location.href='/ereport/view/systemBuseniss.do';
		    return false;
		 }) */
		$(document).ready(function() {
			$('#dataTables').DataTable({
				responsive : true
			});
		});
		function save() {
			var a = $("#userNameAdd").val();
			var b = $("#systemBusinessAdd").val();
			alert(b);
			var url = '&systemBusinessAdd=' + b;
			url = encodeURI(encodeURI(url));
			window.location.href = '/ereport/view/systemBusenissAdd.do?userName='
					+ a + url;
		}

		$('#dataTables').on('click-row.bs.table', function(e, row, element) {
			$('.success').removeClass('success');//去除之前选中的行的，选中样式
			$(element).addClass('success');//添加当前选中的 success样式用于区别
		});
		function update_sel() {
			//选择要更新的行
			var index1 = $('#dataTables').find('tr.success').data('index');//获得选中的行
			if (index1 == -1) {
				alert("选择行");
				return false;
			}
			/*
			var selects = $('#dataTables').bootstrapTable('getSelections');
			userNames = $.map(selects, function(row) {
				return row.userName;
			});
		 	$("#userNameUpdate").val(); */
			
			var sysName = $("#systemBusinessUpdate").val();
			var usrName = $("#userNameUpdate").val();
			// var theTableData = $('#dataTables').bootstrapTable('getData')[index1];//返回选中行所有数据
			//页面上改
			$('#dataTables').bootstrapTable('updateRow', {
				index : index1,
				row : {
					systemBusinessName : sysName,
					userName : usrName
				}
			});
			var url = '&systemBusinessUpdate=' + sysName;
			url = encodeURI(encodeURI(url));
			window.location.href = '/ereport/view/systemBusenissUpdate.do?userName='
					+ usrName + url;
		}
		function sys_delete() {
			var selects = $('#dataTables').bootstrapTable('getSelections');
			if(selects==""||selects==null){
				alert("请选择");
				return false;
			}
			if(confirm("确定要删除吗？")){
				//获取行里的值
			userNames = $.map(selects, function(row) {
				return row.userName;
			});
			$('#dataTables').bootstrapTable('remove', {
				field : 'userName',
				values : userNames
			});
			//=====================传username
			window.location.href = "/ereport/view/systemBusenissDelete.do?userName="+userNames;
			}
			return false;
		}
		function All_delete(){
			$('#dataTables').bootstrapTable('removeAll');
		}
	</script>
</body>
</html>