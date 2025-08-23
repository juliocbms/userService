package com.microservice.User.Models.DTO;

import com.microservice.User.Models.Entities.UserRole;

public record RegisterDTO(Long id,String email, String senha, UserRole role) {
}
