package com.study.board;

import java.io.IOException;
import java.net.*;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.util.MyUtil;

public class BoardServlet extends HttpServlet 
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
		
		BoardDAO board = new BoardDAO();
		BoardVO vo = new BoardVO();
		MyUtil myUtil = new MyUtil();
		
		// ����Ʈ ������
		if(uri.indexOf("fboardList.do") != -1){															
			
			int numPerPage = 10;
			int total_page = 0;
			int dataCount = 0;			
			int current_page = 0;
			
			// GET���� �޾ƿ��� pageNum�� ������ 1�� ����
			if(req.getParameter("pageNum") == null){
				current_page = 1;
			}else{			
				current_page = Integer.parseInt(req.getParameter("pageNum"));    // ���� ������ �� �޾ƿ���
			}
			
			//��� �̾Ƽ� List�� ����
			List<Object> lists = board.getBoardList(numPerPage, Integer.toString(current_page));
			
			if ( lists != null && lists.size() > 0 )
			{
				BoardVO info = (BoardVO)lists.get(0);
				dataCount = info.getTotal_count();
				total_page = myUtil.getPageCount(numPerPage, dataCount);
			}
			
			if(total_page<current_page)
				current_page=total_page;
			
			//���� �� ���ϱ�
			//���⼱ �������� page������ ����¡ �ϹǷ� �۸�� ���ϴ� start���� �̿���
			int start = (current_page-1)*numPerPage+1;
			int end = current_page*numPerPage;			
			
						
			// �۹�ȣ �����
			int listNum, n=0;
			Iterator<Object> it=lists.iterator();
			while(it.hasNext())
			{
				BoardVO data = (BoardVO)it.next();
				listNum = dataCount-(start+n-1);
				data.setListNum(listNum);
				n++;
			}
			
			String params="";
			String urlList="";
						
			if(params.length()!=0) {
				urlList=cp+"/board/fboardList.do?"+params;
			} else  {
				urlList=cp+"/board/fboardList.do";
			}
			
			req.setAttribute("lists", lists);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("pageNum", current_page);
			req.setAttribute("pageIndexList", myUtil.pageIndexList(current_page, total_page, urlList));
									
			forward(req, resp, "/boardView/fboardList.jsp");
		
		// �Է� ������
		}else if(uri.indexOf("fboardWrite.do") != -1){
			
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));    // ���� ������ �� �޾ƿ���
			HttpSession session = req.getSession();
			String id = (String)session.getAttribute("userId");
			
			//�α��� �ؾ߸� �۾��� ����
			if(id == null){
				resp.sendRedirect(cp+"/message/message.do?msg=no_login&path=/board/fboardList.do?pageNum="+pageNum);
			}else{			
				forward(req, resp, "/boardView/fboardWrite.jsp");
			}

		// �Է�ó��
		}else if(uri.indexOf("fboardWrite_proc.do") != -1){			
			vo.setUser_id(req.getParameter("user_id"));
			vo.setUser_name(req.getParameter("user_name"));						
			vo.setTitle(req.getParameter("title"));
			vo.setContent(req.getParameter("content"));
			vo.setBbs_id(req.getParameter("bbs_id"));

			int chk = board.insertBoard(vo);			
			resp.sendRedirect(cp+"/board/fboardList.do");
			
		// ���� ������			
		}else if(uri.indexOf("fBoardView.do") != -1){
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));    // ���� ������ �� �޾ƿ���
			int seq = Integer.parseInt(req.getParameter("seq")); // �۹�ȣ �޾ƿ���					
			BoardVO bbsInfo = board.boardView(seq);
			
			HttpSession session = req.getSession();
			String login_id = (String)session.getAttribute("userId");
			
			// �Խñ� ������ ������
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("seq", seq);
			req.setAttribute("name", bbsInfo.getUser_name());
			req.setAttribute("write_id", bbsInfo.getUser_id());
			req.setAttribute("login_id", login_id);
			req.setAttribute("id", bbsInfo.getUser_id());
			req.setAttribute("title", bbsInfo.getTitle());
			req.setAttribute("content", bbsInfo.getContent());
			req.setAttribute("date", bbsInfo.getInput_date());
			
			forward(req, resp, "/boardView/fboardView.jsp");
		//����������	
		}else if(uri.indexOf("fboardUpdate.do") != -1){
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));    // ���� ������ �� �޾ƿ���
			int seq = Integer.parseInt(req.getParameter("seq")); // �۹�ȣ �޾ƿ���					
			BoardVO bbsInfo = board.boardView(seq);
			
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("seq", seq);
			req.setAttribute("name", bbsInfo.getUser_name());
			req.setAttribute("title", bbsInfo.getTitle());
			req.setAttribute("content", bbsInfo.getContent());			
			
			
			forward(req, resp, "/boardView/fboardUpdate.jsp");
		//����ó��
		}else if(uri.indexOf("fboardUpdate_proc.do") != -1){
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));    // ���� ������ �� �޾ƿ���
			int seq = Integer.parseInt(req.getParameter("seq")); // �۹�ȣ �޾ƿ���			
			
			vo.setTitle(req.getParameter("title"));
			vo.setContent(req.getParameter("content"));
			vo.setSeq(seq);	
			
			int bbsupdate = board.boardUpdate(vo);
			
			//������ �̻������ ����Ʈ�� ������ ���â ���� ����Ʈ��������
			if(bbsupdate > 0){	
				resp.sendRedirect(cp+"/board/fboardList.do?pageNum="+ pageNum);
			}else{
				resp.sendRedirect(cp+"/message/message.do?msg=error&path=/board/fboardList.do?pageNum="+pageNum);
			}
		//�� ����	
		}else if(uri.indexOf("fboardDelete_proc.do") != -1){
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));    // ���� ������ �� �޾ƿ���
			int seq = Integer.parseInt(req.getParameter("seq")); // �۹�ȣ �޾ƿ���
															
			int bbsdelete = board.boardDelete(seq);
			
			//������ �̻������ ����Ʈ�� ������ ���â ���� ����Ʈ��������
			if(bbsdelete > 0){
				resp.sendRedirect(cp+"/board/fboardList.do?pageNum="+ pageNum);
			}else{
				resp.sendRedirect(cp+"/message/message.do?msg=error&path=/board/fboardList.do?pageNum="+pageNum);
			}
		}
		
		
		
	}
	
}
