package com.iskwon.wc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.iskwon.wc.common.Utility;
import com.iskwon.wc.vo.Chat;
import com.iskwon.wc.vo.ChatRoomManager;
import com.iskwon.wc.vo.User;

@Controller
public class WebChatController {
	
	private static final Logger logger = LoggerFactory.getLogger(WebChatController.class);
	
	private ChatRoomManager chatRoomManager = ChatRoomManager.getInstance();
	
	/**
	 * 채팅방 참여
	 */
	@MessageMapping("/join")
	@SendTo("/topic/receive")
	public Chat wsJoin(User user, Message<?> message) {
		
		StompHeaderAccessor sha = StompHeaderAccessor.wrap(message);
		String sessionId = sha.getSessionId();
		String userId = user.getUserId();
		
		logger.info("-> [userId = {}], [sessionId = {}]", userId, sessionId);
		
		chatRoomManager.addUser(sessionId, userId);
		Chat resChat = new Chat(userId, userId + "님이 입장하셨습니다.", chatRoomManager.getUserIdList());
		
		logger.info("<- [resChat = {}]", resChat.toString());
		return resChat;
	}
	
	/**
	 * 채팅 메시지 보내기
	 */
	@MessageMapping("/send")
	@SendTo("/topic/receive")
	public Chat wsSend(Chat reqChat) {
		
		String userId = reqChat.getUserId();
		String content = reqChat.getContent();
		
		logger.info("-> [userId = {}], [content = {}]", new Object[] { userId, content });
		
		Chat chatResult = new Chat(userId, "[" + userId + "] " + Utility.swearwordFilter(content), chatRoomManager.getUserIdList());
		
		logger.info("<- [resChat = {}]", chatResult.toString());
		return chatResult;
	}
	
	/**
	 * 채팅방 나가기
	 */
	@MessageMapping("/out")
	@SendTo("/topic/receive")
	public Chat wsOut(User user, Message<?> message) {
		
		StompHeaderAccessor sha = StompHeaderAccessor.wrap(message);
		String sessionId = sha.getSessionId();
		String userId = user.getUserId();
		
		logger.info("-> [userId = {}], [sessionId = {}]", userId, sessionId);
		
		chatRoomManager.removeUser(sessionId);
		Chat resChat = new Chat(userId, userId + "님이 퇴장하셨습니다.", chatRoomManager.getUserIdList());
		
		logger.info("<- [resChat = {}]", resChat.toString());
		return resChat;
	}
	
}
