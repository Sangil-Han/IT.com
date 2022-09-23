<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 수료후기 수정</title>
</head>
<body>
	<div id="wrap">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h1 align="center">수료후기 게시판</h1>
		<form action="/finish/modify.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="page" value="${page }"/>
			<input type="hidden" name="fBoardNo" value="${fBoard.fBoardNo }"/>
			<input type="hidden" name="fBoardFileName" value="${fBoard.fBoardFileName }"/>
			<input type="hidden" name="fBoardFileRename" value="${fBoard.fBoardFileRename }"/>
			제목 <input type="text" name="fBoardTitle" value="${fBoard.fBoardTitle }" /><br /><br />
			지역구 <input type="text" name="fBoardLocalName" value="${fBoard.fBoardLocalName }" /><br /><br />
			교육원명 <input type="text" name="fBoardCenterName" value="${fBoard.fBoardCenterName }" /><br /><br />
			과정명 <input type="text" name="fBoardCourseName" value="${fBoard.fBoardCourseName }"/><br /><br />
			수료연도 <input type="text" name="fBoardFinishYear" value="${fBoard.fBoardFinishYear }" /><br /><br />
			취업여부 <input type="text" name="fBoardJobYn" value="${fBoard.fBoardJobYn }" /><br /><br />
			직무 <input type="text" name="fBoardJobName" value="${fBoard.fBoardJobName }"/><br /><br />
			초봉 <input type="number" name="fBoardSalary" value="${fBoard.fBoardSalary }" /><br /><br />
			기업명 <input type="text" name="fBoardCompany" value="${fBoard.fBoardCompany }"/><br /><br />
			내용 <textarea rows="10" cols="50" name="fBoardContents">${fBoard.fBoardContents }</textarea>
			<input type="file" name="reloadFile" />
			<a href="#">${fBoard.fBoardFileName }</a>
			<input type="submit" value="수정" />
			<a href="/finish/listView.do?page=${page }">목록으로</a>
			<a href="javascript:history.go(-1);">이전 페이지로</a>
		</form>
    </div>
</body>
</html>