package com.programmingwizzard.lvlup;

import com.programmingwizzard.lvlup.retrofit.RetrofitConstant;
import com.programmingwizzard.lvlup.token.Token;
import com.programmingwizzard.lvlup.token.TokenRefresher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;

import java.io.IOException;

public class API {

    private final TokenRefresher refresher;
    private final APIService service;

    API(Endpoint endpoint, String login, String password) {
        this.refresher = new TokenRefresher(this, login, password);
        OkHttpClient client = new OkHttpClient.Builder()
                                      .addInterceptor(chain -> {
                                          Request request = chain.request();
                                          if (request != null) {
                                              if (this.refresher.isPresent()) {
                                                  Token token = refresher.refresh();
                                                  if (token == null) {
                                                      return chain.proceed(request);
                                                  }
                                                  String content = token.getContent();
                                                  if (content == null) {
                                                      return chain.proceed(request);
                                                  }
                                                  Request newRequest = request
                                                                               .newBuilder()
                                                                               .addHeader("Authorization",
                                                                                       "Bearer " + content)
                                                                               .build();
                                                  return chain.proceed(newRequest);
                                              }
                                              return chain.proceed(request);
                                          }
                                          throw new APIException("Request can not be null!");
                                      }).build();

        RetrofitConstant retrofit = new RetrofitConstant(endpoint.getUrl(), client);
        this.service = retrofit.create(APIService.class);
    }

    public Token login() {
        Call<Token> tokenCall = this.service.login(this.refresher.getLogin(), this.refresher.getPassword());
        try {
            Token token = tokenCall.execute().body();
            return token;
        } catch (IOException ex) {
            throw new APIException(ex);
        }
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
