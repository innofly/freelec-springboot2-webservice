<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>메인 페이지</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/common/WolfHarufacebookNav.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/common/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/common/jquery.WolfHarufacebookNav.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('.nav').WolfHaruNav();
});

function fnMovePage(url){
	document.getElementById("body").src = url;
}

</script>
</head>
<body>

	<div class="wrap">
	<div class="container">
			
			
<head>
<title>목록</title>
</head>

<table cellspacing=1 width=700 border=0>
	<tr><td>총 게시물수: </td><td><p align=right>페이지:</td></tr>
</table>

<table cellspacing=1 width=700 border=1>
	<tr>
	<td width=50><p align=center><img alt="새소식" src="common/logo.gif"/></p></td>
	<td width=100><p align=center>이름</p></td>
	<td width=320><p align=center>제목</p></td>
	<td width=100><p align=center>등록일</p></td>
	<td width=100><p align=center>조회수</p></td>
	</tr>

	<tr>
	
	<td width=50><p align=center></td>
	
	<td width=50><p align=center></p></td>
	
	<td width=100><p align=center></p></td>
	<td width=320><p align=center></p></td>
	
	<td width=100><p align=center></p></td>
	<td width=100><p align=center></p></td>
	</tr>

</table>

<table cellspacing=1 width=700 border=1>
	<tr><td>

	

	</td></tr>
</table>

<table width=700>
	<tr>
		<td><input type=button value="공지사항쓰기" OnClick="window.location='/BackEndProj/notice/notie_write.jsp'"></td>
		<td><form name=searchf method=post action="notice_list.jsp">
			<p align=right><input type=text name=dbsearch size=50  maxlength=50>
			<input type=submit value="공지사항찾기"></p></td>
	</tr>
</table>

					<iframe width="80%" height="100%" id="body" src="">
					</iframe>
				
			</div>
			
		<
		
	
	</div>
</body>
</html>