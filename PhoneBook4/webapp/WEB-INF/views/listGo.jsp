<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.javaex.dao.PersonDao"%>
<%@ page import="com.javaex.vo.PersonVo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
</head>
<body>
	<h1>전화번호부</h1>
	<h2>리스트</h2>
	
	<p>등록된 전화번호 리스트 입니다.</p>
	
	<c:forEach items="${personList }" var="personVo">
		<table border="1">
			<tr>
				<td>이 름</td>
				<td>${personVo.name }</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>${personVo.hp }</td>
			</tr>
			<tr>
				<td>회사번호</td>
				<td>${personVo.company }</td>
			</tr>
			<tr>
				<td><a href="/PhoneBook4/updateForm/${personVo.personId }">수정</a></td>
				<td><a href="/PhoneBook4/delete/${personVo.personId }">삭제</a></td>
			</tr>
		</table>
		<br>
	</c:forEach>
	
	<a href="/PhoneBook4/writeForm">전화번호부 등록폼</a>
</body>
</html>