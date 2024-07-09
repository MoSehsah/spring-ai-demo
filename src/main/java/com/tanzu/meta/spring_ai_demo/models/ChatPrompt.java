package com.tanzu.meta.spring_ai_demo.models;

public class ChatPrompt {

    private String request;
    private String response;


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }




    public String getRequest() {
        return request;
    }

    public void setRequest(String content) {
        this.request = content;
    }
}
