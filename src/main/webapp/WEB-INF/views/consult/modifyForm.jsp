<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
	<h1 align="center">게시글 수정하기</h1>
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
</body>
</html>