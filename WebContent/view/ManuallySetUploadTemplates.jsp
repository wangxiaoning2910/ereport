<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ytincl.ereport.pojo.*" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>手动设置上传文件模板</title>
	
	<script type="text/javascript" src="<c:url value='/resources/script/jquery-1.11.1.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/bootstrap.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/sb-admin-2.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/metisMenu.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-table/bootstrap-table.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/jquery.validate.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-table/bootstrap-table-zh-CN.js' />"></script>
 	<script type="text/javascript" src="<c:url value='/resources/bootstrap-editable/bootstrap-editable.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-editable/bootstrap-table-editable.js' />"></script>
	<!-- Bootstrap Core CSS -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.min.css' />">
	<!-- MetisMenu CSS -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/metisMenu.min.css' />">
	<!-- Custom CSS -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/sb-admin-2.css' />">
	<!-- Custom Fonts -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/custom.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap-table/bootstrap-table.css' />">
	<%-- <link rel="stylesheet" type="text/css"
		href="<c:url value='/resources/bootstrap-editable/bootstrap-editable.css' />">  --%>
	<link rel="stylesheet" type="text/css" href="../resources/bootstrap-editable/bootstrap-editable.css" rel="stylesheet" /> 
	<script type="text/javascript">
		function init(){
			$("#templateTable").bootstrapTable({
		    	classes:'table table-hover table-condensed table-no-bordered',
		    	dataType:'json',
		    	striped:true,
		    	pagination: true,
		    	search:true,
		    	toolbar:'#toolbar',
		    	searchOnEnterKey:true,
		    	trimOnSearch:false,
		    	sortable: false,
		    	showRefresh:false,
		    	pagination:true,
		    	pageNumber:1,      //初始化加载第一页，默认第一页
		    	pageSize: 10,      //每页的记录行数（*）
		    	pageList: [10, 25, 50, 100],
		    	showExport: false,                     //是否显示导出
		        exportDataType: "basic",              //basic', 'all', 'selected'.
		    	columns: [{
		            field: 'state',
		            radio: true,
		    	},{
		    	    field: 'tempName',
		    	    title: '模板名称',
		    	    align: 'center',
		    	},{
		    		field: 'dataSpace',
		    	    title: '数据区域',
		    	    align: 'center',
		    	},{
		    		title: '状态',
		    		field: 'status',
		    		align: 'center',
				  	formatter:function(value,row,index){
				  		var status = row.status
				  		console.log(row.status)
				  		var icon;
				  		if(status==0){//失效
				  			icon = '<span class="glyphicon glyphicon-remove" style="color: rgb(151, 0, 0);"></span>';
				  		}else if(status==1){//生效
				  			icon = '<span class="glyphicon glyphicon-ok" style="color: rgb(0, 174, 0);"></span>';
				  		}else{//其他情况
				  			icon = '<span class="glyphicon glyphicon-question-sign"></span>';
				  		}
				    	return icon;  
					} 
				}],
		    	onRefresh:function(){
		    	},
		    	onCheck:function(row){
		    	},
		    	onDblClickRow:function(row,element){
		    	}
			});
		}
		function queryTemplates(){
			$.ajax({
				type:"POST",
			    url:"queryTemplates.do",
			    dataType:'json',
			    success:function(data){
			    	var d = data.list
			    	console.log(d)
			        $("#templateTable").bootstrapTable('load',d);
			    },
			    error:function(){
			    	alert("错误");
			    	return;
			    }
			})
		}
		
    	$(init)
    	$(queryTemplates)
    	
	</script>
	

</head>
<body>
	<div id="wrapper">
	        <!-- Page Content -->
	        <div id="page-wrapper">
	            <div class="row">
	                <div class="col-lg-12">
	                   <!-- <h1 class="page-header">报表文件下载</h1> -->
	                </div>
	            </div>
	            <div class="row">
	                
	                <div class="col-lg-12">
	                    <div class="panel panel-default">
	                        <div class="panel-heading">
	                        </div>
	                        <div class="panel-body">
	                        	<div id="toolbar" class="btn-group">
	                        		<button class="btn btn-primary btn-sm" data-toggle="modal"
										data-target="#addrow">
										<span class="glyphicon glyphicon-plus"></span> 增加
									</button>
				
									<button class="btn btn-primary btn-sm">
										<span class="glyphicon glyphicon-remove"></span> 删除
									</button>
	                        	</div>
	                        	<table id="templateTable"></table>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	        
	        
	        <div class="modal fade" id="addrow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">新增模板</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>模板名称</label> 
								<input class="form-control" id="userNameAdd" name="userNameAdd">
								<label>数据区域</label> 
								<input class="form-control" id="userNameAdd" name="userNameAdd"> 
								<label>登录密码</label> 
								<input class="form-control" type="password" id="passwordAdd" name="passwordAdd"> 
								<label>登录机构</label> 
								<input class="form-control" id="institutionAdd" name="institutionAdd">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="button" id="add" class="btn btn-primary" onClick="save()">提交</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>
    	</div>
</body>
</html>