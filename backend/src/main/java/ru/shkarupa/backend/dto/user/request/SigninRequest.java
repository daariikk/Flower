package ru.shkarupa.backend.dto.user.request;

import lombok.Data;

@Data
public class SigninRequest {
    private String username;
    private String password;
}