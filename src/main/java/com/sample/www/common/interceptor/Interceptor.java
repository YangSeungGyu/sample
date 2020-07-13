package com.sample.www.common.interceptor;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sample.www.common.util.CommonUtil;


public class Interceptor extends HandlerInterceptorAdapter {

	@SuppressWarnings("unchecked")
	@Override
	// preHandle : controller 이벤트 호출전
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws IOException  {
		final HttpSession session = request.getSession();
		//response.sendRedirect("http://www.naver.com"); - 타 사이트로 리다이렉트
		//request.getSession().invalidate(); - 세션 초기화
		//if (StringUtils.contains(request.getHeader("accept"), "text/html")) {return true;} else {return false;} - 접근한게 어떤걸로 했는지 확인(html에서 온게 맞는지..) 엑셀접근오류용
		
		String ip = CommonUtil.getClientIP(request);
		final String url = request.getRequestURL().toString();
		System.out.println(url);
		
		
		//login url check
		final StringBuffer urlSbf = new StringBuffer();
		urlSbf.append(".*(")
		.append("/sample/").append("|/api/")
		.append(").*");
		
		String loginId = null;
		if(session.getAttribute("loginId") != null) {
			loginId = session.getAttribute("loginId").toString();	
		}
		
		boolean isLogin = StringUtils.isEmpty(loginId) ? false : true;
		if(!isLogin) {
			if(url.matches(urlSbf.toString())) {
				System.out.println("url에 포함됨.(비로그인 허용)");
			} else {
				System.out.println("url에 포함되지 않음.(로그인 후 접근 가능)");
			}
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
