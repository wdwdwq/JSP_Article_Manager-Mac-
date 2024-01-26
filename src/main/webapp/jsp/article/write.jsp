<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성</title>
</head>
<body>
	<h1>게시물 작성</h1>
	
	<form action="doWrite" method="post">
		<div>
			제목 : <input type="text" name="title" />
		</div>
		<div>
			내용 : <textarea name="body"></textarea>
		</div>
		
		<button>작성</button>
	</form>
	
	<div>
		<a href="list">목록</a>
	</div>
</body>
</html>