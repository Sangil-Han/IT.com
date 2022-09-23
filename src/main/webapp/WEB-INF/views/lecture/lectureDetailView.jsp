<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 수강후기 상세 조회</title>
<script src="/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
	<div id="wrap">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h1 align="center">수강후기 게시판</h1>
		<br><br>
		<table align="center" width="500" border="1">
			<tr>
				<td>제목</td>
				<td>${lectureBoard.lBoardTitle }</td>
			</tr>
			<tr>
				<td>지역구</td>
				<td>${lectureBoard.lBoardLocalName }</td>
			</tr>
			<tr>
				<td>교육원명</td>
				<td>${lectureBoard.lBoardCenterName }</td>
			</tr>
			<tr>
				<td>과정명</td>
				<td>${lectureBoard.lBoardCourseName }</td>
			</tr>
			<tr height="300">
				<td>내용</td>
				<td>${lectureBoard.lBoardContents }</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${lectureBoard.lBoardCount }</td>
			</tr>
			<tr>
				<td>추천수</td>
				<td>${lectureBoard.lBoardUpCount }</td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><img alt="본문이미지" src="/resources/buploadFiles/${lectureBoard.lBoardFileRename }" width="300" height="300"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><a href="/lecture/modifyView.do?lBoardNo=${lectureBoard.lBoardNo}&page=${page}">수정 페이지로 이동</a></td>
			</tr>
		</table>
	</div>
	<%-- <!-- 	댓글 등록 -->
	<form action="/board/addReply.kh" method="post">
		<input type="hidden" name="page" value="${page }">
		<input type="hidden" name="refBoardNo" value="${board.boardNo }">
		<table align="center" width="500" border="1">
			<tr>
				<td>
					<textarea rows="3" cols="55" name="replyContents"></textarea>
				</td>
				<td>
					<input type="submit" value="등록하기">
				</td>
			</tr>
		</table>
	</form>
	<!-- 	댓글 목록 -->
	<table align="center" width="500" border="1">
		<c:forEach items="${rList }" var="reply">
			<tr>
				<td width="100">${reply.replyWriter }</td>
				<td>${reply.replyContents }</td>
				<td>${reply.rUpdateDate }</td>
				<td>
					<a href="#" onclick="modifyView(this,'${reply.replyContents }', ${reply.replyNo });">수정</a> 
				</td>
			</tr>
		</c:forEach>
	</table> --%>
	<script>
		/* 	function modifyView(obj, replyContents, replyNo) {
				event.preventDefault();
				var $tr = $("<tr>");
				$tr.append("<td colspan='3'><input type='text' size='50' value='"+replyContents+"'></td>");
				$tr.append("<td><button onclick='modifyReply(this, "+replyNo+");'>수정</button></td>");
				$(obj).parent().parent().after($tr);
			} 
			function modifyReply(obj, rNo) {
				var inputTag = $(obj).parent().prev().children();
				console.log(inputTag.val());
				var replyContents = inputTag.val();
				var $form = $("<form>");
				$form.attr("action", "/board/modifyReply.kh");
				$form.attr("method", "post");
				$form.append("<input type='hidden' value='"+replyContents+"' name='replyContents'>");
				$form.append("<input type='hidden' value='"+rNo+"' name='replyNo'>");
				console.log($form[0]);
				$form.appendTo("body");
				$form.submit();
			}  */
	</script>
</body>
</html>