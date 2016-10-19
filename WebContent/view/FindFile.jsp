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
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js' />"></script>
		
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
	                          <input id="date_selected" type="text" name="d11" runat="server" onfocus="selectMonth()" />
	                          <!--<input type="text"  id="datetimepicker" name="datetimepicker" > -->	                         
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
		var date = $('#date_selected').val();
		$.get('getereport.do', {date:date}, 
			function(data){$("#result0").html(data);
		});
	}


	// 日期框设计-只显示年月
    function selectMonth() {  
        WdatePicker({ dateFmt: 'yyyy-MM', isShowToday: false, isShowClear: false });  
    }  
	
</script>


