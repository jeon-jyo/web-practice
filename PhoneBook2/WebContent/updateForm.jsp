<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.javaex.dao.PersonDao2"%>
<%@ page import="com.javaex.vo.PersonVo"%>

<%
p

	String id = request.getParameter("id");
	int personId = Integer.parseInt(id);
	
	System.out.println("수정 번호 : " + personId);
	
	PersonDao2 personDao = new PersonDao2();
	PersonVo personVo = personDao.personSelectOne(personId);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateForm</title>
</head>
<body>
	<h1>전화번호부</h1>
	<h2>수정폼</h2>
	
	<p>정보를 수정하는 폼입니다. 정보를 입력하고 수정 버튼을 누르세요.</p>
	
	<form action="./update.jsp" method="get">
		<input type="hidden" name="id" value="<%=personVo.getId()%>">
		이 름 : <input type="text" name="name" value="<%=personVo.getName()%>"><br>
		전화번호 : <input type="text" name="hp" value="<%=personVo.getHp()%>"><br>
		회사번호 : <input type="text" name="company" value="<%=personVo.getCompany()%>"><br><br>
		
		<button type="submit">수정</button><br><br>
	</form>
	
	<a href="./list.jsp">전화번호부 리스트</a>
</body>
</html>