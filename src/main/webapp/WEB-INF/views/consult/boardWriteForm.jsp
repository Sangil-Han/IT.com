<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 상담후기 작성</title>
</head>
<body>
	<div id="wrap">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h1 align="center">상담후기 게시판</h1>
		<br> <br>
		<form action="/consult/consultRegister.do" method="post"
			enctype="multipart/form-data">
			<table align="center" border="1">
				<tr>
					<td>제목</td>
					<td><input type="text" name="cBoardTitle"></td>
				</tr>
				<tr>
					<td>지역구명</td>
					<td><input type="text" name="cBoardLocalName"></td>
				</tr>
				<tr>
					<td>교육원명</td>
					<td><input type="text" name="cBoardCenterName"></td>
				</tr>
				<tr>
					<td>과정명</td>
					<td><input type="text" name="cBoardCourseName"></td>
				</tr>
				<tr>
					<td>상담 날짜</td>
					<td><input type="date" name="consultDate"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea cols="50" rows="20" name="cBoardContents"></textarea></td>
				</tr>
				<tr>
					<td>첨부파일</td>
					<td><input type="file" name="uploadFile"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="등록"> <input
						type="reset" value="취소"></td>
					<!-- <td></td> -->
				</tr>
			</table>
		</form>
	</div>
</body>
</html>