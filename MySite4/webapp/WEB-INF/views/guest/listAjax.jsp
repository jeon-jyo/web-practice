<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listAjax</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
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
			
				<div id="content-head" class="clearfix">
					<h3>�Ϲݹ���</h3>
					<div id="location">
						<ul>
							<li>Ȩ</li>
							<li>����</li>
							<li class="last">ajax����</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form id="guestForm" action="" method="">
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
									<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">���</button></td>
								</tr>
							</tbody>
						</table>
						<!-- //guestWrite -->
					</form>
					
					<!-- <button id="btnGetData">�����Ͱ�������</button> -->
					<div id="guestListArea"></div>
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

<script type="text/javascript">

	// DOM�� �ϼ��Ǿ��� �� -> �׸��� ����
	$(document).ready(function() {
		console.log("ready()");
		fetchList();
		console.log("ready() ��û ��");
	})
	
	// ȭ���� �׸��� �� ��
	$(window).load(function() {
		console.log("load()");
		// fetchList();
		console.log("load() ��û ��");
	})

	// �����Ͱ������� ��ư
	$("#btnGetData").on("click", function() {
		console.log("btnGetData ��ư Ŭ��");
		// fetchList();
	})
	
	// ��� ��ư
	// $("#btnSubmit").on("click", function(e) {})
	// ���� ���� ��ư�� ��쿡�� �̺�Ʈ�� form ���� ��ƾ� �ϰ� �̺�Ʈ�� submlt ����
	$("#guestForm").on("submit", function(e) {
		console.log("guestForm�� submit ��ư Ŭ��");
		/*
			type="submit" ó�� ������ ����� �۵����� �ʰ� �Ѵ�
			-> �۵��ϰ� �ȴٸ� �̺�Ʈ���� submit ����� �꼭 �ٽ� ���ΰ�ħ ��
		*/
		e.preventDefault();
		
		// ������ ����
		let name = $("#input-uname").val();
		let password = $("#input-pass").val();
		let content = $('[name="content"]').val();
		
		let guestVo = {
			name: name,
			password: password,
			content: $('[name="content"]').val()
		}
		
		$.ajax({
			// 1.
			/* url : "${pageContext.request.contextPath}/api/guest/insert?name=" + name + "&password=" + password + "&content=" + content, */
			url : "${pageContext.request.contextPath}/api/guest/insert/",
			type : "get",
			/* contentType : "application/json", */
			// 2.
			/* data : {name: name, password: password, content: content}, */
			data: guestVo,
			
			dataType : "json",
			success : function(result) {
				// �׸���
				render(result, "up");
				
				// �ʱ�ȭ
				$("#input-uname").val("");
				$("#input-pass").val("");
				$('[name="content"]').val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
	
	// ���� ��ư
	// 1. ������ �׷����ϱ� id ���� class�� ��
	// 2. �θ����� �̺�Ʈ�� ��� ��
	// �������� -> �������� ���� (���� �߰��� �������� ���� �� ���)
	$("#guestListArea").on("click", ".btnDelForm",function(e) {
		console.log("guestListArea�� btnDelForm ��ư Ŭ��");
		
		// password, no
		let password = window.prompt("��й�ȣ�� �Է��ϼ���");
		
		// -> ��ư���ٰ� ��ȣ�� ���ܳ��� data-no (or �θ��� ������� ã��)
		let $this = $(this);
		let no = $this.data("no");
		
		let guestVo = {
			no: no,
			password: password
		}
		
		// ajax ��û db�� ����
		$.ajax({
			url : "${pageContext.request.contextPath}/api/guest/delete/",
			type : "get",
			/* contentType : "application/json", */
			data: guestVo,
			
			dataType : "json",
			success : function(guestVo) {
				if(guestVo.no = no) {
					console.log("��������");
					// ȭ�鿡�� ����� or �ٽ� �׸���
					// -> table�� id�� ��
					$("#t" + no).remove();
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
	
	// ajax ����� �̿��ؼ� �����͸� ��û�ϰ� �׸��� (render())
	function fetchList() {
		
		// ������ ���� ���� �����͸� �ް� �ʹ�
		$.ajax({
			// ��û�� �� �ʿ�
			url : "${pageContext.request.contextPath}/api/guest/list",
			type : "get",
			/* contentType : "application/json", */
			/* data : {name: "ȫ�浿"}, */

			// ������� ��
			dataType : "json",
			success : function(guestList) {
				/* ������ ó���ؾ� �� �ڵ� �ۼ� */
				console.log(guestList);
				
				// ����Ʈ + html �׸���
				for(let i = 0; i < guestList.length; i++) {
					render(guestList[i], "down");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	// ������ 1���� �׸���
	function render(guestVo, dir) {
		let str = '';
		str += '<table id="t' + guestVo.no + '" class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>' + guestVo.no + '</td>';
		str += '		<td>' + guestVo.name + '</td>';
		str += '		<td>' + guestVo.regDate + '</td>';
		str += '		<td><button class="btnDelForm" data-no="' + guestVo.no + '">����</button></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">' + guestVo.content + '</td>';
		str += '	</tr>';
		str += '</table>';
		
		if(dir == "up") {
			$("#guestListArea").prepend(str);
		} else if(dir == "down") {
			$("#guestListArea").append(str);
		} else {
			console.log("�߸��Է�");
		}
	}
	
</script>

</html>