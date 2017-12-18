//package cn.edu.tju.scs.web.controller;
//
//import cn.edu.tju.scs.dao.auth.UserDao;
//import cn.edu.tju.scs.dto.entity.UserDTO;
//import cn.edu.tju.scs.dto.entity.UserResponseDTO;
//import cn.edu.tju.scs.dto.response.BizCode;
//import cn.edu.tju.scs.dto.response.ResponseCode;
//import cn.edu.tju.scs.exception.ResourceException;
//import cn.edu.tju.scs.web.annotation.Logical;
//import cn.edu.tju.scs.web.annotation.Owner;
//import cn.edu.tju.scs.web.annotation.RequiresPermissions;
//import cn.edu.tju.scs.web.controller.base.BaseController;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
///**
// * Created by haoxiaotian on 2016/9/26 21:56.
// */
//
//@Controller
//@RequestMapping(value = "/users")
//public class UserController extends BaseController {
//
//    @Autowired
//    private UserDao userDao;
//
//
//// 增加用户
////  1:添加所有用户,包括管理员+
//    @RequiresPermissions(value = {"*","user:create:*"},logical = Logical.OR)
//    @RequestMapping(method = RequestMethod.POST)
//    public @ResponseBody
//    ResponseCode<UserDTO> createUser(HttpSession session){
//
//        return null;
//    }
//
////  2:只有网站客户,非管理员
//    @RequiresPermissions(value = {"*","user:create:*","user:create:subscribers"},logical = Logical.OR)
//    @RequestMapping(value = "/subscribers",method = RequestMethod.POST)
//    public @ResponseBody
//    ResponseCode<UserDTO> createSubscriber(HttpSession session){
//        return null;
//    }
//
//
////   查看用户
////    1:查看所有用户信息,包括管理员
//    @RequiresPermissions(value = {"*","user:browse:*"},logical = Logical.OR)
//    @RequestMapping(method = RequestMethod.GET)
//    public @ResponseBody
//    ResponseCode<List<UserResponseDTO>> queryAllUser(HttpSession session){
//        return new ResponseCode<List<UserResponseDTO>>(BizCode.SUCCESS,userDao.queryAllUser());
//    }
//
////    2:查看网站客户,非管理员
//    @RequiresPermissions(value = {"*","user:browse:*","user:browse:subscribers"},logical = Logical.OR)
//    @RequestMapping(value = "/subscribers",method = RequestMethod.GET)
//    public @ResponseBody
//    ResponseCode<List<UserResponseDTO>> queryAllSubscribers(HttpSession session){
//        return new ResponseCode<List<UserResponseDTO>>(BizCode.SUCCESS,userDao.queryAllSubscribers());
//    }
//
////    3:自己查看自己信息,或超级管理员查看
//    @Owner
//    @RequiresPermissions(value = {"*","user:browse:*"},logical = Logical.OR)
//    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
//    public @ResponseBody
//    ResponseCode<UserResponseDTO> querySpecificUser(HttpSession session,@PathVariable("id") Integer id){
//        UserResponseDTO userResponseDTO = userDao.querySpecificUser(id);
//        if(userResponseDTO == null){
//            throw new ResourceException(ResourceException.NON_EXISTENT_TYPE,ResourceException.NON_EXISTENT);
//        }
//        return new ResponseCode<UserResponseDTO>(BizCode.SUCCESS,userResponseDTO);
//    }
//
////   4:自己查看特定用户信息,或管理员查看
//    @Owner
//    @RequiresPermissions(value = {"*","user:browse:*","user:browse:subscribers"},logical = Logical.OR)
//    @RequestMapping(value = "/subscribers/{id}",method = RequestMethod.GET)
//    public @ResponseBody
//    ResponseCode<UserResponseDTO> querySpecificSubscriber(HttpSession session,@PathVariable("id") Integer id){
//        UserResponseDTO userResponseDTO = userDao.querySpecificUser(id);
//        if(userResponseDTO == null){
//            throw new ResourceException(ResourceException.NON_EXISTENT_TYPE,ResourceException.NON_EXISTENT);
//        }
//        return new ResponseCode<UserResponseDTO>(BizCode.SUCCESS,userResponseDTO);
//    }
//
//
//
////   删除用户
//    @RequiresPermissions(value = {"*","user:delete:*"},logical = Logical.OR)
//    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
//    public @ResponseBody
//    ResponseCode<String> deleteUser(HttpSession session,@PathVariable("id") Integer id){
//        return new ResponseCode<String>(BizCode.SUCCESS,"删除个数："+userDao.deleteUser(id));
//    }
//
//
////  修改用户
//    //    1:修改用户信息，包括管理员
//    @Owner
//    @RequiresPermissions(value = {"*","user:update:*"},logical = Logical.OR)
//    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
//    public @ResponseBody
//    ResponseCode<UserDTO> updateSpecificUser(HttpSession session,@PathVariable("id") Integer id){
//        return null;
//    }
//
//    //   2:修改客户信息,非管理员
//    @Owner
//    @RequiresPermissions(value = {"*","user:update:*","user:update:subscribers"},logical = Logical.OR)
//    @RequestMapping(value = "/subscribers/{id}",method = RequestMethod.PUT)
//    public @ResponseBody
//    ResponseCode<UserDTO> updateSpecificSubscriber(HttpSession session,@PathVariable("id") Integer id){
//        return null;
//    }
//
//
//}
