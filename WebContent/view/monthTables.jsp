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
	src="<c:url value='/resources/bootstrap-table/bootstrap-table.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/bootstrap-table/bootstrap-table-zh-CN.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/script/jquery.validate.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/bootbox.min.js' />"></script>
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
	
		var data;
		var data2;
		var data3;
		var data4;
		$.ajax({
			type:"get",
		    url:"queryMonthTables.do",
		    contentType: 'application/json',
		    dataType:'json',
		    data:{monthTableName1:"fullaperture_month",monthTableName2:"fullaperture_total",monthTableName3:"selfoperate_month",monthTableName4:"selfoperate_total"},
		    success:function(json){
		    	data = json.list1;
		    	var html;
		    	for(var i = data.length - 1; i >= 0; i--){
		    		html+="<tr><td>"+data[i].institudeCode+"</td><td>"+data[i].institudeName+"</td><td>"+data[i].dailyAverageQuantity+"</td><td>"+data[i].ranking+"</td></tr>";
		    	}
		    	$("#tbody1").html(html);
		    	data2 = json.list2;
		    	var html2;
		    	for(var i = data2.length - 1; i >= 0; i--){
		    		html2+="<tr><td>"+data2[i].institudeCode+"</td><td>"+data2[i].institudeName+"</td><td>"+data2[i].dailyAverageQuantity+"</td><td>"+data2[i].ranking+"</td></tr>";
		    	}
		    	$("#tbody2").html(html2);
		    	data3 = json.list3;
		    	var html3;
		    	for(var i = data3.length - 1; i >= 0; i--){
		    		html3+="<tr><td>"+data3[i].institudeCode+"</td><td>"+data3[i].institudeName+"</td><td>"+data3[i].dailyAverageQuantity+"</td><td>"+data3[i].ranking+"</td></tr>";
		    	}
		    	$("#tbody3").html(html3);
		    	data4 = json.list4;
		    	var html4;
		    	for(var i = data4.length - 1; i >= 0; i--){
		    		html4+="<tr><td>"+data4[i].institudeCode+"</td><td>"+data4[i].institudeName+"</td><td>"+data4[i].dailyAverageQuantity+"</td><td>"+data4[i].ranking+"</td></tr>";
		    	}
		    	$("#tbody4").html(html4);
		    },
		    error:function(){
		    	alert("错误");
		    	return;
		    }
		})
		//下载
		$('#downloadfiles').click(function(){
	    	downloadFiles();
	    })
	});
	function downloadFiles(){
		/*	$('#MonthFilename').attr('value',MonthFilename);
	 	$('#ymounth').attr('value',date); */
		$('#exportFileMonth').attr('method','post');
		$("#exportFileMonth").attr("action", "exportFileMonth.do");
		$('#exportFileMonth').submit();
	}
	 $(function(){
	            $('#MonthFilename').attr('value',"fullaperture_month");
	        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
	            // 获取已激活的标签页的名称
	            var activeTab = $(e.target).text();
	           if(activeTab=="全口径积累"){
	            	$('#MonthFilename').attr('value',"fullaperture_total");
	            }else if(activeTab=="自营本月"){
	            	$('#MonthFilename').attr('value',"selfoperate_month");
	            }else if(activeTab=="自营积累"){
	            	$('#MonthFilename').attr('value',"selfoperate_total");
	            }
	        });
	    });
	 function getWaterMarkFileName(institution,operator){
		$.ajax({
			type:"POST",
		    url:"queryWaterMarkFileName.do",
		    data:{institution:institution,operator:operator},
		    dataType:'json',
		    success:function(data){
		    	var filename = data.filename;
		    	$('#table1').css('background','url(../resources/img/'+filename+')')
		    },
		    error:function(){
		    	bootbox.alert("错误", function () {
		    		//回调方法
		    	});
		    	return;
		    }
		})
	 }
	 $(getWaterMarkFileName("水印测试-机构","测试柜员"))
</script>
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
						<!-- <div class="panel-heading"></div> -->
						<div class="panel-body">
							<input type="text" id="datetimepicker" name="datetimepicker">
							<button type="button" 
								class="btn btn-primary btn-sm">
								<span class="glyphicon glyphicon-search"></span> 查询
							</button>
							<form role="form"  id="exportFileMonth">
		                        	<input type="hidden"  name="MonthFilename" id="MonthFilename">
		                        	<input type="hidden"  name="ymounth" id="ymounth">
		                        </form>
							<button class="btn btn-primary btn-sm" id="downloadfiles">
									<span class="glyphicon glyphicon-download"></span> DownLoad
								</button>
						</div>
						<!-- /.panel-body -->
					</div>
				</div>
				<!-- /.col-lg-6 -->
				<div class="col-lg-12">
					<div class="panel panel-default">
						<!-- <div class="panel-heading"></div> -->
						<!-- /.panel-heading -->
					<div class="panel-body">
					<div class="dataTable_wrapper">
						<ul id="myTab" class="nav nav-tabs">
							<li class="active"><a href="#fullaperture_month" data-toggle="tab">全口径本月</a></li>
							<li><a href="#fullaperture_total" data-toggle="tab">全口径积累</a></li>
							<li><a href="#selfoperate_month" data-toggle="tab">自营本月</a></li>
							<li><a href="#selfoperate_total" data-toggle="tab">自营积累</a></li>
						</ul>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane fade in active" id="fullaperture_month">
							<table id="table1" class="table  table-bordered ">
					 <thead>
						<tr>
							<th>机构代码</th>
							<th>机构名称</th>
							<th>日均保有量</th>
							<th>排名</th>
						</tr> 
					</thead>
					<tbody id="tbody1"></tbody>
					</table>
						</div>
						<div class="tab-pane fade" id="fullaperture_total">
							<table id="table1" class="table table-striped table-bordered table-hover">
					 <thead>
						<tr>
							<th>机构代码</th>
							<th>机构名称</th>
							<th>日均保有量</th>
							<th>排名</th>
						</tr> 
					</thead>
					<tbody id="tbody2"></tbody>
					</table>
						</div>
						<div class="tab-pane fade" id="selfoperate_month">
							<table id="table1" class="table table-striped table-bordered table-hover" >
					 <thead>
						<tr>
							<th>机构代码</th>
							<th>机构名称</th>
							<th>日均保有量</th>
							<th>排名</th>
						</tr> 
					</thead>
					<tbody id="tbody3"></tbody>
					</table>
						</div>
						<div class="tab-pane fade" id="selfoperate_total">
							<table id="table1" class="table table-striped table-bordered table-hover">
					 <thead>
						<tr>
							<th>机构代码</th>
							<th>机构名称</th>
							<th>日均保有量</th>
							<th>排名</th>
						</tr> 
					</thead>
					<tbody id="tbody4"></tbody>
					</table>
						</div>
					</div>
				</div>
				</div>
					</div>
					</div>
				</div>
			</div>
		</div>
</body>

</html>