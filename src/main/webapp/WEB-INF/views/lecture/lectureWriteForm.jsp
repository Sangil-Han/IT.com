<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>
<h1 align="center">게시글 등록 페이지</h1>
	<br><br>
	<form action="/lecture/register.do" method="post" enctype="multipart/form-data">
		<table align="center" border="1">
			<tr>
				<td>제목</td>
				<td><input type="text" name="lBoardTitle"></td>
			</tr>
			<tr>
				<td>지역구</td>
				<td>
					<select name="lBoardLocalName">
						<option value="">지역구</option>
						<option value="종로구">종로구</option>
						<option value="종로구">강남구</option>
						<option value="서대문구">서대문구</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>교육원명</td>
				<td>
					<select name="lBoardCenterName">
						<option value="">교육원명</option>
						<option value="KH정보교육원">KH정보교육원</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>과정명</td>
				<td><input type="text" name="lBoardCourseName"></td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><input type="file" name="uploadFile"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea cols="50" rows="10" name="lBoardContents"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="등록">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>