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
    		queryList();
    	},
    	onCheck:function(row){
    		changeBtnStatus(row.status);
    	},
    	onDblClickRow:function(row,element){
    	}
	});

	
});
$(setDate)
$(queryList)
var Listdata;
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
	})
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
	$('#exportfileform').submit();
}
