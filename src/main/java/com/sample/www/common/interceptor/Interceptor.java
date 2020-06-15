package com.sample.www.common.interceptor;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class Interceptor extends HandlerInterceptorAdapter {

	@SuppressWarnings("unchecked")
	@Override
	// preHandle : controller 이벤트 호출전
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws IOException  {
		final HttpSession session = request.getSession();
		final String url = request.getRequestURL().toString();
		System.out.println(url);
		
		
		boolean returnRedirect = false;
		if(returnRedirect) {
			response.sendRedirect("http://www.naver.com");
			return false;
		}
		
		boolean sessionRemove = false;
		if(sessionRemove) {
			request.getSession().invalidate();	
		}
		
		boolean alert = false;
		if(alert) {
			response.setContentType("text/html;charset=utf-8");
			final PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>");
			out.println("alert('테스트alert');");
			out.println("</script>");
			return false;
		}
		
		
		return true;
	}

	@Override
	// postHandle : controller 호출 후 view 페이지 출력전
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) {
	}

	@Override
	// afterCompletion : controller + view 페이지 모두 출력 후
	public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) {
	}

	@Override
	// afterConcurrentHandlingStarted: 뭐 동시에 핸들링 해주는 메서드인대 정확히는 모르겠습니다
	public void afterConcurrentHandlingStarted(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
		//
	}

}
