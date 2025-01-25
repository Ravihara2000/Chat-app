package com.chatApp.Chat.App.controller;

import com.chatApp.Chat.App.model.ChatInMessage;
import com.chatApp.Chat.App.model.ChatOutMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/guestchat")//mapped handler method
    @SendTo("/topic/guestchats") //mapped subscription queue
    public ChatOutMessage handleMessaging(ChatInMessage message) throws Exception{
        Thread.sleep(1000);

        return new ChatOutMessage(message.getMessage());
    }

    @MessageMapping("/guestupdate")
    @SendTo("/topic/guestupdates")
    public ChatOutMessage handleUserIsTyping(ChatInMessage message)throws Exception{
        return new ChatOutMessage("Someone is typing...");
    }
}
