package com.test.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BoardkInterCeptor extends HandlerInterceptorAdapter {

	//컨트롤러 실행전에 호출
	//블로그 참조한 코드
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		System.out.println("게시판@ preHandle");
//		
//		String userid=(String) request.getAttribute("userid");
//		if(userid==null)
//		{
//			String path=request.getContextPath();		//주소값을 얻어옴
//			System.out.println("@"+path);
//			response.sendRedirect(path+"/member/loginForm");
//			return false;
//		}
//		
//		return super.preHandle(request, response, handler);
//	}
	
	//07_25숙제풀이 선생님이 해주시는 코드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		if(session.getAttribute("userid")!=null)
		{
			return true;
		}
		response.sendRedirect("/web/member/loginForm");
		return false;
	}
	
	
//	//컨트롤러 실행 후에 호출
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		System.out.println("게시판@ postHandle");
//	}
//	
//	//컨트롤러 실행 후에 화면전환 후 출력
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		System.out.println("게시판@ afterCompletion");
//	}
	
}
