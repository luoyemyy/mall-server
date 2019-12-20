package com.github.luoyemyy.mall.core.entity;

public class WeChatUser {
    private Long id;

    private Long weChatId;

    private Long userId;

    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWeChatId() {
        return weChatId;
    }

    public void setWeChatId(Long weChatId) {
        this.weChatId = weChatId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }
}