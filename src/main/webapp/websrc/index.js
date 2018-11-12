'use strict'
var appModule = angular.module('appModule', []);

appModule.controller('appCtrl',['$scope','$http', '$location','$timeout', function($scope, $http, $location, $timeout){
    var editor = null;
    $scope.isLoading = false;
    $scope.word = {
        headerContent : "",
        dateString :  dateFormat(new Date(), "yyyy年MM月dd日"),
        currentYear : dateFormat(new Date(), "yyyy"),
        contactUser : "",
        contactPhone : "",
        versionNo : "",
        detailContent : ""
    };

    // http://localhost:8080/java-demo/index.html#?user=小明&phone=（0755）48803XX&version=X&title=关于XX维护通知书
    $scope.word.contactUser = $location.search().user ? $location.search().user : ""; 
    $scope.word.contactPhone = $location.search().phone ? $location.search().phone : "";
    $scope.word.versionNo = $location.search().version ? $location.search().version : " ";
    $scope.word.headerContent = $location.search().title ? $location.search().title : "关于系统维护通知书";
    
   // 导出方法
    $scope.exportWordPost = function(){
        $scope.isLoading = true;
        $timeout(function(){
        	var base64 = new Base64();
        	// IE 下复制的内容可能出现属性
        	$('#editor br').removeAttr('style');
        	$('#editor br').removeAttr('class');
        	var style = '*{line-height:1.5;font-family:FangSong;font-family:仿宋;font-size:14pt;margin:0} p, h1,h2, h3, h4, h5, table, li, pre {line-height: 1.5; margin: 0px;}';
        	$scope.word.detailContent = "<html><body><style>"+ style +"</style>" + "<div>" + editor.txt.html() + "</div></body></html>";
            if($scope.word.detailContent.indexOf("<p>请在此编辑正文内容......</p>") === 0){
           	 $scope.word.detailContent = $scope.word.detailContent.replace("<p>请在此编辑正文内容......</p>", "");
             }
            var jsonStr = JSON.stringify($scope.word);
            var noticeExportStr = replaceCharacter(jsonStr.replace(/<p><br><\/p><\/div>/g, " ").replace(/&nbsp;/g, " "))
           								 .replace(/"/g, "\"")
           								 .replace(/<br><\/br>/g, "</br>") // 为了确保不存在成对的换行符 <br></br>
       		                             .replace(/<br>|<br\/>/g, "<br></br>") // 去除换行
       									 .replace(/<br><\/br><\/li>/g, "</li>") // 去除换行
       									 .replace(/<br><\/br><\/p>/g, "</p>"); // 去除换行
      	        
            var url = "./export/exportNoticeWord";
            $.fileDownload(url,{
                httpMethod: 'POST',
                data: {"noticeExportStr": noticeExportStr},
                prepareCallback:function(url){
             	    $scope.isLoading = false;
             	    $scope.$applyAsync();
                },
                successCallback:function(url){
             	    $scope.isLoading = false;
             	    $scope.$applyAsync();
                },
                failCallback: function (html, url) {
                    console.log(html);
             	    $scope.isLoading = false;
             	    $scope.$applyAsync();
                }
             });
        }, 100);
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
    
    function Base64() {
        // private property  
        var _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";  

        // public method for encoding  
        this.encode = function (input) {  
            var output = "";  
            var chr1, chr2, chr3, enc1, enc2, enc3, enc4;  
            var i = 0;  
            input = _utf8_encode(input);  
            while (i < input.length) {  
                chr1 = input.charCodeAt(i++);  
                chr2 = input.charCodeAt(i++);  
                chr3 = input.charCodeAt(i++);  
                enc1 = chr1 >> 2;  
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);  
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);  
                enc4 = chr3 & 63;  
                if (isNaN(chr2)) {  
                    enc3 = enc4 = 64;  
                } else if (isNaN(chr3)) {  
                    enc4 = 64;  
                }  
                output = output +  
                _keyStr.charAt(enc1) + _keyStr.charAt(enc2) +  
                _keyStr.charAt(enc3) + _keyStr.charAt(enc4);  
            }  
            return output;  
        }  

        // public method for decoding  
        this.decode = function (input) {  
            var output = "";  
            var chr1, chr2, chr3;  
            var enc1, enc2, enc3, enc4;  
            var i = 0;  
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");  
            while (i < input.length) {  
                enc1 = _keyStr.indexOf(input.charAt(i++));  
                enc2 = _keyStr.indexOf(input.charAt(i++));  
                enc3 = _keyStr.indexOf(input.charAt(i++));  
                enc4 = _keyStr.indexOf(input.charAt(i++));  
                chr1 = (enc1 << 2) | (enc2 >> 4);  
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);  
                chr3 = ((enc3 & 3) << 6) | enc4;  
                output = output + String.fromCharCode(chr1);  
                if (enc3 != 64) {  
                    output = output + String.fromCharCode(chr2);  
                }  
                if (enc4 != 64) {  
                    output = output + String.fromCharCode(chr3);  
                }  
            }  
            output = _utf8_decode(output);  
            return output;  
        }  

        // private method for UTF-8 encoding  
        var _utf8_encode = function (string) {  
            string = string.replace(/\r\n/g,"\n");  
            var utftext = "";  
            for (var n = 0; n < string.length; n++) {  
                var c = string.charCodeAt(n);  
                if (c < 128) {  
                    utftext += String.fromCharCode(c);  
                } else if((c > 127) && (c < 2048)) {  
                    utftext += String.fromCharCode((c >> 6) | 192);  
                    utftext += String.fromCharCode((c & 63) | 128);  
                } else {  
                    utftext += String.fromCharCode((c >> 12) | 224);  
                    utftext += String.fromCharCode(((c >> 6) & 63) | 128);  
                    utftext += String.fromCharCode((c & 63) | 128);  
                }  

            }  
            return utftext;  
        }  

        // private method for UTF-8 decoding  
        var _utf8_decode = function (utftext) {  
            var string = "";  
            var i = 0;  
            var c = c1 = c2 = 0;  
            while ( i < utftext.length ) {  
                c = utftext.charCodeAt(i);  
                if (c < 128) {  
                    string += String.fromCharCode(c);  
                    i++;  
                } else if((c > 191) && (c < 224)) {  
                    c2 = utftext.charCodeAt(i+1);  
                    string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));  
                    i += 2;  
                } else {  
                    c2 = utftext.charCodeAt(i+1);  
                    c3 = utftext.charCodeAt(i+2);  
                    string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));  
                    i += 3;  
                }  
            }  
            return string;  
        }  
    }
}]);