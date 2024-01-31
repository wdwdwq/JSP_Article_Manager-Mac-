<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int loginedMemberId = (int) request.getAttribute("loginedMemberId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
	<h1>메인 페이지</h1>
	
	<div>
		<% if (loginedMemberId == -1) { %>
			<a href="../member/join">회원가입</a>
			<a href="../member/login">로그인</a>
		<% } %>
		
		<% if (loginedMemberId != -1) { %>
			<a href="../member/doLogout">로그아웃</a>
		<% } %>
		
		<a href="../article/list">게시물 리스트</a>
	</div>
</body>
</html>