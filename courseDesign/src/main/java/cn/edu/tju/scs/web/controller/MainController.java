package cn.edu.tju.scs.web.controller;

import cn.edu.tju.scs.Config;
import cn.edu.tju.scs.dao.auth.RoleDao;
import cn.edu.tju.scs.dao.auth.UserDao;
import cn.edu.tju.scs.dto.entity.PermissionDTO;
import cn.edu.tju.scs.dto.entity.UserBasicInfo;
import cn.edu.tju.scs.dto.entity.UserDTO;
import cn.edu.tju.scs.dto.response.BizCode;
import cn.edu.tju.scs.dto.response.ResponseCode;
import cn.edu.tju.scs.entity.auth.Role;
import cn.edu.tju.scs.entity.auth.User;
import cn.edu.tju.scs.exception.UserException;
import cn.edu.tju.scs.service.UserService;
import cn.edu.tju.scs.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Takahashi on 2016/12/19.
 */
@Controller
public class MainController {


    @Autowired
    UserService userService;

    @Autowired
    RoleDao roleDao;

    @Autowired
    Config config;

    @Autowired
    UserDao userDao;
    @RequestMapping(value = "/login",method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public @ResponseBody
    ResponseCode<UserBasicInfo> login(HttpSession session, @RequestBody User user1,
                                HttpServletResponse response) throws UnsupportedEncodingException {
        User user = userService.hasMathUser(user1.getUid(),user1.getPassword());
        if(user == null){
            throw new UserException(UserException.WRONG_USER_EXCEPTION);
        }

        if(PasswordUtil.isValid(PasswordUtil.encodeOriginPassword(user1.getPassword()), user.getSalt(), user.getPassword())){
            UserBasicInfo userBasicInfo = userDao.queryUserByUid(user1.getUid());
            System.out.println(userBasicInfo);
//            String role = userBasicInfo.getRolename();
//            if(role != null && role != ""){
//                List<PermissionDTO> permissions = permissionDao.queryPermissionByRoles(role);
//                userBasicInfo.setPermissions(permissions);
//            }
            session.setAttribute("user",userBasicInfo);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(userBasicInfo.getUid());
            stringBuilder.append(",");
            stringBuilder.append(userBasicInfo.getUsername());
            stringBuilder.append(",");
            stringBuilder.append(userBasicInfo.getAge());
            stringBuilder.append(",");
            stringBuilder.append(userBasicInfo.getTel());
            stringBuilder.append(",");
            stringBuilder.append(userBasicInfo.getRolename());

            Cookie cookie = new Cookie(config.getCookieName(),URLEncoder.encode(stringBuilder.toString(), "UTF-8"));
            cookie.setPath(config.getCookiePath());
//            cookie.setMaxAge(config.getCookieMaxAge());
            cookie.setHttpOnly(false);
            if(config.getCookieDomain() != null){
                cookie.setDomain(config.getCookieDomain());
            }

            response.addCookie(cookie);

            return new ResponseCode<UserBasicInfo>(BizCode.SUCCESS,userBasicInfo);
        }else{
            throw new UserException(UserException.WRONG_USER_EXCEPTION);
        }
    }



    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public @ResponseBody
    ResponseCode<String> logout(HttpSession session,HttpServletResponse response){
        session.setAttribute("user", null);
//        Cookie cookieOver = new Cookie(config.getCookieName(),"");
//        cookieOver.setMaxAge(0);
//        cookieOver.setPath(config.getCookiePath());
//        cookieOver.setHttpOnly(false);
//        cookieOver.setDomain(config.getCookieDomain());
//        response.addCookie(cookieOver);

        return new ResponseCode<String>(BizCode.SUCCESS,"注销成功");
    }

}
