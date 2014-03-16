/**
 * @author gyrate<gyratesky@gmail.com>
 * @copyright www.gyratesky.com
 *
 * @ SysDialog 类名
 * @ option(Object) 参数
 *   title(String) : 标题
 *   body(String , Dom) :  主体内容
 *   url(String) : 若该属性存在，则窗口主体为以该值为src的iframe
 *   button(Array) : 按钮,如 [{value:'ok',fun:{},arg:[]},{value:'cancel',fun:{},arg:[]},]
 *   mask(boolean) : 是否生成遮罩层，默认为true，此时除弹出窗口外其他区域点击无效
 *   maskAlpha(float): 遮罩层透明度，为0-1二位小数，默认值0.4
 *   width(int)   : 内容区宽度
 *   height(int)  : 内容区高度
 *   openAfterFun(function):弹出窗口后执行函数  [ { fun:function(){},arg:[] },{ fun:function(){},arg:[]},... ]
 *   closeAfterFun(function):关闭窗口后执行函数 [ { fun:function(){},arg:[] },{ fun:function(){},arg:[]},... ]
 *   hideScroll(boolean):是否隐藏html滚动条 true|false(缺省)
 *   autoOpen(boolean):是否自动弹出 true(缺省)|false
 *   drag(boolean):是否可拖拽 true(缺省)|false
 *   trigger(object):声明该弹出窗口的触发者，以便返回数据给它
 */
/* @ 模态对话窗口（基类）*/
function SysDialog(option) {

    this.option = $.extend({
        title:'系统消息',
        body:'',
        url:null,
        button:[],
        mask:true,
        maskAlpha:0.4,
        width:'auto',
        height:'auto',
        openAfterFun:[],
        closeAfterFun:[],
        hideScroll:false,
        autoOpen:true,
        drag:true,
        trigger:null
    }, option); //定义并覆盖默认参数

    this.template = option.template
        || '<div class="std_pop">'
        +'    <div class="pop_border"> '
        +'        <table>'
        +'            <colgroup>'
        +'                <col>'
        +'            </colgroup>'
        +'            <tbody>'
        +'            <tr>'
        +'                <td>'
        +'                    <div class="pop_title" actor="drager">'
        +'                        <span actor="title"></span>'
        +'                        <b class="s_ico s_ico_clh fr" actor="closer"></b> '
        +'                    </div>'
        +'                </td>'
        +'            </tr>'
        +'            <tr>'
        +'                <td class="pop_main" actor="main"> '
        +'                    <div class="pop_content" actor="content"> '
        +'                    </div> '
        +'                </td>'
        +'            </tr>'
        +'            <tr>'
        +'                <td>'
        +'                    <div class="pop_foot" actor="foot">'
        +'                    </div>'
        +'                </td>'
        +'            </tr>'
        +'            </tbody>'
        +'        </table>'
        +'    </div>'
        +'</div>';

    this.initialize();
    this.createDialog();

    if (this.option.autoOpen && !this.option.url) {
        this.openDialog();
    }


}

SysDialog.prototype = {
    constructor:SysDialog,

    //初始化各种参数
    initialize:fInitialize,

    //创建并显示遮罩层
    screenMask:fScreenMask,

    //创建窗体
    createDialog:fCreateDialog,

    //打开窗体
    openDialog:fOpenDialog,

    //关闭窗体
    closeDialog:fCloseDialog,

    //以下为私有函数不建议外部调用

    //显示数据加载提示
    _openTip:fOpenTip,

    //关闭数据加载提示
    _closeTip:fCloseTip,

    //显示窗体
    _showDialog:fShowDialog,

    //获得组件Dom元素
     _getDom:fGetDom,

    //初始化按钮
    _setButton:fSetButton,
    //设置内容
    _setContent:fSetContent,
    _setIframe:fSetIframe,
    //事件监听
    _setEventListener:fAddEventListener,
    _removeEventListener:fRemoveEventListener,
    //调整尺寸
    _size:fSize,
    //定位
    _locationDom:fLocationDom
}

/* @ 系统提示窗口（实例）
 * @ 类：SysDialog
 * @ option(Object) 参数
 *   body(string): 提示内容 *
 *   type(string): 提示类型  inf(缺省) | suc | err | war
 * */
function SysAlert(option){
    //子类属性
    var type = option.type || "inf",
        _body = '<table> ' +
                '     <tbody><tr>  ' +
                '         <td class="tc td_ico">  ' +
                '             <b class="b_ico b_ico_' + type + ' "></b>  ' +
                '         </td>  ' +
                '         <td class="td_txt"><div>' + option.body + '</div></td>  ' +
                '     </tr>  ' +
                '</tbody></table>';
    return  new SysDialog({
        title:option.title || '系统提示',
        body:_body,
        button:[{
            value:'确定'
        }]
    });

}

