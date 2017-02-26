//交易与后台交互 获取数据
$(document).ready(function() {
	
	$('#datetimepicker').datetimepicker({
    	format: 'yyyy-mm',
    	startDate:'201606',
        startView:3,
        weekStart:1,
        minView:3,
        maxView:4,
        todayHighlight:true,
        language:'zh-CN',
    });
	
    //bootstrapTable
    $("#uploadTable").bootstrapTable({
    	classes:'table table-hover table-condensed table-no-bordered',
    	dataType:'json',
    	striped:true,
    	cache:false,
    	pagination: true,
    	sortable: false,
    	showRefresh:false,
    	pagination:true,
    	pageNumber:1,      //初始化加载第一页，默认第一页
    	pageSize: 10,      //每页的记录行数（*）
    	pageList: [10, 25, 50, 100],
    	columns: [{
    		checkbox:true,
    	},{
    	    field: 'filename',
    	    title: '名称',
    	    align: 'center',
    	},{
    	    field: 'date',
    	    title: '日期',
    	    align: 'center',
    	},{
    	    field: 'status',
    	    title: '状态',
    	    align: 'center',
    	    formatter:function(value,row,index){
    	    	var status = row.status;
    	    	var str;
    	    	if(status == 0){
    	    		str = "待下载";
    	    	}
		    	return str;  
			}
    	},{
    		title: '操作',
    		field: '#',
    		align: 'center',
		  	formatter:function(value,row,index){
		    	return '<a onclick="downloadFile('+index+')"><span class="glyphicon glyphicon-download-alt"></span></a>';  
			} 
		}],
    	onRefresh:function(){
    	},onCheck:function(row){
    	},onCheckAll:function(rows){
    	},onUncheckAll:function(rows){
    	},onUncheck:function(row){
    	}
	});
    $("#choosemerch").bootstrapTable({
    	classes:'table table-hover',
    	dataType:'json',
    	height: $('#InsertNewMerch').height()/4*3,
    	striped:true,
    	cache:false,
    	pagination: true,
    	sortable: false,
    	showRefresh:false,
    	pagination:false,
    	columns: [{
    		checkbox:true
    	},{
    	    field: 'merchid',
    	    title: '委托单位代码',
    	    align: 'center',
    	    width: '20%',
    	},{
    	    field: 'merchname',
    	    title: '委托单位名称',
    	    align: 'center',
    	    width: '50%',
    	},{
    	    field: 'businame',
    	    title: '业务代码-名称',
    	    align: 'center',
    	    width: '30%',
    	}]
	});
    $("#choosemerch1").bootstrapTable({
    	classes:'table table-hover',
    	dataType:'json',
    	height: $('#InsertNewMerch').height()/4*3,
    	striped:true,
    	cache:false,
    	pagination: true,
    	sortable: false,
    	showRefresh:false,
    	pagination:false,
    	columns: [{
    		checkbox:true
    	},{
    	    field: 'merchid',
    	    title: '委托单位代码',
    	    align: 'center',
    	    width: '20%',
    	},{
    	    field: 'merchname',
    	    title: '委托单位名称',
    	    align: 'center',
    	    width: '50%',
    	},{
    	    field: 'businame',
    	    title: '业务代码-名称',
    	    align: 'center',
    	    width: '30%',
    	}]
	});
    $('#downloadfiles').click(function(){
    	downloadFiles();
    });
    $('#insertdata').click(function(){
    	insertNewMerchData_month();
    });
    $('#insertdata1').click(function(){
    	insertNewMerchData_year();
    });
	
});
$(setDate)
$(init)
$(queryList)
var Listdata;
function insertNewMerchData_month(){
	var ziying = $('#choosemerch').bootstrapTable('getSelections');
	var total = $('#choosemerch').bootstrapTable('getData');
	var insertdate = $('#ymounth').val();
	var daili = [];
	for(var x = 0;x<ziying.length;x++){
		var temp = ziying[x].merchid;
		var temp1 = ziying[x].businame;
		for(var i = 0;i<total.length;i++){
			if(total[i].merchid == temp && total[i].businame == temp1){
				total.splice(i,1);
			}
		}
	}
	daili = total;
	var selfsupport_merchid = [];
	var selfsupport_merchname = [];
	var selfsupport_businame = [];
	var agent_merchid = [];
	var agent_merchname = [];
	var agent_businame = [];
	for(var a = 0;a<ziying.length;a++){
		selfsupport_merchid[a] = ziying[a].merchid;
		selfsupport_merchname[a] = ziying[a].merchname;
		selfsupport_businame[a] = ziying[a].businame;
	}
	for(var b = 0;b<daili.length;b++){
		agent_merchid[b] = daili[b].merchid;
		agent_merchname[b] = daili[b].merchname;
		agent_businame[b] = daili[b].businame;
	}
	var param = {'insertdate':insertdate,'selfsupport_merchid':selfsupport_merchid,
			'selfsupport_merchname':selfsupport_merchname,'selfsupport_businame':selfsupport_businame,
			'agent_merchid':agent_merchid,'agent_merchname':agent_merchname,'agent_businame':agent_businame};
	$.ajax({
		type:"POST",
	    url:"intsertnewMerch_month.do",
	    traditional: true,
	    data:param,
	    dataType:'json',
	    success:function(json){
	    	$('#InsertNewMerch').modal("hide");
	    	getnm_year();
	    	
	    },
	    error:function(){
	    	alert("错误");
	    	return;
	    }
	})
	
}
function insertNewMerchData_year(){
	var ziying = $('#choosemerch1').bootstrapTable('getSelections');
	var total = $('#choosemerch1').bootstrapTable('getData');
	var insertdate = $('#ymounth').val().substring(0,4);
	var daili = [];
	for(var x = 0;x<ziying.length;x++){
		var temp = ziying[x].merchid;
		var temp1 = ziying[x].businame;
		for(var i = 0;i<total.length;i++){
			if(total[i].merchid == temp && total[i].businame == temp1){
				total.splice(i,1);
			}
		}
	}
	daili = total;
	var selfsupport_merchid = [];
	var selfsupport_merchname = [];
	var selfsupport_businame = [];
	var agent_merchid = [];
	var agent_merchname = [];
	var agent_businame = [];
	for(var a = 0;a<ziying.length;a++){
		selfsupport_merchid[a] = ziying[a].merchid;
		selfsupport_merchname[a] = ziying[a].merchname;
		selfsupport_businame[a] = ziying[a].businame;
	}
	for(var b = 0;b<daili.length;b++){
		agent_merchid[b] = daili[b].merchid;
		agent_merchname[b] = daili[b].merchname;
		agent_businame[b] = daili[b].businame;
	}
	var param = {'insertdate':insertdate,'selfsupport_merchid':selfsupport_merchid,
			'selfsupport_merchname':selfsupport_merchname,'selfsupport_businame':selfsupport_businame,
			'agent_merchid':agent_merchid,'agent_merchname':agent_merchname,'agent_businame':agent_businame};
	$.ajax({
		type:"POST",
	    url:"intsertnewMerch_year.do",
	    traditional: true,
	    data:param,
	    dataType:'json',
	    success:function(json){
	    	$('#InsertNewMerch1').modal("hide");
	    	$('#exportfileform').attr('method','post');
			$("#exportfileform").attr("action", "exportfiles.do");
			$('#exportfileform').submit();
	    },
	    error:function(){
	    	alert("错误");
	    	return;
	    }
	})
	
}
function queryList(){
	var ymounth = $("#datetimepicker").val();
	ymounth = ymounth.replace("-","");
	var data;
	$.ajax({
		type:"POST",
	    url:"getToBeUpDownLoad.do",
	    data:{ymounth:ymounth},
	    dataType:'json',
	    success:function(json){
	    	data = json.filelist;
	    	Listdata = data;
	        $("#uploadTable").bootstrapTable('load',data);
	    },
	    error:function(){
	    	alert("错误");
	    	return;
	    }
	});
	return data;
}
function setDate(){
	var oDate = new Date();
	var year = oDate.getFullYear();    //获取完整的年份
	var Month = oDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
	var length = Month.length;
	if(Month < 10){
		Month = "0"+Month;
	}
	var now = year+"-"+Month;
	$("#datetimepicker").attr('value',now);
}
function downloadFile(index){
	var row = Listdata[index];
	var date = row.date;
	var filename = row.filename
	$('#filename').attr('value',filename);
	$('#ymounth').attr('value',date);
	if(filename == "代收付新增委托单位"){
		getnm_month(filename,date);
	}else{
		$('#exportfileform').attr('method','post');
		$("#exportfileform").attr("action", "exportfiles.do");
		$('#exportfileform').submit();
	}
	
}
function getnm_month(filename,date){
	$.ajax({
		type:"POST",
	    url:"getnewMerch_month.do",
	    data:{ymounth:date,filename:filename},
	    dataType:'json',
	    success:function(json){
	    	data = json.list;
	        $("#choosemerch").bootstrapTable('load',data);
	        $('#InsertNewMerch').modal("show");
	    },
	    error:function(){
	    	alert("错误");
	    	return;
	    }
	})
}
function getnm_year(){
	var filename = $('#filename').val();
	var date = $('#ymounth').val();
	$.ajax({
		type:"POST",
	    url:"getnewMerch_year.do",
	    data:{ymounth:date,filename:filename},
	    dataType:'json',
	    success:function(json){
	    	data = json.list;
	    	console.log(data)
	        $("#choosemerch1").bootstrapTable('load',data);
	        $('#InsertNewMerch1').modal("show");
	    },
	    error:function(){
	    	alert("错误");
	    	return;
	    }
	})
}
function downloadFiles(){
	var selectedrows = $('#uploadTable').bootstrapTable('getSelections');
	var filename = [];
	var date = selectedrows[0].date;
	var length = selectedrows.length;
	for(var i = 0; i <length; i++){
		filename[i] = selectedrows[i].filename;
	}
	$('#filename').attr('value',filename);
	$('#ymounth').attr('value',date);
	$('#exportfileform').attr('method','post');
	$("#exportfileform").attr("action", "exportfiles.do");
	$('#exportfileform').submit();
}
function init(){
	changeBtnStatus(0)
}
function changeBtnStatus(length){
	if(length != 0){
		$('#downloadfiles').removeAttr('disabled');
	}else{
		$('#downloadfiles').attr('disabled',true);
	}
}
function getSelectNum(){
	var selected = $('#uploadTable').bootstrapTable('getSelections');
	var length = selected.length;
	changeBtnStatus(length)
}
