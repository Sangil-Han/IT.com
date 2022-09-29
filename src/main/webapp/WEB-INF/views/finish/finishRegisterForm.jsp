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
      <div class="container">
        <div class="row">
          <div class="col-lg-10 offset-lg-1">
            <form action="/finish/register.do" method="post" enctype="multipart/form-data">
              <input type="hidden" name="fBoardUserId" value="${sessionScope.loginUser.userId }" />
              <table class="table table-bordered">
                <tbody>
                  <tr>
                    <th scope="row" class="table-primary">제목</th>
                    <td><input type="text" class="form-control" name="fBoardTitle" /></td>
                  </tr>
                  <tr>
                    <th scope="row" class="table-primary">지역구</th>
                    <td><input type="text" class="form-control" name="fBoardLocalName" /></td>
                  </tr>
                  <tr>
                    <th scope="row" class="table-primary">교육원명</th>
                    <td><input type="text" class="form-control" name="fBoardCenterName" /></td>
                  </tr>
                  <tr>
                    <th scope="row" class="table-primary">과정명</th>
                    <td><input type="text" class="form-control" name="fBoardCourseName" /></td>
                  </tr>
                  <tr>
                    <th scope="row" class="table-primary">수료연도</th>
                    <td><input type="text" class="form-control" name="fBoardFinishYear" /></td>
                  </tr>
                  <tr>
                    <th scope="row" class="table-primary">취업여부</th>
                    <td>
                      <input class="form-check-input" type="radio" name="fBoardJobYn" id="jobY" value="Y" />
                      <label class="form-check-label" for="jobY"> Y</label>

                      <input class="form-check-input" type="radio" name="fBoardJobYn" id="jobN" value="N" />
                      <label class="form-check-label" for="jobN"> N</label>
                    </td>
                  </tr>
                  <tr>
                    <th scope="row" class="table-primary">직무</th>
                    <td><input type="text" class="form-control" name="fBoardJobName" /></td>
                  </tr>
                  <tr>
                    <th scope="row" class="table-primary">초봉</th>
                    <td><input type="text" class="form-control" name="fBoardSalary" /></td>
                  </tr>
                  <tr>
                    <th scope="row" class="table-primary">기업명</th>
                    <td><input type="text" class="form-control" name="fBoardCompany" /></td>
                  </tr>
                </tbody>
              </table>
              <div class="row mb-4">
                <div class="col">
                  <textarea class="form-control" name="fBoardContents" style="resize: none; height: 500px"></textarea>
                </div>
              </div>
              <div class="row">
                <div class="input-group mb-3" style="width: 75%">
                  <input type="file" class="form-control" id="inputGroupFile02" name="uploadFile" />
                  <label class="input-group-text" for="inputGroupFile02">Upload</label>
                </div>
                <button class="btn btn-primary ms-3" type="submit" style="width: 10%; height: 80%">등록</button>
                <button class="btn btn-primary ms-3" type="reset" onclick="location.href='javascript:history.go(-1);'" style="width: 10%; height: 80%">취소</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
