<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>writeForm</title>
</head>
<body>
	<h1>전화번호부</h1>
	<h2>등록폼</h2>
	
	<p>정보를 등록하는 폼입니다. 정보를 입력하고 등록 버튼을 누르세요.</p>
	
	<form action="/PhoneBook4/write" method="get">
		이 름 : <input type="text" name="name" value=""><br>
		전화번호 : <input type="text" name="hp" value=""><br>
		회사번호 : <input type="text" name="company" value=""><br><br>
		
		<button type="submit">등록</button><br><br>
	</form>
	
	<a href="/PhoneBook4/list">전화번호부 리스트</a>
</body>
</html>