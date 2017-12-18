/**
 * Created by jack on 2016/12/21.
 */
var leaveMng = angular.module('leaveMng',['ui.router', 'ngGrid','ngSanitize','ngCookies','applicationService','urlService','filterModule']);
/**
 * 由于整个应用都会和路由打交道，所以这里把$state和$stateParams这两个对象放到$rootScope上，方便其它地方引用和注入。
 * 这里的run方法只会在angular启动的时候运行一次。
 * @param  {[type]} $rootScope
 * @param  {[type]} $state
 * @param  {[type]} $stateParams
 * @return {[type]}
 */
leaveMng.run(function($rootScope, $state, $stateParams) {
    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;
});



leaveMng.config(function($stateProvider,$urlRouterProvider){
    $urlRouterProvider.otherwise('/index');
    $stateProvider
        .state('index', {   // login
            url: '/index',
            templateUrl: 'login.html',
            controller:'LoginController'
        })
        .state('applications', {   // home
            url: '/applications',
            views: {
                '': {
                    templateUrl: 'mng.html'
                },
                '@applications': {
                    templateUrl: 'welcome.html'
                }
            }
        })
        .state('applications.new', {  // 请假表单
            url: '/new',
            templateUrl: 'apply/apply.html',
            controller:'NewApplicationController'
        })
        .state('applications.detail', {  // 个人查看详细信息
            url: '/detail/:appId',
            templateUrl: 'apply/applyDetail.html',
            controller:'ViewApplicationController'
        })
        //  请假记录
        .state('applications.completes', {  // 已完成
            url: '/completes/:pageNum',
            templateUrl: 'history/finished.html',
            controller:'CompletesController'
        })
        .state('applications.unapprovals', {  // 未审批
            url: '/unapprovals/:pageNum',
            templateUrl: 'history/unapproved.html',
            controller:'UnapprovedController'
        })
        .state('applications.uncompletes', {  // 未销假
            url: '/uncompletes/:pageNum',
            templateUrl: 'history/unback.html',
            controller:'UnbackController'
        })
        .state('applications.clear', {  // 员工销假
            url: '/clear/:appId',
            templateUrl: 'apply/applyClear.html',
            controller:'ClearApplicationController'
        })
        .state('applications.rejects', {  // 已拒绝
            url: '/rejects/:pageNum',
            templateUrl: 'history/rejected.html',
            controller:'RejectedController'

        })

        //  假务审批
        .state('applications.checks-unapprovals', {  // 未审批
            url: '/checks/unapprovals/:pageNum',
            templateUrl: 'approval/unapprove.html',
            controller:'UnapproveController'
        })
        .state('applications.check', {  // 审批某条记录
            url: '/check/:uid/:appId',
            templateUrl: 'approval/check.html',
            controller:'CheckApplicationController'
        })
        .state('applications.checks-history', {  // 审批记录
            url: '/checks/history',
            templateUrl: 'approval/approved.html',
            controller:'ApproveHistoryController'
        })

        //  代课管理
        .state('applications.substitute-apply', {  // 申请代课
            url: '/substitute/apply',
            templateUrl: 'overtime/apply.html',
            controller:'NewReplaceController'
        })
        .state('applications.substitute-history', {  // 代课记录
            url: '/substitute/history/:pageNum',
            templateUrl: 'overtime/history.html',
            controller:'ReplacesController'
        })
        //  假务明细
        .state('applications.details', {  // 查看明细
            url: '/details/:pageNum',
            templateUrl: 'record/detail.html',
            controller:'DetailsController'
        })

});

// 未审批
leaveMng.controller('LoginController', ['$scope','$rootScope','$location','$cookies','URL','Login', function($scope,$rootScope,$location,$cookies,URL,Login) {
    $scope.user = {};
    $scope.login = function(){
        Login.login(URL.urls.base.login,$scope.user)
            .success(function(data, status, headers, config) {
                console.log("success...");
                console.log(data);
                console.log(headers);

                //var cookie = decodeURIComponent(escape($cookies.USER_INFO));
                ////var cookie = decodeURIComponent(escape(jQuery.cookie('USER_INFO')));
                //
                //cookie = cookie.replace("\"","");
                //
                //var array= cookie.split(",");
                //
                //console.log(array);
                //
                //$rootScope.user = {
                //    uid:array[0],
                //    username:array[1],
                //    age:array[2],
                //    phone:array[3],
                //    role:array[4]
                //};
                //console.log($rootScope.user);

                $location.path("/applications");

            }).error(function(data, status, headers, config) {
                console.log("error...");
                alert("error");

            });
        console.log($scope.user);
    }
}]);

