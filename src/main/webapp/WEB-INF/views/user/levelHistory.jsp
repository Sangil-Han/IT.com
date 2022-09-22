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
<title>IT.com : 등급 변동 내역</title>
</head>
<body>
	<div id="wrap">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h2 id="page-title">
			<a href="/user/myPageView.do">My Page</a>
		</h2>
		<h3>등급 변동 내역</h3>
		<table width="80%">
			<tr>
				<th width="10%">NO</th>
				<th width="40%">변동일자</th>
				<th width="50%">구분</th>
				<th width="50%">포인트</th>
			</tr>
			<c:if test="${not empty phList }">
				<c:forEach items="${phList }" var="point" varStatus="i">
					<tr>
						<td>${pointCount - ((currentPage - 1 ) * pointLimit + i.index) }</td>
						<td>${point.pointDate}</td>
						<td>${point.pointName}</td>
						<td>${point.pointAmount}P</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4">
						<c:if test="${startPage ne 1 }">
							<a href="/point/historyView.do?page=${startPage - 1 }">[이전]</a>
						</c:if>
						<c:forEach var="p" begin="${startPage }" end="${endPage }">
							<c:if test="${currentPage eq p }">
								<b>${p }</b>
							</c:if>
							<c:if test="${currentPage ne p }">
								<a href="/point/historyView.do?page=${p }">${p }</a>
							</c:if>
						</c:forEach>
						<c:if test="${endPage ne pageCount }">
							<a href="/point/historyView.do?page=${endPage + 1 }">[다음]</a>
						</c:if>
					</td>
				</tr>
			</c:if>
			<c:if test="${empty phList }">
				<tr>
					<td colspan="4">포인트 내역이 존재하지 않습니다.</td>
				</tr>
			</c:if>
		</table>
	</div>
</body>
</html>
