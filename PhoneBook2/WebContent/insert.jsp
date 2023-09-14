<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.javaex.dao.PersonDao"%>
<%@ page import="com.javaex.vo.PersonVo"%>

<%
	// 파라미터 값 꺼내오기
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String company = request.getParameter("company");
	
	// Vo 묶기
	PersonVo personVo = new PersonVo();
	personVo.setName(name);
	personVo.setHp(hp);
	personVo.setCompany(company);
	
	System.out.println("입력 값 : " + personVo);
	
	// Dao를 통해서 데이터 저장
	PersonDao personDao = new PersonDao();
	int count = personDao.personInsert(personVo);
	
	if(count == 1) {
		System.out.println("등록 성공");
	} else {
		System.out.println("등록 실패");
	}
	
	/******************************/
	// 리스트로 이동
	response.sendRedirect("list.jsp");
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