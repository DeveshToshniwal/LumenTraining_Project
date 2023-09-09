package com.example.project.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginRequest {

    @NotBlank
    @Size(min = 3, max = 20, message = "Username must have between 3-20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ,()\\-]{3,20}$", message = "Invalid character entered")
    private String username;

    @NotBlank
    @Size(min = 6, max = 40, message = "Password must have between 6-40 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ,()@\\-]{6,40}$", message = "Invalid character entered")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
