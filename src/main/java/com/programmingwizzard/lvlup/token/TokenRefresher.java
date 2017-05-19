package com.programmingwizzard.lvlup.token;

import com.programmingwizzard.lvlup.API;

public class TokenRefresher {

    private final API api;

    private final String login;
    private final String password;

    // TODO: cache
    private Token token;

    public TokenRefresher(API api, String login, String password) {
        this.api = api;
        this.login = login;
        this.password = password;
    }

    public Token refresh() {
        // TODO: cache
        return this.api.login();
    }

    public boolean isPresent() {
        return this.token != null;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
