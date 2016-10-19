<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ytincl.ereport.pojo.*" %>
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
<!--  ${rset.depTypeResult[0].savetype_no}-->

	<table class="table table-bordered">
	
		<!--<form enctype="multipart/form-data" action="upLoadFile.do" method="post">--> 
		<form name="form1" action="view/upLoadFile.jsp" method="post">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
					     <div class="panel-heading">
					           <span class="glyphicon glyphicon-upload"></span> 
					               <a href="ereportname.jsp">重新上传文件</a>									
					      </div>
					           <!-- /.panel-body -->
					 </div>
				 </div>
			</div>
		</form>
		
		<caption style="text-align:center">中国邮政储蓄银行黑龙江省分行储蓄业务完成情况--地市</caption>
		<thead>
			<tr>
				<th data-field="单位" rowspan="3" style="text-align:left;vertical-align:middle">单位</th>
				<th colspan="13" valign="middle" style="text-align:center" >全口径</th>
				<th colspan="13" valign="middle" style="text-align:center" >银行</th>
				<th data-field="年累计时点净增额自营占比" rowspan="3" style="text-align:left;vertical-align:middle">年累计时点净增额自营占比</th>
				<th data-field="年累计时点净增额计划完成比" rowspan="3" style="text-align:left;vertical-align:middle">年累计时点净增额计划完成比</th>
				<th data-field="排名" rowspan="3" style="text-align:left;vertical-align:middle">排名</th>
				<th data-field="年累计日均净增额自营占比" rowspan="3" style="text-align:left;vertical-align:middle">年累计日均净增额自营占比</th>
				<th data-field="年累计日均净增额计划完成比" rowspan="3" style="text-align:left;vertical-align:middle">年累计日均净增额计划完成比</th>
				<th data-field="排名" rowspan="3" style="text-align:left;vertical-align:middle">排名</th>
				
			</tr>
			
			<tr>
				<th data-field="时点余额（亿元）" rowspan="2" style="text-align:left;vertical-align:middle">时点余额（亿元）</th>
				<th data-field="活期余额占比" rowspan="2" style="text-align:left;vertical-align:middle">活期余额占比</th>
				<th data-field="本月日均余额（亿元）" rowspan="2" style="text-align:left;vertical-align:middle">本月日均余额（亿元）</th>
				<th data-field="年累计日均余额（亿元）" rowspan="2" style="text-align:left;vertical-align:middle">年累计日均余额（亿元）</th>
				<th data-field="账户数(万户)" rowspan="2" style="text-align:left;vertical-align:middle">账户数(万户)</th>
				<th colspan="4" valign="middle" style="text-align:center;vertical-align:middle" >本月</th>
				<th colspan="4" valign="middle" style="text-align:center;vertical-align:middle" >年累计</th>
				
				<th data-field="时点余额（亿元）" rowspan="2" style="text-align:left;vertical-align:middle">时点余额（亿元）</th>
				<th data-field="活期余额占比" rowspan="2" style="text-align:left;vertical-align:middle">活期余额占比</th>
				<th data-field="本月日均余额（亿元）" rowspan="2" style="text-align:left;vertical-align:middle">本月日均余额（亿元）</th>
				<th data-field="年累计日均余额（亿元）" rowspan="2" style="text-align:left;vertical-align:middle">年累计日均余额（亿元）</th>
				<th data-field="账户数(万户)" rowspan="2" style="text-align:left;vertical-align:middle">账户数(万户)</th>
				<th colspan="4" valign="middle" style="text-align:center;vertical-align:middle" >本月</th>
				<th colspan="4" valign="middle" style="text-align:center;vertical-align:middle" >年累计</th>
				
				
			</tr>
			
			<tr>
				<th>时点净增额（万元）</th>
				<th>同比增量（万元）</th>
				<th>比上月月日均余额增长（万元））</th>
				<th>同比增量（万元</th>
				
				<th>时点净增额（万元）</th>
				<th>同比增量（万元）</th>
				<th>比上月月日均余额增长（万元））</th>
				<th>同比增量（万元</th>
				
				<th>时点净增额（万元）</th>
				<th>同比增量（万元）</th>
				<th>比上月月日均余额增长（万元））</th>
				<th>同比增量（万元</th>
				
				<th>时点净增额（万元）</th>
				<th>同比增量（万元）</th>
				<th>比上月月日均余额增长（万元））</th>
				<th>同比增量（万元</th>			
			</tr>
			<%
			FindFileSet rset = (FindFileSet)request.getAttribute("rset");
			for (int i = 0;i <15;i++){
				//alert("qweqweqweqweqw");
				if(i==0){	
			%>
					<tr>
					<th data-field="定期" rowspan="1" style="text-align:left;vertical-align:middle">全省</th>
					
			<%
			    }
				if(i==1){   
			%>					
					<tr>					
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">哈尔滨</th>

			<%
				}
				if(i==2){   
			%>
									
					<tr>
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">佳木斯</th>
										
			<%
				}
						
				if(i==3){   
			%>
									
					<tr>
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">绥化</th>							
							
			<%
				}
						
				if(i==4){   
			%>
									
					<tr>
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">牡丹江</th>
							
			<%
				}
						
						
				if(i==5){   
			%>
									
					<tr>
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">齐齐哈尔</th>
							
			<%
				}
						
				if(i==6){   
			%>
									
					<tr>
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">大庆</th>
		
			<%
				}
				if(i==7){   
			%>
									
					<tr>
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">鸡西</th>
			
			<%
				}
						
				if(i==8){
			%>
					<tr>
					<th data-field="活期" rowspan="1" style="text-align:left;vertical-align:middle">双鸭山</th>

			<% 	  
				}
				if(i==9){
			%>
					<tr>					
					<th data-field="定活两便" rowspan="1" data-valign="middle" data-align="center">黑河</th>

							
			<% 	  
				}
				if(i==10){
			%>
					<tr>
					<th data-field="通知存款" rowspan="1" data-valign="middle" data-align="center">鹤岗</th>

							
			<% 
				}
				if(i==11){
			%>
					<tr>

					<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">伊春</th>
	
			<%
				 }
						
				if(i==12){
			%>
					<tr>

					<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">七台河</th>
		
			<%
				 }
						
				if(i==13){
			%>
					<tr>

					<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">大兴安岭</th>
							
			<%
				 }
				if(i==14){
			%>
					<tr>

					<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">省直属</th>
							
			<%
				}
										
			%>		

					<td><%=rset.getDepCityResult().get(i).getTime_money() %></td>
					<td><%=rset.getDepCityResult().get(i).getCurrentbalance_money() %></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money1() %></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money2() %></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money3() %></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money4() %></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money5() %></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money6() %></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money7() %></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money8() %></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money9() %></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money10() %></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money11() %></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money12() %></td>
					<td><%=rset.getDepCityResult().get(i).getCurrentbalance_money1() %></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money13() %></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money14()%></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money15()%></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money16()%></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money17()%></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money18()%></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money19()%></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money20()%></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money21()%></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money22()%></td>
					<td><%=rset.getDepCityResult().get(i).getTime_money23()%></td>
					<td><%=rset.getDepCityResult().get(i).getCurrentbalance_money2() %></td>
					<td><%=rset.getDepCityResult().get(i).getCurrentbalance_money3() %></td>
					<td><%=rset.getDepCityResult().get(i).getRanking_no() %></td>
					<td><%=rset.getDepCityResult().get(i).getCurrentbalance_money4() %></td>
					<td><%=rset.getDepCityResult().get(i).getCurrentbalance_money5() %></td>
					<td><%=rset.getDepCityResult().get(i).getRanking_no1() %></td>

				
									
				</tr>
			<% 
			}
			 %>
		</thead>	
	</table>
			











</body>
