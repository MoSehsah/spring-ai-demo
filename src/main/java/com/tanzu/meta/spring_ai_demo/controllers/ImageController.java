package com.tanzu.meta.spring_ai_demo.controllers;

import com.tanzu.meta.spring_ai_demo.models.ChatPrompt;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ImageController {

    public ImageController(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    private final ImageModel imageModel;

    @GetMapping("/image")
    public String chatForm(Model model) {
        return "imageprompt";
    }

    @PostMapping("/image")
    public ResponseEntity<String> transcribe(String prompt) {
//        System.out.println("Prompt: " + prompt);
        ImageResponse response =
                imageModel.call(
                        new ImagePrompt(
                                prompt,
                                OpenAiImageOptions.builder()
                                        .withQuality("hd")
                                        .withN(1)
                                        .withHeight(1024)
                                        .withWidth(1024)
                                        .build()));

        String imageUrl = response.getResult().getOutput().getUrl();
        System.out.println(imageUrl);
        return ResponseEntity.ok(imageUrl);
    }
}
