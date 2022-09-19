<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수강후기 게시판</title>
</head>
<body>
	<h1 align="center">게시글 목록</h1>
	<br><br>
	<table align="center" border="1">
		<tr>
			<th>제목</th>
			<th>등록일</th>
			<th>조회수</th>
			<th>추천수</th>
		</tr>
		<c:forEach items="${lbList }" var="lectureBoard">
			<tr>
				<td>${lectureBoard.lBoardTitle }</td>
				<td>${lectureBoard.lBoardCreateDate }</td>
				<td>${lectureBoard.lBoardCount }</td>
				<td>${lectureBoard.lBoardUpCount }</td>
			</tr>
		</c:forEach>
		<td>
			<button onclick="location.href='/lectureBoard/writeView.do';">글쓰기</button>
		</td>
	</table>
</body>
</html>