<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IT.com : 등업 신청</title>
<link href="/resources/css/header.css" rel="stylesheet">
</head>
<body>
	<div id="wrap">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h2 id="page-title">
			<a href="/user/myPageView.do">My Page</a>
		</h2>
		<h3>등업 신청</h3>
		<form action="/level/up.do" method="post" enctype="multipart/form-data">
			<div>
				<c:if test="${sessionScope.loginUser.userLevel eq '일반회원' }">
					<input type="radio" id="lecture-user" name="applicationLv" value="수강회원" checked /> <label for="lecture-user">수강회원</label>
				</c:if>
				<input type="radio" id="finish-user" name="applicationLv" value="수료회원" <c:if test="${sessionScope.loginUser.userLevel eq '수강회원' }">checked</c:if> /> <label for="finish-user">수료회원</label>
			</div>
			<div class="notice"></div>
			<div class="notice"></div>
			<div>
				<input type="file" name="file" />
			</div>
			<div>
				<button>신청</button> <button type="button" onclick="location.href='/user/myPageView.do'">취소</button>
			</div>
		</form>
	</div>
</body>
</html>