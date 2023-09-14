<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.UserVo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
	<%
		UserVo authUser = (UserVo)session.getAttribute("authUser");
	%>
--%>
<div id="header" class="clearfix">
	<h1>
		<a href="main">MySite</a>
	</h1>
	
	<c:if test="${authUser != null }">
		<ul>
			<li>${authUser.name } 님 안녕하세요^^</li>
			<li><a href="user?action=logout" class="btn_s">로그아웃</a></li>
			<li><a href="user?action=modifyForm" class="btn_s">회원정보수정</a></li>
		</ul>
	</c:if>
	<c:if test="${authUser == null }">
		<ul>
			<li><a href="user?action=loginForm" class="btn_s">로그인</a></li>
			<li><a href="user?action=joinForm" class="btn_s">회원가입</a></li>
		</ul>
	</c:if>
</div>