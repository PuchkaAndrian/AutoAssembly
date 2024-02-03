package com.unnkn0own1.autoassembly.persistence.entity.impl;

import com.google.gson.JsonObject;

import java.time.LocalDate;
import java.util.UUID;

public class User {

    private final UUID id;
    private final String password;
    private final LocalDate birthday;
    private String email;
    private String username;

    public User(UUID id, String password, String email, LocalDate birthday, String username) {
        this.id = id;
        this.password = validatedPassword(password);
        setEmail(email);
        this.birthday = validatedBirthday(birthday);
        setUsername(username);
    }

    public UUID getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // Логiка валiдацiї для email
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        // Логiка валiдацiї для username
        this.username = username;
    }

    private String validatedPassword(String password) {
        // Логiка валiдацiї для password
        return password;
    }

    private LocalDate validatedBirthday(LocalDate birthday) {
        // Логiка валiдацiї для birthday
        return birthday;
    }

    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id.toString());
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("birthday", birthday.toString());
        jsonObject.addProperty("username", username);
        return jsonObject;
    }



}
