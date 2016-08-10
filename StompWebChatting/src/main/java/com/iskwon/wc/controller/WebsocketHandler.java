package com.iskwon.wc.controller;

import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.iskwon.wc.service.UserService;
import com.iskwon.wc.vo.ChatRoomManager;
import com.iskwon.wc.vo.SessionManager;

// 서버단의 WebSocketHandler 정의
public class WebsocketHandler extends TextWebSocketHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(WebsocketHandler.class);
	
	@Autowired
	private UserService userService;
	
	// 접속하는 사용자에 대한 세션 및 정보를 보관하기 위해 정의
	private SessionManager clients;
	private ChatRoomManager chatRoomManager;

	public WebsocketHandler() {
		super();
		clients = SessionManager.getInstance();
		chatRoomManager = ChatRoomManager.getInstance();
	}

	// 클라이언트에서 접속을 하여 성공할 경우 발생하는 이벤트
	@SuppressWarnings("unchecked")
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		
		logger.info("-> [Session Connected]");

		// 메시지 발송을 위해 세션목록에 추가한다.
		clients.add(session);
		
		Map<String, Object> map = session.getAttributes();
		String userId = (String) map.get("userId");
//		String userName = (String) map.get("userName");
		
//		chatRoomManager.addUserId(userId);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("userId", userId);
		jsonObj.put("message", userId + "님이 입장하셨습니다.");
		jsonObj.put("count", clients.getList().size());
		jsonObj.put("userIdList", chatRoomManager.getUserIdList());
		
		for (WebSocketSession client : clients.getList()) {
			client.sendMessage(new TextMessage(jsonObj.toString()));
		}
		
		logger.info("<- [jsonObj = {}]", jsonObj.toString());
	}

	// 클라이언트에서 send를 이용해서 메시지 발송을 한 경우 이벤트 핸들링
	@SuppressWarnings("unchecked")
	@Override
	protected void handleTextMessage(
			WebSocketSession session,
			TextMessage textMessage) throws Exception {
		
		String payloadMessage = textMessage.getPayload();
		logger.info("-> [payloadMessage = {}]", payloadMessage);
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(payloadMessage);
		
		String message = (String) jsonObj.get("message");
		String userId = (String) jsonObj.get("userId");
		
		jsonObj.put("message", userId + ": " + message);
		
		for (WebSocketSession client : clients.getList()) {
			client.sendMessage(new TextMessage(jsonObj.toString()));
		}
		
		logger.info("<- [sender = {}], [sendMessage = {}]", userId, message);
	}

	// 클라이언트에서 연결을 종료할 경우 발생하는 이벤트
	@SuppressWarnings("unchecked")
	@Override
	public void afterConnectionClosed(
			WebSocketSession session,
			CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		
		logger.info("-> [Session Closed]");
		
		// 메시지 발송 제외를 위해 세션목록에서 삭제한다.
		clients.remove(session);
		
		Map<String, Object> map = session.getAttributes();
		String userId = (String) map.get("userId");
//		chatRoomManager.removeUserId(userId);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("userId", userId);
		jsonObj.put("message", userId + "님이 퇴장하셨습니다.");
		jsonObj.put("count", clients.getList().size());
		jsonObj.put("userIdList", chatRoomManager.getUserIdList());
		
		for (WebSocketSession client : clients.getList()) {
			client.sendMessage(new TextMessage(jsonObj.toString()));
		}
		
		logger.info("<- [jsonObj = {}]", jsonObj.toString());
	}

}