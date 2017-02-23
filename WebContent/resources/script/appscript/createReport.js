$(function(){
	$('#new').click(showModel)
	$("#rulestable").bootstrapTable({
    	classes:'table table-hover table-condensed table-no-bordered',
    	dataType:'json',
    	striped:true,
    	cache:false,
    	pagination: true,
    	sortable: false,
    	showRefresh:true,
    	pagination:true,
    	pageNumber:1,      //初始化加载第一页，默认第一页
    	pageSize: 10,      //每页的记录行数（*）
    	pageList: [10, 25, 50, 100],
    	columns: [{
            field: 'state',
            radio: true,
    	},{
    	    field: 'name',
    	    title: '名称',
    	    align: 'center',
    	},{
    		title: '状态',
    		field: '#',
    		align: 'center',
		  	formatter:function(value,row,index){
		  		var status = row.status
		  		var icon;
		  		if(status==0){//待上传
		  			icon = '<span class="glyphicon glyphicon-remove" style="color: rgb(151, 0, 0);"></span>';
		  		}else if(status==1){//已上传
		  			icon = '<span class="glyphicon glyphicon-ok" style="color: rgb(0, 174, 0);"></span>';
		  		}else{//其他情况
		  			icon = '<span class="glyphicon glyphicon-question-sign"></span>';
		  		}
		    	return icon;  
			} 
		}],
    	onRefresh:function(){
    		queryList();
    	},
    	onCheck:function(row){
    	},
    	onDblClickRow:function(row,element){
    	}
	});
	$('#selectreport').select2({
		language:'zh-CN',
		placeholder: '选择报表',
		multiple:true,
		width:"100%",
		allowClear:true,
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
})
function showModel(){
	$('#createReport').modal("show")
}
