<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<!-- SpreadJS语言设置（1）-->
    	<meta name="spreadjs culture" content="zh-cn" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.min.css' />">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/font-awesome.min.css' />">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/custom.css' />">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/Spread/css/gc.spread.sheets.10.0.1.css' />">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/Spread/css/gc.spread.sheets.excel2016colorful.10.0.1.css' />">
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/select2-4.0.3/css/select2.css' />" />
		<script type="text/javascript" src="<c:url value='/resources/script/jquery-1.11.1.min.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/script/bootstrap.min.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/script/appscript/SSFrame.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/Spread/scripts/gc.spread.sheets.all.10.0.1.min.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/Spread/scripts/resources/zh/gc.spread.sheets.resources.zh.10.0.1.min.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/select2-4.0.3/js/select2.full.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/select2-4.0.3/js/i18n/zh-CN.js' />"></script>
	</head>
	<body>
		 <div class="row" id="tool">
            <div class="col-lg-12">
            	<button class="btn btn-primary btn-sm" id="closeframe">
					<span class="glyphicon glyphicon-remove"></span> 关闭窗口
				</button>
            	<button class="btn btn-primary btn-sm" id="merge">
					<span class="glyphicon glyphicon-plus"></span> 合并单元格
				</button>

				<button class="btn btn-primary btn-sm" id="UnMerge">
					<span class="glyphicon glyphicon-edit"></span> 拆分单元格
				</button>
				<!-- <button class="btn btn-primary btn-sm" id="deletereport">
					<span class="glyphicon glyphicon-remove"></span> 字体
				</button>
				<button class="btn btn-primary btn-sm" id="deletereport">
					<span class="glyphicon glyphicon-remove"></span> 数据来源
				</button> -->
				<button class="btn btn-primary btn-sm" id="addonerow">
					<span class="glyphicon glyphicon-remove"></span> 增加行
				</button>
				<button class="btn btn-primary btn-sm" id="deletonerow">
					<span class="glyphicon glyphicon-remove"></span> 删除行
				</button>
				<button class="btn btn-primary btn-sm" id="AddColumn">
					<span class="glyphicon glyphicon-remove"></span> 增加列
				</button>
				<button class="btn btn-primary btn-sm" id="deleteColumn">
					<span class="glyphicon glyphicon-remove"></span> 删除列
				</button>
				<button class="btn btn-primary btn-sm" id="chooseData">
					<span class="glyphicon glyphicon-remove"></span> 选择数据来源
				</button>
				<button class="btn btn-primary btn-sm" id="Tojson">
					<span class="glyphicon glyphicon-remove"></span> 导出数据(测试用)
				</button>
            </div>
        </div>
		<div class="sample-turtorial">
        	<div id="ss" style="width:100%; height:70%;border: 1px solid gray;"></div>
    	</div>
    	<div class="modal fade" id="cdf" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">选择<lable id="titlecontent"></lable>数据来源</h4>
					</div>
					<div class="modal-body">
						 <div class="form-group">
							<label>报表名称</label> <br/>
							<select id="selectreport"></select><br/>
							<label>报表属性</label> 
							
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" id="insertdata1" class="btn btn-primary">提交</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
		</div>
	</body>
</html>