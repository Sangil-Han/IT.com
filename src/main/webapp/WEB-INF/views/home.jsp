<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h1>메인 페이지</h1>
	<button onclick="location.href='/cBoard/consultWriteFormView.do'">글쓰기</button>
	<button onclick="location.href='/cBoard/consultList.do'">글목록</button>
</body>
</html>
