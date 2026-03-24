package br.com.fiap.foodlivery.foodlivery.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdatePasswordDTO {

    @NotBlank(message = "Senha atual é obrigatória")
    private String senhaAtual;
    @NotBlank(message = "Nova senha é obrigatória")
    @Size(min = 8, message = "A nova senha deve ter pelo menos 8 caracteres")
    private String senhaNova;
    @NotBlank(message = "Confirmação de senha é obrigatória")
    private String confirmarSenha;
}
