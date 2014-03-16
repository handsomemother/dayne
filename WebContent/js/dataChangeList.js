/*
* @ TowList （类名）数据交换组件
 *@ option(Object) 参数
 *    id      ： 对象id *
*/
//$(function () {
function dataChangeList(config) {
    var outerDiv = $("#" + config.id);

    var leftSelectJQ = outerDiv.find("select:eq(0)");
    var rightSelectJQ = outerDiv.find("select:eq(1)");

    var buttonsJQ = outerDiv.find(".toolbar");

    // Option双击事件
    leftSelectJQ.children("option").dblclick(function(e) {
        moveOptionAutoJudge($(e.target));
    });

    rightSelectJQ.children("option").dblclick(function(e) {
        moveOptionAutoJudge($(e.target));
    });

    buttonsJQ.find("b").click(function(e) {

        var targetJQ = $(e.target);

        var opt = targetJQ.attr("opt");

        var srcElement = leftSelectJQ;
        var destElement = rightSelectJQ;

        if (opt == 'fromRight' || opt == 'allFromRight') {
            var temp = srcElement;
            srcElement = destElement;
            destElement = temp;
        }

        if (opt == 'fromLeft' || opt == "fromRight") {
            var selectedValues = srcElement.val() || [];
            for (var i = 0; i < selectedValues.length; i++) {
                moveOption(srcElement, destElement, selectedValues[i]);
            }
        } else {
            moveAllOption(srcElement, destElement);

        }
    });

    function moveAllOption(srcElement, destElement, val) {
        var option = srcElement.children("option");
        option.appendTo(destElement);
    }

    function moveOption(srcElement, destElement, val) {
        var option = srcElement.children("option[value=" + val + "]");

        option.appendTo(destElement);

    }

    function moveOptionAutoJudge(optionJQ) {
        var selectJQ = optionJQ.parents("select").first();
        if (selectJQ[0] == leftSelectJQ[0]) {
            optionJQ.appendTo(rightSelectJQ);
        } else {
            optionJQ.appendTo(leftSelectJQ);
        }
    }

}
//})