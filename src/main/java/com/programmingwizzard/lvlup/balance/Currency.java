package com.programmingwizzard.lvlup.balance;

public enum Currency {

    PLN("PLN"),
    UNKNOWN("UNKNOWN");

    private final String value;

    Currency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Currency fromString(String value) {
        for (Currency currency : values()) {
            if (currency.getValue().equals(value)) {
                return currency;
            }
        }
        return UNKNOWN;
    }
}
