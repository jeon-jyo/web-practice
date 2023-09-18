<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addList</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="wrap">

		<!-- header -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<!-- //header -->
	
		<div id="container" class="clearfix">
			<div id="aside">
				<h2>����</h2>
				<ul>
					<li>�Ϲݹ���</li>
					<li>ajax����</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
			
				<div id="content-head" class="clearfix">
					<h3>�Ϲݹ���</h3>
					<div id="location">
						<ul>
							<li>Ȩ</li>
							<li>����</li>
							<li class="last">�Ϲݹ���</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="${pageContext.request.contextPath}/guest/insert" method="get">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">�̸�</label></th>
									<td><input id="input-uname" type="text" name="name" value=""></td>
									<th><label class="form-text" for="input-pass">�н�����</label></th>
									<td><input id="input-pass"type="password" name="password" value=""></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button type="submit">���</button></td>
								</tr>
							</tbody>
						</table>
						<!-- //guestWrite -->
					</form>	
					
					<c:forEach items="${guestList }" var="guestVo">
					<table class="guestRead">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 40%;">
							<col style="width: 10%;">
						</colgroup>
						<tr>
							<td>${guestVo.no }</td>
							<td>${guestVo.name }</td>
							<td>${guestVo.regDate }</td>
							<td><a href="${pageContext.request.contextPath}/guest/deleteForm/${guestVo.no }">����</a></td>
						</tr>
						<tr>
							<td colspan=4 class="text-left">${guestVo.content }</td>
						</tr>
					</table>
					</c:forEach>
				</div>
				<!-- //guestbook -->
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- footer -->
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		<!-- //footer -->
		
	</div>
	<!-- //wrap -->
</body>
</html>