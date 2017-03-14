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
<script src="../resources/bootstrap-table/bootstrap-table-zh-CN.js"></script>
</head>
<body>
	<div id="tb"></div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<!-- <button class="btn btn-default btn-sm" data-toggle="modal"
					data-target="#account_update">
						<span class="glyphicon glyphicon-pencil"></span> 编辑
					</button> -->
					<button class="btn btn-primary btn-sm" data-toggle="modal"
						data-target="#acc_add">
						<span class="glyphicon glyphicon-plus"></span> 增加
					</button>

					<button class="btn btn-primary btn-sm" onClick="acc_delete()">
						<span class="glyphicon glyphicon-remove"></span> 删除
					</button>
					<button class="btn btn-primary btn-sm" onClick="acc_role()">
						<span class="glyphicon glyphicon-remove"></span> 分配角色
					</button>
					<!-- <button class="btn btn-default btn-sm" onClick="All_delete()">
						<span class="glyphicon glyphicon-link"></span> 全部删除
					</button> -->
				</div>
				<table id="accountList"></table>
			</div>
		</div>
	</div>
	<!-- =============================新增============================== -->
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="acc_add" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">新增账号</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>用户名</label><input class="form-control" id="accountNameAdd"
						name="accountNameAdd">
					</div>
					<!-- <div class="form-group">
						<label>请选择角色</label> <select class="form-control"
							id="accountRoleAdd" name="accountRoleAdd">
						</select>
					</div> -->
					<div class="form-group">
					<label>密码</label> <input class="form-control" id="passwordAdd"
						name="passwordAdd">
					</div>
					<div class="form-group">
					<label>描述</label> <input class="form-control" id="descriptionAdd"
						name="descriptionAdd">
					</div>
					<div class="form-group">
					<label>账号状态</label> 
						<select class="form-control"  id="stateAdd" name="stateAdd">
							<option id="0">0</option>
							<option id="1">1</option><!-- 启用 -->
						</select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" id="add" class="btn btn-primary"
						onClick="add_save()">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!-- ====================================分配角色============================= -->
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="acc_role" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">分配角色</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>用户名</label><input class="form-control" id="acc_roleName"
						name="acc_roleName">
					</div>
					<table id="roleList"></table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" id="add" class="btn btn-primary"
						onClick="acc_roleSave()">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!-- =============================更新============================== -->
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="account_update" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">编辑账户</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>用户名</label><input class="form-control" id="accountNameUpdate"
						name="accountNameUpdate">
					</div>
					<div class="form-group">
						<label>密码</label><input class="form-control" id="passwordUpdate"
						name="passwordUpdate">
					</div>
					<label>描述</label> <input class="form-control" id="descriptionUpdate"
						name="descriptionUpdate">
					<div class="form-group">
					<label>账号状态</label> 
						<select class="form-control"  id="stateUpdate" name="stateUpdate">
							<option id="0">0</option>
							<option id="1">1</option><!-- 启用 -->
						</select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" id="update" class="btn btn-primary"
						onClick="update_save()">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<script>
	
	//===========================新增===============================
		function add_save() {
			var accountNameAdd = $("#accountNameAdd").val();
			var passwordAdd = $("#passwordAdd").val();
			var descriptionAdd = $("#descriptionAdd").val();
			descriptionAdd = encodeURI(descriptionAdd);
			var stateAdd = $("#stateAdd").val();
			var data;
				$.ajax({
				type:"get",
			    url:"accountAdd.do",
			    contentType: 'application/json',
			    data:{accountNameAdd:accountNameAdd,passwordAdd:passwordAdd,descriptionAdd:descriptionAdd,stateAdd:stateAdd},
			    dataType:'json',
			    success:function(){
			    	queryAccountList();
			    	 $('#acc_add').modal('hide');
			    },
			    error:function(){
			    	alert("错误");
			    	return;
			    }
			}) 
			return data;
		}

		//===================================更新=============================
		function onDblClickRow(row){
    		$('#account_update').modal('show');  
    		var accountNameUpdate = row.accountName;
    		document.getElementById("accountNameUpdate").value=accountNameUpdate;
    	}
		function update_save() {
			var passwordUpdate = $("#passwordUpdate").val();
			var descriptionUpdate = $("#descriptionUpdate").val();
			var stateUpdate = $("#stateUpdate").val();
			var accountNameUpdate = $("#accountNameUpdate").val();
			descriptionUpdate = encodeURI(descriptionUpdate);
			accountNameUpdate = encodeURI(accountNameUpdate);
			var data;
				$.ajax({
				type:"get",
			    url:"accountUpdate.do",
			    contentType: 'application/json',
			    data:{accountNameUpdate:accountNameUpdate,passwordUpdate:passwordUpdate,descriptionUpdate:descriptionUpdate,stateUpdate:stateUpdate},
			    dataType:'json',
			    success:function(){
			    	queryAccountList();
			    	 $('#account_update').modal('hide');
			    },
			    error:function(){
			    	alert("错误");
			    	return;
			    }
			}) 
			return data;
			
		} 
		//===============================删除==================================
		function acc_delete() {
			var selects = $('#accountList').bootstrapTable('getSelections');
			if(selects==""||selects==null){
				alert("请选择");
				return false;
			}
			if(confirm("确定要删除吗？")){
				//获取行里的值,單條刪除
				var accountName = $.map(selects, function(row) {
				return row.accountName;
				});
				$.ajax({
					type:"get",
				    url:"accountDelete.do?accountName="+accountName,
				    contentType: 'application/json',
	//			    data:{userNames:'111'},
				    dataType:'json',
				    success:function(){
				    	queryAccountList();
	//			    	alert(userName);
				    },
				    error:function(){
				    	alert("错误");
				    	return;
				    }
				}) 
			 
			}
			return false; 
		}
		function All_delete(){
			$('#accountList').bootstrapTable('removeAll');
		}
		//===========================分配角色=================================
		function acc_role(){
			var selects = $('#accountList').bootstrapTable('getSelections');
			if(selects==""||selects==null){
				alert("请选择");
				return false;
			}else if(selects.length>1){
				alert("只能选择一条")
			}else{
				$('#acc_role').modal('show');
				var acc_roleName = $.map(selects, function(row) {
				return row.accountName;
				});
				document.getElementById("acc_roleName").value=acc_roleName;
				$("#roleList").bootstrapTable({
			    	classes:'table table-hover table-condensed table-no-bordered',
			    	dataType:'json',
			    	striped:true,//斑马线
			    	cache:false,
			    	pagination: true,//底部显示分页条
			    	sortable: false,//禁止所有列排序
			    	showRefresh:false,//不显示刷新
			    	pageNumber:1,      //初始化加载第一页，默认第一页
			    	pageSize: 10,      //每页的记录行数（*）
			    	pageList: [10, 25, 50, 100],
			    	clickToSelect: true,
			    	columns: [{
			            checkbox: true
			        }, {
			            field: 'name',
			            title: '角色',
			            align: 'center'
			        }, {
			            field: 'roleKey',
			            title: 'roleKey',
			            align: 'center'
			        }],
			    	onRefresh:function(){
			    	},
			    	onCheck:function(row){
			    	},
				});
						queryRoleList();	
			}
			
		}
		function queryRoleList(){
			var data;
			$.ajax({
				type:"POST",
			    url:"queryRoleList.do",
			    contentType: 'application/json',
			    dataType:'json',
			    success:function(json){
			    	data = json.roleList;
			    	console.log(data)
			        $("#roleList").bootstrapTable('load',data);
			    },
			    error:function(){
			    	alert("错误");
			    	return;
			    }
			})
			return data;
		}
		function acc_roleSave(){
			var accountName = document.getElementById("acc_roleName").value;
			accountName = encodeURI(accountName);
			var selects = $('#roleList').bootstrapTable('getSelections');
			var roleKeys = $.map(selects, function(row) {
				return row.roleKey;
			});
			$.ajax({
				type:"POST",
			    url:"accRoleAdd.do?accountName="+accountName+"&roleKeys="+roleKeys,
			    contentType: 'application/json',
			    dataType:'json',
//			    data:{accountName:accountName,roleKeys:roleKeys},
			    success:function(json){
			    	$('#roleList').modal('hide');
			    	queryAccountList();
			    },
			    error:function(){
			    	alert("错误");
			    	return;
			    }
			})
		}
