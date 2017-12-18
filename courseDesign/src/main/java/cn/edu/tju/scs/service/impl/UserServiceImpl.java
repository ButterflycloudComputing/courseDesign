package cn.edu.tju.scs.service.impl;

import cn.edu.tju.scs.dao.auth.UserDao;
import cn.edu.tju.scs.dto.entity.AdminUserDTO;
import cn.edu.tju.scs.dto.entity.UserDTO;
import cn.edu.tju.scs.entity.auth.User;
import cn.edu.tju.scs.exception.UserException;
import cn.edu.tju.scs.service.RoleService;
import cn.edu.tju.scs.service.UserService;
import cn.edu.tju.scs.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haoxiaotian on 2016/9/3 2:08.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User hasMathUser(String uid, String password) {
        if(uid == null || "".equals(uid.trim())){
            throw new UserException(UserException.NULL_USERNAME_EXCEPTION);
        }

        if(password == null || "".equals(password.trim())){
            throw new UserException(UserException.NULL_PASSWORD_EXCEPTION);
        }

        return userDao.queryUser(uid);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.queryUserByEmail(email);
    }

    public int createDefautUser(User user) {
        String[] pswdAndSalt = PasswordUtil.encodePassword(user.getPassword());
        String finalPassword = pswdAndSalt[0];
        String salt = pswdAndSalt[1];
        user.setPassword(finalPassword);
        user.setSalt(salt);
        int res = userDao.createDefautUser(user);

        return res;
    }

    public List<AdminUserDTO> getUserList(int startPos, int offset) {


        return userDao.queryAdminUserList(startPos, offset);
    }

    @Override
    public int activeUser(int id) {
        return userDao.activeUser(id);
    }

    @Override
    public int resetPassword(int id) {
        String[] pswdAndSalt = PasswordUtil.encodePassword("000000");
        String finalPassword = pswdAndSalt[0];
        String salt = pswdAndSalt[1];
        return userDao.resetPassword(id,finalPassword,salt);
    }
}