// 未审批
leaveMng.controller('NewApplicationController', ['$scope','$location','URL', function($scope,$location,URL) {
    $scope.submit = function(){
        console.log($("#apply").serializeArray());
        console.log();
        $.ajax({
            url: URL.urls.applications.newApply,
            type: 'post',
            dataType: 'json',
            data:$("#apply").serializeArray()
        }).success(function(data){
            if(data.state == 200) {
                alert("添加成功：" + data.data);
                window.location.href=window.location.href.replace("new","unapprovals/1");

            } else{
                alert("添加失败："+data.data);
            }
        }).error(function(){
            alert("添加失败："+data.data);
        });
    }
}]);

leaveMng.controller('ViewApplicationController', ['$scope','$state','$stateParams','Applications','URL', function($scope, $state, $stateParams,Applications,URL) {
    //这里可以根据路由上传递过来的bookType参数加载不同的数据

    Applications.applications(URL.urls.applications.detail+$stateParams.appId)
        .success(function(data, status, headers, config) {
            console.log("success...");
            console.log(data);
            $scope.application = data.data;
        }).error(function(data, status, headers, config) {
            console.log("error...");
        });
}]);


leaveMng.controller('ClearApplicationController', ['$scope','$location','$state','$stateParams','Applications','URL','Clear', function($scope,$location, $state, $stateParams,Applications,URL,Clear) {
    //这里可以根据路由上传递过来的bookType参数加载不同的数据

    Applications.applications(URL.urls.applications.detail+$stateParams.appId)
        .success(function(data, status, headers, config) {
            console.log("success...");
            console.log(data);
            $scope.application = data.data;
        }).error(function(data, status, headers, config) {
            console.log("error...");
        });


    $scope.clear = function(){
        Clear.clear(URL.urls.applications.clear,$scope.application.id)
            .success(function(data, status, headers, config) {
                console.log("success...");
                console.log(data);

                //$('#my-confirm').modal({});
                //$("#my-confirm").find(".modal-btn").on("click", function (e) {
                //    alert(true)
                //
                //});
                alert("操作成功");
                $location.path("/applications/uncompletes/1");

            }).error(function(data, status, headers, config) {
                alert("操作失败");
                console.log("error...");
            });
    };
}]);

// 已完成
leaveMng.controller('CompletesController', ['$scope','$state','$stateParams','Applications','URL', function($scope, $state, $stateParams,Applications,URL) {
    console.log(URL.urls);

    //这里可以根据路由上传递过来的bookType参数加载不同的数据
    console.log($stateParams);

    Applications.applications(URL.urls.applications.completes+($stateParams.pageNum?$stateParams.pageNum:1))
        .success(function(data, status, headers, config) {
            console.log("success...");
            console.log(data);
            $scope.applications = data.data.results;
        }).error(function(data, status, headers, config) {
            console.log("error...");
        });
}]);

// 未审批
leaveMng.controller('UnapprovedController', ['$scope','$state','$stateParams','Applications','URL', function($scope,$state, $stateParams,Applications,URL) {
    console.log(URL.urls);
    Applications.applications(URL.urls.applications.unapprovals+($stateParams.pageNum?$stateParams.pageNum:1))
        .success(function(data, status, headers, config) {
            console.log("success...");
            console.log(data);
            $scope.applications=data.data.results;
        }).error(function(data, status, headers, config) {
            console.log("error...");
        });
}]);

// 未销假
leaveMng.controller('UnbackController', ['$scope','$state','$stateParams','Applications','URL', function($scope,$state, $stateParams,Applications,URL) {
    console.log(URL.urls);
    Applications.applications(URL.urls.applications.uncompletes+($stateParams.pageNum?$stateParams.pageNum:1))
        .success(function(data, status, headers, config) {
            console.log("success...");
            console.log(data);
            $scope.applications=data.data.results;
        }).error(function(data, status, headers, config) {
            console.log("error...");
        });
}]);

// 已拒绝
leaveMng.controller('RejectedController', ['$scope','$state','$stateParams','Applications','URL', function($scope,$state, $stateParams,Applications,URL) {
    console.log(URL.urls);
    Applications.applications(URL.urls.applications.rejects+($stateParams.pageNum?$stateParams.pageNum:1))
        .success(function(data, status, headers, config) {
            console.log("success...");
            console.log(data);
            $scope.applications=data.data.results;
        }).error(function(data, status, headers, config) {
            console.log("error...");
        });
}]);



