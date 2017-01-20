$(function(){
	$('#querydate_busi').datetimepicker({
    	format: 'yyyy-mm',
        startView:3,
        weekStart:1,
        minView:3,
        maxView:4,
        autoclose:true,
        todayBtn:true,
        todayHighlight:true,
        language:'zh-CN',
    });
	$('#querydate_inst').datetimepicker({
    	format: 'yyyy-mm',
        startView:3,
        weekStart:1,
        minView:3,
        maxView:4,
        autoclose:true,
        todayBtn:true,
        todayHighlight:true,
        language:'zh-CN',
    });
	$('#selectreport').select2({
		language:'zh-CN',
		placeholder: '选择报表',
		multiple:false,
		width:'500',
		ajax : {
	        url:"getoriginalDataList.do",
	        dataType:"json",
	        processResults: function (data) {
	            return {
	              results: data.list
	            };
	        },
	        cache: false,
	        escapeMarkup : function (m) { return m; }               // 字符转义处理
	    }
	});
	$('#selectreport').on('select2:select', function (evt) {
		var reprotname = evt.params.data.text;
		querydataclick(reprotname);
	}).on('select2:unselecting',function(evt){
	}).on('change',function(evt){
	}).on('select2:open',function(evt){
		$("#selectreport").val("0").trigger("change");
	});
	$('#query_inst').click(function(){
		var date = $('#querydate_inst').val().replace("-","");
		var used_inst = $('#used_inst').val();
		var institutionAdd_inst = $('#institutionAdd_inst').val();
		var busicode_inst = $('#busicode_inst').val();
		var param = {
				reportname:rn,
				data:date,
				used_inst:used_inst,
				institutionAdd_inst:institutionAdd_inst,
				busicode_inst:busicode_inst
		};
		console.log(param)
		$.ajax({
			type:'POST',
			url:'getPubsmrInsts.do',
			contentType: 'application/json',
			data:param,
		    dataType: 'json',
		    success:function(json){
		    	var reuslt = json.list;
		    	if(reuslt.length > 0){
		    		$('#queryReport_inst').modal("hide");
			    	var str = "<table id='inst'></table>";
			    	var reportname = reuslt[0].reportName;
			    	var data = {id:reportname,name:reportname,data:str}
			    	addatab(data);
			    	setBusiColumns(reuslt)
		    	}
		    },
		    error:function(){
		    	bootbox.alert("错误", function () {})
		    	return;
		    }
		});
	});
	$('#query_busi').click(function(){
		var date = $('#querydate_busi').val().replace("-","");
		var used_busi = $('#used_busi').val();
		var institutionAdd_busi = $('#institutionAdd_busi').val();
		var param = {
				reportname:rn,
				date:date,
				used_busi:used_busi,
				institutionadd_busi:institutionAdd_busi
		};

		$.ajax({
			type:"POST",
		    url:"getPubsmrBusis.do",
		    data:param,
		    dataType:'json',
		    success:function(json){
		    	var reuslt = json.list;
		    	if(reuslt.length > 0){
		    		$('#queryReport_busi').modal("hide");
			    	var str = "<table id='busi'></table>";
			    	var reportname = reuslt[0].reportName;
			    	var data = {id:reportname,name:reportname,data:str}
			    	addatab(data);
			    	setBusiColumns(reuslt)
		    	}
		    },
		    error:function(){
		    	bootbox.alert("错误", function () {});
		    	return;
		    }
		})
	});
	$('#myTabs').addtabs();
})
var rn;
function querydataclick(reportname){
	rn = reportname;
	if(reportname == "代收付业务统计月报--按业务"){
		$('#queryReport_busi').modal("show");
	}else if(reportname == "代收付业务统计月报--按机构"){
		$('#queryReport_inst').modal("show");
	}else{
		
	}
}

