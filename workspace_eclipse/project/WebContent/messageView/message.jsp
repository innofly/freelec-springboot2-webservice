<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    //메세지와 분기될 경로를 받아서 처리하는 페이지    
	String cp = request.getContextPath();
	String msgchk = request.getParameter("msg");   // 메세지 코드를 받아옴
	String path   = request.getParameter("path");  // 분기시킬 경로를 받아옴
	String msg = "";                               // 메세지 저장 할 변수

	if(msgchk.equals("error")){
		msg = "처리되지 못함";
	}else if(msgchk.equals("join_ok")){
		msg = "회원가입 성공";
	}else if(msgchk.equals("join_no")){
		msg = "회원가입 실패";
	}else if(msgchk.equals("login_ok")){
		msg = "로그인 되었습니다.";
	}else if(msgchk.equals("login_no")){
		msg = "로그인 실패";
	}else if(msgchk.equals("no_login")){
		msg = "로그인 후 작성하세요";
	}
	
%>

<script>
	// 메세지 출력 후 페이지 분기
	function msgAlert(msg, cp, path){
		alert(msg);		
		location.replace(cp+path);
	}
	msgAlert('<%=msg%>', '<%=cp%>', '<%=path%>');
</script>
