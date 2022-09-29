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
<link href="/resources/css/menubar-style.css" rel="stylesheet">
<link href="/resources/css/header-style.css" rel="stylesheet">
</head>
<body>
	<div id="wrap">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h1 align="center">수료후기 게시판</h1>
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
						<%-- <a href="/finish/modifyView.do?fBoardNo=${fBoard.fBoardNo }&page=${page}">수정</a> --%>
						<button  onclick="location.href='/finish/modifyView.do?fBoardNo=${fBoard.fBoardNo }&page=${page}' ">수정</button>
						<%-- <a href="#" onclick="boardRemove(${page});">삭제하기</a> --%>
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
			<c:forEach items="${cList }" var="comment">
				<tr>
          
					<td id="modify-inactive">${comment.fCommentContents }</td>
					<td id="modify-active" style="display:none">
						<input id="fCommentNo" type="hidden" value="${comment.fCommentNo}">
						<textarea id="modify-text" name="modifyCommentContents" >${comment.fCommentContents }</textarea>
					</td>
					<td>${comment.fCommentRegtime }</td>
					<td>
						<c:if test="${comment.userId eq sessionScope.loginUser.userId }">
						<%-- <a href="#" onclick="modifyView(this, '${reply.replyContents }',${reply.replyNo });">수정</a>
						<a href="#" onclick="removeReply(${reply.replyNo});">삭제</a> --%>
            <!-- 댓글 수정 -->
						<button id="modify-comment-btn">수정</button>
            <!-- 댓글 삭제 -->
						<button onclick="checkCommentRemove(${comment.fCommentNo}, ${comment.fBoardNo }, ${page });">삭제</button>
            
						</c:if>
					</td>
				</tr>
				<%-- <tr>
			<td colspan="3"><input type="text" size="50" value="${reply.replyContents }"></td>
			<td><button>수정</button></td>
		</tr> --%>
			</c:forEach>
		</table>
	</div>
	<script>
		function checkCommentRemove(commentNo, fBoardNo, page){
			if(confirm("댓글을 삭제하시겠습니까?")){
				location.href="/finish/removeComment.do?fCommentNo="+commentNo+"&fBoardNo="+fBoardNo+"&page="+page;
			}
		}
		
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

  //   function checkModifyActive(){
  //     var modifyCommentArea=document.getElementById("modify-active");
  //     if(modifyCommentArea.style.display=="none"){
  //       modifyCommentArea.style.display="block";
        
  //   }
  // }
		
  var httpRequest;
        if (document.getElementById('modify-comment-btn')==null) {
        }
        document.getElementById('modify-comment-btn').addEventListener('click', ()=> {
          if (document.getElementById('modify-active').style.display == 'block') {
            var commentText = document.getElementById('modify-text').value;
            var fCommentNo = document.getElementById('fCommentNo').value;
            var reqJson = new Object();
            reqJson.commentText = commentText;
            reqJson.fCommentNo = fCommentNo;

            httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = () => {
              if (httpRequest.readyState === XMLHttpRequest.DONE) {
                if (httpRequest.status === 200) {
                  var result = httpRequest.response;
                  
                  document.getElementById('modify-active').style.display = 'none';
                  document.getElementById('modify-inactive').innerText = result.modifiedComment;
                  document.getElementById('modify-inactive').style.display='block'
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
            document.getElementById('modify-inactive').style.display = 'none';
            document.getElementById('modify-active').style.display = 'block';
          }
        });
		
	</script>
</body>
</html>