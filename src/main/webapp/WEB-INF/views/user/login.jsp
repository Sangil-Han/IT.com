<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 로그인</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<link href="/resources/css/form.css" rel="stylesheet">
</head>
<body>
	<div id="wrapper" class="col-lg-4 offset-lg-4">
		<h1 style="margin-top: 100px;">
			<a href="/home.do" class="text-primary"><i class="fa-solid fa-desktop"></i>&nbsp;&nbsp;IT.com</a>
		</h1>
		<form action="/user/login.do" method="post">
			<div class="mb-3">
				<label for="user-id" class="form-label">아이디</label>
				<input type="text" id="login-id" class="form-control" name="loginId" autocomplete="off" required />
			</div>
			<div class="mb-3">
				<label for="user-pw" class="form-label">비밀번호</label>
				<input type="password" id="login-pw" class="form-control" name="loginPw" autocomplete="off" required />
			</div>
			<div class="d-grid gap-2">
				<button class="btn btn-primary mb-3">로그인</button>
			</div>
		</form>
		<div id="user-opt">
			<a href="/user/findId.do">아이디 찾기</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="/user/joinView.do">회원가입</a>
		</div>
	</div>
  <script src="https://kit.fontawesome.com/422d96f707.js" crossorigin="anonymous"></script>
</body>
</html>