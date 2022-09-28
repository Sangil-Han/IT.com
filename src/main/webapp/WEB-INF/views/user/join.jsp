<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>IT.com : 회원가입</title>
<link href="/resources/css/header.css" rel="stylesheet">
</head>
<body>
	<div id="wrapper">
		<h1>
			<a href="/home.do">IT.com</a>
		</h1>
		<form action="/user/join.do" method="post" onsubmit="return beforeJoin();">
			<input type="hidden" id="chk-num" value="${chkNum }" />
			<div class="join-input">
				<label for="user-id">아이디</label>
				<input type="text" id="user-id" name="userId" value="${userId }" autocomplete="off" placeholder="영문+숫자 6~20자" required />
				<button type="button" id="id-chk-btn" onclick="checkId();">확인</button>
				<div id="chk-msg">${chkMsg }</div>
			</div>
			<div class="join-input">
				<label for="user-pw">비밀번호</label>
				<input type="password" id="user-pw" name="userPw" autocomplete="off" placeholder="영문+숫자 8~30자 " required />
			</div>
			<div class="join-input">
				<label for="user-pw">비밀번호 확인</label>
				<input type="password" id="user-pw-re" name="userPwRe" autocomplete="off" required />
				<div id="pw-msg"></div>
			</div>
			<div class="join-input">
				<label for="user-email">이메일</label>
				<input type="email" id="user-email" name="userEmail" autocomplete="off" required />
			</div>
			<div class="btn-area">
				<button>가입하기</button>
			</div>
		</form>
		<ul id="user-opt">
			<li><a href="/user/loginView.do">로그인</a></li>
		</ul>
	</div>
	<script>
		
		window.onkeydown = function() {
			let kcode = event.keyCode;
			if (kcode == 116) {
				// history.replaceState({}, null, location.pathname);
				// location.replace(location.href);
				location.href = '/user/joinView.do';
			}
		}

		let idCondition = /^[a-zA-Z0-9]{6,20}$/;
		let idTag = document.querySelector('#user-id');
		idTag.addEventListener('keyup', function(e) {
      document.querySelector('#chk-num').value = '';
			if (idTag.value.length > 0) {
				if (!idTag.value.match(idCondition)) {
					idTag.style.outline = "1px solid red";
				} else{
					idTag.style.outline = "none";
				}
			}
		});

		let pwCondition = /^[a-zA-Z0-9]{8,30}$/;
		let pwTag = document.querySelector('#user-pw');
		pwTag.addEventListener('keyup', function(e) {
			if (pwTag.value.length > 0) {
				if (!pwTag.value.match(pwCondition)) {
					pwTag.style.outline = "1px solid red";
				} else{
					pwTag.style.outline = "none";
				}
			}
		});

		let reTag = document.querySelector('#user-pw-re');
		let pwMsg = document.querySelector('#pw-msg');
		reTag.addEventListener('keyup', function(e) {
			if (pwTag.value != reTag.value) {
				pwMsg.innerHTML = '비밀번호가 일치하지 않습니다.';
				pwMsg.style.color = 'red';
			} else{
				pwMsg.innerHTML = '';
			}
    });

		function checkId() {
			let userId = document.querySelector('#user-id').value;
			if (userId != '') {
				let form = document.createElement('form');
				form.setAttribute('action', '/user/idCheck.do')
				form.setAttribute('method', 'post');
				document.charset = 'UTF-8';

				let input = document.createElement('input');
				input.setAttribute('type', 'hidden');
				input.setAttribute('name', 'userId');
				input.setAttribute('value', userId);
				form.appendChild(input);

				document.body.appendChild(form);
				form.submit();
			}
		}

		function beforeJoin() {
      if (document.querySelector('#chk-num').value === '') {
				document.querySelector('#chk-msg').innerHTML = '아이디 확인을 진행해주세요.';
				return false;
        }
			if (!pwTag.value.match(pwCondition)) {
				return false;
			}
			if (pwTag.value != reTag.value) {
				return false;
			}
      return true;
    }
	</script>
</body>
</html>
