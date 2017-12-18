package cn.edu.tju.scs.exception;

/**
 * Created by haoxiaotian on 2016/9/3 2:31.
 */
public class UserException extends RuntimeException{
    
    public static final String NULL_USERNAME_EXCEPTION = "请输入用户名！";
    public static final String WRONG_USER_EXCEPTION = "用户名或密码错误！";
    public static final String WRONG_CAPTCHA_EXCEPTION = "验证码错误！";
    public static final String NULL_PASSWORD_EXCEPTION = "请输入密码！";
    public static final String NOT_LOGIN_EXCEPTION = "未登录！";

    public static final String ACTIVE_USER_FAILED_EXCEPTION = "链接非法！";
    public static final String FIND_PASS_FAILED_EXCEPTION = "链接非法！";
    public static final String NO_ACTIVE_USER_EXCEPTION = "未激活用户！";
    public static final String WRONG_EMAIL_EXCEPTION = "非法邮箱！";

    public UserException(String message) {
        super(message);
    }

}
