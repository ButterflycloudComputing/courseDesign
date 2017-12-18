/**
 * Created by jack on 2016/11/21.
 */
'use strict';

var urlService = angular.module("urlService", ['ngCookies']);
urlService.factory('URL', ["$cookies","$rootScope",'$http','$location',function($cookies,$rootScope,$http,$location){
    //var cookie = decodeURIComponent(escape($cookies.USER_INFO));
    var cookie = decodeURIComponent(escape($cookies.USER_INFO));
    cookie = cookie.replace("\"","");

    var array= cookie.split(",");

    console.log(array);
    var user = {
        uid:array[0],
        username:array[1],
        age:array[2],
        phone:array[3],
        role:array[4]
    };

    if(user.role == "校长" || user.role == "教导主任" || user.role == "副校长"){
        $rootScope.admin = true;
    }else{
        $rootScope.admin = false;
    }
    $rootScope.user = user;


        var base = {
            uid:array[0],
            baseUrl:"http://localhost:8080/Leave",
            login:"http://localhost:8080/Leave/login",  // 登录
            logout:"http://localhost:8080/Leave/logout"  // 退出
        };
        var applications = {
            newApply:base.baseUrl + "/applications/"+base.uid,  // 新申请
            completes:base.baseUrl + "/applications/"+base.uid+"/completes/",  // 已完成
            uncompletes:base.baseUrl + "/applications/"+base.uid+"/uncompletes/",  // 未销假
            unapprovals:base.baseUrl + "/applications/"+base.uid+"/unapprovals/",  // 待审批
            rejects:base.baseUrl + "/applications/"+base.uid+"/rejects/",  // 已拒绝
            detail:base.baseUrl + "/applications/"+base.uid+"/", // 个人查看详情页
            clear:base.baseUrl + "/applications/"+base.uid+"/clear/", // 个人查看详情页

            approves:base.baseUrl + "/applications/"+base.uid+"/approves/",  // 管理员待审批页面
            approved:base.baseUrl + "/applications/"+base.uid+"/approved/",  // 管理员审批历史记录
            check:base.baseUrl + "/applications/approves/",  // 管理员待审批页面
            approve:base.baseUrl + "/applications/approve",  // 审核通过
            reject:base.baseUrl + "/applications/reject",  // 审核不通过

            substitute:base.baseUrl + "/substitute/"+base.uid,  // 申请加班
            substitutes:base.baseUrl + "/substitute/"+base.uid+"/",  // 加班记录

            details:base.baseUrl + "/bonus/"+base.uid+"/"  // 扣罚明细

        };

        $rootScope.logout = function(){
            $http({
                method: 'GET',
                url: base.logout
            }).success(function(data, status, headers, config) {
                console.log("success...");
                console.log(data);
                //alert("success");

                $location.path("/index");
            }).error(function(data, status, headers, config) {
                alert("error");
                console.log("error...");
            });
        };
     return {
         urls:{
             user: user,
             base:base,
             applications:applications
         }
     }
}]);