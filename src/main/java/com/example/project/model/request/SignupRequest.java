package com.example.project.model.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20, message = "Username must have between 3-20 alphanumeric characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ,()\\-]{3,20}$", message = "Username can contain only alphanumeric characters, spaces, and dashes")
    private String username;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40, message = "Password needs to be between 6 and 40 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ,()@\\-]{6,40}$", message = "Password can contain only alphanumeric characters, spaces, and dashes and @ only")
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

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
