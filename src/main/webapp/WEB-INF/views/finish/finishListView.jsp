<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
  <body>
    <div id="header">
      <a href="#">IT.com</a>
      <input type="text" placeholder="검색어를 입력해주세요" />
      <button onclick="location.href='#';">로그인</button>
      <button onclick="location.href='#';">회원가입</button>
    </div>
    <div id="navi"></div>
    <div id="banner"></div>
    <form action="/finish/search.do" method="get">
      <select name="searchOption">
      <!-- option 변경해서 검색했을 때 지정한 option값이 변하지 않게 하기 미구현 -->
        <option value="all" selected>전체</option>
        <option value="center">교육원</option>
        <option value="local">지역구</option>
        <option value="title">제목</option>
        <option value="contents">내용</option>
      </select>
       <input type="text" name="searchValue" value="${searchValue }" />
      <input type="submit" value="검색" />
    </form>
    <button onclick="location.href='/finish/registerView.do'">글쓰기</button>
    <div id="contents"></div>
    
    <h1 align="center">게시글 목록</h1>
	<br>
	<br>
	<table align="center" border="1">
		<tr>
			<th>제목</th>
			<th>등록일</th>
			<th>조회수</th>
			<th>추천수</th>
		</tr>
		<c:if test="${!empty fList }">
			<c:forEach items="${fList }" var="fBoard" varStatus="i">
				<tr>
					<td><a href="/finish/detailView.do?fBoardNo=${fBoard.fBoardNo}&page=${currentPage}">${fBoard.fBoardTitle }</a></td>
					<td>${fBoard.fBoardCreateDate }</td>
					<td>${fBoard.fBoardUpCount }</td>
					<td>${fBoard.fBoardCount }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty fList }">
			<tr>
				<td colspan="6" align="center"><b>데이터가 존재하지 않습니다.</b></td>
			</tr>
		</c:if>
		
		<!-- 페이징 처리 -->
		<tr align="center" height="20">
			<td colspan="6"><c:if test="${startNavi ne 1}">
					<a href="/finish/${urlVal }.do?page=${startNavi-1}&searchCondition=${searchCondition}&serachValue=${searchValue}">[이전]</a>
				</c:if> <c:forEach var="p" begin="${startNavi }" end="${endNavi }">
					<c:if test="${currentPage eq p }">
						<b>${p }</b>
					</c:if>
					<c:if test="${currentPage ne p}">
						<a href="/finish/${urlVal }.do?page=${p }&searchCondition=${searchCondition}&serachValue=${searchValue}">${p }</a>
					</c:if>
				</c:forEach> <c:if test="${endNavi ne maxPage }">
					<a href="/finish/${urlVal }.do?page=${endNavi+1 }&searchCondition=${searchCondition}&serachValue=${searchValue}">[다음]</a>
				</c:if></td>
		</tr>
		</table>
  </body>
</html>