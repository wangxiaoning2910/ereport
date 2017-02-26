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
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap-table/bootstrap-table.css' />">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css' />">
		<!-- jQuery -->
		<script type="text/javascript" src="<c:url value='/resources/script/jquery-1.11.1.min.js' />"></script>
		<!-- Bootstrap Core JavaScript -->
		<script type="text/javascript" src="<c:url value='/resources/script/bootstrap.min.js' />"></script>
		<!-- Custom Theme JavaScript -->
		<script type="text/javascript" src="<c:url value='/resources/script/sb-admin-2.js' />"></script>
		<!-- Metis Menu Plugin JavaScript -->
    	<script type="text/javascript" src="<c:url value='/resources/script/metisMenu.min.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/bootstrap-table/bootstrap-table.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/script/jquery.validate.min.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/script/appscript/downLoadFile.js' />"></script>
		<script src="../resources/bootstrap-table/bootstrap-table-zh-CN.js"></script>
		<title>UpLoadFileTOService</title>
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
	                        	<input type="text"  id="datetimepicker" name="datetimepicker" >
	                        	<button type="button" onclick="queryList()" class="btn btn-default btn-sm">
          							<span class="glyphicon glyphicon-search"></span> 查询
        						</button>
        						<form role="form"  id="exportfileform">
		                        	<input type="hidden"  name="filename" id="filename">
		                        	<input type="hidden"  name="ymounth" id="ymounth">
		                        </form>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-lg-12">
	                    <div class="panel panel-default">
	                        <div class="panel-heading">
	                        </div>
	                        <!-- /.panel-heading -->
	                        <div class="panel-body">
	                        	<button class="btn btn-primary btn-sm" id="downloadfiles">
									<span class="glyphicon glyphicon-download"></span> 下载
								</button>
	                        	<table id="uploadTable"></table>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
    	</div>
    	<div class="modal fade" id="InsertNewMerch" tabindex="-1" role="dialog" 
    			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" >
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">本月新增委托单位，勾选自营</h4>
					</div>
					<div class="modal-body">
						<table id="choosemerch" ></table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" id="insertdata" class="btn btn-primary">提交</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<div class="modal fade" id="InsertNewMerch1" tabindex="-1" role="dialog" 
    			aria-labelledby="myModalLabel1" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">本年新增委托单位，勾选自营</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<table id="choosemerch1"></table>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" id="insertdata1" class="btn btn-primary">提交</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</body>
</html>