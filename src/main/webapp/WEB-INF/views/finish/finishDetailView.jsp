<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 수료후기 상세 조회</title>
<link href="/resources/css/header.css" rel="sytlesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<style>
  th{
    text-align: center;
  }
</style>
</head>
<body>
	<div id="wrap">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h1 class="h1" align="center">수료후기 게시판</h1>
    <div class="container">
      <div class="row">
        <div class="col-lg-10 offset-lg-1">
          <table class="table table-bordered" >
            <tbody>
              <tr class>
                <th scope="row" class="table-primary" >제목</th>
                <td class="ms-2">${fBoard.fBoardTitle }</td>
              </tr>
              <tr>
                <th scope="row" class="table-primary">지역구</th>
                <td>${fBoard.fBoardLocalName }</td>
              </tr>
              <tr>
                <th scope="row" class="table-primary">교육원명</th>
                <td>${fBoard.fBoardCenterName }</td>
              </tr>
              <tr>
                <th scope="row" class="table-primary">과정명</th>
                <td>${fBoard.fBoardCourseName }</td>
              </tr>
              <tr>
                <th scope="row" class="table-primary">수료연도</th>
                <td>${fBoard.fBoardFinishYear }</td>
              </tr>
              <tr>
                <th scope="row" class="table-primary">취업여부</th>
                <td>${fBoard.fBoardJobYn }</td>
              </tr>
              <c:if test="${fBoard.fBoardJobYn  eq 'Y'}">
                <tr>
                  <th scope="row" class="table-primary">직무</th>
                  <td>${fBoard.fBoardJobName }</td>
                </tr>
                <tr>
                  <th scope="row" class="table-primary">초봉</th>
                  <td>${fBoard.fBoardSalary }</td>
                </tr>
                <tr>
                  <th scope="row" class="table-primary">기업명</th>
                  <td>${fBoard.fBoardCompany }</td>
                </tr>
              </c:if>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="row px-3">
        <div class="col-lg-10 offset-lg-1 border border-secondary rounded-1 p-5">
          <c:if test="${fBoard.fBoardFileRename ne null}">
            <img class="img-fluid" src="/resources/files/fuploadFiles/${fBoard.fBoardFileRename}">
          </c:if>
          <p class="py-4">${fBoard.fBoardContents }</i></p>
        </div>
        <div class="col-md-8 offset-md-2 py-5 text-center">
          <form action="/finish/addUpCount.do" method="post" style="display: inline">
						<input type="hidden" name="userId" value="${sessionScope.loginUser.userId }"/>
						<input type="hidden" name="fBoardNo" value="${fBoard.fBoardNo }"/>
						<input type="hidden" name="page" value="${page }"/>
            <button class="btn btn-primary"onclick="return checkCanUpDown('${isRecomm}');"><i class="fa-solid fa-thumbs-up fa-lg"></i> 추천 <b>${upCount }</b></button>
          </form>
          <form action="/finish/addDownCount.do" method="post" style="display: inline">
						<input type="hidden" name="userId" value="${sessionScope.loginUser.userId }"/>
						<input type="hidden" name="fBoardNo" value="${fBoard.fBoardNo }"/>
						<input type="hidden" name="page" value="${page }"/>
            <button class="btn btn-danger" onclick="return checkCanUpDown('${isRecomm}');"><i class="fa-solid fa-thumbs-down fa-lg"></i> 비추천 <b>${downCount } </b></button>
          </form>
        </div>
        <div class="col-md-2 py-5 text-center">
          <c:if test="${fBoard.fBoardUserId eq sessionScope.loginUser.userId}">
						<form action="/finish/modifyView.do" method=post class="me-5">
							<input type="hidden" name="fBoardNo" value="${fBoard.fBoardNo }">
            	<input type="hidden" name="page" value="${page}">
							<input type="submit" class="btn btn-secondary" value="수정"/>
						</form>
					</c:if>
        </div>
      </div>
    </div>

    <div class="container">
      <div class="row">
        <div class="col-lg-10 offset-lg-1">
          <table class="table">
              <tr class="border-bottom">
                <td scope="row"><b class="fs-5">댓글 ${fn:length(cList)}</b></td>
              </tr>
              <c:forEach items="${cList }" var="comment" varStatus="i">
                <tr class="border-bottom">
                  <td id="modify-inactive${i.count}" width="67%" style="border-style: none;">${comment.fCommentContents }</td>
					        <td id="modify-active${i.count}" style="display:none" width="67%">
						        <input id="fCommentNo${i.count}" type="hidden" value="${comment.fCommentNo}">
                    <div>
                      <textarea class="form-control" id="modify-text${i.count}" name="modifyCommentContents" style="resize:none">${comment.fCommentContents }</textarea>
                    </div>
					        </td>
                  <td width="15%" class="lh-lg">${comment.fCommentRegtime }</td>
                  <td width="18%" align="right" >
                    <c:if test="${comment.userId eq sessionScope.loginUser.userId }">
                    <form action="/finish/removeComment.do" method=post onsubmit="return checkRemove();" >
                      <input type="hidden" name="fCommentNo" value="${comment.fCommentNo }">
                      <input type="hidden" name="fBoardNo" value="${comment.fBoardNo }">
                      <input type="hidden" name="page" value="${page }">
                      <!-- <button type="button" class="btn" id="modify-comment-btn${i.count}">수정</button>
                      <button class="btn">삭제</button> -->
                      <div class="btn-group" role="group" aria-label="Basic example">
                        <button type="button" class="btn btn-outline-secondary text-black" id="modify-comment-btn${i.count}" >수정</button>
                        <button class="btn btn-outline-secondary text-black">삭제</button>
                      </div>
                    </form>
                    </c:if>
                  </td>
                </tr>
              </c:forEach>
            </table>
        </div>
      </div>
      <div class="row mt-3">
        <div class="col-lg-10 offset-lg-1">
          
            <form action="/finish/addComment.do" method="post">
              <input type="hidden" name="fBoardNo" value="${fBoard.fBoardNo }">
              <input type="hidden" name="page" value=${page }>
              <div class="input-group mb-3">
                <input type="text" class="form-control" name="fCommentContents" placeholder="댓글을 입력해주세요." aria-label="댓글을 입력해주세요." aria-describedby="button-addon2">
                <button class="btn btn-outline-secondary">등록</button>
              </div>
            </form>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-2 offset-sm-5 text-center my-3">
          <button class="btn btn-secondary px-4" onclick="location.href='/finish/listView.do?page=${page }';">목록으로</button>
        </div>
      </div> 
    </div>

	<script>
		
		function checkCanUpDown(isRecomm){
			const yes='Y'
			if(isRecomm==yes){
				alert("이미 추천 또는 비추천한 게시글입니다.");
				return false;
			}
			else{
				return true;
			}
		}'';;''
		
		function checkRemove(){
			if (confirm("댓글을 삭제하시겠습니까?")){
				return true;
			}
			else{
				return false;
			}
		}

  var httpRequest;
       let modifyCommentList=Array.from(document.querySelectorAll('[id^=modify-comment-btn]'));
       let modifyInactiveList=Array.from(document.querySelectorAll('[id^=modify-inactive]'));
       let modifyActiveList=Array.from(document.querySelectorAll('[id^=modify-active]'));
       let fCommentNoList=Array.from(document.querySelectorAll('[id^=fCommentNo]'));
       let modifyTextList=Array.from(document.querySelectorAll('[id^=modify-text]'));

       if (modifyCommentList==null){
        console.log("list is null");
       }
        for(let i=0; i<modifyCommentList.length; i++){
          modifyCommentList[i].addEventListener('click', ()=> {
          if (modifyActiveList[i].style.display == 'block') {
            var commentText = modifyTextList[i].value;
            var fCommentNo = fCommentNoList[i].value;
            console.log(commentText);
            console.log(fCommentNo);
            var reqJson = new Object();
            reqJson.commentText = commentText;
            reqJson.fCommentNo = fCommentNo;

            httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = () => {
              if (httpRequest.readyState === XMLHttpRequest.DONE) {
                if (httpRequest.status === 200) {
                  var result = httpRequest.response;
                  
                  modifyActiveList[i].style.display = 'none';
                  modifyInactiveList[i].innerText = result.modifiedComment;
                  modifyInactiveList[i].style.display='block'
                } else {
                  alert(httpRequest.status);
                }
              }
            };
            httpRequest.open('POST', '/finish/modifyComment.do', true);
            httpRequest.responseType = 'json';
            httpRequest.setRequestHeader('Content-Type', 'application/json');
            httpRequest.send(JSON.stringify(reqJson));
          } 
          else {
            modifyInactiveList[i].style.display = 'none';
            modifyActiveList[i].style.display = 'block';
          }
        });
        }
		
	</script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://kit.fontawesome.com/422d96f707.js" crossorigin="anonymous"></script>
</body>
</html>