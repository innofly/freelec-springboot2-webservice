<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../inc/top.jsp"%>

<div style="margin:50px 0px 0px 200px">
			<table border="1">
				<caption>자유게시판</caption>
						<colgroup>
							<col width="50" />
							<col width="500" />
							<col width="100" />
							<col width="100" />							
						</colgroup>
						<thead>
							<tr>
								<th scope="col" style="text-align:center">번호</th>
								<th scope="col" style="text-align:center">제목</th>
								<th scope="col" style="text-align:center">이름</th>
								<th scope="col" style="text-align:center">날짜</th>							
							</tr>
						</thead>
						<tbody>
		<c:choose>
			<c:when test="${requestScope.dataCount == 0}">
						<tr>
							<td colspan="4" height="100" align="center">게시글이 없습니다.</td>
						</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${lists}" var="vo">
					<tr>
						<td height="20" style="text-align:center">${vo.listNum}</td>
						<td height="20">&nbsp;&nbsp;<a href="fBoardView.do?seq=${vo.seq}&pageNum=${requestScope.pageNum}">${vo.title}</a></td>
						<td height="20" style="text-align:center">${vo.user_name}</td>
						<td height="20" style="text-align:center">${vo.input_date.substring(0,10)}</td>
					</tr>
				</c:forEach>	
			</c:otherwise>
		</c:choose>

							
				</tbody>
				</table>
					<br/><br/>
					<p style="margin:00px 0px 0px 200px"> ${requestScope.pageIndexList}<br/><br/> </p>
				<input type="button" onClick="window.location='fboardWrite.do?pageNum=${pageNum}'" value="쓰기"/>
</div>
<%@include file="../inc/bottom.jsp"%>