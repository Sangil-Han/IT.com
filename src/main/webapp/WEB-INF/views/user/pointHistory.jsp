<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 포인트 내역</title>
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
		<h3 class="mb-3">포인트 내역</h3>
    <c:if test="${not empty phList }">
      <table class="table" width="80%">
				<tr>
					<th width="10%">NO</th>
					<th width="20%">변동일자</th>
					<th width="35%">구분</th>
					<th width="35%">포인트</th>
				</tr>
				<c:forEach items="${phList }" var="point" varStatus="i">
					<tr>
						<th scope="row">${phpi.rowCount - ((phpi.currentPage - 1 ) * phpi.rowLimit + i.index) }</th>
						<td>${point.pointDate}</td>
						<td>${point.pointName}</td>
						<td>${point.pointAmount}P</td>
					</tr>
				</c:forEach>
      </table>
      <nav>
        <ul class="pagination justify-content-center">
          <li class="page-item <c:if test='${phpi.startPage eq 1 }'>disabled</c:if>">
              <a class="page-link" href="/user/pointHistoryView.do?page=${phpi.startPage - 1 }" aria-label="Previous">
                <span class="disabled" aria-hidden="true">&laquo;</span>
              </a>
          </li>
          <c:forEach var="p" begin="${phpi.startPage }" end="${phpi.endPage }">
            <c:if test="${phpi.currentPage eq p }">
              <a class="page-link" href="#" ><b>${p }</b></a>
            </c:if>
            <c:if test="${phpi.currentPage ne p }">
              <li class="page-item">
                <a class="page-link" href="/user/pointHistoryView.do?page=${p }">${p }</a>
              </li>
            </c:if>
          </c:forEach>
            <li class="page-item">
              <a class="page-link <c:if test='${phpi.endPage eq mppi.pageCount }'>disabled</c:if>" href="//user/pointHistoryView.do?page=${phpi.endPage + 1 }" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
              </a>
            </li>
        </ul>
      </nav>
    </c:if>
    <c:if test="${empty phList }">
        <div>포인트 내역이 존재하지 않습니다.</div>
    </c:if>
	</div>
</body>
</html>
