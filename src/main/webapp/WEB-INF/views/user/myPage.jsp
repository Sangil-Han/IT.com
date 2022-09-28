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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<link href="/resources/css/header.css" rel="stylesheet">
<link href="/resources/css/common.css" rel="stylesheet">
</head>
<body>
	<div id="wrap" class="col-lg-10 offset-lg-1">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h2 id="page-title" class="text-bg-primary my-3 p-3 bg-opacity-50">
      <a href="/user/myPageView.do" class="link-dark">My Page</a>
		</h2>
    <div class="d-flex justify-content-center">
      <div class="card text-bg-primary text-center m-5 bg-opacity-25" style="width: 15rem;">
        <div class="card-header text-dark">등급</div>
        <div class="card-body">
          <a href="/user/levelHistoryView.do" class="link-dark text-decoration-underline">${sessionScope.loginUser.userLevel }</a>
          <br><br>
          <c:if test="${sessionScope.loginUser.userLevel ne '수료회원' }">
            <a href="/user/levelUpView.do" class="link-dark">등업 신청</a>
          </c:if>
        </div>
      </div>
      <div class="card text-bg-primary text-center m-5 bg-opacity-25" style="width: 15rem;">
        <div class="card-header text-dark">포인트</div>
        <div class="card-body" style="height: 80px; padding-top: 35px;">
          <a href="/user/pointHistoryView.do" class="link-dark text-decoration-underline">${sessionScope.loginUser.userPoint }P</a>
        </div>
      </div>
      <div class="card text-bg-primary text-center m-5 bg-opacity-25" style="width: 15rem;">
        <div class="card-header text-dark">계정</div>
        <div class="card-body">
          <a href="/user/pwResetView.do" class="link-dark">비밀번호 재설정</a>
          <br><br>
          <a href="/user/emailResetView.do" class="link-dark">이메일 재설정</a>
        </div>
      </div>
    </div>
		<hr />
		<div>
			<h3 class="mb-3">내 작성글</h3>
			<c:if test="${not empty mpList }">
        <table class="table" width="100%">
					<tr>
						<th width="10%">NO</th>
						<th width="20%">게시판</th>
						<th width="55%">제목</th>
						<th width="15%">작성일</th>
					</tr>
					<c:forEach items="${mpList }" var="post" varStatus="i">
						<tr>
							<th scope="row">${mppi.rowCount - ((mppi.currentPage - 1 ) * mppi.rowLimit + i.index) }</th>
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
        </table>
        <nav>
          <ul class="pagination justify-content-center">
            <li class="page-item <c:if test='${mppi.startPage eq 1 }'>disabled</c:if>">
                <a class="page-link" href="/user/myPageView.do?page=${mppi.startPage - 1 }" aria-label="Previous">
                  <span class="disabled" aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach var="p" begin="${mppi.startPage }" end="${mppi.endPage }">
              <c:if test="${mppi.currentPage eq p }">
                <a class="page-link" href="#" ><b>${p }</b></a>
							</c:if>
              <c:if test="${mppi.currentPage ne p }">
                <li class="page-item">
                  <a class="page-link" href="/user/myPageView.do?page=${p }">${p }</a>
                </li>
							</c:if>
            </c:forEach>
              <li class="page-item">
                <a class="page-link <c:if test='${mppi.endPage eq mppi.pageCount }'>disabled</c:if>" href="/user/myPageView.do?page=${mppi.endPage + 1 }" aria-label="Next">
                  <span aria-hidden="true">&raquo;</span>
                </a>
              </li>
          </ul>
        </nav>
			</c:if>
      <c:if test="${empty mpList }">
        <div>작성글이 존재하지 않습니다.</div>
      </c:if>
		</div>
		<hr />
		<section id="withdraw-area">
			<h3 class="mb-3">회원 탈퇴</h3>
      <label for="user-pw" class="form-label mb-2">현재 비밀번호</label>
			<form action="/user/withdraw.do" method="post">
        <div class="input-group mb-3" style="width: 40%">
          <input type="password" id="user-pw" class="form-control" name="userPw" autocomplete="off" required/>
          <button class="btn btn-danger">탈퇴</button>
        </div>
			</form>
		</section>
	</div>
</body>
</html>
