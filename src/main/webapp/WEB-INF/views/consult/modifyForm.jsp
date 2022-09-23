<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 상담후기 수정</title>
</head>
<body>
	<div id="wrap">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h1 align="center">상담후기 게시판</h1>
		<br>
		<form action="/consult/consultModify.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="page" value="${page }">
			<input type="hidden" name="cBoardNo" value="${cBoard.cBoardNo }">
			<input type="hidden" name="cBoardFileName" value="${cBoard.cBoardFileName }">
			<input type="hidden" name="cBoardFileRename" value="${cBoard.cBoardFileRename }">
			<table align="center" border="1">
				<tr>
					<td>제목</td>
					<td><input type="text" name="cBoardTitle" value="${cBoard.cBoardTitle }"></td>
				</tr>
				<tr>
					<td>지역구</td>
					<td><input type="text" name="cBoardLocalName" value="${cBoard.cBoardLocalName }"></td>
				</tr>
				<tr>
					<td>교육원명</td>
					<td><input type="text" name="cBoardCenterName" value="${cBoard.cBoardCenterName}"></td>
				</tr>
				<tr>
					<td>과정명</td>
					<td><input type="text" name="cBoardCourseName" value="${cBoard.cBoardCourseName}"></td>
				</tr>
				<tr>
					<td>상담날짜</td>
					<td><input type="Date" name="consultDate" value="${cBoard.consultDate}"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea cols="30" rows="7" name="cBoardContents">${cBoard.cBoardContents }</textarea></td>
				</tr>
				<tr>
					<td>첨부파일</td>
					<td><input type="file" name="reloadFile">
						<a href="#">${cBoard.cBoardFileName }</a>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="수정">
						<a href="/consult/consultList.do">목록으로</a>
						<a href="javascript:history.go(-1);">이전 페이지로</a>
					</td>
					<!-- <td></td> -->
				</tr>
			</table>
		</form>
	</div>
</body>
</html>