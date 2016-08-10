package com.iskwon.wc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

import com.iskwon.wc.vo.ChatRoomManager;

public class PresenceChannelInterceptor extends ChannelInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(PresenceChannelInterceptor.class);
	
	@Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
		StompHeaderAccessor sha = StompHeaderAccessor.wrap(message);
		
		// ignore non-STOMP messages like heartbeat messages
		if (sha.getCommand() == null) {
			logger.info("~~ [Command is null]");
			return;
		}

		// 이 sessionId는 웹소켓(STOMP)의 sessionId 이다.
		String sessionId = sha.getSessionId();
		switch (sha.getCommand()) {
		case CONNECT:
		case CONNECTED:
			break;
		case DISCONNECT:
			// 브라우저가 비 정상적으로 닫혔을 때 소켓이 끊어지는데, 이 때를 캐치하여 userList에서 제거한다.
			// TODO 삭제는 하지만 접속해 있는 사람들한테 나갔다고 알림을 줘야 한다.
			logger.info("~~ [STOMP Disconnect!! sessionId = {}]", sessionId);
			ChatRoomManager.getInstance().removeUser(sessionId);
			break;
		default:
			break;
		}
	}
	
}
