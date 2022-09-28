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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/422d96f707.js" crossorigin="anonymous"></script>
	<style>
		.upDown{
			display: flex;
  			justify-content: center;
		}

	</style>
</head>
<body>
	<div id="wrap">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<div><img src="/resources/img/consult9.png" alt="상담후기 게시판"></div>
		<br><br>
		<table align="center" border="2" class="table table-bordered w-75">
			<tr>
				<th class="table-primary">제목</th>
				<td>${cBoard.cBoardTitle }</td>
			</tr>
			<tr>
				<th class="table-primary">지역구</td>
				<td>${cBoard.cBoardLocalName }</td>
				<th class="table-primary">작성일자</td>
				<td>${cBoard.cBoardCreateDate }</td>
				<th class="table-primary">조회수</th>
				<td>${totalViewCount }</td>
			</tr>
			<tr>
				<th class="table-primary">교육원명</td>
				<td>${cBoard.cBoardCenterName }</td>
			</tr>
			<tr>
				<th class="table-primary">과정명</td>
				<td>${cBoard.cBoardCourseName }</td>
			</tr>
			<tr>
				<th class="table-primary">상담날짜</td>
				<td>${cBoard.consultDate }</td>
			</tr>
			<tr height="200" width="200">
				<td colspan="6">${cBoard.cBoardContents }</td>
			</tr>
			<tr height="100">
				<th class="table-primary">첨부파일</td>
				<td colspan="5">
					<img alt="본문이미지" src="/resources/files/consult/${cBoard.cBoardFileRename }" width="300" height="300">
				</td>
			</tr>
		</table>
		<c:if test="${not empty sessionScope.loginUser }">
		<div class="col-md-4 offset-md-4 py-5 text-center">
        	<form action="/consult/boardUpCount.do" method="post" style="display: inline">
        		<input type="hidden" name="userId" value="${sessionScope.loginUser.userId }"/>
                <input type="hidden" name="cBoardNo" value="${cBoard.cBoardNo }"/>
                <input type="hidden" name="page" value="${page }"/>
            	<button type="submit" class="btn btn-primary mx-3"><i class="fa-solid fa-thumbs-up fa-lg"></i> 추천 <b>${totalUp }</b></button>
          	</form>
          	<form action="/consult/boardDownCount.do" method="post" style="display: inline">
            	<input type="hidden" name="userId" value="${sessionScope.loginUser.userId }"/>
                <input type="hidden" name="cBoardNo" value="${cBoard.cBoardNo }"/>
                <input type="hidden" name="page" value="${page }"/>
            	<button type="submit" class="btn btn-danger"><i class="fa-solid fa-thumbs-down fa-lg"></i> 비추천 <b>${totalDown } </b></button>
          	</form>
        </div>
			<br><br>
		</c:if>
		<table align="center" width="500" border="1" class="table table-bordered w-75">
			<c:forEach items="${cList }" var="comment">
				<tr id="tr">
					<td>${comment.commentContents }</td>
					<td>${comment.commentRegtime }</td>
					<c:if test="${loginUserId eq comment.userId }">
						<td>
							<button class="btn btn-secondary" onclick="commentModifyView(this,'${comment.commentContents }',${comment.commentNo },${cBoardNo },${page });">수정</button>
							<button class="btn btn-danger" onclick="removeComment(${comment.commentNo},${cBoard.cBoardNo }, ${page });">삭제</button>
						</td>
					</c:if>
					<c:if test="${not empty sessionScope.loginAdmin }">
						<td>
							<button class="btn btn-danger" onclick="removeComment(${comment.commentNo},${cBoard.cBoardNo }, ${page });">삭제</button>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${not empty sessionScope.loginUser }">
		<form action="/consult/consultAddComment.do" method="post">
			<input type="hidden" name="page" value="${page }">
			<input type="hidden" name="cBoardNo" value="${cBoard.cBoardNo }">
			<table align="center">
				<tr>
					<td width="1050"><input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg"name="commentContents"></td>
					<td><input class="input-group-text" id="inputGroup-sizing-lg" type="submit" value="등록하기"></td>
				</tr>
			</table>
		</form>
		<br><br>
		</c:if>
		<table align="center">
			<tr>
				<td>
					<c:if test="${loginUserId eq cBoard.userId }">
						<button class="btn btn-primary" onclick="location.href='/consult/consultModifyView.do?cBoardNo=${cBoard.cBoardNo }&page=${page}'">수정</button>
					</c:if>
					<c:if test="${not empty sessionScope.loginAdmin }">
						<button class="btn btn-danger" onclick="removeBoard(${page});">삭제</button>
					</c:if>
					<button class="btn btn-secondary" onclick="location.href='/consult/consultList.do?page=${page}'">목록</button>
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
			$tr.append("<td colspan='3'><input type='text' class='form-control' aria-label='Sizing example input' aria-describedby='inputGroup-sizing-default' type='text' size='50' value='"+commentContents+"'");
			$tr.append("<td><button class='input-group-text' id='inputGroup-sizing-default' onclick='modifyComment(this, "+commentNo+","+cBoardNo+","+page+");'>수정</button></td>");
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