package com.programmingwizzard.lvlup.retrofit;

import com.programmingwizzard.lvlup.moshi.MoshiConstant;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class RetrofitConstant {

    private final Retrofit retrofit;

    public RetrofitConstant(String url, OkHttpClient client) {
        MoshiConstant moshi = new MoshiConstant();

        this.retrofit = new Retrofit.Builder()
                                .baseUrl(url)
                                .client(client)
                                .addConverterFactory(moshi.converter())
                                .build();
    }

    public RetrofitConstant(String url) {
        this(url, new OkHttpClient());
    }

    public <T> T create(Class<T> tClass) {
        return this.retrofit.create(tClass);
    }

}
