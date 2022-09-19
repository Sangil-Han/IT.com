<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Main</title>
<link href="/resources/css/menubar-style.css" rel="stylesheet">
<link href="/resources/css/header-style.css" rel="stylesheet">
</head>
<body>
	<div class="header">
		<div class="Logo-area">
			<img src="/resources/images/Logo.png">
		</div>
		<form class="form-area" action="" method="post">
			<div class="search-area">
				<input class="search" type="text" placeholder="검색어 입력">
				<input type="button" class="img_btn">
			</div>
		</form>
			<div class="login-area">
				<table align="right">
					<tr>
						<td rowspan="2">
							<input type="submit" value="로그인">
						</td>
					</tr>
				</table>
			</div>
	</div>
	<div class="nav-area">
		<div class="menu" onclick="">HRD수강평</div>
		<div class="menu" onclick="">상담후기 게시판</div>
		<div class="menu" onclick="location.href='/lectureBoard/list.do'">수강후기 게시판</div>
		<div class="menu" onclick="">수료후기 게시판</div>
		<div class="menu" onclick="">공지사항</div>
	</div>
</body>
</html>