/* @ 系统信息窗口（实例）
 * @ 类：SysPrompt
 * @ option(Object) 参数
 *   name(string): 输入字段名称描述 *
 *   defaultValue(string): 输入字段默认值,缺省为空字符串
 *   fun(function): 处理输入字段值的函数
 * */
function SysPrompt(option){
    var _defaultVal = option.defaultValue || '',
        _name = option.name || '输入数值',
        _body = '<table width="width:100%;">' +
            '<tr><td class="tl"><label>'+_name+'：</label></td></tr>' +
            '<tr><td><input type="text" value="'+_defaultVal+'" class="ipt_txt w250"/></td></tr>' +
            '</table>';
    var target = new SysDialog({
        body:_body,
        width:280,
        height:60,
        button:[{
            value:'确定',
            fun:_handler
        }]
    })

    function _handler() {
        if (typeof option.fun == 'function') {
            option.fun(target.inputs[0].value);
        }
        target.closeDialog();
    }
}

/* @ 系统弹出自动消失框（实例）
* @  类：MsgDialog
* @  option(Object) 参数
*    body(string):提示内容 *
*    sec(float): 窗口停留时间
*    autoClose(boolean): 是否自动关闭，缺省为true
*    width(int):窗口宽度
* */
function MsgDialog(option) {
    var sec = option.sec || 2,
        autoClose = option.autoClose == null ? true : option.autoClose,
        template = '<div class="msg' + (autoClose ? '' : ' msg_loading') + '" actor="content"></div>';
    return  new SysDialog({
        title:option.title || '系统提示',
        template:template,
        body:option.body ||'自动消失的提示信息' ,
        width: option.width || 'auto',
        openAfterFun:[
            {
                fun:function() {
                    if (autoClose) {
                        var that = this;
                        setTimeout(function() {
                            that.closeDialog()
                        }, sec * 1000);
                    }
                }
            }
        ],
        closeAfterFun:[
            {
                fun:option.closeAfterFun || null
            }
        ]
    })
}

/* @ 弹出窗口阅读器（实例）
 * @  类：SysReader
 * @  option(Object) 参数
 *    title(string): 文章标题
 *    body(string):提示内容
 * */
function SysReader(option){
    var _h = $(window.top).height();

    var template = '<div class="std_preview">'+
        '<div class="std_preview_tool">'+
        '<span class="btn_close fr" title="关闭" actor="closer"></span></div>'+
        '<div class="box-cont std_preview_wrap " style="height:'+ (_h - 130)+'px;">'+
        '<div class="article"><h1 class="h" actor="title"></h1><div actor="content"></div></div>'+
        '</div></div>';

    return new SysDialog({
        title:option.title||'文章标题',
        body:option.body||'',
        template:template,
        width:780,
        height:_h,
        hideScroll:true
    });
}

function fInitialize(){
    //$遮罩层
    this.mask = null;
    //异步加载完成前的提示标签，option.url不为null时会使用到
    this.tip = null;
    //$窗口主体
    this.dlg = null;
    //窗口主体iframe
    this.iframe = null;
    //$顶部窗口
    this.win = $(window.top);
    this.document = $(window.top.document);
    //$窗口内容区的所有表单项
    this.inputs = null;
    //该弹出窗的触发者
    this.trigger = this.option.trigger;
    //当前滚动位置
    this.scrolltop = $(this.win.document).scrollTop();
    //按钮
    this.button = [];

    //catch各个组件Dom元素
    this.title = null;
    this.main = null;
    this.content = null;
    this.foot = null;
    this.closer = null;
    this.drager = null;
    this.resizer = null;

}

function fScreenMask(callback) {
    var that = this,
        wh = Math.max($(window.top.document).height(), $(this.win).height()),
        alpha = this.option.maskAlpha,
        mask = this.document[0].createElement("div"),
        isIE6 =  !('minWidth' in document.documentElement.style);

    if(this.option.hideScroll){
        $(this.document).find('html').css('overflow','hidden').scrollTop(that.scrolltop);
    }

    mask.style.cssText = 'position:absolute;top:0;left:0;margin:0;padding:0;background:#333;'
                            + 'width:100%;height:' + wh + 'px;z-index:9990;overflow:hidden;display:none;'
                            + 'filter:alpha(opacity=' + alpha * 100 + ');opacity:' + alpha + ';';

    //IE6下使用iframe覆盖select，object等标签
    if ( isIE6 ) {
        var _iframe = this.document[0].createElement("iframe");
        _iframe.style.cssText = "filter:alpha(opacity=0);width:100%;height:100%;";
        mask.appendChild(_iframe);
    }

    this.document[0].body.appendChild(mask);
    this.mask = $(mask);

    $(mask).fadeIn("fast", function() {
        callback.call(that);
    });
}

