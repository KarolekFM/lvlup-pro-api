package com.programmingwizzard.lvlup.token;

import com.squareup.moshi.Json;

public class Token {

    @Json(name = "token")
    private final String content;

    public Token(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
