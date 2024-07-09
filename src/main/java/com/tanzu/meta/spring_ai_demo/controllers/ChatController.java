package com.tanzu.meta.spring_ai_demo.controllers;

import com.tanzu.meta.spring_ai_demo.models.ChatPrompt;
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

    public ChatController(ChatModel chatModel) {
        this.chatModel = chatModel;
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

        return ResponseEntity.ok(chatPrompt);

    }
}