function fLocationDom() {
    var _win = window.top,
        dom = this.dlg[0],
        dw = dom.offsetWidth,
        dh = dom.offsetHeight,
        x,y;

    if (_win.innerWidth) {
        var  ww  = _win.innerWidth,
             wh  = _win.innerHeight,
             bgX = _win.pageXOffset,
             bgY = _win.pageYOffset;
    } else {
        var dde = _win.document.documentElement,
             ww = dde.offsetWidth,
             wh = dde.offsetHeight,
             bgX = dde.scrollLeft,
             bgY = dde.scrollTop;
        //IE6\7\8
    }
    dom.style.left = bgX + ((ww - dw) / 2) + 'px';
    dom.style.top = Math.max(bgY + ((wh - dh) / 2), 0) + 'px';
    dom.style.position = 'absolute';
}

function fGetDom(){
    // IE6:在当前窗口创建的dom元素才能被操作
    var wrap = this.document[0].createElement('div');
    wrap.style.cssText = 'position:absolute;top:0;left:0;margin:0;padding:0;z-index:9999;visibility: hidden;';
    wrap.innerHTML = this.template ;
    this.document.find('body').append(wrap);

    var tags = wrap.getElementsByTagName('*'),
        i,
        len = tags.length;

    for (i = 0; i < len; i++) {
        var actor =  tags[i].getAttribute('actor') ;
        if(actor){
            this[actor] = $(tags[i]);
        }
    }

    this.dlg = $(wrap);

    return this.dlg;

}

function fSetButton(){

    var option = this.option,
        args = option.button
        ;
    if (args.length == 0) {
        this.foot && this.foot.hide();
        return ;
    }
    for (var i = 0,len = args.length; i < len; i++) {

        var that = this,
        btn = this.document[0].createElement('input');
        btn.type = 'button';
        btn.value = args[i].value;
        btn.className = 'btn';
        btn.disabled = !!args[i].disabled;

        this.button.push(btn);
        this.foot.append(btn);
    }
}

function fSetContent(arg) {

    var option = this.option ;

    if (typeof arg == 'string') {

        this.content.html(option.body);

    } else if (arg && arg.nodeType == 1) {

        var display = arg.style.display,
            prev = arg.previousSibling,
            next = arg.nextSibling,
            parent = arg.parentNode;

        this.option.closeAfterFun.push({
            fun:function() {
                if (prev && prev.parentNode) {
                    prev.parentNode.insertBefore(arg, prev.nextSibling);
                } else if (next && next.parentNode) {
                    next.parentNode.insertBefore(arg, next);
                } else if (parent) {
                    parent.appendChild(arg);
                }
                arg.style.display = display;
            }
        });

        this.content.html('').append(arg);
        $(arg).show();
    }
    this.inputs = this.content.find('input');
}

function fSetIframe(){

    var that = this,
        option = this.option;

    var iframe = this.document[0].createElement('iframe');
    iframe.style.cssText = 'width:100%;border:0 none;';
    iframe.setAttribute('frameborder', '0',0); //IE6
    iframe.style.height = option.height + 'px';
    iframe.src = this.option.url;
    this.iframe = $(iframe) ;
    this.content.css('padding', 0).append(iframe);
    this._openTip();

    this.iframe.load(function() {

        //autoOpen为true则打开窗口
        that.option.autoOpen && that.openDialog();

        if (!that.iframe[0].contentWindow.$) return;

        //获得iframe的jquery对象
        var jq = that.iframe[0].contentWindow.$ ;

        //将SysDialog的句柄交给iframe的body，以便子页面调用
        jq('body').data('caller',that);

    })


}

function fSize( width, height) {
    var option = this.option,
        w = width || option.width,
        h = height || option.height;
    this.main && this.main.css({width:w,height:h});
}

function fCreateDialog() {

    var that = this ,
        option = this.option;
    this._getDom();
    this.title && this.title.html(option.title);

    if (option.url) {
        this._setIframe();
    } else {
        this._setContent(option.body);
    }

    this._setButton();
    this._size();
    this._setEventListener();
    this._locationDom();

	//初始化窗口拖拽
    if(this.option.drag && this.drager){
        this.drager.addClass('move');
        this.drager.bind('mousedown',function(event){
            dragInit(event,$(that.dlg));
            return false;
        })
    }

}

