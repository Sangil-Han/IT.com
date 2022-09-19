<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
  </head>
  <body>
    <form action="/finish/register.do" method="post" enctype="multipart/form-data">
      제목 <input type="text" name="fBoardTitle" /><br /><br />
      지역구 <input type="text" name="fBoardLocalName" /><br /><br />
      교육원명 <input type="text" name="fBoardCenterName" /><br /><br />
      과정명 <input type="text" name="fBoardCourseName" /><br /><br />
      수료연도 <input type="text" name="fBoardFinishYear" /><br /><br />
      취업여부 <input type="text" name="fBoardJobYn" /><br /><br />
      직무 <input type="text" name="fBoardJobName" /><br /><br />
      초봉 <input type="number" name="fBoardSalary" /><br /><br />
      기업명 <input type="text" name="fBoardCompany" /><br /><br />
      내용 <textarea rows="10" cols="50" name="fBoardContents"></textarea>
      <input type="submit" value="등록" />
    </form>
  </body>
</html>
