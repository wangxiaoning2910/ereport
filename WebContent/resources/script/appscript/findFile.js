//交易与后台交互 获取数据
var isChoosedFile = false;
var choosedFileStatus = 1;
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
	
	function getReport(){
		var date = $('#datetimepicker').val();
		date = date.replace("-","");
		$.ajax({
			type:"POST",
		    url:"getereport.do",
		    contentType: 'application/json',
		    dataType: 'json',
		    data:{date:date},
		    success:function(json){
		    	console.log(json)
		    	data = json.list;
		    	//var queueSizeLimit = data.length;
		        $("#uploadTable").bootstrapTable('load',data);
		    },
		    error:function(){
		    	alert("错误");
		    	return;
		    }
		})
	}
});
    
$(setDate)


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

