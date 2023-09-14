<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String id = request.getParameter("id");
	int personId = Integer.parseInt(id);
	
	System.out.println("삭제 번호 : " + personId);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteForm</title>
</head>
<body>
	<form action="./delete.jsp" method="get">
		<table>
			<tr>
				<td>비밀번호</td>
				<td><input type="hidden" name="id" value="<%=personId%>"></td>
				<td><input type="password" name="password"></td>
				<td><button type="submit">삭제</button></td>
			</tr>
		</table>
	</form>
	<br><br>
	
	<a href="./addList.jsp">메인으로 돌아가기</a>
</body>
</html>