package com.apipousada.pousadacompletateste.model;

public class LoginResponse {
    
    private String token;
    
    //contructor
    public LoginResponse(String token) {
        this.token = token;
    }
    //getter e setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
