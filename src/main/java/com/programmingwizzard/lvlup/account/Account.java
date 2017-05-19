package com.programmingwizzard.lvlup.account;

import com.squareup.moshi.Json;

public class Account {

    @Json(name = "id")
    private final int id;

    @Json(name = "fullname")
    private final String fullname;

    @Json(name = "username")
    private final String username;

    @Json(name = "email")
    private final String email;

    public Account(int id, String fullname, String username, String email) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
