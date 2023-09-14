<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.javaex.dao.GuestDao"%>
<%@ page import="com.javaex.vo.GuestVo"%>

<%
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	int personId = Integer.parseInt(id);
	GuestDao guestDao = new GuestDao();
	GuestVo guestVo = guestDao.guestSelectOne(personId);
	
	System.out.println("기존 정보 : " + guestVo);
	
	if(guestVo.getPassword().equals(password)) {
		
		System.out.println("비밀번호 일치 : " + password);
		
		int count = guestDao.guestDelete(personId);
		
		if(count == 1) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
	} else {
		System.out.println("비밀번호 불일치 : " + password);
	}
	
	response.sendRedirect("addList.jsp");
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