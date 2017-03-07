//交易与后台交互 获取数据
var parentwidth;
var parenth;
$(document).ready(function() {
	parentwidth  = $(document).width();
	parenth = $(document).height();
    //bootstrapTable
    $("#integration").bootstrapTable({
    	classes:'table table-hover table-condensed table-no-bordered',
    	dataType:'json',
    	striped:true,
    	cache:false,
    	pagination: true,
    	sortable: false,
    	showRefresh:true,
    	pagination:true,
    	toolbar:"#toolbar",
    	pageNumber:1,      //初始化加载第一页，默认第一页
    	pageSize: 10,      //每页的记录行数（*）
    	pageList: [10, 25, 50, 100],
    	showExport: false,                     //是否显示导出
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
    	},
    	onCheck:function(row){
    	},
    	onDblClickRow:function(row,element){
    	}
	});
    $('#addreport').click(function(){
    	//获取parentpage的宽和高，给iframe
    	
    	showIframe('SSFrame.jsp',parentwidth,parenth);
    });
});
function showIframe(url,w,h){
    //添加iframe
    var if_w = w; 
    var if_h = h; 
    //allowTransparency='true' 设置背景透明
    $("<iframe width='" + if_w + "' height='" + if_h + "' id='SSFrame' name='SSFrame' style='position:absolute;z-index:4;'  frameborder='no' marginheight='0' marginwidth='0' allowTransparency='true'></iframe>").prependTo('body');    
    var st=document.documentElement.scrollTop|| document.body.scrollTop;//滚动条距顶部的距离
    var sl=document.documentElement.scrollLeft|| document.body.scrollLeft;//滚动条距左边的距离
    var ch=document.documentElement.clientHeight;//屏幕的高度
    var cw=document.documentElement.clientWidth;//屏幕的宽度
    var objH=$("#SSFrame").height();//浮动对象的高度
    var objW=$("#SSFrame").width();//浮动对象的宽度
    var objT=Number(st)+(Number(ch)-Number(objH))/2;
    var objL=Number(sl)+(Number(cw)-Number(objW))/2;
    $("#SSFrame").css('left',objL);
    $("#SSFrame").css('top',objT);
 
    $("#SSFrame").attr("src", url)
 
    //添加背景遮罩
    //$("<div id='SSFrameBg' style='background-color: Gray;display:block;z-index:3;position:absolute;left:0px;top:0px;filter:Alpha(Opacity=30);/* IE */-moz-opacity:0.4;/* Moz + FF */opacity: 0.4; '/>").prependTo('body'); 
    //var bgWidth = Math.max($("body").width(),cw);
    //var bgHeight = Math.max($("body").height(),ch);
    //$("#SSFrameBg").css({width:bgWidth,height:bgHeight});
 
    /*//点击背景遮罩移除iframe和背景
    $("#SSFrameBg").click(function() {
        $("#SSFrame").remove();
        $("#SSFrameBg").remove();
    });*/
}