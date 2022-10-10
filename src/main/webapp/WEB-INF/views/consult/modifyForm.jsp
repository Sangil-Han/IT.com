<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<title>IT.com : 상담후기 수정</title>
<link href="/resources/css/header.css" rel="stylesheet">
<link href="/resources/css/common.css" rel="stylesheet">
</head>
<body>
	<div id="wrap" class="col-lg-10 offset-lg-1">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<div><img src="/resources/img/consult9.png" alt="상담후기 게시판"></div>
		<br><br>
		<br>
		<form action="/consult/consultModify.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="page" value="${page }">
			<input type="hidden" name="cBoardNo" value="${cBoard.cBoardNo }">
			<input type="hidden" name="cBoardFileName" value="${cBoard.cBoardFileName }">
			<input type="hidden" name="cBoardFileRename" value="${cBoard.cBoardFileRename }">
			<table align="center" border="2" class="table table-bordered w-75">
				<tr>
					<th class="table-primary">제목</td>
					<td><input type="text" name="cBoardTitle" value="${cBoard.cBoardTitle }"></td>
				</tr>
				<tr>
					<th class="table-primary">지역구</td>
					<td><input type="text" name="cBoardLocalName" value="${cBoard.cBoardLocalName }"></td>
				</tr>
				<tr>
					<th class="table-primary">교육원명</td>
					<td><input type="text" name="cBoardCenterName" value="${cBoard.cBoardCenterName}"></td>
				</tr>
				<tr>
					<th class="table-primary">과정명</td>
					<td><input type="text" name="cBoardCourseName" value="${cBoard.cBoardCourseName}"></td>
				</tr>
				<tr>
					<th class="table-primary">상담날짜</td>
					<td><input type="Date" name="consultDate" value="${cBoard.consultDate}"></td>
				</tr>
				<tr>
					<th class="table-primary">내용</td>
					<td><textarea cols="135" rows="20" name="cBoardContents">${cBoard.cBoardContents }</textarea></td>
				</tr>
				<tr>
					<th class="table-primary">첨부파일</td>
					<td class="input-group mb-3"><input class="form-control" id="inputGroupFile02" type="file" name="reloadFile">
						<label class="input-group-text" for="inputGroupFile02">Upload</label>
					</td>
				</tr>
			</table>
			<table align="center">
				<tr colspan="2" align="center">
					<td>
						<input class="btn btn-primary" type="submit" value="수정">
						<a class="btn btn-secondary" href="javascript:history.go(-1);">취소</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<br><br>
</body>
</html>