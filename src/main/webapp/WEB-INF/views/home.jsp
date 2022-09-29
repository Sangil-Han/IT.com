<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<link rel="stylesheet" type="text/css" href="/resources/css/main.css">
<head>
<script src="https://kit.fontawesome.com/422d96f707.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<title>IT.com : Main</title>
<script>
      window.addEventListener("load", () => {
        var leftBtn = document.querySelector(".leftbtn");
        var arrImg = [
          "resources/img/mainImg1.png",
          "resources/img/mainImg2.png",
          "resources/img/mainImg3.png",
        ];
        var cntleft = 1;
        leftBtn.onclick = function () {
          var mainImg = document.querySelector("#main_image");
          if (cntleft >= arrImg.length) {
            cntleft = 0;
          }
          cntleft++;
          mainImg.style.backgroundImage = "url(" + arrImg[cntleft - 1] + ")";
          /* element css 속성 변경하는 법 object.style.attribute
         원래 css 파일에선 background-image 라고 작성하지만,
         javascript에선 하이픈(-) 권장하지 않기 때문에 대문자로 대체
        */
        };
      });
      // 오른 쪽 버튼 이미지 클릭 시 동작 how
      window.addEventListener("load", () => {
        var rightBtn = document.querySelector(".rightbtn");
        var arrImg = [
          "resources/img/mainImg1.png",
          "resources/img/mainImg2.png",
          "resources/img/mainImg3.png",
        ];
        var cntright = arrImg.length;
        rightBtn.onclick = function () {
          var mainImg = document.querySelector("#main_image");
          cntright--;
          if (cntright < 0) {
            cntright = arrImg.length - 1;
          }
          mainImg.style.backgroundImage = "url(" + arrImg[cntright] + ")";
        };
      });
      
    </script>
</head>
<body>

	<jsp:include page="common/header.jsp"></jsp:include>
<!-- 	<main>
		<div id="main_image">
	        <input type="image" class="leftbtn" src="resources/img/leftbtn.png" />
	        <input type="image" class="rightbtn" src="resources/img/rightbtn.png" />
	    </div>
	</main>

<div class="card" style="width: 18rem;">
  <img src="/resources/img/point.png" class="card-img-top" alt="포인트">
  <div class="card-body">
    <p class="card-text">IT.com 만의 포인트 제도<br>포인트도 받고~ 경험도 쌓고~<br>정보도 얻고~  </p>
  </div>
</div>

<div class="card" style="width: 18rem;">
  <img src="/resources/img/levelup.png" class="card-img-top" alt="등업">
  <div class="card-body">
    <p class="card-text">IT.com 완전 정복<br>상담부터 수료까지<br>등급업으로 달려보자!!</p>
  </div>
</div>

<div class="card" style="width: 18rem;">
  <img src="/resources/img/security.png" class="card-img-top" alt="보안">
  <div class="card-body">
    <p class="card-text">익명 커뮤니티 사이트<br>보안은 철저히<br>하지만 자유롭게</p>
  </div>
</div>
<br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>
<hr>
<table>
	<tfoot>
	<i class="fa-solid fa-desktop fa-4x text-primary"></i>
	<a href="/home.do" style="text-decoration-line: none; font-size: 50px;">IT.com</a>
	<td style="font-size: 25px;">익명 커뮤니티 사이트</td>	
	</tfoot>
</table> -->
</body>
</html>
