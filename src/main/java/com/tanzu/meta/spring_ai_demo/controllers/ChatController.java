package com.tanzu.meta.spring_ai_demo.controllers;

import com.tanzu.meta.spring_ai_demo.models.ChatLog;
import com.tanzu.meta.spring_ai_demo.models.ChatPrompt;
import com.tanzu.meta.spring_ai_demo.repositories.ChatLogRepository;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChatController {
    private final ChatModel chatModel;
    private final ChatLogRepository chatLogRepository;

    public ChatController(ChatModel chatModel, ChatLogRepository chatLogRepository) {
        this.chatModel = chatModel;
        this.chatLogRepository = chatLogRepository;
    }


    @GetMapping("/chat")
    public String chatForm(Model model) {
        model.addAttribute("chatPrompt", new ChatPrompt());
        return "chatprompt";
    }

    @PostMapping("/chat")
    public ResponseEntity<?>  promptSubmit(@ModelAttribute ChatPrompt chatPrompt) {
        String result = chatModel.call(chatPrompt.getRequest());

        chatPrompt.setResponse(result);

        //get all chat logs and print them
        Iterable<ChatLog> chatLogs = chatLogRepository.findAll();
        for (ChatLog chatLog : chatLogs) {
            System.out.println(chatLog.getRequest() + " : " + chatLog.getResponse());
        }
        System.out.println("=================================");

        ChatLog chatLog =  new ChatLog();
        chatLog.setRequest(chatPrompt.getRequest());
        chatLog.setResponse(result);
        chatLogRepository.save(chatLog);

        return ResponseEntity.ok(chatPrompt);

    }
}
