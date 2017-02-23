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
	<script type="text/javascript" src="../resources/script/appscript/createReport.js"></script>
	<script type="text/javascript" src="../resources/bootstrap-table/bootstrap-table-zh-CN.js"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/bootbox.min.js' />"></script>
	<title>自定义报表报表生成规则</title>
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
                       		<button type="button" id="new" class="btn btn-primary">新建</button>
                       	</div>
                    </div>
                </div>
                
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                        </div>
                        <div class="panel-body">
                        	<table id="rulestable"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
	</div>
	<div class="modal fade" id="createReport" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>报表名称</label>
						<input class="form-control" id="createName" name="createName"/>
						<label>数据来源</label><br/>
						<select class="form-control" id="selectreport" name="selectreport"></select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" id="next" class="btn btn-primary">下一步</button>
				</div>
			</div>
		</div>
	</div>
    
</body>
</html>





