package com.programmingwizzard.lvlup.moshi;

import com.programmingwizzard.lvlup.balance.CurrencyAdapter;
import com.squareup.moshi.Moshi;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MoshiConstant {

    private final MoshiConverterFactory converter;

    public MoshiConstant() {
        Moshi moshi = new Moshi.Builder()
                              .add(new CurrencyAdapter())
                              .build();

        this.converter = MoshiConverterFactory.create(moshi);
    }

    public MoshiConverterFactory converter() {
        return this.converter;
    }

}
