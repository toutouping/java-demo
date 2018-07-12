'use strict'
var appModule = angular.module('appModule', []);

appModule.controller('appCtrl',['$scope','$http', '$location', function($scope, $http, $location){
    var editor = null;
    $scope.word = {
        headerContent : "",
        dateString :  dateFormat(new Date(), "yyyy年MM月dd日"),
        currentYear : dateFormat(new Date(), "yyyy"),
        contactUser : "XX",
        contactPhone : "（0755）4880xxx",
        versionNo : "X",
        detailContent : ""
    };

    // http://localhost:8080/java-demo/index.html#?user=小明&phone=（0755）48803XX&version=X&title=关于XX维护通知书
    $scope.word.contactUser = $location.search().user ? $location.search().user : "XXX"; 
    $scope.word.contactPhone = $location.search().phone ? $location.search().phone : "（0755）XXXXXXX";
    $scope.word.versionNo = $location.search().version ? $location.search().version : "X";
    $scope.word.headerContent = $location.search().title ? $location.search().title : "关于系统维护通知书";
    
   // 导出方法
    $scope.exportWordPost = function(){
        $scope.word.detailContent = "<div style='font-size:14pt;'>" + editor.txt.html() + "</div>";
        if($scope.word.detailContent.indexOf("<p>请在此编辑正文内容......</p>") === 0){
         $scope.word.detailContent = $scope.word.detailContent.replace("<p>请在此编辑正文内容......</p>", "");
         }
        var jsonStr = JSON.stringify($scope.word);
        var noticeExportStr = replaceCharacter(jsonStr.replace(/&nbsp;/g, " "))
         .replace(/"/g, "\"")
                                 .replace(/<br>|<br\/>/g, "<br></br>") // 去除换行
     .replace(/<br><\/br><\/li>/g, "</li>") // 去除换行
     .replace(/<br><\/br><\/p>/g, "</p>"); // 去除换行
           
        var url = "./export/exportNoticeWord";
        $.fileDownload(url,{
           httpMethod: 'POST',
           data: {"noticeExportStr": noticeExportStr},
           prepareCallback:function(url){
           },
           successCallback:function(url){
           },
           failCallback: function (html, url) {
              console.log(html);
           }
        });
    };

    // 富文本编辑
    $(document).ready(function() {
        var E = window.wangEditor
        editor = new E('#toolbar', '#editor')
        // 自定义菜单配置
        editor.customConfig.menus = [
            // 'head',  // 标题
            'bold',  // 粗体
            // 'fontSize',  // 字号
            'list',  // 列表
            'justify',  // 对齐方式
            'undo',  // 撤销
            'redo'  // 重复
        ]
        editor.create()
    });
    
    /**
     * 处理URL当中的特殊字符
     */
    function replaceCharacter(str){
     return str.replace(/\%/, '${{_25}}') // 百分号
               .replace(/"/g,"${{_22}}")  // 替换双引号
       .replace(/\+/g,"${{_2B}}")
       .replace(/\%/g,"${{_2F}}")
       .replace(/\?/g,"${{_3F}}")
       .replace(/\#/g,"${{_23}}")
       .replace(/\&/g,"${{_26}}")
       .replace(/\=/g,"${{_3D}}");
    }
    /**
     * 对Date的扩展，将 Date 转化为指定格式的String 
     * 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
     * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
     * 
     * (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
     * (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
     * 
     * @param {*} fmt 
     */
    function dateFormat(date, fmt) { //author: meizz 
        var o = {
            "M+": date.getMonth() + 1,                 //月份 
            "d+": date.getDate(),                    //日 
            "h+": date.getHours(),                   //小时 
            "m+": date.getMinutes(),                 //分 
            "s+": date.getSeconds(),                 //秒 
            "q+": Math.floor((date.getMonth() + 3) / 3), //季度 
            "S": date.getMilliseconds()             //毫秒 
        };
        if (/(y+)/.test(fmt))
            fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
}]);
