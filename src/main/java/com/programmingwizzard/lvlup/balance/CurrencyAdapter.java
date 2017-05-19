package com.programmingwizzard.lvlup.balance;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

public class CurrencyAdapter {

    @ToJson
    public String toJson(@CurrencyValue Currency currency) {
        return currency.getValue();
    }

    @FromJson
    @CurrencyValue
    public Currency fromJson(String json) {
        if (json == null || json.isEmpty()) {
            return Currency.UNKNOWN;
        }
        return Currency.fromString(json);
    }

}
