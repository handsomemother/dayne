/**
 * @author gyrate<gyratesky@gmail.com>
 * @copyright www.gyratesky.com
 *
 * @ SysDialog 类名
 * @ option(Object) 参数
 *  id(String):目标元素的ID  *
 *  url(String):数据请求url  *
 *  initloadData(Boolean):是否初始化时加载数据 true缺省 |false
 *  defaultIndex(int):初始化时输入区和表单项默认取值，缺省为0
 *  name(string):表单项名称，如果该项不设置，首先查找在input标签里的声明，如果标签无声明则默认为 'input_' + this.option.id
 *  data(Array):初始化数据，支持定义时传入或通过Ajax传入，目前与url不共存
 **/

function SingleSelect(option){
    this.option = $.extend({
        id:null,
        url:'',
        initLoadData:true,
        defaultIndex:0,
        data:null
    },option);
    this.target = $('#' + this.option.id); //目标组件
    this.trigger = this.target.find('span').first();  //触发器
    this.input = this.target.find('input').first(); //输入区域
    this.name = this.option.name || this.input.attr('name') || 'input_' + this.option.id;
    this.input.attr('name', this.name + '_forbiden');
    this.hidden = null;//创建隐藏表单项
    this.single = null;//单选浮动层主体
    this.isShowing = false;//当前是否显示
    this.data = this.option.data ;//基础数据
    /*初始化组件*/
    this.initSingle();
}
SingleSelect.prototype = {
    constructor:SingleSelect,
    initSingle:fInitSingle,
    createSingle:fCreateSingle,
    openSingle:fOpenSingle,
    closeSingle:fCloseSingle,
    loadData:fLoadData,
    initTrigger:fInitTrigger,
    filterItem:fFilterItem
}

function fInitSingle(){
    /*创建隐藏表单项*/
    var _hidden = $('<input type="hidden"/>').attr({name:this.name});
    this.target.append(_hidden);
    this.hidden = _hidden;

    var that = this;
    if (this.option.initLoadData && this.data == null) {
        this.loadData(this.option.url, this.initTrigger);
    } else {
        this.initTrigger();
    }
    /*document监听控制组件开闭*/
    $(document).mousedown(function(event) {
        var event = event || window.event,
            eventTargt = event.target || event.srcElement;
        if (that.target[0] !== $(eventTargt).parents(".ipt_extxt")[0] && that.isShowing) {
            that.closeSingle();
        }
    })
}

function fInitTrigger() {
    var that = this;
    this.trigger.click(function() {
        if (that.isShowing) {
            that.closeSingle();
        } else {
            if (that.single == null) {
                if (that.data == null) {
                    that.loadData(that.option.url, that.createSingle);
                    //如果数据不存在，则异步加载数据，然后再创建浮动层
                } else {
                    that.createSingle();
                    //数据存在，直接创建浮动层
                }
            }
            that.openSingle();
        }
    })
    this.input.focus(function(){
        if (that.single == null) {
            if (that.data == null) {
                that.loadData(that.option.url, that.createSingle);
                //如果数据不存在，则异步加载数据，然后再创建浮动层
            } else {
                that.createSingle();
                //数据存在，直接创建浮动层
            }
        }
        that.openSingle();
    }).keyup(function(){
        that.filterItem();
    }).val( isDataForbidden() ? '' : this.data[this.option.defaultIndex].desc);

    this.hidden.val( isDataForbidden() ? '' : this.data[this.option.defaultIndex].value);

    function isDataForbidden(){
        return that.data == null || that.data.length == 0 || that.data.length < that.option.defaultIndex + 1;
    }
}

function fCreateSingle() {
    var that = this,
        idx = this.option.defaultIndex,
        data = this.data ,
        _w = this.target.width() + getPadWidth(this.target) + 1,
        isIE6 = $.browser.msie && $.browser.version == '6.0';
    var _ul = document.createElement('ul');
    $(_ul).addClass("droplist").css({width:_w,display:'none',height:isIE6 ? '120px' : 'auto'});

    /*创建单选浮动层主体*/
    for (var i = 0; i < data.length; i++) {
        var _li = document.createElement('li'),
            _a = document.createElement('a');
        _a.innerHTML = _a.title = data[i].desc;
        _a.href="javascript:void(0);"; //令IE6下hover样式不失效
        _a.setAttribute("value", data[i].value);
        _a.onclick = function() {
            that.hidden.val(this.getAttribute("value"));
            that.input.val(this.innerHTML);
            that.closeSingle();
        }
        _li.appendChild(_a);
        _ul.appendChild(_li);
    }
    this.target.append(_ul);
    this.single = _ul;
}

function fOpenSingle(){
    this.target.css({zIndex:10});
    $(this.single).show();
    this.isShowing = true;
}

function fCloseSingle(){
    this.target.css({zIndex:0});
    $(this.single).hide();
    this.isShowing = false;
}

function fLoadData(url,callback) {
    if (!url) {
        return;
    }
    var that = this;
    $.ajax({
        url:url,
        type:'POST',
        //dataType:'json', 添加则出错，需寻找能够返回json的方式
        success:function(result) {
            that.data = eval('(' + result + ')');
            if (typeof callback == 'function') {
                callback.call(that);
            }
        },
        error:function(event) {
            console && console.warn("loadData fail:");
            console && console.warn(event);
        }
    })
}

function fFilterItem() {
    var _key = $.trim(this.input.val()) ,
        items = $(this.single).find('li') ,
        re = new RegExp(_key, i);

    for (var i = 0; i < items.length; i++) {
        if ( $.trim(items[i].childNodes[0].innerHTML).match(re) ) {
            items[i].style.display = "";
        } else {
            items[i].style.display = "none";
        }
    }
}