package com.cetur.model;

/**
 * Created by Fatih on 2.8.2015.
 */
public class LoginResponse {
    private Person person;
    private String code;
    private String message;
    private String description;
    private String acToken;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAcToken() {
        return acToken;
    }

    public void setAcToken(String acToken) {
        this.acToken = acToken;
    }
}
