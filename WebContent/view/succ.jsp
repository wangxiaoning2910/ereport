<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
<script type="text/javascript" src="resources/script/jquery1.9.1.js"></script>
<script type="text/javascript" src="resources/script/bootstrap.min.js"></script>
<title>succ</title>
</head>
<body>
	登录成功
</body>
</html>
<script type="text/javascript">
	function test(){
		var inputUsername = $('#inputUsername').val();
		var inputPassword = $('#inputPassword').val();
		$.ajax({
			type:'post',
			url:'regedit',
			data:{"inputUsername":inputUsername,"inputPassword":inputPassword},
			async:false,
			success:function(data){
				
			}
		});
	}

</script>