function fAddEventListener(){
    var that = this;

    $(this.button).each(function() {

        $(this).bind('click', function() {
            var idx = $(this).index();

            if (that.option.button[idx].fun && typeof that.option.button[idx].fun == 'function') {
                that.option.button[idx].fun.apply();
            } else {
                that.closeDialog();
            }
        })
    })

    this.closer && this.closer.bind('click', function() {
        that.closeDialog();
    })
}

function fRemoveEventListener(){

    var arr = ['button','close'];
    for (var i = 0,len = arr.length; i < len; i++) {
        $(this[arr[i]]).unbind();
    }
}

function fShowDialog(){

    this.dlg.css({visibility:'visible'});

    //打开后执行函数
    var funs = this.option.openAfterFun;
    for (var i = 0,len = funs.length; i < len; i++) {
        typeof funs[i]['fun'] == 'function' && funs[i]['fun'].apply(this, funs[i]['arg'] || []);
    }

}

/*打开弹出窗口*/
function fOpenDialog() {

    //销毁[数据加载中...]提示信息
    this.tip && this._closeTip();

    if (this.option.mask && !this.mask ) {
        //BUG: url模式下代码会执行两次，用!this.mask避免
        this.screenMask(this._showDialog);
    } else {
        this._showDialog();
    }
}

/*关闭弹出窗口*/
function fCloseDialog(callback) {

    this.option.mask && this.mask.remove();
    this.dlg.remove();
    if (this.option.hideScroll) {
        $(this.document).find('html').css('overflow', 'auto').scrollTop(this.scrolltop);
    }

    //关闭后执行函数
    var funs = this.option.closeAfterFun;
    for (var i = 0,len = funs.length; i < len; i++) {
        typeof funs[i]['fun'] == 'function' && funs[i]['fun'].apply(this, funs[i]['arg'] || []);
    }

    //释放内存
    this._removeEventListener();
    for (var i in this) delete this[i];

}

function fOpenTip() {

    var tip = this.document[0].createElement("span");
    tip.className = "sys_tip sys_suc";
    tip.cssText = 'margin-left:' + tip.offsetWidth / 2 + 'px;z-index:10000;';
    tip.innerHTML = '<img class="vm mr4" width="16" height="16" src="../images/ico_load.gif"/>数据加载中...';

    this.document[0].body.appendChild(tip);
    this.tip = tip;
}

function fCloseTip() {
    $(this.tip).remove();
        this.tip = null;
}

/*-------------------拖拽功能------------------------*/
function dragInit(event,dom) {
    var dragEvent = new DragEvent;
    var startLeft,startTop;

    var minX = $(document).scrollLeft(),
        minY = $(document).scrollTop(),
        maxX = $(window).width() - dom.width(),
        maxY = $(window).height() - dom.height();

    dragEvent.onstart = function (x, y) {
        startLeft = dom.offset().left;
        startTop = dom.offset().top;

        $(document).bind('dblclick', dragEvent.end);
    };
    dragEvent.onover = function (x, y) {
        var left = Math.max(minX, Math.min(maxX, x + startLeft)),
            top = y + startTop;
        dom.css({left:left,top:top});
    };
    dragEvent.onend = function (x, y) {

    };
    dragEvent.start(event);
}

function DragEvent() {
    var that = this,
        proxy = function (name) {
            var fn = that[name];
            that[name] = function () {
                return fn.apply(that, arguments);
            };
        }; //保证start、over、end执行空间为DragEvent

    proxy('start');
    proxy('over');
    proxy('end');
}
DragEvent.prototype = {
    start:function (event) {
        $(window.top.document)
            .bind('mousemove', this.over)
            .bind('mouseup', this.end);

        this._sClientX = event.clientX;
        this._sClientY = event.clientY;
        this.onstart(event.clientX, event.clientY);
        return false;
    },
    over:function (event) {
        this._mClientX = event.clientX + $(document).offsetLeft;
        this._mClientY = event.clientY + $(document).scrollTop;
        this.onover(
            event.clientX - this._sClientX,
            event.clientY - this._sClientY
        );
        return false;
    },
    end:function (event) {
        $(window.top.document)
            .unbind('mousemove', this.over)
            .unbind('mouseup', this.end);
        this.onend(event.clientX, event.clientY);
        return false;
    }
}
