package br.com.fiap.foodlivery.foodlivery.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginRequestDTO {
    @NotNull(message = "Login is required")
    private String login;
    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String senha;
}
