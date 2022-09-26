<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 수료후기 게시판</title>
<link href="/resources/css/menubar-style.css" rel="stylesheet">
<link href="/resources/css/header-style.css" rel="stylesheet">
</head>
<body>
	<div id="wrap">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h1 align="center">수료후기 게시판</h1>
		<form action="/finish/search.do" method="get">
			<select name="searchOption">
				<!-- option 변경해서 검색했을 때 지정한 option값이 변하지 않게 하기 미구현 -->
				<option value="all"
					<c:if test="${searchOption eq 'all'}">selected</c:if>>전체</option>
				<option value="center"
					<c:if test="${searchOption eq 'center'}">selected</c:if>>교육원
				</option>
				<option value="local"
					<c:if test="${searchOption eq 'local'}">selected</c:if>>지역구
				</option>
				<option value="title"
					<c:if test="${searchOption eq 'title'}">selected</c:if>>제목
				</option>
				<option value="contents"
					<c:if test="${searchOption eq 'contents'}">selected</c:if>>내용
				</option>
			</select> <input type="text" name="searchValue" value="${searchValue }" /> <input
				type="submit" value="검색" />
		</form>
		<c:if test="${sessionScope.loginUser.userLevel eq '수료회원'}">
			<button onclick="location.href='/finish/registerView.do'">글쓰기</button>
		</c:if>
		<div id="contents"></div>

		<h1 align="center">게시글 목록</h1>
		<br> <br>
		<table align="center" border="1">
			<tr>
				<th>제목</th>
				<th>등록일</th>
				<th>조회수</th>
				<th>추천수</th>
			</tr>
			<c:if test="${!empty fList }">
				<c:forEach items="${fList }" var="fBoard" varStatus="i">
					<tr>
						<td>
							<%-- <a href="/finish/detailView.do?fBoardNo=${fBoard.fBoardNo}&page=${currentPage}" onclick="return showDetailView('${loginUser.userLevel}', ${loginUser.userPoint });">${fBoard.fBoardTitle }</a> --%>
							<a href="#"
							<c:if test="${sessionScope.loginUser ne null }"> onclick="showDetailView('${loginUser.userLevel}', '${loginUser.viewable}', ${loginUser.userPoint }, ${fBoard.fBoardNo}, ${currentPage });"</c:if>
							<c:if test="${sessionScope.loginUser eq null }"> onclick="notLogin()";</c:if>>
								${fBoard.fBoardTitle }</a>
						</td>
						<td>${fBoard.fBoardCreateDate }</td>
						<td>${fBoard.fBoardCount }</td>
						<td>${fBoard.fBoardUpCount }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty fList }">
				<tr>
					<td colspan="4" align="center"><b>데이터가 존재하지 않습니다.</b></td>
				</tr>
			</c:if>

			<!-- 페이징 처리 -->
			<tr align="center" height="20">
				<td colspan="6"><c:if test="${startNavi ne 1}">
						<a
							href="/finish/${urlVal }.do?page=${startNavi-1}&searchOption=${searchOption}&serachValue=${searchValue}">[이전]</a>
					</c:if> <c:forEach var="p" begin="${startNavi }" end="${endNavi }">
						<c:if test="${currentPage eq p }">
							<b>${p }</b>
						</c:if>
						<c:if test="${currentPage ne p}">
							<a href="/finish/${urlVal }.do?page=${p }&searchCondition=${searchOption}&serachValue=${searchValue}">${p }</a>
						</c:if>
					</c:forEach> <c:if test="${endNavi ne maxPage }">
						<a
							href="/finish/${urlVal }.do?page=${endNavi+1 }&searchOption=${searchOption}&serachValue=${searchValue}">[다음]</a>
					</c:if></td>
			</tr>
		</table>
	</div>
	<script>
		// 회원의 등급, 포인트를 확인하는 메소드
		function showDetailView(userLevel, viewable, userPoint, fBoardNo,
				currentPage) {
			event.preventDefault();
			const levelName = '수료회원';
			const requiredViewable = 'Y';
				// 수료회원이고 작성이력이 있으면
				if (userLevel == levelName&& viewable==requiredViewable) {
					location.href = "/finish/detailView.do?fBoardNo="
							+ fBoardNo + "&page=" + currentPage + "&point=0";
				}

				// 수료회원이 아니거나 작성이력이 없으면 포인트 사용
				else {
					if (confirm("300포인트를 사용하여 열람하시겠습니까?")) {
						if (userPoint >= 300) {
							location.href = "/finish/detailView.do?fBoardNo="
									+ fBoardNo + "&page=" + currentPage
									+ "&point=-300";
						} else {
							alert("포인트가 부족합니다");
						}
					}
				}
			}

		function notLogin() {
			alert("로그인이 필요합니다");
			location.href = "/user/loginView.do";
		}
	</script>
</body>
</html>