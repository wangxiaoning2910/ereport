<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>报表查询</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.min.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap-fileinput/css/fileinput.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css' />">
	<!-- MetisMenu CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/metisMenu.min.css' />">
	<!-- Custom CSS -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/sb-admin-2.css' />">
	<script type="text/javascript" src="<c:url value='/resources/script/jquery-1.11.1.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/bootstrap.min.js' />"></script>
	<script src="http://www.my97.net/dp/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-fileinput/js/fileinput.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/metisMenu.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/jquery.validate.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/uploadify/jquery.uploadify.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-table/bootstrap-table.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/appscript/findFile.js' />"></script>
</head>
<body>
	<form class="form-horizontal" role="form">
		<div class="row">
	    	<div class="col-lg-12">
	          	<div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title">报表数据查询</h4>
                   	</div>                                                        
	               	<div class="panel-body">	                     
						<input type="text"  id="datetimepicker" name="datetimepicker" >
                       	<button type="button" onclick="getReport()" class="btn btn-default btn-sm">
     						<span class="glyphicon glyphicon-search"></span> 查询
   						</button>   					  
	               	</div>              	              
	               <!-- /.panel-body -->
	           	</div>
	       	</div>
	       <!-- /.col-lg-12 -->
      
		</div>
    <!-- /.row -->
    

	</form>

 	<div id="result0" ></div>

</body>
</html>
	
<!-- 查询报表个数 -->
<script type="text/javascript">
	function getReport(){
		var date = $('#datetimepicker').val();
		date = date.replace("-","");
		$.get('getereport.do', {date:date}, 
			function(data){
			console.log(data)
				$("#result0").html(data);
		});
	}

	
</script>


