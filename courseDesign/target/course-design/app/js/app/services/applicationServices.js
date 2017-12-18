/**
 * Created by jack on 2016/11/21.
 */
'use strict';
/**
 * 这里是请假记录 http 请求模块
 * @type {[type]}
 */
var applicationService = angular.module("applicationService", []);

applicationService.factory('Login', ['$http',
    function($http) {
        var doRequest = function(path,user) {
            return $http({
                method: 'POST',
                url: path,
                data:user
            });
        };

        return {
            login: function(path,user) {
                return doRequest(path,user);
            }
        };
    }
]);


applicationService.factory('Applications', ['$http',
    function($http) {
        var doRequest = function(path) {
            return $http({
                method: 'GET',
                url: path
            });
        };

        return {
            applications: function(path) {
                return doRequest(path);
            }
        };
    }
]);


// 用户销假
applicationService.factory('Clear', ['$http',
    function($http) {
        var doRequest = function(path,id) {
            return $http({
                method: 'PUT',
                url: path+"/"+id
            });
        };

        return {
            clear: function(path,id) {
                return doRequest(path,id);
            }
        };
    }
]);

// 审批通过
applicationService.factory('Approve', ['$http',
    function($http) {
        var doRequest = function(path,id) {
            return $http({
                method: 'PUT',
                url: path+"/"+id
            });
        };

        return {
            approve: function(path,id) {
                return doRequest(path,id);
            }
        };
    }
]);

// 审批不通过
applicationService.factory('Reject', ['$http',
    function($http) {
        var doRequest = function(path,id) {
            return $http({
                method: 'PUT',
                url: path+"/"+id
            });
        };

        return {
            reject: function(path,id) {
                return doRequest(path,id);
            }
        };
    }
]);

// 申请代课
applicationService.factory('Replace', ['$http',
    function($http) {
        var doRequest = function(path,apply) {
            return $http({
                method: 'POST',
                url: path,
                data:apply
            });
        };

        return {
            replace: function(path,apply) {
                return doRequest(path,apply);
            }
        };
    }
]);

// 代课记录
applicationService.factory('Replaces', ['$http',
    function($http) {
        var doRequest = function(path) {
            return $http({
                method: 'GET',
                url: path
            });
        };

        return {
            replaces: function(path) {
                return doRequest(path);
            }
        };
    }
]);


applicationService.factory('Details', ['$http',
    function($http) {
        var doRequest = function(path) {
            return $http({
                method: 'GET',
                url: path
            });
        };

        return {
            details: function(path) {
                return doRequest(path);
            }
        };
    }
]);
