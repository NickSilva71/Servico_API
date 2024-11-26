package com.projeto.cadastro.dto;

import com.projeto.cadastro.model.UserRole;

public record RegisterDto(String login, String password, UserRole role){
}
