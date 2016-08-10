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

<script type="text/javascript" src="../../resources/js/jquery-1.11.1.min.js"></script>	
<script type="text/javascript" src="../../resources/js/sockjs-1.0.0.js"></script>
<script type="text/javascript" src="../../resources/js/stomp.js"></script>

<script type="text/javascript">

$(document).ready(function() {
	// 페이지 첫 진입 시 화면 처리
	$('#chatArea').hide();
	$('#joinBtn').prop('disabled', false);
	$('#outBtn').prop('disabled', true);
});

var socket = null;
var stompClient = null;
function connect() {
	
	// Create stomp client over sockJS protocol (see Note 1)
	socket = new SockJS("/webchat");
	stompClient = Stomp.over(socket);

	// Connect
	stompClient.connect({}, function(frame) {
		console.log('Connected');
		setJoinView();
		
		stompClient.send('/chat/join', {}, JSON.stringify({ 'userId': '${userId}' }));
		
		// subscribe message
		stompClient.subscribe('/topic/receive', function(message) {
			console.log(message);
			addMessage(message.body);
		});
		
		stompClient.subscribe('/user/topic/private', function(message) {
			console.log('/user/topic/private');
			addMessage(message.body);
		});
	});
}

function disconnect() {
	if (socket != null) {
		stompClient.send('/chat/out', {}, JSON.stringify({ 'userId': '${userId}' }));
		
		stompClient.disconnect();
// 		socket.close(); // 안닫아도 되나?
		
		socket = null;
		stompClient = null;
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
	var receivedObj = JSON.parse(receivedData);
	
	var chatWindow = $('#chatWindow');
	var preChat = chatWindow.val(); 
	chatWindow.val(preChat + '\n' + receivedObj.content);
	
	// 스크롤바 바닥으로 이동
	if(chatWindow.length)
		chatWindow.scrollTop(chatWindow[0].scrollHeight - chatWindow.height());
	
	var userList = receivedObj.userIdList;
	var count = receivedObj.userIdList.length;
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
		
		if (message != null && message.length > 0) {
			var sendObj = { 'userId': '${userId}', 'content': message };
			stompClient.send('/chat/send', {}, JSON.stringify({ 'userId': '${userId}', 'content': message }));
		}
		
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
