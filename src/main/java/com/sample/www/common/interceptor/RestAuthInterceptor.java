package com.sample.www.common.interceptor;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sample.www.common.util.CommonUtil;


public class RestAuthInterceptor extends HandlerInterceptorAdapter {
	
	private final String[] accessKey = {"restAuth", "apiKey"};

	@SuppressWarnings("unchecked")
	@Override
	// preHandle : controller 이벤트 호출전
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws IOException  {
		RestAuthCheck restAuthCheck = ((HandlerMethod) handler).getMethodAnnotation(RestAuthCheck.class);
		boolean isRestCheck = restAuthCheck.value();
		
		if(isRestCheck == false) {
			return true;	
		}else {
			
			//통신 정보를 받기위해 헤더에 있는 key값 비교
			String restAuth = request.getHeader(accessKey[0]);
			
			if(restAuth.equals(accessKey[1])) {
				return true;	
			} else {
				return false;
			}
		}
	}

	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) {
	}

	@Override
	public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) {
	}

	@Override
	public void afterConcurrentHandlingStarted(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
		//
	}

}
