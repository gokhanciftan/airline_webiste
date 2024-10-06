package org.example.havayolu.entity.enums;

public enum Roles {
    NORMAL("normal"),
    DAIMI("daimi"),
    VIP("vip");

    private String message;

    Roles(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
