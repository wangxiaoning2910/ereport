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
	href="<c:url value='/resources/bootstrap-table/bootstrap-table.css' />">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/uploadify/uploadify.css' />">
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
	src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/bootstrap-fileinput/js/fileinput.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/script/jquery.validate.min.js' />"></script>
<script src="../resources/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script>
		var files_start;
		var files_rows;
	$(document).ready(function() {
		//文件table
		 $("#fileSelected").bootstrapTable({
		    	classes:'table table-hover table-condensed ',
		    	dataType:'json',
		    	striped:true,//斑马线
		    	cache:false,
		    	pagination: true,//底部显示分页条
		    	sortable: false,//禁止所有列排序
		    	showRefresh:true,//显示刷新
		    	pageNumber:1,      //初始化加载第一页，默认第一页
		    	pageSize: 10,      //每页的记录行数（*）
		    	pageList: [10, 25, 50, 100],
		    	clickToSelect: true,
		    	columns: [{
		            checkbox: true
		        },{
		    		field: 'Number',
                   title: 'Number',
                   align: 'center',
                   formatter: function (value, row, index) {
                       return index+1;
                   }
		        }, {
		            field: 'temp_id',
		            title: '模板名称',
		            align: 'center'
		        }, {
		            field: 'file_name',
		            title: '上传文件名称',
		            align: 'center'
		        }, {
		            field: '#',
		            title: '删除',
		            align: 'center',
		            formatter:function(value,row,index){
				  		var icon = '<a herf="#" onClick="deleteFile(1)"><span class="glyphicon glyphicon-remove"></span></a>';
				    	return icon;  
					}
		        }],
		        onRefresh:function(){
		    		queryFile(1);
		    	},
		    	onDblClickRow:function(row){
		    		
		    	}
			});
		//上传
		$("#choosefile").fileinput({
			language : 'zh', //设置语言
			uploadUrl : "uploadFiles.do", //上传的地址
			browseLabel : "选择文件",
			removeLabel : "删除",
			uploadLabel : "上传",
			allowedFileExtensions : [ 'xls' ],//接收的文件后缀
			uploadAsync : true, //默认异步上传
			showUpload : true, //是否显示上传按钮
			showRemove : true, //显示移除按钮
			showPreview : false, //是否显示预览
			showCaption : true,//是否显示标题
			browseClass : "btn btn-primary", //按钮样式     
			dropZoneEnabled : false,//是否显示拖拽区域
			maxFileCount : 10, //表示允许同时上传的最大文件个数
			enctype : 'multipart/form-data',
			validateInitialCount : true,
			previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
			msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
			uploadExtraData: function() {
				var temp_id = document.getElementById('models').value;
				files_start = document.getElementById('file_start').value;
				files_rows = document.getElementById('file_rows').value;
				return {
					"temp_id":temp_id
				};
            }
		});
			//异步上传失败返回结果处理
			$('#choosefile').on('fileerror',
					function(event, data, msg) {
						console.log(data)
						console.log(event)
						console.log(msg)
					});
			//异步上传成功返回结果处理
			$("#choosefile").on("fileuploaded",
					function(event, data, previewId, index) {
						console.log(data.response);
						 $('#model_add').modal('hide');
						// $("#choosefile").empty();
					});
			var downloadTable = "fileSelected";
			$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
				// 获取已激活的标签页的名称
	            var activeTab = $(e.target).text();
				if(activeTab=="上传子模板及文件"){
					downloadTable = "fileSelected1";
				}
			});
			//下载
			$('#exportfiles').click(function(){
				exportfiles(downloadTable);
		    })
			//获取模板
			var data;
			$.ajax({
				type:"POST",
			    url:"queryTemplate.do?t=0",
			    contentType: 'application/json',
			    dataType:'json',
			    success:function(json){
			        data= json.list;
			    	for(var i = data.length - 1; i >= 0; i--){
			    		$("#models").append("<option>"+ data[i].temp_id+"."+data[i].version + "</option>");
			    	}
			    },
			    error:function(){
			    	alert("错误");
			    	return;
			    }
			})
			return data;
	})
	function queryFile(t){
		if(t==1){
			var temp_id = document.getElementById('models').value;
		}else if(t==2){
			var temp_id = document.getElementById('upTemp_id').value;
		}
		var data;
		$.ajax({
			type:"POST",
		    url:"queryFile.do?temp_id="+temp_id,
		    contentType: 'application/json',
		    dataType:'json',
	//	    data:{temp_id:temp_id},
		    success:function(json){
		        data= json.list2;
		        if(t==1){
		       		 $("#fileSelected").bootstrapTable('load',data);
		        }else if(t==2){
		        	$("#fileSelected1").bootstrapTable('load',data);
		        }
		    },
		    error:function(){
		    	alert("错误");
		    	return;
		    }
		})
		return data;
	}
	var f = true;
	function filesAndModels(){
		//文件table
		 $("#fileSelected1").bootstrapTable({
		    	classes:'table table-hover table-condensed ',
		    	dataType:'json',
		    	striped:true,//斑马线
		    	cache:false,
		    	pagination: true,//底部显示分页条
		    	sortable: false,//禁止所有列排序
		    	showRefresh:true,//不显示刷新
		    	pageNumber:1,      //初始化加载第一页，默认第一页
		    	pageSize: 10,      //每页的记录行数（*）
		    	pageList: [10, 25, 50, 100],
		    	clickToSelect: true,
		    	columns: [{
		            checkbox: true
		        },{
		    		field: 'Number',
                  title: 'Number',
                  align: 'center',
                  formatter: function (value, row, index) {
                      return index+1;
                  }
		        }, {
		            field: 'temp_id',
		            title: '模板名称',
		            align: 'center'
		        }, {
		            field: 'file_name',
		            title: '上传文件名称',
		            align: 'center'
		        }, {
		            field: '#',
		            title: '删除',
		            align: 'center',
		            formatter:function(value,row,index){
				  		var icon = '<a herf="#"  onClick="deleteFile(2)><span class="glyphicon glyphicon-remove"></span></a>';
				    	return icon;  
					}
		        }],
		        onRefresh:function(){
		    		queryFile(2);
		    	},
		    	onDblClickRow:function(row){
		    		
		    	}
			});
		//上传
		$("#choosefile1").fileinput({
			language : 'zh', //设置语言
			uploadUrl : "uploadFiles.do", //上传的地址
			browseLabel : "选择文件",
			removeLabel : "删除",
			uploadLabel : "上传",
			allowedFileExtensions : [ 'xls' ],//接收的文件后缀
			uploadAsync : true, //默认异步上传
			showUpload : true, //是否显示上传按钮
			showRemove : true, //显示移除按钮
			showPreview : false, //是否显示预览
			showCaption : true,//是否显示标题
			browseClass : "btn btn-primary", //按钮样式     
			dropZoneEnabled : false,//是否显示拖拽区域
			maxFileCount : 10, //表示允许同时上传的最大文件个数
			enctype : 'multipart/form-data',
			validateInitialCount : true,
			previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
			msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
			uploadExtraData: function() {
				return {"temp_id":document.getElementById('upTemp_id').value};
           }
		});
			//异步上传失败返回结果处理
			$('#choosefile1').on('fileerror',
					function(event, data, msg) {
						console.log(data)
						console.log(event)
						console.log(msg)
					});
			//异步上传成功返回结果处理
			$("#choosefile1").on("fileuploaded",
					function(event, data, previewId, index) {
						console.log(data.response);
						 $('#model_add').modal('hide');
					});
		
		//上传
		$("#modelsC").fileinput({
			language : 'zh', //设置语言
			uploadUrl : "uploadFiles.do", //上传的地址
			browseLabel : "选择文件",
			removeLabel : "删除",
			uploadLabel : "上传",
			allowedFileExtensions : [ 'xls' ],//接收的文件后缀
			uploadAsync : true, //默认异步上传
			showUpload : true, //是否显示上传按钮
			showRemove : true, //显示移除按钮
			showPreview : false, //是否显示预览
			showCaption : true,//是否显示标题
			browseClass : "btn btn-primary", //按钮样式     
			dropZoneEnabled : false,//是否显示拖拽区域
			maxFileCount : 10, //表示允许同时上传的最大文件个数
			enctype : 'multipart/form-data',
			validateInitialCount : true,
			previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
			msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
			uploadExtraData: function() {
				return {"temp_id":document.getElementById('upTemp_id').value};
           }
		});
			//异步上传失败返回结果处理
			$('#modelsC').on('fileerror',
					function(event, data, msg) {
						console.log(data)
						console.log(event)
						console.log(msg)
					});
			//异步上传成功返回结果处理
			$("#modelsC").on("fileuploaded",
					function(event, data, previewId, index) {
						console.log(data.response);
						 $('#model_add').modal('hide');
					});
			
			if(f){
			var data1;
			$.ajax({
				type:"get",
			    url:"queryTemplate.do?t=1",//只查询复合模板
			    contentType: 'application/json',
			    dataType:'json',
			    success:function(json){
			    	data1 = json.list;
			    //	var html = "<option value='0'>无</option> ";
			    	for(var i = data1.length - 1; i >= 0; i--){
			    		$("#upTemp_id").append("<option value='" + data1[i].temp_id + "'>"+ data1[i].temp_id + "</option>");
			    	}
			    //	$("#upTemp_id").append(html);
			    },
			    error:function(){
			    	alert("错误");
			    	return;
			    }
			})
			f = false;
			}
			
	}
	function deleteFile(t){
		var selects = $('#fileSelected').bootstrapTable('getSelections');
		
		if(selects==""||selects==null){
			alert("请选择");
			return false;
		}
		if(confirm("确定要删除吗？")){
			//获取行里的值,單條刪除
			var fileName = $.map(selects, function(row) {
			return row.file_name;
			});
			var temp_id = $.map(selects, function(row) {
				return row.temp_id;
				});
			$.ajax({
				type:"get",
			    url:"fileDelete.do?fileName="+fileName+"&temp_id="+temp_id,
			    contentType: 'application/json',
			    dataType:'json',
			    success:function(){
			    	queryFile(t);
			    },
			    error:function(){
			    	alert("错误");
			    	return;
			    }
			}) 
		}
		return false; 
	}
	function exportfiles(downloadTable){
		if(downloadTable=="fileSelected"){
			var selects = $('#fileSelected').bootstrapTable('getSelections');
			var temp_id = document.getElementById('models').value;
			var url = "exportByTemplate.do";
		}else if(downloadTable=="fileSelected1"){
			var selects = $('#fileSelected1').bootstrapTable('getSelections');
			var url = "";
		}
		if(selects==""||selects==null){
			alert("请选择");
			return false;
		}
		if(downloadTable=="fileSelected"&&selects.length>1){
			alert("只能选择一条");
			return false;
		}
		var fileNames = $.map(selects, function(row) {
			return row.file_name;
		});
		fileNames = encodeURI(fileNames);
		$.ajax({
			type:"get",
		//    url:"exportByTemplate.do?fileNames="+fileNames,//合并导出
		    url:url,//合并导出
		    contentType: 'application/json',
		    dataType:'json',
		    data:{temp_id:temp_id,fileNames:fileNames,files_start:files_start,files_rows:files_rows},
		    success:function(json){
		    },
		    error:function(){
		    	alert("错误");
		    	return;
		    }
		})
	}
