<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 마이페이지</title>
<link href="/resources/css/header.css" rel="stylesheet">
</head>
<body>
	<div id="wrap">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h2 id="page-title">
			<a href="/user/myPageView.do">My Page</a>
		</h2>
		<section id="info-area">
			<div id="level-box">
				<h4>등급</h4>
				<ul>
					<li id="now-level"><a href="/user/levelHistoryView.do">${sessionScope.loginUser.userLevel }</a></li>
					<c:if test="${sessionScope.loginUser.userLevel ne '수료회원' }">
						<li id="level-up"><a href="/user/levelUpView.do">등업 신청</a></li>
					</c:if>
				</ul>
			</div>
			<div id="point-box">
				<h4>포인트</h4>
				<ul>
					<li><a href="/user/pointHistoryView.do">${sessionScope.loginUser.userPoint }P</a></li>
				</ul>
			</div>
			<div id="change-box">
				<h4>계정</h4>
				<ul>
					<li><a href="/user/pwResetView.do">비밀번호 재설정</a></li>
					<li><a href="/user/emailResetView.do">이메일 재설정</a></li>
				</ul>
			</div>
		</section>
		<hr />
		<section id="">
			<h3>내 작성글</h3>
			<table width="100%">
				<c:if test="${not empty mpList }">
					<tr>
						<th>NO</th>
						<th>게시판</th>
						<th>제목</th>
						<th>작성일</th>
					</tr>
					<c:forEach items="${mpList }" var="post" varStatus="i">
						<tr>
							<td>${mppi.rowCount - ((mppi.currentPage - 1 ) * mppi.rowLimit + i.index) }</td>
							<c:if test="${post.boardName eq 'CONSULT_BOARD_TBL' }">
								<td>상담후기</td>
							</c:if>
							<c:if test="${post.boardName eq 'LECTURE_BOARD_TBL' }">
								<td>수강후기</td>
							</c:if><c:if test="${post.boardName eq 'FINISH_BOARD_TBL' }">
								<td>수료후기</td>
							</c:if>
							<td>${post.postTitle}</td>
							<td>${post.createDate}</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="4"><c:if test="${mppi.startPage ne 1 }">
								<a href="/user/myPageView.do?page=${mppi.startPage - 1 }">[이전]</a>
							</c:if>
							<c:forEach var="p" begin="${mppi.startPage }" end="${mppi.endPage }">
								<c:if test="${mppi.currentPage eq p }">
									<b>${p }</b>
								</c:if>
								<c:if test="${mppi.currentPage ne p }">
									<a href="/user/myPageView.do?page=${p }">${p }</a>
								</c:if>
							</c:forEach>
							<c:if test="${mppi.endPage ne mppi.pageCount }">
								<a href="/user/myPageView.do?page=${mppi.endPage + 1 }">[다음]</a>
							</c:if>
						</td>
					</tr>
				</c:if>
				<c:if test="${empty mpList }">
					<tr>
						<td colspan="4">작성글이 존재하지 않습니다.</td>
					</tr>
				</c:if>
			</table>
		</section>
		<hr />
		<section id="withdraw-area">
			<h3>회원 탈퇴</h3>
			<form action="/user/withdraw.do" method="post">
				<label for="user-pw">현재 비밀번호</label>
				<input type="password" id="user-pw" name="userPw" />
				<button>탈퇴</button>
			</form>
		</section>
	</div>
</body>
</html>
