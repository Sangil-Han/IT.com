<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">게시글 목록</h1>
	<table align="center" border="1">
		<tr>
			<th>제목</th>
			<th>등록일</th>
			<th>조회수</th>
			<th>추천수</th>
		</tr>
		<c:if test="${!empty cList }">
		<c:forEach items="${cList }" var="cBoard" varStatus="i">
		<tr>
			<td><a href="/cBoard/consultDetailView.do?cBoardNo=${cBoard.cBoardNo }&page=${currentPage}">${cBoard.cBoardTitle }</a></td>
			<td>${cBoard.cBoardCreateDate }</td>
			<td>${cBoard.cBoardCount }</td>
			<td>${cBoard.cBoardUpCount }</td>
		</tr>
		</c:forEach>
		<tr align="center" height="20">
			<td colspan="6">
			<c:if test="${currentPage > 5}">
				<a href="/cBoard/${urlVal }.do?page=${startNavi - 1 }&searchCondition=${searchCondition}&searchValue=${searchValue}">[이전]</a>
			</c:if>
				<c:forEach var="p" begin="${startNavi }" end="${endNavi }">
					<c:if test="${currentPage == p }">
						<b>${p }</b>
					</c:if>
					<c:if test="${currentPage != p }">
					<a href="/cBoard/${urlVal }.do?page=${p }&searchCondition=${searchCondition}&searchValue=${searchValue}">${p }</a>
					</c:if>
				</c:forEach>
				<c:if test="${maxPage-4 > currentPage }">
					<a href="/cBoard/${urlVal }.do?page=${endNavi + 1 }&searchCondition=${searchCondition}&searchValue=${searchValue}">[다음]</a>
				</c:if>
			</td>
		</tr>
		</c:if>
		<c:if test="${empty cList }">
			<tr>
				<td colspan="6">검색한 결과가 없습니다</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="5" align="center">
				<form action="/cBoard/consultSearch.do" method="get">
					<select name="searchCondition">
						<option value="all" <c:if test="${searchCondition == 'all' }">selected</c:if>>전체</option>
						<option value="title" <c:if test="${searchCondition == 'title' }">selected</c:if>>제목</option>
						<option value="contents" <c:if test="${searchCondition == 'contents' }">selected</c:if>>내용</option>
						<option value="center" <c:if test="${searchCondition == 'center' }">selected</c:if>>교육원명</option>
						<option value="local" <c:if test="${searchCondition == 'local' }">selected</c:if>>지역명</option>
					</select>
					<input type="text" name="searchValue" value="${searchValue }">
					<input type="submit" value="검색">
				</form>
			</td>
			<td>
				<button onclick="location.href='/cBoard/consultWriteFormView.do'">글쓰기</button>
			</td>
		</tr>
	</table>
</body>
</html>