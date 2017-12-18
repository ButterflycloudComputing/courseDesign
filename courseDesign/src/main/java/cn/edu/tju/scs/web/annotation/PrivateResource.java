package cn.edu.tju.scs.web.annotation;

import java.lang.annotation.*;

/**
 * Created by haoxiaotian on 2016/11/23 14:14.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrivateResource {
    public boolean requireOwner() default true;
}
