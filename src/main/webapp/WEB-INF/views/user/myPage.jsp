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
					<li id="now-level"><a href="/level/historyView.do">${sessionScope.loginUser.userLevel }</a></li>
					<c:if test="${sessionScope.loginUser.userLevel ne '수료회원' }">
						<li id="level-up"><a href="/level/upView.do">등업 신청</a></li>
					</c:if>
				</ul>
			</div>
			<div id="point-box">
				<h4>포인트</h4>
				<ul>
					<li><a href="/point/historyView.do">${sessionScope.loginUser.userPoint }P</a></li>
				</ul>
			</div>
			<div id="change-box">
				<h4>계정</h4>
				<ul>
					<li><a href="/user/pwChangeView.do">비밀번호 재설정</a></li>
					<li><a href="/user/emailChangeView.do">이메일 재설정</a></li>
				</ul>
			</div>
		</section>
		<hr />
		<section id="">
			<h3>내 작성글</h3>
			<table width="100%">
				<tr>
					<th>NO</th>
					<th>작성일</th>
					<th>제목</th>
				</tr>
				<tr>
					<td>{NO}</td>
					<td>{작성일}</td>
					<td>{제목}</td>
				</tr>
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
