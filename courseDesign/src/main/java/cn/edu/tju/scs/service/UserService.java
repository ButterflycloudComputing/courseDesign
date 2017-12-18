package cn.edu.tju.scs.service;


import cn.edu.tju.scs.dto.entity.AdminUserDTO;
import cn.edu.tju.scs.dto.entity.UserDTO;
import cn.edu.tju.scs.entity.auth.User;

import java.util.List;

/**
 * Created by haoxiaotian on 2016/9/3 2:07.
 */
public interface UserService {

    /**
     * 判断用户是否存在
     * @param phone
     * @param password
     * @return
     */
    public User hasMathUser(String phone, String password);

    /**
     * 根据邮箱查找用户
     * @param email
     * @return
     */
    public User findUserByEmail(String email);

    /**
     * 创建用户注册信息
     * @return
     */
    public int createDefautUser(User user);

    /**
     * 获取user列表
     * @param startPos
     * @param offset
     * @return
     */
    public List<AdminUserDTO> getUserList(int startPos, int offset);


    /**
     * 激活用户
     * @param id
     * @return
     */
    public int activeUser(int id);

    /**
     * 重置密码
     * @param id
     */
    public int resetPassword(int id);
}
