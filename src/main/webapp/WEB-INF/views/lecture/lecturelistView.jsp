<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 수강후기 게시판</title>
</head>
<body>
	<div id="wrap">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h1 align="center">수강후기 게시판</h1>
		<table align="center" border="1">
			<tr>
				<th>제목</th>
				<th>등록일</th>
				<th>조회수</th>
				<th>추천수</th>
			</tr>
			<c:if test="${!empty lbList }">
				<c:forEach items="${lbList }" var="lectureBoard" varStatus="i">
					<tr>
						<td>
							<a href="#" onclick="lectureDetailView('${userId}', ${lectureBoard.lBoardNo }, ${currentPage });">${lectureBoard.lBoardTitle }</a>
						</td>
						<td>${lectureBoard.lBoardCreateDate }</td>
						<td>${lectureBoard.lBoardCount }</td>
						<td>${lectureBoard.lBoardUpCount }</td>
						<c:if test="${not empty sessionScope.loginAdmin }">
							<td>삭제</td>
						</c:if>
					</tr>
				</c:forEach>
				<tr align="center" height="20">
					<td colspan="6">
						<c:if test="${currentPage != 1 }">
							<a href="/lecture/${urlVal }.do?page=${currentPage - 1 }&searchCondition=${searchCondition}&searchValue=${searchValue}">[이전]</a>
						</c:if>
						<c:forEach var="p" begin="${startNavi }" end="${endNavi }">
							<c:if test="${currentPage eq p }">
								<b>${p }</b>
							</c:if>
							<c:if test="${currentPage ne p }">
								<a href="/lecture/${urlVal }.do?page=${p }&searchCondition=${searchCondition}&searchValue=${searchValue}">${p }</a>
							</c:if>
						</c:forEach>
						<c:if test="${maxPage > currentPage }">
							<a href="/lecture/${urlVal }.do?page=${currentPage + 1 }&searchCondition=${searchCondition}&searchValue=${searchValue}">[다음]</a>
						</c:if>
					</td>
				</tr>
			</c:if>
			<c:if test="${empty lbList }">
				<tr>
					<td colspan="6">검색한 결과가 없습니다</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="5" align="center">
					<form action="/lecture/search.do" method="get">
						<select name="searchCondition">
							<option value="all"
								<c:if test="${searchCondition == 'all' }">selected</c:if>>전체
							</option>
							<option value="title"
								<c:if test="${searchCondition == 'title' }">selected</c:if>>제목
							</option>
							<option value="contents"
								<c:if test="${searchCondition == 'contents' }">selected</c:if>>내용
							</option>
							<option value="center"
								<c:if test="${searchCondition == 'center' }">selected</c:if>>교육원명
							</option>
							<option value="local"
								<c:if test="${searchCondition == 'local' }">selected</c:if>>지역명
							</option>
						</select>
						<input type="text" name="searchValue" value="${searchValue }">
						<input type="submit" value="검색">
					</form>
				</td>
				<td>
					<button href="#" onclick="writeForm('${userId}');">글쓰기</button>
				</td>
			</tr>
		</table>
	</div>
	<script>
		function lectureDetailView(userId, adminId, lBoardNo, currentPage, userLevel, userPoint, viewable) {
			event.preventDefault();
			var level = '일반회원';
			var viewableNo = 'N';
			if(userId == "" && adminId == "") {
				if(confirm("로그인이 필요한 서비스입니다. 로그인하시겠습니까?")){
					location.href='/user/loginView.do';
				}
 			}
			if(userLevel == level && viewable == viewableNo) {
 				if(confirm("200포인트를 사용하여 열람하시겠습니까?")) {
 					if(userPoint >= 200){
	 					location.href='/lecture/detail.do?lBoardNo='+lBoardNo+'&page='+currentPage;
 					}else if(userPoint < 200){
 						alert("포인트가 부족합니다");
 					}
 				}
 			}else{
 				location.href='/lecture/detail.do?lBoardNo='+lBoardNo+'&page='+currentPage;
 			}
		}
		function writeForm(userId) {
			if(userId == ""){
				if(confirm("로그인이 필요한 서비스입니다 로그인하시겠습니까?")){
					location.href='/user/loginView.do';
				}
			}else {
				location.href='/lecture/writeView.do';
		}
		}
	</script>
</body>
</html>