<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수료 후기 게시판</title>
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
					<td><a href="/user/myPageView.do">${sessionScope.loginUser.userId }</a>님
						환영합니다</td>
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
	<!-- -------------------------------------------------------------------------- -->
	
	<h1 align="center">${fBoard.fBoardNo }번 게시글 상세 보기</h1>
	<br>
	<br>
	<table align="center" width="500" border="1">
		<tr>
			<td>제목</td>
			<td>${fBoard.fBoardTitle }</td>
		<tr>
			<td>지역구</td>
			<td>${fBoard.fBoardLocalName }</td>
		</tr>
		<tr>
			<td>교육원명</td>
			<td>${fBoard.fBoardCenterName }</td>
		</tr>
		<tr>
			<td>과정명</td>
			<td>${fBoard.fBoardCourseName }</td>
		</tr>
		<tr>
			<td>수료연도</td>
			<td>${fBoard.fBoardFinishYear }</td>
		</tr>
		<tr>
			<td>취업여부</td>
			<td>${fBoard.fBoardJobYn }</td>
		</tr>
		<c:if test="${fBoard.fBoardJobYn  eq 'Y'}">
		<tr>
			<td>직무</td>
			<td>${fBoard.fBoardJobName }</td>
		</tr>
		<tr>
			<td>초봉</td>
			<td>${fBoard.fBoardSalary }</td>
		</tr>
		<tr>
			<td>기업명</td>
			<td>${fBoard.fBoardCompany }</td>
		</tr>
		</c:if>
		<tr height="300">
			<td>내용</td>
			<td>${fBoard.fBoardContents }<img alt="본문이미지"
				src="/resources/fuploadFiles/${fBoard.fBoardFileRename}">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<c:if test="${fBoard.fBoardUserId eq sessionScope.loginUser.userId}">
					<a href="/finish/modifyView.do?fBoardNo=${fBoard.fBoardNo }&page=${page}">수정
						페이지로 이동</a>
				</c:if></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><a
				href="/finish/listView.do?page=${page }">목록으로</a> <a
				href="javascript:history.go(-1);">이전 페이지로</a></td>
		</tr>
	</table>
	
	<!-- 댓글 등록 -->
	<form action="/finish/addComment.do" method="post">
		<input type="hidden" name="fBoardNo" value="${fBoard.fBoardNo }">
		<input type="hidden" name="page" value=${page }>
		<table align="center" width="500" border="1">
			<tr>
				<td><textarea rows="3" cols="55" name="fCommentContents"></textarea></td>
				<td>
					<button>등록하기</button>
				</td>
			</tr>
		</table>
	</form>
	
	<!-- 댓글 목록 -->
	<table align="center" width="500" border="1">
		<c:forEach items="${fContentsList }" var="content">
		<tr>
			<td>${content.fCommentContents }</td>
			<td>${content.fCommentRegtime }</td>
			<td><a href="#" onclick="modifyView(this, '${reply.replyContents }',${reply.replyNo });">수정</a> 
				<a href="#" onclick="removeReply(${reply.replyNo});">삭제</a></td>
		</tr>
		<%-- <tr>
			<td colspan="3"><input type="text" size="50" value="${reply.replyContents }"></td>
			<td><button>수정</button></td>
		</tr> --%>
		</c:forEach>
	
	</table>
</body>
</html>