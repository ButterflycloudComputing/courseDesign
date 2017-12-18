package cn.edu.tju.scs.web.aspect;

import cn.edu.tju.scs.dao.ApplicationDao;
import cn.edu.tju.scs.dto.entity.PermissionDTO;
import cn.edu.tju.scs.dto.entity.UserBasicInfo;
import cn.edu.tju.scs.dto.entity.UserDTO;
import cn.edu.tju.scs.entity.Application;
import cn.edu.tju.scs.exception.AuthException;
import cn.edu.tju.scs.exception.ResourceException;
import cn.edu.tju.scs.exception.UserException;
import cn.edu.tju.scs.web.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* Created by haoxiaotian on 2016/11/23 14:20.
*/

@Component
@Aspect
public class AuthAspect {

    @Autowired
    ApplicationDao applicationDao;

    private static Logger logger = LoggerFactory.getLogger(AuthAspect.class);

    @Pointcut("execution(* cn.edu.tju.scs.web.controller.*.*(..)) && !withoutAuthorization() && @annotation(requiresRole)")
    public void authCheck(RequiresRole requiresRole) {
    }

    @Pointcut("execution(* cn.edu.tju.scs.web.controller.MainController.*(..))")
    public void withoutAuthorization() {
    }


    @Pointcut("execution(* cn.edu.tju.scs.web.controller.*.*(..)) &&  @annotation(owner)")
    public void pointcutWithAnnotation(Owner owner) {}


    @Pointcut("execution(* cn.edu.tju.scs.web.controller.*.*(..)) &&  @annotation(login)")
    public void pointcutWithLogin(Login login) {}


//    @Around("!withoutAuthorization()")
//    public Object validateUser(ProceedingJoinPoint jp) throws Throwable {
//        System.out.println("test------------------------------------------------");
//        return jp.proceed();
//        if (!SessionUtil.isLogin()) {
//            // 未登录验证
//            StateCode stateCode = StateCode.buildCode(BizCode.WITHOUT_AUTHORIZATION);
//            stateCode.addData("loginUrl", Config.CAS_LOGIN_URL);
//            return stateCode;
//        }
//
//        try {
//            // 权限验证
//            MethodSignature signature = (MethodSignature) jp.getSignature();
//            Method method = signature.getMethod();
//            if (method.getAnnotationsByType(RequiredAdmin.class) != null && SessionUtil.isAdmin()) {
//                return StateCode.buildCode(BizCode.WRONGPERMISSION);
//            }
//            return jp.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//            return StateCode.buildCode(BizCode.FAIL);
//        }
//    }



    @Around(value = "authCheck(requiresRole)", argNames = "pjp,requiresRole")
    public Object aroundAuthorization(ProceedingJoinPoint pjp,RequiresRole requiresRole) throws Throwable {
        Object obj;
        System.out.println("@Around：执行目标方法之前...");

        //访问目标方法的参数：
        Object[] args = pjp.getArgs();
        HttpSession session = (HttpSession)args[0];
        UserBasicInfo userDTO = (UserBasicInfo) session.getAttribute("user");
        if(userDTO == null){   // 未登录
            logger.info("未登录！");
            throw new UserException(UserException.NOT_LOGIN_EXCEPTION);
        }

        // 具有的角色名
        String roleHad = userDTO.getRoleE();

        String [] roleNeed = requiresRole.value();
        String targetClassName = pjp.getTarget().getClass().getName();
        logger.error(targetClassName);

        String methodName = pjp.getSignature().getName();
        logger.error(methodName);
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        logger.error(method.getName());

        // 如果是个人私有资源
        if (method.getAnnotation(Owner.class) != null && method.getAnnotation(Owner.class).requireOwner()) {
            if(args.length > 1 && args[1] instanceof String){   // 第二个参数 /{id}
                if(userDTO.getUid().equals(args[1])){  // 是owner

                    // 如果未标记为：私有资源
                    if(method.getAnnotation(PrivateResource.class) == null || !method.getAnnotation(PrivateResource.class).requireOwner()){
                        logger.info("私有资源：并具有操作权限！");
                        obj = pjp.proceed();
                        System.out.println("@Around：执行目标方法之后...");
                        return obj;
                    }else{  //标记为：私有资源
                        if(args.length > 2 && args[2] instanceof Integer){
                            // 数据库查询资源
                            Application application = applicationDao.queryById((Integer)args[2]);
                            if(application == null){  // 对应资源不存在
                                // 404 ERROR
                                throw new ResourceException(ResourceException.NON_EXISTENT_TYPE,ResourceException.NON_EXISTENT);
                            }else{
                                if(!application.getUid().equals(args[1])){  // 不是资源的拥有者
                                    throw new AuthException(AuthException.NO_PERMISSION);
                                }else{
                                    logger.info("私有资源：并具有操作权限！");
                                    obj = pjp.proceed();
                                    System.out.println("@Around：执行目标方法之后...");
                                    return obj;
                                }
                            }
                        }
                    }

                }
            }
        }

        if(hasPermission(roleHad,roleNeed, requiresRole.logical())){
            logger.info("具有操作权限！");
            obj = pjp.proceed();
            System.out.println("@Around：执行目标方法之后...");
            return obj;
        }else{
            logger.info("没有操作权限！");
            throw new AuthException(AuthException.NO_PERMISSION);
        }

    }

    /**
     * 判断是否有操作权限
     * @param roleHad
     * @param roleNeed
     * @param logical
     * @return
     */
    private boolean hasPermission(String roleHad,String [] roleNeed,String logical){
        if(roleHad == null){
            return false;
        }

        if(roleNeed == null){
            return true;
        }
        logger.info("权限间的逻辑："+logical);

        Set<String> roleNeedSet = new HashSet<String>();
        Collections.addAll(roleNeedSet, roleNeed);
        logger.info("应有的权限："+roleNeedSet);

        Set<String> roleHadSet = new HashSet<String>();
        roleHadSet.add(roleHad);

        logger.info("具有的的权限："+roleHadSet);

        if(logical.equals(Logical.OR)){   // 或
            roleNeedSet.retainAll(roleHadSet);
            System.out.println("交集："+roleNeedSet);
            return !roleNeedSet.isEmpty();
        }else{   // 与
            roleNeedSet.removeAll(roleHadSet);
            System.out.println("并集："+roleHadSet);
            return roleHadSet.isEmpty();
        }
    }


}
