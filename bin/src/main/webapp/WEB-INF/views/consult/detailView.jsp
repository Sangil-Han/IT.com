<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 상담후기 상세 조회</title>
<script src="/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
	<div id="wrap">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h1 align="center">상담후기 게시판</h1>
		<table align="center" border="2">
			<tr>
				<td>제목</td>
				<td>${cBoard.cBoardTitle }</td>
			</tr>
			<tr>
				<td>지역구</td>
				<td>${cBoard.cBoardLocalName }</td>
				<td>작성일자</td>
				<td>${cBoard.cBoardCreateDate }</td>
				<td>조회수</td>
				<td>${totalViewCount }</td>
			</tr>
			<tr>
				<td>교육원명</td>
				<td>${cBoard.cBoardCenterName }</td>
			</tr>
			<tr>
				<td>과정명</td>
				<td>${cBoard.cBoardCourseName }</td>
			</tr>
			<tr>
				<td>상담날짜</td>
				<td>${cBoard.consultDate }</td>
			</tr>
			<tr height="200" width="200">
				<td colspan="2">${cBoard.cBoardContents }</td>
			</tr>
			<tr height="100">
				<td>첨부파일</td>
				<td>
					<img alt="본문이미지" src="/resources/files/consult/${cBoard.cBoardFileRename }" width="300" height="300">
				</td>
			</tr>
			<c:if test="${not empty sessionScope.loginUser }">
			<tr>
				<td>
					<form action="/consult/boardUpCount.do" method="POST">
						<input type="hidden" name="page" value="${page }">
						<input type="hidden" name="cBoardNo" value="${cBoard.cBoardNo }">
						<input type="submit" value="추천">${totalUp}
					</form>
				</td>
				<td>
					<form action="/consult/boardDownCount.do" method="POST">
						<input type="hidden" name="page" value="${page }">
						<input type="hidden" name="cBoardNo" value="${cBoard.cBoardNo }">
						<input type="submit" value="비추천">${totalDown}
					</form>
				</td>
			</tr>
			</c:if>
		</table>
		<table align="center" width="500" border="1">
			<c:forEach items="${cList }" var="comment">
				<tr id="tr">
					<td>${comment.commentContents }</td>
					<td>${comment.commentRegtime }</td>
					<c:if test="${loginUserId eq comment.userId }">
						<td>
							<button onclick="commentModifyView(this,'${comment.commentContents }',${comment.commentNo },${cBoardNo },${page });">수정</button>
							<button onclick="removeComment(${comment.commentNo},${cBoard.cBoardNo }, ${page });">삭제</button>
						</td>
					</c:if>
					<td>
						<c:if test="${not empty sessionScope.loginAdmin }">
							<button onclick="removeComment(${comment.commentNo},${cBoard.cBoardNo }, ${page });">삭제</button>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${not empty sessionScope.loginUser }">
		<form action="/consult/consultAddComment.do" method="post">
			<input type="hidden" name="page" value="${page }">
			<input type="hidden" name="cBoardNo" value="${cBoard.cBoardNo }">
			<table align="center" width="500" border="1">
				<tr>
					<td><textarea rows="3" cols="55" name="commentContents"></textarea></td>
					<td><input type="submit" value="등록하기"></td>
				</tr>
			</table>
		</form>
		</c:if>
		<table align="center">
			<tr>
				<td>
					<c:if test="${loginUserId eq cBoard.userId }">
						<button onclick="location.href='/consult/consultModifyView.do?cBoardNo=${cBoard.cBoardNo }&page=${page}'">수정</button>
					</c:if>
					<c:if test="${not empty sessionScope.loginAdmin }">
						<button onclick="removeBoard(${page});">삭제</button>
					</c:if>
					<button onclick="location.href='/consult/consultList.do?page=${page}'">목록</button>
				</td>
			</tr>
		</table>
	</div>
	<script>
		function removeComment(commentNo, cBoardNo, page) {
			event.preventDefault();
			if(confirm("댓글을 삭제하시겠습니까?")) {
				location.href="/consult/removeComment.do?cBoardNo="+cBoardNo+"&page="+page+"&commentNo="+commentNo;
			}
		}
		function commentModifyView(obj, commentContents, commentNo,cBoardNo,page) {
			event.preventDefault();
			var $tr = $("<tr>");
			$tr.append("<td colspan='3'><input type='text' size='50' value='"+commentContents+"'");
			$tr.append("<td><button onclick='modifyComment(this, "+commentNo+","+cBoardNo+","+page+");'>수정</button></td>");
			$(obj).parent().parent().after($tr);
		}
		function modifyComment(obj, commentNo ,cBoardNo, page) {
			var inputTag = $(obj).parent().siblings().eq(0).children();
			var commentContents = inputTag.val();
			var $form = $("<form>");
			$form.attr("action", "/consult/modifyComment.do");
			$form.attr("method", "post");
			$form.append("<input type='hidden' value='"+cBoardNo+"' name='cBoardNo'>");
			$form.append("<input type='hidden' value='"+page+"' name='page'>");
			$form.append("<input type='hidden' value='"+commentContents+"' name='commentContents'>");
			$form.append("<input type='hidden' value='"+commentNo+"' name='commentNo'>");
			console.log($form[0]);
			$form.appendTo("body");
			$form.submit();
		}
		function removeBoard(page) {
			event.preventDefault(); // 하이퍼링크 이동 방지
			if(confirm("게시물을 삭제하시겠습니까?")) {
				location.href="/consult/consultRemoveBoard.do?page="+page;
			}
		}
</script>
</body>
</html>