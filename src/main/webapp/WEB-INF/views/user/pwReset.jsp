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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<link href="/resources/css/header.css" rel="stylesheet">
<link href="/resources/css/common.css" rel="stylesheet">
</head>
<body>
  <div id="wrap" class="col-lg-10 offset-lg-1">
    <jsp:include page="../common/header.jsp"></jsp:include>
    <h2 id="page-title" class="text-bg-primary my-3 p-3 bg-opacity-50">
      <a href="/user/myPageView.do">My Page</a>
    </h2>
    <h3 class="mb-3">비밀번호 재설정</h3>
    <form action="/user/pwReset.do" method="post" onsubmit="return comparePw();">
      <div class="my-3 col-md-4 offset-md-4">
        <label for="origin-pw" class="form-label">현재 비밀번호</label>
        <input type="password" id="origin-pw" class="form-control" name="userPw" autocomplete="off" required />
      </div>
      <div class="mb-3 col-md-4 offset-md-4">
        <label for="new-pw" class="form-label">새로운 비밀번호</label>
        <input type="password" id="new-pw" class="form-control" name="newPw" autocomplete="off" required />
      </div>
      <div class="mb-3 col-md-4 offset-md-4">
        <label for="new-pw-re" class="form-label">새로운 비밀번호 확인</label>
        <input type="password" id="new-pw-re" class="form-control" name="newPwRe" autocomplete="off" required />
        <div id="pw-msg"></div>
      </div>
      <div class="d-flex justify-content-center my-5">
        <button class="btn btn-primary m-3 px-4">
          <c:if test="${sessionScope.loginUser eq null }"> onclick="loginView();"></c:if>
          변경
        </button>
        <button type="button" class="btn btn-secondary m-3 px-4" onclick="location.href='/user/myPageView.do';">
          취소
        </button>
      </div>
    </form>
  </div>
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
