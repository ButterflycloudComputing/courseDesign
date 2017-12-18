package cn.edu.tju.scs.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class LoginInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	private List<String> excludedUrls;

	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}

//	返回值，表示是否需要将当前的请求拦截下来
//	如果返回false，请求将被停止
//	Object 是被拦截请求的目标
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		logger.info("preHandle");
		// excluded URLs:
		String requestUri = request.getRequestURI();
//		request.getMethod()
//		logger.info("请求的uri:" + requestUri);

		for (String url : excludedUrls) {
			if (requestUri.endsWith(url)) {
				return true;
			}
		}


		return true;
	}


//	可以通过 ModelAndView 改变显示的视图，或修改发往视图的方法
	@Override
	public void postHandle (HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
			modelAndView)throws Exception {
//		logger.info("postHandle");
	}

	@Override
	public void afterCompletion (HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {
//		logger.info("afterCompletion");
	}
}
