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
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-fileinput/js/fileinput.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/metisMenu.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/jquery.validate.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/uploadify/jquery.uploadify.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-table/bootstrap-table.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js' />"></script>
	
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
	
	
		
</head>
<body>
<!-- <form name="form1" action="getereport.do" method="post"> -->
 <form class="form-horizontal" role="form">
	 <div class="row">
	      <div class="col-lg-12">
	          <div class="panel panel-default">
	               <div class="panel-heading">
	               </div>
                    <div class="panel-heading">
                        <h4 class="panel-title">报表数据查询</h4>
                   </div>                                                        
	               <div class="panel-body">	                     
	                          <!--<input id="date_selected" type="text" name="d11" runat="server" onfocus="selectMonth()" />-->	
	                          <input type="text"  id="datetimepicker" name="datetimepicker" >                         
	                          <button type="button" onclick="getReport()" class="btn btn-default btn-sm">
          					        <span class="glyphicon glyphicon-search"></span> 查询
        					  </button>  
        					  
        					  
        					  <button type="button" onclick="makReport()" class="btn btn-default btn-sm">
          					        <span class="glyphicon glyphicon-ok"></span> 生成报表
        					  </button>        					    					  
	               </div>              	              
	               <!-- /.panel-body -->	              
	           </div>
	       </div>
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

	</form>

 	<div id="result0" ></div>

</body>
</html>


	
<!-- 查询报表个数 -->
<script type="text/javascript">
 
    $(document).ready(function() {		
         window.onload=getReport();		
    })
    
    //报表原始数据查询
	function getReport(){
		var date = $('#datetimepicker').val();
		date = date.replace("-","");
		$.get('getereport.do', {date:date}, 
			function(data){
				$("#result0").html(data);
		});
	}
    
    //生成报表
    function makReport(){   	
    	alert("报表生成成功");
		//var date = $("#datetimepicker").val();
		//date = date.replace("-","");
		//$.get('getereport.do', {date:date}, 
			//function(data){$("#result0").html(data);
		//});
	}

	// 日期框设计-只显示年月
    function selectMonth() {  
        WdatePicker({ dateFmt: 'yyyyMM', isShowToday: false, isShowClear: false });  
    }  
	
</script>


