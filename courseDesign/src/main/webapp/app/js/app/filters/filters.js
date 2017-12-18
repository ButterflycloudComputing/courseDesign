/**
 * Created by jack on 2016/11/21.
 * 自定义的过滤器
 */
var filterModule=angular.module("filterModule",[]);
filterModule.filter('applicationStatus',function(){
    return function(item){
        switch(item){
            case 0:
                return "未审批";
            case 1:
                return "未销假";
            case 2:
                return "已完成";
            case -1:
                return "已回绝"
        }
    }
});

filterModule.filter('applicationCount',function(){
    return function(item){
        return item+"天";
    }
});

filterModule.filter('applicationType',function(){
    return function(item){
        switch(item){
            case 1:
                return "公假";
            case 2:
                return "事假";
            case 3:
                return "病假";
            case 4:
                return "婚假";
            case 5:
                return "产假、计划生育假";
            case 6:
                return "丧假";
            case 7:
                return "工伤假";
        }
    }
});