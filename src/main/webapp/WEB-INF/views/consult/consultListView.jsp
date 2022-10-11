<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 상담후기 게시판</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<link href="/resources/css/header.css" rel="sytlesheet">
</head>
<body>
	<div id="wrap">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<div id="banner"><img id="img" src="/resources/img/consult9.png" class="img-fluid" alt="상담후기 게시판"></div>
		<br><br>
		<table class="table table-bordered w-75" align="center" border="2">
			<tr>
				<th colspan="3" class="table-primary" style="text-align:center">제목</th>
				<th class="table-primary" style="text-align:center">등록일</th>
				<th class="table-primary" style="text-align:center">조회수</th>
				<th class="table-primary" style="text-align:center">추천수</th>
			</tr>
			<c:if test="${!empty cList }">
				<c:forEach items="${cList }" var="cBoard" varStatus="i">
					<tr>
						<td colspan="3">
							<a href="#" onclick="detailView('${userId}','${sessionScope.loginAdmin.adminId}', ${cBoard.cBoardNo },${currentPage },'${level }','${point }','${viewable }');">${cBoard.cBoardTitle }</a>
						</td>
						<td style="text-align:center">${cBoard.cBoardCreateDate }</td>
						<td style="text-align:center">${cBoard.cBoardCount }</td>
						<td style="text-align:center">${cBoard.cBoardUpCount }</td>
					</tr>
				</c:forEach>
				<tr align="center" height="20">
					<td align="center" colspan="6">
						<nav aria-label="Page navigation example">
							<ul class="pagination" align="center">
								<li class="page-item">
									<c:if test="${currentPage > 5}">
						 				<a class="page-link" href="/consult/${urlVal }.do?page=${startNavi - 1 }&searchCondition=${searchCondition}&searchValue=${searchValue}"" aria-label="Previous">
											<span aria-hidden="true">&laquo;</span>
				      					</a>
				      				</c:if>
			    				</li>
								<c:forEach var="p" begin="${startNavi }" end="${endNavi }">	
								    <li class="page-item"><a class="page-link" href="/consult/${urlVal }.do?page=${p }&searchCondition=${searchCondition}&searchValue=${searchValue}">${p }</a></li>
			    				</c:forEach>
			    				<c:if test="${maxPage-4 > currentPage }">
								    <li class="page-item">
								    	<a class="page-link" href="/consult/${urlVal }.do?page=${endNavi + 1 }&searchCondition=${searchCondition}&searchValue=${searchValue}" aria-label="Next">
					        				<span aria-hidden="true">&raquo;</span>
					      				</a>
				    				</li>
				    			</c:if>
							</ul>
						</nav>
					</td>
				</tr>
			</c:if>
			<c:if test="${empty cList }">
				<tr>
					<td colspan="6">게시글이 존재하지 않습니다</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="5" align="center">
					<form action="/consult/consultSearch.do" method="get">
						<select style="float:left" class="form-select w-25" aria-label="Default select example" name="searchCondition">
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
						<input style="float:left" class="form-control w-50" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" type="text" name="searchValue" value="${searchValue }">
						<input style="float:left" type="submit" value="검색" class="btn btn-outline-primary">
					</form>
				</td>
				<c:if test="${empty sessionScope.loginAdmin }">
					<td align="center">
						<button type="button" class="btn btn-primary" href="#" onclick="writeForm('${userId}');">글쓰기</button>
					</td>
				</c:if>
			</tr>
		</table>
	</div>
	<script>
		function detailView(userId, adminId, cBoardNo, currentPage,level, point, viewable) {
			event.preventDefault();
			var userLevel = '일반회원';
			var viewableNo = 'N';
			if(userId == "" && adminId == "") {
				if(confirm("로그인이 필요한 서비스입니다. 로그인하시겠습니까?")){
					location.href='/user/loginView.do';
				}
			}else{
				if(level == userLevel && viewable == viewableNo){
					if(confirm("100포인트를 사용하여 열람하시겠습니까?")) {
						if(point >= 100){
		 					location.href='/consult/consultDetailView.do?cBoardNo='+cBoardNo+'&page='+currentPage;
						}else {
							alert("포인트가 부족합니다");
						}
					}
				}else{
					location.href='/consult/consultDetailView.do?cBoardNo='+cBoardNo+'&page='+currentPage;
				}
			}
		}
		function writeForm(userId) {
			if(userId == ""){
				if(confirm("로그인이 필요한 서비스입니다 로그인하시겠습니까?")){
					location.href='/user/loginView.do';
				}
			}else {
				location.href='/consult/consultWriteFormView.do';
		}
	}
	</script>
	<script src="https://kit.fontawesome.com/422d96f707.js" crossorigin="anonymous"></script>
</body>
</html>