//===========================页面主table===============================//
	$(document).ready(function() {
		
		$("#accountList").bootstrapTable({
    	classes:'table table-hover table-condensed table-no-bordered',
    	dataType:'json',
    	striped:true,//斑马线
    	cache:false,
    	pagination: true,//底部显示分页条
    	sortable: false,//禁止所有列排序
    	showRefresh:false,//不显示刷新
    	pageNumber:1,      //初始化加载第一页，默认第一页
    	pageSize: 10,      //每页的记录行数（*）
    	pageList: [10, 25, 50, 100],
    	clickToSelect: true,
    	columns: [{
            checkbox: true
        }, {
            field: 'accountName',
            title: '用户名',
            align: 'center'
        }, {
            field: 'role',
            title: '所属角色',
            align: 'center'
        }, {
            field: 'description',
            title: '描述',
            align: 'center'
        },{
            field: 'state',
            title: '账号状态',
            align: 'center'
        },{
            field: 'createTime',
            title: '创建日期',
            align: 'center'
        },{
    		title: '修改',
    		field: '#',
    		align: 'center',
		  	formatter:function(value,row,index){
		  		var icon = '<a herf="#" data-target="#account_update" data-toggle="modal"><span class="glyphicon glyphicon-pencil"></span></a>';
		    	return icon;  
			} 
        }],
    	onRefresh:function(){
	  		queryAccountList();
    	},
    	onCheck:function(row){
    	},
    	onDblClickRow:onDblClickRow
	});
			queryAccountList();	
});
	
		function queryAccountList(){
			var data;
			$.ajax({
				type:"POST",
			    url:"queryAccountList.do",
			    contentType: 'application/json',
			    dataType:'json',
			    success:function(json){
			    	data = json.accountRoleModel;
			    	console.log(data)
			        $("#accountList").bootstrapTable('load',data);
			    	/*  data1 = json.roleList;
			    	for(var i = data1.length - 1; i >= 0; i--){
			    		//$("#systemBusinessUpdate").append("<option value='" + data1[i].businessName + "'>"+ data1[i].businessName + "</option>");
			    		$("#accountRoleAdd").append("<option value='" + data1[i].name + "'>"+ data1[i].name + "</option>");
			    	}  */
			        
			    },
			    error:function(){
			    	alert("错误");
			    	return;
			    }
			})
			return data;
		}
	//===========================页面主table完成===============================//	
	</script>
</body>
</html>