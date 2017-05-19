package com.programmingwizzard.lvlup;

import com.programmingwizzard.lvlup.account.Account;
import com.programmingwizzard.lvlup.balance.Balance;
import com.programmingwizzard.lvlup.retrofit.RetrofitConstant;
import com.programmingwizzard.lvlup.token.Token;
import com.programmingwizzard.lvlup.token.TokenRefresher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

public class API {

    private final TokenRefresher refresher;
    private final APIService service;

    API(Endpoint endpoint, String login, String password) {
        this.refresher = new TokenRefresher(this, login, password);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(this::makeResponse).build();

        RetrofitConstant retrofit = new RetrofitConstant(endpoint.getUrl(), client);
        this.service = retrofit.create(APIService.class);

        this.refresher.refresh();
    }

    public Token login() {
        Call<Token> tokenCall = this.service.login(this.refresher.getLogin(), this.refresher.getPassword());
        try {
            return tokenCall.execute().body();
        } catch (IOException ex) {
            throw new APIException(ex);
        }
    }

    public Account account() {
        Call<Account> accountCall = this.service.account();
        try {
            return accountCall.execute().body();
        } catch (IOException ex) {
            throw new APIException(ex);
        }
    }

    public List<Balance> balance() {
        Call<List<Balance>> balanceCall = this.service.balance();
        try {
            return balanceCall.execute().body();
        } catch (IOException ex) {
            throw new APIException(ex);
        }
    }

    private Response makeResponse(Interceptor.Chain chain) throws IOException {
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
                                             .addHeader("Authorization", "Bearer " + content)
                                             .build();
                return chain.proceed(newRequest);
            }
            return chain.proceed(request);
        }
        throw new APIException("Request can not be null!");
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
