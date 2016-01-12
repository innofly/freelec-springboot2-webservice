<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../inc/top.jsp"%>
<br/><br/><br/>
	   <table border="1" width="500" align="center"  cellpadding="3" cellspacing="1" bordercolor="#5DC75D">
	      <tr>
		     <td width="30%" align="center">이름</td>
			 <td>${requestScope.name}</td>
		  </tr>
		  <tr>
		     <td align="center">제목</td>
			 <td>${requestScope.title}</td>
		  </tr>
		  <tr>
		     <td align="center">내용</td>
			 <td>${requestScope.content}</td>
		  </tr>
		  <tr>
		     <td align="center">날짜</td>
			 <td>${requestScope.date}</td>
		  </tr>		  
		  <tr>
		  
		     <td colspan="2" align="center">
		     <!-- 글쓴 아이디와 로그인 아이디가 같아야 수정버튼 보임 -->
		     <c:if test="${requestScope.write_id == requestScope.login_id }">
		     	<input type="button" onclick="location.href='fboardUpdate.do?seq=${requestScope.seq}&pageNum=${requestScope.pageNum}'" value="수정" /> &nbsp;&nbsp;&nbsp;
		     	<input type="button" onclick="location.href='fboardDelete_proc.do?seq=${requestScope.seq}&pageNum=${requestScope.pageNum}'" value="삭제" /> &nbsp;&nbsp;&nbsp;
		     </c:if>
			    <input type="button" onclick="location.href='fboardList.do?pageNum=${requestScope.pageNum}'" value="목록" />				
			 </td>
		  </tr>
	   </table>
<%@include file="../inc/bottom.jsp"%>