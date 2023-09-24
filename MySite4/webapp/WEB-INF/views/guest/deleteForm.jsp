<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteForm</title>
<link href="${pageContext.request.contextPath}//assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}//assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="wrap">

		<!-- header -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<!-- //header -->

		<div id="container" class="clearfix">
		
			<!-- aside -->
			<jsp:include page="/WEB-INF/views/include/asideGuest.jsp"></jsp:include>
			<!-- //aside -->

			<div id="content">
			
				<div id="content-head">
					<h3>�Ϲݹ���</h3>
					<div id="location">
						<ul>
							<li>Ȩ</li>
							<li>����</li>
							<li class="last">�Ϲݹ���</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="guestbook">
					<form action="${pageContext.request.contextPath}/guest/delete" method="get">
						<table id="guestDelete">
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 40%;">
								<col style="width: 25%;">
								<col style="width: 25%;">
							</colgroup>
							<tr>
								<td>��й�ȣ</td>
								<td><input type="password" name="password"></td>
								<td class="text-left"><button type="submit">����</button></td>
								<td><a href="${pageContext.request.contextPath}/guest/addList">[�������� ���ư���]</a></td>
							</tr>
						</table>
						<input type='hidden' name="no" value="${no }">
					</form>
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