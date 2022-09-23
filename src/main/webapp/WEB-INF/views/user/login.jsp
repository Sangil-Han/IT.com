<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 로그인</title>
<link rel="stylesheet" href="/resources/css/user.css" />
</head>
<body>
	<div id="wrapper">
		<h1>
			<a href="/home.do">IT.com</a>
		</h1>
		<form action="/user/login.do" method="post">
			<div class="join-input">
				<label for="user-id">아이디</label>
				<input type="text" id="login-id" name="loginId" autocomplete="off" required />
			</div>
			<div class="join-input">
				<label for="user-pw">비밀번호</label>
				<input type="password" id="login-pw" name="loginPw" autocomplete="off" required />
			</div>
			<div class="btn-area">
				<button>로그인</button>
			</div>
		</form>
		<ul id="user-opt">
			<li><a href="/user/findId.do">아이디 찾기</a></li>
			<li>|</li>
			<li><a href="/user/joinView.do">회원가입</a></li>
		</ul>
	</div>
</body>
</html>