</script>
<title>UpLoadFileTOService</title>
</head>
<body>
	<div id="wrapper">
		<!-- Page Content -->
		<div id="page-wrapper">
			<!-- /.row -->
			<div class="row">
				<!-- /.col-lg-6 -->
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
						<button class="btn btn-primary btn-sm" id="exportfiles">
									<span class="glyphicon glyphicon-download"></span> 下载
								</button>
						</div>
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<ul id="myTab" class="nav nav-tabs">
									<li class="active"><a href="#sourseFiles" data-toggle="tab">上传多个文件</a></li>
									<li><a href="#sourseFilesAndModels" data-toggle="tab" onClick="filesAndModels()">上传子模板及文件</a></li>
								</ul>
								<div id="myTabContent" class="tab-content">
									<div class="tab-pane fade in active" id="sourseFiles">
									<div class="form-group">
										<label>请选择模板</label>
										<select id="models" class="form-control" name="models">
										</select></div>
										<div class="form-group">
										<button class="btn btn-primary btn-sm" data-toggle="modal"
											data-target="#files_upload">
											<span class="glyphicon glyphicon-plus"></span> 请选择文件
										</button>
										</div>
										<table id="fileSelected"></table>
									</div>
									<div class="tab-pane fade" id="sourseFilesAndModels">
										<div class="tab-pane fade in active" id="sourseFiles">
											<label>对应模板</label>
											<select class="form-control" id="upTemp_id">
											</select>
											<label>请选择文件</label>
											<input type="file" name="files" id="choosefile1" /> 
											<label>请选择模板</label>
											<input type="file" id="modelsC"  name="files"/>
											<table id="fileSelected1"></table> 
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.col-lg-6 -->
			</div>
			<!-- /.row -->
			<!-- =============================单一模板上传文件============================== -->
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="files_upload" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">文件上传</h4>
				</div>
				<div class="modal-body">
					 <label>标题起终点坐标(如:a1-h1)</label><input
									class="form-control" id="file_start" name="file_start" /> 
									<div class="form-group">
									<label>行数</label><input
									class="form-control" id="file_rows" name="file_rows" />
									</div>
								<div class="form-group">
									<input type="file" name="files" id="choosefile" /> 
								</div>
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
	<!-- =============================单一模板上传文件完============================== -->
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
</div>
</body>
</html>