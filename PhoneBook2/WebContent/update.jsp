<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.javaex.dao.PersonDao2"%>
<%@ page import="com.javaex.vo.PersonVo"%>

<%
p

	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String company = request.getParameter("company");
	
	int personId = Integer.parseInt(id);
	PersonVo personVo = new PersonVo(personId, name, hp, company);
	
	System.out.println("수정 값 : " + personVo);
	
	PersonDao2 personDao = new PersonDao2();
	int count = personDao.personUpdate(personVo);
	
	if(count == 1) {
		System.out.println("수정 성공");
	} else {
		System.out.println("수정 실패");
	}
	
	/******************************/
	// 리스트로 이동
	response.sendRedirect("list.jsp");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update</title>
</head>
<body>

</body>
</html>