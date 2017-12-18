package cn.edu.tju.scs.utils;

/**
 * Created by haoxiaotian on 2016/9/3 1:33.
 */
public class PasswordUtil {

    /**
     * 传入未加密的密码，返回加密后密码及md5盐
     * @param password
     * @return
     */
    public static String[] encodePassword(String password){
        String salt = String.valueOf(System.currentTimeMillis());
        salt = EncryptUtil.encodeMD5String(salt);
        password = EncryptUtil.encodeMD5String(password);
        password = EncryptUtil.encodeMD5String(password + salt);
        return new String[]{password,salt};
    }

    public static String encodeOriginPassword(String password){
        return EncryptUtil.encodeMD5String(password);
    }

    /**
     * 用户名密码是否匹配
     * @param password 已经md5 过一次的密码（页面传入）
     * @param salt
     * @return
     */
    public static boolean isValid(String password,String salt,String realPassword){
//        String pass = EncryptUtil.encodeMD5String(password.trim()+salt);
        String pass = EncryptUtil.MD5(password.trim()+salt);
        return pass.equals(realPassword.trim());
    }
}
