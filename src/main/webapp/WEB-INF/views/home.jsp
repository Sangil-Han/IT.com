<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>IT.com : Main</title>
<link href="/resources/css/menubar-style.css" rel="stylesheet">
<link href="/resources/css/header-style.css" rel="stylesheet">
</head>
<body>
	<h1>메인 페이지</h1>
	<div class="header">
		<div class="Logo-area">
			<img src="/resources/images/Logo.png">
		</div>
		<form class="form-area" action="" method="post">
			<div class="search-area">
				<input class="search" type="text" placeholder="검색어 입력"> <input
					type="button" class="img_btn">
			</div>
		</form>
		<c:if test="${empty sessionScope.loginUser }">
			<div class="login-area">
				<table align="right">
					<tr>
						<td rowspan="2">
							<button onclick="location.href='/user/loginView.do'">로그인</button>
							<button onclick="location.href='/user/joinView.do'">회원가입</button>
						</td>
					</tr>
				</table>
			</div>
		</c:if>
		<c:if test="${not empty sessionScope.loginUser }">
			<table>
				<tr>
					<td>
						<a href="/user/myPageView.do">${sessionScope.loginUser.userId }</a>님 환영합니다
					</td>
				</tr>
				<tr>
					<td><a href="/user/logout.do">로그아웃</a></td>
				</tr>
			</table>
		</c:if>
	</div>
	<div class="nav-area">
		<div class="menu" onclick="">HRD수강평</div>
		<div class="menu" onclick="location.href='/cBoard/consultList.do'">상담후기
			게시판</div>
		<div class="menu" onclick="location.href='/lectureBoard/list.do'">수강후기
			게시판</div>
		<div class="menu" onclick="location.href='/finish/listView.do'">수료후기
			게시판</div>
		<div class="menu" onclick="">공지사항</div>
	</div>
</body>
</html>
