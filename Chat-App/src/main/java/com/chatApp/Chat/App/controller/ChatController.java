package com.chatApp.Chat.App.controller;

import com.chatApp.Chat.App.model.ChatInMessage;
import com.chatApp.Chat.App.model.ChatOutMessage;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatController {
    @MessageMapping("/guestchat")//mapped handler method
    @SendTo("/topic/guestchats") //mapped subscription queue
    public ChatOutMessage handleMessaging(ChatInMessage message) throws Exception{
        Thread.sleep(1000);
        message=null;
        message.getMessage();
        return new ChatOutMessage(HtmlUtils.htmlEscape(message.getMessage()));
    }

    @MessageMapping("/guestupdate")
    @SendTo("/topic/guestupdates")
    public ChatOutMessage handleUserIsTyping(ChatInMessage message)throws Exception{
        return new ChatOutMessage("Someone is typing...");
    }

    @MessageMapping("/guestjoin")
    @SendTo("/topic/guestnames")
    public ChatOutMessage handleMemberJoins(ChatInMessage message) throws Exception {
        return new ChatOutMessage(message.getMessage());
    }

    @MessageExceptionHandler
    @SendTo("/topic/errors")
    public ChatOutMessage handleException(Throwable exception){
        ChatOutMessage myError = new ChatOutMessage("An error has occurred: " + exception.getMessage());
        return myError;
    }
}
