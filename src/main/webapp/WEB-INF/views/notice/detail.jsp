<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 공지사항 상세 조회</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<link href="/resources/css/header.css" rel="stylesheet">
<link href="/resources/css/common.css" rel="stylesheet"></head>
<body>
  <div id="wrap" class="col-lg-10 offset-lg-1">
    <jsp:include page="../common/header.jsp"></jsp:include>
		<h2 id="page-title" class="text-bg-primary my-3 p-3 bg-opacity-50">
      <a href="/notice/boardView.do">공지사항</a>
    </h2>
    <form action="/notice/remove.do" method="post" onsubmit="return removeConfirm();">
      <input type="hidden" name="noticeNo" value="${notice.noticeNo }" readonly>
      <c:if test="${sessionScope.loginAdmin ne null }">
        <button type="button" onclick="location.href='/notice/modifyView.do?noticeNo=${notice.noticeNo }&page=${page }">수정</button><button>삭제</button>
      </c:if>
      <div id="notice-title">${notice.noticeTitle }</div>
      <div id="notice-contents">${notice.noticeContents }</div>
      <c:if test="${notice.noticeFileRename ne ''}">
        <img alt="" src="/resources/files/notice/${notice.noticeFileRename }">
      </c:if>
      <button type="button" onclick="location.href='/notice/boardView.do?page=${page }'">목록</button>
    </form>
  </div>
	<script>
		function removeConfirm() {
			if(confirm('정말 삭제하시겠습니까?')){
				return true;
			} else{
				return false;
			}
		}
	</script>
</body>
</html>