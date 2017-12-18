package cn.edu.tju.scs.web.annotation;

import java.lang.annotation.*;

/**
 * Created by haoxiaotian on 2016/9/26 21:53.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
    boolean required() default true;
}
