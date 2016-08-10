<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Chatting Web App!</title>

<style type="text/css">

.chat-area {
	width: 800px;
	min-width: 800px;
}

.chat-window {
	width: 400px;
	float: left;
}

.chat-list {
	width: 290px;
	float: left;
}

.chat-area-textarea {
	background-color: #fff;
	resize: none;
}

</style>

<script type="text/javascript" src="/resources/js/jquery-1.11.1.min.js"></script>	

<script type="text/javascript">

var userId = null;
$(document).ready(function() {
	// 페이지 첫 진입 시 화면 처리
	$('#chatArea').hide();
	$('#joinBtn').prop('disabled', false);
	$('#outBtn').prop('disabled', true);
	
	userId = '${userId}';
});

var socket = null;
function connect() {
	var url = 'ws://10.1.2.58:8080/chat'; // websocket
// 	var url = 'http://10.1.2.58:8080/chat'; // sockjs
	
// 	if ('WebSocket' in window) {
// 		socket = new SockJS(url);
		socket = new WebSocket(url);
		
		socket.onopen = function() {
			console.log('Connected');
			sendMessage();
			setJoinView();
		}

		socket.onmessage = function(msg) {
			addMessage(msg.data);
		}
// 	}
// 	else {
// 		alert('지원하지 않는 브라우저입니다. IE 10 이상이나 크롬을 이용하세요.');
// 	}
}

function disconnect() {
	if (socket != null) {
		
		socket.onclose = function(evt) {
			console.log(evt);
			console.log('Closed');
			setOutView();
		}
		
		console.log('Disconnect');
		socket.close();
		socket = null;
		setOutView();
	}
	else {
		alert("Disconnected!");
	}
}

/*
 * 서버에서 메시지를 받았을 때 채팅창에 내용 추가
 */
function addMessage(receivedData) {
	var data = $.parseJSON(receivedData);
	
	var chatWindow = $('#chatWindow');
	var preChat = chatWindow.val(); 
	chatWindow.val(preChat + '\n' + data.message);
	
	// 스크롤바 바닥으로 이동
	if(chatWindow.length)
		chatWindow.scrollTop(chatWindow[0].scrollHeight - chatWindow.height());
	
	var count = data.count;
	var userList = data.userIdList;
	if (count != null && typeof count != 'undefined' && count != '') {
		$('#userCount').html(count + '명 접속 중');
		
		var sortedUserList = [];
		
		var userListHtml = '';
		var userId = null;
		for (var idx = 0; idx < userList.length; idx++) {
			userId = userList[idx];
			if (userId == '${userId}')
				sortedUserList.unshift(userId + '(나)<br/>');
			else
				sortedUserList.push(userId + '<br/>');
		}
		
		for (var idx = 0; idx < sortedUserList.length; idx++) {
			userListHtml += sortedUserList[idx];
		}
		
		$('#userList').html(userListHtml);
	}
}

/*
 * 메시지 전송
 */
function sendMessage() {
	if (socket != null) {
		var message = $('#message').val();
		
		if (message != null && message.length > 0)
			socket.send(JSON.stringify({ 
					'message': message,
					'userId': userId 
				})
			);
		
		// 메시지 전송후 입력창 비움
		$('#message').val('');
	}
	else {
		alert("Disconnected!");
	}
}

/*
 * 채팅방 참여 시 화면 처리
 */
function setJoinView() {
	$('#chatArea').show();
	$('#joinBtn').prop('disabled', true);
	$('#outBtn').prop('disabled', false);
}

/*
 * 채팅방 나갈 시 화면 처리
 */
function setOutView() {
	$('#chatArea').hide();
	$('#joinBtn').prop('disabled', false);
	$('#outBtn').prop('disabled', true);
	$('#chatWindow').val('');
}

/**
 * 엔터 처리
 */
function checkEnterKey(event) {
    var keyupEvent = event || window.event;

    if (keyupEvent.keyCode == 13) {
    	sendMessage();
    }
}

</script>
	
</head>
<body>

<h1>Chatting Home!</h1>

<div>
	<table>
		<tr>
			<td><input type="button" id="joinBtn" value="채팅 참여" onclick="connect();"></td>
			<td><input type="button" id="outBtn" value="나가기" onclick="disconnect();"></td>
		</tr>
	</table>
</div>

<div class="chat-area" id="chatArea">	
	<div class="chat-window">
		<table>
			<tr>
				<td>
					<textarea class="chat-area-textarea" id="chatWindow" rows="20" cols="50" disabled="disabled"></textarea>
				</td>
			</tr>
			<tr>
				<td width="100%">
					<input type="text" id="message" name="message" size="42" onkeypress="checkEnterKey();">
					<input type="button" value="전송" onclick="sendMessage();">
				</td>
			</tr>
		</table>
	</div>
	<div class="chat-list">
		<table style="border: 1px solid #999999; min-height: 305px; margin-top: 3px;">
			<tr>
				<td id="userCount" width="200px" style="border-bottom: 1px solid #999999;"></td>
			</tr>
			<tr height="100%" style="vertical-align: text-top;">
				<td width="200px">
					<span id="userList" style="padding-top: 3px;"></span>
				</td>
			</tr>
		</table>
	</div>
</div>

</body>
</html>
