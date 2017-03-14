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
						data-target="#role_add">
						<span class="glyphicon glyphicon-plus"></span> 增加
					</button>

					<button class="btn btn-primary btn-sm" onClick="role_delete()">
						<span class="glyphicon glyphicon-remove"></span> 删除
					</button>
					<button class="btn btn-primary btn-sm" onClick="role_res()">
						<span class="glyphicon glyphicon-remove"></span> 分配权限
					</button>
					<!-- <button class="btn btn-default btn-sm" onClick="All_delete()">
						<span class="glyphicon glyphicon-link"></span> 全部删除
					</button> -->
				</div>
				<table id="roleList"></table>
			</div>
		</div>
	</div>
	<!-- =============================新增============================== -->
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="role_add" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">新增角色</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>角色名</label><input class="form-control" id="roleNameAdd"
						name="roleNameAdd">
					</div>
					<div class="form-group">
						<label>roleKey</label><input class="form-control" id="roleKeyAdd"
						name="roleKeyAdd">
					</div>
					<div class="form-group">
					<label>描述</label> <input class="form-control" id="descriptionAdd"
						name="descriptionAdd">
					</div>
					<div class="form-group">
					<label>角色状态</label> 
						<select class="form-control"  id="enableAdd" name="enableAdd">
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
	<!-- =============================更新============================== -->
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="account_update" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">编辑角色</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>角色名</label><input class="form-control" id="roleNameUpdate"
						name="roleNameUpdate">
					</div>
					<div class="form-group">
						<label>roleKey</label><input class="form-control" id="roleKeyUpdate"
						name="roleKeyUpdate">
					</div>
					<div class="form-group">
					<label>描述</label> <input class="form-control" id="descriptionUpdate"
						name="descriptionUpdate">
					</div>
					<div class="form-group">
					<label>角色状态</label> 
						<select class="form-control"  id="enableUpdate" name="enableUpdate">
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
	<!-- ======================分配权限=================================== -->
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="role_res" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">编辑角色</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>角色名</label><input class="form-control" id="roleResName"
						name="roleResName">
					</div>
					<table id="resourceList"></table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" id="update" class="btn btn-primary"
						onClick="role_resSave()">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<script>
	//===========================新增===============================
		function add_save() {
			var roleNameAdd = $("#roleNameAdd").val();
			var enableAdd = $("#enableAdd").val();
			var roleKeyAdd = $("#roleKeyAdd").val();
			var descriptionAdd = $("#descriptionAdd").val();
			descriptionAdd = encodeURI(descriptionAdd);
			roleNameAdd = encodeURI(roleNameAdd);
			var data;
				$.ajax({
				type:"get",
			    url:"roleAdd.do",
			    contentType: 'application/json',
			    data:{roleNameAdd:roleNameAdd,roleKeyAdd:roleKeyAdd,descriptionAdd:descriptionAdd,enableAdd:enableAdd},
			    dataType:'json',
			    success:function(){
			    	queryRoleList();
			    	 $('#role_add').modal('hide');
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
    		var roleNameUpdate = row.name;
    		document.getElementById("roleNameUpdate").value=roleNameUpdate;
    	}
		function update_save() {
			var roleNameUpdate = $("#roleNameUpdate").val();
			var descriptionUpdate = $("#descriptionUpdate").val();
			var enableUpdate = $("#enableUpdate").val();
			var roleKeyUpdate = $("#roleKeyUpdate").val();
			descriptionUpdate = encodeURI(descriptionUpdate);
			roleNameUpdate = encodeURI(roleNameUpdate);
			var data;
				$.ajax({
				type:"get",
			    url:"roleUpdate.do",
			    contentType: 'application/json',
			    data:{roleNameUpdate:roleNameUpdate,roleKeyUpdate:roleKeyUpdate,descriptionUpdate:descriptionUpdate,enableUpdate:enableUpdate},
			    dataType:'json',
			    success:function(){
			    	queryRoleList();
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
		function role_delete() {
			var selects = $('#roleList').bootstrapTable('getSelections');
			if(selects==""||selects==null){
				alert("请选择");
				return false;
			}
			if(confirm("确定要删除吗？")){
				//获取行里的值,單條刪除
				var name = $.map(selects, function(row) {
				return row.name;
				});
				name = encodeURI(name);
				$.ajax({
					type:"get",
				    url:"roleDelete.do?name="+name,
				    contentType: 'application/json',
	//			    data:{userNames:'111'},
				    dataType:'json',
				    success:function(){
				    	queryRoleList();
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
			$('#roleList').bootstrapTable('removeAll');
		}
	//======================分配权限====================================
		function role_res(){
			var selects = $('#roleList').bootstrapTable('getSelections');
			if(selects==""||selects==null){
				alert("请选择");
				return false;
			}else if(selects.length>1){
				alert("只能选择一条")
			}else{
				$('#role_res').modal('show');
				var roleResName = $.map(selects, function(row) {
				return row.name;
				});
				document.getElementById("roleResName").value=roleResName;
				$("#resourceList").bootstrapTable({
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
			            field: 'mid',
			            title: 'mid',
			            align: 'center'
			        }, {
			            field: 'text',
			            title: '描述',
			            align: 'center'
			        }],
			    	onRefresh:function(){
			    	},
			    	onCheck:function(row){
			    	},
				});
						queryResourceList();	
			}
			
		}
		function queryResourceList(){
			var data;
			$.ajax({
				type:"POST",
			    url:"queryResourceList.do",
			    contentType: 'application/json',
			    dataType:'json',
			    success:function(json){
			    	 data = json.resources;
			    	console.log(data)
			        $("#resourceList").bootstrapTable('load',data); 
			    },
			    error:function(){
			    	alert("错误");
			    	return;
			    }
			})
			return data;
		}
		function role_resSave(){
			var roleResName = document.getElementById("roleResName").value;
			roleResName = encodeURI(roleResName);
			var selects = $('#resourceList').bootstrapTable('getSelections');
			var mids = $.map(selects, function(row) {
				return row.mid;
			});
			$.ajax({
				type:"POST",
			    url:"roleResAdd.do?roleResName="+roleResName+"&mids="+mids,
			    contentType: 'application/json',
			    dataType:'json',
//			    data:{roleResName:roleResName,mids:mids},
			    success:function(json){
			    	queryRoleList();
			    	 $('#resourceList').modal('hide');
			    },
			    error:function(){
			    	alert("错误");
			    	return;
			    }
			})
		}
//===========================页面主table===============================//
	$(document).ready(function() {
		
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
            title: '角色名',
            align: 'center'
        }, {
            field: 'enable',
            title: '是否禁用',
            align: 'center'
        }, {
            field: 'roleKey',
            title: 'roleKey',
            align: 'center'
        },{
            field: 'description',
            title: '描述',
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
	  		queryRoleList();
    	},
    	onCheck:function(row){
    	},
    	onDblClickRow:onDblClickRow
	});
			queryRoleList();	
});
	
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
	//===========================页面主table完成===============================//	
	</script>
</body>
</html>