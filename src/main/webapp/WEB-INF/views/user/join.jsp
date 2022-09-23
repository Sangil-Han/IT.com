<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>IT.com : 회원가입</title>
    <link rel="stylesheet" href="/resources/css/user.css" />
  </head>
  <body>
    <div id="wrapper">
      <h1><a href="/home.do">IT.com</a></h1>
      <form action="/user/join.do" method="post">
        <div class="join-input">
          <label for="user-id">아이디</label>
          <input type="text" id="user-id" name="userId" autocomplete="off" required />
        </div>
        <div class="join-input">
          <label for="user-pw">비밀번호</label>
          <input type="password" id="user-pw" name="userPw" autocomplete="off" required />
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
  </body>
</html>
