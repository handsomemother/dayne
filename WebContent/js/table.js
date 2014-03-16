/**
 * @author gyrate<gyratesky@gmail.com>
 * @copyright www.gyratesky.com
 *
 * @ SysTable 类名
 *
 * @ option(Object) 参数
 *   id 表格id *
 *   sortable(boolean) : 是否支持排序，默认为 false
 *   autoSelect（boolean）:是否自动选择[全选，反选，不选] ,缺省true
 */
function SysTable(option) {
    this.option = $.extend({
        sortable:false,
        autoSelect:true
    }, option);
    this.table = this.getTable(option.id);
    this.sortable = this.option.sortable;
    this.curIndex = null;//当前索引列
    this.selArr = ['全选','反选','不选'];
    this.selFuns = [  // [ 函数名，参数 ]
        ['setAllSel',true],
        ['setInvertSel',null],
        ['setAllSel',false]
    ];
    this.init();
}
SysTable.prototype = {
    constructor:SysTable,
    init:fInit,
    setAllSel:fSetAllSel,//全选 | 不选
    setInvertSel:fSetInvertSel,//反选
    getTable:fGetTable,//获得table对象
    initSortTable:fInitSortTable,//初始化排列表格
    getSelItem:fGetSelItem //获得当前选中input对象
};

function fInit() {

    var that = this;
    //sortable为true,则初始化排序
    this.sortable && this.initSortTable();
    //初始化全选组件
    $(this.table).find("thead .fid").first().change(function() {
        var _is = $(this).attr("checked") == "checked";
        $(that.table).find("tbody .fid").attr('checked', _is);
    });
    //初始化自动选择组件
    if (this.option.autoSelect) {
    	//添加空判断
    	if(this.table.tBodies[0].rows.length!=0){
	        var _links = '',
	            _cellLen = this.table.tBodies[0].rows[0].cells.length,
	            _toolTd = $('<td class="tool" colspan="' + _cellLen + '"></td>'),
	            _toolTr = $('<tr></tr>');
	    	
	        for (var i = 0; i < this.selArr.length; i++) {
	            _links += '<a href="javascript:void(0);" class="_forSel">' + this.selArr[i] + '</a>' + (i == this.selArr.length - 1 ? '' : ' | ');
	        }
	        _toolTd.append('<span>选择：</span>' + _links);
	        _toolTd.find('._forSel').click(function(){
	            var i = $(this).index()-1;
	            that[ that.selFuns[i][0] ].call(that, that.selFuns[i][1]);
	        });
	        _toolTr.append(_toolTd);
	        $(this.table).find('tfoot tr').first().before(_toolTr);
    	};
    }
}

function fGetTable(id) {
    return document.getElementById(id);
}

/*全选行checkbox*/
function fSetAllSel(is) {
    var th_input = $(this.table).find("thead .fid").first();
    var _is = is !== null ? is : !th_input.attr("checked") == "checked";
    th_input.attr('checked', _is);
    $(this.table).find("tbody .fid").attr('checked',  _is);
}

/*反选行checkbox*/
function fSetInvertSel() {
    var count = 0;//统计选中行数
    var th_input = $(this.table).find("thead .fid").first();
    var checkboxs = $(this.table).find("tbody .fid");
    checkboxs.each(function() {
        var opt = $(this).attr("checked") == "checked";
        $(this).attr('checked', !opt);
        if(!opt){count++;};
    });
    th_input.attr("checked", count >= checkboxs.length ? true : false);

}

/*转换数据类型 */
function parse(data, dataType) {
    switch (dataType) {
        case 'num':
            return parseFloat(data) || 0;

        case 'date':
            return Date.parse(data) || 0;

        default :
            return data.toString();
    }
}

/*表格行比较
* @index 列索引
* @dataType 数据类型
* */
function generateCompare(index, dataType) {
    return function(row1, row2) {
        var _r1 = parse($(row1.cells[index].childNodes[0]).text(), dataType);
        var _r2 = parse($(row2.cells[index].childNodes[0]).text(), dataType);
        if (dataType == 'string') {
            return _r1.localeCompare(_r2);//比较字符串
        } else {
            return _r1 - _r2;
        }
    }
}
/*表格排序*/
function fInitSortTable() {
    var _tab = this.table;
    var _tb = _tab.tBodies[0];
    var _rows = _tb.rows;
    var _rowArr = [];
    var that = this; //指向Systable

    for (var i = 0; i < _rows.length; i++) {
        _rowArr[i] = _rows[i];
    }

    $(_tab).find('th').click(function() {
        if (!$(this).attr('sortable')) {
            return;
        }
        var _colIndex = $(this).index();
        var dataType = $(this).attr('dataType') || 'num';
        //判断最后一次排序的列是否与现在要进行排序的列相同，是的话直接使用reverse()逆序
        if (that.curIndex == _colIndex) {
            _rowArr.reverse();
        } else {
            _rowArr.sort(generateCompare(_colIndex, dataType));
            $(that.table.tHead).find('._cur').removeClass().addClass('s_ico s_ico_sor_nev');
        }

        var _oFragment = document.createDocumentFragment();
        for (var i = 0; i < _rowArr.length; i++) {
            _oFragment.appendChild(_rowArr[i]);
        }
        _tb.appendChild(_oFragment);
        that.curIndex = _colIndex;//设置为当前索引列

        var _b = $(this).find('b')[0];
        var _class = $(_b).hasClass('s_ico_sor_up') ? 's_ico_sor_dow' : 's_ico_sor_up';//s_ico_sor_up为升序图标 s_ico_sor_dow为降序图标
        _b.className = 's_ico ' + _class + ' _cur';


    })
}

function fGetSelItem(attr){
    var checkboxs = $(this.table).find("tbody input.fid:checked");
    return checkboxs;
}

