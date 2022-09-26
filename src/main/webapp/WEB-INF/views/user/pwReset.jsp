<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 비밀번호 재설정</title>
<link href="/resources/css/header.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<h2 id="page-title">
		<a href="/user/myPageView.do">My Page</a>
	</h2>
	<h3>비밀번호 재설정</h3>
	<form action="/user/pwReset.do" method="post" onsubmit="return comparePw();">
		<div class="reset-input">
			<label for="origin-pw">현재 비밀번호</label>
			<input type="password" id="origin-pw" name="userPw" autocomplete="off" required />
		</div>
		<div class="reset-input">
			<label for="new-pw">새로운 비밀번호</label>
			<input type="password" id="new-pw" name="newPw" autocomplete="off" required />
		</div>
		<div class="reset-input">
			<label for="new-pw-re">새로운 비밀번호 확인</label>
			<input type="password" id="new-pw-re" name="newPwRe" autocomplete="off" required />
			<div id="pw-msg"></div>
		</div>
		<button
			<c:if test="${sessionScope.loginUser eq null }"> onclick="loginView();"></c:if>>
			변경
		</button>
		<button type="button" onclick="location.href='/user/myPageView.do';">
			취소
		</button>
	</form>
	<script>
		function comparePw() {
			let pw = document.querySelector('#new-pw');
			let re = document.querySelector('#new-pw-re');
			if (pw.value != re.value) {
				document.querySelector('#pw-msg').innerHTML = '비밀번호가 일치하지 않습니다.';
				return false;
			} else {
				return true;
			}
		}
		function loginView(){
			event.preventDefault();
			alert('로그인이 필요한 서비스입니다. 로그인 화면으로 이동합니다.');
			location.href='/user/loginView.do';
		}
	</script>
</body>
</html>
