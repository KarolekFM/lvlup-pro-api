package com.programmingwizzard.lvlup.token;

import com.programmingwizzard.lvlup.API;
import com.programmingwizzard.lvlup.APIException;

import java.util.concurrent.TimeUnit;

public class TokenRefresher {

    private final API api;

    private final String login;
    private final String password;

    // TODO: configuration
    private final TokenCache cache = new TokenCache(15, TimeUnit.MINUTES);

    private boolean present;

    public TokenRefresher(API api, String login, String password) {
        this.api = api;
        this.login = login;
        this.password = password;
    }

    public Token refresh() {
        return this.cache.get();
    }

    public boolean isPresent() {
        return this.present;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    private class TokenCache {

        private final int value;
        private final TimeUnit unit;

        private long expire;
        private Token token;

        public TokenCache(int value, TimeUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        public Token get() {
            if (System.currentTimeMillis() > expire) {
                token = null;

                if (present) {
                    present = false;
                }
            }
            if (expire == 0 || token == null) {
                token = TokenRefresher.this.api.login();

                this.expire = this.calc();
                if (!present) {
                    present = true;
                }
            }
            return token;
        }

        private long calc() {
            long time = System.currentTimeMillis();

            switch (unit) {
                case MILLISECONDS:
                    time = time + value;
                    break;
                case SECONDS:
                    time = time + value * 1000;
                    break;
                case MINUTES:
                    time = time + (value * 1000) * 60;
                    break;
                case HOURS:
                    time = time + ((value * 1000) * 60) * 60;
                    break;
                case DAYS:
                    time = time + (((value * 1000) * 60) * 60) * 24;
                    break;
                default:
                    throw new APIException("Not implementable yet!");
            }
            return time;
        }

    }
}
