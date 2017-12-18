package cn.edu.tju.scs.exception;

/**
 * Created by haoxiaotian on 2016/11/23 21:06.
 */
public class ResourceException extends RuntimeException{
    public static final String NON_EXISTENT = "您查找的资源不存在或已经被删除！";  // type 0
    public static final Integer NON_EXISTENT_TYPE = 0;  // type 0
    private int type;
    public ResourceException(int type,String message) {
        super(message);
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
