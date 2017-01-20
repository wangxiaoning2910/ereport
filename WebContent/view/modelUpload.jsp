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
	href="<c:url value='/resources/bootstrap-fileinput/css/fileinput.css' />">
<%-- <link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/bootstrap-editable/bootstrap-editable.css' />">  --%>
<link rel="stylesheet" type="text/css" href="../resources/bootstrap-editable/bootstrap-editable.css" rel="stylesheet" /> 


<script>
	$(document).ready(function() {
		//查询模板
		 $("#template").bootstrapTable({
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
		            field: 'temp_id',
		            title: '模板编号',
		            align: 'center',
		        }, {
		            field: 'temp_name',
		            title: '模板说明',
		            align: 'center'
		        }, {
		            field: 'create_time',
		            title: '创建时间',
		            align: 'center'
		        }, {
		            field: 'version',
		            title: '版本号',
		            align: 'center',
		        },{
		    		title: '模板类型',
		    		field: 'temp_type',
		    		align: 'center',
		    		formatter:function(value,row,index){
				  		if(value == "0"){
				  			return "单一模板";
				  		}else if(value == "1"){
				  			return "复合模板";
				  		}else if(value == "2"){
				  			return "复合子模板";
				  		}
					}
		        },{
		    		title: '规则',
		    		field: '#',
		    		align: 'center',
		    		formatter:function(value,row,index){
				  		if(row.temp_type == "2"){
				  			return "无";
				  		}
					}
		        }],
		    	onCheck:function(row){
		    	},
		    	onDblClickRow:onDblClickRow
			});
		 		queryTemplate();
			
	})
	function queryTemplate(){
		var data;
		$.ajax({
			type:"get",
		    url:"queryTemplate.do?t=0",//查询全部模板
		    contentType: 'application/json',
		    dataType:'json',
		    success:function(json){
		    	data = json.list;
		        $("#template").bootstrapTable('load',data);
		    },
		    error:function(){
		    	alert("错误");
		    	return;
		    }
		})
		return data;
	}
	function onDblClickRow(row){
		//模板类型
		var temp_type = row.temp_type;
		//以下变量是给源模板以及源模板列获取值
		  var data;
		  var dataR;
	        var result = [];
	        var resultR = [];
	        $.ajax({
	            url: 'queryTemplate.do?t=3&temp_id='+row.temp_id+'&version='+row.version,
	            type: "get",
	            async:false,
	            contentType: 'application/json',
			    dataType:'json',
	            success: function (json) {
	            	 data = json.list;
	            	 dataR = json.list1;
	            	$.each(data,function(key,value){
	            		result.push({ value: value.temp_id, text: value.temp_id });
//	               		console.log({ value: key, text: value.temp_id });
	            	});
	            	$.each(dataR,function(key,value){
	            		resultR.push({ value: key, text: value.temp_id+'.'+value.loc_name});
	            	});
	            }
	        });
		$('#model_detail').modal('show');
		$("#template_detail").bootstrapTable('destroy'); 
		$("#template_detail").bootstrapTable({
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
	            field: 'temp_id',
	            title: '模板编号',
	            align: 'center'
	        }, {
	            field: 'loc_num',
	            title: '序号',
	            align: 'center'
	        }, {
	            field: 'location',
	            title: '列位置',
	            align: 'center'
	        }, {
	            field: 'loc_name',
	            title: '列名称',
	            align: 'center'
	        },{
	    		field: 'formula',
	    		title: '列公式',
	    		align: 'center',
	    		editable: {
                    type: 'text',
                    validate: function (v) {
//                        if (isNaN(v)) return '年龄必须是数字';
  //                      var age = parseInt(v);
    //                    if (age <= 0) return '年龄必须是正整数';
                    }
                }
	        },{
	    		field: 'type',
	    		title: '计算类型',
	    		align: 'center',
	        },{
	    		field: 'Temp_idC',
	    		title: '源模板',
	    		align: 'center',
	    		editable: {
                    type: 'checklist',
               //   title: '源模板',
                    source: result
                }
	        },{
	    		field: 'Temp_rowC',
	    		title: '源模板列',
	    		align: 'center',
	    		editable: {
                    type: 'select',
                    source: resultR
                }
                /* formatter: function (value, row, index) {
                    return "<a href=\"#\" name=\"Temp_rowC\" data-type=\"select\" data-pk='1' > </a>";
                } */
                
	        },{
	    		field: 'version',
	    		title: '版本号',
	    		align: 'center',
	        },{
	    		field: 'is_extend',
	    		title: '是否扩展列',
	    		align: 'center',
	        }],
	    	onCheck:function(row){
	    	},
	    	//四个参数 当前列的名称、当前行数据对象、更新前的值、编辑的当前单元格的jQuery对象
	    	onEditableSave: function (field, row, oldValue, $el) {
	    		if(field=="Temp_idC"){//查询源模板中的列
	    			if(temp_type=="0"||temp_type=="2"){//如果是单一模板和子模板，保存不改变
	    				return false;
	    			}
	    		       
	    		}
	    		if(field=="formula"){
	    			if(temp_type=="1"||temp_type=="2"){//如果是单一模板和子模板，保存不改变
	    				return false;
	    			}
	    			var temp_id = row.temp_id;
	    			var version = row.version;
	    			var loc_num = row.loc_num;
	    			var formula = row.formula;
	    			console.log(temp_id+version+loc_num);
	    			$.ajax({
	    				type:"get",
	    			    url:"addTemplateFormula.do",
	    			    contentType: 'application/json',
	    			    dataType:'json',
	    			    data:{temp_id:temp_id,version:version,loc_num:loc_num,formula:formula},
	    			    success:function(json){
	    			        alert(123);
	    			    },
	    			    error:function(){
	    			    	alert("错误");
	    			    	return;
	    			    }
	    			})
	    		}
            }
		});
		var temp_id = row.temp_id;
		var version = row.version;
			var data;
			$.ajax({
				type:"get",
			    url:"queryTemplateDetail.do",
			    contentType: 'application/json',
			    dataType:'json',
			    data:{temp_id:temp_id,version:version},
			    success:function(json){
			    	data = json.list1;
			        $("#template_detail").bootstrapTable('load',data);
			    },
			    error:function(){
			    	alert("错误");
			    	return;
			    }
			})
			return data;
	}
	function upload(){
		//上传模板
		$("#choosefile").fileinput({
			language : 'zh', //设置语言
			uploadUrl : "ModelUpload.do?t=1", //上传的地址
			browseLabel : "选择文件",
			removeLabel : "删除",
			uploadLabel : "提交",
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
                var temp_id = document.getElementById('temp_id').value;
                var temp_name = document.getElementById('temp_name').value;
                var temp_start = document.getElementById('temp_start').value;
                var tempType = $('input:radio[name="tempType"]:checked').val();
                var temp_rows = document.getElementById('temp_rows').value;
                return {"temp_id": temp_id,
                	"temp_name":temp_name,
                	"temp_start":temp_start,
                	"tempType":tempType,
                	"temp_rows":temp_rows
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
			});
			
	}
//	<!--清除模态框数据=====================================================================-->
	$(function() {
		$('#model_add').on('hide.bs.modal', function() {
		//	alert('嘿，我听说您喜欢模态框...');
			$(this).removeData("bs.modal");
		})
	});
	function uploadC() {
		//上传模板
		$("#choosefileC").fileinput({
			language : 'zh', //设置语言
			uploadUrl : "ModelUpload.do?t=2", //上传的地址
			browseLabel : "选择文件",
			removeLabel : "删除",
			uploadLabel : "提交",
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
			uploadExtraData : function() {
				var upTemp_id = document.getElementById('upTemp_id').value;
				var temp_id = document.getElementById('temp_idC').value;
				var temp_start = document.getElementById('temp_startC').value;
				var temp_name = document.getElementById('temp_nameC').value;
				var temp_rows = document.getElementById('temp_rowsC').value;
				return {
					"upTemp_id" : upTemp_id,
					"temp_id" : temp_id,
					"temp_start" : temp_start,
					"temp_name" : temp_name,
					"temp_rows" : temp_rows
				};
			}
		});
		//异步上传失败返回结果处理
		$('#choosefileC').on('fileerror', function(event, data, msg) {
			console.log(data)
			console.log(event)
			console.log(msg)
		});
		//异步上传成功返回结果处理
		$("#choosefileC").on("fileuploaded",
				function(event, data, previewId, index) {
					console.log(data.response);
					$('#modelC_add').modal('hide');
				});
		var data;
		$.ajax({
			type : "get",
			url : "queryTemplate.do?t=1",//只查询复合模板
			contentType : 'application/json',
			dataType : 'json',
			success : function(json) {
				data = json.list;
				for (var i = data.length - 1; i >= 0; i--) {
					$("#upTemp_id").append(
							"<option value='" + data[i].temp_id + "'>"
									+ data[i].temp_id + "</option>");
				}
			},
			error : function() {
				alert("错误");
				return;
			}
		})
		return data;
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
							<button class="btn btn-primary btn-sm" data-toggle="modal"
								data-target="#model_add" onClick="upload()">
								<span class="glyphicon glyphicon-plus"></span> 上传模板
							</button>
							<button class="btn btn-primary btn-sm" data-toggle="modal"
								data-target="#modelC_add" onClick="uploadC()">
								<span class="glyphicon glyphicon-plus"></span> 上传子模板
							</button>
						</div>
						<div class="panel-body">
							<table id="template"></table>
						</div>
					</div>
				</div>
				<!-- /.col-lg-6 -->
			</div>
			<!-- /.row -->
			<!-- =========================上传模板模态框========================================= -->
			<div class="modal fade" id="model_add" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">上传模板</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
                                <label class="radio-inline">
                                    <input type="radio" name="tempType" id="tempType1" value="0" checked>单一模板
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="tempType" id="tempType2" value="1">复合模板
                                </label>
                             </div>
								<label>模板编号</label> <input class="form-control" id="temp_id"
									name="temp_id" /> <label>模板说明</label> <input
									class="form-control" id="temp_name" name="temp_name" />
									 <label>标题起终点坐标(如:a1-h1)</label><input
									class="form-control" id="temp_start" name="temp_start" /> 
									<div class="form-group">
									<label>行数</label><input
									class="form-control" id="temp_rows" name="temp_rows" />
									</div>
								<div class="form-group">
									<input type="file" name="templateFile" id="choosefile" />
								</div>
						</div>
						<!-- </form> -->
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>
			<!-- =========================================上传子模板==================================================== -->
			<div class="modal fade" id="modelC_add" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true" >
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">上传子模板</h4>
						</div>
						<div class="modal-body">
							<label>模板编号</label> <input class="form-control" id="temp_idC"
								name="temp_idC" /> <label>模板说明</label> <input
								class="form-control" id="temp_nameC" name="temp_nameC" /> <label>标题起终点坐标(如:a1-h1)</label><input
								class="form-control" id="temp_startC" name="temp_startC" />
							<label>行数</label><input
									class="form-control" id="temp_rowsC" name="temp_rowsC" />
							<div class="form-group">
								<label>对应模板</label>
								<select class="form-control" id="upTemp_id">
								</select>
							</div>
							<div class="form-group">
								<input type="file" name="templateFile" id="choosefileC" />
							</div>
						</div>
						<!-- </form> -->
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>
			<!-- ====================================查看模板明细============================================== -->
			<div class="modal fade" id="model_detail" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" style="width:1000px;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">模板明细</h4>
						</div>
						<div class="modal-body">
						<table id="template_detail"></table>
 						</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
</div>
</body>
</html>