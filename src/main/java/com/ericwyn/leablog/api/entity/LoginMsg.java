package com.ericwyn.leablog.api.entity;

/**
 * Created by Ericwyn on 17-12-13.
 */
public class LoginMsg {
    private boolean Ok;
    private String Token;
    private String UserId;
    private String Email;
    private String Username;
    public void setOk(boolean Ok) {
        this.Ok = Ok;
    }
    public boolean getOk() {
        return Ok;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }
    public String getToken() {
        return Token;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }
    public String getUserId() {
        return UserId;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    public String getEmail() {
        return Email;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }
    public String getUsername() {
        return Username;
    }
}
