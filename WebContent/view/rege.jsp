<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
<script type="text/javascript" src="resources/script/jquery1.9.1.js"></script>
<script type="text/javascript" src="resources/script/bootstrap.min.js"></script>
<title>welcome</title>
</head>
<body>
	<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="row clearfix">
				<div class="col-md-4 column">
				</div>
				<div class="col-md-4 column">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							 <label for="inputUsername" class="col-sm-3 control-label">用户名</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="inputUsername" />
							</div>
						</div>
						<div class="form-group">
							 <label for="inputPassword" class="col-sm-3 control-label">密码</label>
							<div class="col-sm-9">
								<input type="password" class="form-control" id="inputPassword" />
							</div>
						</div>
						
						
						<div class="col-sm-offset-3 col-sm-9">
							 <button type="button"  onclick="test()" class="btn btn-default">注册</button>
						</div>
						
					</form>
				</div>
				<div class="col-md-4 column">
				</div>
			</div>
		</div>
	</div>
	</div>
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
				console.log(data);
				if(data.succ == "true"){
					
				}
			}
		});
	}

</script>