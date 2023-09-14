<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.javaex.dao.PersonDao"%>

<%
	// 파라미터 꺼내기 (DB전달해서 삭제할 id값)
	String id = request.getParameter("id");
	int personId = Integer.parseInt(id);
	
	System.out.println("삭제 번호 : " + personId);
	
	PersonDao personDao = new PersonDao();
	int count = personDao.personDelete(personId);
	
	if(count == 1) {
		System.out.println("삭제 성공");
	} else {
		System.out.println("삭제 실패");
	}
	
	/******************************/
	// 리스트로 이동
	response.sendRedirect("list.jsp");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>delete</title>
</head>
<body>

</body>
</html>