function addatab(data){
	Addtabs.add({
       id: data.id,
       title: data.name,
       content:data.data
    })
}
function setInstColumns(resultData){
	$("#busi").bootstrapTable({
    	classes:'table table-hover table-condensed',
    	striped:true,
    	cache:false,
    	pagination: false,
    	sortable: false,
    	data:resultData,
    	columns: [[{
    	    title: '代收付业务统计月报--按业务',
    	    align: 'center',
    	    colspan:18
    	}],[{
    	    title: '统计机构代码/名称：',
    	    align: 'left',
    	    colspan:1
    	},{
    		colspan:17
    	}],[{
    		title: '统计年月：',
     	    align: 'left',
     	    colspan:1
    	},{colspan:17}],[{
    		title: '统计方式：',
     	    align: 'left',
     	    colspan:1
    	},{colspan:1},{
    		title: '网点属性：',
     	    align: 'left',
     	    colspan:1
    	},{colspan:1},{
    		title: '支付方式：',
     	    align: 'left',
     	    colspan:1
    	},{colspan:1},{
    		title: '是否包含下级机构：',
     	    align: 'left',
     	    colspan:1
    	},{colspan:1},{
    		title: '业务代码：',
     	    align: 'left',
     	    colspan:1
    	},{colspan:10}],[{
    		title: '业务代码',
     	    align: 'center',
     	    colspan:1
    	},{
    		title: '业务名称',
     	    align: 'center',
     	    colspan:1
    	},{
    		title: '交易笔数',
     	    align: 'center',
     	    colspan:2
    	},{
    		title: '交易金额(元)',
     	    align: 'center',
     	    colspan:2
    	},{
    		title: '冲正笔数',
     	    align: 'center',
     	    colspan:2
    	},{
    		title: '冲正金额(元)',
     	    align: 'center',
     	    colspan:2
    	},{
    		title: '取消笔数',
     	    align: 'center',
     	    colspan:2
    	},{
    		title: '取消金额(元)',
     	    align: 'center',
     	    colspan:2
    	},{
    		title: '交易总笔数',
     	    align: 'center',
     	    colspan:2
    	},{
    		title: '交易总金额(元)',
     	    align: 'center',
     	    colspan:2
    	}],[{
    		title: '代码',
     	    align: 'center',
     	    field: 'busicode'
    	},{
    		title: '简称',
     	    align: 'center',
     	    field: 'businame'
    	},{
    		title: '本期发生',
     	    align: 'center',
     	    field: 'transamount_month'
    	},{
    		title: '本年累计',
     	    align: 'center',
     	    field: 'transamount_year'
    	},{
    		title: '本期发生',
     	    align: 'center',
     	    field: 'transmoney_month'
    	},{
    		title: '本年累计',
     	    align: 'center',
     	    field: 'transmoney_year'
    	},{
    		title: '本期冲正笔数',
     	    align: 'center',
     	    field: 'correctnum_month'
    	},{
    		title: '本年累计冲正笔数',
     	    align: 'center',
     	    field: 'correctnum_year'
    	},{
    		title: '本期冲正金额',
     	    align: 'center',
     	    field: 'correctmoney_month'
    	},{
    		title: '本年累计冲正金额',
     	    align: 'center',
     	    field: 'correctmoney_year'
    	},{
    		title: '本期发本期取消笔数',
     	    align: 'center',
     	    field: 'cancelamount_month'
    	},{
    		title: '本年累计取消笔数',
     	    align: 'center',
     	    field: 'cancelamount_year'
    	},{
    		title: '本期取消金额',
     	    align: 'center',
     	    field: 'cancelmoney_month'
    	},{
    		title: '本年累计取消金额',
     	    align: 'center',
     	    field: 'cancelmoney_year'
    	},{
    		title: '本期交易总笔数',
     	    align: 'center',
     	    field: 'transtotalnum_month'
    	},{
    		title: '本年累计交易总笔数',
     	    align: 'center',
     	    field: 'transtotalnum_year'
    	},{
    		title: '本期交易总金额',
     	    align: 'center',
     	    field: 'transtotalmoney_month'
    	},{
    		title: '本年累计交易总金额',
     	    align: 'center',
     	    field: 'transtotalmoney_year'
    	}]],
	});
	
}
function setBusiColumns(resultData){
	// 
    $("#busi").bootstrapTable({
    	classes:'table table-hover table-condensed',
    	striped:true,
    	cache:false,
    	pagination: false,
    	sortable: false,
    	data:resultData,
    	columns: [[{
    	    title: '代收付业务统计月报--按业务',
    	    align: 'center',
    	    colspan:18
    	}],[{
    	    title: '统计机构代码/名称：',
    	    align: 'left',
    	    colspan:1
    	},{
    		colspan:17
    	}],[{
    		title: '统计年月：',
     	    align: 'left',
     	    colspan:1
    	},{colspan:17}],[{
    		title: '统计方式：',
     	    align: 'left',
     	    colspan:1
    	},{colspan:1},{
    		title: '网点属性：',
     	    align: 'left',
     	    colspan:1
    	},{colspan:1},{
    		title: '支付方式：',
     	    align: 'left',
     	    colspan:1
    	},{colspan:1},{
    		title: '是否包含下级机构：',
     	    align: 'left',
     	    colspan:1
    	},{colspan:10}],[{
    		title: '业务代码',
     	    align: 'center',
     	    colspan:1
    	},{
    		title: '业务名称',
     	    align: 'center',
     	    colspan:1
    	},{
    		title: '交易笔数',
     	    align: 'center',
     	    colspan:2
    	},{
    		title: '交易金额(元)',
     	    align: 'center',
     	    colspan:2
    	},{
    		title: '冲正笔数',
     	    align: 'center',
     	    colspan:2
    	},{
    		title: '冲正金额(元)',
     	    align: 'center',
     	    colspan:2
    	},{
    		title: '取消笔数',
     	    align: 'center',
     	    colspan:2
    	},{
    		title: '取消金额(元)',
     	    align: 'center',
     	    colspan:2
    	},{
    		title: '交易总笔数',
     	    align: 'center',
     	    colspan:2
    	},{
    		title: '交易总金额(元)',
     	    align: 'center',
     	    colspan:2
    	}],[{
    		title: '代码',
     	    align: 'center',
     	    field: 'busicode'
    	},{
    		title: '简称',
     	    align: 'center',
     	    field: 'businame'
    	},{
    		title: '本期发生',
     	    align: 'center',
     	    field: 'transamount_month'
    	},{
    		title: '本年累计',
     	    align: 'center',
     	    field: 'transamount_year'
    	},{
    		title: '本期发生',
     	    align: 'center',
     	    field: 'transmoney_month'
    	},{
    		title: '本年累计',
     	    align: 'center',
     	    field: 'transmoney_year'
    	},{
    		title: '本期冲正笔数',
     	    align: 'center',
     	    field: 'correctnum_month'
    	},{
    		title: '本年累计冲正笔数',
     	    align: 'center',
     	    field: 'correctnum_year'
    	},{
    		title: '本期冲正金额',
     	    align: 'center',
     	    field: 'correctmoney_month'
    	},{
    		title: '本年累计冲正金额',
     	    align: 'center',
     	    field: 'correctmoney_year'
    	},{
    		title: '本期发本期取消笔数',
     	    align: 'center',
     	    field: 'cancelamount_month'
    	},{
    		title: '本年累计取消笔数',
     	    align: 'center',
     	    field: 'cancelamount_year'
    	},{
    		title: '本期取消金额',
     	    align: 'center',
     	    field: 'cancelmoney_month'
    	},{
    		title: '本年累计取消金额',
     	    align: 'center',
     	    field: 'cancelmoney_year'
    	},{
    		title: '本期交易总笔数',
     	    align: 'center',
     	    field: 'transtotalnum_month'
    	},{
    		title: '本年累计交易总笔数',
     	    align: 'center',
     	    field: 'transtotalnum_year'
    	},{
    		title: '本期交易总金额',
     	    align: 'center',
     	    field: 'transtotalmoney_month'
    	},{
    		title: '本年累计交易总金额',
     	    align: 'center',
     	    field: 'transtotalmoney_year'
    	}]],
	});
}