// 管理员未审批
leaveMng.controller('UnapproveController', ['$scope','$state','$stateParams','Applications','URL', function($scope,$state, $stateParams,Applications,URL) {
    console.log(URL.urls);
    Applications.applications(URL.urls.applications.approves+($stateParams.pageNum?$stateParams.pageNum:1))
        .success(function(data, status, headers, config) {
            console.log("success...");
            console.log(data);
            $scope.applications=data.data.results;
        }).error(function(data, status, headers, config) {
            console.log("error...");
        });
}]);


// 管理员审批历史记录
leaveMng.controller('ApproveHistoryController', ['$scope','$state','$stateParams','Applications','URL', function($scope,$state, $stateParams,Applications,URL) {
    console.log(URL.urls);
    Applications.applications(URL.urls.applications.approved+($stateParams.pageNum?$stateParams.pageNum:1))
        .success(function(data, status, headers, config) {
            console.log("success...");
            console.log(data);
            $scope.applications=data.data.results;
        }).error(function(data, status, headers, config) {
            console.log("error...");
        });
}]);

// 审批
leaveMng.controller('CheckApplicationController', ['$scope','$location','$state','$stateParams','Applications','URL','Approve','Reject',function($scope,$location, $state, $stateParams,Applications,URL,Approve,Reject) {
    //这里可以根据路由上传递过来的bookType参数加载不同的数据

    Applications.applications(URL.urls.applications.check+$stateParams.uid+"/"+$stateParams.appId)
        .success(function(data, status, headers, config) {
            console.log("success...");
            console.log(data);
            $scope.application = data.data.application;
            $scope.user = data.data.userInfo;
        }).error(function(data, status, headers, config) {
            console.log("error...");
        });

    $scope.approve = function(){
        Approve.approve(URL.urls.applications.approve,$scope.application.id)
            .success(function(data, status, headers, config) {
                console.log("success...");
                console.log(data);

                //$('#my-confirm').modal({});
                //$("#my-confirm").find(".modal-btn").on("click", function (e) {
                //    alert(true)
                //
                //});
                alert("操作成功");
                $location.path("/applications/checks/unapprovals/1");

            }).error(function(data, status, headers, config) {
                alert("操作失败");
                console.log("error...");
            });
    };

    $scope.reject = function(){
        Reject.reject(URL.urls.applications.reject,$scope.application.id)
            .success(function(data, status, headers, config) {
                console.log("success...");
                console.log(data);
                //$('#my-confirm').modal({});
                alert("操作成功");
                $location.path("/applications/checks/unapprovals/1");
            }).error(function(data, status, headers, config) {
                alert("操作失败");
                console.log("error...");
            });
    };

}]);



// 申请代课
leaveMng.controller('NewReplaceController', ['$scope','$location','URL','Replace', function($scope,$location,URL,Replace) {
    console.log(URL.urls);
    $scope.apply = {
        uid1:URL.urls.base.uid,
    };

    $scope.submit = function(){
        console.log($scope.apply);
        Replace.replace(URL.urls.applications.substitute,$scope.apply)
            .success(function(data, status, headers, config) {
                console.log("success...");
                console.log(data);
                //$('#my-confirm').modal({});
                alert("操作成功");
                $location.path("/substitute/history");
            }).error(function(data, status, headers, config) {
                alert("操作失败");
                console.log("error...");
            });
    }

}]);

// 代课记录
leaveMng.controller('ReplacesController', ['$scope','$state','$stateParams','URL','Replaces', function($scope,$state,$stateParams,URL,Replaces) {
    console.log(URL.urls);
    Replaces.replaces(URL.urls.applications.substitutes+($stateParams.pageNum?$stateParams.pageNum:1))
        .success(function(data, status, headers, config) {
            console.log("success...");
            console.log(data);
            $scope.replaces = data.data.results;
            //$('#my-confirm').modal({});
        }).error(function(data, status, headers, config) {
            alert("操作失败");
            console.log("error...");
        });

}]);


// 代课记录
leaveMng.controller('DetailsController', ['$scope','$state','$stateParams','URL','Details', function($scope,$state,$stateParams,URL,Details) {
    console.log(URL.urls);
    Details.details(URL.urls.applications.details+($stateParams.pageNum?$stateParams.pageNum:1))
        .success(function(data, status, headers, config) {
            console.log("success...");
            console.log(data);
            $scope.details = data.data.results;
            //$('#my-confirm').modal({});
        }).error(function(data, status, headers, config) {
            alert("操作失败");
            console.log("error...");
        });

}]);