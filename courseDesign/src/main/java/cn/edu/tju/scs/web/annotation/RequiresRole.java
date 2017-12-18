package cn.edu.tju.scs.web.annotation;

import java.lang.annotation.*;

/**
 * Created by haoxiaotian on 2016/11/23 13:07.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresRole {
    public String[] value();  // 权限列表

    public String logical();  // 逻辑

}
