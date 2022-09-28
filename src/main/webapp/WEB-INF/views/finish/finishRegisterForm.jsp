<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>IT.com : 수료후기 작성</title>
    <link href="/resources/css/header.css" rel="sytlesheet" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
  </head>
  <body>
    <div id="wrap">
      <jsp:include page="../common/header.jsp"></jsp:include>
      <h1 align="center">수료후기 게시판</h1>
      <form action="/finish/register.do" method="post" enctype="multipart/form-data">
        <input type="hidden" name="fBoardUserId" value="${sessionScope.loginUser.userId }" /> 제목 <input type="text" name="fBoardTitle" /><br /><br />
        지역구 <input type="text" name="fBoardLocalName" /><br /><br />
        교육원명 <input type="text" name="fBoardCenterName" /><br /><br />
        과정명 <input type="text" name="fBoardCourseName" /><br /><br />
        수료연도 <input type="text" name="fBoardFinishYear" /><br /><br />
        취업여부 <input type="text" name="fBoardJobYn" /><br /><br />
        직무 <input type="text" name="fBoardJobName" /><br /><br />
        초봉 <input type="number" name="fBoardSalary" /><br /><br />
        기업명 <input type="text" name="fBoardCompany" /><br /><br />
        내용<textarea rows="10" cols="50" name="fBoardContents"></textarea>
        <input type="file" name="uploadFile" />
        <input type="submit" value="등록" />
      </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
