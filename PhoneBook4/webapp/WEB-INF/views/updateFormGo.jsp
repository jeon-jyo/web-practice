<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.javaex.dao.PersonDao"%>
<%@ page import="com.javaex.vo.PersonVo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateFormGo</title>
</head>
<body>
	<h1>전화번호부</h1>
	<h2>수정폼</h2>
	
	<p>정보를 수정하는 폼입니다. 정보를 입력하고 수정 버튼을 누르세요.</p>
	
	<form action="/PhoneBook4/update" method="get">
		<input type="hidden" name="id" value="${personMap.PID }">
		이 름 : <input type="text" name="name" value="${requestScope.personMap.NAME }"><br>
		전화번호 : <input type="text" name="hp" value="${personMap.HP }"><br>
		회사번호 : <input type="text" name="company" value="${personMap.COMPANY }"><br><br>
		
		<button type="submit">수정</button><br><br>
	</form>
	
	<a href="/PhoneBook4/list">전화번호부 리스트</a>
</body>
</html>