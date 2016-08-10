<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Chatting Web App!</title>

<script src="/resources/js/jquery-1.11.1.min.js" type="text/javascript" ></script>

<script type="text/javascript">

/*
 * 상수 정의
 * - 값이 서버와 매핑되어 있으므로, 한쪽이 바뀌게 되면 다른 한쪽도 똑같이 바꿔줘야 한다.
 */
var CONSTANTS = {
	LOGIN_SUCCESS: 	0,
	LOGIN_FALI: 	1
}

/*
 * 로그인
 * - 입력 정보를 확인해 결과에 따른 처리를 한다.
 */
function doLogin() {
	var userId = $('#userId').val();
	var password = $('#password').val();
	
	if (userId == null || userId.length == 0) {
		alert('ID를 입력하세요.');
		return false;
	}
	
	if (password == null || password.length == 0) {
		alert('비밀번호를 입력하세요.');
		return false;
	}
	
	$.ajax({
		url: '/login',
		type: 'post',
		dataType: 'json',
		data: {
			userId: userId,
			password: password
		},
		success: function(data) {
			if (data.result == CONSTANTS.LOGIN_SUCCESS)
				location.href = '/home';
			else
				alert('계정 정보를 다시 확인해주세요.');
		},
		error: function(jqXHR, status, errorThrown) {
			alert('에러가 발생했습니다.');
		}
	})
}

/**
 * 엔터 처리
 */
function checkEnterKey(event) {
    var keyupEvent = event || window.event;

    if (keyupEvent.keyCode == 13) {
    	doLogin();
    }
}
		
</script>
</head>
<body>

<h1>Welcome to Web Chatting!</h1>

<table>
	<tr>
		<td>ID</td>
		<td><input type="text" id="userId" name="userId" onkeypress="checkEnterKey();"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" id="password" name="password" onkeypress="checkEnterKey();"></td>
	</tr>
	<tr>
		<td colspan="2" align="right"><input type="button" onclick="doLogin();" value="로그인"></td>
	</tr>
</table>

</body>
</html>
