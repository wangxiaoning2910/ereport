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
   		
</head>
<body>

	<table class="table table-bordered">
	
		<form name="form1" action="view/upLoadFile.jsp" method="post">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
					     <div class="panel-heading">
					           <span class="glyphicon glyphicon-upload"></span> 
					               <a href="ereportname.jsp">重新上传文件</a>									
					      </div>
					 </div>
				 </div>
			</div>
		</form>
		<caption style="text-align:center">中国邮政储蓄银行黑龙江省分行储蓄分储种情况</caption>
		<thead>
			<tr>
				<th colspan="3" valign="middle" align="center" ></th>
				<th colspan="4" valign="middle" style="text-align:center" >全国口径</th>
				<th colspan="4" valign="middle" style="text-align:center" >银行自营</th>
			</tr>
				<tr>
				<th>储种</th>
				<th>存储方式</th>
				<th>期限</th>
				<th>本期结存户数（户）</th>
				<th>本期存款余额（亿元）</th>
				<th>存款占比%</th>
				<th>定比增幅%</th>
				<th>本期结存户数（户）</th>
				<th>本期存款余额（亿元）</th>
				<th>存款占比%</th>
				<th>定比增幅%</th>
			</tr>
			<%
			for (int i = 0;i <34;i++){
				//alert("qweqweqweqweqw");
				if(i==0){	
			%>
					<tr>
					<th data-field="定期" rowspan="30" style="text-align:left;vertical-align:middle">定期</th>
					<th data-field="定期" rowspan="8" style="text-align:left;vertical-align:middle">整存整取</th>
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">三个月</th>
					<td>${rset.depTypeResult[0].balance_number}</td>
					<td>${rset.depTypeResult[0].balance_money}</td>
					<td>${rset.depTypeResult[0].save_compare}</td>
					<td>${rset.depTypeResult[0].order_add}</td>
					<td>${rset.depTypeResult[0].balance_number1}</td>
					<td>${rset.depTypeResult[0].balance_money1}</td>
					<td>${rset.depTypeResult[0].save_compare1}</td>
					<td>${rset.depTypeResult[0].order_add1}</td>

			<%
			    }
				if(i==1){   
			%>					
					<tr>					
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">半年</th>
					<td>${rset.depTypeResult[1].balance_number}</td>
					<td>${rset.depTypeResult[1].balance_money}</td>
					<td>${rset.depTypeResult[1].save_compare}</td>
					<td>${rset.depTypeResult[1].order_add}</td>
					<td>${rset.depTypeResult[1].balance_number1}</td>
					<td>${rset.depTypeResult[1].balance_money1}</td>
					<td>${rset.depTypeResult[1].save_compare1}</td>
					<td>${rset.depTypeResult[1].order_add1}</td>

			<%
				}
				
				if(i==2){   
			%>
							
					<tr>
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">一年</th>
					<td>${rset.depTypeResult[2].balance_number}</td>
					<td>${rset.depTypeResult[2].balance_money}</td>
					<td>${rset.depTypeResult[2].save_compare}</td>
					<td>${rset.depTypeResult[2].order_add}</td>
					<td>${rset.depTypeResult[2].balance_number1}</td>
					<td>${rset.depTypeResult[2].balance_money1}</td>
					<td>${rset.depTypeResult[2].save_compare1}</td>
					<td>${rset.depTypeResult[2].order_add1}</td>
							
			<%
				}
				
				if(i==3){   
			%>
							
					<tr>
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">二年</th>
					<td>${rset.depTypeResult[3].balance_number}</td>
					<td>${rset.depTypeResult[3].balance_money}</td>
					<td>${rset.depTypeResult[3].save_compare}</td>
					<td>${rset.depTypeResult[3].order_add}</td>
					<td>${rset.depTypeResult[3].balance_number1}</td>
					<td>${rset.depTypeResult[3].balance_money1}</td>
					<td>${rset.depTypeResult[3].save_compare1}</td>
					<td>${rset.depTypeResult[3].order_add1}</td>
					
			<%
				}
				
				if(i==4){   
			%>
							
					<tr>
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">三年</th>
					<td>${rset.depTypeResult[4].balance_number}</td>
					<td>${rset.depTypeResult[4].balance_money}</td>
					<td>${rset.depTypeResult[4].save_compare}</td>
					<td>${rset.depTypeResult[4].order_add}</td>
					<td>${rset.depTypeResult[4].balance_number1}</td>
					<td>${rset.depTypeResult[4].balance_money1}</td>
					<td>${rset.depTypeResult[4].save_compare1}</td>
					<td>${rset.depTypeResult[4].order_add1}</td>
					
			<%
				}
				
				
				if(i==5){   
			%>
							
					<tr>
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">五年</th>
					<td>${rset.depTypeResult[5].balance_number}</td>
					<td>${rset.depTypeResult[5].balance_money}</td>
					<td>${rset.depTypeResult[5].save_compare}</td>
					<td>${rset.depTypeResult[5].order_add}</td>
					<td>${rset.depTypeResult[5].balance_number1}</td>
					<td>${rset.depTypeResult[5].balance_money1}</td>
					<td>${rset.depTypeResult[5].save_compare1}</td>
					<td>${rset.depTypeResult[5].order_add1}</td>
					
			<%
				}
				
				if(i==6){   
			%>
							
					<tr>
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">八年</th>
					<td>${rset.depTypeResult[6].balance_number}</td>
					<td>${rset.depTypeResult[6].balance_money}</td>
					<td>${rset.depTypeResult[6].save_compare}</td>
					<td>${rset.depTypeResult[6].order_add}</td>
					<td>${rset.depTypeResult[6].balance_number1}</td>
					<td>${rset.depTypeResult[6].balance_money1}</td>
					<td>${rset.depTypeResult[6].save_compare1}</td>
					<td>${rset.depTypeResult[6].order_add1}</td>
					
			<%
			    }
				if(i==7){   
			%>
							
					<tr>
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">小计</th>
					<td>${rset.depTypeResult[7].balance_number}</td>
					<td>${rset.depTypeResult[7].balance_money}</td>
					<td>${rset.depTypeResult[7].save_compare}</td>
					<td>${rset.depTypeResult[7].order_add}</td>
					<td>${rset.depTypeResult[7].balance_number1}</td>
					<td>${rset.depTypeResult[7].balance_money1}</td>
					<td>${rset.depTypeResult[7].save_compare1}</td>
					<td>${rset.depTypeResult[7].order_add1}</td>
					
			<%
				}
						
			    if(i==8){
			%>
					<tr>
					<th data-field="活期" rowspan="7" style="text-align:left;vertical-align:middle">整存整取协议存款</th>
					<th data-field="活期" rowspan="1" data-valign="middle" data-align="center">三个月</th>
					<td>${rset.depTypeResult[8].balance_number}</td>
					<td>${rset.depTypeResult[8].balance_money}</td>
					<td>${rset.depTypeResult[8].save_compare}</td>
					<td>${rset.depTypeResult[8].order_add}</td>
					<td>${rset.depTypeResult[8].balance_number1}</td>
					<td>${rset.depTypeResult[8].balance_money1}</td>
					<td>${rset.depTypeResult[8].save_compare1}</td>
					<td>${rset.depTypeResult[8].order_add1}</td>
						
			<% 	  
				  }
				if(i==9){
			%>
					<tr>					
					<th data-field="定活两便" rowspan="1" data-valign="middle" data-align="center">半年</th>
					<td>${rset.depTypeResult[9].balance_number}</td>
					<td>${rset.depTypeResult[9].balance_money}</td>
					<td>${rset.depTypeResult[9].save_compare}</td>
					<td>${rset.depTypeResult[9].order_add}</td>
					<td>${rset.depTypeResult[9].balance_number1}</td>
					<td>${rset.depTypeResult[9].balance_money1}</td>
					<td>${rset.depTypeResult[9].save_compare1}</td>
					<td>${rset.depTypeResult[9].order_add1}</td>
						
			<% 	  
				  }
				if(i==10){
			%>
					<tr>
					<th data-field="通知存款" rowspan="1" data-valign="middle" data-align="center">一年</th>
					<td>${rset.depTypeResult[10].balance_number}</td>
					<td>${rset.depTypeResult[10].balance_money}</td>
					<td>${rset.depTypeResult[10].save_compare}</td>
					<td>${rset.depTypeResult[10].order_add}</td>
					<td>${rset.depTypeResult[10].balance_number1}</td>
					<td>${rset.depTypeResult[10].balance_money1}</td>
					<td>${rset.depTypeResult[10].save_compare1}</td>
					<td>${rset.depTypeResult[10].order_add1}</td>
			<% 
				  }
				if(i==11){
			%>
					<tr>

					<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">二年</th>
					<td>${rset.depTypeResult[11].balance_number}</td>
					<td>${rset.depTypeResult[11].balance_money}</td>
					<td>${rset.depTypeResult[11].save_compare}</td>
					<td>${rset.depTypeResult[11].order_add}</td>
					<td>${rset.depTypeResult[11].balance_number1}</td>
					<td>${rset.depTypeResult[11].balance_money1}</td>
					<td>${rset.depTypeResult[11].save_compare1}</td>
					<td>${rset.depTypeResult[11].order_add1}</td>
						
			<%
				  }
				
				if(i==12){
			%>
					<tr>

					<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">三年</th>
					<td>${rset.depTypeResult[12].balance_number}</td>
					<td>${rset.depTypeResult[12].balance_money}</td>
					<td>${rset.depTypeResult[12].save_compare}</td>
					<td>${rset.depTypeResult[12].order_add}</td>
					<td>${rset.depTypeResult[12].balance_number1}</td>
					<td>${rset.depTypeResult[12].balance_money1}</td>
					<td>${rset.depTypeResult[12].save_compare1}</td>
					<td>${rset.depTypeResult[12].order_add1}</td>
						
			<%
				 }
				
				if(i==13){
			%>
					<tr>

					<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">五年</th>
					<td>${rset.depTypeResult[13].balance_number}</td>
					<td>${rset.depTypeResult[13].balance_money}</td>
					<td>${rset.depTypeResult[13].save_compare}</td>
					<td>${rset.depTypeResult[13].order_add}</td>
					<td>${rset.depTypeResult[13].balance_number1}</td>
					<td>${rset.depTypeResult[13].balance_money1}</td>
					<td>${rset.depTypeResult[13].save_compare1}</td>
					<td>${rset.depTypeResult[13].order_add1}</td>
						
			<%
				 }
				if(i==14){
			%>
					<tr>

					<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">小计</th>
					<td>${rset.depTypeResult[14].balance_number}</td>
					<td>${rset.depTypeResult[14].balance_money}</td>
					<td>${rset.depTypeResult[14].save_compare}</td>
					<td>${rset.depTypeResult[14].order_add}</td>
					<td>${rset.depTypeResult[14].balance_number1}</td>
					<td>${rset.depTypeResult[14].balance_money1}</td>
					<td>${rset.depTypeResult[14].save_compare1}</td>
					<td>${rset.depTypeResult[14].order_add1}</td>
						
			<%
				 }
				
				 if(i==15){
			%>
					<tr>
					<th data-field="活期" rowspan="4" style="text-align:left;vertical-align:middle">整存整取</th>
					<th data-field="活期" rowspan="1" data-valign="middle" data-align="center">一年</th>
					<td>${rset.depTypeResult[15].balance_number}</td>
					<td>${rset.depTypeResult[15].balance_money}</td>
					<td>${rset.depTypeResult[15].save_compare}</td>
					<td>${rset.depTypeResult[15].order_add}</td>
					<td>${rset.depTypeResult[15].balance_number1}</td>
					<td>${rset.depTypeResult[15].balance_money1}</td>
					<td>${rset.depTypeResult[15].save_compare1}</td>
					<td>${rset.depTypeResult[15].order_add1}</td>
						
			<% 	  
				 }
				if(i==16){
			%>
					<tr>					
						<th data-field="定活两便" rowspan="1" data-valign="middle" data-align="center">三年</th>
						<td>${rset.depTypeResult[16].balance_number}</td>
						<td>${rset.depTypeResult[16].balance_money}</td>
						<td>${rset.depTypeResult[16].save_compare}</td>
						<td>${rset.depTypeResult[16].order_add}</td>
						<td>${rset.depTypeResult[16].balance_number1}</td>
						<td>${rset.depTypeResult[16].balance_money1}</td>
						<td>${rset.depTypeResult[16].save_compare1}</td>
						<td>${rset.depTypeResult[16].order_add1}</td>
						
			<% 	  
				}
				if(i==17){
			%>
					<tr>
						<th data-field="通知存款" rowspan="1" data-valign="middle" data-align="center">五年</th>
						<td>${rset.depTypeResult[17].balance_number}</td>
						<td>${rset.depTypeResult[17].balance_money}</td>
						<td>${rset.depTypeResult[17].save_compare}</td>
						<td>${rset.depTypeResult[17].order_add}</td>
						<td>${rset.depTypeResult[17].balance_number1}</td>
						<td>${rset.depTypeResult[17].balance_money1}</td>
						<td>${rset.depTypeResult[17].save_compare1}</td>
						<td>${rset.depTypeResult[17].order_add1}</td>
						
			<% 
				}
				if(i==18){
			%>
					<tr>

					    	<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">小计</th>
					  	 	<td>${rset.depTypeResult[18].balance_number}</td>
							<td>${rset.depTypeResult[18].balance_money}</td>
							<td>${rset.depTypeResult[18].save_compare}</td>
							<td>${rset.depTypeResult[18].order_add}</td>
							<td>${rset.depTypeResult[18].balance_number1}</td>
							<td>${rset.depTypeResult[18].balance_money1}</td>
							<td>${rset.depTypeResult[18].save_compare1}</td>
							<td>${rset.depTypeResult[18].order_add1}</td>
			<%
				}
				
				if(i==19){
			%>
						<tr>
							<th data-field="活期" rowspan="4" style="text-align:left;vertical-align:middle">存本取息</th>
							<th data-field="活期" rowspan="1" data-valign="middle" data-align="center">一年</th>
							<td>${rset.depTypeResult[19].balance_number}</td>
							<td>${rset.depTypeResult[19].balance_money}</td>
							<td>${rset.depTypeResult[19].save_compare}</td>
							<td>${rset.depTypeResult[19].order_add}</td>
							<td>${rset.depTypeResult[19].balance_number1}</td>
							<td>${rset.depTypeResult[19].balance_money1}</td>
							<td>${rset.depTypeResult[19].save_compare1}</td>
							<td>${rset.depTypeResult[19].order_add1}</td>
			<% 	  
				}
				if(i==20){
			%>
						<tr>					
							<th data-field="定活两便" rowspan="1" data-valign="middle" data-align="center">三年</th>
							<td>${rset.depTypeResult[20].balance_number}</td>
							<td>${rset.depTypeResult[20].balance_money}</td>
							<td>${rset.depTypeResult[20].save_compare}</td>
							<td>${rset.depTypeResult[20].order_add}</td>
							<td>${rset.depTypeResult[20].balance_number1}</td>
							<td>${rset.depTypeResult[20].balance_money1}</td>
							<td>${rset.depTypeResult[20].save_compare1}</td>
							<td>${rset.depTypeResult[20].order_add1}</td>
							
			<% 	  
				}
				if(i==21){
			%>
						<tr>
							<th data-field="通知存款" rowspan="1" data-valign="middle" data-align="center">五年</th>
							<td>${rset.depTypeResult[21].balance_number}</td>
							<td>${rset.depTypeResult[21].balance_money}</td>
							<td>${rset.depTypeResult[21].save_compare}</td>
							<td>${rset.depTypeResult[21].order_add}</td>
							<td>${rset.depTypeResult[21].balance_number1}</td>
							<td>${rset.depTypeResult[21].balance_money1}</td>
							<td>${rset.depTypeResult[21].save_compare1}</td>
							<td>${rset.depTypeResult[21].order_add1}</td>
							
			<% 
				}
				if(i==22){
			%>
						<tr>

							 <th data-field="总计" rowspan="1" data-valign="middle" data-align="center">小计</th>
							 <td>${rset.depTypeResult[22].balance_number}</td>
							<td>${rset.depTypeResult[22].balance_money}</td>
							<td>${rset.depTypeResult[22].save_compare}</td>
							<td>${rset.depTypeResult[22].order_add}</td>
							<td>${rset.depTypeResult[22].balance_number1}</td>
							<td>${rset.depTypeResult[22].balance_money1}</td>
							<td>${rset.depTypeResult[22].save_compare1}</td>
							<td>${rset.depTypeResult[22].order_add1}</td>
							 
			<%
				}
				
				if(i==23){
			%>
						<tr>
							<th data-field="活期" rowspan="4" style="text-align:left;vertical-align:middle">整存零取</th>
							<th data-field="活期" rowspan="1" data-valign="middle" data-align="center">一年</th>
							<td>${rset.depTypeResult[23].balance_number}</td>
							<td>${rset.depTypeResult[23].balance_money}</td>
							<td>${rset.depTypeResult[23].save_compare}</td>
							<td>${rset.depTypeResult[23].order_add}</td>
							<td>${rset.depTypeResult[23].balance_number1}</td>
							<td>${rset.depTypeResult[23].balance_money1}</td>
							<td>${rset.depTypeResult[23].save_compare1}</td>
							<td>${rset.depTypeResult[23].order_add1}</td>
							
			<% 	  
				}
				if(i==24){
			%>
						<tr>					
							<th data-field="定活两便" rowspan="1" data-valign="middle" data-align="center">三年</th>
							<td>${rset.depTypeResult[24].balance_number}</td>
							<td>${rset.depTypeResult[24].balance_money}</td>
							<td>${rset.depTypeResult[24].save_compare}</td>
							<td>${rset.depTypeResult[24].order_add}</td>
							<td>${rset.depTypeResult[24].balance_number1}</td>
							<td>${rset.depTypeResult[24].balance_money1}</td>
							<td>${rset.depTypeResult[24].save_compare1}</td>
							<td>${rset.depTypeResult[24].order_add1}</td>
							
			<% 	  
				}
				if(i==25){
			%>
						<tr>
							<th data-field="通知存款" rowspan="1" data-valign="middle" data-align="center">五年</th>
							<td>${rset.depTypeResult[25].balance_number}</td>
							<td>${rset.depTypeResult[25].balance_money}</td>
							<td>${rset.depTypeResult[25].save_compare}</td>
							<td>${rset.depTypeResult[25].order_add}</td>
							<td>${rset.depTypeResult[25].balance_number1}</td>
							<td>${rset.depTypeResult[25].balance_money1}</td>
							<td>${rset.depTypeResult[25].save_compare1}</td>
							<td>${rset.depTypeResult[25].order_add1}</td>
							
			<% 
				}
				if(i==26){
			%>
						<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">小计</th>
							<td>${rset.depTypeResult[26].balance_number}</td>
							<td>${rset.depTypeResult[26].balance_money}</td>
							<td>${rset.depTypeResult[26].save_compare}</td>
							<td>${rset.depTypeResult[26].order_add}</td>
							<td>${rset.depTypeResult[26].balance_number1}</td>
							<td>${rset.depTypeResult[26].balance_money1}</td>
							<td>${rset.depTypeResult[26].save_compare1}</td>
							<td>${rset.depTypeResult[26].order_add1}</td>
							
			<%
				}
				
				
				if(i==27){
			%>
						<tr>
							<th data-field="活期" rowspan="1" style="text-align:left;vertical-align:middle">定额定期</th>
							<th data-field="活期" rowspan="1" data-valign="middle" data-align="center"></th>
							<td>${rset.depTypeResult[27].balance_number}</td>
							<td>${rset.depTypeResult[27].balance_money}</td>
							<td>${rset.depTypeResult[27].save_compare}</td>
							<td>${rset.depTypeResult[27].order_add}</td>
							<td>${rset.depTypeResult[27].balance_number1}</td>
							<td>${rset.depTypeResult[27].balance_money1}</td>
							<td>${rset.depTypeResult[27].save_compare1}</td>
							<td>${rset.depTypeResult[27].order_add1}</td>
							
			<% 	  
				}
				
				if(i==28){
			%>
						<tr>
							<th data-field="活期" rowspan="1" style="text-align:left;vertical-align:middle">保值储蓄</th>
							<th data-field="活期" rowspan="1" data-valign="middle" data-align="center"></th>
							<td>${rset.depTypeResult[28].balance_number}</td>
							<td>${rset.depTypeResult[28].balance_money}</td>
							<td>${rset.depTypeResult[28].save_compare}</td>
							<td>${rset.depTypeResult[28].order_add}</td>
							<td>${rset.depTypeResult[28].balance_number1}</td>
							<td>${rset.depTypeResult[28].balance_money1}</td>
							<td>${rset.depTypeResult[28].save_compare1}</td>
							<td>${rset.depTypeResult[28].order_add1}</td>
							
			<% 	  
				}
				
				if(i==29){
			%>
						<tr>
							<th data-field="活期" rowspan="1" style="text-align:left;vertical-align:middle">定期合计</th>
							<th data-field="活期" rowspan="1" data-valign="middle" data-align="center"></th>
							<td>${rset.depTypeResult[29].balance_number}</td>
							<td>${rset.depTypeResult[29].balance_money}</td>
							<td>${rset.depTypeResult[29].save_compare}</td>
							<td>${rset.depTypeResult[29].order_add}</td>
							<td>${rset.depTypeResult[29].balance_number1}</td>
							<td>${rset.depTypeResult[29].balance_money1}</td>
							<td>${rset.depTypeResult[29].save_compare1}</td>
							<td>${rset.depTypeResult[29].order_add1}</td>
							
			<% 	  
				}
				
				if(i==30){	
			%>
				 	    <tr>
						    <th data-field="定期" rowspan="1" style="text-align:left;vertical-align:middle">活期</th>
							<th data-field="定期" rowspan="1" style="text-align:left;vertical-align:middle"></th>
							<th data-field="定期" rowspan="1" data-valign="middle" data-align="center"></th>
							<td>${rset.depTypeResult[30].balance_number}</td>
							<td>${rset.depTypeResult[30].balance_money}</td>
							<td>${rset.depTypeResult[30].save_compare}</td>
							<td>${rset.depTypeResult[30].order_add}</td>
							<td>${rset.depTypeResult[30].balance_number1}</td>
							<td>${rset.depTypeResult[30].balance_money1}</td>
							<td>${rset.depTypeResult[30].save_compare1}</td>
							<td>${rset.depTypeResult[30].order_add1}</td>
			<%
				}
				
				
				if(i==31){	
			%>
						<tr>
							<th data-field="定期" rowspan="1" style="text-align:left;vertical-align:middle">定存两便</th>
							<th data-field="定期" rowspan="1" style="text-align:left;vertical-align:middle"></th>
							<th data-field="定期" rowspan="1" data-valign="middle" data-align="center"></th>
							<td>${rset.depTypeResult[31].balance_number}</td>
							<td>${rset.depTypeResult[31].balance_money}</td>
							<td>${rset.depTypeResult[31].save_compare}</td>
							<td>${rset.depTypeResult[31].order_add}</td>
							<td>${rset.depTypeResult[31].balance_number1}</td>
							<td>${rset.depTypeResult[31].balance_money1}</td>
							<td>${rset.depTypeResult[31].save_compare1}</td>
							<td>${rset.depTypeResult[31].order_add1}</td>
							
			<%
				}
				
				if(i==32){	
			%>
						<tr>
							<th data-field="定期" rowspan="1" style="text-align:left;vertical-align:middle">通知存款</th>
							<th data-field="定期" rowspan="1" style="text-align:left;vertical-align:middle"></th>
							<th data-field="定期" rowspan="1" data-valign="middle" data-align="center"></th>
							<td>${rset.depTypeResult[32].balance_number}</td>
							<td>${rset.depTypeResult[32].balance_money}</td>
							<td>${rset.depTypeResult[32].save_compare}</td>
							<td>${rset.depTypeResult[32].order_add}</td>
							<td>${rset.depTypeResult[32].balance_number1}</td>
							<td>${rset.depTypeResult[32].balance_money1}</td>
							<td>${rset.depTypeResult[32].save_compare1}</td>
							<td>${rset.depTypeResult[32].order_add1}</td>
							
			<%
				}
				
				if(i==33){	
			%>
						<tr>
							<th data-field="定期" rowspan="1" style="text-align:left;vertical-align:middle">总计</th>
							<th data-field="定期" rowspan="1" style="text-align:left;vertical-align:middle"></th>
							<th data-field="定期" rowspan="1" data-valign="middle" data-align="center"></th>
							<td>${rset.depTypeResult[33].balance_number}</td>
							<td>${rset.depTypeResult[33].balance_money}</td>
							<td>${rset.depTypeResult[33].save_compare}</td>
							<td>${rset.depTypeResult[33].order_add}</td>
							<td>${rset.depTypeResult[33].balance_number1}</td>
							<td>${rset.depTypeResult[33].balance_money1}</td>
							<td>${rset.depTypeResult[33].save_compare1}</td>
							<td>${rset.depTypeResult[33].order_add1}</td>
							
			<%
				}
	
			%>			    						    												
				</tr>
			<% 
			}
			 %>
		</thead>	
	</table>
			



</body>
