<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>IT.com : 공지사항 게시판</title>
    <link href="/resources/css/header.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
  </head>
  <body>
    <div id="wrap">
      <jsp:include page="../common/header.jsp"></jsp:include>
        <h2 id="page-title" class="text-bg-primary my-3 p-3 bg-opacity-50 text-center">
          <a href="/notice/boardView.do" style="text-decoration: none; color:black;">공지사항</a>
        </h2>
      <div class="container">
      <div class="row">
        <div class="col-lg-9 offset-lg-1">
          <!-- 검색 옵션 -->
          <form action="/notice/search.do" class="search-option">
            <div class="row">
              <div class="col-lg-2">
                <select name="searchOption" class="form-select search-option" aria-label="Default select example">
                  <option value="all">전체</option>
                  <option value="title">제목</option>
                  <option value="contents">내용</option>
                </select>
              </div>
              <div class="col-lg-7">
                <div class="input-group mb-3">
                  <input type="text" class="form-control" name="searchValue" value="${searchValue }" aria-describedby="button-addon2" />
                  <button class="btn btn-outline-secondary" id="button-addon2"><i class="fa-sharp fa-solid fa-magnifying-glass"></i></button>
                </div>
              </div>
            </div>
          </form>
        </div>
        <div class="col-lg-2">
          <!--글쓰기 버튼-->
          <c:if test="${sessionScope.loginAdmin ne null}">
            <button class="btn btn-primary" onclick="location.href='/notice/writeView.do'">글쓰기</button>
          </c:if>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-10 offset-lg-1">
          <table class="table text-center table-bordered">
            <thead class="table-primary">
              <tr class="text-center">
                <th scope="col" width="10%">NO</th>
                <th scope="col" width="50%">제목</th>
                <th scope="col" width="40%">등록일</th>
              </tr>
            </thead>
            <tbody>
              <c:if test="${not empty nList }">
                <c:forEach items="${nList }" var="notice" varStatus="i">
                  <tr>
                    <td>${npi.rowCount - ((npi.currentPage - 1 ) * npi.rowLimit + i.index)}</td>
                    <td><a href="/notice/detailView.do?noticeNo=${notice.noticeNo }&page=${npi.currentPage }" style="text-decoration: none; color: black">${notice.noticeTitle}</a></td>
                    <td>${notice.createDate }</td>
                  </tr>
                </c:forEach>
              </c:if>
              <c:if test="${empty nList }">
          <tr>
            <td colspan="4">공지사항이 존재하지 않습니다.</td>
          </tr>
        </c:if>
            </tbody>
          </table>
        </div>
      </div>


      <div class="row">
        <nav aria-label="Page navigation example">
          <ul class="pagination" style="justify-content: center;">
            <c:if test="${npi.startPage ne 1 }">
              <li class="page-item">
                <a class="page-link" href="/notice/boardView.do?page=${npi.startPage - 1 }" aria-label="Previous">
                  <span aria-hidden="true">&laquo;</span>
                </a> 
              </li>
            </c:if>
            
            <c:forEach var="p" begin="${npi.startPage }" end="${npi.endPage }">
              <c:if test="${npi.currentPage eq p }">
                <li class="page-item"><a class="page-link" href="#">${p }</a></li>
              </c:if>
              <c:if test="${npi.currentPage ne p }">
                <li class="page-item"><a class="page-link" href="/notice/boardView.do?page=${p }">${p }</a></li>
              </c:if>
            </c:forEach>
            <c:if test="${npi.endPage ne npi.pageCount }">
              <li class="page-item">
                <a class="page-link" href="/notice/boardView.do?page=${npi.endPage + 1 }" aria-label="Next">
                  <span aria-hidden="true">&raquo;</span>
                </a>
              </li>
            </c:if>
          </ul>
        </nav>
      </div>

      <!-- <form action="/notice/search.do" class="search-option">
        <select name="searchOption" class="search-option">
          <option value="all">전체</option>
          <option value="title">제목</option>
          <option value="contents">내용</option>
        </select>
        <input type="text" />
        <button>검색</button>
      </form>
      <c:if test="${sessionScope.loginAdmin ne null}">
        <button onclick="location.href='/notice/writeView.do'">글쓰기</button>
      </c:if>
      <table width="100%">
        <tr>
          <th width="10%">NO</th>
          <th width="50%">제목</th>
          <th width="40%">등록일</th>
        </tr>
        <c:if test="${not empty nList }">
          <c:forEach items="${nList }" var="notice" varStatus="i">
            <tr>
              <td>${npi.rowCount - ((npi.currentPage - 1 ) * npi.rowLimit + i.index) }</td>
              <td><a href="/notice/detailView.do?noticeNo=${notice.noticeNo }&page=${npi.currentPage }">${notice.noticeTitle}</a></td>
              <td>${notice.createDate }</td>
            </tr>
          </c:forEach>
          <tr>
            <td colspan="4">
              <c:if test="${npi.startPage ne 1 }">
                <a href="/notice/boardView.do?page=${npi.startPage - 1 }">[이전]</a>
              </c:if>
              <c:forEach var="p" begin="${npi.startPage }" end="${npi.endPage }">
                <c:if test="${npi.currentPage eq p }">
                  <b>${p }</b>
                </c:if>
                <c:if test="${npi.currentPage ne p }">
                  <a href="/notice/boardView.do?page=${p }">${p }</a>
                </c:if>
              </c:forEach>
              <c:if test="${npi.endPage ne npi.pageCount }">
                <a href="/notice/boardView.do?page=${npi.endPage + 1 }">[다음]</a>
              </c:if>
            </td>
          </tr>
        </c:if>
        <c:if test="${empty nList }">
          <tr>
            <td colspan="4">공지사항이 존재하지 않습니다.</td>
          </tr>
        </c:if>
      </table> -->
    </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/422d96f707.js" crossorigin="anonymous"></script>
  </body>
</html>
