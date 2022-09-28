<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 관리자 페이지</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<link href="/resources/css/header.css" rel="stylesheet">
<link href="/resources/css/common.css" rel="stylesheet">
</head>
<body>
	<div id="wrap" class="col-lg-10 offset-lg-1">
		<jsp:include page="../common/header.jsp"></jsp:include>
      <h2 id="page-title" class="text-bg-primary p-3 bg-opacity-50">
				<a href="/admin/adminPageView.do">Admin Page</a>
			</h2>
    <ul class="nav nav-pills nav-fill" id="pills-tab" role="tablist">
      <li class="nav-item" role="presentation">
        <button class="nav-link" id="pills-home-tab" data-bs-toggle="pill" data-bs-target="#pills-home" type="button" role="tab" aria-controls="pills-home" aria-selected="true">교육원 조회</button>
      </li>
      <li class="nav-item" role="presentation">
        <button class="nav-link" id="pills-profile-tab" data-bs-toggle="pill" data-bs-target="#pills-profile" type="button" role="tab" aria-controls="pills-profile" aria-selected="false">회원 관리</button>
      </li>
      <li class="nav-item" role="presentation">
        <button class="nav-link" id="pills-contact-tab" data-bs-toggle="pill" data-bs-target="#pills-contact" type="button" role="tab" aria-controls="pills-contact" aria-selected="false">등업 관리</button>
      </li>
    </ul>
    <div class="tab-content" id="pills-tabContent">
      <div class="tab-pane fade show" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab" tabindex="0">
        <h4>교육원 조회</h4>
				<form action="/admin/searchCenter.do">
					<select name="searchOption" class="search-option">
						<option value="all">전체</option>
						<option value="region">지역구</option>
						<option value="center">교육원</option>
					</select> <input type="text" />
					<button>검색</button>
				</form>
				<table width="80%">
					<tr>
						<th width="30%">지역구</th>
						<th width="70%">교육원</th>
					</tr>
					<tr>
						<td>{지역구}</td>
						<td>{교육원}</td>
					</tr>
				</table>
      </div>
      <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab" tabindex="0">
        <h4>회원 관리</h4>
				<form action="/admin/userSearch.do" class="search-option">
					<select name="searchOption" class="search-option">
						<option value="">전체</option>
						<option value="">아이디</option>
						<option value="">이메일</option>
					</select> <input type="text" />
					<button>검색</button>
				</form>
				<form action="/admin/userDelete.do" method="post"
					id="user-delete-form" onsubmit="return beforeUserDeletion();">
					<button>삭제</button>
					<table>
						<tr>
							<th>NO</th>
							<th>아이디</th>
							<th>이메일</th>
							<th>등급</th>
							<th>포인트</th>
							<th>가입일</th>
							<th>탈퇴일</th>
							<th><input type="checkbox" id="user-chk-all"
								name="userChkAll" onclick="checkAll(this.name);" /></th>
						</tr>
						<c:if test="${not empty uList }">
							<c:forEach items="${uList }" var="user" varStatus="i">
								<tr>
									<td>${upi.rowCount - ((upi.currentPage - 1 ) * upi.rowLimit + i.index) }</td>
									<td><a
										href="/user/myPostView.do?userId=${user.userId }&page=${upi.currentPage }">${user.userId }</a>
									</td>
									<td>${user.userEmail }</td>
									<td>${user.userLevel }</td>
									<td>${user.userPoint }</td>
									<td>${user.joinDate }</td>
									<td><c:if test="${user.withdrawal eq 'N' }">-</c:if> <c:if
											test="${user.withdrawal eq 'Y' }">${user.withdrawDate }</c:if>
									</td>
									<td><input type="checkbox" name="userChkOne"
										value="${user.userId }" onclick="checkOnes(this.name);">
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="7"><c:if test="${upi.startPage ne 1 }">
										<a
											href="/admin/${url }.do?content=user&page=${upi.startPage - 1 }">[이전]</a>
									</c:if> <c:forEach var="p" begin="${upi.startPage }"
										end="${upi.endPage }">
										<c:if test="${upi.currentPage eq p }">
											<b>${p }</b>
										</c:if>
										<c:if test="${upi.currentPage ne p }">
											<a href="/admin/${url }.do?content=user&page=${p }">${p }</a>
										</c:if>
									</c:forEach> <c:if test="${upi.endPage ne upi.pageCount }">
										<a
											href="/admin/${url }.do?content=user&page=${upi.endPage + 1 }">[다음]</a>
									</c:if></td>
							</tr>
						</c:if>
						<c:if test="${empty uList }">
							<tr>
								<td colspan="8">회원이 존재하지 않습니다.</td>
							</tr>
						</c:if>
					</table>
				</form>
      </div>
      <div class="tab-pane fade" id="pills-contact" role="tabpanel" aria-labelledby="pills-contact-tab" tabindex="0"><h4>등업 관리</h4>
				<form action="" method="post" id="level-control-form">
					<button type="button" id="approve" class="level-control-btn">승인</button>
					<button type="button" id="deny" class="level-control-btn">거절</button>
					<table>
						<tr>
							<th>NO</th>
							<th>상태</th>
							<th>아이디</th>
							<th>신청등급</th>
							<th>신청일</th>
							<th>첨부파일</th>
							<th><input type="checkbox" id="level-chk-all"
								name="levelChkAll" onclick="checkAll(this.name);" /></th>
						</tr>
						<c:if test="${not empty luList }">
							<c:forEach items="${luList }" var="level" varStatus="i">
								<tr>
									<td>${lupi.rowCount - ((lupi.currentPage - 1 ) * lupi.rowLimit + i.index) }</td>
									<c:if test="${level.applicationStatus eq 'Y' }">
										<td>승인</td>
									</c:if>
									<c:if
										test="${level.applicationStatus ne 'Y' && level.applicationStatus ne 'N' }">
										<td>대기</td>
									</c:if>
									<c:if test="${level.applicationStatus eq 'N' }">
										<td>거절</td>
									</c:if>
									<td>${level.userId }</td>
									<td>${level.applicationLv }</td>
									<td>${level.applicationDate }</td>
									<td><a><img alt="" src=""></a></td>
									<td><input type="checkbox" name="levelChkOne"
										value="${level.userId }" onclick="checkOnes(this.name);" /></td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="7"><c:if test="${lupi.startPage ne 1 }">
										<a
											href="/admin/${url }.do?content=level&page=${lupi.startPage - 1 }">[이전]</a>
									</c:if> <c:forEach var="p" begin="${upi.startPage }"
										end="${lupi.endPage }">
										<c:if test="${lupi.currentPage eq p }">
											<b>${p }</b>
										</c:if>
										<c:if test="${lupi.currentPage ne p }">
											<a href="/admin/${url }.do?content=level&page=${p }">${p }</a>
										</c:if>
									</c:forEach> <c:if test="${lupi.endPage ne lupi.pageCount }">
										<a
											href="/admin/${url }.do?content=level&page=${lupi.endPage + 1 }">[다음]</a>
									</c:if></td>
							</tr>
						</c:if>
						<c:if test="${empty luList }">
							<tr>
								<td colspan="8">등업 신청 내역이 존재하지 않습니다.</td>
							</tr>
						</c:if>
					</table>
				</form>
      </div>
    </div>
  </div>
	<script>
		// 체크박스 - 전체 선택 to 모두 선택
		function checkAll(name) {
			if (name == 'userChkAll') {
				let isChecked = document.querySelector('#user-chk-all').checked;
				let oneChkBx = document.getElementsByName('userChkOne');
				for (let i = 0; i < oneChkBx.length; i++) {
					oneChkBx[i].checked = isChecked;
				}
			} else if (name == 'levelChkAll') {
				let isChecked = document.querySelector('#level-chk-all').checked;
				let oneChkBx = document.getElementsByName('levelChkOne');
				for (let i = 0; i < oneChkBx.length; i++) {
					oneChkBx[i].checked = isChecked;
				}
			}
		}

		// 체크박스 - 모두 선택 to 전체 선택
		function checkOnes(name) {
			if (name == 'userChkOne') {
				let oneChkBx = document.getElementsByName('userChkOne');
				let chkBxCount = oneChkBx.length;
				let chkedCount = 0;
				for (let i = 0; i < oneChkBx.length; i++) {
					if (oneChkBx[i].checked) {
						chkedCount++;
					}
				}
				if (chkBxCount == chkedCount) {
					document.querySelector('#user-chk-all').checked = true;
				} else {
					document.querySelector('#user-chk-all').checked = false;
				}
			} else if (name == 'levelChkOne') {
				let oneChkBx = document.getElementsByName('levelChkOne');
				let chkBxCount = oneChkBx.length;
				let chkedCount = 0;
				for (let i = 0; i < oneChkBx.length; i++) {
					if (oneChkBx[i].checked) {
						chkedCount++;
					}
				}
				if (chkBxCount == chkedCount) {
					document.querySelector('#level-chk-all').checked = true;
				} else {
					document.querySelector('#level-chk-all').checked = false;
				}
			}
		}

		// 체크된 유저 리스트
		function beforeUserDeletion() {
			let checked = '';
			document.querySelectorAll('input[name=userChkOne]:checked')
					.forEach(function(item) {
						checked += item.value;
						checked += ',';
					});
			if (checked.length === 0) {
				alert('삭제할 회원을 선택해주세요.');
				return false;
			} else {
				if (confirm('정말 삭제하시겠습니까?')) {
					let input = document.createElement('input');
					input.setAttribute('type', 'hidden');
					input.setAttribute('name', 'checked');
					input.setAttribute('value', checked);
					document.querySelector('#user-delete-form').appendChild(
							input);
					return true;
				} else {
					return false;
				}
			}
		};

		let levelControlForm = document.querySelector('#level-control-form');
		document.querySelector('#approve')
				.addEventListener(
						'click',
						function() {
							let checked = '';
							document.querySelectorAll(
									'input[name=levelChkOne]:checked').forEach(
									function(item) {
										debugger;
										checked += item.value;
										checked += ',';
									});
							if (checked.length === 0) {
								alert('승인할 등업 신청을 선택해주세요.');
							} else {
								let input = document.createElement('input');
								input.setAttribute('type', 'hidden');
								input.setAttribute('name', 'checked');
								input.setAttribute('value', checked);
								document.querySelector('#level-control-form')
										.appendChild(input);
								levelControlForm.setAttribute('action',
										'/admin/levelUpApprove.do');
								levelControlForm.submit();
							}
						});
		document.querySelector('#deny')
				.addEventListener(
						'click',
						function() {
							let checked = '';
							document.querySelectorAll(
									'input[name=levelChkOne]:checked').forEach(
									function(item) {
										checked += item.value;
										checked += ','
									});
							if (checked.length === 0) {
								alert('거절할 등업 신청을 선택해주세요.');
							} else {
								let input = document.createElement('input');
								input.setAttribute('type', 'hidden');
								input.setAttribute('name', 'checked');
								input.setAttribute('value', checked);
								document.querySelector('#level-control-form')
										.appendChild(input);
								levelControlForm.setAttribute('action',
										'/admin/levelUpDeny.do');
								levelControlForm.submit();
							}
						});
	</script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
</body>
</html>
