package cn.edu.tju.scs.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by haoxiaotian on 2016/11/29 21:33.
 */
public class CorsFilter implements Filter {


    private static final String crossOriginUrl = "http://47.90.12.82:8080";
//    private static final String crossOriginUrl = "http://localhost:8080";

    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        // TODO Auto-generated method stub
        HttpServletResponse res = (HttpServletResponse) response;
//        res.setHeader("Access-Control-Allow-Origin", "http://47.90.12.82:8080");
        res.setHeader("Access-Control-Allow-Origin", crossOriginUrl);
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
//        res.setHeader("Access-Control-Max-Age", "0");
        res.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");
        res.setHeader("Access-Control-Allow-Credentials", "true"); // 接收跨域的cookie

//        res.setHeader("XDomainRequestAllowed","1");
        chain.doFilter(request, response);
    }


    public void destroy() {
        // TODO Auto-generated method stub

    }

}
