/**
 * @author gyrate<gyratesky@gmail.com>
 * @copyright www.gyratesky.com
 *
 * @ SysDialog 类名
 * @ option(Object) 参数
 *   id      ： 对象id *
 *   trigger :  触发事件类型
 *   defaultIndex : 当前tab的索引值
 *   curClass(String): 当前选项触发器的class
 *   menuClass(String): tab触发器的样式名称,根据该样式初始化tab轮换的触发对象，缺省为'tab_menu'
 *   contentClass(String): tab内容区样式名称，根据该样式初始化tab内容区域，缺省为'tab_content'
 *   effect(String): 选项卡内容区过渡特效,目前只支持'fade'，缺省时无特效
 */

function SysTab(option) {
    this.trigger = option.trigger || 'click';//触发器
    this.defaultIndex = option.defaultIndex || 0;//默认打开tab索引
    this.curIndex = 0;//当前tab的索引值
    this.curClass = option.curClass || 'on';//当前menu的class
    this.autoplay = option.autoplay || false;//自动播放
    this.duration = option.duration || 3000;//持续时间
    this.effect = option.effect || ''; //特效类型
    this.menuClass = option.menuClass || 'tab_menu'; //tab触发器样式名称
    this.contentClass = option.contentClass || 'tab_content';//tab内容区样式名称
    this.changeAfterFun = option.changeAfterFun;//每次切换状态触发函数
    this._changeEffect = null;//状态切换函数（私有）
    if (typeof option.id == "string") {
        this.tab = document.getElementById(option.id);
    } else {
        this.tab = option.id;
    }
    this.menu = $(this.tab).find('.' + this.menuClass);
    this.content = $(this.tab).find('.' + this.contentClass);
    //初始化tab
    this.init();
}

SysTab.prototype = {
    constructor:SysTab,
    init:fInit,
    changeState:fChangeState,
    setChangeEffect:fSetChangeEffect
}

function fInit() {
    var that = this,inter;
    this.setChangeEffect();
    this.changeState(this.defaultIndex);
    if (this.autoplay) {
        var inter = setInterval(function() {
            that.changeState((that.curIndex + 1) % that.menu.length);
        }, this.duration);
    }
    this.menu.each(function(index) {
        $(this).attr("_index", index).bind(that.trigger, function() {
            that.changeState($(this).attr("_index"));
            if (this.autoplay) {
                /*重启周期*/
                clearInterval(inter);
                inter = setInterval(function() {
                    that.changeState((that.curIndex + 1) % that.menu.length);
                }, that.duration);
            }

        })
    })
}

function fChangeState(index) {
    if (index == this.curIndex) {
        return;
    } else if (index > this.menu.length - 1) {
        index = this.menu.length - 1;
    } else if (index < 0) {
        index = 0;
    }
    var _curClass = this.curClass,
            _curIndex = this.curIndex;
    /*执行触发器状态切换*/
    this.menu.removeClass(_curClass);
    this.menu.eq(index).addClass(_curClass);
    /*执行内容区状态切换*/
    this._changeEffect(index);
    this.curIndex = index;
    if( this.changeAfterFun && typeof this.changeAfterFun == 'function'){
        this.changeAfterFun.apply(this);
    }
}

function fSetChangeEffect(){
    var that = this;
    switch(this.effect){
        case 'slideHor':
            break;
        case 'slideVer':
            break;
        case 'fade':
            this._changeEffect = function(index) {
                this.content.eq(this.curIndex).css({zIndex:99}).fadeOut();
                this.content.eq(index).css({zIndex:0}).fadeIn();
            }
            break;
        default:
            this._changeEffect = function(index) {
                this.content.eq(this.curIndex).hide();
                this.content.eq(index).show();
            }
            break;
    }
}

