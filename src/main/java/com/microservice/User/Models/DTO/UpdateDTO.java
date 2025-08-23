package com.microservice.User.Models.DTO;

import com.microservice.User.Models.Entities.UserRole;

public record UpdateDTO(String email, String senha, UserRole role) {
}
