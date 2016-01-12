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
			//ȸ������ ��������~
			forward(req, resp, "/joinView/join.jsp");
			
		}else if(uri.indexOf("join_proc.do") != -1){
			// ȸ������ ����~
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
			// ���� ó�� �� alert�޼����� ǥ���ϱ� ���� message������ �������� ����
			// �޼��� �ڵ�� �޼��� ���� �� ���� ��θ� ����
			// ������ ���������� �Ǹ� chk�� ���ϰ� 1 �ƴϸ� 0
			if(chk > 0){								
				//resp.sendRedirect(cp+"/main/main.do");
				resp.sendRedirect(cp+"/message/message.do?msg=join_ok&path=/main/main.do");
			}else{
				resp.sendRedirect(cp+"/message/message.do?msg=join_no&path=/main/main.do");
			}
							
		}else if(uri.indexOf("login_proc.do") != -1){			
			
			LoginDAO login = new LoginDAO();			
			String id = req.getParameter("userId");  // ���̵� �޾ƿ�
			String pass = req.getParameter("userPw");  // ��� �޾ƿ�
						
			MemberVO vo = login.loginMember(id, pass);  // ���̵�� ������� ȸ������ �˻�
						
			if(vo == null){
				//�α��� ����
				resp.sendRedirect(cp+"/message/message.do?msg=login_no&path=/main/main.do");				
			}else{			
				// �α��� ����
				// ������ �������� �ϳ��� ��������� Ŭ���̾�Ʈ������ ������ ����
				// ���� �α��� ������ ����
				HttpSession session = req.getSession();
				session.setMaxInactiveInterval(60*20); //���� �����Ⱓ ����(�ʴ���)
							
				// ���ǿ� ������ ����
				// ���� ������ ������ ��쿡�� ���ǿ� ������ Ŭ������ ����� ��ü�� ����
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
			//�α׾ƿ�
			HttpSession session = req.getSession();
			// ���ǿ� ����� ������ ����
			session.removeAttribute("userId"); // ���ǿ� ����� �� ����
			session.removeAttribute("userName");			
			session.removeAttribute("phone_num");
			session.removeAttribute("address");
			session.removeAttribute("email");
			session.removeAttribute("location");			
			session.invalidate();// ���ǿ� ����� ��� ������ ����� ������ �ʱ�ȭ
			
			resp.sendRedirect(cp+"/main/main.do");			
		}
		
	}

}
