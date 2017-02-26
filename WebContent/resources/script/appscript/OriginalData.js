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
	$('#querydate_eu').datetimepicker({
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
				'name':rn,
				'date':date,
				'used_inst':used_inst,
				'institutionAdd_inst':institutionAdd_inst,
				'busicode_inst':busicode_inst
		};
		$.ajax({
			type:'POST',
			url:'getPubsmrInsts.do',
			data:param,
		    dataType: 'json',
		    success:function(json){
		    	var reuslt = json.list;
		    	if(reuslt.length > 0){
		    		$('#queryReport_inst').modal("hide");
		    		var result0 = reuslt[0];
			    	var statisticsmechanism = result0.statisticsmechanism;
			    	var statisticsdate = result0.statisticsdate;
			    	var statisticstype = result0.statisticstype;//开户机构
			    	var dotproperties = result0.dotproperties;//网店属性
			    	var paytype = result0.paytype;//支付方式
			    	var containsubordinateinst = result0.containsubordinateinst;//是否包含下级
			    	var busicode = result0.busicode;//业务代码
			    	var nbsp = "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
		    		var str = "<label>统计机构代码/名称：</label>"+
		    		"<label>"+statisticsmechanism+"</label>"+"</br>"+
		    		"<label>统计年月：</label>"+"<label>"+statisticsdate+"</label>"+"</br>"+
		    		"<label>统计方式：</label>"+"<label>"+statisticstype+"</label>"+nbsp+
		    		"<label>网点属性：</label>"+"<label>"+dotproperties+"</label>"+nbsp+
		    		"<label>支付方式：</label>"+"<label>"+paytype+"</label>"+nbsp+
		    		"<label>是否包含下级：</label>"+"<label>"+containsubordinateinst+"</label>"+nbsp+
		    		"<label>业务代码：</label>"+"<label>"+busicode+"</label>"+
		    		"<table id='inst'></table>";
			    	var reportname = reuslt[0].reportName;
			    	var data = {id:reportname,name:reportname,data:str}
			    	addatab(data);
			    	setInstColumns(reuslt)
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
		    		var result0 = reuslt[0];
		    		
		    		
		    		var statisticsmechanism = result0.statisticsmechanism;
			    	var statisticsdate = result0.statisticsdate;
			    	var statisticstype = result0.statisticstype;//开户机构
			    	var dotproperties = result0.dotproperties;//网店属性
			    	var paytype = result0.paytype;//支付方式
			    	var containsubordinateinst = result0.containsubordinateinst;//是否包含下级
			    	var nbsp = "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
			    	
			    	var str = "<label>统计机构代码/名称：</label>"+
			    		"<label>"+statisticsmechanism+"</label>"+"</br>"+
			    		"<label>统计年月：</label>"+"<label>"+statisticsdate+"</label>"+"</br>"+
			    		"<label>统计方式：</label>"+"<label>"+statisticstype+"</label>"+nbsp+
			    		"<label>网点属性：</label>"+"<label>"+dotproperties+"</label>"+nbsp+
			    		"<label>支付方式：</label>"+"<label>"+paytype+"</label>"+nbsp+
			    		"<label>是否包含下级：</label>"+"<label>"+containsubordinateinst+"</label>"+
			    		"<table id='busi'></table>";
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
	$('#query_eu').click(function(){
		var date = $('#querydate_eu').val().replace("-","");
		var used_eu = $('#used_eu').val();
		var institutionAdd_eu = $('#institutionAdd_eu').val();
		var param = {
				reportname:rn,
				date:date,
				used_eu:used_eu,
				institutionAdd_eu:institutionAdd_eu
		};
		$.ajax({
			type:"POST",
		    url:"getPubsmrEntrustUnit.do",
		    data:param,
		    dataType:'json',
		    success:function(json){
		    	var reuslt = json.list;
		    	if(reuslt.length > 0){
		    		$('#queryReport_eu').modal("hide");
		    		var result0 = reuslt[0];
		    		var statisticsmechanism = result0.statisticsmechanism;
			    	var statisticsdate = result0.statisticsdate;
			    	var statisticstype = result0.statisticstype;//开户机构
			    	var dotproperties = result0.dotproperties;//网店属性
			    	var paytype = result0.paytype;//支付方式
			    	var containsubordinateinst = result0.containsubordinateinst;//是否包含下级
			    	var nbsp = "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
			    	
			    	var str = "<label>统计机构代码/名称：</label>"+
			    		"<label>"+statisticsmechanism+"</label>"+"</br>"+
			    		"<label>统计年月：</label>"+"<label>"+statisticsdate+"</label>"+"</br>"+
			    		"<label>统计方式：</label>"+"<label>"+statisticstype+"</label>"+nbsp+
			    		"<label>网点属性：</label>"+"<label>"+dotproperties+"</label>"+nbsp+
			    		"<label>支付方式：</label>"+"<label>"+paytype+"</label>"+nbsp+
			    		"<label>是否包含下级：</label>"+"<label>"+containsubordinateinst+"</label>"+
			    		"<table id='EU'></table>";
			    	var reportname = reuslt[0].reportName;
			    	var data = {id:reportname,name:reportname,data:str}
			    	addatab(data);
			    	setEUColumns(reuslt)
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
	}else if(reportname == "代收付业务统计月报--按委托单位"){
		$('#queryReport_eu').modal("show");
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
	$("#inst").bootstrapTable({
    	classes:'table table-hover table-condensed',
    	striped:true,
    	cache:false,
    	pagination: false,
    	sortable: false,
    	data:resultData,
    	columns: [
    	          [
    	           {title: '机构代码',align: 'center',colspan:1},
    	           {title: '机构名称',align: 'center',colspan:1},
    	           {title: '交易笔数',align: 'center',colspan:2},
    	           {title: '交易金额(元)',align: 'center',colspan:2},
    	           {title: '冲正笔数',align: 'center',colspan:2},
    	           {title: '冲正金额(元)',align: 'center',colspan:2},
    	           {title: '取消笔数',align: 'center',colspan:2},
    	           {title: '取消金额(元)',align: 'center',colspan:2},
    	           {title: '交易总笔数',align: 'center',colspan:2},
    	           {title: '交易总金额(元)',align: 'center',colspan:2}],
    	          [
    	           {title: '代码',align: 'center',field: 'instcode'},
    	           {title: '简称',align: 'center',field: 'instname',},
    	           {title: '本期发生',align:'center',field: 'transamount_month'},
    	           {title: '本年累计',align:'center',field: 'transamount_year'	},
    	           {title: '本期发生',align:'center',field: 'transmoney_month'	},
    	           {title: '本年累计',align:'center',field: 'transmoney_year'},
    	           {title: '本期冲正笔数',align:'center',field: 'correctnum_month'},
    	           {title: '本年累计冲正笔数',align: 'center',field: 'correctnum_year'},
    	           {title: '本期冲正金额',align: 'center',field: 'correctmoney_month'},
    	           {title: '本年累计冲正金额',align: 'center',field: 'correctmoney_year'},
    	           {title: '本期发本期取消笔数',align: 'center',field: 'cancelamount_month'},
    	           {title: '本年累计取消笔数',align: 'center',field: 'cancelamount_year'},
    	           {title: '本期取消金额',align: 'center',field: 'cancelmoney_month'},
    	           {title: '本年累计取消金额',align: 'center',field: 'cancelmoney_year'},
    	           {title: '本期交易总笔数',align: 'center',field: 'transtotalnum_month'},
    	           {title: '本年累计交易总笔数',align: 'center',field: 'transtotalnum_year'},
    	           {title: '本期交易总金额',align: 'center',field: 'transtotalmoney_month'},
    	           {title: '本年累计交易总金额',align: 'center',field: 'transtotalmoney_year'}
    	          ]
    	         ]
	});
	
}
function setBusiColumns(resultData){
    $("#busi").bootstrapTable({
    	classes:'table table-hover table-condensed',
    	striped:true,
    	cache:false,
    	pagination: false,
    	sortable: false,
    	data:resultData,
    	columns: [
    	          [
    	           {title: '业务代码',align: 'center',colspan:1	},
    	           {title: '业务名称',align: 'center',colspan:1,},
    	           {title: '交易笔数',align: 'center',colspan:2},
    	           {title: '交易金额(元)',align: 'center',colspan:2},
    	           {title: '冲正笔数',align: 'center',colspan:2},
    	           {title: '冲正金额(元)',align: 'center',colspan:2},
    	           {title: '取消笔数',align: 'center',colspan:2},
    	           {title: '取消金额(元)',align: 'center',colspan:2},
    	           {title: '交易总笔数',align: 'center',colspan:2},
    	           {title: '交易总金额(元)',align: 'center',colspan:2}],
    	          [
    	           {title: '代码',align: 'center',field: 'busicode'},
    	           {title: '简称',align: 'center',field: 'businame',},
    	           {title: '本期发生',align: 'center',field: 'transamount_month'},
    	           {title: '本年累计',align: 'center',field: 'transamount_year'},
    	           {title: '本期发生',align: 'center',field: 'transmoney_month'},
    	           {title: '本年累计',align: 'center',field: 'transmoney_year'},
    	           {title: '本期冲正笔数',align: 'center',field: 'correctnum_month'},
    	           {title: '本年累计冲正笔数',align: 'center',field: 'correctnum_year'},
    	           {title: '本期冲正金额',align: 'center',field: 'correctmoney_month'},
    	           {title: '本年累计冲正金额',align: 'center',field: 'correctmoney_year'},
    	           {title: '本期发本期取消笔数',align: 'center',field: 'cancelamount_month'},
    	           {title: '本年累计取消笔数',align: 'center',field: 'cancelamount_year'},
    	           {title: '本期取消金额',align: 'center',field: 'cancelmoney_month'},
    	           {title: '本年累计取消金额',align: 'center',field: 'cancelmoney_year'},
    	           {title: '本期交易总笔数',align: 'center',field: 'transtotalnum_month'},
    	           {title: '本年累计交易总笔数',align: 'center',field: 'transtotalnum_year'},
    	           {title: '本期交易总金额',align: 'center',field: 'transtotalmoney_month'},
    	           {title: '本年累计交易总金额',align: 'center',field: 'transtotalmoney_year'}]
    	         ]
	});
}
function setEUColumns(resultData){
    $("#EU").bootstrapTable({
    	classes:'table table-hover table-condensed',
    	striped:true,
    	cache:false,
    	pagination: false,
    	sortable: false,
    	data:resultData,
    	columns: [
    	          [
    	           {title: '委托单位',align: 'center',colspan:1	},
    	           {title: '委托单位',align: 'center',colspan:1,},
    	           {title: '业务名称',align: 'center',colspan:1,},
    	           {title: '交易笔数',align: 'center',colspan:2},
    	           {title: '交易金额(元)',align: 'center',colspan:2},
    	           {title: '冲正笔数',align: 'center',colspan:2},
    	           {title: '冲正金额(元)',align: 'center',colspan:2},
    	           {title: '取消笔数',align: 'center',colspan:2},
    	           {title: '取消金额(元)',align: 'center',colspan:2},
    	           {title: '交易总笔数',align: 'center',colspan:2},
    	           {title: '交易总金额(元)',align: 'center',colspan:2}],
    	          [
    	           {title: '代码',align: 'center',field: 'merchid'},
    	           {title: '简称',align: 'center',field: 'merchname',},
    	           {title: '',align: 'center',field: 'businame',},
    	           {title: '本期发生',align: 'center',field: 'transamount_month'},
    	           {title: '本年累计',align: 'center',field: 'transamount_year'},
    	           {title: '本期发生',align: 'center',field: 'transmoney_month'},
    	           {title: '本年累计',align: 'center',field: 'transmoney_year'},
    	           {title: '本期冲正笔数',align: 'center',field: 'correctnum_month'},
    	           {title: '本年累计冲正笔数',align: 'center',field: 'correctnum_year'},
    	           {title: '本期冲正金额',align: 'center',field: 'correctmoney_month'},
    	           {title: '本年累计冲正金额',align: 'center',field: 'correctmoney_year'},
    	           {title: '本期发本期取消笔数',align: 'center',field: 'cancelamount_month'},
    	           {title: '本年累计取消笔数',align: 'center',field: 'cancelamount_year'},
    	           {title: '本期取消金额',align: 'center',field: 'cancelmoney_month'},
    	           {title: '本年累计取消金额',align: 'center',field: 'cancelmoney_year'},
    	           {title: '本期交易总笔数',align: 'center',field: 'transtotalnum_month'},
    	           {title: '本年累计交易总笔数',align: 'center',field: 'transtotalnum_year'},
    	           {title: '本期交易总金额',align: 'center',field: 'transtotalmoney_month'},
    	           {title: '本年累计交易总金额',align: 'center',field: 'transtotalmoney_year'}]
    	         ]
	});
}