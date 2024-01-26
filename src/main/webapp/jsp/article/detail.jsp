<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Map<String, Object> articleMap = (Map<String, Object>) request.getAttribute("articleMap");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세보기</title>
</head>
<body>
	<h1><%= articleMap.get("id") %>번 게시물 상세보기</h1>
	
	<table border="1">
		<colgroup>
			<col />
			<col width="200"/>
		</colgroup>
		<tr>
			<th>번호</th>
			<td><%= articleMap.get("id") %></td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><%= articleMap.get("regDate") %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%= articleMap.get("title") %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><%= articleMap.get("body") %></td>
		</tr>
	</table>
	
	<div>
		<a href="list">목록</a>
		<a href="modify?id=<%= articleMap.get("id") %>">수정</a>
		<a href="doDelete?id=<%= articleMap.get("id") %>">삭제</a>
	</div>
</body>
</html>