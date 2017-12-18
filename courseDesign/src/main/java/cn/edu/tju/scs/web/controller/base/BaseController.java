package cn.edu.tju.scs.web.controller.base;

import cn.edu.tju.scs.dto.response.BizCode;
import cn.edu.tju.scs.dto.response.ResponseCode;
import cn.edu.tju.scs.exception.AuthException;
import cn.edu.tju.scs.exception.ResourceException;
import cn.edu.tju.scs.exception.UserException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * BaseController 异常处理的基类
 * Created by jack on 2016/1/5.
 */
public class BaseController {
//    @ExceptionHandler
//    public @ResponseBody
//    ResponseCode<Object> handleException(Exception ex, HttpServletRequest request,HttpServletResponse response) throws IOException {
//        if(ex instanceof UserException){
//            return new ResponseCode<Object>(BizCode.FAIL,ex.getMessage());
//        }else if(ex instanceof ResourceException && ((ResourceException) ex).getType() == ResourceException.NON_EXISTENT_TYPE){
//            return new ResponseCode<Object>(BizCode.NON_EXISTENT,ex.getMessage());
//        }else if(ex instanceof  RuntimeException){
//            return new ResponseCode<Object>(BizCode.FAIL,ex.getMessage());
//        }
//        return new ResponseCode<Object>(BizCode.FAIL,ex.getMessage());
//    }


    @ExceptionHandler
    public @ResponseBody
    ResponseCode<Object> handleException(Exception ex, HttpServletRequest request,HttpServletResponse response) throws IOException {
        if(ex instanceof UserException){
            System.out.println("user exception");
            response.setStatus(BizCode.AUTHORIZE_FAILED.getState());
            return new ResponseCode<Object>(BizCode.AUTHORIZE_FAILED,ex.getMessage());
        }else if(ex instanceof ResourceException && ((ResourceException) ex).getType() == ResourceException.NON_EXISTENT_TYPE){
            System.out.println("resource exception");

            response.setStatus(BizCode.NON_EXISTENT.getState());
            return new ResponseCode<Object>(BizCode.NON_EXISTENT,ex.getMessage());
        }else if(ex instanceof AuthException){
            System.out.println("auth exception");
            response.setStatus(BizCode.AUTH_FAILED.getState());
            return new ResponseCode<Object>(BizCode.AUTH_FAILED,ex.getMessage());
        }else if(ex instanceof  RuntimeException){
            System.out.println("runtime exception");
            ex.printStackTrace();
            response.setStatus(BizCode.FAIL.getState());
            return new ResponseCode<Object>(BizCode.FAIL,ex.getMessage());
        }
        System.out.println("other exception");

        response.setStatus(BizCode.FAIL.getState());
        return new ResponseCode<Object>(BizCode.FAIL,ex.getMessage());
    }
}

