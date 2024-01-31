<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1>회원 가입</h1>
	<form action="doJoin" method="post"
		onsubmit="joinFormSubmit(this); return false;">
		<div>
			로그인 아이디 : <input type="text" name="loginId" placeholder="아이디를 입력해주세요" />
		</div>
		<div>
			로그인 비밀번호 : <input type="text" name="loginPw" />
		</div>
		<div>
			로그인 비밀번호 확인 : <input type="text" name="loginPwChk" />
		</div>
		<div>
			이름 : <input type="text" name="name" />
		</div>
		<button>작성</button>
	</form>

	<div>
		<a href="../home/main">메인으로 돌아가기</a>
	</div>
</body>
</html>