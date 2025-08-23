package com.microservice.User.Models.DTO;

import com.microservice.User.Models.Entities.UserRole;

import java.util.UUID;

public record RegisterDTO(UUID id, String email, String senha, UserRole role) {
}
