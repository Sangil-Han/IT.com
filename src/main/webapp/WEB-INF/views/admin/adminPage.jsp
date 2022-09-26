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
<link href="/resources/css/header.css" rel="stylesheet">
</head>
<body>
	<div id="wrap">
		<div id="page-title">
			<h2>
				<a href="/admin/adminPageView.do">Admin Page</a>
			</h2>
		</div>
		<ul id="tab-menu">
			<li><a href="#center-control" class="tab-btn">교육원 조회</a></li>
			<li><a href="#user-control" class="tab-btn">회원 관리</a></li>
			<li><a href="#level-control" class="tab-btn">등업 관리</a></li>
		</ul>
		<div id="content-area">
			<div id="center-control" class="tab-content" style="display: none">
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
			<div id="user-control" class="tab-content" style="display: none">
				<h4>회원 관리</h4>
				<form action="/admin/userSearch.do" class="search-option">
					<select name="searchOption" class="search-option">
						<option value="">전체</option>
						<option value="">아이디</option>
						<option value="">이메일</option>
					</select> <input type="text" />
					<button>검색</button>
				</form>
				<form action="/admin/userDeletion.do" method="post">
					<input type="hidden" onsubmit="beforeUserDeletion();">
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
										value="${user.userId }" onclick="checkOnes(this.name);"></td>
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
			<div id="level-control" class="tab-content" style="display: none">
				<h4>등업 관리</h4>
				<form action="">
					<button>승인</button>
					<button>거절</button>
					<table>
						<tr>
							<th>상태</th>
							<th>아이디</th>
							<th>현재등급</th>
							<th>신청등급</th>
							<th>신청일</th>
							<th>첨부파일</th>
							<th><input type="checkbox" id="level-chk-all"
								name="levelChkAll" onclick="checkAll(this.name);" /></th>
						</tr>
						<tr>
							<td>{상태}</td>
							<td>{아이디}</td>
							<td>{현재등급}</td>
							<td>{신청등급}</td>
							<td>{신청일}</td>
							<td>{첨부파일}</td>
							<td><input type="checkbox" name="levelChkOne"
								onclick="checkOnes(this.name);" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<script>
		// 탭 메뉴
		const tabMenu = document.querySelectorAll('#tab-menu li');
		const tabContent = document.querySelectorAll('#content-area > div');

		tabMenu.forEach(function(item, i) {
			item.addEventListener('click', function(e) {
				e.preventDefault();
				showContent(i);
			});
		});

		function showContent(n) {
			tabContent.forEach(function(item) {
				item.style.display = 'none';
			});
			tabContent[n].style.display = 'block';
		}

		// 페이징
		let preContent = '${ tabContent }';

		if (preContent == 'center') {
			showContent(0);
		} else if (preContent == 'user') {
			showContent(1);
		} else if (preContent == 'level') {
			showContent(2);
		}

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
				selectUsers();
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
		function selectUsers() {
			let checkedBoxes = document
					.querySelectorAll('input[name=userChkOne]:checked');
			let checkedUsers = [];
			checkedBoxes.forEach(function(item) {
				checkedUsers.push(item.value);
			});
			console.log(checkedUsers);
			document.querySelector('input#checkedUsers').setAttribute('value',
					checkedUsers);
		};
	</script>
</body>
</html>
