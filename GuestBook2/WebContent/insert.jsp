<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.javaex.dao.GuestDao"%>
<%@ page import="com.javaex.vo.GuestVo"%>

<%
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String content = request.getParameter("content");

	GuestVo guestVo = new GuestVo();
	guestVo.setName(name);
	guestVo.setPassword(password);
	guestVo.setContent(content);
	
	System.out.println("입력 값 : " + guestVo);

	GuestDao guestDao = new GuestDao();
	int count = guestDao.guestInsert(guestVo);
	
	if(count == 1) {
		System.out.println("등록 성공");
	} else {
		System.out.println("등록 실패");
	}

	response.sendRedirect("addList.jsp");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert</title>
</head>
<body>

</body>
</html>