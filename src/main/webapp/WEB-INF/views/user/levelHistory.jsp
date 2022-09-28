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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<link href="/resources/css/header.css" rel="stylesheet">
<link href="/resources/css/common.css" rel="stylesheet">
</head>
<body>
	<div id="wrap" class="col-lg-10 offset-lg-1">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h2 id="page-title" class="text-bg-primary my-3 p-3 bg-opacity-50">
			<a href="/user/myPageView.do">My Page</a>
		</h2>
		<h3 class="mb-3">등급 변동 내역</h3>
    <c:if test="${not empty lhList }">
      <table class="table" width="80%">
				<tr>
					<th width="10%">NO</th>
					<th width="40%">변동일</th>
					<th width="50%">등급</th>
				</tr>
				<c:forEach items="${lhList }" var="level" varStatus="i">
					<tr>
						<th scope="row">${fn:length(lhList) - i.index }</th>
						<td>${level.lvChangeDate }</td>
						<td>${level.applicationLv }</td>
					</tr>
				</c:forEach>
      </table>
    </c:if>
    <c:if test="${empty lhList }">
        <div>등급 변동 내역이 존재하지 않습니다.</div>
    </c:if>
	</div>
</body>
</html>
