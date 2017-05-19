package com.programmingwizzard.lvlup;

import com.programmingwizzard.lvlup.token.Token;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @FormUrlEncoded
    @POST("auth/login/")
    Call<Token> login(@Field("username") String username, @Field("password") String password);

}
