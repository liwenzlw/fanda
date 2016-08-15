package com.yisi.back.interceptor;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录拦截器，如果用户没有登录forward到登录界面
 * @author liwen
 *
 */
public class LoginInterceptor implements HandlerInterceptor{
 
	private static Logger logger = Logger.getLogger(LoginInterceptor.class);
   
	/**
     * 该方法在目标方法之前被调用.
     * 若返回值为 true, 则继续调用后续的拦截器和目标方法. 
     * 若返回值为 false, 则不会再调用后续的拦截器和目标方法. 
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
    	logger.info("[FirstInterceptor] preHandle");
        
        HttpSession session = request.getSession(false);
        if(null == session) {
        	request.getRequestDispatcher("/login.jsp").forward(request, response);
        	return false;
        } else if(null == session.getAttribute("loginBean")){
        	request.getRequestDispatcher("/login.jsp").forward(request, response);
        	return false;
        } else {
        	return true;
        }
    }
 
    /**
     * 调用目标方法之后, 但渲染视图之前. 
     * 可以对请求域中的属性或视图做出修改. 
     */
    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    	logger.info("[FirstInterceptor] postHandle");
    }
 
    /**
     * 渲染视图之后被调用. 释放资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    	logger.info("[FirstInterceptor] afterCompletion");
    }
}