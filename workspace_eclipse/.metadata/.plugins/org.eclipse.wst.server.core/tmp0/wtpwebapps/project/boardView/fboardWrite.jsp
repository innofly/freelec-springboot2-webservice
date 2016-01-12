<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/top.jsp"%>
<%

	//String cp = request.getContextPath();
	//String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;

	//String user_id = (String)session.getAttribute("userId");
	//String user_name = (String)session.getAttribute("userName");	
	
%>
<br/><br/><br/>
	
	<form name="input" method="post" action="fboardWrite_proc.do">
	<!-- hidden값으로 아이디와 이름을 넘감 bbs_id는 게시판 식별값으로 지금은 사용 안함 -->
	<input type="hidden" name="user_id" value="${sessionScope.userId}">
	<input type="hidden" name="user_name" value="${sessionScope.userName}">
	<input type="hidden" name="bbs_id" value="">
	
	   <table border="1" width="500" align="center"  cellpadding="3" cellspacing="1" bordercolor="#5DC75D">
	      <tr>
		     <td width="30%" align="center">이름</td>
			 <td>${sessionScope.userName}</td>
		  </tr>
		  <tr>
		     <td align="center">제목</td>
			 <td><input type="text" name="title" size="40"></td>
		  </tr>
		  <tr>
		     <td align="center">내용</td>
			 <td><textarea name="content" rows="5" cols="40"></textarea></td>
		  </tr>
		  <tr>
		  
		     <td colspan="2" align="center">
			    <input type="submit" value="쓰기" /> &nbsp;&nbsp;
				<input type="reset" value="다시" /> &nbsp;&nbsp;
				<input type="button" onClick="location.href='fboardList.do'" value="목록" />
			 </td>
		  </tr>
	   </table>
	</form>
<%@include file="../inc/bottom.jsp"%>