<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 수강후기 작성</title>
</head>
<body>
	<div id="wrap">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h1 align="center">수강후기 게시판</h1>
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
	</div>
</body>
</html>