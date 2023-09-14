<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteForm</title>
</head>
<body>
	<form action="/GuestBook4/delete" method="get">
		<table>
			<tr>
				<td><input type="hidden" name="no" value="${no }"></td>
				<td><input type="password" name="password"></td>
				<td><button type="submit">삭제</button></td>
			</tr>
		</table>
	</form>
	<br><br>
	
	<a href="/GuestBook4/addList">메인으로 돌아가기</a>
</body>
</html>