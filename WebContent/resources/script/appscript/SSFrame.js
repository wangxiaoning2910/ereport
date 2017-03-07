//交易与后台交互 获取数据
var iframewidth;//iframe的宽度
var iframeheight;//iframe的高度
var toolwidth;//工具栏的宽度，等于iframe的宽度
var toolheight;//工具栏的高度，200px
var spread;
$(document).ready(function() {
	iframewidth = parent.parentwidth;
	toolwidth = iframewidth;
	toolheight = $('#tool').height();
	iframeheight = parent.parenth - toolheight;
	$('#ss').css('width',iframewidth);
	$('#ss').css('height',iframeheight);
	spread = new GC.Spread.Sheets.Workbook(document.getElementById('ss'), { sheetCount: 1 });
	$("#closeframe").click(function() {
	    parent.$("#SSFrame").remove();
	    parent.$("#SSFrameBg").remove();
    });
	$('#addonerow').click(function(){
		addonerow();
	});
	$('#deletonerow').click(function(){
		deletonerow();
	});
	$('#merge').click(function(){
		merge();
	});
	$('#UnMerge').click(function(){
		UnMerge();
	});
	$('#AddColumn').click(function(){
		AddColumn();
	});
	$('#deleteColumn').click(function(){
		deleteColumn();
	});
	$('#chooseData').click(function(){
		chooseData();
	});
	$('#Tojson').click(function(){
		 var spread1 = $("#ss").data("workbook");
         var jsonStr = JSON.stringify(spread1.toJSON());
         console.log(jsonStr)
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
		getFieldName(reprotname);
	})
});
function getFieldName(tablename){
	var param = {tablename:tablename};
	$.ajax({
		type:'POST',
		url:'getFieldName.do',
		data:param,
	    dataType: 'json',
	    success:function(result){
	    	console.log(result)
	    }
	});
}
/*
 * row == -1 且 rowcount == 本sheet rowcount 表示选择了col列
 * col == -1 且 colcount == 本sheet colcount 表示选择了row行
 * var rowCount = sheet.getRowCount();
 * var columnCount = sheet.getColumnCount();
 * */
function chooseData(){
	var sheet = spread.getActiveSheet();
	var rowCount = sheet.getRowCount();
	var columnCount = sheet.getColumnCount();
	var title;
	var sel = sheet.getSelections();
	var chooserow = sel[0].row
    var choosecol = sel[0].col;
	var totalrow = sel[0].rowCount;
	var totalcol = sel[0].colCount;
	console.log(chooserow)
	if(chooserow == -1 && rowCount == totalrow){
		title = (choosecol+1)+"列的"
	}else if(choosecol == -1 && columnCount == totalcol){
		title = (chooserow+1)+"行的"
	}else{
		title = (chooserow+1)+"行"+(choosecol+1)+"列的"
	}
	$('#titlecontent').html(title);
	$('#cdf').modal('show');
    
	
	
}
//在选中的cell之上插入新的一行，并选中新增行
function addonerow(){
	var sheet = spread.getActiveSheet();
	var sel = sheet.getSelections();
	if (sel.length > 0) {
		sel = getActualCellRange(sel[sel.length - 1], sheet.getRowCount(), sheet.getColumnCount());
    }
	sheet.addRows(sel.row, 1);
}
//删除选中行
function deletonerow(){
	var sheet = spread.getActiveSheet();
	var sel = sheet.getSelections();
	if (sel.length > 0) {
		sel = getActualCellRange(sel[sel.length - 1], sheet.getRowCount(), sheet.getColumnCount());
	}
	 sheet.deleteRows(sel.row, 1);
}
function merge() {
	var sheet = spread.getActiveSheet();
    var sel = sheet.getSelections();
    if (sel.length > 0) {
        sel = getActualCellRange(sel[sel.length - 1], sheet.getRowCount(), sheet.getColumnCount());
        sheet.addSpan(sel.row, sel.col, sel.rowCount, sel.colCount);
    }
}
function UnMerge(){
	var sheet = spread.getActiveSheet();
    var sel = sheet.getSelections();
    if (sel.length > 0) {
        sel = getActualCellRange(sel[sel.length - 1], sheet.getRowCount(), sheet.getColumnCount());
        sheet.suspendPaint();
        for (var i = 0; i < sel.rowCount; i++) {
            for (var j = 0; j < sel.colCount; j++) {
                sheet.removeSpan(i + sel.row, j + sel.col);
            }
        }
        sheet.resumePaint();
    }
}
function AddColumn(){
	var sheet = spread.getActiveSheet();
	var sel = sheet.getSelections();
    if (sel.length > 0) {
    	sel = getActualCellRange(sel[sel.length - 1], sheet.getRowCount(), sheet.getColumnCount());
    }
    sheet.addColumns(sel.col,1);
}
function deleteColumn(){
	var sheet = spread.getActiveSheet();
	var sel = sheet.getSelections();
    if (sel.length > 0) {
    	sel = getActualCellRange(sel[sel.length - 1], sheet.getRowCount(), sheet.getColumnCount());
    }
    sheet.deleteColumns(sel.col,1);
}
function getActualCellRange(cellRange, rowCount, columnCount) {
    if (cellRange.row == -1 && cellRange.col == -1) {
        return new spreadNS.Range(0, 0, rowCount, columnCount);
    }
    else if (cellRange.row == -1) {
        return new spreadNS.Range(0, cellRange.col, rowCount, cellRange.colCount);
    }
    else if (cellRange.col == -1) {
        return new spreadNS.Range(cellRange.row, 0, cellRange.rowCount, columnCount);
    }
    return cellRange;
}