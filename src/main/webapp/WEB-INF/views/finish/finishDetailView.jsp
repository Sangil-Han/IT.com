<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
        <div class="col-lg-8 offset-lg-2">
          <table class="table table-boldered border" >
            <tbody>
              <tr>
                <th scope="row" class="table-primary">제목</th>
                <td>${fBoard.fBoardTitle }</td>
              </tr>
              <tr>
                <th scope="row" class="table-primary">지역구</th>
                <td>${fBoard.fBoardLocalName }</td>
              </tr>
              <tr>
                <th scope="row" class="table-primary">교육원명</th>
                <td colspan="2">${fBoard.fBoardCenterName }</td>
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

    
		<br> <br>
		<table align="center" width="500" border="1">
			<tr>
				<td>제목</td>
				<td>${fBoard.fBoardTitle }</td>
			<tr>
				<td>지역구</td>
				<td>${fBoard.fBoardLocalName }</td>
			</tr>
			<tr>
				<td>교육원명</td>
				<td>${fBoard.fBoardCenterName }</td>
			</tr>
			<tr>
				<td>과정명</td>
				<td>${fBoard.fBoardCourseName }</td>
			</tr>
			<tr>
				<td>수료연도</td>
				<td>${fBoard.fBoardFinishYear }</td>
			</tr>
			<tr>
				<td>취업여부</td>
				<td>${fBoard.fBoardJobYn }</td>
			</tr>
			<c:if test="${fBoard.fBoardJobYn  eq 'Y'}">
				<tr>
					<td>직무</td>
					<td>${fBoard.fBoardJobName }</td>
				</tr>
				<tr>
					<td>초봉</td>
					<td>${fBoard.fBoardSalary }</td>
				</tr>
				<tr>
					<td>기업명</td>
					<td>${fBoard.fBoardCompany }</td>
				</tr>
			</c:if>
			<tr height="300">
				<td>내용</td>
				<td>${fBoard.fBoardContents }
					<img alt="본문이미지" src="/resources/fuploadFiles/${fBoard.fBoardFileRename}">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<c:if test="${fBoard.fBoardUserId eq sessionScope.loginUser.userId}">
						<form action="finish/modifyView.do" method=post>
							<input type="hidden" name="fBoardNo" value="${fBoard.fBoardNo }">
            				<input type="hidden" name="page" value="${page}">
							<button>수정</button>
						</form>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a href="/finish/listView.do?page=${page }">목록으로</a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<form action="/finish/addUpCount.do" method="post" style="display: inline">
						<input type="hidden" name="userId" value="${sessionScope.loginUser.userId }"/>
						<input type="hidden" name="fBoardNo" value="${fBoard.fBoardNo }"/>
						<input type="hidden" name="page" value="${page }"/>
						<button onclick="return checkCanUpDown('${isRecomm}');">추천 ${upCount }</button>
					</form>
					<form action="/finish/addDownCount.do" method="post" style="display: inline">
						<input type="hidden" name="userId" value="${sessionScope.loginUser.userId }"/>
						<input type="hidden" name="fBoardNo" value="${fBoard.fBoardNo }"/>
						<input type="hidden" name="page" value="${page }"/>
						<button onclick="return checkCanUpDown('${isRecomm}');">비추천 ${downCount }</button>
					</form>
				</td>
			</tr>
				
		</table>

		<!-- 댓글 등록 -->
		<form action="/finish/addComment.do" method="post">
			<input type="hidden" name="fBoardNo" value="${fBoard.fBoardNo }">
			<input type="hidden" name="page" value=${page }>
			<table align="center" width="500" border="1">
				<tr>
					<td><textarea rows="3" cols="55" name="fCommentContents"></textarea></td>
					<td>
						<button>등록하기</button>
					</td>
				</tr>
			</table>
		</form>

		<!-- 댓글 목록 -->
		<table align="center" width="500" border="1">
			<c:forEach items="${cList }" var="comment" varStatus="i">
				<tr>
          
					<td id="modify-inactive${i.count}">${comment.fCommentContents }</td>
					<td id="modify-active${i.count}" style="display:none">
						<input id="fCommentNo${i.count}" type="hidden" value="${comment.fCommentNo}">
						<textarea id="modify-text${i.count}" name="modifyCommentContents" >${comment.fCommentContents }</textarea>
					</td>
					<td>${comment.fCommentRegtime }</td>
					<td>
						<c:if test="${comment.userId eq sessionScope.loginUser.userId }">
            <!-- 댓글 수정 -->
						<button id="modify-comment-btn${i.count}">수정</button>
            <!-- 댓글 삭제 -->
            			<form action="/finish/removeComment.do" method=post onsubmit="return checkRemove();">
            				<input type="hidden" name="fCommentNo" value="${comment.fCommentNo }">
            				<input type="hidden" name="fBoardNo" value="${comment.fBoardNo }">
            				<input type="hidden" name="page" value="${page }">
							<button>삭제</button>
            			</form>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
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
		}
		
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
</body>
</html>