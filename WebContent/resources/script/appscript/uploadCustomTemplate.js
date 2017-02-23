$(function(){
	$("#templateTable").bootstrapTable({
    	classes:'table table-hover table-condensed table-no-bordered',
    	dataType:'json',
    	striped:true,
    	pagination: true,
    	search:true,
    	toolbar:'#toolbar',
    	searchOnEnterKey:true,
    	trimOnSearch:false,
    	sortable: false,
    	showRefresh:false,
    	pagination:true,
    	pageNumber:1,      //初始化加载第一页，默认第一页
    	pageSize: 10,      //每页的记录行数（*）
    	pageList: [10, 25, 50, 100],
    	showExport: false,                     //是否显示导出
        exportDataType: "basic",              //basic', 'all', 'selected'.
    	columns: [{
            field: 'state',
            radio: true,
    	},{
    	    field: 'tempName',
    	    title: '模板名称',
    	    align: 'center',
    	},{
    		field: 'dataSpace',
    	    title: '数据区域',
    	    align: 'center',
    	},{
    		title: '状态',
    		field: 'status',
    		align: 'center',
		  	formatter:function(value,row,index){
		  		var status = row.status
		  		console.log(row.status)
		  		var icon;
		  		if(status==0){//失效
		  			icon = '<span class="glyphicon glyphicon-remove" style="color: rgb(151, 0, 0);"></span>';
		  		}else if(status==1){//生效
		  			icon = '<span class="glyphicon glyphicon-ok" style="color: rgb(0, 174, 0);"></span>';
		  		}else{//其他情况
		  			icon = '<span class="glyphicon glyphicon-question-sign"></span>';
		  		}
		    	return icon;  
			} 
		}],
    	onRefresh:function(){
    		queryTemplates();
    	},
    	onCheck:function(row){
    	},
    	onDblClickRow:function(row,element){
    	}
	});
	queryTemplates();
	$("#choosefile").fileinput({
        language: 'zh', //设置语言
        uploadUrl: "uploadCT.do", //上传的地址
        browseLabel : "选择文件",
        removeLabel : "删除",
        uploadLabel : "上传",
        allowedFileExtensions: ['xls'],//接收的文件后缀
        uploadAsync: true, //默认异步上传
        showUpload: true, //是否显示上传按钮
        showRemove : true, //显示移除按钮
        showPreview : false, //是否显示预览
        showCaption: true,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式     
        dropZoneEnabled: false,//是否显示拖拽区域
        maxFileCount: 10, //表示允许同时上传的最大文件个数
        enctype: 'multipart/form-data',
        validateInitialCount:true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
    });
	//异步上传失败返回结果处理
	$('#choosefile').on('fileerror', function(event, data, msg){
	});
	//异步上传成功返回结果处理
	$("#choosefile").on("fileuploaded", function (event, data, previewId, index) {
    });
})
function queryTemplates(){
	$.ajax({
		type:"POST",
	    url:"queryTemplates.do",
	    dataType:'json',
	    success:function(data){
	    	var d = data.list
	        $("#templateTable").bootstrapTable('load',d);
	    },
	    error:function(){
	    	alert("错误");
	    	return;
	    }
	})
}
function getTemplateVal(){
	return $('#templateName').val();
}
