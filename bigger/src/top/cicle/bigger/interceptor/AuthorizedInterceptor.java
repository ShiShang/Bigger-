package top.cicle.bigger.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static top.cicle.bigger.common.biggerConstants.*;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import top.cicle.bigger.controller.UserController;
import top.cicle.bigger.domain.User;

public class AuthorizedInterceptor implements HandlerInterceptor{

	private static Logger logger = Logger.getLogger(AuthorizedInterceptor.class);
	
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		boolean flag=false;
		String servletPath=request.getRequestURL().toString();
		logger.info("登陆认证正在进行...");
		for(String uri:IGNORE_URI)
		{
			if(servletPath.contains(uri))
			{
				flag=true;
				break;
			}
		}
			if(!flag)
			{
				User user=(User)request.getSession().getAttribute(USER_SESSION);
				if(user==null)
				{
					logger.info("user_session 为null，未登陆");
					request.setAttribute("message", "你未登陆或登陆已过期，请重新登陆！");
					response.sendRedirect("/bigger/user/login");
					//request.getRequestDispatcher(LOGIN).forward(request, response);
					return flag;
				}
				else
				{
					flag=true;
					logger.info("认证结束，该用户已登陆！");
				}
						
			}	
		return flag;
	}

	
	
}
