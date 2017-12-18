package cn.edu.tju.scs.service;

import cn.edu.tju.scs.entity.auth.User;

/**
 * Created by haoxiaotian on 2016/12/15 19:31.
 */
public interface EmailService {

    /**
     * 激活刚刚注册用户
     * @param id
     * @return
     */
    public boolean activeUser(int id,String md5);

    public boolean resetPassword(int id,String md5);

    public void sendActiveMailTo(User user);

    public void sendForgetPasswordTo(User user);


}
