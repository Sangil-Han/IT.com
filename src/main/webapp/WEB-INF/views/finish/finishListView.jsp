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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<link href="/resources/css/header.css" rel="sytlesheet">
<link href="/resources/css/common.css" rel="stylesheet">
</head>
<body>
	<div id="wrap" class="col-lg-10 offset-lg-1">
    <jsp:include page="../common/header.jsp"></jsp:include>
    <img src="../../../resources/img/finish-logo.png" alt="">
    <div class="container">
      <div class="row">
        <div class="col-lg-9 offset-lg-1">
          <!-- 검색 옵션 -->
          <form action="/finish/search.do" method="get">
            <div class="row">
              <div class="col-lg-2">
                <select name="searchOption" class="form-select" aria-label="Default select example">
                  <option value="all" <c:if test="${searchOption eq 'all'}">selected</c:if>>전체</option>
                  <option value="center" <c:if test="${searchOption eq 'center'}">selected</c:if>>교육원</option>
                  <option value="local" <c:if test="${searchOption eq 'local'}">selected</c:if>>지역구</option>
                  <option value="title"<c:if test="${searchOption eq 'title'}">selected</c:if>>제목</option>
                  <option value="contents"<c:if test="${searchOption eq 'contents'}">selected</c:if>>내용</option>
                </select>
              </div>
              <div class="col-lg-7">
                <div class="input-group mb-3">
                  <input type="text" class="form-control" name="searchValue" value="${searchValue }" aria-describedby="button-addon2">
                  <button class="btn btn-outline-secondary" id="button-addon2"><i class="fa-sharp fa-solid fa-magnifying-glass"></i></button>
                </div>                                
              </div>
            </div>
          </form>
        </div>
        <div class="col-lg-2">
          <!--글쓰기 버튼-->
          <c:if test="${sessionScope.loginUser.userLevel eq '수료회원'}">
            <button class="btn btn-primary" onclick="location.href='/finish/registerView.do'">글쓰기</button>
          </c:if>
        </div>
      </div>
        
        



<!-- 게시글 리스트 -->
      <div class="row">
        <div class="col-lg-10 offset-lg-1">
          <table class="table text-center table-bordered">
            <thead class="table-primary">
              <tr class="text-center">
                <th scope="col" width="70%">제목</th>
                <th scope="col">등록일</th>
                <th scope="col">조회수</th>
                <th scope="col">추천</th>
              </tr>
            </thead>
            <tbody>
              <c:if test="${!empty fList }">
                <c:forEach items="${fList }" var="fBoard" varStatus="i">
                  <tr>
                    <td>
                      <a href="#"  style="text-decoration:none; color:black"
                        <c:if test="${sessionScope.loginUser ne null }"> onclick="showDetailView('${loginUser.userId}', '${loginUser.userLevel}', '${loginUser.viewable}', ${loginUser.userPoint }, ${fBoard.fBoardNo}, ${currentPage });"</c:if>
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
            </tbody>
          </table>
        </div>
      </div>
      


      <!-- 페이징 처리 -->
      <div class="row">
        <nav aria-label="Page navigation example">
          <ul class="pagination" style="justify-content: center;">
            <c:if test="${startNavi ne 1}">
              <li class="page-item">
                <a class="page-link" href="/finish/${urlVal }.do?page=${startNavi-1}&searchOption=${searchOption}&serachValue=${searchValue}" aria-label="Previous">
                  <span aria-hidden="true">&laquo;</span>
                </a> 
              </li>
            </c:if>
            
            <c:forEach var="p" begin="${startNavi }" end="${endNavi }">
              <c:if test="${currentPage eq p }">
                <li class="page-item"><a class="page-link" href="#">${p }</a></li>
              </c:if>
              <c:if test="${currentPage ne p}">
                <li class="page-item"><a class="page-link" href="/finish/${urlVal }.do?page=${p }&searchCondition=${searchOption}&serachValue=${searchValue}">${p }</a></li>
              </c:if>
            </c:forEach>
            <c:if test="${endNavi ne maxPage }">
              <li class="page-item">
                <a class="page-link" href="/finish/${urlVal }.do?page=${endNavi+1 }&searchOption=${searchOption}&serachValue=${searchValue}" aria-label="Next">
                  <span aria-hidden="true">&raquo;</span>
                </a>
              </li>
            </c:if>
          </ul>
        </nav>
      </div>
	  </div>
  </div>
	<script>
		// 회원의 등급, 포인트를 확인하는 메소드
		function showDetailView(userId, userLevel, viewable, userPoint, fBoardNo,
				currentPage) {
			event.preventDefault();
			const levelName = '수료회원';
			const requiredViewable = 'Y';
				// 수료회원이고 작성이력이 있으면
				if (userLevel == levelName&& viewable==requiredViewable) {
					location.href = "/finish/detailView.do?fBoardNo="
							+ fBoardNo + "&page=" + currentPage;
				}

				// 수료회원이 아니거나 작성이력이 없으면 포인트 사용
				else {
					if (confirm("300포인트를 사용하여 열람하시겠습니까?")) {
						if (userPoint >= 300) {
							location.href = "/finish/detailView.do?fBoardNo="
									+ fBoardNo + "&page=" + currentPage

                  var httpRequest;
                  var reqJson=new Object();
                  reqJson.point='-300';
                  reqJson.userId=userId;
                  httpRequest=new XMLHttpRequest();
                  httpRequest.open('POST', '/finish/usePoint.do', true);
                  httpRequest.responseType="json";
                  httpRequest.setRequestHeader('Content-Type', 'application/json');
                  httpRequest.send(JSON.stringify(reqJson));
						} else {
							alert("포인트가 부족합니다");
						}
					}
				}
			}

    // var httpSession1;
    // var httpSession2;
    // var httpSession3;

    // var fBoardList=Array.from(document.querySelectorAll('[id^=check-user-first]'));

    // for(let i=0; fBoardList.length; i++){
    //   fBoardList[i].addEventListener('click', (${loginUser.userId})=>{
    //   })
    // }

		function notLogin() {
			alert("로그인이 필요합니다");
			location.href = "/user/loginView.do";
		}
    
	</script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://kit.fontawesome.com/422d96f707.js" crossorigin="anonymous"></script>
</body>
</html>