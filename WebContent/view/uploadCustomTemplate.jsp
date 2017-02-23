<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- Bootstrap Core CSS -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.min.css' />">
	<!-- MetisMenu CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/metisMenu.min.css' />">
	<!-- Custom CSS -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/sb-admin-2.css' />">
	<!-- Custom Fonts -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/font-awesome.min.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/custom.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/uploadify/uploadify.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap-table/bootstrap-table.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap-fileinput/css/fileinput.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/select2-4.0.3/css/select2.css' />" />
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/resources/bootstrap-addtabs/css/bootstrap.addtabs.css' />">
	<!-- jQuery -->
	<script type="text/javascript" src="<c:url value='/resources/script/jquery-1.11.1.min.js' />"></script>
	<!-- Bootstrap Core JavaScript -->
	<script type="text/javascript" src="<c:url value='/resources/script/bootstrap.min.js' />"></script>
	<!-- Custom Theme JavaScript -->
	<script type="text/javascript" src="<c:url value='/resources/script/sb-admin-2.js' />"></script>
	<!-- Metis Menu Plugin JavaScript -->
   	<script type="text/javascript" src="<c:url value='/resources/script/metisMenu.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/uploadify/jquery.uploadify.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-table/bootstrap-table.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-fileinput/js/fileinput.js' />"></script>
	<script type="text/javascript" src="../resources/select2-4.0.3/js/select2.full.js"></script>
	<script type="text/javascript" src="../resources/select2-4.0.3/js/i18n/zh-CN.js"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-addtabs/js/bootstrap.addtabs.js' />"></script>
	<script type="text/javascript" src="../resources/bootstrap-table/bootstrap-table-zh-CN.js"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/bootbox.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/appscript/uploadCustomTemplate.js' />"></script>
	
	<title>上传自定义模板</title>
</head>
<body>
	<div id="wrapper">
		 <!-- Page Content -->
        <div id="page-wrapper">
        	 <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                        </div>
                        <div class="panel-body">
                        	<div id="toolbar" class="btn-group">
                        		<button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addrow">
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
	</div>
	
	
	
	<div class="modal fade" id="addrow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">上传自定义模板</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>选择文件</label>
						<input type="file" name="ctfile" id="choosefile" />
					</div>
				</div>
				<div class="modal-footer">
				</div>
			</div>
		</div>
	</div>
</body>
</html>





