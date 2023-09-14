<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.javaex.dao.PersonDao2"%>
<%@ page import="com.javaex.vo.PersonVo"%>

<%
p

	PersonDao2 personDao = new PersonDao2();
	List<PersonVo> personList = personDao.personSelect();
	
	System.out.println("personList : " + personList);
%>

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
	
	<%for(int i = 0; i < personList.size(); i ++) {%>
		<table border="1">
			<tr>
				<td>이 름</td>
				<td><%=personList.get(i).getName()%></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><%=personList.get(i).getHp()%></td>
			</tr>
			<tr>
				<td>회사번호</td>
				<td><%=personList.get(i).getCompany()%></td>
			</tr>
			<tr>
				<td><a href="./updateForm.jsp?id=<%=personList.get(i).getId()%>">수정</a></td>
				<td><a href="./delete.jsp?id=<%=personList.get(i).getId()%>">삭제</a></td>
			</tr>
		</table>
		<br>
	<%}%>
	
	<a href="./writeForm.jsp">전화번호부 등록폼</a>
	<!--
		http://localhost:8000/PhoneBook2/writeForm.jsp
		
		(1) / : root (젤 위쪽 부터 http://localhost:8000/) -> 절대경로
		"/writeForm.jsp"
		
		(2) ./ : 현재위치 -> 나를 기준으로. 상대경로
		"./writeForm.jsp"
		
		(3) : (1)과 같음
		"writeForm.jsp"
	-->
</body>
</html>