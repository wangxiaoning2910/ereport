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
					data-target="#sysBusiness_update">
						<span class="glyphicon glyphicon-pencil"></span> 编辑
					</button> -->
					<button class="btn btn-primary btn-sm" data-toggle="modal"
						data-target="#sysBusiness_add">
						<span class="glyphicon glyphicon-plus"></span> 增加
					</button>

					<button class="btn btn-primary btn-sm" onClick="sys_delete()">
						<span class="glyphicon glyphicon-remove"></span> 删除
					</button>
					<!-- <button class="btn btn-default btn-sm" onClick="All_delete()">
						<span class="glyphicon glyphicon-link"></span> 全部删除
					</button> -->
				</div>
				<table id="dataTables"></table>
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
							<%-- <c:forEach items="${list1 }" var="m" varStatus="n">
								<option>${m.businessName }</option>
							</c:forEach> --%>
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
						onClick="sys_update()">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<script>
	//===========================新增===============================
		function save() {
			var userNameAdd = $("#userNameAdd").val();
			var systemBusinessAdd = $("#systemBusinessAdd").val();
			systemBusinessAdd = encodeURI(systemBusinessAdd);
			var data;
				$.ajax({
				type:"get",
			    url:"systemBusenissAdd.do",
			    contentType: 'application/json',
			    data:{userNameAdd:userNameAdd,systemBusinessAdd:systemBusinessAdd},
			    dataType:'json',
			    success:function(){
			    	querySysBusList();
			    	 $('#sysBusiness_add').modal('hide');
			    },
			    error:function(){
			    	alert("错误");
			    	return;
			    }
			}) 
			return data;
			/* var a = $("#userNameAdd").val();
			var b = $("#systemBusinessAdd").val();
			var url = '&systemBusinessAdd=' + b;
			url = encodeURI(encodeURI(url));
			window.location.href = '/ereport/view/systemBusenissAdd.do?userName='
					+ a + url; */
		}

		/* 
		 */
		//===================================更新=============================
		function onDblClickRow(row){
    		$('#sysBusiness_update').modal('show');  
    		var userName = row.userName;
    		var systemBusinessName = row.systemBusinessName;
    		document.getElementById("userNameUpdate").value=userName;
    		document.getElementById("systemBusinessUpdate").value=systemBusinessName;
    	}
		function sys_update() {
			var userName = $("#userNameUpdate").val();
			var systemBusinessUpdate = $("#systemBusinessUpdate").val();
			systemBusinessUpdate = encodeURI(systemBusinessUpdate);
			var data;
				$.ajax({
				type:"get",
			    url:"systemBusenissUpdate.do",
			    contentType: 'application/json',
			    data:{userName:userName,systemBusinessUpdate:systemBusinessUpdate},
			    dataType:'json',
			    success:function(){
			    	querySysBusList();
			    	 $('#sysBusiness_update').modal('hide');
			    },
			    error:function(){
			    	alert("错误");
			    	return;
			    }
			}) 
			return data;
			//选择要更新的行
			/* var index1 = $('#dataTables').find('tr.success').data('index');//获得选中的行
			if (index1 == -1) {
				alert("选择行");
				return false;
			} */
			
		/* 	var sysName = $("#systemBusinessUpdate").val();
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
					+ usrName + url;*/
		} 
		//===============================删除==================================
		function sys_delete() {
			var selects = $('#dataTables').bootstrapTable('getSelections');
			if(selects==""||selects==null){
				alert("请选择");
				return false;
			}
			if(confirm("确定要删除吗？")){
				//获取行里的值,單條刪除
				var userNames = $.map(selects, function(row) {
				return row.userName;
				});
				/* var names = [];
				for(var i=0;i<selects.length;i++){
					names.push(selects[i].userName);
				} */
				$.ajax({
					type:"get",
				    url:"systemBusenissDelete.do?userNames="+userNames,
				    contentType: 'application/json',
	//			    data:{userNames:'111'},
				    dataType:'json',
				    success:function(){
				    	querySysBusList();
	//			    	alert(userName);
				    },
				    error:function(){
				    	alert("错误");
				    	return;
				    }
				}) 
			 
			//页面上移除
		/*	$('#dataTables').bootstrapTable('remove', {
				field : 'userName',
				values : userNames
			});
			//=====================传username
			window.location.href = "/ereport/view/systemBusenissDelete.do?userName="+userNames;*/
			}
			return false; 
		}
		function All_delete(){
			$('#dataTables').bootstrapTable('removeAll');
		}
//===========================页面主table===============================//
	$(document).ready(function() {
		
		 $("#dataTables").bootstrapTable({
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
            field: 'systemBusinessName',
            title: '业务系统名称',
            align: 'center'
        }, {
            field: 'userName',
            title: '登录名',
            align: 'center'
        }, {
            field: 'openDate',
            title: '添加日期',
            align: 'center'
        }, {
            field: 'modifyDate',
            title: '修改日期',
            align: 'center'
        },{
    		title: '修改',
    		field: '#',
    		align: 'center',
		  	formatter:function(value,row,index){
		  		var icon = '<a herf="#" data-target="#sysBusiness_update" data-toggle="modal"><span class="glyphicon glyphicon-pencil"></span></a>';
		    	return icon;  
			} 
        }],
    	onRefresh:function(){
    		querySysBusList();
    	},
    	onCheck:function(row){
    	},
    	onDblClickRow:onDblClickRow
	});
			querySysBusList();	
});
	
		function querySysBusList(){
			var data;
			$("#systemBusinessUpdate").empty();
			$.ajax({
				type:"POST",
			    url:"systemBuseniss.do",
			    contentType: 'application/json',
			    dataType:'json',
			    success:function(json){
			    	data = json.list;
			    	console.log(data)
			        $("#dataTables").bootstrapTable('load',data);
			        data1= json.list1;
			    	for(var i = data1.length - 1; i >= 0; i--){
			    		$("#systemBusinessUpdate").append("<option value='" + data1[i].businessName + "'>"+ data1[i].businessName + "</option>");
			    		$("#systemBusinessAdd").append("<option value='" + data1[i].businessName + "'>"+ data1[i].businessName + "</option>");
			    	}
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