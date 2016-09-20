<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Bootstrap 实例 - 边框表格</title>
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<%@ page import="com.ytincl.ereport.pojo.*" %> 
	<%@ page import="java.util.*" %> 
</head>
<body>

<table class="table table-bordered">
<!--<caption>中国邮政储蓄银行xxx省分行储蓄分储种情况</caption>-->
	<caption style="text-align:center">中国邮政储蓄银行xxx省分行储蓄分储种情况</caption>
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
		<% FindFileSet res_qkj = (FindFileSet) request.getAttribute("objs_qkj");%>
			<% FindFileSet res_zy = (FindFileSet) request.getAttribute("objs_zy");%>
			<% List<FindFile> rset_qkj = res_qkj.getResult(); %>
			<% List<FindFile> rset_zy = res_zy.getResult(); %>
			<% Iterator<FindFile> iter_qkj = rset_qkj.iterator(); %>
			<% Iterator<FindFile> iter_zy = rset_zy.iterator(); %>
			<%! FindFile obj_qkj; %>
			<%! FindFile obj_zy; %>
			<!-- <% while(iter_qkj.hasNext()) {%> -->
			<%  obj_qkj = iter_qkj.next(); %>
			<%  obj_zy = iter_zy.next(); %>
	    <!-- 定期 -->
         <tr>
            <th data-field="定期" rowspan="2" style="text-align:left;vertical-align:middle">定期</th>
            <th data-field="定期" rowspan="1" data-valign="middle" data-align="center">整存整取</th>
            <th data-field="定期" rowspan="1" data-valign="middle" data-align="center">三个月</th>
			<td><%=obj_qkj.getJiecunhushu() %></td>
			<td><%=obj_qkj.getSave_balance_money() %></td>
			<td><%=obj_qkj.getSave_compare() %></td>
			<td><%=obj_qkj.getOrder_add() %></td>


			<td><%=obj_zy.getJiecunhushu() %></td>
			<td><%=obj_zy.getSave_balance_money() %></td>
			<td><%=obj_zy.getSave_compare() %></td>
			<td><%=obj_zy.getOrder_add() %></td>

	    </tr>
	    <%  obj_qkj = iter_qkj.next(); %>
		<%  obj_zy = iter_zy.next(); %>
	     <tr>
            <th data-field="定期" rowspan="1" data-valign="middle" data-align="center">零存零取</th>
            <th data-field="定期" rowspan="1" data-valign="middle" data-align="center">三个月</th>
			<td><%=obj_qkj.getJiecunhushu() %></td>
			<td><%=obj_qkj.getSave_balance_money() %></td>
			<td><%=obj_qkj.getSave_compare() %></td>
			<td><%=obj_qkj.getOrder_add() %></td>


			<td><%=obj_zy.getJiecunhushu() %></td>
			<td><%=obj_zy.getSave_balance_money() %></td>
			<td><%=obj_zy.getSave_compare() %></td>
			<td><%=obj_zy.getOrder_add() %></td>

	    </tr>
	    
	    <!-- 活期 -->
	    <%  obj_qkj = iter_qkj.next(); %>
	    <%  obj_zy = iter_zy.next(); %>
	    <tr>
            <th data-field="活期" rowspan="1" style="text-align:left;vertical-align:middle">活期</th>
            <th data-field="活期" rowspan="1" data-valign="middle" data-align="center"></th>
            <th data-field="活期" rowspan="1" data-valign="middle" data-align="center"></th>
			<td><%=obj_qkj.getJiecunhushu() %></td>
			<td><%=obj_qkj.getSave_balance_money() %></td>
			<td><%=obj_qkj.getSave_compare() %></td>
			<td><%=obj_qkj.getOrder_add() %></td>


			<td><%=obj_zy.getJiecunhushu() %></td>
			<td><%=obj_zy.getSave_balance_money() %></td>
			<td><%=obj_zy.getSave_compare() %></td>
			<td><%=obj_zy.getOrder_add() %></td>

	    </tr>
	    
	    <!-- 定活两便 -->
	    <%  obj_qkj = iter_qkj.next(); %>
	    <%  obj_zy = iter_zy.next(); %>
	    <tr>
            <th data-field="定活两便" rowspan="1" style="text-align:left;vertical-align:middle">定活两便</th>
            <th data-field="定活两便" rowspan="1" data-valign="middle" data-align="center"></th>
            <th data-field="定活两便" rowspan="1" data-valign="middle" data-align="center"></th>
			<td><%=obj_qkj.getJiecunhushu() %></td>
			<td><%=obj_qkj.getSave_balance_money() %></td>
			<td><%=obj_qkj.getSave_compare() %></td>
			<td><%=obj_qkj.getOrder_add() %></td>


			<td><%=obj_zy.getJiecunhushu() %></td>
			<td><%=obj_zy.getSave_balance_money() %></td>
			<td><%=obj_zy.getSave_compare() %></td>
			<td><%=obj_zy.getOrder_add() %></td>

	    </tr>
	    
	    <!-- 通知存款 -->
	    <%  obj_qkj = iter_qkj.next(); %>
	    <%  obj_zy = iter_zy.next(); %>
	    <tr>
            <th data-field="通知存款" rowspan="1" style="text-align:left;vertical-align:middle">通知存款</th>
            <th data-field="通知存款" rowspan="1" data-valign="middle" data-align="center"></th>
            <th data-field="通知存款" rowspan="1" data-valign="middle" data-align="center"></th>
			<td><%=obj_qkj.getJiecunhushu() %></td>
			<td><%=obj_qkj.getSave_balance_money() %></td>
			<td><%=obj_qkj.getSave_compare() %></td>
			<td><%=obj_qkj.getOrder_add() %></td>


			<td><%=obj_zy.getJiecunhushu() %></td>
			<td><%=obj_zy.getSave_balance_money() %></td>
			<td><%=obj_zy.getSave_compare() %></td>
			<td><%=obj_zy.getOrder_add() %></td>

	    </tr>
	    
	    <!-- 总计 -->
	    <%  obj_qkj = iter_qkj.next(); %>
		<%  obj_zy = iter_zy.next(); %>
	    <tr>
            <th data-field="总计" rowspan="1" style="text-align:left;vertical-align:middle">总计</th>
            <th data-field="通总计" rowspan="1" data-valign="middle" data-align="center"></th>
            <th data-field="总计" rowspan="1" data-valign="middle" data-align="center"></th>
			<td><%=obj_qkj.getJiecunhushu() %></td>
			<td><%=obj_qkj.getSave_balance_money() %></td>
			<td><%=obj_qkj.getSave_compare() %></td>
			<td><%=obj_qkj.getOrder_add() %></td>


			<td><%=obj_zy.getJiecunhushu() %></td>
			<td><%=obj_zy.getSave_balance_money() %></td>
			<td><%=obj_zy.getSave_compare() %></td>
			<td><%=obj_zy.getOrder_add() %></td>

	    </tr>
			<!-- <% } %>		 -->
	</thead>
			
</table>


</body>
</html>