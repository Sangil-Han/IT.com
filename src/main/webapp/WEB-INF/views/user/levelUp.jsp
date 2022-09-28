<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IT.com : 등업 신청</title>
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
		<h3 class="mb-3">등업 신청</h3>
		<form action="/user/levelUp.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="userId" value="${sessionScope.loginUser.userId }" />
			<div class="form-check">
				<c:if test="${sessionScope.loginUser.userLevel eq '일반회원' }">
					<input type="radio" id="lecture-user" class="form-check-input" name="applicationLv" value="수강회원" checked />
          <label for="lecture-user" class="form-check-label">수강회원</label>
				</c:if>
      </div>
      <div class="form-check">
				<input type="radio" id="finish-user" class="form-check-input" name="applicationLv" value="수료회원" <c:if test="${sessionScope.loginUser.userLevel eq '수강회원' }">checked</c:if> />
        <label for="finish-user" class="form-check-label">수료회원</label>
			</div>
			<div class="input-group mb-3 col-md-3 offset-md-3 my-5" style="width: 50%">
        <input type="file" class="form-control" id="file">
        <label class="input-group-text" for="file">Upload</label>
      </div>
			<div class="d-flex justify-content-center my-5">
				<button class="btn btn-primary m-3 px-4">신청</button>
        <button type="button" class="btn btn-secondary m-3 px-4" onclick="location.href='/user/myPageView.do'">취소</button>
			</div>
		</form>
	</div>
</body>
</html>