<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 공지사항 작성</title>
<link href="/resources/css/header.css" rel="stylesheet">
</head>
<body>
	<div id="wrap">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h2 id="page-title">
			<a href="/notice/boardView.do">공지사항</a>
		</h2>
		<form action="/notice/write.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="adminId" value="${sessionScope.loginAdmin.adminId }" />
			<label for="notice-title">제목</label>
			<input type="text" id="notice-title" name="noticeTitle" required/><br>
			<label for="notice-contents">내용</label>
			<textarea rows="10" cols="50" id="notice-contents" name="noticeContents" required></textarea><br>
			<input type="file" name="file"><br>
			<button>등록</button>
			<button type="button" onclick="location.href='/notice/boardView.do'">취소</button>
		</form>
	</div>
</body>
</html>