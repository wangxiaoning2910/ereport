<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>报表查询</title>
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="http://www.my97.net/dp/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	function getData(){
		var result;
		var parentmids = [];
		$.ajax({
			url:'ereportname.do',
			type:'post',
			async:false,
			dataType: 'json',
			data:{date:$("#d11").text(), name:$("#d22").val()},
			success:function(data){
				var blank = $("#data11").val() ;
				blank = data;
			}
		});
	}

	
	</script>
</head>
<body>
<form name="form1" action="ereportname.do" method="post">  
   <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Input FindDate</h3>
                    </div>
                    <div class="panel-body">
                            <fieldset>
                                <div class="form-group">
                                     <!--  <input type="text" name="d11" onclick="WdatePicker()">  -->
                                     <input class="form-control" type="text" name="d11" onclick="WdatePicker()" autofocus>                      
                                </div>                               
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="submit" class="btn btn-lg btn-success btn-block" value="提交" >
                            </fieldset>
                    </div>
                                <div class="navbar-default sidebar" role="navigation">                
                <!-- /.sidebar-collapse -->
            </div>
                </div>
            </div>
        </div>
    </div>
    


    <ul class="nav nav-pills">
	    <li class="active"><a href="#">Home</a></li>
	    <li class="dropdown">
		    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
			         业务类报表 <span class="caret"></span>
		    </a>
		    <ul class="dropdown-menu">
		    <!-- #代表本页，#换成 其他的地址，点击后就会跳转到相应的页面-->
			    <li><input type="checkbox" value="储蓄地市信息表" name="d22"> 储蓄地市信息表</input></li>
			    <li><input type="checkbox" value="储蓄储种信息表" name="d22"> 储蓄储种信息表</input></li>
			    <li><input type="checkbox" value="储蓄县行信息表" name="d22"> 储蓄县行信息表</input></li>
			    <li class="divider"></li>
		    </ul>
	   </li>
	   <li class="dropdown">
		   <a class="dropdown-toggle" data-toggle="dropdown" href="#">
			      理财类报表 <span class="caret"></span>
		   </a>
		   <ul class="dropdown-menu">
		        <li><input type="checkbox" value="理财类保有量信息表" name="d22">理财类保有量信息表 表</input></li>
			    <li><input type="checkbox" value="理财类国债信息表" name="d22">理财类国债信息表</input></li>
			    <li><input type="checkbox" value="理财类基金信息表" name="d22">理财类基金信息表</input></li>			    
			    <li class="divider"></li>
		   </ul>
	   </li>
    </ul>
      <div id="data11"></div> 
    <!--  <div>${obj.date}</div>   -->
</form> 
</body>
</html>