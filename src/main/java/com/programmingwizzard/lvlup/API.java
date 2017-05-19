package com.programmingwizzard.lvlup;

import com.programmingwizzard.lvlup.retrofit.RetrofitConstant;

public class API {

    private final RetrofitConstant retrofit;

    API(Endpoint endpoint, String login, String password) {
        this.retrofit = new RetrofitConstant(endpoint.getUrl());
    }


    protected enum Endpoint {

        PRODUCTION("https://api.lvlup.pro/v3/"),
        DEMO("https://demoapi.lvlup.pro/v3/");

        private final String url;

        Endpoint(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

}
