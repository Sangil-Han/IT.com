<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script>

</script>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 수강후기 상세 조회</title>
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
	
	<!-- 댓글 등록 -->
	<form action="/lecture/lectureAddComment.do" method="post">
			<input type="hidden" name="lBoardNo" value="${lectureBoard.lBoardNo }">
			<input type="hidden" name="page" value=${page }>
			<table align="center" width="500" border="1">
				<tr>
					<td><textarea rows="3" cols="55" name="lCommentContents"></textarea></td>
					<td>
						<button>등록하기</button>
					</td>
				</tr>
			</table>
		</form>

	
		<!-- 댓글 목록 -->
		<table align="center" width="500" border="1">
			<c:forEach items="${lcList }" var="lbComment">
				<tr>
					<td>${lbComment.lCommentContents }</td>
					<td>${lbComment.lCommentRegtime }</td>
					<%-- <c:if test="${loginUserId eq lbComment.userId }"> --%>
						<td>
							<a href="#" onclick="modifyView(this,'${lbComment.lCommentContents }',${lbComment.lCommentNo},${lBoardNo },${page });">수정</a>
							<button onclick="removeComment(${lbComment.lCommentNo},${lectureBoard.lBoardNo }, ${page });">삭제</button>
						</td>
					<%-- </c:if> --%>
				</tr>
			<%-- 	<tr> 
 					<td colspan="3"><input type="text" size="50" value="${lbComment.lCommentContents }"></td>
 				</tr>  --%>
			</c:forEach>
		</table>
		<%-- <table align="center">
			<tr>
				<td>
					<<button onclick="location.href='/lecture/modifyComment.do?lBoardNo=${lectureBoard.lBoardNo }&page=${page}'">수정</button> 
					<button onclick="location.href='/lecture/list.do?page=${page}'">목록</button>
				</td>
			</tr>
		</table> --%>
	</div>
	<script>
		function removeComment(lCommentNo, lBoardNo, page) {
			event.preventDefault();
			if(confirm("댓글을 삭제하시겠습니까?")) {
				location.href="/lecture/removeComment.do?lBoardNo="+lBoardNo+"&page="+page+"&lCommentNo="+lCommentNo;
			}
		}
		function modifyView(obj, lCommentContents, lCommentNo,lBoardNo,page) {
			event.preventDefault();
			var $tr = $("<tr>");
			$tr.append("<td colspan='3'><input type='text' size='50' value='"+lCommentContents+"'></td>");
			$tr.append("<td><button onclick='modifyComment(this, "+lCommentNo+","+lBoardNo+","+page+");'>수정</button></td>");
			$(obj).parent().parent().after($tr);
		}
		function modifyComment(obj, lCommentNo ,lBoardNo, page) {
			var inputTag = $(obj).parent().siblings().eq(0).children();
			var lCommentContents = inputTag.val();
			var $form = $("<form>");
			$form.attr("action", "/lecture/modifyComment.do");
			$form.attr("method", "post");
			$form.append("<input type='hidden' value='"+lBoardNo+"' name='lBoardNo'>");
			$form.append("<input type='hidden' value='"+page+"' name='page'>");
			$form.append("<input type='hidden' value='"+lCommentContents+"' name='lCommentContents'>");
			$form.append("<input type='hidden' value='"+lCommentNo+"' name='lCommentNo'>");
			console.log($form[0]);
			$form.appendTo("body");
			$form.submit();
		}
</script>
</body>
</html>