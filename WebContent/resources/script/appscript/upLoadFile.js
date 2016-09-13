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
	
	//上传
    /*$("#file_upload").uploadify({
    	'auto':false,
        'successTimeout':99999,
        'swf': "<c:url value='/resources/uploadify/uploadify.swf' />",
        'queueID':'uploadfileQueue',
        'fileObjName':'pic',
       	'uploader':'uploadFile.do',
        'buttonText':'选择文件',
        'removeCompleted':false,
        'fileTypeDesc':'支持的格式：',
        'fileTypeExts':'*.xlsx', 
        'fileSizeLimit':'100KB',
        'onSelect' : function(file) {
                 
        },
        'onSelectError':function(file, errorCode, errorMsg){
            switch(errorCode) {
                case -100:
                    alert("上传的文件数量已经超出系统限制的"
                     +$('#file_upload').uploadify('settings','queueSizeLimit')+"个文件！");
                    break;

                case -110:
                    alert("文件 ["+file.name+"] 大小超出系统限制的"
                     +$('#file_upload').uploadify('settings','fileSizeLimit')+"大小！");
                    break;
                case -120:
                    alert("文件 ["+file.name+"] 大小异常！");
                    break;
                case -130:
                    alert("文件 ["+file.name+"] 类型不正确！");
                    break;
            }
        },
        'onUploadSuccess':function(file, data, response){
        	getList();
        },
        'onUploadError': function (file, errorCode, errorMsg, errorString) { 
        	alert("上传失败");
        } 
    });*/
    
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
    	showExport: true,                     //是否显示导出
        exportDataType: "basic",              //basic', 'all', 'selected'.
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
		  			icon = '<span class="glyphicon glyphicon-remove"></span>';
		  		}else if(status==1){//已上传
		  			icon = '<span class="glyphicon glyphicon-ok"></span>';
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
    		changeBtnStatus(row.status);
    	},
    	onDblClickRow:function(row,element){
    	}
	});
    $("#choosefile").fileinput({
        language: 'zh', //设置语言
        uploadUrl: "uploadFile1.do", //上传的地址
        browseLabel : "选择文件",
        removeLabel : "删除",
        uploadLabel : "上传",
        allowedFileExtensions: ['xlsx'],//接收的文件后缀
        //uploadExtraData:{"id": 1, "fileName":'123.mp3'},
        uploadAsync: true, //默认异步上传
        showUpload: true, //是否显示上传按钮
        showRemove : true, //显示移除按钮
        showPreview : false, //是否显示预览
        showCaption: true,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式     
        dropZoneEnabled: false,//是否显示拖拽区域
        //minImageWidth: 50, //图片的最小宽度
        //minImageHeight: 50,//图片的最小高度
        //maxImageWidth: 1000,//图片的最大宽度
        //maxImageHeight: 1000,//图片的最大高度
        //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        maxFileCount: 10, //表示允许同时上传的最大文件个数
        enctype: 'multipart/form-data',
        validateInitialCount:true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
    });
	//异步上传失败返回结果处理
	$('#choosefile').on('fileerror', function(event, data, msg){
		console.log(data)
    	console.log(event)
       	console.log(msg)
	});
	//异步上传成功返回结果处理
	$("#choosefile").on("fileuploaded", function (event, data, previewId, index) {
       console.log(data.response)
    });
});
$(setDate)
$(queryList)
$(init)
function getList(){
	var data;
	$.ajax({
		type:"POST",
	    url:"getToBeUpLoaded.do",
	    contentType: 'application/json',
	    dataType: 'json',
	    success:function(json){
	    	data = json.list;
	    	//var queueSizeLimit = data.length;
	        $("#uploadTable").bootstrapTable('load',data);
	    },
	    error:function(){
	    	alert("错误");
	    	return;
	    }
	})
	return data;
}
function queryList(){
	var ymounth = $("#datetimepicker").val();
	ymounth = ymounth.replace("-","");
	var data;
	$.ajax({
		type:"POST",
	    url:"getToBeUpLoaded.do",
	    data:{ymounth:ymounth},
	    dataType:'json',
	    success:function(json){
	    	data = json.list;
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
function init(){
	changeBtnStatus(1)
}
function changeBtnStatus(status){
	if(status == 0){
		$('#choosefile').removeAttr('disabled');
	}else{
		$('#choosefile').attr('disabled',true);
	}
}

