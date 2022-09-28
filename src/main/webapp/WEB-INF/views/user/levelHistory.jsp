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
<link href="/resources/css/header.css" rel="stylesheet">
</head>
<body>
	<div id="wrap">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h2 id="page-title">
			<a href="/user/myPageView.do">My Page</a>
		</h2>
		<h3>등급 변동 내역</h3>
		<table width="80%">
			<c:if test="${not empty lhList }">
				<tr>
					<th width="10%">NO</th>
					<th width="40%">변동일</th>
					<th width="50%">등급</th>
				</tr>
				<c:forEach items="${lhList }" var="level" varStatus="i">
					<tr>
						<td>${fn:length(lhList) - i.index }</td>
						<td>${level.lvChangeDate }</td>
						<td>${level.applicationLv }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty lhList }">
				<tr>
					<td colspan="4">등급 변동 내역이 존재하지 않습니다.</td>
				</tr>
			</c:if>
		</table>
	</div>
</body>
</html>
