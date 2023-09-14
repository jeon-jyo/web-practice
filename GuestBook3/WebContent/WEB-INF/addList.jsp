<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.javaex.dao.GuestDao"%>
<%@ page import="com.javaex.vo.GuestVo"%>

<%
	List<GuestVo> guestList = (List<GuestVo>)request.getAttribute("guestList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addList</title>
</head>
<body>
	<form action="/GuestBook3/GBC" method="get">
		<input type="hidden" name="action" value="insert">
		<table border="1" width="540px">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="4"><textarea cols="72" rows="5" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="4"><button type="submit">등록</button></td>
			</tr>
		</table>
	</form>
	<br>

	<%for(int i = 0; i < guestList.size(); i ++) {%> 
	<table border="1" width="540px">
		<tr>
			<td><%=guestList.get(i).getNo()%></td>
			<td><%=guestList.get(i).getName()%></td>
			<td><%=guestList.get(i).getRegDate()%></td>
			<td><a href="/GuestBook3/GBC?action=deleteForm&id=<%=guestList.get(i).getNo()%>">삭제</a></td>
		</tr>
		<tr>
			<td colspan="4"><%=guestList.get(i).getContent()%></td>
		</tr>
	</table>
	<%}%>
</body>
</html>