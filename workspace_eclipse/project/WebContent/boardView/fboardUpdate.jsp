<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/top.jsp"%>
<br/><br/><br/>
	
	<form name="input" method="post" action="fboardUpdate_proc.do?seq=${requestScope.seq}&pageNum=${requestScope.pageNum}">
		
	   <table border="1" width="500" align="center"  cellpadding="3" cellspacing="1" bordercolor="#5DC75D">
	      <tr>
		     <td width="30%" align="center">이름</td>
			 <td>${requestScope.name}</td>
		  </tr>
		  <tr>
		     <td align="center">제목</td>
			 <td><input type="text" name="title" size="40" value="${requestScope.title}"></td>
		  </tr>
		  <tr>
		     <td align="center">내용</td>
			 <td><textarea name="content" rows="5" cols="40">${requestScope.content}</textarea></td>
		  </tr>
		  <tr>
		  
		     <td colspan="2" align="center">
			    <input type="submit" value="수정" /> &nbsp;&nbsp;
				<input type="reset" value="다시" /> &nbsp;&nbsp;
				<input type="button" onClick="location.href='fboardList.do?pageNum=${requestScope.pageNum}'" value="목록" />
			 </td>
		  </tr>
	   </table>
	   
	</form>
<%@include file="../inc/bottom.jsp"%>