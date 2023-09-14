<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.javaex.vo.PersonVo"%>

<%
	// request 의 어트리뷰트 영역에 있는 data 를 꺼내온다
	List<PersonVo> personList = (List<PersonVo>)request.getAttribute("personList");
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
				<td><a href="/PhoneBook3/PBC?action=updateForm&id=<%=personList.get(i).getId()%>">수정</a></td>
				<td><a href="/PhoneBook3/PBC?action=delete&id=<%=personList.get(i).getId()%>">삭제</a></td>
			</tr>
		</table>
		<br>
	<%}%>
	
	<a href="/PhoneBook3/PBC?action=writeForm">전화번호부 등록폼</a>
</body>
</html>
