<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/resources/css/bootstrap-addtabs.css' />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/resources/css/bootstrap-treeview.min.css' />">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/custom.css' />">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/uploadify/uploadify.css' />">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap-table/bootstrap-table.css' />">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css' />">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap-fileinput/css/fileinput.css' />">
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
		<script type="text/javascript" src="<c:url value='/resources/script/jquery.validate.min.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/script/appscript/upLoadFile.js' />"></script>
		<script src="../resources/bootstrap-table/bootstrap-table-zh-CN.js"></script>
		<title>UpLoadFileTOService</title>
	</head>
	<body>
		<div id="wrapper">
	        <!-- Page Content -->
	        <div id="page-wrapper">
	            <div class="row">
	                <div class="col-lg-12">
	                  <!--  <h1 class="page-header">原始文件上传</h1> -->
	                </div>
	                <!-- /.col-lg-12 -->
	            </div>
	            <!-- /.row -->
	            <div class="row">
	                <div class="col-lg-12">
	                	<div class="panel panel-default">
	                    	<div class="panel-heading">
	                       	</div>
	                       	<div class="panel-body">
	                       		<input type="text"  id="datetimepicker" name="datetimepicker" >
	                        	<button type="button" onclick="queryList()" class="btn btn-default btn-sm">
          							<span class="glyphicon glyphicon-search"></span> 查询
        						</button>
	                       	</div>
	                        <!-- /.panel-body -->
	                    </div>
	                </div>
	                <!-- /.col-lg-6 -->
	                <div class="col-lg-12">
	                    <div class="panel panel-default">
	                        <div class="panel-heading">
	                        </div>
	                        <!-- /.panel-heading -->
	                        <div class="panel-body">
	                        	<input type="file" name="file" id="choosefile" />
	                        	<table id="uploadTable"></table>
	                        <!--<button type="button" id="file_upload"></button>-->
	                        <!-- 
	                            <div id="uploadfileQueue"></div>
	                            <button type="button" class="btn btn-default" onclick="javascript:$('#file_upload').uploadify('upload','*')" >上传</button>
								<button type="button" class="btn btn-default" onclick="javascript:$('#file_upload').uploadify('cancel','*')" >取消上传</button>
							-->
	                        </div>
	                        <!-- /.panel-body -->
	                    </div>
	                </div>
	                <!-- /.col-lg-6 -->
	            </div>
	            <!-- /.row -->
	        </div>
	        <!-- /#page-wrapper -->
    	</div>
    <!-- /#wrapper -->
	</body>
</html>