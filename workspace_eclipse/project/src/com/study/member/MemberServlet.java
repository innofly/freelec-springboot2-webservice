package com.study.member;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.study.main.*;


public class MemberServlet extends HttpServlet 
{
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		process(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		process(req, resp);
	}
	
	protected void forward(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException 
	{
		RequestDispatcher rd = req.getRequestDispatcher(path);
		rd.forward(req, resp);
	}
	
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String cp = req.getContextPath();
		String uri = req.getRequestURI();
		
		if(uri.indexOf("join.do") != -1)			
		{
			//회원가입 페이지로~
			forward(req, resp, "/joinView/join.jsp");
			
		}else if(uri.indexOf("join_proc.do") != -1){
			// 회원가입 로직~
			MemberDAO join = new MemberDAO();
			MemberVO vo = new MemberVO();			
			
			vo.setUser_id(req.getParameter("user_id"));
			vo.setPass(req.getParameter("pass"));
			vo.setUser_name(req.getParameter("user_name"));
			vo.setEmail(req.getParameter("email"));
			vo.setPhone_num(req.getParameter("phone_num"));
			vo.setBirthday(req.getParameter("birthday"));
			vo.setAddress(req.getParameter("address"));
			vo.setLocation(req.getParameter("location"));
			
			int chk = join.insertMember(vo);
			// 가입 처리 후 alert메세지를 표현하기 위해 message서블릿과 페이지를 만들어서
			// 메세지 코드와 메세지 보인 후 보낼 경로를 보냄
			// 가입이 정상적으로 되면 chk의 리턴값 1 아니면 0
			if(chk > 0){								
				//resp.sendRedirect(cp+"/main/main.do");
				resp.sendRedirect(cp+"/message/message.do?msg=join_ok&path=/main/main.do");
			}else{
				resp.sendRedirect(cp+"/message/message.do?msg=join_no&path=/main/main.do");
			}
							
		}else if(uri.indexOf("login_proc.do") != -1){			
			
			LoginDAO login = new LoginDAO();			
			String id = req.getParameter("userId");  // 아이디 받아옴
			String pass = req.getParameter("userPw");  // 비번 받아옴
						
			MemberVO vo = login.loginMember(id, pass);  // 아이디와 비번으로 회원인지 검색
						
			if(vo == null){
				//로그인 실패
				resp.sendRedirect(cp+"/message/message.do?msg=login_no&path=/main/main.do");				
			}else{			
				// 로그인 성공
				// 세션은 브라우저당 하나가 만들어지고 클라이언트정보를 서버에 저장
				// 보통 로그인 정보를 저장
				HttpSession session = req.getSession();
				session.setMaxInactiveInterval(60*20); //세션 유지기간 설정(초단위)
							
				// 세션에 정보를 저장
				// 많은 정보를 저장할 경우에는 세션에 저장할 클래스를 만들고 객체를 저장
				session.setAttribute("userId", vo.getUser_id());
				session.setAttribute("userName", vo.getUser_name());
				session.setAttribute("phone_num", vo.getPhone_num());
				session.setAttribute("address", vo.getAddress());
				session.setAttribute("email", vo.getEmail());
				session.setAttribute("location", vo.getLocation());				
										
				resp.sendRedirect(cp+"/main/main.do");
				//resp.sendRedirect(cp+"/message/message.do?msg=login_ok&path=/main/main.do");
			}
			
		}else if(uri.indexOf("logout.do") != -1){
			//로그아웃
			HttpSession session = req.getSession();
			// 세션에 저장된 정보를 삭제
			session.removeAttribute("userId"); // 세션에 저장된 값 삭제
			session.removeAttribute("userName");			
			session.removeAttribute("phone_num");
			session.removeAttribute("address");
			session.removeAttribute("email");
			session.removeAttribute("location");			
			session.invalidate();// 세션에 저장된 모든 정보를 지우고 세션을 초기화
			
			resp.sendRedirect(cp+"/main/main.do");			
		}
		
	}

}
