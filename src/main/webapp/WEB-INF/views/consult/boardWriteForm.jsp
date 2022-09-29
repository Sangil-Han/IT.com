<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<title>IT.com : 상담후기 작성</title>
</head>
<body>
	<div id="wrap">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<div><img src="/resources/img/consult9.png" alt="상담후기 게시판"></div>
		<br><br>
		<form action="/consult/consultRegister.do" method="post"
			enctype="multipart/form-data">
			<table align="center" border="2" class="table table-bordered w-75">
			<input type="hidden" name="userId" value="${sessionScope.loginUser.userId }">
				<tr>
					<th class="table-primary">제목</td>
					<td><input type="text" name="cBoardTitle"></td>
				</tr>
				<tr>
					<th class="table-primary">지역구명</td>
					<td><input type="text" name="cBoardLocalName"></td>
				</tr>
				<tr>
					<th class="table-primary">교육원명</td>
					<td><input type="text" name="cBoardCenterName"></td>
				</tr>
				<tr>
					<th class="table-primary">과정명</td>
					<td><input type="text" name="cBoardCourseName"></td>
				</tr>
				<tr>
					<th class="table-primary">상담 날짜</td>
					<td><input type="date" name="consultDate"></td>
				</tr>
				<tr>
					<th class="table-primary">내용</td>
					<td><textarea cols="135" rows="20" name="cBoardContents"></textarea></td>
				</tr>
				<tr>
					<th class="table-primary">첨부파일</td>
					<td class="input-group mb-3"><input class="form-control" id="inputGroupFile02" type="file" name="uploadFile">
						<label class="input-group-text" for="inputGroupFile02">Upload</label>
					</td>
				</tr>
			</table>
			<table align="center">
				<tr>
					<td colspan="2"><input class="btn btn-primary" type="submit" value="등록"> <a class="btn btn-secondary" href="javascript:history.go(-1);">취소</a></td>
				</tr>
			</table>
		</form>
	</div>
	<br><br><br>
</body>
</html>