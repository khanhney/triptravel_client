package com.example.apple.triptravel.models.chat;

public class Chat {
    private String id;
    private String message;

    public Chat(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public Chat() {
    }

    public String getId() {
        return id;
    }

    public Chat setId(String id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Chat setMessage(String message) {
        this.message = message;
        return this;
    }
}
