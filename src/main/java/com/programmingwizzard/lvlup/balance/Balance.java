package com.programmingwizzard.lvlup.balance;

import com.squareup.moshi.Json;

public class Balance {

    @Json(name = "currency")
    @CurrencyValue
    private final Currency currency;

    @Json(name = "balance")
    private final double balance;

    @Json(name = "balance_pretty")
    private final String prettyBalance;

    public Balance(Currency currency, int balance, String prettyBalance) {
        this.currency = currency;
        this.balance = balance;
        this.prettyBalance = prettyBalance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getBalance() {
        return balance;
    }

    public String getPrettyBalance() {
        return prettyBalance;
    }
}
