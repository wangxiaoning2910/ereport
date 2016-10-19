<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>报表查询</title>
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap-fileinput/css/fileinput.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css' />">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="http://www.my97.net/dp/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-fileinput/js/fileinput.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/jquery.validate.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/uploadify/jquery.uploadify.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-table/bootstrap-table.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js' />"></script>
   
    <script type="text/javascript">
		function getDeptype(){
			var date = $('#date_selected').val();
			$.get('getDepType.do', {date:date}, 
				function(data){
				    $("#result").empty();
					$("#result").html(data);
				});
		}
		
		function getDepCity(){
			var date = $('#date_selected').val();
			$.get('getDepCity.do', {date:date}, 
				function(data){
					$("#result").empty();
					$("#result").html(data);
				});
		}
		
		function getDepCounty(){
			var date = $('#date_selected').val();
			$.get('getDepCounty.do', {date:date}, 
				function(data){
					$("#result").empty();
					$("#result").html(data);
				});
		}
		
		
		
	</script>
		
</head>
<body>


<ul id="myTab" class="nav nav-tabs">
		<li class="active">
			<a href="#home"  onclick="getDeptype()" data-toggle="tab">
				${rset.result[0].name}
			</a>

		</li>
	
		<li>

			<a href="#ios"  onclick="getDepCity()" data-toggle="tab">
				${rset.result[1].name}
			</a>
			
		</li>

		<li>		
			<a href="#jmeter"  onclick="getDepCounty()" data-toggle="tab">
				${rset.result[2].name}
			</a>
		</li>		
</ul>
	

<div id="myTabContent" class="tab-content">
		 <div class="tab-pane fade in active" id="home">
		      <p id ="result"></p>
		 </div>
		 <div id="result"></div>
</div>            
    
<div id="myTabContent" class="tab-content">
	<div class="tab-pane fade in active" id="home">
		<p></p>
	</div>
	<div class="tab-pane fade" id="ios">
		<p></p>
	</div>
	<div class="tab-pane fade" id="jmeter">
		<p></p>
	</div>
	
</div>
    
    
</body>
