<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand navbar-dark bg-dark">

  <div class="container">
     <div class="navbar-header">
         <a class="navbar-brand nav-link" href="${pageContext.request.contextPath}/home.do">My Market</a>
     </div>
  </div>

  <div>
      <ul class="navbar-nav mr-auto">
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/login.do">로그인</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/signup.do">회원가입</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/board/list.do">게시판</a></li>
      </ul>
  </div> 
</nav>    