<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 수강후기 상세 조회</title>
<link href="/resources/css/menubar-style.css" rel="stylesheet">
<link href="/resources/css/header-style.css" rel="stylesheet">
</head>
<body>
	<div id="wrap">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<h1 align="center">수강후기 게시판</h1>
		<br><br>
		<table align="center" width="500" border="1">
			<tr>
				<td>제목</td>
				<td>${lectureBoard.lBoardTitle }</td>
			</tr>
			<tr>
				<td>지역구</td>
				<td>${lectureBoard.lBoardLocalName }</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${totalViewCount }</td>
			</tr>
			<tr>
				<td>교육원명</td>
				<td>${lectureBoard.lBoardCenterName }</td>
			</tr>
			<tr>
				<td>과정명</td>
				<td>${lectureBoard.lBoardCourseName }</td>
			</tr>
			<tr height="300">
				<td>내용</td>
				<td>${lectureBoard.lBoardContents }</td>
			</tr>
			
			<tr>
				<td>첨부파일</td>
				<td><img alt="본문이미지" src="/resources/buploadFiles/${lectureBoard.lBoardFileRename }" width="300" height="300"></td>
			</tr>
			
		</table>
	
	<!-- 댓글 등록 -->
	<form action="/lecture/lectureAddComment.do" method="post">
			<input type="hidden" name="lBoardNo" value="${lectureBoard.lBoardNo }">
			<input type="hidden" name="page" value=${page }>
			<table align="center" width="500" border="1">
				<tr>
					<td><textarea rows="3" cols="55" name="lCommentContents"></textarea></td>
					<td>
						<button>등록하기</button>
					</td>
				</tr>
			</table>
		</form>

	
		<!-- 댓글 목록 -->
		<table align="center" width="500" border="1">
			<c:forEach items="${lcList }" var="lbComment" varStatus="i">
				<tr>
					<td id="lmodify-inactive${i.count}">${lbComment.lCommentContents }</td>
					<td id="lmodify-active${i.count}" style="display:none">
						<input id="lCommentNo${i.count}" type="hidden" value="${lbComment.lCommentNo}">
						<textarea id="lmodify-text${i.count}" name="lmodifyCommentContents" >${lbComment.lCommentContents }</textarea>
					</td>
					<td>${lbComment.lCommentRegtime }</td>
					<td>
						<c:if test="${lbComment.userId eq sessionScope.loginUser.userId }">
            <!-- 댓글 수정 -->
						<button id="lmodify-comment-btn${i.count}">수정</button>
            <!-- 댓글 삭제 -->
          			    <button onclick="removeComment(${lbComment.lCommentNo},${lectureBoard.lBoardNo }, ${page });">삭제</button>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script>
		function removeComment(lCommentNo, lBoardNo, page) {
			if(confirm("댓글을 삭제하시겠습니까?")) {
				location.href="/lecture/removeComment.do?lBoardNo="+lBoardNo+"&page="+page+"&lCommentNo="+lCommentNo;
			}
		}
		
		var httpRequest;
		let lmodifyCommentList=Array.from(document.querySelectorAll('[id^=lmodify-comment-btn]'));
	       let lmodifyInactiveList=Array.from(document.querySelectorAll('[id^=lmodify-inactive]'));
	       let lmodifyActiveList=Array.from(document.querySelectorAll('[id^=lmodify-active]'));
	       let lCommentNoList=Array.from(document.querySelectorAll('[id^=lCommentNo]'));
	       let lmodifyTextList=Array.from(document.querySelectorAll('[id^=lmodify-text]'));
	      
	       if (lmodifyCommentList==null){
	           console.log("list is null");
	          }
	           for(let i=0; i<lmodifyCommentList.length; i++){
	             lmodifyCommentList[i].addEventListener('click', ()=> {
	             if (lmodifyActiveList[i].style.display == 'block') {
	               var lcommentText = lmodifyTextList[i].value;
	               var lCommentNo = lCommentNoList[i].value;
	               console.log(lcommentText);
	               console.log(lCommentNo);
	               var reqJson = new Object();
	               reqJson.lcommentText = lcommentText;
	               reqJson.lCommentNo = lCommentNo;

	               httpRequest = new XMLHttpRequest();
	               httpRequest.onreadystatechange = () => {
	                 if (httpRequest.readyState === XMLHttpRequest.DONE) {
	                   if (httpRequest.status === 200) {
	                     var result = httpRequest.response;
	                     
	                     lmodifyActiveList[i].style.display = 'none';
	                     lmodifyInactiveList[i].innerText = result.lmodifiedComment;
	                     lmodifyInactiveList[i].style.display='block'
	                   } else {
	                     alert(httpRequest.status);
	                   }
	                 }
	               };
	               httpRequest.open('POST', '/lecture/modifyComment.do', true);
	               httpRequest.responseType = 'json';
	               httpRequest.setRequestHeader('Content-Type', 'application/json');
	               httpRequest.send(JSON.stringify(reqJson));
	             } 
	             else {
	               lmodifyInactiveList[i].style.display = 'none';
	               lmodifyActiveList[i].style.display = 'block';
	             }
	           });
	           }
</script>
</body>
</html>