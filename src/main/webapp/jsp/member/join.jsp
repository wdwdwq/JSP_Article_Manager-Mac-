<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1>회원가입</h1>
	
	<script>
		const joinFormSubmit = function(form){
			form.loginId.value = form.loginId.value.trim();
			form.loginPw.value = form.loginPw.value.trim();
			form.loginPwChk.value = form.loginPwChk.value.trim();
			form.name.value = form.name.value.trim();
			
			if (form.loginId.value.length == 0) {
				alert('아이디를 입력해주세요');
				form.loginId.focus();
				return;
			}
			
			if (form.loginPw.value.length == 0) {
				alert('비밀번호를 입력해주세요');
				form.loginId.focus();
				return;
			}
			
			if (form.loginPwChk.value.length == 0) {
				alert('비밀번호 확인을 입력해주세요');
				form.loginId.focus();
				return;
			}
			
			if (form.loginPw.value != form.loginPwChk.value) {
				alert('비밀번호를 확인해주세요');
				form.loginPw.value = '';
				form.loginPwChk.value = '';
				form.loginPw.focus();
				return;
			}
			
			if (form.name.value.length == 0) {
				alert('이름을 입력해주세요');
				form.loginId.focus();
				return;
			}
			
			form.submit();
		}
	</script>
	
	<form action="doJoin" method="post" onsubmit="joinFormSubmit(this); return false;">
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