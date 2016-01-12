<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%	
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Hello Study!!!</title>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../common/js/jquery-1.11.2.min.js"></script>
    <!-- Bootstrap -->
    <link href="../common/css/bootstrap.min.css" rel="stylesheet">
    <link href="../common/css/bootstrap-theme.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
       
</head>
<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<%=cp%>/main/main.do"">메인</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">      
            <li><a href="javascript:alert('준비 중입니다')">스터디 홍보</a></li>
            <li><a href="<%=cp%>/board/fboardList.do">자유게시판</a></li>
            <li><a href="javascript:alert('준비 중입니다')">이거저거</a></li>
			<c:choose>
			      <c:when test="${empty sessionScope.userId}">
					  <li><a href="<%=cp%>/member/join.do">회원가입</a></li>
				</c:when>				
			</c:choose>
			<!-- 
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>
             -->
          </ul>
		  <c:choose>
			  <c:when test="${empty sessionScope.userId}">
	          <form name="loginForm" id="loginForm" method="post" action="../member/login_proc.do" class="navbar-form navbar-right">
	            <div class="form-group">
	              <input type="text" name="userId" id="userId" placeholder="ID" class="form-control">
	            </div>
	            <div class="form-group">
	              <input type="password" name="userPw" id="userPw" placeholder="Password" class="form-control">
	            </div>
	            <button type="submit" class="btn btn-success">Login</button>
	          </form>
	          </c:when>
  			  <c:otherwise>
  			  <div class="navbar-form navbar-right">  			  
				<span style="color:#FFF">${sessionScope.userName}님 하이~</span>
				<span><a href="<%=cp%>/member/logout.do">로그아웃</a></span>
			 </div>				
			  </c:otherwise>
          </c:choose>
        </div><!--/.navbar-collapse -->
      </div>
    </div>
<br/>
<br/>