package cn.edu.tju.scs.exception;

/**
 * Created by haoxiaotian on 2016/11/23 14:19.
 */
public class AuthException extends RuntimeException{

    public static final String NO_PERMISSION = "没有操作权限！";


    public AuthException(String message) {
        super(message);
    }
}
