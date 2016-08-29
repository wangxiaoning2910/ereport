<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>SB Admin 2 - Bootstrap Admin Theme</title>
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value='resources/css/bootstrap.min.css' />">
    <!-- MetisMenu CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value='resources/css/metisMenu.min.css' />">
    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value='resources/css/sb-admin-2.css' />">
    <!-- Custom Fonts -->
    <link rel="stylesheet" type="text/css" href="<c:url value='resources/css/font-awesome.min.css' />">
    
</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h2 class="form-signin-heading">欢迎登陆JSP</h2>
                    </div>
                    <div class="panel-body">
                        <form role="form"  id="loginform" method="POST" action="javascript:verifyUser();" >
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Your Name" name="inputUsername" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="inputPassword" type="password">
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <button class="btn btn-lg btn-success btn-block" type="submit">登录</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script type="text/javascript" src="<c:url value='resources/script/jquery-1.11.1.min.js' />"></script>
    <!-- Bootstrap Core JavaScript -->
    <script type="text/javascript" src="<c:url value='resources/script/bootstrap.min.js' />"></script>
    <!-- Metis Menu Plugin JavaScript -->
    <script type="text/javascript" src="<c:url value='resources/script/metisMenu.min.js' />"></script>
    <!-- Custom Theme JavaScript -->
    <script type="text/javascript" src="<c:url value='resources/script/sb-admin-2.js' />"></script>
    <script type="text/javascript" src="<c:url value='resources/script/jquery.validate.min.js' />"></script>
	<script type="text/javascript">
    	
		$().ready(function() {
			$("#loginform").validate({
				debug:false,
		    	rules: {
		      		inputUsername: "required",
		      		inputPassword: "required",
		      		inputUsername: {
		        		required: true,
		        		minlength: 3,
		        		success:function(){
		        			//可以检查用户是否存在
		        		}
		      		},
			      	inputPassword: {
			        	required: true,
			        	minlength: 5
			      	},
		    	},
			    messages: {
			     	inputUsername: {
			        	required: "请输入用户名",
			        	minlength: "用户名必需由3个字母组成"
			      	},
			      	inputPassword: {
			        	required: "请输入密码",
			        	minlength: "密码长度不能小于 5 个字母"
			      	}
			    }
		  	})
		});
		function verifyUser(){
			$.ajax({
				url: "verifyUser.do",
				type: "POST",
				dataType: 'json',
				data: $("#loginform").serialize(),
				async:true,
				success:function(data){
					console.log(data)
					var rspCode = data.rspCode;
					var rspMsg = data.rspMsg;
					if(rspCode != "100000"){
						alert(rspCode+"-"+rspMsg);
					}else{ 
						$("#loginform").attr("action","ereport.do").submit();
					}
				}
			});
		}
		
	</script>
</body>

</html>
