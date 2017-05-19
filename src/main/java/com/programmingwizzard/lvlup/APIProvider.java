package com.programmingwizzard.lvlup;

public enum APIProvider {

    PRODUCTION {
        @Override
        public API create(String login, String password) {
            return new API(API.Endpoint.PRODUCTION, login, password);
        }
    },

    DEMO {
        @Override
        public API create(String login, String password) {
            return new API(API.Endpoint.DEMO, login, password);
        }
    };

    public abstract API create(String login, String password);

}
