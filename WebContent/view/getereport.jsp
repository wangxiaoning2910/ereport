<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ytincl.ereport.pojo.*" %>

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
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-fileinput/js/fileinput.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/script/jquery.validate.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/uploadify/jquery.uploadify.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-table/bootstrap-table.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js' />"></script>
   
    <script type="text/javascript">
   		 function getDeppub(tablename){
			//alert(tablename);			
			if(tablename=="储蓄分储种情况"){

				getDeptype();
			}
			
			else if(tablename=="储蓄净增额完成情况--地市"){

				getDepCity();
			}
			
			else if(tablename=="储蓄净增额完成情况--县行"){

				getDepCounty();
			}
			
			else{
				alert("无相应的报表数据");
				
			}
		}   
    
    
    
		function getDeptype(){

			var date = $('#datetimepicker').val();
			date = date.replace("-", "");
			$.get('getDepType.do', {date:date}, 
				function(data){
				    $("#result").empty();
					$("#result").html(data);
				});
		}
		
		function getDepCity(){
			var date = $('#datetimepicker').val();
			date = date.replace("-", "");
			$.get('getDepCity.do', {date:date}, 
				function(data){
					$("#result").empty();
					$("#result").html(data);
				});
		}
		
		function getDepCounty(){
			var date = $('#datetimepicker').val();
			date = date.replace("-", "");
			$.get('getDepCounty.do', {date:date}, 
				function(data){
					$("#result").empty();
					$("#result").html(data);
				});
		}

		
	</script>
		
</head>
<body>

<table >
<ul id="myTab" class="nav nav-tabs">
		<%
		    GetEreportSet rset = (GetEreportSet)request.getAttribute("rset");
		    List<GetEreport> result = rset.getResult();
		    Iterator<GetEreport> iter = result.iterator();//迭代器
		    GetEreport obj;
            if(iter.hasNext()) {
            	obj = iter.next();
		%>
		
			<li class="active">
				<a href="#home"   onclick="getDeppub('<%=obj.getName() %>')" data-toggle="tab">
					<%=obj.getName() %>
				</a>	
			</li>
			
			 <script type="text/javascript">         
			            $(document).ready(function() {
			    			
			   			 getDeppub('<%=obj.getName() %>');
			   	        		
			   	    })
			   		
			</script>
			
			
		<%
            }    
            while(iter.hasNext()) {
		    	obj = iter.next();
       %>
       		    	
		<li>
			<a href="#home"   onclick="getDeppub('<%=obj.getName() %>')" data-toggle="tab">
				<%=obj.getName() %>
			</a>

		</li>
		
		<%
		}
		    
		%>
				
</ul>
	
</table>
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






