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
		
		<caption style="text-align:center">中国邮政储蓄银行黑龙江省分行储蓄业务完成情况--县行</caption>
		<thead>
		    <tr>
				<th colspan="3" valign="middle" style="text-align:center" >填报单位：</th>
				<th colspan="3" valign="middle" style="text-align:center" >个人金融</th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>
				<th colspan="1" valign="middle" style="text-align:center" ></th>					
			</tr>
			<tr>
				<th data-field="单位" rowspan="3" style="text-align:left;vertical-align:middle">单位</th>
				<th colspan="14" valign="middle" style="text-align:center" >全口径</th>
				<th colspan="14" valign="middle" style="text-align:center" >银行</th>				
			</tr>
			
			<tr>
				<th data-field="余额（亿元）" rowspan="2" style="text-align:left;vertical-align:middle">余额（亿元）</th>
				<th data-field="活期余额占比" rowspan="2" style="text-align:left;vertical-align:middle">活期余额占比</th>
				<th data-field="本月日均余额（亿元）" rowspan="2" style="text-align:left;vertical-align:middle">本月日均余额（亿元）</th>
				<th data-field="年累计日均余额（亿元）" rowspan="2" style="text-align:left;vertical-align:middle">年累计日均余额（亿元）</th>
				<th colspan="4" valign="middle" style="text-align:center;vertical-align:middle" >本月</th>
				<th colspan="6" valign="middle" style="text-align:center;vertical-align:middle" >年累计</th>
				
				<th data-field="时点余额（亿元）" rowspan="2" style="text-align:left;vertical-align:middle">余额（亿元）</th>
				<th data-field="活期余额占比" rowspan="2" style="text-align:left;vertical-align:middle">活期余额占比</th>
				<th data-field="本月日均余额（亿元）" rowspan="2" style="text-align:left;vertical-align:middle">本月日均余额（亿元）</th>
				<th data-field="年累计日均余额（亿元）" rowspan="2" style="text-align:left;vertical-align:middle">年累计日均余额（亿元）</th>
				<th colspan="4" valign="middle" style="text-align:center;vertical-align:middle" >本月</th>
				<th colspan="6" valign="middle" style="text-align:center;vertical-align:middle" >年累计</th>
				
				
			</tr>
			
			<tr>
				<th>时点净增额（万元）</th>
				<th>同比增量（万元）</th>
				<th>比上月月日均余额增长（万元））</th>
				<th>同比增量（万元</th>
				
				<th>时点净增额（万元）</th>
				<th>排名</th>
				<th>同比增量（万元）</th>
				<th>比上月月日均余额增长（万元））</th>
				<th>排名</th>
				<th>同比增量（万元</th>
				
				<th>时点净增额（万元）</th>
				<th>同比增量（万元）</th>
				<th>比上月月日均余额增长（万元））</th>				
				<th>同比增量（万元</th>
				
				<th>时点净增额（万元）</th>
				<th>排名</th>
				<th>同比增量（万元）</th>
				<th>比上月月日均余额增长（万元））</th>
				<th>排名</th>
				<th>同比增量（万元</th>			
			</tr>
			<%
			FindFileSet rset = (FindFileSet)request.getAttribute("rset");
			for (int i = 0;i <71;i++){
				//alert("qweqweqweqweqw");
				if(i==0){	
			%>
					<tr>
					<th data-field="定期" rowspan="1" style="text-align:left;vertical-align:middle">合计</th>
					
			<%
			    }
				if(i==1){   
			%>					
					<tr>					
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">呼兰</th>
			<%
				}
				if(i==2){   
			%>
									
					<tr>
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">依兰</th>
			<%
				}
						
				if(i==3){   
			%>
									
					<tr>
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">方正</th>
							
			<%
				}
						
				if(i==4){   
			%>
									
					<tr>
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">宾县</th>
			<%
				}
						
						
				if(i==5){   
			%>
									
					<tr>
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">巴彦</th>
			<%
				}
						
				if(i==6){   
			%>
									
					<tr>
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">木兰</th>
							
			<%
				}
				if(i==7){   
			%>
									
					<tr>
					<th data-field="定期" rowspan="1" data-valign="middle" data-align="center">通河</th>
							
			<%
				}
						
				if(i==8){
			%>
					<tr>
					<th data-field="活期" rowspan="1" style="text-align:left;vertical-align:middle">延寿</th>
					
							
			<% 	  
				}
				if(i==9){
			%>
					<tr>					
					<th data-field="定活两便" rowspan="1" data-valign="middle" data-align="center">阿城</th>
					
							
			<% 	  
				}
				if(i==10){
			%>
					<tr>
					<th data-field="通知存款" rowspan="1" data-valign="middle" data-align="center">双城</th>
					
							
			<% 
				}
				if(i==11){
			%>
					<tr>

					<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">尚志</th>
					
							
			<%
				 }
						
				if(i==12){
			%>
					<tr>

					<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">五常</th>
					
							
			<%
				 }
						
				if(i==13){
			%>
					<tr>

					<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">龙江</th>
				
							
			<%
				 }
				if(i==14){
			%>
					<tr>

					<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">依安</th>
	
							
			<%
			
				 }
				if(i==15){
			%>
					<tr>

					<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">泰来</th>
	
							
			<%
			
				}
				
				
				if(i==16){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">甘南</th>
			
									
					<%
					
						}
				if(i==17){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">富裕</th>
			
									
					<%
					
						}
				if(i==18){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">克山</th>
			
									
					<%
					
						}
				if(i==19){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">克东</th>
			
									
					<%
					
						}
				if(i==20){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">拜泉</th>
			
									
					<%
					
						}
				if(i==21){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">讷河</th>
			
									
					<%
					
						}
				if(i==22){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">鸡东</th>
			
									
					<%
					
						}
				if(i==23){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">虎林</th>
			
									
					<%
					
						}
				if(i==24){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">密山</th>
			
									
					<%
					
						}
				if(i==25){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">萝北</th>
			
									
					<%
					
						}
				if(i==26){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">绥滨</th>
			
									
					<%
					
						}
				if(i==27){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">集贤</th>
			
									
					<%
					
						}
				if(i==28){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">友谊</th>
			
									
					<%
					
						}
				if(i==29){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">宝清</th>
			
									
					<%
					
						}
				if(i==30){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">饶河</th>
			
									
					<%
					
						}
				if(i==31){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">肇州</th>
			
									
					<%
					
						}
				if(i==32){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">肇源</th>
			
									
					<%
					
						}
				if(i==33){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">林甸</th>
			
									
					<%
					
						}
				if(i==34){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">杜尔伯特</th>
			
									
					<%
					
						}
				if(i==35){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">嘉荫</th>
			
									
					<%
					
						}
				if(i==36){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">铁力</th>
			
									
					<%
					
						}
				if(i==37){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">桦南</th>
			
									
					<%
					
						}
				if(i==38){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">桦川</th>
			
									
					<%
					
						}
				if(i==39){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">汤源</th>
			
									
					<%
					
						}
				if(i==40){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">抚远</th>
			
									
					<%
					
						}
				if(i==41){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">同江</th>
			
									
					<%
					
						}
				if(i==42){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">富锦</th>
			
									
					<%
					
						}
				if(i==43){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">建三江</th>
			
									
					<%
					
						}
				if(i==44){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">勃利</th>
			
									
					<%
					
						}
				if(i==45){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">东宁</th>
			
									
					<%
					
						}
				if(i==46){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">林口</th>
			
									
					<%
					
						}
				if(i==47){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">绥芬河</th>
			
									
					<%
					
						}
				if(i==48){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">海林</th>
			
									
					<%
					
						}
				if(i==49){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">宁安</th>
			
									
					<%
					
						}
				if(i==50){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">穆棱</th>
			
									
					<%
					
						}
				if(i==51){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">嫩江</th>
			
									
					<%
					
						}
				if(i==52){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">逊克</th>
			
									
					<%
					
						}
				if(i==53){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">孙吴</th>
			
									
					<%
					
						}
				if(i==54){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">北安</th>
			
									
					<%
					
						}
				if(i==55){
					%>
					<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">五大连池</th>
			
									
					<%
					
						}
				if(i==56){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">望奎</th>
			
									
					<%
					
						}
				if(i==57){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">兰西</th>
			
									
					<%
					
						}
				if(i==58){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">青冈</th>
			
									
					<%
					
						}
				if(i==59){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">庆安</th>
			
									
					<%
					
						}
				if(i==60){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">明水</th>
			
									
					<%
					
						}
				if(i==61){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">绥棱</th>
			
									
					<%
					
						}
				if(i==62){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">安达</th>
			
									
					<%
					
						}
				if(i==63){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">肇东</th>
			
									
					<%
					
						}
				if(i==64){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">海伦</th>
			
									
					<%
					
						}
				if(i==65){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">呼玛</th>
			
									
					<%
					
						}
				if(i==66){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">塔河</th>
			
									
					<%
					
						}
				if(i==67){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">漠河</th>
			
									
					<%
					
						}
				if(i==68){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">呼中</th>
			
									
					<%
					
						}
				if(i==69){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">新林</th>
			
									
					<%
					
						}
				if(i==70){
					%>
							<tr>

							<th data-field="总计" rowspan="1" data-valign="middle" data-align="center">松岭</th>
			
									
					<%
					
						}
				 if(rset.getDepCountyResult().size() <= i) {
					%>
					       </tr>
					<%
							continue;
						}
					%>	
			
					        <td><%=rset.getDepCountyResult().get(i).getTime_money() %></td>                               
          					<td><%=rset.getDepCountyResult().get(i).getCurrentbalance_money()%></td>           
          					<td><%=rset.getDepCountyResult().get(i).getTime_money1() %></td>                    
          					<td><%=rset.getDepCountyResult().get(i).getTime_money2() %></td>                    
          					<td><%=rset.getDepCountyResult().get(i).getTime_money3() %></td>                    
          					<td><%=rset.getDepCountyResult().get(i).getTime_money4() %></td>                    
          					<td><%=rset.getDepCountyResult().get(i).getTime_money5() %></td>                    
          					<td><%=rset.getDepCountyResult().get(i).getTime_money6() %></td>                    
          					<td><%=rset.getDepCountyResult().get(i).getTime_money7() %></td>                    
          					<td><%=rset.getDepCountyResult().get(i).getRanking_no() %></td>                     
          					<td><%=rset.getDepCountyResult().get(i).getTime_money8() %></td>                    
          					<td><%=rset.getDepCountyResult().get(i).getTime_money9() %></td>                    
          					<td><%=rset.getDepCountyResult().get(i).getRanking_no1() %></td>                    
          					<td><%=rset.getDepCountyResult().get(i).getTime_money10() %></td>                   
          					<td><%=rset.getDepCountyResult().get(i).getTime_money11() %></td>                   
          					<td><%=rset.getDepCountyResult().get(i).getCurrentbalance_money1() %></td>					
          					<td><%=rset.getDepCountyResult().get(i).getTime_money12() %></td>                   
          					<td><%=rset.getDepCountyResult().get(i).getTime_money13() %></td>                   
          					<td><%=rset.getDepCountyResult().get(i).getTime_money14() %></td>                   
          					<td><%=rset.getDepCountyResult().get(i).getTime_money15() %></td>                   
          					<td><%=rset.getDepCountyResult().get(i).getTime_money16() %></td>                   
          					<td><%=rset.getDepCountyResult().get(i).getTime_money17() %></td>                   
          					<td><%=rset.getDepCountyResult().get(i).getTime_money18() %></td>                   
          					<td><%=rset.getDepCountyResult().get(i).getRanking_no2() %></td>                    
          					<td><%=rset.getDepCountyResult().get(i).getTime_money19() %></td>                   
          					<td><%=rset.getDepCountyResult().get(i).getTime_money20() %></td>                   
          					<td><%=rset.getDepCountyResult().get(i).getRanking_no3() %></td>                    
          					<td><%=rset.getDepCountyResult().get(i).getTime_money21() %></td>									
				</tr>
			<% 
			}
			 %>
		</thead>	
	</table>
			


</body>
